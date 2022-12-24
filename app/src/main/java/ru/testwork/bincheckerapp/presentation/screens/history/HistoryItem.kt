package ru.testwork.bincheckerapp.presentation.screens.history

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.testwork.bincheckerapp.R
import ru.testwork.bincheckerapp.data.models.remote.Bank
import ru.testwork.bincheckerapp.data.models.remote.BinInfoModel
import ru.testwork.bincheckerapp.data.models.remote.Country
import ru.testwork.bincheckerapp.data.models.remote.Number
import ru.testwork.bincheckerapp.presentation.screens.home.BinInfoCard
import ru.testwork.bincheckerapp.presentation.theme.LightLightBlue
import ru.testwork.bincheckerapp.presentation.utils.CardArrow

@SuppressLint("UnusedTransitionTargetStateParameter")
@Suppress("UpdateTransitionLabel")
@Composable
fun HistoryItem(data: BinInfoModel, isOpen: Boolean, onOpenClose: () -> Unit) {

    val arrowTransitionState = remember {
        MutableTransitionState(initialState = isOpen).apply {
            targetState = !isOpen
        }
    }

    val arrowRotationDegreeTransitionState by updateTransition(
        transitionState = arrowTransitionState,
    ).animateFloat(label = "") {
        if (isOpen) 180f else 0f
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        backgroundColor = LightLightBlue,
        elevation = 6.dp,
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
        ) {


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(start = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier
                        .wrapContentWidth()
                        .wrapContentHeight(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start
                ) {

                    Text(
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(4.dp),
                        text = stringResource(id = R.string.bin_code),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.DarkGray
                    )

                    Text(
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(4.dp),
                        text = data.binCode.toString(),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.DarkGray
                    )
                }

                CardArrow(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .size(30.dp, 30.dp),
                    degrees = arrowRotationDegreeTransitionState, onClick = onOpenClose
                )
            }
            AnimatedVisibility(visible = isOpen) {
                BinInfoCard(data = data)
            }
        }
    }
}


@Composable
@Preview(showBackground = true)
fun PreviewHistoryItem() {


    val isOpen = remember {
        mutableStateOf(false)
    }

    HistoryItem(
        data = BinInfoModel(
            binCode = 12345678,
            bank = Bank(
                city = "Москва",
                bankName = "Jyske Bank, Hjørring",
                phone = " 8 800 922 00 00",
                url = "www.leningrad-spb.ru"
            ),
            brand = "Visa/Dankort",
            country = Country(
                alpha2 = "DK",
                currency = "DKK",
                emoji = "DK",
                latitude = 56,
                longitude = 10,
                name = "Denmark",
                numeric = "208"
            ),
            number = Number(
                length = 16,
                luhn = true
            ),
            prepaid = true,
            scheme = "visa",
            type = "debit"
        ), isOpen = isOpen.value, onOpenClose = {
            isOpen.value = !isOpen.value
        })

}