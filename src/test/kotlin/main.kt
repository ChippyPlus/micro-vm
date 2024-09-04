package org.example.dog

class FixedSizeStack(private val maxSize: Int) {
    private val stack = Array<Int?>(maxSize) { null }
    private var topIndex = -1

    fun push(element: Int) {
        if (isFull()) {
            throw StackOverflowError("Stack is full")
        }
        topIndex++
        stack[topIndex] = element
    }

    fun pop(): Int? {
        if (isEmpty()) {
            return null
        }
        val element = stack[topIndex]
        stack[topIndex] = null
        topIndex--
        return element
    }

    fun peek(): Int? {
        return if (isEmpty()) null else stack[topIndex]
    }

    fun isEmpty(): Boolean {
        return topIndex == -1
    }

    fun isFull(): Boolean {
        return topIndex == maxSize - 1
    }
}

fun main() {
    val x = FixedSizeStack(2)

}