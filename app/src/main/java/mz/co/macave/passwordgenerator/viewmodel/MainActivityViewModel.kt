package mz.co.macave.passwordgenerator.viewmodel

import androidx.lifecycle.ViewModel
import java.util.Random
import kotlin.random.Random

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

    private fun getComputedPassword(charsQuantity: Int, charsList: String) : String {
        val password = StringBuilder()
        for (i in 1..charsQuantity) {
            password.append(charsList[Random.nextInt(charsList.length)])
        }
        return password.toString()
    }
}