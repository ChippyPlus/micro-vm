package kernel.libEx

import internals.Vm
import kernel.ExecuteLib
import kernel.process.KProcess
import kilb.Klib
import os
import java.io.File

fun ExecuteLib.executeKt(name: String) {
	val newKp = KProcess(Vm(), File("Non-existent"))
	os.snapShotManager.snapShotRegisters(newKp)
	if (!Klib(newKp).match(name)) {
		vm.errors.missingLibraryException(name) // Kt should be the last resort
	}
	currentFunction = name

}