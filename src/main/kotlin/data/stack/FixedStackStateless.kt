package data.stack

import config
import data.registers.IntelRegisters
import data.registers.intelNames
import environment.errorsCatchable.ErrorType
import helpers.toLong
import kernel.process.KProcess

class FixedStackStateless(kp: KProcess) {
	private val maxSize = config.stackSize
	private val registers = kp.vm.registers
	private val stack = Array<Long>(maxSize) { _ -> 0 }
	private var topIndex = -1

	fun push(element: Long) {
		if (isFull()) {
			registers.write(intelNames[IntelRegisters.ENSF], false.toLong())
			registers.write(intelNames[IntelRegisters.ESF], ErrorType.STACK_OVERFLOW.code)
			return
		}
		topIndex++
		stack[topIndex] = element
	}

	fun pop(): Long {
		if (isEmpty()) {
			registers.write(intelNames[IntelRegisters.ENSF], false.toLong())
			registers.write(intelNames[IntelRegisters.ESF], ErrorType.STACK_UNDERFLOW.code)
			return 0
		}
		val element = stack[topIndex]
		stack[topIndex] = 0
		topIndex--
		return element
	}

	@Suppress("unused")
	fun inspect(): Array<Long> {
		return stack
	}

	fun peek(): Long {
		if (isEmpty()) {
			registers.write(intelNames[IntelRegisters.ENSF], false.toLong())
			registers.write(intelNames[IntelRegisters.ESF], ErrorType.STACK_UNDERFLOW.code)
			return 0

		}
		return stack[topIndex]
	}

	fun isEmpty(): Boolean {
		return topIndex == -1
	}

	fun isFull(): Boolean {
		return topIndex == maxSize - 1
	}
}