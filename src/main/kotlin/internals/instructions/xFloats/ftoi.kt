package internals.instructions.xFloats

import data.registers.RegisterType

fun XFloats.ftoi(registerX: RegisterType, register: RegisterType) {
	val valueLong = registers.readX(registerX)

	if (valueLong.isDouble) {
		if (valueLong.value == null) {
			vm.errors.nullRegisterException(registerX)
		}
		registers.write(register, Double.fromBits(valueLong.value!!).toLong())
	} else {
		registers.write(register, Float.fromBits(valueLong.value!!.toInt()).toLong())
	}
}