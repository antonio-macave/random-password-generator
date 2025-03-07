package mz.co.macave.passwordgenerator

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import mz.co.macave.passwordgenerator.viewmodel.MainActivityViewModel

@Composable
fun TextInput(viewModel: MainActivityViewModel) {

    val text by viewModel.password.collectAsStateWithLifecycle()

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {

        TextField(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth()
                .weight(0.85f),
            value = text,
            readOnly = true,
            onValueChange = {
                viewModel.updatePasswordValue {
                    //If no include option is selected
                }
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
fun PasswordLengthRange(viewModel: MainActivityViewModel) {
    val currentValue by viewModel.sliderValue.collectAsStateWithLifecycle()
    Column {
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = "Quantidade de caracteres"
        )
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
                    viewModel.updateSliderValue(value)
                },
                valueRange = 10f..100f,
            )

            Text(
                modifier = Modifier.weight(0.15f),
                text = currentValue.toInt().toString(),
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}


@Composable
fun OptionsToInclude(viewModel: MainActivityViewModel) {

    val includeCapitalLetters by viewModel.includeCapitalLetters.collectAsState()
    val includeNonCapitalLetters by viewModel.includeNonCapitalLetters.collectAsStateWithLifecycle()
    val includeNumbers by viewModel.includeNumbers.collectAsStateWithLifecycle()
    val includeSpecialCharacters by viewModel.includeSpecialChars.collectAsStateWithLifecycle()


    CheckBoxOption(text = stringResource(id = R.string.include_capital_letters), checked = includeCapitalLetters) {
        viewModel.updateIncludeCapitalLetterValue(!includeCapitalLetters)
    }

    CheckBoxOption(text = stringResource(id = R.string.include_noncapital_letters), checked = includeNonCapitalLetters) {
        viewModel.updateIncludeNonCapitalLettersValue(!includeNonCapitalLetters)
    }

    CheckBoxOption(text = stringResource(id = R.string.include_numbers), checked = includeNumbers) {
        viewModel.updateIncludeNumbersValue(!includeNumbers)
    }

    CheckBoxOption(text = stringResource(id = R.string.include_special_chars), checked = includeSpecialCharacters) {
        viewModel.updateIncludeSpecialCharsValue(!includeSpecialCharacters)
    }
}


@Composable
fun GenerateButton(onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Button(onClick = { onClick() }) {
            Icon(painter = painterResource(id = R.drawable.ic_generate_24), contentDescription = stringResource(
                id = R.string.generate_password
            ))
            Text(text = stringResource(id = R.string.generate_password))
        }
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
        Checkbox(
            modifier = Modifier.padding(start = 16.dp),
            checked = checked,
            onCheckedChange = { onCheckedChanged() }
        )
        Text(text = text)
    }
}

@Preview
@Composable
fun Previews() {
    Column {
        //TextInput()
        //PasswordLengthRange()
       // OptionsToInclude()
        GenerateButton {

        }
    }
}