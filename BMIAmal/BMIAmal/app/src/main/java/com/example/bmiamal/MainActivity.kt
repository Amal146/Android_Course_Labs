package com.example.bmiamal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.IntRange
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bmiamal.ui.theme.BMIAmalTheme
import java.text.NumberFormat
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BMIAmalTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BMI()
                }
            }
        }
    }
}

@Composable
fun BMI(modifier: Modifier = Modifier) {
    var roundUp by remember { mutableStateOf(false) }
    var heightInput by remember { mutableStateOf("0") }
    var weightInput by remember { mutableStateOf("0") }
    var height = heightInput.toDoubleOrNull() ?: 0.0
    var weight = weightInput.toDoubleOrNull() ?: 0.0
    var BMICalculated = calculateBMI(height , weight)
    var Classification = BmiClass(BMICalculated)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(horizontal = 40.dp)
            .verticalScroll(rememberScrollState())
            .safeDrawingPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.app_title),
            modifier = Modifier
                .padding(bottom = 16.dp, top = 40.dp)
                .align(alignment = Alignment.Start),
            color = Color.Magenta,
            fontStyle = FontStyle.Italic,
            fontSize = 35.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(150.dp))
        EditNumberField(
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth(),
            label = R.string.heightLabel,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            value = weightInput,
            onValueChange = { weightInput = it }
        )
        EditNumberField(
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth(),
            label = R.string.weightLabel,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            value = heightInput,
            onValueChange = { heightInput = it }
        )
        Spacer(modifier = Modifier.height(150.dp))
        Text(
            text = stringResource(R.string.BMI) + " : " + BMICalculated.toString()
        )
        Text(
            text = stringResource(R.string.BMIclass) + " : " + stringResource(Classification)
        )
    }

    }



private fun calculateBMI(weight: Double, height: Double ): Double {
    var BMI = weight / ( height  *  height )
    BMI = Math.ceil(BMI * 10 ) / 10
    return BMI
}

private fun BmiClass(bmi: Double)  =
    when (bmi) {
        in 0.0..18.5 -> R.string.underweight
        in 18.5..24.9 -> R.string.normalweight
        in 25.0..29.9 -> R.string.overweight
        else -> R.string.obese
    }


@Preview(showBackground = true , showSystemUi = true , device = Devices.PIXEL_3A )
@Composable
fun BMIPreview() {
    BMIAmalTheme {
        BMI()
    }
}

@Composable
fun EditNumberField(modifier: Modifier = Modifier,
                    @StringRes label: Int,
                    keyboardOptions: KeyboardOptions,
                    value: String ,
                    onValueChange: (String) -> Unit )
{
    TextField(
        label = { Text ( text = stringResource(label))} ,
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number , imeAction = ImeAction.Next)
    )
}