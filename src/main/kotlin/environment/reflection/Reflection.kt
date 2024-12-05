package environment.reflection

import internals.Vm
import kernel.process.KProcess


val reflection = Reflection()

class Reflection {
	val currentFileData = CurrentFileData()
	val vmTracker = mutableListOf<KProcess>()

	fun groupTrackedVmById(): Map<Int, KProcess> {
		val w = mutableMapOf<Int, KProcess>()
		println("Init = $vmTracker")
		for (i in vmTracker) {
			println("doing stuff with ${i.id}")
			w[i.id] = i
		}

		return w
	}

	fun groupTrackedVmByVm(): MutableMap<Vm, KProcess> {
		val x = vmTracker.groupBy(KProcess::vm)
		val y = mutableMapOf<Vm, KProcess>()
		for (i in x) {
			y[i.key] = i.value[0]
		}
		return y
	}


}