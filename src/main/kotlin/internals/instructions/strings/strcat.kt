package internals.instructions.strings

import data.registers.enumIdenifiers.SuperRegisterType
import environment.VMErrors
import errors
import helpers.readRegisterString
import helpers.writeRegisterString

fun Strings.strcat(string1: SuperRegisterType, string2: SuperRegisterType): Any = try {
    val s1: String = readRegisterString(register = string1)
    val s2: Comparable<String> = readRegisterString(register = string2)
    @Suppress("UNUSED_VARIABLE") val newString = (s1 + s2).apply {
        writeRegisterString(
            register = SuperRegisterType.R4,
            string = this@apply
        )
    }

} catch (_: Exception) {
    @Suppress("RemoveExplicitTypeArguments")
    with<VMErrors, Unit>(receiver = errors) {
        GeneralStringException("strcat")
    }
}