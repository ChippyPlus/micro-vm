package internals.instructions.arithmetic

import data.registers.IntelRegisters
import data.registers.RegisterType
import data.registers.intelNames
import helpers.toLong


fun Arithmetic.gt(operand1: RegisterType, operand2: RegisterType) = try {

	val out = registers.read(register = operand1) > registers.read(register = operand2)

	registers.write(
		intelNames[IntelRegisters.GF], if (out) true.toLong() else false.toLong()
	)

} catch (e: Exception) {
	errors.generalArithmeticException(message = "gt")

}