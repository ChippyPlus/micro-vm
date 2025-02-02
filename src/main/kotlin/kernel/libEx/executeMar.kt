package kernel.libEx

import engine.parserReturn
import internals.Vm
import kernel.ExecuteLib
import kernel.UnstableSnapShots
import kernel.process.KProcess
import os
import java.io.File

@OptIn(UnstableSnapShots::class)
suspend fun ExecuteLib.executeMar(file: File) {

	val localKp = KProcess(Vm(), file) //	os.snapShotManager.snapShotRegisters(kp)
	val registerReserves = vm.registers.registers.map { (k, v) -> k to v.copy() }.toMap()

	val oldInstructs = kp.instructionMemory

	localKp.instructionMemory = parserReturn(localKp.vm, file.readLines())
	localKp.notifyOS()
	for (registerType in registerReserves) vm.registers.registers[registerType.key] = registerType.value

	kp.instructionMemory = oldInstructs

//	os.snapShotManager.populateSnapShotRegister(kp)
}