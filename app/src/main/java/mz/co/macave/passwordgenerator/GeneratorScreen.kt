package mz.co.macave.passwordgenerator

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TextInput() {

    var text by remember { mutableStateOf("") }

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {

        TextField(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .weight(0.85f),
            value = text,
            readOnly = true,
            onValueChange = {word ->
                text = word
            }
        )
        
        IconButton(
            modifier = Modifier
                .weight(0.15f),
            onClick = {

            }
        ) {
            Icon(
                modifier = Modifier
                    .size(32.dp),
                painter = painterResource(id = R.drawable.ic_content_copy),
                contentDescription = stringResource(
                    id = android.R.string.copy)
                )
        }
    }
}


@Composable
fun PasswordLengthRange() {
    var currentValue by remember { mutableFloatStateOf(10f) }
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Slider(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth()
                .weight(0.85f),
            value = currentValue,
            onValueChange = { value ->
                currentValue = value
            },
            valueRange = 10f..100f,
        )
        
        Text(
            modifier = Modifier.weight(0.15f),
            text = currentValue.toInt().toString()
        )
    }
}


@Composable
fun OptionsToInclude() {

    var includeCapitalLetters by remember { mutableStateOf(false) }
    var includeNonCapitalLetters by remember { mutableStateOf(false) }
    var includeNumbers by remember { mutableStateOf(false) }
    var includeSpecialCharacters by remember { mutableStateOf(false) }


    CheckBoxOption(text = stringResource(id = R.string.include_capital_letters), checked = includeCapitalLetters) {
        includeCapitalLetters = !includeCapitalLetters
    }

    CheckBoxOption(text = stringResource(id = R.string.include_noncapital_letters), checked = includeNonCapitalLetters) {
        includeNonCapitalLetters = !includeNonCapitalLetters
    }

    CheckBoxOption(text = stringResource(id = R.string.include_numbers), checked = includeNumbers) {
        includeNumbers = !includeNumbers
    }

    CheckBoxOption(text = stringResource(id = R.string.include_special_chars), checked = includeSpecialCharacters) {
        includeSpecialCharacters = !includeSpecialCharacters
    }
}

@Composable
fun CheckBoxOption(text: String, checked: Boolean, onCheckedChanged: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onCheckedChanged() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(checked = checked, onCheckedChange = { onCheckedChanged() } )
        Text(text = text)
    }
}

@Preview
@Composable
fun Previews() {
    Column {
        TextInput()
        PasswordLengthRange()
        OptionsToInclude()
    }
}