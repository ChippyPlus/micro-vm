package kilb

import data.registers.RegisterType
import helpers.readRegisterString
import internals.Vm
import kernel.process.KProcess

class Klib(val kp: KProcess) {
	val strings = Strings(kp)
	val arrays = Arrays(kp)
	fun match(fame: String): Boolean {
		kp.notifyOS()
		when (fame) {

			"strings.strcmp" -> strings.strcmp()
			"strings.strcat" -> strings.strcat()
			"strings.strcpy" -> strings.strcpy()
			"strings.strlen" -> strings.strlen()
			"io.println" -> println(kp.vm.helpers.readRegisterString(RegisterType.F1))


			"strings.cheekyfloat" -> cheekyFloat()
			"strings.cheekydouble" -> cheekyDouble()

			"arrays.size" -> arrays.size()
			"arrays.create" -> arrays.create()
			"arrays.set" -> arrays.add()
			"arrays.get" -> arrays.get()

			"randmax" -> randMax()
			"strtoint" -> strtoint()
			else -> return false
		}
		return true
	}


}

fun sreturn(vm: Vm, value: Long) {
	vm.stackOperations.internalStack.push(value)
}