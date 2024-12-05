package kernel

import data.registers.RegisterType
import data.registers.Registers
import internals.Vm
import kernel.process.KProcess

class SnapShotManager(private val registers: Registers) {

	private val snapshots = mutableMapOf<KProcess, Map<RegisterType, Long>>()

	fun populateSnapShotRegister(kProcess: KProcess) {
		for (i in snapshots[kProcess]!!) {
			registers.write(i.key, i.value)
		}
		println("{${kProcess}} [POPULATE] G1=${snapshots[kProcess]!![RegisterType.G1]}")
	}

	fun snapShotRegisters(kProcess: KProcess) {
		val allRegisters = mutableMapOf<RegisterType, Long>()
		for (i in RegisterType.entries) {
			allRegisters[i] = registers.read(i)
		}
		snapshots[kProcess] = allRegisters

	}

	fun initSnapShotRegister(kProcess: KProcess) {
		for (register in RegisterType.entries) {
			registers.write(register, 0L)
		}
		snapShotRegisters(kProcess)

	}
}


@UnstableSnapShots
@Deprecated("Please dont use this")
class SnapShotManagerLegacy(val vm: Vm) {
	@Deprecated("Please dont use this")
	fun populateSnapShotRegister(snapShotRegisters: Map<RegisterType, Long>) {
		for (i in snapShotRegisters) {
			vm.registers.write(i.key, i.value)
		}
	}

	@Deprecated("Please dont use this")
	fun snapShotRegisters(): Map<RegisterType, Long> {
		val allRegisters = mutableMapOf<RegisterType, Long>()
		allRegisters.forEach { if (!it.key.name.startsWith('I')) allRegisters.remove(it.key) }
		for (i in RegisterType.entries) {
			allRegisters[i] = vm.registers.read(i)
		}
		return allRegisters
	}

}


@RequiresOptIn
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
annotation class UnstableSnapShots



