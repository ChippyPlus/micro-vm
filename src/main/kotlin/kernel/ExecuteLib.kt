package kernel

import environment.reflection.reflection
import internals.Vm
import kernel.libEx.executeKt
import kernel.libEx.executeMar
import kernel.libEx.findMarLib
import java.io.File


class ExecuteLib(val vm: Vm) {
	var currentFunction = ""
	var enabledFunction = false
	val kp = reflection.groupTrackedVmByVm()[vm]!!
	suspend fun execute(name: String) {
		if (findMarLib(name) != null) {
			val file = File(findMarLib(name)!!)
			enabledFunction = true
			val lastFile = kp.file
			kp.file = file
			executeMar(file)
			kp.file = lastFile
			enabledFunction = false
		} else {
			executeKt(name)
		}
	}
}


