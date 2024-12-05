package environment

import data.registers.RegisterType
import internals.Vm
import internals.instructions.misc.HelpJsonArguments
import kotlin.system.exitProcess


@Suppress("unused")
class VMErrors(val vm: Vm? = null, val pc: Long? = null) {
	fun invalidRegisterException(message: String) {
		System.err.println("${prefix()}: Invalid Register of type \"$message\"")
		exitProcess(1)
	}

	fun invalidMemoryAddressException(message: Long) {
		System.err.println("${prefix()}: Invalid Memory Address \"${message}\"")
		exitProcess(2)
	}

	fun invalidMemoryAddressException(message: String) {
		System.err.println("${prefix()}: Invalid Memory Address \"${message}\"")
		exitProcess(2)
	}

	fun invalidInstructionException(message: String) {
		System.err.println("${prefix()}: Invalid Instruction \"${message}\"")
		exitProcess(3)
	}

	fun invalidSystemCallException(message: String) {
		System.err.println("${prefix()}: Invalid System Call \"${message}\"")
		exitProcess(4)
	}

	fun stackOverflowException() {
		System.err.println("${prefix()}: Stack Overflow Exception")
		exitProcess(5)
	}

	fun emptyStackException() {
		System.err.println("${prefix()}: Empty Stack Exception")
		exitProcess(6)
	}

	@Deprecated("Please remove General ERRORS")
	fun generalArithmeticException(message: String) {
		System.err.println("${prefix()}: General Arithmetic Exception \"$message operation failed\"")
		exitProcess(7)
	}

	@Deprecated("Please remove General ERRORS")
	fun systemCallGeneralException(message: String, info: String? = null) {
		if (info == null) {
			System.err.println("${prefix()}: System Call General Exception \"$message operation failed\"")
		} else {
			System.err.println("${prefix()}: System Call General Exception \"$message\" \"$info\"")
		}
		exitProcess(8)
	}


	fun fileAccessException() {
		System.err.println("${prefix()}: File Access Exception")
		exitProcess(9)
	}

	fun socketException() {
		System.err.println("${prefix()}: Socket Exception")
		exitProcess(10)
	}

	fun memoryAllocationException(message: String) {
		System.err.println("${prefix()}: Memory Allocation Exception \"$message\"")
		exitProcess(11)
	}

	fun invalidInstructionArgumentException(message: String) {
		System.err.println("${prefix()}: Invalid Instruction Argument \"$message\"")
		exitProcess(12)
	}

	@Deprecated("Registers use a 0 init, instead of a null init")
	fun nullRegisterException(message: RegisterType) {
		System.err.println("${prefix()}: Null Register of \"$message\"")
		exitProcess(13)
	}

	@Deprecated("Addresses use a 0 init, instead of a null init")
	fun nullAddressException(message: Long) {
		System.err.println("${prefix()}: Null Address of \"${message}\"")
		exitProcess(14)
	}

	@Deprecated("File Descriptors are not used anymore")
	fun invalidFileDescriptorException(message: String) {
		System.err.println("${prefix()}: Invalid File Descriptor of \"$message\"")
		exitProcess(15)
	}

	fun notFreeMemoryException(message: String) {
		System.err.println("${prefix()}: Address \"$message\" is not free Memory")
		exitProcess(16)
	}

	@Deprecated("Please remove General ERRORS")
	fun generalBitwiseException(message: String) {
		System.err.println("${prefix()}: General Bitwise Exception \"$message operation failed\"")
		exitProcess(17)
	}

	@Deprecated("Please remove General ERRORS")
	fun generalControlFlowException(message: String) {
		System.err.println("${prefix()}: General ControlFlow Exception \"$message operation failed\"")
		exitProcess(18)
	}

	@Deprecated("Please remove General ERRORS")
	fun generalDataTransferException(message: String) {
		System.err.println("${prefix()}: General DataTransfer Exception \"$message operation failed\"")
		exitProcess(19)
	}

	@Deprecated("Please remove General ERRORS")
	fun generalIoAbstractionsException(message: String) {
		System.err.println("${prefix()}: General IoAbstractions Exception \"$message operation failed\"")
		exitProcess(20)
	}

	@Deprecated("Please remove General ERRORS")
	fun generalMemoryException(message: String) {
		System.err.println("${prefix()}: General Memory Exception \"$message operation failed\"")
		exitProcess(21)
	}

	@Deprecated("Please remove General ERRORS")
	fun generalStackOperationsException(message: String) {
		System.err.println("${prefix()}: General Stack Operation \"$message operation failed\"")
		exitProcess(22)
	}

	@Deprecated("Please remove General ERRORS")
	fun generalStringException(message: String) {
		System.err.println("${prefix()}: General Strings Exception \"$message operation failed\"")
		exitProcess(23)
	}

	@Deprecated("Please remove General ERRORS")
	fun invalidArgumentException(info: HelpJsonArguments) {
		System.err.println("${prefix()}:Missing argument Exception: \"${info.name}\"")
		exitProcess(24)
	}

	@Deprecated("Please remove General ERRORS")
	fun invalidArgumentFormatException(badType: String, shouldBe: String) {
		System.err.println(
			"${prefix()}:Invalid Argument Format Exception: Invalid type of \"$badType\", should be " + "\"$shouldBe\""
		)
		exitProcess(25)
	}

	fun missingLibraryException(message: String) {
		System.err.println("${prefix()}: Missing Library Exception \"$message\"")
		exitProcess(26)
	}

	fun fileAlreadyExistsException(message: String) {
		System.err.println("${prefix()}: File Already Exists Exception: \"$message\"")
		exitProcess(27)
	}

	fun fileNotFoundException(message: String) {
		System.err.println("${prefix()}: File Not Found Exception: \"$message\"")
		exitProcess(28)
	}

	fun badSymbolAtRuntimeException(message: String) {
		System.err.println("${prefix()}: Bad Symbol at Runtime Exception in MAR \"${message}\"")
		exitProcess(29)
	}

	fun invalidRegisterTypeException(message: String) {
		System.err.println("${prefix()}: Invalid Register Type Exception: \"$message\"")
		exitProcess(30)
	}

	fun invalidPcValueException(message: String) {
		System.err.println("${prefix()}: Invalid Pc Value Exception: \"$message\"")
		exitProcess(31)
	}

	fun processNotFoundException(message: String) {
		System.err.println("${prefix()}: Process Not Found Exception: \"$message\"")
		exitProcess(32)
	}


	fun invalidOffsetForBlockException(message: Long) {
		System.err.println("${prefix()}: Invalid Offset For Block Exception: \"$message\"")
		exitProcess(33)
	}

	fun accessingUnallocatedMemoryException(message: Long) {
		System.err.println("${prefix()}: Accessing Unallocated Memory Exception: \"$message\"")
		exitProcess(34)
	}


	private fun prefix(): String {
		return if (vm != null) {
			"ERROR:${vm.pc}"
		} else {
			"ERROR:$pc"
		} //		 Let's just forget about line numbers for now
	}

}


