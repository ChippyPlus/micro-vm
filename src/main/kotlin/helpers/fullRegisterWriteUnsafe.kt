package helpers

import data.registers.enumIdenifiers.*
import functionRegisters
import generalRegisters
import internalFunctionRegisters
import returnRegisters
import systemRegisters

fun fullRegisterWriteUnsafe(register: SuperRegisterType, value: Long?) {
	when (register) {
		SuperRegisterType.G1 -> generalRegisters.write(register.toGeneralRegisterType(), value)
		SuperRegisterType.G2 -> generalRegisters.write(register.toGeneralRegisterType(), value)
		SuperRegisterType.G3 -> generalRegisters.write(register.toGeneralRegisterType(), value)
		SuperRegisterType.G4 -> generalRegisters.write(register.toGeneralRegisterType(), value)
		SuperRegisterType.S1 -> systemRegisters.write(register.toSystemRegisterType(), value)
		SuperRegisterType.S2 -> systemRegisters.write(register.toSystemRegisterType(), value)
		SuperRegisterType.S3 -> systemRegisters.write(register.toSystemRegisterType(), value)
		SuperRegisterType.S4 -> systemRegisters.write(register.toSystemRegisterType(), value)
		SuperRegisterType.R1 -> returnRegisters.write(register.toReturnRegisterType(), value)
		SuperRegisterType.R2 -> returnRegisters.write(register.toReturnRegisterType(), value)
		SuperRegisterType.R3 -> returnRegisters.write(register.toReturnRegisterType(), value)
		SuperRegisterType.R4 -> returnRegisters.write(register.toReturnRegisterType(), value)
		SuperRegisterType.F1 -> functionRegisters.write(register.toFunctionRegisterType(), value)
		SuperRegisterType.F2 -> functionRegisters.write(register.toFunctionRegisterType(), value)
		SuperRegisterType.F3 -> functionRegisters.write(register.toFunctionRegisterType(), value)
		SuperRegisterType.F4 -> functionRegisters.write(register.toFunctionRegisterType(), value)
		SuperRegisterType.IF1 -> internalFunctionRegisters.write(register.toInternalFunctionType(), value)
		SuperRegisterType.IF2 -> internalFunctionRegisters.write(register.toInternalFunctionType(), value)
		SuperRegisterType.IF3 -> internalFunctionRegisters.write(register.toInternalFunctionType(), value)
		SuperRegisterType.IF4 -> internalFunctionRegisters.write(register.toInternalFunctionType(), value)
	}
}