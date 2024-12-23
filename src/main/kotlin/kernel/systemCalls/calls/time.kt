package kernel.systemCalls.calls

import data.registers.RegisterType
import kernel.systemCalls.SystemCall

/**
 * Gets the current system time in milliseconds.
 *
 * System call number: 14
 */
fun SystemCall.time() = call("time") {
	registers.write(register = RegisterType.R2, value = System.currentTimeMillis())
}