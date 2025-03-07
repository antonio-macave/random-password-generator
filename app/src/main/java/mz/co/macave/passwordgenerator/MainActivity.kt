package mz.co.macave.passwordgenerator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch
import mz.co.macave.passwordgenerator.ui.theme.PasswordGeneratorTheme
import mz.co.macave.passwordgenerator.viewmodel.MainActivityViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PasswordGeneratorTheme {

                val snackbarHostState = remember { SnackbarHostState() }
                val scope = rememberCoroutineScope()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    snackbarHost = {
                        SnackHost(host = snackbarHostState)
                    }
                ) { innerPadding ->

                    Column(modifier = Modifier.padding(innerPadding)) {

                        val viewModel = MainActivityViewModel()

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

                }
            }
        }
    }
}

@Composable
fun SnackHost(host: SnackbarHostState) {
    SnackbarHost(hostState = host)
}