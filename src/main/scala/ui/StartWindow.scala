package ui

import applicationModel.Start
import org.uqbar.arena.actions.MessageSend
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.layout.ColumnLayout
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.WindowOwner

class StartWindow(parent: WindowOwner) extends SimpleWindow[Start](parent, new Start) {

  override def createFormPanel(mainPanel: Panel) = {
    var searchFormPanel = new Panel(mainPanel)
    searchFormPanel.setLayout(new ColumnLayout(1))
  }

  override def addActions(actionsPanel: Panel) = {
    new Button(actionsPanel)
      .setCaption("Comprar Entrada")
      .onClick(new MessageSend(this, "comprarEntrada"))

    new Button(actionsPanel)
      .setCaption("Anular Entrada")
      .onClick(new MessageSend(this, "anularEntrada"))

    new Button(actionsPanel)
      .setCaption("Ver Bandas por Festival")
      .onClick(new MessageSend(this, "verBandasPorFestival"))
  }

  def comprarEntrada() {
    this.openDialog(new GestorDeCompraW(this)) // HAY QUE FIJARSE EL POR QUE ES NECESARIO EL 2DO PARAMETRO!!
  }

  def anularEntrada() {
    this.openDialog(new AnularEntradaWindow(this))
  }

  def verBandasPorFestival() {
    this.openDialog(new VerBandasPorFestivalWindow(this))
  }

  def openDialog(dialog: Dialog[_]) {
    dialog.open
  }

}