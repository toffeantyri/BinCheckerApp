package ru.testwork.bincheckerapp.presentation.screens.home

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.testwork.bincheckerapp.R

@Composable
fun HomeView(viewModel: HomeViewModel = hiltViewModel()) {

    var inputBinCode by remember {
        mutableStateOf("45717360")
    }

    val binCodeIsValidate by viewModel.binCodeIsValid.collectAsState()

    val inputPattern = remember { Regex("^\\d{0,8}\$") }

    val onValueChanged: (text: String) -> Unit = { text ->
        if (text.isEmpty() || text.matches(inputPattern)) {
            inputBinCode = text
            viewModel.validateBinCode(text)
        } else {
            if (!inputBinCode.matches(inputPattern)) {
                viewModel.validateBinCode(text)
            }
        }
    }

    val onSearchBinCodeClick = viewModel::getBinCodeInfo




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
            fontWeight = FontWeight.SemiBold,
            color = Color.Black
        )
        Text(
            modifier = Modifier
                .wrapContentSize()
                .padding(4.dp),
            text = stringResource(id = R.string.bin_code_description),
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black
        )

        InputableCard(
            inputText = inputBinCode,
            binCodeIsValid = binCodeIsValidate,
            onValueChanged = onValueChanged,
            onInputSearchBarCode = onSearchBinCodeClick
        )


    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeView() {
    HomeView()
}