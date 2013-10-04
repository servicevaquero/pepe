package ui

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
import applicationModel.BuscadorEntradasFecha
import domain._

class VerEntradasFechaWindow(parent: WindowOwner) extends SimpleWindow[BuscadorEntradasFecha](parent, new BuscadorEntradasFecha) {

  getModelObject.search()

  override def createMainTemplate(mainPanel: Panel) = {
    this.setTitle("Buscador de entradas por fecha")
    this.setTaskDescription("Ingrese los parámetros de búsqueda")

    super.createMainTemplate(mainPanel)

    this.createResultsGrid(mainPanel)
  }

  override def createFormPanel(mainPanel: Panel) = {
    var searchFormPanel = new Panel(mainPanel)
    searchFormPanel.setLayout(new ColumnLayout(2))

    var labelNumero = new Label(searchFormPanel)
    labelNumero.setText("Número")
    labelNumero.setForeground(Color.BLUE)

    new TextBox(searchFormPanel).bindValueToProperty("numero")

    var labelNombre = new Label(searchFormPanel)
    labelNombre.setText("Nombre del cliente")
    labelNombre.setForeground(Color.BLUE)

    new TextBox(searchFormPanel).bindValueToProperty("nombre")
  }

  override def addActions(actionsPanel: Panel) {
    new Button(actionsPanel)
      .setCaption("Buscar")
      .onClick(new MessageSend(getModelObject, "search"))
      .setAsDefault
      .disableOnError

    new Button(actionsPanel)
      .setCaption("Limpiar")
      .onClick(new MessageSend(getModelObject, "clear"))
  }

  def createResultsGrid(mainPanel: Panel) {
    var table = new Table[Entrada](mainPanel, classOf[Entrada])
    table.setHeigth(200)
    table.setWidth(450)
    table.bindItemsToProperty("resultados")
    this.describeResultsGrid(table)
  }

  def describeResultsGrid(table: Table[Entrada]) {
    new Column[Entrada](table)
      .setTitle("Cliente")
      .setFixedSize(150)
      .bindContentsToProperty("cliente")

    new Column[Entrada](table)
      .setTitle("Fecha desde")
      .setFixedSize(100)
      .bindContentsToProperty("fechaDesde")

    new Column[Entrada](table)
      .setTitle("Fecha hasta")
      .setFixedSize(150)
      .bindContentsToProperty("fechaHasta")
  }

  def openDialog(dialog: Dialog[_]) {
    dialog.onAccept(new MessageSend(getModelObject, "search"))
    dialog.open
  }

}