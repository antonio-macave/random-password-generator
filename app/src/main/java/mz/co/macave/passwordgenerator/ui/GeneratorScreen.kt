package mz.co.macave.passwordgenerator.ui

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.delay
import mz.co.macave.passwordgenerator.R
import mz.co.macave.passwordgenerator.viewmodel.MainActivityViewModel

@Composable
fun TextInput(viewModel: MainActivityViewModel, onCopyClick: ()-> Unit) {

    val text by viewModel.password.collectAsStateWithLifecycle()

    Row(
        verticalAlignment = Alignment.Bottom
    ) {

        TextField(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth()
                .weight(0.85f),
            minLines = 6,
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
                .weight(0.15f)
                .padding(end = 24.dp, bottom = 24.dp)
                .size(24.dp),
            enabled = text.isNotEmpty(),
            onClick = { onCopyClick() }
        ) {
            Icon(
                modifier = Modifier
                    .size(24.dp),
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
            modifier = Modifier.padding(horizontal = 24.dp),
            text = stringResource(id = R.string.characters_quantity),
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Slider(
                modifier = Modifier
                    .padding(
                        start = 24.dp,
                        end = 24.dp,
                        bottom = 24.dp,
                        top = 8.dp
                    )
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

    Text(
        modifier = Modifier.padding(
            start = 24.dp,
            bottom = 8.dp
        ),
        text = stringResource(id = R.string.include)
    )

    CheckBoxOption(text = stringResource(id = R.string.capital_letters), checked = includeCapitalLetters) {
        viewModel.updateIncludeCapitalLetterValue(!includeCapitalLetters)
    }

    CheckBoxOption(text = stringResource(id = R.string.lowercase_letters), checked = includeNonCapitalLetters) {
        viewModel.updateIncludeNonCapitalLettersValue(!includeNonCapitalLetters)
    }

    CheckBoxOption(text = stringResource(id = R.string.numbers), checked = includeNumbers) {
        viewModel.updateIncludeNumbersValue(!includeNumbers)
    }

    CheckBoxOption(text = stringResource(id = R.string.special_chars), checked = includeSpecialCharacters) {
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

        Button(
            onClick = {
                isPressed = true
                onClick()
            },
            Modifier.scale(scale)
        ) {
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
        val viewModel = MainActivityViewModel()
        TextInput(viewModel) { }
        PasswordLengthRange(viewModel)
        OptionsToInclude(viewModel)
    }
}