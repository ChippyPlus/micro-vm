package internals.instructions.controlFlow


/**
 * Performs an unconditional jump to the specified target address.
 *
 * @param targetAddress The target address (line number) to jump to.
 * @throws GeneralControlFlowException If an error occurs during the jump operation.
 */
fun ControlFlow.jmp(targetAddress: Long): Any = try {
	vm.pc = targetAddress
} catch (_: Exception) {
	errors.generalControlFlowException(message = "Jmp")
}