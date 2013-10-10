package ui

import java.awt.Color



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

class AnularEntradaWindow(owner: WindowOwner, model: Object) extends Dialog[Object](owner, model) {

  override def createFormPanel(mainPanel: Panel) = {
    var searchFormPanel = new Panel(mainPanel)
    searchFormPanel.setLayout(new ColumnLayout(2))

    var labelNumero = new Label(searchFormPanel)
    labelNumero.setText("Anulador XD")
    labelNumero.setForeground(Color.GREEN)
  }

}