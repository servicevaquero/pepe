package ui

import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.widgets.TextBox
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.arena.layout.ColumnLayout
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.actions.MessageSend
import domain.Cliente
import applicationModel.ABMClientes

class EditarClienteW(owner: WindowOwner, unCliente: Cliente) extends Dialog[ABMClientes](owner, new ABMClientes(unCliente)) {

  override def createFormPanel(mainPanel: Panel) = {
    var form = new Panel(mainPanel)
    form.setLayout(new ColumnLayout(2))
    new Label(form).setText("DNI")
    new TextBox(form).bindValueToProperty("dni")
    new Label(form).setText("Nombre del cliente")
    new TextBox(form).bindValueToProperty("nombre")
    new Label(form).setText("Edad")
    new TextBox(form).bindValueToProperty("edad")
    new Label(form).setText("Sexo")
    new TextBox(form).bindValueToProperty("sexo")

  }

  override def addActions(actions: Panel) = {
    new Button(actions)
      .setCaption("Aceptar")
      .onClick(new MessageSend(EditarClienteW.this, "aceptar"))
      .setAsDefault.disableOnError

    new Button(actions)
      .setCaption("Cancelar")
      .onClick(new MessageSend(EditarClienteW.this, "cancel"))
  }

  def aceptar() {
    getModelObject.modificar()
    this.accept()
  }

}
