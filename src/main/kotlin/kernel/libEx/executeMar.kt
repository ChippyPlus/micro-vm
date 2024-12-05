package kernel.libEx

import engine.execution.Execute
import engine.parserReturn
import kernel.ExecuteLib
import kernel.SnapShotManagerLegacy
import kernel.UnstableSnapShots
import java.io.File

@OptIn(UnstableSnapShots::class)
suspend fun ExecuteLib.executeMar(file: File) {
	val vm = vm
	val smLegacy = SnapShotManagerLegacy(vm)
	val oldPC = kp.vm.pc
	val oldInstructs = kp.instructionMemory
	val snapshot = smLegacy.snapShotRegisters()
	vm.pc - 2
	val instructions = parserReturn(vm, file.readLines())
	Execute(kp).run(instructions)
	smLegacy.populateSnapShotRegister(snapshot)
	kp.vm.pc = oldPC
	kp.instructionMemory = oldInstructs
}