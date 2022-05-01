package committee.nova.afw.client.proxy

import committee.nova.afw.GameNeedsRestartException
import net.minecraft.client.Minecraft
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent

import java.io.{File, FileOutputStream, IOException}
import java.util.Locale

class ClientProxy {
  def preInit(event: FMLPreInitializationEvent): Unit = fetchJar()

  def fetchJar(): Unit = {
    try {
      val resourcePacks = Minecraft.getMinecraft.getResourcePackRepository.getDirResourcepacks.getCanonicalFile
      val witchery = new File(resourcePacks + "/")
      if (!witchery.exists()) witchery.mkdirs()
      generateFile("data/afw/witchery.jar", "witchery.jar", witchery.getAbsolutePath)
    } catch {
      case e: IOException => e.printStackTrace()
    }

  }

  def generateFile(input: String, name: String, path: String): Unit = {
    try {
      val file = new File(path + "/" + name)
      if (!file.exists) {
        val inputStream = this.getClass.getClassLoader.getResourceAsStream(input)
        val outputStream = new FileOutputStream(file)
        if (inputStream != null) {
          var i = 0
          while ( {
            i = inputStream.read();
            i != -1
          }) outputStream.write(i)
          inputStream.close()
          outputStream.close()
        }
        val locale = Locale.getDefault
        throw new GameNeedsRestartException
      }
    } catch {
      case ignore: IOException =>
    }
  }
}
