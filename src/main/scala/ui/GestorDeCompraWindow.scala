package ui
import domain._
import home._

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

class GestorDeCompraWindow(parent: WindowOwner) extends Dialog[GestorDeCompra](parent, new GestorDeCompra) {

  override def createMainTemplate(mainPanel: Panel) = {
    this.setTitle("Entradas Escogidas")
    this.setTaskDescription("hola")

    super.createMainTemplate(mainPanel)

    this.createResultsGrid(mainPanel)
    this.createGridActions(mainPanel)
  }

  override def createFormPanel(mainPanel: Panel) = {
    var form = new Panel(mainPanel)
    form.setLayout(new ColumnLayout(2))
  }

  def createResultsGrid(mainPanel: Panel) {
    var table = new Table[Entrada](mainPanel, classOf[Entrada])
    table.setHeigth(200)
    table.setWidth(550)
    table.bindItemsToProperty("entradasElegidas")
    table.bindValueToProperty("entradaSeleccionada")
    this.describeResultsGrid(table)
  }

  def describeResultsGrid(table: Table[Entrada]) {
    new Column[Entrada](table)
      .setTitle("Fecha")
      .setFixedSize(100)
      .bindContentsToProperty("agregarRecargos")
      
      new Column[Entrada](table)
      .setTitle("Cliente")
      .setFixedSize(100)
      .bindContentsToProperty("agregarRecargos")

    new Column[Entrada](table)
      .setTitle("Costo")
      .setFixedSize(100)
      .bindContentsToProperty("agregarRecargos")
      
      new Column[Entrada](table)
      .setTitle("Presentacion")
      .setFixedSize(100)
      .bindContentsToProperty("agregarRecargos")
      
      new Column[Entrada](table)
      .setTitle("Festival")
      .setFixedSize(100)
      .bindContentsToProperty("agregarRecargos")
  }

  def createGridActions(mainPanel: Panel) {
    var actionsPanel = new Panel(mainPanel)
    actionsPanel.setLayout(new HorizontalLayout)
    
    new Button(actionsPanel)
      .setCaption("Agregar Entrada/s")
      .onClick(new MessageSend(this, "agregarEntrada"))

    var confirmarButton = new Button(actionsPanel)
      .setCaption("Confirmar Elección")
      .onClick(new MessageSend(this, "ingresarPago"))

    var elementSelected = new NotNullObservable("listaNULLorNotNULL")
    confirmarButton.bindEnabled(elementSelected)
  }
  
  def ingresarPago() {
    //this.openDialog(new ComprarEntradasDePresentacionWindow(this, getModelObject.presentacionSeleccionada))
  }
  
   def agregarEntrada() {
    this.openDialog(new AgregarEntradaWindow(this, getModelObject))
  }

  def openDialog(dialog: Dialog[_]) {
    dialog.open
  }

}