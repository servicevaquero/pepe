package ui

import applicationModel.ElegirClienteYEntrada
import org.uqbar.arena.actions.MessageSend
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.layout.ColumnLayout
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.TextBox
import java.awt.Color


class ElegirClienteYEntradaW(parent: WindowOwner, model: ElegirClienteYEntrada) extends Dialog[ElegirClienteYEntrada](parent, model) {

  override def addActions(actionsPanel: Panel) = {
    new Button(actionsPanel)
      .setCaption("Elegir Entrada")
      .onClick(new MessageSend(this, "elegirEntrada"))

    new Button(actionsPanel)
      .setCaption("Elegir Cliente")
      .onClick(new MessageSend(this, "elegirCliente"))

    new Button(actionsPanel)
      .setCaption("Confirmar Eleccion")
      .onClick(new MessageSend(this, "confirmarEleccion"))
  }

  def elegirEntrada() {
    this.openDialog(new ElegirPresentacionW(this, getModelObject))
  }

  def elegirCliente() {
   
    this.openDialog(new ElegirClienteW(this, getModelObject))
  }

  def confirmarEleccion() {
    getModelObject.validarEleccion
    this.accept
  }

  def openDialog(dialog: Dialog[_]) {
    dialog.open
  }

  override def createFormPanel(mainPanel: Panel) = {
    var form = new Panel(mainPanel)
    form.setLayout(new ColumnLayout(2))

    var labelNumero = new Label(form)
    labelNumero.setText("Codigo de Reserva")
    labelNumero.setForeground(Color.BLUE)
    new TextBox(form).bindValueToProperty("codigoTipeado")

  }

}