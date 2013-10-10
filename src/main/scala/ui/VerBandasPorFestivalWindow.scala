package ui

import applicationModel.VerBandasPorFestival
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
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.actions.MessageSend
import org.uqbar.arena.widgets.tables.Column
import org.uqbar.arena.widgets.tables.Table
import domain.Festival
import domain.Banda
import home.HomeFestivales
import java.awt.Color
import controller.BandaCategoriaTransformer

class VerBandasPorFestivalWindow(parent: WindowOwner) extends Dialog[VerBandasPorFestival](parent, new VerBandasPorFestival) {

  getModelObject.search()

  override def createMainTemplate(mainPanel: Panel) = {
    this.setTitle("Visualizar Bandas por Festival")
    this.setTaskDescription("Seleccione un Festival")

    super.createMainTemplate(mainPanel)
    this.createResultsGrid(mainPanel)
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

    var labelNombre = new Label(form)
    labelNombre.setText("Nombre de la Banda")
    labelNombre.setForeground(Color.BLUE)
    new TextBox(form).bindValueToProperty("nombre")
  }

  def createResultsGrid(mainPanel: Panel) {
    var table = new Table[Banda](mainPanel, classOf[Banda])
    table.setHeigth(200)
    table.setWidth(450)
    table.bindItemsToProperty("resultados")
    this.describeResultsGrid(table)
  }

  override def addActions(actionsPanel: Panel) {
    new Button(actionsPanel)
      .setCaption("Buscar")
      .onClick(new MessageSend(getModelObject, "search"))
      .setAsDefault
      .disableOnError
  }

  def describeResultsGrid(table: Table[Banda]) {
    new Column[Banda](table)
      .setTitle("Nombre de la Banda")
      .setFixedSize(150)
      .bindContentsToProperty("nombre")

    new Column[Banda](table)
      .setTitle("Categoria")
      .setFixedSize(50)
      .bindContentsToTransformer(new BandaCategoriaTransformer)
  }

}