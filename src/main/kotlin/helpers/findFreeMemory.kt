package helpers

import MEMORY_LIMIT
import kotlin.system.exitProcess


// should work with writeClosestString
@Deprecated("The MAYBE new memory system may not like this function")
fun Helpers.findFreeMemory(size: Long): Long {
	var currentAddress = 0L
	var freeCount = 0L

	while (currentAddress + size <= MEMORY_LIMIT) {
		if (vm.heap!!.get(currentAddress) == 0L) {
			freeCount++
			if (freeCount == size + 1L) {
				return currentAddress - size
			}
		} else {
			freeCount = 0L
		}
		currentAddress++
	}

	vm.errors.memoryAllocationException("Could not allocate memory")
	exitProcess(1)
}
