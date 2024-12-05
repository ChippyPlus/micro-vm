package kernel

import config
import data.memory.EMPTY_MEM_VAL
import data.memory.Heap.AllocatedBlock
import data.registers.Registers

class OS {
	val mainMemory = LongArray((config.memorySize - config.stackSize).toInt()) { EMPTY_MEM_VAL }
	val usedMemory = mutableListOf<AllocatedBlock>()
	val taskManager = TaskManagerV2()
	val registers = Registers()
	val snapShotManager = SnapShotManager(registers)
	val ipc = Ipc()
}