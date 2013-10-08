package ui
import domain._
import home._
import applicationModel.ComprarEntrada

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

class ComprarEntradaWindow(parent: WindowOwner) extends Dialog[ComprarEntrada](parent, new ComprarEntrada) {

  getModelObject.search()

  override def createMainTemplate(mainPanel: Panel) = {
    this.setTitle("Comprar Entradas")
    this.setTaskDescription("Seleccione un Festival")

    super.createMainTemplate(mainPanel)

    this.createResultsGrid(mainPanel)
    this.createGridActions(mainPanel)
  }

  override def createFormPanel(mainPanel: Panel) = {
    var form = new Panel(mainPanel)
    form.setLayout(new ColumnLayout(2))

    new Label(form).setText("Festival")
    var selectorFestival = new Selector[Festival](form)
    selectorFestival.allowNull(false)
    selectorFestival.bindValueToProperty("festivalSeleccionado")
    var propiedadFestival = selectorFestival.bindItems(new ObservableProperty(HomeFestivales, "festivales")) // Bindea a TODA las instancias del Home
    propiedadFestival.setAdapter(new PropertyAdapter(classOf[Festival], "nombre"))

  }

  def createResultsGrid(mainPanel: Panel) {
    var table = new Table[Presentacion](mainPanel, classOf[Presentacion])
    table.setHeigth(200)
    table.setWidth(450)
    table.bindItemsToProperty("listaDePresentaciones")
    table.bindValueToProperty("presentacionSeleccionada")
    this.describeResultsGrid(table)
  }

  def describeResultsGrid(table: Table[Presentacion]) {
    new Column[Presentacion](table) //
      .setTitle("Fecha")
      .setFixedSize(150)
      .bindContentsToProperty("fecha")

    new Column[Presentacion](table) //
      .setTitle("Categoria")
      .setFixedSize(100)
      .bindContentsToProperty("categoriaString")
  }

  override def addActions(actionsPanel: Panel) {
    new Button(actionsPanel)
      .setCaption("Buscar")
      .onClick(new MessageSend(getModelObject, "search"))
      .setAsDefault
      .disableOnError
  }

  def createGridActions(mainPanel: Panel) {
    var actionsPanel = new Panel(mainPanel)
    actionsPanel.setLayout(new HorizontalLayout)

    var consultarEntradasDisponibles = new Button(actionsPanel)
      .setCaption("Mostrar Entradas Disponibles")
      .onClick(new MessageSend(this, "consultarEntradasDisponibles"))

    var elementSelected = new NotNullObservable("presentacionSeleccionada")
    consultarEntradasDisponibles.bindEnabled(elementSelected)
  }
  
  def consultarEntradasDisponibles() {
    this.openDialog(new ComprarEntradasDePresentacionWindow(this, getModelObject.presentacionSeleccionada))
  }

  def openDialog(dialog: Dialog[_]) {
    dialog.onAccept(new MessageSend(getModelObject, "search"))
    dialog.open
  }

}