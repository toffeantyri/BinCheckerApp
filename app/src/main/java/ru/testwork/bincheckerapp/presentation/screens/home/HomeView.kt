package ru.testwork.bincheckerapp.presentation.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.testwork.bincheckerapp.R
import ru.testwork.bincheckerapp.presentation.theme.SmokyWhite

@Composable
fun HomeView() {

    var inputBinCode by remember {
        mutableStateOf(TextFieldValue("12345678"))
    }

    var borderColor by remember {
        mutableStateOf(Pair(Color.DarkGray, Color.Blue))
    }

    val pattern = remember { Regex("^\\d{0,8}\$") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(enabled = true, state = ScrollState(0))
    ) {

        Text(
            modifier = Modifier
                .wrapContentSize()
                .padding(4.dp),
            text = stringResource(id = R.string.title_input_your_bin),
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            modifier = Modifier
                .wrapContentSize()
                .padding(4.dp),
            text = stringResource(id = R.string.bin_code_description),
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            backgroundColor = SmokyWhite,
            shape = RoundedCornerShape(10.dp),
            elevation = 6.dp
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .weight(1f)
                            .padding(horizontal = 32.dp),
                        contentAlignment = Alignment.BottomStart
                    ) {
                        Image(
                            modifier = Modifier.wrapContentSize(),
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_chip),
                            contentDescription = null
                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .padding(horizontal = 16.dp),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        Row(
                            modifier = Modifier.wrapContentSize(),
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically

                        ) {
                            Image(
                                modifier = Modifier
                                    .wrapContentSize()
                                    .padding(horizontal = 8.dp),
                                imageVector = ImageVector.vectorResource(id = R.drawable.ic_back_logo),
                                contentDescription = null
                            )
                            Text(
                                modifier = Modifier
                                    .wrapContentSize()
                                    .padding(horizontal = 8.dp),
                                text = stringResource(id = R.string.bank),
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                fontSize = 20.sp
                            )
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(horizontal = 10.dp)
                        .padding(vertical = 25.dp)
                ) {
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .weight(1f)
                            .padding(horizontal = 4.dp),
                        value = inputBinCode,
                        onValueChange = {
                            if (it.text.isEmpty() || it.text.matches(pattern)) {
                                inputBinCode = it
                                borderColor = Pair(Color.DarkGray, Color.Blue)
                            } else {
                                Pair(Color.Red, Color.Red)
                            }
                        }, maxLines = 1,
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            autoCorrect = false,
                            keyboardType = KeyboardType.Number
                        ),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            textColor = Color.Black,
                            backgroundColor = Color.White,
                            unfocusedBorderColor = borderColor.first,
                            focusedBorderColor = borderColor.second
                        )
                    )


                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .weight(0.5f)
                            .padding(horizontal = 4.dp),
                        value = "XXXX",
                        onValueChange = {},
                        readOnly = true,
                        maxLines = 1,
                        singleLine = true
                    )

                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .weight(0.5f)
                            .padding(horizontal = 4.dp),
                        value = "XXXX",
                        onValueChange = {},
                        readOnly = true,
                        maxLines = 1,
                        singleLine = true
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 16.dp),
                        text = stringResource(id = R.string.person_name),
                        fontWeight = FontWeight.Medium,
                        color = Color.Black,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Start
                    )

                    Image(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 16.dp)
                            .padding(vertical = 8.dp),
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_visa),
                        contentDescription = null,
                        alignment = Alignment.CenterEnd
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeView() {
    HomeView()
}