package internals.debug

import data.registers.RegisterData
import data.registers.RegisterType
import environment.reflection.reflection
import internals.Vm
import kernel.process.KProcess
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

@Serializable
data class DebugRegisters(val fileName: String, val pc: Long, val registers: Map<RegisterType, RegisterData>)


class DebugFullSnapShots {
	val where = "debug/register snapshots"

	init {
		File(where).deleteRecursively()
//		println("Deleted $where")
		File(where).mkdir()
	}

	private fun collect(vm: Vm): DebugRegisters {
		return DebugRegisters(
			reflection.groupTrackedVmByVm()[vm]!!.file.name, vm.pc, vm.registers.registers
		)
	}


	private val json = Json { prettyPrint = true }

	private fun write(collected: DebugRegisters) {
		val string = json.encodeToString(collected)

		File("$where/${collected.fileName}").mkdirs()

		File("$where/${collected.fileName}/${collected.pc}.json").createNewFile()
		File("$where/${collected.fileName}/${collected.pc}.json").writeText(string)
	}


	fun act(kp: KProcess) = write(collect(kp.vm))

}