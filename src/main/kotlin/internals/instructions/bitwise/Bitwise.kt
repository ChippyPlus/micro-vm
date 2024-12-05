package internals.instructions.bitwise

import data.registers.IntelRegisters
import data.registers.RegisterType
import data.registers.intelNames
import helpers.toLong
import internals.Vm


open class Bitwise(vm: Vm) {
	val errors = vm.errors
	val registers = vm.registers
	fun call(name: String, where: RegisterType, function: () -> Long) {
		try {
			val out = function()
			registers.write(
				where, value = out
			)
			registers.write(intelNames[IntelRegisters.ENSF], true.toLong())

		} catch (e: Exception) {
			errors.run { this@run.generalBitwiseException(message = name) }
		}
	}

}