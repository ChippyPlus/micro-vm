package os_package.systemCalls.calls

import data.registers.RegisterType
import helpers.readRegisterString
import os_package.systemCalls.SystemCall

fun SystemCall.newFile(name: RegisterType) = call("newFile") {
	vm.vfs.new(helpers.readRegisterString(name)) ?: errors.FileAlreadyExistsException(helpers.readRegisterString(name))
}