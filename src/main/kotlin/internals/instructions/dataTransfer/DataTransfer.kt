package internals.instructions.dataTransfer

import data.registers.IntelRegisters
import data.registers.intelNames
import errors
import helpers.toDouble
import registers


/**
 * Represents the data transfer operations unit within the virtual machine.
 *
 * This class provides functions for moving data between registers and loading literal values.
 */
open class DataTransfer {


	fun call(name: String, function: () -> Unit?) {
		try {
			val out = function()

			if (out != null) {
				registers.write(intelNames[IntelRegisters.ENSF], true.toDouble())
			} else {
				registers.write(intelNames[IntelRegisters.ENSF], false.toDouble())
			}
		} catch (e: Exception) {
			errors.GeneralDataTransferException(message = name)
		}
	}
}

