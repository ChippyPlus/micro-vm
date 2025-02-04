package internals.instructions.arithmetic

import data.registers.IntelRegisters
import data.registers.RegisterType
import data.registers.intelNames
import environment.errorsCatchable.ErrorType

/**
 * Divides the value in registerA by registerB and stores the result in the `R4` register.
 *
 * @param registerA The [RegisterType] holding the dividend.
 * @param registerB The [RegisterType] holding the divisor.
 * @throws GeneralArithmeticException If an arithmetic error occurs during the division (e.g. division by zero).
 */
fun Arithmetic.div(registerA: RegisterType, registerB: RegisterType, where: RegisterType) = call("div") {
	val a: Long = registers.read(register = registerA)
	val b: Long = registers.read(register = registerB)

	try {
		val out = a / b
		registers.write(where, out)
	} catch (_: ArithmeticException) {
		intelNames[IntelRegisters.ESF]
		registers.write(intelNames[IntelRegisters.ESF], ErrorType.DIVIDE_BY_0.code)
		return@call null
	}


}