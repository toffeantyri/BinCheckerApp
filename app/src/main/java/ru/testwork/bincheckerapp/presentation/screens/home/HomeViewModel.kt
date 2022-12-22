package ru.testwork.bincheckerapp.presentation.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.testwork.bincheckerapp.TAG
import ru.testwork.bincheckerapp.domain.IBinCodeInfoInteractor
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val binCodeInteractor: IBinCodeInfoInteractor) :
    ViewModel() {

    private val inputPattern = Regex("^\\d{6,8}\$")

    val binCodeIsValid = MutableStateFlow(value = true)

    fun validateBinCode(text: String): Boolean {
        return if (text.matches(inputPattern)) {
            binCodeIsValid.tryEmit(true)
            Log.d("MyLog", "VALIDate")
            true
        } else {
            binCodeIsValid.tryEmit(false)
            false
        }
    }

    fun getBinCodeInfo(text: String) {
        viewModelScope.launch {
            if (validateBinCode(text)) {
                kotlin.runCatching {
                    Log.d(TAG, "VM: $text")
                    binCodeInteractor.getBinCodeInfo(text.toInt())
                }.onSuccess {

                }.onFailure {

                }
            } else {
                binCodeIsValid.tryEmit(false)
            }
        }
    }

}