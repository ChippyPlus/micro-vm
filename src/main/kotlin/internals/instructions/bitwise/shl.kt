package internals.instructions.bitwise


import data.registers.RegisterType

fun Bitwise.shl(operand1: RegisterType, operand2: RegisterType, where: RegisterType) = call("shl", where) {
	registers.read(operand1) shl registers.read(operand2).toInt()

}