import data.registers.RegisterType

val r4Outs = setOf("add", "sub", "mul", "div", "mod", "strlen", "strcmp", "strcat", "lt", "gt")
val r3Outs = setOf("and", "or", "not", "xor", "shr", "shl")

val funcR = arrayOf(
	RegisterType.F1, RegisterType.F2, RegisterType.F3, RegisterType.F4, RegisterType.F5, RegisterType.F6,
	RegisterType.F7, RegisterType.F8, RegisterType.F9, RegisterType.F10
)