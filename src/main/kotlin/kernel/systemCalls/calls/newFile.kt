package kernel.systemCalls.calls

import data.registers.RegisterType
import kernel.systemCalls.SystemCall

fun SystemCall.newFile(name: RegisterType) = call("newFile") { //vm.vfs.new(helpers.readRegisterString(name)) ?: errors.FileAlreadyExistsException(helpers.readRegisterString(name))
	TODO("Bring back VFS")

}