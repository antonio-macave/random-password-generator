package mz.co.macave.passwordgenerator.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.random.Random

class MainActivityViewModel : ViewModel() {


    private val _generatedPassword: MutableStateFlow<String> = MutableStateFlow("")
    val password: StateFlow<String> get() = _generatedPassword.asStateFlow()

    private val _sliderValue: MutableStateFlow<Int> = MutableStateFlow(0)
    val sliderValue: StateFlow<Int> get() = _sliderValue.asStateFlow()

    private val _includeCapitalLetter: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val includeCapitalLetters: StateFlow<Boolean> get() = _includeCapitalLetter.asStateFlow()

    private val _includeNonCapitalLetter: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val includeNonCapitalLetters: StateFlow<Boolean> get() = _includeNonCapitalLetter.asStateFlow()

    private val _includeNumbers: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val includeNumbers: StateFlow<Boolean> get() = _includeNumbers.asStateFlow()

    private val _includeSpecialChars: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val includeSpecialChars: StateFlow<Boolean> get() = _includeSpecialChars.asStateFlow()


    private fun getDefinedChars(
        includeCapitalLetters: Boolean,
        includeNonCapitalLetters: Boolean,
        includeNumbers: Boolean,
        includeSpecialCharacters: Boolean
    ) : String {

        val chars = StringBuilder()

        if (includeCapitalLetters) {
            chars.append("ABCDEFGHIJKLMNOPQRSTUVWXYZ")
        }

        if (includeNonCapitalLetters) {
            chars.append("abcdefghijklmnopqrstuvwxyz")
        }

        if (includeNumbers) {
            chars.append("1234567890")
        }

        if (includeSpecialCharacters) {
            chars.append("!$%&#")
        }
        return chars.toString()
    }

    private fun getComputedPassword(charsQuantity: Int, charsList: String) : String {
        val password = StringBuilder()
        for (i in 1..charsQuantity) {
            password.append(charsList[Random.nextInt(charsList.length)])
        }
        return password.toString()
    }
}