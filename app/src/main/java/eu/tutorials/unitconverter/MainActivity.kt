package eu.tutorials.unitconverter

import android.graphics.drawable.Icon
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import eu.tutorials.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {


                    UnitConverter()
                }
            }
        }
    }
}
@Composable
     fun UnitConverter() {
     var inputValue by remember { mutableStateOf("") }
     var outputValue by remember { mutableStateOf("") }
     var inputUnit by remember { mutableStateOf("Meters") }
     var outputUnit by remember { mutableStateOf("Meters") }
     var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    var conversionFactor = remember { mutableStateOf(1.00) }
    var oConversionFactor = remember { mutableStateOf(1.00) }
fun convertUnit(){
    // ?: elvish operator ---if value is not right then take 00
    val inputValuDouble=inputValue.toDoubleOrNull() ?: 0.0
    val result =(inputValuDouble * conversionFactor.value * 100.0/oConversionFactor.value).roundToInt()/100.0
    outputValue=result.toString()
}


    Column  (//  here UI element stacked below each other
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {





        Text(text = "Unit Converter" , style = MaterialTheme.typography.headlineMedium)



        Spacer(modifier=Modifier.heightIn(16.dp))  //modifier = Modifier.padding(16.dp)



        OutlinedTextField(
            value = inputValue,
            onValueChange = {
            inputValue=it


                convertUnit()

            // Here what should happen when value of outline text field change} )

        },
            label = { Text("Enter Value")})




        Spacer(modifier=Modifier.heightIn(16.dp))  // hight space




        Row {
               // input box
            Box {
               // input button
                Button(onClick = { iExpanded = true}) {
                    Text(text = inputUnit)
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Down"
                                )


                }

                DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded=false }) {
                    DropdownMenuItem(
                        text = { Text(text = "Centimeters") },
                        onClick = { iExpanded=false
                            inputUnit = "Centimeters"
                        conversionFactor.value=0.01
                            convertUnit()
                        })
                    DropdownMenuItem(
                        text = { Text(text = "Meter") },
                        onClick = {iExpanded=false
                            inputUnit = "Meter"
                            conversionFactor.value=1.0
                            convertUnit()

                        })

                    DropdownMenuItem(
                        text = { Text(text = "Feet") },
                        onClick = { iExpanded=false
                            inputUnit = "Feet"
                            conversionFactor.value=0.3048
                            convertUnit()
                        })
                    DropdownMenuItem(
                        text = { Text(text = "Milimeter") },
                        onClick = { iExpanded=false
                            inputUnit = "Milimeter"
                            conversionFactor.value=0.001
                            convertUnit()})
            }



}



            Spacer(modifier=Modifier.width(16.dp))  // width space

             // output box
            Box {
               // output button
                Button(onClick = { oExpanded=true }) {
                    Text(text = outputUnit)
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Down"



                    )

                }






                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded=false }) {
                    DropdownMenuItem(
                        text = { Text(text = "Centimeters") },
                        onClick = { oExpanded=false
                            outputUnit = "Centimeters"
                            oConversionFactor.value=0.01
                            convertUnit()
                        })
                    DropdownMenuItem(
                        text = { Text(text = "Meter") },
                        onClick = {  oExpanded=false
                            outputUnit = "Meter"
                            oConversionFactor.value=1.00
                            convertUnit()

                        })

                    DropdownMenuItem(
                        text = { Text(text = "Feet") },
                        onClick = {  oExpanded=false
                            outputUnit = "Feet"
                            oConversionFactor.value=0.3048
                            convertUnit()

                        })
                    DropdownMenuItem(
                        text = { Text(text = "Milimeter") },
                        onClick = {  oExpanded=false
                            outputUnit = "Milimeter"
                            oConversionFactor.value=.001
                            convertUnit()

                        })//  here UI element stacked next to each other


            }

        }




    }

        Spacer(modifier = Modifier.heightIn(16.dp))

        Text(text = "Result:$outputValue$outputUnit", style = MaterialTheme.typography.headlineLarge)
}
    Spacer(modifier = Modifier.heightIn(16.dp))

    Text(text = "This is changed version for trial by Ritesh", style = MaterialTheme.typography.headlineLarge)

}




    @Preview(showBackground = true)
    @Composable
    fun UnitConverterPreview() {
        UnitConverter()

    }








