package mz.co.macave.passwordgenerator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.launch
import mz.co.macave.passwordgenerator.ui.DisclaimerDialog
import mz.co.macave.passwordgenerator.ui.GenerateButton
import mz.co.macave.passwordgenerator.ui.OptionsToInclude
import mz.co.macave.passwordgenerator.ui.PasswordLengthRange
import mz.co.macave.passwordgenerator.ui.TextInput
import mz.co.macave.passwordgenerator.ui.theme.PasswordGeneratorTheme
import mz.co.macave.passwordgenerator.viewmodel.MainActivityViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PasswordGeneratorTheme {

                val viewModel = MainActivityViewModel()
                val snackbarHostState = remember { SnackbarHostState() }
                val scope = rememberCoroutineScope()
                val showDisclaimerDialog by viewModel.showDisclaimerDialog.collectAsStateWithLifecycle()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = { TopAppBar(viewModel) },
                    snackbarHost = { SnackHost(host = snackbarHostState) }
                ) { innerPadding ->

                    Column(modifier = Modifier.padding(innerPadding)) {



                        TextInput(viewModel)
                        PasswordLengthRange(viewModel)
                        OptionsToInclude(viewModel)
                        GenerateButton {
                            viewModel.updatePasswordValue {
                                //If no include option is selected
                                scope.launch {
                                    val textToShow = getString(R.string.please_select_an_option)
                                    snackbarHostState.showSnackbar(textToShow)
                                }
                            }
                        }
                    }


                    if (showDisclaimerDialog) {
                        DisclaimerDialog {

                        }
                    }

                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(viewModel: MainActivityViewModel) {
    val showDisclaimerDialog by viewModel.showDisclaimerDialog.collectAsStateWithLifecycle()
    MediumTopAppBar(
        title = { Text(text = stringResource(id = R.string.app_name)) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        actions = {
            IconButton(
                onClick = {
                    viewModel.updateShowDisclaimerDialogValue(!showDisclaimerDialog)
                }
            ) {
                Icon(imageVector = Icons.Default.Info, contentDescription = null)
            }
        }
    )
}


@Composable
fun SnackHost(host: SnackbarHostState) {
    SnackbarHost(hostState = host)
}