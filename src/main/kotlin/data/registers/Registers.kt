package data.registers

data class FDRegister(val isDouble: Boolean, val value: Long?)

class Registers {
	val registers = mutableMapOf<RegisterType, RegisterData>()

	init {
		for (register in RegisterType.entries) {
			registers[register] = RegisterData(

				name = register, data = 0, dataType = if (register.name.startsWith('X')) {
					RegisterDataType.RFloat
				} else if (register.name == "R5") {
					RegisterDataType.RFloat
				} else {
					RegisterDataType.RLong
				}
			)
		}
	}


	fun readX(registerX: RegisterType): FDRegister {
		return if (registers[registerX]!!.dataType == RegisterDataType.RFloat) {
			FDRegister(false, registers[registerX]!!.data)
		} else {
			FDRegister(true, registers[registerX]!!.data)
		}
	}

	fun writeX(registerX: RegisterType, valueX: FDRegister) {
		registers[registerX]!!.write(valueX.value)
	}


	fun read(register: RegisterType): Long {
		return registers[register]!!.read()!!
	}

	fun write(register: RegisterType, value: Long) {
		registers[register]!!.write(value)
	}


}