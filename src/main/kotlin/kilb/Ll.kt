package kilb

import kernel.process.KProcess

class Ll(kp: KProcess) {
	val h = kp.addressSpace.heap
	fun create(): Long {
		val initP = h.alloc(2)
		return initP
	}

	fun add(ll: Long, element: Long) {
//		val addr = h.get()
	}

	fun getAt(index: Long) {

	}

	fun removeAt(index: Long) {}

	fun clean() {}

	fun findL() {}

	fun findR() {}

}