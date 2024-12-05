package internals.instructions.strings

import data.registers.IntelRegisters
import data.registers.RegisterType
import data.registers.intelNames
import helpers.readRegisterString
import helpers.toLong

/**
 * Compares two strings lexicographically and sets the `R4` register to indicate the result.
 *
 * - If the strings are equal, `R4` is set to 0.
 * - If the strings are not equal, `R4` is set to 1.
 *
 * @param string1 The register containing the memory address of the first string.
 * @param string2 The register containing the memory address of the second string.
 * @throws GeneralStringException If an error occurs during the string comparison.
 */
@Deprecated("Moved into stdlib functions")
fun Strings.strcmp(string1: RegisterType, string2: RegisterType) = try {
	registers.write(intelNames[IntelRegisters.ENSF], true.toLong())

	val s1 = helpers.readRegisterString(string1)
	val s2 = helpers.readRegisterString(string2)
	if (s1 == s2) {
		registers.write(
			register = RegisterType.R4, value = 0
		)
	} else {
		registers.write(register = RegisterType.R4, value = 1)
	}


} catch (_: Exception) {
	errors.generalStringException("Strcmp")
}