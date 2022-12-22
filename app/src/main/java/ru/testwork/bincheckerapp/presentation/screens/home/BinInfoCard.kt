package ru.testwork.bincheckerapp.presentation.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.testwork.bincheckerapp.R
import ru.testwork.bincheckerapp.data.models.remote.Bank
import ru.testwork.bincheckerapp.data.models.remote.BinInfoModel
import ru.testwork.bincheckerapp.data.models.remote.Country
import ru.testwork.bincheckerapp.presentation.theme.LightLightGray

@Composable
fun BinInfoCard(binInfoModel: BinInfoModel) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        backgroundColor = LightLightGray
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {

                SimpleColumn(
                    modifier = Modifier
                        .weight(1f),
                    title = stringResource(id = R.string.scheme_network),
                    value = binInfoModel.scheme
                )

                SimpleColumn(
                    modifier = Modifier
                        .weight(1f),
                    title = stringResource(id = R.string.TYPE),
                    value = binInfoModel.type
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {

                SimpleColumn(
                    modifier = Modifier
                        .weight(1f),
                    title = stringResource(id = R.string.BRAND),
                    value = binInfoModel.brand,
                )

                SimpleColumn(
                    modifier = Modifier
                        .weight(1f),
                    title = stringResource(id = R.string.PREPAID),
                    value = if (binInfoModel.prepaid != null) {
                        if (binInfoModel.prepaid) stringResource(id = R.string.yes) else stringResource(
                            id = R.string.no
                        )
                    } else "?"
                )


            }

        }
    }
}

@Composable
fun SimpleColumn(modifier: Modifier = Modifier, title: String, value: String?) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = title, fontSize = 14.sp, color = Color.Gray, fontWeight = FontWeight.Normal)

        Text(
            text = value ?: "?",
            fontSize = 20.sp,
            color = Color.Black,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center
        )

    }

}


@Composable
@Preview(showBackground = true)
fun PreviewBinInfoCard() {
    BinInfoCard(
        binInfoModel = BinInfoModel(
            binCode = 12345678,
            bank = Bank(
                city = "Москва",
                name = "Jyske Bank, Hjørring",
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
            number = ru.testwork.bincheckerapp.data.models.remote.Number(
                length = 16,
                luhn = true
            ),
            prepaid = true,
            scheme = "visa",
            type = "debit"
        )
    )
}