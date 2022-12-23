package ru.testwork.bincheckerapp.presentation.screens.history

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import ru.testwork.bincheckerapp.TAG

@Composable
fun HistoryDetailsView(viewModel: HistoryBinInfoViewModel = hiltViewModel()) {

    val binInfoList = viewModel.listData.collectAsState()

    Log.d(TAG, "View: $binInfoList")

}