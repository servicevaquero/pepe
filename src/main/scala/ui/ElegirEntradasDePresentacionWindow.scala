package ui
import org.uqbar.arena.windows.Dialog

import controller.EntradaCategoriaTransformer
import controller.EntradaNumeroDeFilaTransformer
import controller.EntradaNumeroDeButacaTransformer

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
import domain.Presentacion
import domain.Entrada
import home.HomePresentaciones
import applicationModel.ElegirEntradasDePresentacion
import applicationModel.GestorDeCompra

class ElegirEntradasDePresentacionWindow(owner: WindowOwner, unaPresentacion: Presentacion, unGestorDeCompra : GestorDeCompra) extends Dialog[ElegirEntradasDePresentacion](owner, new ElegirEntradasDePresentacion(unaPresentacion)) {

  var gestorDeCompra : GestorDeCompra = unGestorDeCompra
  
  override def createMainTemplate(mainPanel: Panel) = {
    ElegirEntradasDePresentacionWindow.this.setTitle("Comprar Entradas")
    ElegirEntradasDePresentacionWindow.this.setTaskDescription(unaPresentacion.toString())

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
    table.bindItemsToProperty("entradasDisponibles")
    table.bindValueToProperty("entradaEscogida")
    this.describeResultsGrid(table)
  }

  def describeResultsGrid(table: Table[Entrada]) {
    new Column[Entrada](table)
      .setTitle("Sector")
      .setFixedSize(100)
      .bindContentsToTransformer(new EntradaCategoriaTransformer)

    new Column[Entrada](table)
      .setTitle("Fila")
      .setFixedSize(100)
      .bindContentsToTransformer(new EntradaNumeroDeFilaTransformer)

    new Column[Entrada](table)
      .setTitle("Numero de Butaca")
      .setFixedSize(100)
      .bindContentsToTransformer(new EntradaNumeroDeButacaTransformer)
  }

  def createGridActions(mainPanel: Panel) {
    var actionsPanel = new Panel(mainPanel)
    actionsPanel.setLayout(new HorizontalLayout)

    new Button(actionsPanel)
      .setCaption("Elegir Cliente")
      .onClick(new MessageSend(this, "elegirCliente"))

    var confirmarButton = new Button(actionsPanel)
      .setCaption("Confirmar Elección")
      .onClick(new MessageSend(this, "ingresarPago"))

    var elementSelected = new NotNullObservable("hayUnaEntradaElegida")
    confirmarButton.bindEnabled(elementSelected)
  }

  def elegirCliente() {
	    this.openDialog(new SeleccionarClienteWindow(this, gestorDeCompra))
  }

  def openDialog(dialog: Dialog[_]) {
    dialog.open
  }

}