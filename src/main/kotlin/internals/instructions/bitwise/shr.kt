package internals.instructions.bitwise

import data.registers.RegisterType


fun Bitwise.shr(operand1: RegisterType, operand2: RegisterType, where: RegisterType) = call("shr", where) {
	registers.read(operand1) shr registers.read(operand2).toInt()
}