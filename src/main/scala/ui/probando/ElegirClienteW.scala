package ui.probando
import ui.CrearClienteW
import ui.EditarClienteW

import domain._
import applicationModel.SeleccionarCliente
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.WindowOwner
import collection.JavaConversions._
import java.awt.Color
import org.uqbar.arena.layout.ColumnLayout
import org.uqbar.arena.layout.HorizontalLayout
import org.uqbar.arena.bindings.NotNullObservable
import org.uqbar.arena.bindings.ObservableProperty
import org.uqbar.arena.bindings.PropertyAdapter
import org.uqbar.arena.widgets.CheckBox
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.widgets.Selector
import org.uqbar.arena.widgets.TextBox
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.arena.layout.ColumnLayout
import org.uqbar.commons.utils.ApplicationContext
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.actions.MessageSend
import collection.JavaConversions._
import org.uqbar.arena.widgets.tables.Column
import org.uqbar.arena.widgets.tables.Table
import applicationModel.GestorDeCompra
import home.HomeClientes
import applicationModel.ElegirClienteYEntrada

class ElegirClienteW(parent: WindowOwner, unElector: ElegirClienteYEntrada) extends Dialog[SeleccionarCliente](parent, new SeleccionarCliente(unElector)) {

  getModelObject.search

  override def createMainTemplate(mainPanel: Panel) = {
    this.setTitle("Seleccion de Cliente")
    super.createMainTemplate(mainPanel)

    this.createResultsGrid(mainPanel)
    this.createGridActions(mainPanel)
  }

  override def createFormPanel(mainPanel: Panel) = {
    var form = new Panel(mainPanel)
    form.setLayout(new ColumnLayout(2))

    var labelNumero = new Label(form)
    labelNumero.setText("DNI")
    labelNumero.setForeground(Color.BLUE)
    new TextBox(form).bindValueToProperty("dni")

    var labelNombre = new Label(form)
    labelNombre.setText("Nombre y Apellido")
    labelNombre.setForeground(Color.BLUE)
    new TextBox(form).bindValueToProperty("nombre")

  }

  def createResultsGrid(mainPanel: Panel) {
    var table = new Table[Cliente](mainPanel, classOf[Cliente])
    table.setHeigth(200)
    table.setWidth(400)
    table.bindItemsToProperty("resultados")
    table.bindValueToProperty("clienteSeleccionado")
    this.describeResultsGrid(table)
  }

  def describeResultsGrid(table: Table[Cliente]) {
    new Column[Cliente](table)
      .setTitle("DNI")
      .setFixedSize(100)
      .bindContentsToProperty("dni")

    new Column[Cliente](table)
      .setTitle("Nombre")
      .setFixedSize(150)
      .bindContentsToProperty("nombre")

    new Column[Cliente](table)
      .setTitle("Sexo")
      .setFixedSize(50)
      .bindContentsToProperty("sexo")

    new Column[Cliente](table)
      .setTitle("Edad")
      .setFixedSize(50)
      .bindContentsToProperty("edad")
  }

  def createGridActions(mainPanel: Panel) {
    var actionsPanel = new Panel(mainPanel)
    var editarButton = new Button(actionsPanel)
      .setCaption("Editar")
      .onClick(new MessageSend(this, "modificarCliente"))

    var elementSelected = new NotNullObservable("clienteSeleccionado")
    editarButton.bindEnabled(elementSelected)
  }

  override def addActions(actionsPanel: Panel) {
    new Button(actionsPanel)
      .setCaption("Buscar")
      .onClick(new MessageSend(getModelObject, "search"))
      .setAsDefault
      .disableOnError

    new Button(actionsPanel)
      .setCaption("Limpiar")
      .onClick(new MessageSend(getModelObject, "clear"))

    new Button(actionsPanel) //
      .setCaption("Nuevo Cliente")
      .onClick(new MessageSend(this, "crearCliente"))

    var confirmarButton = new Button(actionsPanel) //
      .setCaption("Confirmar Eleccion")
      .onClick(new MessageSend(this, "aceptar"))

    var elementSelected = new NotNullObservable("clienteSeleccionado")
    confirmarButton.bindEnabled(elementSelected)
  }

  def aceptar() {
    getModelObject.setCliente
    this.accept
  }

  def crearCliente() {
    this.openDialog(new CrearClienteW(this, home.HomeClientes.createExample))
  }

  def modificarCliente() {
    this.openDialog(new EditarClienteW(this, getModelObject.clienteSeleccionado))
  }

  def openDialog(dialog: Dialog[_]) {
    dialog.onAccept(new MessageSend(getModelObject, "search"))
    dialog.open
  }

}