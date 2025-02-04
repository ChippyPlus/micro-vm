package internals.instructions.arithmetic

import data.registers.RegisterType

/**
 * Subtracts the value in registerB from registerA and stores the result in the `R4` register.
 *
 * @param registerA The [RegisterType] holding the minuend.
 * @param registerB The [RegisterType] holding the subtrahend.
 * @throws GeneralArithmeticException If an arithmetic error occurs during the subtraction.
 */

fun Arithmetic.sub(registerA: RegisterType, registerB: RegisterType, where: RegisterType) = call("sub") {
	val a: Long = registers.read(register = registerA)
	val b: Long = registers.read(register = registerB)
	val out = a - b
	registers.write(where, out)


}