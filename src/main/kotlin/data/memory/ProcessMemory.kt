package data.memory

import data.stack.FixedStackStateless
import kernel.process.KProcess

class ProcessMemory(kp: KProcess) {
	val heap = Heap(kp)
	val stack = FixedStackStateless(kp)
}