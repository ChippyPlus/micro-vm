# MVM Standard Library Table

This table lists the functions currently implemented in the MVM standard library. All functions use the stack for
arguments and return values.

| Module       | Function Name     | Description                                                         | Argument 1 (F1)                  | Argument 2 (F2)                      | Argument 3 (F3) | Return Value(s) (Stack)                      | Notes                                                                        |
|--------------|-------------------|---------------------------------------------------------------------|----------------------------------|--------------------------------------|-----------------|----------------------------------------------|------------------------------------------------------------------------------|
| `strings`    | `strlen`          | Returns the length of a string.                                     | String Address (Memory Address)  | -                                    | -               | Length (Long)                                | Excludes the null terminator.                                                |
|              | `strcat`          | Concatenates two strings.                                           | String1 Address (Memory Address) | String2 Address (Memory Address)     | -               | Concatenated String Address (Memory Address) | Allocates memory for the concatenated string.                                |
|              | `strcpy`          | Copies a string from one location to another.                       | Source Address (Memory Address)  | Destination Address (Memory Address) | -               | Destination Address (Memory Address)         | Allocates memory for the copied string. The source string remains unchanged. |
|              | `strcmp`          | Compares two strings lexicographically.                             | String1 Address (Memory Address) | String2 Address (Memory Address)     | -               | Result (0 or 1)                              | Returns 1 if strings are equal; 0 otherwise.                                 |
|              | `asciiToInt`      | Converts an ASCII character to its integer representation.          | Character Code (Long)            | -                                    | -               | Integer Value (Long)                         | Returns -1 if the input is not a valid ASCII character.                      |
|              | `findFirstString` | Finds the index of the first occurrence of a character in a string. | String Address (Memory Address)  | Character Code (Long)                | -               | Index (Long)                                 | Returns -1 if the character is not found.                                    |
| `arrays`     | `create`          | Creates a new array.                                                | Size (Long)                      | Data Type (RegisterDataType code)    | -               | Array Base Address (Long)                    | Data Type code: 0=Byte, 1=Short, 2=Int, 3=Long, 4=Float, 5=Double.           |
|              | `get`             | Gets an element from an array.                                      | Array Address (Long)             | Index (Long)                         | -               | Element Value (Long)                         | Performs bounds checking.                                                    |
|              | `size`            | Returns the size of an array                                        | Array Address (Long)             | Index (Long)                         | Value (Long)    | -                                            | Performs bounds checking.                                                    |
|              | `append`          | Adds a new element to the end of an array                           | Array Address (Long)             | -                                    | -               | Length (Long)                                | -                                                                            |
| `math`       | `sqrt`            | Calculates the square root of a number.                             | Number (Long)                    | -                                    | -               | Result (Long)                                | Returns 0 if the input is negative.                                          |
|              | `pow`             | Raises a number to a power.                                         | Base (Long)                      | Exponent (Long)                      | -               | Result (Long)                                | Returns 0 on error.                                                          |
|              | `min`             | Returns the minimum of two values.                                  | Value 1 (Long)                   | Value 2 (Long)                       | -               | Minimum Value (Long)                         | -                                                                            |
|              | `max`             | Returns the maximum of two values.                                  | Value 1 (Long)                   | Value 2 (Long)                       | -               | Maximum Value (Long)                         | -                                                                            |
|              | `inc`             | Increments a value by 1.                                            | Value (Long)                     | -                                    | -               | Incremented Value (Long)                     | -                                                                            |
|              | `dec`             | Decrements a value by 1.                                            | Value (Long)                     | -                                    | -               | Decremented Value (Long)                     | -                                                                            |
|              | `neg`             | Negates a value.                                                    | Value (Long)                     | -                                    | -               | Negated Value (Long)                         | -                                                                            |
|              | `sq`              | Squares a value.                                                    | Value (Long)                     | -                                    | -               | Squared Value (Long)                         | -                                                                            |
| `io`         | `println`         | Prints a string to the console with a newline.                      | String Address (Memory Address)  | -                                    | -               | -                                            | The string must be null-terminated.                                          |
|              | `readln`          | Reads a line from standard input.                                   | -                                | -                                    | -               | String Address (Memory Address)              | Allocates memory for the string.                                             |
| `conversion` | `asciiToInt`      | Converts an ASCII character to an integer.                          | Character Code (Long)            | -                                    | -               | Integer Value (Long)                         | Returns -1 if the input is not a valid ASCII character.                      |
|              | `strtoint`        | Converts a string to a Long.                                        | String Address (Memory Address)  | -                                    | -               | Integer Value (Long)                         | Returns 0 on error.                                                          |
|              | `findFirstString` | Finds the index of the first occurrence of a character in a string. | String Address (Memory Address)  | Character Code (Long)                | -               | Index (Long)                                 | Returns -1 if the character is not found.                                    |
