package environment.errorsCatchable

@Suppress("unused")
enum class ErrorType(val code: Long) { // TODO. Need to add working Error handling
	FILE_NOT_FOUND(0), FILE_ALREADY_EXISTS(1), LONG_OVERFLOW(2), LONG_UNDERFLOW(3), DIVIDE_BY_0(4), STACK_OVERFLOW(5),
	STACK_UNDERFLOW(6), RANDOM_GREATER_THAN_ORIGIN(7)
}