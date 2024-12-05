package kernel.systemCalls

import data.registers.IntelRegisters
import data.registers.RegisterType
import data.registers.intelNames
import data.registers.write
import environment.reflection.reflection
import helpers.toLong
import internals.Vm
import kernel.process.KProcess
import kernel.systemCalls.calls.*
import kotlin.system.exitProcess

/**
 * Handles the execution of system calls within the virtual machine.
 */
@Suppress("UNUSED_EXPRESSION")
class SystemCall(val vm: Vm) {
	val kp: KProcess by lazy { // This may be a BAD IDEA
		reflection.groupTrackedVmByVm()[vm]!!
	}

	val helpers = vm.helpers
	val errors = vm.errors
	val registers = vm.registers

	suspend fun execute(
		callId: RegisterType,
		s2: RegisterType,
		s3: RegisterType,
		s4: RegisterType,
	) {
		when (registers.read(callId).toInt()) {
			1 -> newFile(s2)
			2 -> readFile(s2)
			3 -> writeFile(s2, s3)
			4 -> listFiles()
			5 -> deleteFile(s2)
			6 -> exit(s2)
			7 -> exec(s2)
			8 -> fork()
//			9 -> spawn(s2)
			10 -> shareM(s2, s3, s4)
			11 -> pauseT(s2)
			12 -> continueT(s2)
			14 -> time()
			16 -> getPid()
			17 -> getUid()
			18 -> handleSignals(s2, s3)
			19 -> sendSignal(s2, s3)
			24 -> writeIo(s2)
			25 -> readIo()
			26 -> allocate(s2) // TODO Update docs with 26 and 27 now alloc & dealloc
			27 -> dealloc(s2)
			29 -> getMyPid()
			30 -> Ipc(vm).link(s2, s3)
			31 -> "unlink_pro"
			32 -> Ipc(vm).send(s2, s3)
			33 -> Ipc(vm).receive(s2)
			34 -> getParentPid()


			else -> errors.invalidSystemCallException(registers.read(callId).toString())
		}
	}


	inline fun call(name: String, crossinline function: () -> Unit?) {

		val functionResult = try {
			function()
		} catch (_: Exception) {
			errors.systemCallGeneralException(message = name)
			exitProcess(29384)
		}

		if (functionResult != null) {
			registers.write(intelNames[IntelRegisters.ENSF], true.toLong())
		} else {
			registers.write(intelNames[IntelRegisters.ENSF], false.toLong())
		}
	}


	fun getMyPid() {
		RegisterType.R2.write(vm, reflection.groupTrackedVmByVm()[vm]!!.id.toLong())
	}


	fun getParentPid() {
		RegisterType.R2.write(vm, reflection.groupTrackedVmByVm()[vm]!!.parent?.toLong() ?: -1L)
	}
}