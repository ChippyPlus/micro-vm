package kernel.process

import helpers.RuntimeStates

data class KThread(val id: UInt, val state: RuntimeStates)