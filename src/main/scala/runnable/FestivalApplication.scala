package runnable

import ui.StartWindow
import org.uqbar.arena.Application

object FestivalApplication extends Application with App {
	
	override def createMainWindow() = new StartWindow(this)
	
	start()
}