package ui

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

class SeleccionarClienteWindow(parent: WindowOwner, unGestorDeCompra: GestorDeCompra) extends Dialog[SeleccionarCliente](parent, new SeleccionarCliente()) {
	
  var gestorDeCompra: GestorDeCompra = unGestorDeCompra
  getModelObject.search
  
  override def createMainTemplate(mainPanel: Panel) = {
    this.setTitle("Clientes")
    this.setTaskDescription("Lista de clientes")

    super.createMainTemplate(mainPanel)

    //this.createResultsGrid(mainPanel)
    //this.createGridActions(mainPanel)
  }
  
  override def createFormPanel(mainPanel: Panel){
    var table = new Table[Cliente](mainPanel, classOf[Cliente])
    table.setHeigth(200)
    table.setWidth(550)
    table.bindItemsToProperty("resultados")
    table.bindValueToProperty("clienteSeleccionado")
    //this.describeResultsGrid(table)
  }
}