package internals.instructions.controlFlow

import data.registers.IntelRegisters
import data.registers.intelNames

/**
 * Performs a conditional jump to the target address if the value in the test register is zero.
 *
 * @param targetAddress The target address (line number) to jump to.
 * @param testRegister The register to check for a zero value.
 * @throws GeneralControlFlowException If an error occurs during the jump operation.
 */
fun ControlFlow.jz(targetAddress: Long): Any = try {
	if (registers.read(intelNames[IntelRegisters.EF]) == 0L) {
		vm.pc = targetAddress
	} else {
		// I'm not sure why I need this else block
	}

} catch (_: Exception) {
	errors.GeneralControlFlowException(message = "Jz")

}