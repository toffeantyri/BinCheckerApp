package ru.testwork.bincheckerapp.presentation.screens.home

import androidx.compose.animation.AnimatedVisibility
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
fun BinInfoCard(binInfoModel: BinInfoModel?) {

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

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = if (binInfoModel != null) stringResource(
                    id = R.string.info_title_with_number,
                    binInfoModel.binCode
                ) else stringResource(
                    id =
                    R.string.info_title_with_number_error
                ),
                fontSize = 16.sp,
                color = Color.DarkGray,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )

            AnimatedVisibility(visible = binInfoModel?.bank != null) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {

                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(id = R.string.BANK),
                        fontSize = 14.sp,
                        color = Color.Gray,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center
                    )
                    AnimatedVisibility(visible = binInfoModel?.bank?.name != null) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = binInfoModel?.bank?.name ?: "?",
                            fontSize = 20.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center
                        )
                    }

                    AnimatedVisibility(visible = binInfoModel?.bank?.url != null) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = (binInfoModel?.bank?.url ?: "?"),
                            fontSize = 16.sp,
                            color = Color.Blue,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center
                        )
                    }

                    AnimatedVisibility(visible = binInfoModel?.bank?.phone != null) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = binInfoModel?.bank?.phone ?: "?",
                            fontSize = 16.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center
                        )
                    }
                    AnimatedVisibility(visible = binInfoModel?.bank?.city != null) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = binInfoModel?.bank?.city ?: "?",
                            fontSize = 16.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {

                SimpleColumn(
                    modifier = Modifier
                        .weight(1f),
                    title = stringResource(id = R.string.scheme_network),
                    value = binInfoModel?.scheme
                )

                SimpleColumn(
                    modifier = Modifier
                        .weight(1f),
                    title = stringResource(id = R.string.TYPE),
                    value = binInfoModel?.type
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
                    value = binInfoModel?.brand,
                )

                SimpleColumn(
                    modifier = Modifier
                        .weight(1f),
                    title = stringResource(id = R.string.PREPAID),
                    value = if (binInfoModel?.prepaid != null) {
                        if (binInfoModel.prepaid) stringResource(id = R.string.yes) else stringResource(
                            id = R.string.no
                        )
                    } else "?"
                )
            }

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.CARD_NUMBER),
                fontSize = 14.sp,
                color = Color.Gray,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {

                SimpleColumn(
                    modifier = Modifier
                        .weight(1f),
                    title = stringResource(id = R.string.length),
                    value = if (binInfoModel?.number?.length != null) {
                        binInfoModel.number.length.toString()
                    } else "?"
                )

                SimpleColumn(
                    modifier = Modifier
                        .weight(1f),
                    title = stringResource(id = R.string.luhn),
                    value = if (binInfoModel?.number?.luhn != null) {
                        binInfoModel.number.luhn.toString()
                    } else "?"
                )
            }

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.COUNTRY),
                fontSize = 14.sp,
                color = Color.Gray,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center
            )

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = binInfoModel?.country?.name ?: "?",
                fontSize = 20.sp,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )

            SimpleRowRow(
                leftTitle = stringResource(id = R.string.latitude),
                leftValue = if (binInfoModel?.country?.latitude != null) {
                    binInfoModel.country.latitude.toString()
                } else "?",
                rightTitle = stringResource(id = R.string.longitude),
                rightValue = if (binInfoModel?.country?.longitude != null) {
                    binInfoModel.country.longitude.toString()
                } else "?",
            )

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
fun SimpleRowRow(
    modifier: Modifier = Modifier,
    leftTitle: String,
    leftValue: String,
    rightTitle: String,
    rightValue: String
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {

        Row() {
            Text(
                text = leftTitle,
                fontSize = 14.sp,
                color = Color.Gray,
                fontWeight = FontWeight.Normal
            )

            Text(
                text = leftValue,
                fontSize = 14.sp,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )
        }

        Row() {
            Text(
                text = rightTitle,
                fontSize = 14.sp,
                color = Color.Gray,
                fontWeight = FontWeight.Normal
            )

            Text(
                text = rightValue,
                fontSize = 14.sp,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )
        }

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