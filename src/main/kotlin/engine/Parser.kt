package engine

import data.registers.RegisterType
import data.registers.toRegisterDataType
import engine.execution.InstructData
import helpers.gatherHelp
import helpers.toRegisterType
import internals.Vm
import kernel.process.KProcess
import kotlin.system.exitProcess

fun parser(kp: KProcess, file: List<String>) {
	kp.instructionMemory = parserReturn(kp.vm, file)
}


fun parserReturn(vm: Vm, file: List<String>): List<InstructData> {
	val out = emptyArray<InstructData>().toMutableList()
	val tokens = emptyList<MutableList<String>>().toMutableList()

	// Read each line from the file and split it into tokens
	for (line in file) {
		val secretLineParts = emptyList<String>().toMutableList()
		for (token in line.split(' ')) {
			if (token.isEmpty()) continue
			secretLineParts.add(token)
		}
		tokens.add(secretLineParts)
	}
	for (line in tokens) {
		val instruction = if (line.isEmpty()) "" else line[0].lowercase()
		try {
			out.add(
				element = when (instruction) {


					"xlit" -> {
						InstructData(name = "xlit", values = arrayOf(line[1].toRegisterType(), line[2]))
					}


					"settype" -> {
						InstructData(
							name = "settype", values = arrayOf(line[1].toRegisterType(), line[2].toRegisterDataType())
						)
					}


					"help" -> {
						InstructData(
							name = "help", values = arrayOf(line[1])
						)
					}

					"call" -> {
						val outI: MutableList<Any> =
							line.subList(2, line.size).toList().map { it.toRegisterType()!! }.toMutableList()
						outI.add(0, line[1])

						InstructData(
							name = "call", values = outI.toTypedArray()
						)

					}


					// Register Register Register
					// What is substr?
					// I've never seen this.
					// Oh, Well
					"substr" -> { // Meant to leave in the string deprecation via new stdlib. But this can stay
						InstructData( // for reasons
							name = "substr", arrayOf(
								line[1].toRegisterType(), line[2].toRegisterType(), line[3].toRegisterType()
							)
						)
					}


					"str" -> {
						InstructData(
							name = "str", arrayOf(
								line[1].toRegisterType(), try {
									line.joinToString(" ").split("\"")[1]
								} catch (e: IndexOutOfBoundsException) {
									vm.errors.invalidArgumentFormatException(
										"Any", shouldBe = "String"
									)
								}

							)
						)
					}


					"syscall" -> {
						InstructData(
							name = "syscall", arrayOf(
								RegisterType.S1, RegisterType.S2, RegisterType.S3, RegisterType.S4
							)

						)
					}

					"" -> {
						InstructData(
							name = "emptyLine", arrayOf()
						)
					}

					"//" -> {
						InstructData(
							name = "comment", arrayOf()
						)
					}

					// Register Register Register
					"mod", "add", "sub", "mul", "div", "shl", "shr", "and", "or", "xor", "pow", "xadd", "xsub", "xmul",
					"xdiv", "xpow",
						-> {
						InstructData(
							name = instruction,
							arrayOf(line[1].toRegisterType(), line[2].toRegisterType(), line[3].toRegisterType())

						)
					}

					// Register Register
					"eq", "mov", "swp", "lt", "gt", "itof", "ftoi", "not",
						-> {
						InstructData(
							name = instruction, arrayOf(line[1].toRegisterType(), line[2].toRegisterType())

						)
					}


					// None
					"prints", "ret" -> {
						InstructData(
							name = "prints", emptyArray()
						)
					}


					// Register
					"printr", "peek", "pop", "push", "inr", "sleep" -> {
						InstructData(
							name = instruction, arrayOf(line[1].toRegisterType())
						)
					}

					"store", "load" -> {
						InstructData(
							name = instruction, arrayOf(line[1].toRegisterType(), line[2].toRegisterType())
						)
					}

					"lit" -> {
						InstructData(
							name = "lit", arrayOf(line[1].toRegisterType(), line[2].toLong())
						)
					}

					// Long
					"jmp", "jz", "jnz", "pushl" -> {
						InstructData(
							name = instruction, arrayOf(line[1].toLong())
						)
					}

					else -> {
						vm.errors.invalidInstructionException(instruction)
						exitProcess(99) // for kotlin. Ughhhhhh
					}
				}
			)
		} catch (missingArgument: IndexOutOfBoundsException) {
			val missingIndex = missingArgument.message!!.split(" ")[1].toByte() - 1
			val info = vm.helpers.gatherHelp(instruction).arguments[missingIndex]
			vm.errors.invalidArgumentException(info = info)


		} catch (e: NumberFormatException) {
			try {
				val x = e.message!!.split(" ")[3].substring(1, e.message!!.split(" ").size - 1).toRegisterType()
				if (x == null) {
					vm.errors.invalidArgumentFormatException(badType = "Any", shouldBe = "Long")
				} else {
					vm.errors.invalidArgumentFormatException(badType = "Long", shouldBe = "Register")
				}
			} catch (_: IllegalStateException) {
				vm.errors.invalidArgumentFormatException(
					badType = "String", shouldBe = "Long"
				)
			}
		}
	}
	vm.pc = 0

	//	if (reflection.groupTrackedVmByVm()[vm]!!.file.extension != "lib") {
	out.add(InstructData("HALT", arrayOf())) //	}
	return out
}