package ui
import home._
import domain.Festival

import applicationModel.Start
import applicationModel.ComprarEntrada
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
    this.openDialog(new GestorDeCompraWindow(this)) // HAY QUE FIJARSE EL POR QUE ES NECESARIO EL 2DO PARAMETRO!!
  }

  def AnularEntrada() {
    //this.openDialog(new AnularEntradaWindow(this, new Object)) // HAY QUE FIJARSE EL POR QUE ES NECESARIO EL 2DO PARAMETRO!!
    this.openDialog(new ABMClientesWindow(this, domain.Cliente.apply(0,0,'\0')))
  }

  def openDialog(dialog: Dialog[_]) {
    dialog.open
  }

  override def createFormPanel(mainPanel: Panel) = {
    var searchFormPanel = new Panel(mainPanel)
    searchFormPanel.setLayout(new ColumnLayout(1))
  }

}