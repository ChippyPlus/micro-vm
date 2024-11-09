package internals.systemCalls.calls

import data.registers.RegisterType
import data.registers.read
import environment.interuptManager.InterruptManager
import internals.systemCalls.SystemCall

fun SystemCall.handleSignals(code: RegisterType) {
	InterruptManager().handleSystemCallRequest(code.read().toInt())
}