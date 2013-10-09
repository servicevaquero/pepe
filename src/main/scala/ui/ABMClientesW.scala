package ui

import domain._
import applicationModel.ABMClientes

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

class ABMClientesW(parent: WindowOwner, unCliente: Cliente) extends Dialog[ABMClientes](parent, new ABMClientes(unCliente)){

  override def createMainTemplate(mainPanel: Panel) = {
    ABMClientesW.this.setTitle("Clientes")
    ABMClientesW.this.setTaskDescription("Lista de clientes")

    super.createMainTemplate(mainPanel)
  }

  override def createFormPanel(mainPanel: Panel) = {
    var form = new Panel(mainPanel)
    form.setLayout(new ColumnLayout(2))

    (new Label(form)).setText("DNI")
    (new TextBox(form)).bindValueToProperty("dni")
    (new Label(form)).setText("Nombre del cliente")
    (new TextBox(form)).bindValueToProperty("nombre")
    (new Label(form)).setText("Edad")
    (new TextBox(form)).bindValueToProperty("edad")
    (new Label(form)).setText("Sexo")
    (new TextBox(form)).bindValueToProperty("sexo")

  }

  override def addActions(actionsPanel: Panel) = {
    (new Button(actionsPanel))
    .setCaption("Aceptar")
    .onClick(new MessageSend(ABMClientesW.this, "modificar"))

    (new Button(actionsPanel))
    .setCaption("Cancelar")
    .onClick(new MessageSend(ABMClientesW.this, "cancel"))
  }

  def modificar(){
    getModelObject.modificar()
    ABMClientesW.this.accept()
  }
  
  def agregarCliente() {
    //  this.openDialog(new AgregarClienteWindow(this, getModelObject))
  }

  def aceptarClienteElegido() {
    //  this.openDialog(new AgregarClienteWindow(this, getModelObject))
    ABMClientesW.this.accept()
  }

  def openDialog(dialog: Dialog[_]) {
    dialog.open
  }
}
