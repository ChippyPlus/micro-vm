package helpers

import MEMORY_LIMIT
import data.memory.MemoryAddress
import kotlin.system.exitProcess


// should work with writeClosestString
fun Helpers.findFreeMemory(size: Long): Long {
	var currentAddress = 0L
	var freeCount = 0L

	while (currentAddress + size <= MEMORY_LIMIT) {
		if (vm.internalMemory.memory[MemoryAddress(currentAddress)]?.value == null) {
			freeCount++
			if (freeCount == size + 1L) {
				return currentAddress - size
			}
		} else {
			freeCount = 0L
		}
		currentAddress++
	}

	vm.errors.MemoryAllocationException("Could not allocate memory")
	exitProcess(1)
}
