package com.example.carbonfootprintcalc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.carbonfootprintcalc.ui.theme.CarbonFootprintCalcTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.NumberFormat

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
    var LPGInput by remember { mutableStateOf("") }
    var naturalgasInput by remember { mutableStateOf("")}
    var ElectricityINput by remember { mutableStateOf("")}
    var petrolInput by remember { mutableStateOf("")}
    var dieselIput by remember { mutableStateOf("")}

    val electricity = ElectricityINput.toDoubleOrNull()?: 0.0
    val cng = naturalgasInput.toDoubleOrNull()?: 0.0
    val lpg = LPGInput.toDoubleOrNull()?: 0.0
    val petrol = petrolInput.toDoubleOrNull()?: 0.0
    val diesel = dieselIput.toDoubleOrNull()?: 0.0

    val carbon = carbonCalculator(
        electricity = electricity,
        cng = cng,
        lpg = lpg,
        petrol = petrol,
        diesel = diesel
    )


    Column(
        modifier = Modifier.padding(34.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Card(
            elevation = 4.dp,
            backgroundColor = Color.Blue,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = "Carbon Footprint Calculator",
                modifier = Modifier
                    .padding(8.dp),
                fontSize = 21.sp,
                color = Color.Green,


            )
        }
        Spacer(modifier = Modifier.height(8.dp))

        EntryInput(
            value = LPGInput,
            onvaluechange = {LPGInput = it},
            label = "No of LPG Cylinder used"
        )
        EntryInput(
            value = naturalgasInput,
            onvaluechange = {naturalgasInput = it},
            label = "CNG used at home (m3)"
        )

        EntryInput(
            value = ElectricityINput,
            onvaluechange = {ElectricityINput = it},
            label = "Electricity used in a month (kWh)"
        )

        EntryInput(
            value = petrolInput,
            onvaluechange = {petrolInput = it},
            label = "Petrol consumed per month (litres) "
        )
        EntryInput(
            value = dieselIput,
            onvaluechange = {dieselIput = it},
            label = "Diesel consumed per month (litres) "
        )
        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = "Total carbon emissions per Month : $carbon kg",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

    }

}


@Composable
private fun carbonCalculator(
    electricity: Double ,
    cng: Double,
    lpg: Double,
    petrol: Double,
    diesel: Double,
): String{
    val carbon = (lpg*42.50) + (cng*1.82) + (electricity*0.90) + (petrol*2.33) + (diesel*2.68)
    return carbon.toString()
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