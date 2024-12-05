package kernel.interuptManager

import data.registers.RegisterType
import data.registers.write
import helpers.toLong
import internals.Vm
import sun.misc.Signal
import sun.misc.Signal.handle
import java.lang.Runtime.getRuntime
import kotlin.system.exitProcess

class InterruptManager(val vm: Vm) {

	private fun handle(name: String, jumpWhere: Long) {
		handle(Signal(name)) {
			vm.pc = jumpWhere - 1
			RegisterType.I9.write(vm, true.toLong())
		}
	}

	fun sendSignal(code: Int, process: Long) {
		val exitCode = getRuntime().exec("kill -$code $process").exitValue()
		if (exitCode == 1) {
			vm.errors.systemCallGeneralException("sendSignal", "Bad PID")
		}
	}


	fun handleSystemCallRequest(code: Int, jumpWhere: Long) {
		var did = false
		Signals.entries.forEach {
			if (it.code.equals(code)) handle(it.signalName, jumpWhere);did = true;return@forEach
		}
		if (!did) {
			vm.errors.systemCallGeneralException("handleSignals")
		}

	}
}


fun main() {


	println(ProcessHandle.current().pid())

	handle(Signal("INFO")) {
		println("Oh no received a signal!!!!")
		exitProcess(0)
	}


	while (true) {
		Thread.sleep(1000)
	}
}
