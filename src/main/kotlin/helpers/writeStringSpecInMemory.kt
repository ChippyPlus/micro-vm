package helpers


/**
 * Writes a string to memory at the specified [destinationAddress].
 *
 * @param string The string to write to memory.
 * @param destinationAddress The starting [MemoryAddress] where the string will be written.
 * @throws NotFreeMemoryException If the destination memory range is not free.
 */
fun Helpers.writeStringSpecInMemory(string: String, destinationAddress: Long) {
	val allocMem = string.length

	for (i in (destinationAddress until (destinationAddress + allocMem))) {
		if (heap!!.get(i) != 0L) {
			errors.notFreeMemoryException(i.toString())
		}
	}

	for ((index, i) in (destinationAddress until (destinationAddress + allocMem)).withIndex()) {
		heap!!.set(i, (string[index].code.toLong()))
	}
	heap!!.set(destinationAddress + allocMem, 0L)
}

