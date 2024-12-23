package internals.instructions.arithmetic

import data.registers.RegisterType

/**
 * Adds the values in two registers and stores the result in the `R4` register.
 *
 * @param registerA The [RegisterType] holding the first operand.
 * @param registerB The [RegisterType] holding the second operand.
 * @throws GeneralArithmeticException If an arithmetic error occurs during the addition.
 */
fun Arithmetic.add(registerA: RegisterType, registerB: RegisterType, where: RegisterType) = call("add") {
	val a: Long = registers.read(register = registerA)
	val b: Long = registers.read(register = registerB)
	val out = a + b
	registers.write(where, out)

	zeroFlag(out)
	signFlag(out)


}