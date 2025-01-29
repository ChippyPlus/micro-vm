package internals.debug

import kernel.process.KProcess
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

@Suppress("ArrayInDataClass")
@Serializable
data class DebugMemory(val fileName: String, val pc: Long, val memory: LongArray)


class DebugMemorySnapShots {
	val where = "debug/memory snapshots"

	init {
		File(where).deleteRecursively()
//		println("Deleted $where")
		File(where).mkdir()
	}

	private fun collect(kp: KProcess): DebugMemory {
		return DebugMemory(kp.file.name, kp.vm.pc, kp.addressSpace.heap.m)
	}


	private val json = Json { prettyPrint = true }

	private fun write(collected: DebugMemory) {
		val string = json.encodeToString(collected)

		File("$where/${collected.fileName}").mkdirs()

		File("$where/${collected.fileName}/${collected.pc}.json").createNewFile()
		File("$where/${collected.fileName}/${collected.pc}.json").writeText(string)
	}


	fun act(kp: KProcess) = write(collect(kp))

}