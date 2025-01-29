package kernel

import engine.execution.Execute
import internals.Pc
import kernel.process.KProcess
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class TaskManagerV2 {
	private val mutex = Mutex()
	val keepPcs = mutableMapOf<KProcess, Pair<Pc, Execute>>()
	suspend fun add(process: KProcess) {
		mutex.withLock {
			keepPcs[process] = Pair(process.vm.pcInternal, Execute(process))
		}
	}

	suspend fun eventLoop() {
		outer@ while (true) {
			val copyK = keepPcs.toMap()
			if (copyK.isEmpty()) break@outer
			inner@ for (process in copyK) {
				val kp = process.key
				val pc = process.value.first.toInt()
				if (kp.instructionMemory[pc].name == "HALT") {
					keepPcs.remove(kp)
					kp.vm.pc = 0
				} else {
					process.value.second.singleEvent(kp.instructionMemory[pc])
				} //				println("${kp.vm}:${kp.vm.pc}:${kp.instructionMemory[pc]}")

			}
		}
	}
}


