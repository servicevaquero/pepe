package ui

import applicationModel.Start
import java.awt.Color
import org.uqbar.arena.actions.MessageSend
import org.uqbar.arena.bindings.NotNullObservable
import org.uqbar.arena.layout.ColumnLayout
import org.uqbar.arena.layout.HorizontalLayout
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.widgets.TextBox
import org.uqbar.arena.widgets.tables.Column
import org.uqbar.arena.widgets.tables.Table
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import com.uqbar.commons.collections.Transformer
import org.uqbar.arena.layout.VerticalLayout

class StartWindow (parent: WindowOwner) extends SimpleWindow[Start](parent, new Start) {

  //getModelObject.search()
  override def addActions(actionsPanel:Panel) = {
		new Button(actionsPanel) //
			.setCaption("Convertir a kilómetros")
			.onClick(new MessageSend(this.getModelObject(), "convertir"))
	}

	override def createFormPanel(mainPanel:Panel ) {
		this.setTitle("Conversor de millas a kilómetros")
		mainPanel.setLayout(new VerticalLayout())

		new Label(mainPanel).setText("Ingrese la longitud en millas")

		//new TextBox(mainPanel).bindValueToProperty("millas")

		new Label(mainPanel) //
			.setBackground(Color.ORANGE)
			//.bindValueToProperty("kilometros")

		new Label(mainPanel).setText(" kilómetros")
		//new Label(mainPanel).bindValueToProperty("country")
	}

}