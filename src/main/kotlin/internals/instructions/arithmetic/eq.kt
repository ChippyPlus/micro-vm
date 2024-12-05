package internals.instructions.arithmetic

import data.registers.IntelRegisters
import data.registers.RegisterType
import data.registers.intelNames
import helpers.toLong

/**
 * Compares the values in two registers for equality and sets the `R4` register accordingly.
 *
 * - If the values are equal, `R4` is set to 0.
 * - If the values are not equal, `R4` is set to 1.
 *
 * @param operand1 The [RegisterType] holding the first operand.
 * @param operand2 The [RegisterType] holding the second operand.
 * @throws GeneralArithmeticException If an error occurs during the comparison.
 */
fun Arithmetic.eq(operand1: RegisterType, operand2: RegisterType) = try {

	val out = registers.read(register = operand1) == registers.read(register = operand2)
	registers.write(intelNames[IntelRegisters.EF], out.toLong())
	registers.write(intelNames[IntelRegisters.ZF], out.toLong())

} catch (e: Exception) {
	errors.generalArithmeticException(message = "eq")
}