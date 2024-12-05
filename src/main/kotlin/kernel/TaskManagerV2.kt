package kernel

import engine.execution.Execute
import internals.Pc
import kernel.process.KProcess
import os

class TaskManagerV2 {
	val keepPcs = mutableMapOf<KProcess, Pair<Pc, Execute>>()
	val deadProcess = mutableListOf<KProcess>()

	fun add(process: KProcess) = run { keepPcs[process] = Pair(process.vm.pcInternal, Execute(process)) }

	suspend fun eventLoop() {
		if (keepPcs.isEmpty()) return
		outer@ while (true) {
			inner@ for (process in keepPcs) {
				val kp = process.key
				if (deadProcess.toTypedArray().contentEquals(keepPcs.keys.toTypedArray())) break@outer
				if (process.key.instructionMemory[process.value.first.toInt()].name == "HALT" && process.key !in deadProcess) {
					deadProcess.add(process.key)
				} else if (process.key in deadProcess) {
					continue@inner
				} else {
					os.snapShotManager.populateSnapShotRegister(kp)
					process.value.second.singleEvent(process.key.instructionMemory[process.value.first.toInt()])
					os.snapShotManager.snapShotRegisters(kp)
				}
			}
		}
	}


}