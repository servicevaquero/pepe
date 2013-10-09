package ui.probando
import home._
import applicationModel.GestorDeCompra
import applicationModel.ElegirClienteYEntrada
import org.uqbar.arena.actions.MessageSend
import org.uqbar.arena.layout.ColumnLayout
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.arena.actions.MessageSend
import org.uqbar.arena.layout.ColumnLayout
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import ui.GestorDeCompraWindow
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.TextBox
import java.awt.Color
import domain.ExcepcionButacaOcupada
import domain.ExcepcionCodigoInvalido
import org.uqbar.commons.model.UserException

class ElegirClienteYEntradaW(parent: WindowOwner, unGestorDeCompra: GestorDeCompra) extends Dialog[ElegirClienteYEntrada](parent, new ElegirClienteYEntrada(unGestorDeCompra)) {

  override def addActions(actionsPanel: Panel) = {
    new Button(actionsPanel)
      .setCaption("Elegir Entrada")
      .onClick(new MessageSend(this, "elegirEntrada"))

    new Button(actionsPanel)
      .setCaption("Elegir Cliente")
      .onClick(new MessageSend(this, "elegirCliente"))

    new Button(actionsPanel)
      .setCaption("Confirmar Eleccion")
      .onClick(new MessageSend(this, "confirmarEleccion"))
  }

  def elegirEntrada() {
    getModelObject.setCodigoDeReserva
    this.openDialog(new AgregarEntradaW(this, unGestorDeCompra)) // HAY QUE FIJARSE EL POR QUE ES NECESARIO EL 2DO PARAMETRO!!
  }

  def elegirCliente() {
    //this.openDialog(new AnularEntradaWindow(this, new Object)) // HAY QUE FIJARSE EL POR QUE ES NECESARIO EL 2DO PARAMETRO!!
    this.openDialog(new ElegirClienteW(this, unGestorDeCompra))
  }

  def confirmarEleccion() {
    getModelObject.setCodigoDeReserva
    getModelObject.validarEleccion
    try{
    unGestorDeCompra.agregarEntradas
    } catch{
      case butacaOcupada: (ExcepcionButacaOcupada) => throw new UserException(butacaOcupada.getMessage())
      case codigoInvalido: (ExcepcionCodigoInvalido) => throw new UserException(codigoInvalido.getMessage())
    } 
    this.accept
  }

  def openDialog(dialog: Dialog[_]) {
    dialog.open
  }

  override def createFormPanel(mainPanel: Panel) = {
    var form = new Panel(mainPanel)
    form.setLayout(new ColumnLayout(2))

    var labelNumero = new Label(form)
    labelNumero.setText("Codigo de Reserva")
    labelNumero.setForeground(Color.BLUE)
    new TextBox(form).bindValueToProperty("codigoDeReserva")

  }

}