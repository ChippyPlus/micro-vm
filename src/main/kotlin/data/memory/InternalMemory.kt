package data.memory

import MEMORY_LIMIT
import internals.Vm

@Deprecated("We have moved to using the HEAP", replaceWith = ReplaceWith("data.memory.Heap"))
class InternalMemory(vm: Vm) {
	val errors = vm.errors
	var memory = emptyMap<Long, Long>().toMutableMap()

	var linkedR: LongRange? = null

	@Suppress("DEPRECATION")
	var linedRef: InternalMemory? = null

	init {
		for (i in 0L until MEMORY_LIMIT) {
			memory[i] = 0
		}
	}

	fun write(address: Long, value: Long) {
		if (address.toInt() > MEMORY_LIMIT) {
			errors.invalidMemoryAddressException(address)
		}

		if (linkedR != null && linedRef != null && address in linkedR!!) {
			linedRef!!.memory[address] = value
		}

		memory[address] = value
	}

	fun read(address: Long): Long {
		if (memory[address] == 0L) {
			errors.nullAddressException(address)
		}
		if (address > MEMORY_LIMIT) {
			errors.invalidMemoryAddressException(address)
		}
		return memory[address]!!
	}

	@Deprecated("Should implement a new method for memoryV2")
	@Suppress("DEPRECATION")
	fun link(ref: InternalMemory, range: LongRange) {
		linkedR = range
		linedRef = ref
		ref.linedRef = this
		ref.linkedR = range

		for (i in range) {
			memory[i] = ref.memory[i]!!
		}
	}
}