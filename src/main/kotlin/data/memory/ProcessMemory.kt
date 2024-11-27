package data.memory

import data.stack.FixedStackStateless
import os_package.KProcess

class ProcessMemory(kp: KProcess) {
	val heap = Heap()
	val stack = FixedStackStateless(kp)
}