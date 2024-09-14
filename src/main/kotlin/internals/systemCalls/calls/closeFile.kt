package org.example.kvmInternals.systemCalls.calls

import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.errors
import org.example.fileDescriptors
import org.example.helpers.fullRegisterRead
import org.example.kvmInternals.systemCalls.SystemCall

fun SystemCall.closeFile(s2: SuperRegisterType) {
    val fd = fullRegisterRead(s2)
    if (fileDescriptors.fds.remove(fd) == null) {
        errors.InvalidFileDescriptorException(fd.toString())
    }
}