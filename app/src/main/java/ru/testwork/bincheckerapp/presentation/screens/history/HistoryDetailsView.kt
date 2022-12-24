package ru.testwork.bincheckerapp.presentation.screens.history

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.testwork.bincheckerapp.R
import ru.testwork.bincheckerapp.TAG

@Preview(showBackground = true)
@Composable
fun HistoryDetailsView(viewModel: HistoryBinInfoViewModel = hiltViewModel()) {

    val binInfoList by viewModel.listData.collectAsState()
    val expandedCards by viewModel.openedCards.collectAsState()
    val onOpenCloseFun = viewModel::onCardOpenClose
    val onClearHistory = viewModel::clearHistory

    Log.d(TAG, "View: $binInfoList")

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(vertical = 4.dp)
    ) {

        item {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .wrapContentHeight()
                    .padding(horizontal = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(4.dp),
                    text = stringResource(id = R.string.title_history_view),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )

                IconButton(
                    modifier = Modifier
                        .size(50.dp, 50.dp)
                        .padding(8.dp),
                    onClick = onClearHistory
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_baseline_delete_sweep_24),
                        contentDescription = null,
                    )
                }
            }
        }

        itemsIndexed(binInfoList) { index, item ->
            HistoryItem(
                data = item,
                isOpen = expandedCards.contains(index),
                onOpenClose = { onOpenCloseFun(index) }
            )

            Spacer(modifier = Modifier.padding(vertical = 4.dp))
        }


    }

}


