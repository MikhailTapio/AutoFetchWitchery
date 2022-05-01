package committee.nova.afw

import committee.nova.afw.AutoFetchWitchery.proxy
import committee.nova.afw.client.proxy.ClientProxy
import net.minecraftforge.fml.common.Mod.EventHandler
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import net.minecraftforge.fml.common.{Mod, SidedProxy}

object AutoFetchWitchery {
  final val VERSION = "1.0"
  final val MODID = "afw"
  @SidedProxy(clientSide = "committee.nova.afw.client.proxy.ClientProxy")
  val proxy: ClientProxy = new ClientProxy
  @Mod.Instance(AutoFetchWitchery.MODID)
  var instance: AutoFetchWitchery = _
}

@Mod(
  modid = AutoFetchWitchery.MODID,
  version = AutoFetchWitchery.VERSION,
  acceptedMinecraftVersions = "[1.12,)",
  dependencies = "required-before:witchery",
  clientSideOnly = true)
class AutoFetchWitchery {
  AutoFetchWitchery.instance = this

  @EventHandler
  def preInit(event: FMLPreInitializationEvent): Unit = proxy.preInit(event)
}