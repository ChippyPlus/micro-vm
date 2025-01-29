package internals.debug

import kernel.process.KProcess
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File


@Serializable
data class DebugInstruction(val fileName: String, val pc: Long, val instruction: String)


class DebugInstructionBuffer {
	private val json = Json { prettyPrint = true }
	val where = "debug/instruction snapshots"

	init {
		File(where).deleteRecursively()
//		println("Deleted $where")

		File(where).mkdir()
	}


	fun collect(kp: KProcess): String {
		var instruct = kp.currentInstruction.name
		kp.currentInstruction.values.forEach { instruct += " $it" }
		return instruct
	}

	fun write(kp: KProcess, instruct: String) {
		val collected = DebugInstruction(kp.file.name, kp.vm.pc, instruct)
		val string = json.encodeToString(collected)
		File("$where/${collected.fileName}").mkdirs()

		File("$where/${collected.fileName}/${collected.pc}.json").createNewFile()
		File("$where/${collected.fileName}/${collected.pc}.json").writeText(string)
	}

	fun act(kp: KProcess) = write(kp, collect(kp))

}