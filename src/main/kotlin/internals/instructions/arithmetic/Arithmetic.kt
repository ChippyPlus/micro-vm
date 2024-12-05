package internals.instructions.arithmetic

import data.registers.IntelRegisters
import data.registers.RegisterType
import data.registers.intelNames
import helpers.toLong
import internals.Vm
import kotlin.math.sign


/**
 * Represents the arithmetic operations unit within the virtual machine.
 *
 * This class provides functions for performing arithmetic operations on register values.
 */
open class Arithmetic(val vm: Vm) {
	val errors = vm.errors
	val registers = vm.registers
	fun zeroFlag(out: Long) {
		if (out == 0L) registers.write(
			intelNames[IntelRegisters.ZF], true.toLong()
		)
		else registers.write(
			intelNames[IntelRegisters.ZF], false.toLong()
		)
	}

	fun signFlag(out: Long) {
		if (out.sign == -1) {
			registers.write(
				intelNames[IntelRegisters.SF], true.toLong()
			)
		} else {
			registers.write(
				intelNames[IntelRegisters.SF], false.toLong()
			)
		}
	}


	fun call(name: String, function: () -> Unit?) {

		val out = try {
			function()

		} catch (e: Exception) {
			vm.errors.generalArithmeticException(message = name)
		}
		signFlag(registers.read(RegisterType.R4))
		zeroFlag(registers.read(RegisterType.R4))



		if (out != null) {
			registers.write(intelNames[IntelRegisters.ENSF], true.toLong())
		} else {
			registers.write(intelNames[IntelRegisters.ENSF], false.toLong())
		}
	}
}