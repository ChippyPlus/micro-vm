package environment

import environment.libEx.*
import java.io.File


class ExecuteLib {
	var currentFunction = ""
	var enabledFunction = false

	fun execute(name: String) {
		if (findMarLib(name) != null) {
			val file = File(findMarLib(name)!!)
			enabledFunction = true
			currentFunction = File(findMarLib(name)!!).name
			executeMar(file)
			enabledFunction = false
		} else {
			executeKt(name)
		}
	}
}

