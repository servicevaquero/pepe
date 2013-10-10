package ui
import home._
import applicationModel.GestorDeCompra
import applicationModel.Start
import org.uqbar.arena.actions.MessageSend
import org.uqbar.arena.layout.ColumnLayout
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.arena.actions.MessageSend
import org.uqbar.arena.layout.ColumnLayout
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner

class StartWindow(parent: WindowOwner) extends SimpleWindow[Start](parent, new Start) {

  override def addActions(actionsPanel: Panel) = {
    new Button(actionsPanel)
      .setCaption("Comprar Entrada")
      .onClick(new MessageSend(this, "ComprarEntrada"))

    new Button(actionsPanel)
      .setCaption("Anular Entrada")
      .onClick(new MessageSend(this, "AnularEntrada"))
  }

  def ComprarEntrada() {
    this.openDialog(new GestorDeCompraW(this)) // HAY QUE FIJARSE EL POR QUE ES NECESARIO EL 2DO PARAMETRO!!
  }

  def AnularEntrada() {
    //this.openDialog(new AnularEntradaWindow(this, new Object)) // HAY QUE FIJARSE EL POR QUE ES NECESARIO EL 2DO PARAMETRO!!
    //this.openDialog(new ElegirClienteW(this, new GestorDeCompra))
  }

  def openDialog(dialog: Dialog[_]) {
    dialog.open
  }

  override def createFormPanel(mainPanel: Panel) = {
    var searchFormPanel = new Panel(mainPanel)
    searchFormPanel.setLayout(new ColumnLayout(1))
  }

}