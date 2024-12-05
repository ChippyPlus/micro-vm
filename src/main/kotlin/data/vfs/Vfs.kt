package data.vfs

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromHexString
import kotlinx.serialization.encodeToByteArray
import kotlinx.serialization.protobuf.ProtoBuf
import java.io.File
import kotlin.system.exitProcess


@OptIn(ExperimentalStdlibApi::class, ExperimentalSerializationApi::class) //TODO. Turn into syscalls
class Vfs {

	init {
		if (!File("src/main/resources/vfs.fs").exists()) {
			File("src/main/resources/vfs.fs").createNewFile()
			this.flash(listOf(Ventry("/", "", Permissions(directory = true))))

		}
	}

	fun error(message: String): Nothing {
		System.err.println(message)
		exitProcess(1)
	}

	private fun mk(p: Ventry) {
		if (p.path.endsWith('/')) {
			p.permissions.directory = true
			p.path = p.path.removeSuffix("/")
		}
		val ren = list()
		val pathPartsUnclear = if (p.path.startsWith('/')) {
			p.path.split('/').subList(1, p.path.split('/').size).toMutableList()
		} else p.path.split('/').toMutableList()

		val pathParts = pathPartsUnclear.filter { it.isNotBlank() }

		println(pathParts)
		var memoized = ""

		for (i in pathParts.withIndex()) {
			memoized += "/${pathParts[i.index]}"
			println(memoized)

			if (i.index == pathParts.size - 1) {
				if (memoized in ren.map(Ventry::path)) {
					error("Ventry already exists")
				} else {
					flash(ren + p)
					return
				}
			}
			if (memoized !in ren.map(Ventry::path)) {
				error("Parent directory not found")
			}
		}
	}

	private fun rm(p: Ventry) {
		val ren = list()
		if (p !in ren) {
			error("Ventry not found")
		}
		flash(ren - p)
	}

	fun read(path: String): String {
		val rend = list()
		for (ventry in rend) {
			if (ventry.path == path) {
				return ventry.content
			}
		}
		error("Ventry Not found")
	}

	fun write(path: String, content: String) {
		val rend = list()
		for (ventry in rend.withIndex()) {
			if (ventry.value.path == path) {
				ventry.value.content = content
				println(ventry.value === rend[ventry.index])
			}
		}
		println(rend)
	}

	fun flash(ventries: List<Ventry>) =
		File("src/main/resources/vfs.fs").writeText(ProtoBuf.encodeToByteArray(value = ventries).toHexString())

	fun list(): List<Ventry> =
		ProtoBuf.decodeFromHexString<List<Ventry>>(hex = File("src/main/resources/vfs.fs").readText())

	@Suppress("unused")
	fun mkFile(path: String) { // // TODO. Implement
		mk(Ventry(path = path, content = "", permissions = Permissions(directory = false, write = true, read = true)))
	}

	@Suppress("unused")
	fun rmFile(path: String) { // TODO. Implement
		val l = list().map { Pair(it.path, it) }
		if (path !in l.map { it.first }) {
			error("Ventry not found")
		}

		val x = list().groupBy(Ventry::path).map { it.key to it.value.first() }.toMap()

		rm(p = l.first { borrow ->
			return@first borrow.first == path
		}.second)
	}

}


fun main() {
	val v = Vfs()
	v.list().forEach(::println)
}

