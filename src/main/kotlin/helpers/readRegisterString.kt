package helpers

import data.registers.RegisterType
import data.registers.read

/**
 * Reads a null-terminated string from memory starting at the address stored in the specified register.
 *
 * @param register The register containing the starting memory address of the string.
 * @return The string read from memory.
 */
fun Helpers.readRegisterString(register: RegisterType): String {
	var index = 0
	var string = ""
	while (true) {
		val byte = vm.heap!!.get(registers.read(register) + index)
		if (byte == 0L) break
		string += byte.toInt().toChar()
		index++
	}
//	println("[] $register = {${register.read(vm)}}  $string")
	return string
}