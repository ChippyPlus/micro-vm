package internals.instructions.xFloats

import data.registers.RegisterType

fun XFloats.xLit(registerX: RegisterType, valueX: Long) {
	registers.writeX(registerX, valueX)
}