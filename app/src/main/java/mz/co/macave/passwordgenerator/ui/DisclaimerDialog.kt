package mz.co.macave.passwordgenerator.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import mz.co.macave.passwordgenerator.R

@Composable
fun DisclaimerDialog(onDismissRequest: ()-> Unit) {
    Dialog(onDismissRequest = {   }) {
        Card(
            modifier = Modifier
                .padding(24.dp)
                .wrapContentHeight()
                .fillMaxWidth(),
            shape = AlertDialogDefaults.shape,
            colors = CardDefaults.cardColors(),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp)
        ) {
            Column {
                DisclaimerTitle()
                HorizontalDivider()
                Text(
                    modifier = Modifier
                        .padding(24.dp)
                        .fillMaxWidth(),
                    text = stringResource(id = R.string.disclaimer_content)
                )
                HorizontalDivider()
                BottomButton(onClick = onDismissRequest)
            }
        }
    }
}

@Composable
fun DisclaimerTitle() {
    Row(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = Icons.Default.Info, contentDescription = null)
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = stringResource(id = R.string.disclaimer_title),
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Composable
fun BottomButton(onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .padding(horizontal = 24.dp, vertical = 16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextButton(onClick = onClick) {
            Text(text = stringResource(id = R.string.got_it))
        }
    }
}

@Preview
@Composable
fun DisclaimerPreview() {
    DisclaimerDialog {

    }
}