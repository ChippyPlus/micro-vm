package kernel.libEx

import internals.Vm
import kernel.ExecuteLib
import kernel.process.KProcess
import kilb.Klib
import os
import java.io.File

suspend fun ExecuteLib.executeKt(name: String) {
	val newVm = Vm()
	val registerReserves =
		vm.registers.registers.map { (k, v) -> k to v.copy() }.toMap()

	val newKp = KProcess(newVm, File("\$lib-$name"), isKlib = true)
//	os.snapShotManager.snapShotRegisters(newKp)
	for (registerType in registerReserves) vm.registers.registers[registerType.key] = registerType.value
	if (!Klib(newKp).match(name)) vm.errors.missingLibraryException(name) // Kt should be the last resort
	currentFunction = name

}