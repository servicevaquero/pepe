package ui
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
import domain.Presentacion
import domain.Entrada
import home.HomePresentaciones

class ComprarEntradasDePresentacionWindow(owner: WindowOwner, model: Presentacion) extends Dialog[Presentacion](owner, model) {

  override def createMainTemplate(mainPanel: Panel) = {
    this.setTitle("Comprar Entradas")
    this.setTaskDescription("Seleccione un Entrada")

    super.createMainTemplate(mainPanel)
  }
  
    override def createFormPanel(mainPanel: Panel) = {
    var form = new Panel(mainPanel)
    form.setLayout(new ColumnLayout(2))

    new Label(form).setText("Entradas")
    var selectorFestival = new Selector[Entrada](form)
    selectorFestival.allowNull(false)
    //selectorFestival.bindValueToProperty("festivalSeleccionado")
    var propiedadFestival = selectorFestival.bindItems(new ObservableProperty(model, "listaDeEntradasJava")) // Bindea a TODA las instancias del Home
    propiedadFestival.setAdapter(new PropertyAdapter(classOf[Entrada], "precioBase"))

  }

}