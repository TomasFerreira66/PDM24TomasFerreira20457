package com.example.calculadora

object Backend {

    fun handleButtonClick(
        button: String,
        input: String,
        operator: String,
        firstNumber: String,
        result: String,
        updateState: (String, String, String, String) -> Unit
    ) {
        when (button) {
            in "0".."9" -> {
                // Reset the state if there is a result (indicating a previous calculation)
                if (result.isNotEmpty()) {
                    updateState(button, "", "", "")
                } else {
                    updateState(input + button, operator, firstNumber, result)
                }
            }

            in listOf("+", "-", "*", "/") -> {
                // If result exists, use it as firstNumber for the new operation
                if (result.isNotEmpty()) {
                    updateState("", button, result, "")
                } else if (input.isNotEmpty()) {
                    updateState("", button, input, result)
                }
            }

            "=" -> {
                if (operator == "/") {
                    val num1 = firstNumber.toDoubleOrNull()
                    val num2 = input.toDoubleOrNull()

                    if (num1 != null && num2 != null) {
                        val calculatedResult = if (num2 != 0.0) {
                            val divisionResult = num1 / num2
                            if (divisionResult == divisionResult.toInt().toDouble()) {
                                divisionResult.toInt().toString()  // Show as an integer
                            } else {
                                divisionResult.toString()  // Show as a double
                            }
                        } else {
                            "Error"
                        }
                        updateState("", "", "", calculatedResult)
                    }
                } else {
                    val num1 = firstNumber.toIntOrNull()
                    val num2 = input.toIntOrNull()

                    if (num1 != null && num2 != null) {
                        val calculatedResult = when (operator) {
                            "+" -> num1 + num2
                            "-" -> num1 - num2
                            "*" -> num1 * num2
                            else -> "Error"
                        }
                        updateState("", "", "", calculatedResult.toString())
                    }
                }
            }

            "C" -> {
                // Reset the state when "C" is clicked
                updateState("", "", "", "")
            }
        }
    }
}
