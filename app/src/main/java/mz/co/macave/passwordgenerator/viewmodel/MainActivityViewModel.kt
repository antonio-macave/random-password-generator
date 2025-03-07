package mz.co.macave.passwordgenerator.viewmodel

import androidx.lifecycle.ViewModel
import java.util.Random

class MainActivityViewModel : ViewModel() {

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

    private fun computePassword(charsQuantity: Int, charsList: List<String>) : String {
        val password = StringBuilder()
        val random = Random()
        for (value in charsList.take(charsQuantity)) {
            password.append(value[random.nextInt(charsQuantity+1)])
        }
        return password.toString()
    }
}