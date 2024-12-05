package kilb

import data.registers.RegisterType
import data.registers.read
import kernel.process.KProcess
import os

class Arrays(val kp: KProcess) {
	val h = kp.addressSpace.heap
	fun size() {
		val meta = kp.vm.registers.read(RegisterType.F1)
		val count = kp.vm.heap!!.get(meta + 1)
		kp.vm.stackOperations.internalStack.push(count)
	}

	fun create() {
		val size = RegisterType.F1.read(kp.vm)
		val startAddr = h.alloc(size.toInt() + 1)
		h.set(startAddr, size)
		sreturn(kp.vm, startAddr)
	}

	fun get() {
		val stAddr = RegisterType.F1.read(kp.vm)
		val size = h.get(stAddr)
		val minAddr = stAddr + 1
		println("-----------------------------------------------------------------------------")
		println("OS M = ${os.mainMemory.toList().subList(0, 30).joinToString()}")

		val index = RegisterType.F2.read(kp.vm)
		val value = RegisterType.F3.read(kp.vm)

		if (index >= size) {
			println("Index = $index, size - 2 = ${size - 2}")
			println("NOO!!!")
			return
		}
		h.set(minAddr + index, value)

		println("OS M = ${os.mainMemory.toList().subList(0, 30).joinToString()}")
	}

	fun add() {
		val stAddr = RegisterType.F1.read(kp.vm)
		// F1 = addr
		// F2 = index
		// F3 = value
		val size = h.get(stAddr)
		val minAddr = stAddr + 1

		val index = RegisterType.F2.read(kp.vm)
		val value = RegisterType.F3.read(kp.vm)

		if (index >= size) {
			println("Index = $index, size - 2 = ${size - 2}")
			println("ERRRORRR!!! FOR ADD RAY!")
			return
		}
		h.set(minAddr + index, value)
	}


	//	fun createLinked(kp.vm: kp.vm) {
//
//	}


}