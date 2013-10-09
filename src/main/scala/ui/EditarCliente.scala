package ar.edu.celulares.ui

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
import domain.Cliente
import applicationModel.ABMClientes

class EditarCliente(owner: WindowOwner, unCliente: Cliente) extends Dialog[ABMClientes](owner, new ABMClientes(unCliente)) {

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
		
	}

	override def addActions(actions: Panel) = {
		new Button(actions)
			.setCaption("Aceptar")
			.onClick(new MessageSend(this, "accept"))
			.setAsDefault.disableOnError

		new Button(actions) //
			.setCaption("Cancelar")
			.onClick(new MessageSend(this, "cancel"))
	}

}
