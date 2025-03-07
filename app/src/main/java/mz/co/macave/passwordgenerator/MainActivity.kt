package mz.co.macave.passwordgenerator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import mz.co.macave.passwordgenerator.ui.theme.PasswordGeneratorTheme
import mz.co.macave.passwordgenerator.viewmodel.MainActivityViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PasswordGeneratorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    Column(modifier = Modifier.padding(innerPadding)) {

                        val viewModel = MainActivityViewModel()

                        TextInput(viewModel)
                        PasswordLengthRange(viewModel)
                        OptionsToInclude(viewModel)
                        GenerateButton {
                            viewModel.updatePasswordValue {
                                //If no include option is selected
                            }
                        }
                    }

                }
            }
        }
    }
}