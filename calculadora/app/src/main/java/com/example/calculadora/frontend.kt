package com.example.calculadora.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.calculadora.Backend.handleButtonClick

@Composable
fun CalculatorApp() {
    var input by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }
    var operator by remember { mutableStateOf("") }
    var firstNumber by remember { mutableStateOf("") }

    fun updateState(
        newInput: String,
        newOperator: String,
        newFirstNumber: String,
        newResult: String
    ) {
        input = newInput
        operator = newOperator
        firstNumber = newFirstNumber
        result = newResult
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color(0xFFEDEDED)),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Display area for the input and result
        Text(
            text = if (result.isNotEmpty()) result else input,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(Color.White)
                .padding(8.dp)
                .align(Alignment.End)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Calculator buttons layout
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            CalculatorButtonRow(
                listOf("1", "2", "3", "/"),
                onClick = { button -> handleButtonClick(button, input, operator, firstNumber, result, ::updateState) }
            )
            CalculatorButtonRow(
                listOf("4", "5", "6", "*"),
                onClick = { button -> handleButtonClick(button, input, operator, firstNumber, result, ::updateState) }
            )
            CalculatorButtonRow(
                listOf("7", "8", "9", "-"),
                onClick = { button -> handleButtonClick(button, input, operator, firstNumber, result, ::updateState) }
            )
            CalculatorButtonRow(
                listOf("0", "C", "=", "+"),
                onClick = { button -> handleButtonClick(button, input, operator, firstNumber, result, ::updateState) }
            )
        }
    }
}

@Composable
fun CalculatorButtonRow(
    buttons: List<String>,
    onClick: (String) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        buttons.forEach { button ->
            CalculatorButton(
                label = button,
                modifier = Modifier.weight(1f),
                onClick = { onClick(button) }
            )
        }
    }
}

@Composable
fun CalculatorButton(
    label: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .aspectRatio(1f)
            .background(MaterialTheme.colorScheme.primary)
    ) {
        Text(text = label, style = MaterialTheme.typography.headlineMedium, color = Color.White)
    }
}
