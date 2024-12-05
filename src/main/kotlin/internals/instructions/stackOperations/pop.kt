package internals.instructions.stackOperations

import data.registers.IntelRegisters
import data.registers.RegisterType
import data.registers.intelNames
import helpers.toLong

/**
 * Pops the top value from the stack and stores it in the specified register.
 *
 * @param destination The register to store the popped value.
 * @throws GeneralStackOperationsException If an error occurs during the pop operation (e.g. stack underflow).
 */
fun StackOperations.pop(destination: RegisterType) {
	registers.write(
		intelNames[IntelRegisters.ENSF], true.toLong()
	) // It's above the next expr because the internal stack may throw its own errors


	registers.write(
		register = destination, value = internalStack.pop()
	)
}