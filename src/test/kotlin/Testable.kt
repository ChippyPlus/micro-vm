import DataType.A
import DataType.S

enum class DataType {
	A, S, D, F
}

class Sm {
	fun snapshot() {}

	fun refill() {}
}


class Data {
	private val holders = mutableMapOf<DataType, Byte>()

	init {
		for (i in DataType.entries) {
			holders[i] = 0
		}
	}

	operator fun set(a: DataType, value: Byte) = run { holders[a] = value }
	operator fun get(a: DataType) = holders[a]!!
}

val data = Data()
val sm = Sm()
fun runExternal() {
	data[A] = 2
	data[S] = 22

}


fun main() {
	data[A] = 15
	sm
	runExternal()
	println(data[A])
}
