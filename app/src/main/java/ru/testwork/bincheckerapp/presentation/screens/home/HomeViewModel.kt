package ru.testwork.bincheckerapp.presentation.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.testwork.bincheckerapp.TAG
import ru.testwork.bincheckerapp.data.models.remote.BinInfoModel
import ru.testwork.bincheckerapp.domain.IBinCodeInfoInteractor
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val binCodeInteractor: IBinCodeInfoInteractor) :
    ViewModel() {

    private val inputPattern = Regex("^\\d{6,8}\$")

    val binCodeIsValid = MutableStateFlow(value = true)

    val inputBinCode = MutableStateFlow(value = "45717360")

    val isLoadingState = MutableStateFlow(value = false)

    val toastMessageState = MutableStateFlow<String?>(null)

    val binDtoFlow = MutableStateFlow<BinInfoModel?>(value = null)

    fun validateBinCode(text: String): Boolean {
        return if (text.matches(inputPattern)) {
            binCodeIsValid.tryEmit(true)
            true
        } else {
            binCodeIsValid.tryEmit(false)
            false
        }
    }

    fun changeInputBinCode(text: String) {
        inputBinCode.tryEmit(text)
    }

    fun getBinCodeInfo() {
        viewModelScope.launch {
            if (validateBinCode(inputBinCode.value)) {
                emitNewValue(null)
                kotlin.runCatching {
                    isLoadingState.tryEmit(true)
                    binCodeInteractor.getBinCodeInfo(inputBinCode.value.toInt())
                }.onSuccess {
                    isLoadingState.tryEmit(false)
                    emitNewValue(it)
                    Log.d(TAG, "VM success: $it")
                }.onFailure {
                    isLoadingState.tryEmit(false)
                    Log.d(TAG, "VM error: $it")
                    emitNewValue(null)
                    toastMessageState.value = it.message
                }
            } else {
                binCodeIsValid.tryEmit(false)
            }
        }
    }

    private suspend fun emitNewValue(newValue: BinInfoModel?) {
        delay(500)
        binDtoFlow.value = newValue
    }

    fun clearToastMessage() {
        toastMessageState.value = null
    }

}