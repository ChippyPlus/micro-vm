package internals.instructions.stackOperations

import data.registers.IntelRegisters
import data.registers.RegisterType
import data.registers.intelNames
import helpers.toLong


/**
 * Pushes the value from the specified register onto the stack.
 *
 * @param registerType The register containing the value to push.
 * @throws GeneralStackOperationsException If an error occurs during the push operation (e.g. stack overflow).
 */
fun StackOperations.push(registerType: RegisterType) {
	registers.write(
		intelNames[IntelRegisters.ENSF], true.toLong()
	) // It's above the next expr because the internal stack may throw its own errors

	internalStack.push(element = registers.read(register = registerType))
}

fun StackOperations.pushl(registerType: Long) {
	registers.write(
		intelNames[IntelRegisters.ENSF], true.toLong()
	) // It's above the next expr because the internal stack may throw its own errors

	internalStack.push(registerType)
}
