package org.example.internals.encoding

import kotlinx.serialization.Serializable

@Serializable
data class EachInstruction(val pc: String, val instructionType: String,  val dataOutput: Map<String, Int?>)