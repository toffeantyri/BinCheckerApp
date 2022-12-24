package ru.testwork.bincheckerapp.presentation.screens.home

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import ru.testwork.bincheckerapp.data.models.remote.Number
import ru.testwork.bincheckerapp.presentation.theme.LightLightBlue
import ru.testwork.bincheckerapp.presentation.utils.showToast

@Composable
fun BinInfoCard(data: BinInfoModel?) {


    val context = LocalContext.current as Activity

    val titleNoInfo = stringResource(
        id = R.string.info_title_with_number_error
    )

    val titleInfoIsExist = stringResource(
        id = R.string.info_title_with_number,
        (data?.binCode ?: 0)
    )

    val titleText by remember {
        mutableStateOf(
            if (data != null) titleInfoIsExist else titleNoInfo
        )
    }

    val errorOpenAnyAppText = stringResource(id = R.string.intent_no_application_exist)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        backgroundColor = LightLightBlue
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = titleText,
                fontSize = 16.sp,
                color = Color.DarkGray,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )

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

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = data?.bank?.bankName ?: "?",
                    fontSize = 20.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )

                if (data?.bank?.url != null) {

                    val browserIntent = Intent(
                        Intent.ACTION_VIEW, Uri.parse("https://${data.bank.url}")
                    )

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp)
                            .clickable {
                                try {
                                    context.startActivity(browserIntent)
                                } catch (e: Exception) {
                                    context.showToast(errorOpenAnyAppText)
                                }
                            },
                        text = data.bank.url,
                        fontSize = 16.sp,
                        color = Color.Blue,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )
                }

                if (data?.bank?.phone != null) {

                    val intent = Intent(Intent.ACTION_DIAL)
                    intent.data = Uri.parse("tel:${data.bank.phone}")

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp)
                            .clickable {
                                try {
                                    context.startActivity(intent)
                                } catch (e: Exception) {
                                    context.showToast(errorOpenAnyAppText)
                                }
                            },
                        text = data.bank.phone,
                        fontSize = 16.sp,
                        color = Color.Green,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )
                }
                if (data?.bank?.city != null) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = data.bank.city,
                        fontSize = 16.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )
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
                    value = data?.scheme
                )

                SimpleColumn(
                    modifier = Modifier
                        .weight(1f),
                    title = stringResource(id = R.string.TYPE),
                    value = data?.type
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
                    value = data?.brand,
                )

                SimpleColumn(
                    modifier = Modifier
                        .weight(1f),
                    title = stringResource(id = R.string.PREPAID),
                    value = if (data?.prepaid != null) {
                        if (data.prepaid == true) stringResource(id = R.string.yes)
                        else stringResource(id = R.string.no)
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
                    value = if (data?.number?.length != null) {
                        data.number.length.toString()
                    } else "?"
                )

                SimpleColumn(
                    modifier = Modifier
                        .weight(1f),
                    title = stringResource(id = R.string.luhn),
                    value = if (data?.number?.luhn != null) {
                        data.number.luhn.toString()
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
                text = data?.country?.name ?: "?",
                fontSize = 20.sp,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )

            SimpleRowRow(
                leftTitle = stringResource(id = R.string.latitude),
                leftValue = if (data?.country?.latitude != null) {
                    data.country.latitude.toString()
                } else "?",
                rightTitle = stringResource(id = R.string.longitude),
                rightValue = if (data?.country?.longitude != null) {
                    data.country.longitude.toString()
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

        Row {
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

        Row {
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
        )
    )
}