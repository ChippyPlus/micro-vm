package org.example.kvmInternals.instructions.memory

import org.example.data.memory.MemoryAddress
import org.example.data.memory.MemoryValue
import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.helpers.fullRegisterRead
import org.example.internalMemory
import org.example.kvmInternals.instructions.controlFlow.ControlFlow

/**
 * Stores the value from the specified [source] register into the internal memory
 * at the given [destination] address.
 *
 * @param source The register type containing the value to be stored in memory.
 * @param destination The address in memory where the value should be stored.
 */
fun ControlFlow.store(source: SuperRegisterType, destination: MemoryAddress) {
    internalMemory.write(destination, MemoryValue(fullRegisterRead(source)))
}