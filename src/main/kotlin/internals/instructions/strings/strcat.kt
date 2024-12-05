package internals.instructions.strings

import data.registers.IntelRegisters
import data.registers.RegisterType
import data.registers.intelNames
import helpers.readRegisterString
import helpers.toLong
import helpers.writeClosestString


@Deprecated("Moved into stdlib functions")
fun Strings.strcat(string1: RegisterType, string2: RegisterType): Any = try {

	registers.write(intelNames[IntelRegisters.ENSF], true.toLong())

	val s1: String = helpers.readRegisterString(register = string1)
	val s2: Comparable<String> = helpers.readRegisterString(register = string2)

	val location = helpers.writeClosestString(
		string = (s1 + s2)
	)
	registers.write(RegisterType.R4, location)


} catch (_: Exception) {
	errors.generalStringException("strcat")

}