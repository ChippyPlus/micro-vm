package internals.instructions.dataTransfer

import data.registers.IntelRegisters
import data.registers.intelNames
import helpers.toLong
import internals.Vm


/**
 * Represents the data transfer operations unit within the virtual machine.
 *
 * This class provides functions for moving data between registers and loading literal values.
 */
open class DataTransfer(vm: Vm) {
	val registers = vm.registers
	val errors = vm.errors
	val heap = vm.heap


	fun call(name: String, function: () -> Unit?) {
		try {
			val out = function()


			if (out != null) {
				registers.write(intelNames[IntelRegisters.ENSF], true.toLong())
			} else {
				registers.write(intelNames[IntelRegisters.ENSF], false.toLong())
			}
		} catch (e: Exception) {
			errors.generalDataTransferException(message = name)
		}
	}
}

