package ui

import applicationModel.Pagar
import java.awt.Color
import org.uqbar.arena.bindings.NotNullObservable
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.widgets.TextBox
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.arena.layout.ColumnLayout
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.actions.MessageSend
import org.uqbar.arena.widgets.tables.Column
import org.uqbar.arena.widgets.tables.Table
import domain.Chango

class PagarW(parent: WindowOwner, unChango: Chango) extends Dialog[Pagar](parent, new Pagar(unChango)) {

  var pagoRealizado: Boolean = false

  override def createMainTemplate(mainPanel: Panel) = {
    this.setTitle("Ventana de Pago")
    this.setTaskDescription("")
    super.createMainTemplate(mainPanel)
  }

  def openWithReturn(): Boolean = {
    this.open
    pagoRealizado
  }

  override def createFormPanel(mainPanel: Panel) = {
    var form = new Panel(mainPanel)
    form.setLayout(new ColumnLayout(2))

    var labelNombre = new Label(form)
    labelNombre.setText("Total a Pagar")
    labelNombre.setForeground(Color.BLUE)
    new TextBox(form).bindValueToProperty("precio")
  }

  override def addActions(actionsPanel: Panel) = {
    new Button(actionsPanel)
      .setCaption("Pagar con Tarjeta")
      .onClick(new MessageSend(this, "realizarPagoConTarjeta"))

    new Button(actionsPanel)
      .setCaption("Pagar con Efectivo")
      .onClick(new MessageSend(this, "accept"))
  }

  def realizarPagoConTarjeta() {
    getModelObject.realizarPagoConTarjeta
    this.accept
  }
  
  override def accept(){
    pagoRealizado = true
    super.accept
  }

}