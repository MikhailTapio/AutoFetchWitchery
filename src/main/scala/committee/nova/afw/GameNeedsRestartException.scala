package committee.nova.afw

import committee.nova.afw.GameNeedsRestartException.locale_EN
import net.minecraft.client.gui.{FontRenderer, GuiErrorScreen}
import net.minecraft.client.resources.I18n
import net.minecraftforge.fml.client.CustomModLoadingErrorDisplayException

import scala.collection.JavaConversions._

object GameNeedsRestartException {
  final val locale_EN = "Completed loading witchery 1.7.10 jar. Please restart the game :D"
}

class GameNeedsRestartException() extends CustomModLoadingErrorDisplayException(locale_EN, null) {
  override def initGui(errorScreen: GuiErrorScreen, fontRenderer: FontRenderer): Unit = {}

  override def drawScreen(errorScreen: GuiErrorScreen, fontRenderer: FontRenderer, mouseRelX: Int, mouseRelY: Int, tickTime: Float): Unit = {
    var yOffset = 50
    val text = fontRenderer.listFormattedStringToWidth(I18n.format("tip.afw.restart"), errorScreen.width - 80)
    for (t <- text) {
      errorScreen.drawCenteredString(fontRenderer, t, errorScreen.width / 2, yOffset, 0xFFFFFF)
      yOffset += 10
    }
  }
}