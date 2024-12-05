package internals.debug

import kernel.process.KProcess


class Debug(val kp: KProcess) {
	val dm = DebugMemorySnapShots()
	val di = DebugInstructionBuffer()
	val dr = DebugFullSnapShots()

	fun act() {
		dm.act(kp)
		di.act(kp)
		dr.act(kp)
	}
}
