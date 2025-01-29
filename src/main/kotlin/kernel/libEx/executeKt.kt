package kernel.libEx

import data.registers.RegisterType
import funcR
import internals.Vm
import kernel.ExecuteLib
import kernel.process.KProcess
import kilb.Klib
import os
import java.io.File

suspend fun ExecuteLib.executeKt(name: String) {
	val newVm = Vm()
	val functionRegisterReserves =
		vm.registers.registers.filter { it.key.name.startsWith('F') }.map { (k, v) -> k to v.copy() }.toMap()

	val newKp = KProcess(newVm, File("\$lib-$name"), isKlib = true)
	os.snapShotManager.snapShotRegisters(newKp)

	for (registerType in functionRegisterReserves) {
		vm.registers.registers[registerType.key] = registerType.value
	}


	if (!Klib(newKp).match(name)) {
		vm.errors.missingLibraryException(name) // Kt should be the last resort
	}

	currentFunction = name
}