package ui

import org.uqbar.arena.windows.WindowOwner
import domain.Cliente


class CrearClienteW(owner: WindowOwner, unCliente: Cliente) extends EditarClienteW(owner, unCliente) {

  override def executeTask() = {
    super.executeTask()
  }

  override def aceptar() {
    getModelObject.crear()
    this.accept()
  }

}