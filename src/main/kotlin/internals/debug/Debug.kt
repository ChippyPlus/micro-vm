package internals.debug

import kernel.process.KProcess


class Debug(val kp: KProcess) {
	val dm = if (!kp.isKlib) DebugMemorySnapShots() else null
	val di = if (!kp.isKlib) DebugInstructionBuffer() else null
	val dr = if (!kp.isKlib) DebugFullSnapShots() else null


	fun act() {
		dm?.act(kp)
		di?.act(kp)
		dr?.act(kp)
	}
}
