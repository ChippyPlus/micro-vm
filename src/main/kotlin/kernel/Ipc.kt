@file:Suppress("UnnecessaryVariable")

package kernel

import internals.Vm
import kernel.process.KProcess
import java.io.File
import java.util.*
import kotlin.system.exitProcess


/**
 * # Goals.
 * ## Syscalls
 * - Receive,
 * - Send,
 * - Listen,
 */


class Ipc {
	val messagePassing = MessagePassing()

	class MessagePassing {
		val pending = mutableMapOf<KProcess, MutableSet<KProcess>>()  // requester,acceptent"S"
		var idCount = 0
		val mailBoxes = mutableMapOf<Int, Stack<Long>>()

		fun preposeLink(requestant: KProcess, acceptent: KProcess): Int? {
			if (requestant !in pending) {
				pending[requestant] = mutableSetOf()
			}
			pending[requestant]!!.add(acceptent)
			if (pending[acceptent]?.contains(requestant) == true) {
				idCount++
				mailBoxes[idCount] = Stack()
				requestant.ipcPermissions.add(idCount)
				acceptent.ipcPermissions.add(idCount)

				return idCount
			} else {
				return null
			}
		}

		fun send(host: KProcess, id: Int, message: Long): Boolean {
			if (id !in host.ipcPermissions) return false

			mailBoxes[id]!!.push(message)
			return true
		}


		fun receive(host: KProcess, id: Int): Long? {
			if (id !in host.ipcPermissions) return null
			return mailBoxes[id]!!.pop()
		}

	}


}


fun main() { // TODO add a way to lookup PIDS cause so far processes do not know how to see other ones
	val p1 = KProcess(Vm(), File(""))
	val p2 = KProcess(Vm(), File(""))
	val p3 = KProcess(Vm(), File(""))
	val ipc = Ipc.MessagePassing()

	val requestant = p1
	val acceptent = p2

	ipc.preposeLink(requestant, acceptent)
	val o = ipc.preposeLink(acceptent, requestant)
	println("[Link] [${requestant.id},${acceptent.id}]: ${if (o != null) " success " else " failed"} ")
	if (o == null) exitProcess(1)

	println("[Send] [${requestant.id},${acceptent.id}]: ${if (ipc.send(p1, o, 24)) " success " else " failed"} ")
	val x = ipc.receive(p1, o)
	println("[Receive] [${requestant.id},${acceptent.id}]: ${if (x == null) "failed" else "$x"}")

}