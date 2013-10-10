package ui

import java.awt.Color

import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.widgets.Selector
import org.uqbar.arena.widgets.TextBox
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.arena.layout.ColumnLayout
import org.uqbar.arena.layout.HorizontalLayout
import org.uqbar.commons.utils.ApplicationContext
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.actions.MessageSend
import org.uqbar.arena.widgets.tables.Column
import org.uqbar.arena.widgets.tables.Table
import org.uqbar.arena.bindings.NotNullObservable
import collection.JavaConversions._
import applicationModel.AnularEntrada
import domain.Entrada
import controller._

class AnularEntradaWindow(owner: WindowOwner) extends Dialog[AnularEntrada](owner, new AnularEntrada) {

  override def createFormPanel(mainPanel: Panel) = {
    var searchFormPanel = new Panel(mainPanel)
    searchFormPanel.setLayout(new ColumnLayout(2))
  }

  override def createMainTemplate(mainPanel: Panel) = {
    this.setTitle("Anular Entradas")
    super.createMainTemplate(mainPanel)
    this.createResultsGrid(mainPanel)
    this.createGridActions(mainPanel)
  }
  
  def createResultsGrid(mainPanel: Panel) {
    var table = new Table[Entrada](mainPanel, classOf[Entrada])
    table.setHeigth(200)
    table.setWidth(400)
    table.bindItemsToProperty("entradasNoDisponibles")
    table.bindValueToProperty("entradaEscogida")
    this.describeResultsGrid(table)
  }

  def describeResultsGrid(table: Table[Entrada]) {
    
    new Column[Entrada](table)
      .setTitle("Festival")
      .setFixedSize(100)
      .bindContentsToTransformer(new EntradaFestivalTransformer)
      
    new Column[Entrada](table)
      .setTitle("Presentacion")
      .setFixedSize(100)
      .bindContentsToTransformer(new EntradaPresentacionTransformer)
    
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
      .setCaption("Anular")
      .onClick(new MessageSend(this, "anular"))

    var elementSelected = new NotNullObservable("entradaEscogida")
    confirmarButton.bindEnabled(elementSelected)
  }

  def anular(){
    getModelObject.anularEntradaEscogida
    getModelObject.setEntradasNoDisponibles
  }
  
}