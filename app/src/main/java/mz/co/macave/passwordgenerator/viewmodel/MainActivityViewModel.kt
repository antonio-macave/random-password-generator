package mz.co.macave.passwordgenerator.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.random.Random

class MainActivityViewModel : ViewModel() {


    private val _generatedPassword: MutableStateFlow<String> = MutableStateFlow("")
    val password: StateFlow<String> get() = _generatedPassword.asStateFlow()

    private val _showDisclaimerDialog: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val showDisclaimerDialog: StateFlow<Boolean> get() = _showDisclaimerDialog.asStateFlow()

    private val _sliderValue: MutableStateFlow<Float> = MutableStateFlow(10f)
    val sliderValue: StateFlow<Float> get() = _sliderValue.asStateFlow()

    private val _includeCapitalLetter: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val includeCapitalLetters: StateFlow<Boolean> get() = _includeCapitalLetter.asStateFlow()

    private val _includeNonCapitalLetter: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val includeNonCapitalLetters: StateFlow<Boolean> get() = _includeNonCapitalLetter.asStateFlow()

    private val _includeNumbers: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val includeNumbers: StateFlow<Boolean> get() = _includeNumbers.asStateFlow()

    private val _includeSpecialChars: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val includeSpecialChars: StateFlow<Boolean> get() = _includeSpecialChars.asStateFlow()


    fun updatePasswordValue(noOptionSelected: () -> Unit) {
        val isAbleToGenerate = isAnyIncludeActive()
        if (isAbleToGenerate) {
            val charsToUse = getDefinedChars(
                includeCapitalLetters = _includeCapitalLetter.value,
                includeNonCapitalLetters = _includeNonCapitalLetter.value,
                includeNumbers = _includeNumbers.value,
                includeSpecialCharacters = _includeSpecialChars.value
            )

            val generatePassword = getComputedPassword(_sliderValue.value.toInt(), charsToUse)
            _generatedPassword.value = generatePassword
        } else {
            noOptionSelected()
        }
    }

    fun updateIncludeCapitalLetterValue(newValue: Boolean) {
        _includeCapitalLetter.value = newValue
    }

    fun updateIncludeNonCapitalLettersValue(newValue: Boolean) {
        _includeNonCapitalLetter.value = newValue
    }

    fun updateIncludeNumbersValue(newValue: Boolean) {
        _includeNumbers.value = newValue
    }

    fun updateIncludeSpecialCharsValue(newValue: Boolean) {
        _includeSpecialChars.value = newValue
    }

    fun updateSliderValue(newValue: Float) {
        _sliderValue.value = newValue
    }

    fun updateShowDisclaimerDialogValue(newValue: Boolean) {
        _showDisclaimerDialog.value = newValue
    }

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

    private fun isAnyIncludeActive() : Boolean {
        return _includeCapitalLetter.value ||
                _includeNonCapitalLetter.value ||
                _includeNumbers.value ||
                _includeSpecialChars.value
    }
}