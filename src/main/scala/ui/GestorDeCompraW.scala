package ui

import domain._
import controller._
import org.uqbar.arena.layout.HorizontalLayout
import org.uqbar.arena.bindings.NotNullObservable
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.widgets.TextBox
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.actions.MessageSend
import org.uqbar.arena.widgets.tables.Column
import org.uqbar.arena.widgets.tables.Table
import applicationModel.GestorDeCompra

class GestorDeCompraW(parent: WindowOwner) extends Dialog[GestorDeCompra](parent, new GestorDeCompra) {

  override def createMainTemplate(mainPanel: Panel) = {
    this.setTitle("Entradas Escogidas")
    this.setTaskDescription("")

    super.createMainTemplate(mainPanel)

    this.createResultsGrid(mainPanel)
    this.createGridActions(mainPanel)

  }

  override def createFormPanel(mainPanel: Panel) = {
  }

  def createResultsGrid(mainPanel: Panel) {
    var table = new Table[Entrada](mainPanel, classOf[Entrada])
    table.setHeigth(200)
    table.setWidth(550)
    table.bindItemsToProperty("entradasElegidas")
    this.describeResultsGrid(table)
  }

  def describeResultsGrid(table: Table[Entrada]) {
    new Column[Entrada](table)
      .setTitle("Fecha")
      .setFixedSize(100)
      .bindContentsToTransformer(new EntradaFechaPresentacionTransformer)

    new Column[Entrada](table)
      .setTitle("Nombre del Cliente")
      .setFixedSize(100)
      .bindContentsToTransformer(new EntradaNombreClienteTransformer)

    new Column[Entrada](table)
      .setTitle("DNI del Cliente")
      .setFixedSize(100)
      .bindContentsToTransformer(new EntradaDNIClienteTransformer)

    new Column[Entrada](table)
      .setTitle("Presentacion")
      .setFixedSize(100)
      .bindContentsToTransformer(new EntradaPresentacionTransformer)

    new Column[Entrada](table)
      .setTitle("Festival")
      .setFixedSize(100)
      .bindContentsToTransformer(new EntradaFestivalTransformer)

    def describeResultsGrid(table: Table[Entrada]) {
      new Column[Entrada](table)
        .setTitle("Sector")
        .setFixedSize(100)
        .bindContentsToTransformer(new EntradaSectorTransformer)

      new Column[Entrada](table)
        .setTitle("Fila")
        .setFixedSize(100)
        .bindContentsToTransformer(new EntradaNumeroDeFilaTransformer)

      new Column[Entrada](table)
        .setTitle("Numero de Butaca")
        .setFixedSize(100)
        .bindContentsToTransformer(new EntradaNumeroDeButacaTransformer)
    }
  }

  def createGridActions(mainPanel: Panel) {
    var actionsPanel = new Panel(mainPanel)
    actionsPanel.setLayout(new HorizontalLayout)

    new Button(actionsPanel)
      .setCaption("Agregar Entrada/s")
      .onClick(new MessageSend(this, "agregarEntrada"))

    var confirmarButton = new Button(actionsPanel)
      .setCaption("Realizar Pago")
      .onClick(new MessageSend(this, "ingresarPago"))

    var elementSelected = new NotNullObservable("entradasElegidas")
    confirmarButton.bindEnabled(elementSelected)

    new Button(actionsPanel)
      .setCaption("Cancelar")
      .onClick(new MessageSend(this, "cancel"))
  }

  def ingresarPago() {
    var pagado: Boolean = new PagarW(this, getModelObject.unChanguito).openWithReturn
    if(pagado) getModelObject.limpiarEleccion
  }

  def agregarEntrada() {
    this.openDialog(new ElegirClienteYEntradaW(this, getModelObject.dameUnElector))
    getModelObject.reload()
  }

  def openDialog(dialog: Dialog[_]) {
    dialog.open
  }

  override def cancel() {
    getModelObject.limpiarEleccion
    super.cancel
  }
  
 /* override def close(){ //NO hace lo que quiero de cancelar la eleccion y liberar Butacas :(
    cancel//getModelObject.cancelarEleccion
    super.close
  }
  */

}