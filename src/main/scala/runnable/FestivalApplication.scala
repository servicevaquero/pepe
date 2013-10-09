package runnable

import ui.probando.StartWindow
import org.uqbar.arena.Application

object FestivalApplication extends Application with App {
	
	override def createMainWindow() = new StartWindow(this)
	
	start()
}