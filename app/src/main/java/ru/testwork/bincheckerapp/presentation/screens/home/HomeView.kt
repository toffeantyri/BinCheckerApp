package ru.testwork.bincheckerapp.presentation.screens.home

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Handler
import android.os.Looper
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.FlowPreview
import ru.testwork.bincheckerapp.R
import ru.testwork.bincheckerapp.presentation.utils.OnBackPressedCallBackCompose
import ru.testwork.bincheckerapp.presentation.utils.showToast

@OptIn(FlowPreview::class)
@SuppressLint("FlowOperatorInvokedInComposition")
@Composable
fun HomeView(viewModel: HomeViewModel = hiltViewModel()) {

    val backPressDoubleClick = remember {
        mutableStateOf(false)
    }


    val binCodeIsValidate by viewModel.binCodeIsValid.collectAsState()
    val isLoading by viewModel.isLoadingState.collectAsState()
    val binData by viewModel.binDtoFlow.collectAsState()
    val inputBinCode by viewModel.inputBinCode.collectAsState()

    val inputPattern = remember { Regex("^\\d{0,8}\$") }

    val changeInputText = viewModel::changeInputBinCode

    val onValueChanged: (text: String) -> Unit = { text ->
        if (text.isEmpty() || text.matches(inputPattern)) {
            changeInputText(text)
            viewModel.validateBinCode(text)
        } else {
            if (!inputBinCode.matches(inputPattern)) {
                viewModel.validateBinCode(text)
            }
        }
    }

    val onSearchBinCodeClick = viewModel::getBinCodeInfo

    val context = LocalContext.current as Activity

    val message = stringResource(id = R.string.double_back_for_exit)
    OnBackPressedCallBackCompose() {
        if (backPressDoubleClick.value) {
            context.finish()
        } else {
            context.showToast(message)
        }
        backPressDoubleClick.value = true
        val handler = Looper.myLooper()?.let { Handler(it) }
        handler?.postDelayed({ backPressDoubleClick.value = false }, 2000)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
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
            onInputSearchBarCode = onSearchBinCodeClick,
            isLoading = isLoading
        )

        Button(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 4.dp),
            enabled = !isLoading,
            shape = RoundedCornerShape(8.dp),
            onClick = { onSearchBinCodeClick() }) {
            Text(
                modifier = Modifier.padding(4.dp),
                text = stringResource(id = R.string.button_check),
                maxLines = 1
            )
        }

        AnimatedVisibility(visible = binData != null) {
            BinInfoCard(data = binData)
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeView() {
    HomeView()
}