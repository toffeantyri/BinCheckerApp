package ru.testwork.bincheckerapp.presentation.screens.history

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.testwork.bincheckerapp.TAG
import ru.testwork.bincheckerapp.data.models.remote.BinInfoModel
import ru.testwork.bincheckerapp.domain.IBinCodeInfoInteractor
import javax.inject.Inject

@HiltViewModel
class HistoryBinInfoViewModel @Inject constructor(
    private val binCodeInfoInteractor: IBinCodeInfoInteractor
) : ViewModel() {

    val listData: MutableStateFlow<List<BinInfoModel>> = MutableStateFlow(listOf())

    val openedCards = MutableStateFlow(listOf<Int>())


    init {
        viewModelScope.launch {
            binCodeInfoInteractor.getHistoryListInfo().collect {
                Log.d(TAG, "VM INIT $it")
                listData.value = it
            }
        }
    }

    fun loadBinInfoListHistory() {
        viewModelScope.launch {
            kotlin.runCatching {
                binCodeInfoInteractor.getHistoryListInfo()
            }.onSuccess {
                Log.d(TAG, "VM SUCCESS")
                //listData.emit(it)
            }.onFailure {
                Log.d(TAG, "VM ERROR : $it")
            }

        }
    }


    fun clearHistory() {
        viewModelScope.launch {
            kotlin.runCatching {
                binCodeInfoInteractor.clearHistory()
            }.onSuccess {
                Log.d(TAG, "VM CLEAR :SUCCESS ")
            }.onFailure {
                Log.d(TAG, "VM CLEAR ERROR ")
            }

        }
    }

    fun onCardOpenClose(positionCard: Int) {
        openedCards.value = openedCards.value.toMutableList().also { list ->
            if (list.contains(positionCard)) list.remove(positionCard) else list.add(positionCard)
        }
    }

}