package ui

import domain._
import applicationModel.ABMClientes

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

class ABMClientesWindow(parent: WindowOwner, unCliente: Cliente) extends Dialog[ABMClientes](parent, new ABMClientes()) {

  override def createMainTemplate(mainPanel: Panel) = {
    this.setTitle("Clientes")
    this.setTaskDescription("Lista de clientes")

    super.createMainTemplate(mainPanel)

    this.createResultsGrid(mainPanel)
    this.createGridActions(mainPanel)
  }

  override def createFormPanel(mainPanel: Panel) = {
    var form = new Panel(mainPanel)
    form.setLayout(new ColumnLayout(2))
  }

  def createResultsGrid(mainPanel: Panel) {
    var table = new Table[Cliente](mainPanel, classOf[Cliente])
    table.setHeigth(200)
    table.setWidth(550)
    table.bindItemsToProperty("clientes")
    table.bindValueToProperty("clienteSeleccionado")
   // this.describeResultsGrid(table)
  }

  def describeResultsGrid(table: Table[Cliente]) {
    new Column[Cliente](table)
      .setTitle("Nombre y Apellido")
      .setFixedSize(100)
      .bindContentsToProperty("nombre")

    new Column[Cliente](table)
      .setTitle("DNI")
      .setFixedSize(100)
      .bindContentsToProperty("dni")

    new Column[Cliente](table)
      .setTitle("Edad")
      .setFixedSize(100)
      .bindContentsToProperty("edad")

    new Column[Cliente](table)
      .setTitle("Sexo")
      .setFixedSize(100)
      .bindContentsToProperty("sexo")
  }

  def createGridActions(mainPanel: Panel) {
    var actionsPanel = new Panel(mainPanel)
    actionsPanel.setLayout(new HorizontalLayout)

    new Button(actionsPanel)
      .setCaption("Agregar Cliente")
      .onClick(new MessageSend(this, "agregarCliente"))

    var aceptarButton = new Button(actionsPanel)
      .setCaption("Aceptar")
      .onClick(new MessageSend(this, "aceptarClienteElegido"))

    var elementSelected = new NotNullObservable("clienteSeleccionado")
    aceptarButton.bindEnabled(elementSelected)
  }

  def agregarCliente() {
    //  this.openDialog(new AgregarClienteWindow(this, getModelObject))
  }

  def aceptarClienteElegido() {
    //  this.openDialog(new AgregarClienteWindow(this, getModelObject))
    this.accept()
  }

  def openDialog(dialog: Dialog[_]) {
    dialog.open
  }
}
