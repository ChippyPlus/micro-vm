package kernel.process

import data.memory.ProcessMemory
import engine.execution.InstructData
import environment.reflection.reflection
import helpers.RuntimeStates
import internals.Vm
import internals.debug.Debug
import kernel.ExecuteLib
import os
import java.io.File

data class KProcess(val vm: Vm, var file: File, val isKlib: Boolean = false) {
	val debug = Debug(this)
	var runtimeState = RuntimeStates.RUNNING
	val addressSpace = ProcessMemory(this)
	var currentInstruction: InstructData = InstructData(name = "init", arrayOf())

	init { // Look at the console. There's 2 KProcesses. Different. But both have the same VM!??!?!?!?!??!?!?!?
		reflection.vmTracker.add(this)
		vm.heap = addressSpace.heap
		vm.libExecute = ExecuteLib(vm)
		os.snapShotManager.initSnapShotRegister(this)
	}


	//	val registers = Registers(vm) // PLEASE COME BACK!


	val ipcPermissions = mutableListOf<Int>()
	var parent: Int? = null
	var instructionMemory = emptyList<InstructData>()
	val id: Int = reflection.vmTracker.size
	val thread: Thread = Thread.currentThread()


	suspend fun notifyOS() = os.taskManager.add(this)
}