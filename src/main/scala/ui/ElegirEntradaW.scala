package ui
import org.uqbar.arena.windows.Dialog

import controller.EntradaSectorTransformer
import controller.EntradaNumeroDeFilaTransformer
import controller.EntradaNumeroDeButacaTransformer
import applicationModel.ElegirClienteYEntrada

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

class ElegirEntradaW(owner: WindowOwner, unaPresentacion: Presentacion, unElector : ElegirClienteYEntrada) extends Dialog[ElegirEntradasDePresentacion](owner, new ElegirEntradasDePresentacion(unaPresentacion, unElector)) {
  
  override def createMainTemplate(mainPanel: Panel) = {
    this.setTitle("Comprar Entradas")
    this.setTaskDescription(unaPresentacion.nombre)

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
    table.setWidth(250)
    table.bindItemsToProperty("entradasDisponibles")
    table.bindValueToProperty("entradaEscogida")
    this.describeResultsGrid(table)
  }

  def describeResultsGrid(table: Table[Entrada]) {
    new Column[Entrada](table)
      .setTitle("Sector")
      .setFixedSize(50)
      .bindContentsToTransformer(new EntradaSectorTransformer)

    new Column[Entrada](table)
      .setTitle("Fila")
      .setFixedSize(50)
      .bindContentsToTransformer(new EntradaNumeroDeFilaTransformer)

    new Column[Entrada](table)
      .setTitle("Numero")
      .setFixedSize(100)
      .bindContentsToTransformer(new EntradaNumeroDeButacaTransformer)
  }

  def createGridActions(mainPanel: Panel) {
    var actionsPanel = new Panel(mainPanel)
    actionsPanel.setLayout(new HorizontalLayout)

    var confirmarButton = new Button(actionsPanel)
      .setCaption("Confirmar Elección")
      .onClick(new MessageSend(this, "aceptar"))

    var elementSelected = new NotNullObservable("entradaEscogida")
    confirmarButton.bindEnabled(elementSelected)
  }
  
  def aceptar(){
    getModelObject.setEntradaEscogida
    this.accept
  }

}