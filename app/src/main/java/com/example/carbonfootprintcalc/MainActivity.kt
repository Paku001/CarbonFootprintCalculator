package com.example.carbonfootprintcalc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.carbonfootprintcalc.ui.theme.CarbonFootprintCalcTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CarbonFootprintCalcTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    var electricityInput by remember { mutableStateOf("") }
    val electricity = electricityInput.toDoubleOrNull()?: 0.0

    Column(
        modifier = Modifier.padding(34.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Carbon Footprint Calculator",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        EntryInput(
            value = electricityInput,
            onvaluechange = {electricityInput = it},
            label = "Electricity consumption in Kw/H"
        )
    }

}

@Composable
fun EntryInput(
    value: String,
    onvaluechange: (String) -> Unit,
    label: String,
) {
    TextField(
        value = value,
        onValueChange = onvaluechange,
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        label = { Text(text = label)},
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)


    )

}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    CarbonFootprintCalcTheme {
        MainScreen()
    }
}