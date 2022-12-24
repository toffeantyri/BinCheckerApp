package ru.testwork.bincheckerapp.presentation.screens.history

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import ru.testwork.bincheckerapp.R
import ru.testwork.bincheckerapp.presentation.theme.LightLightGreen
import ru.testwork.bincheckerapp.presentation.theme.LightLightRed

@Composable
fun DialogClearHistory(
    visible: Boolean,
    onActionSubmit: () -> Unit,
    onActionDismiss: () -> Unit
) {

    if (visible) {
        Dialog(onDismissRequest = onActionDismiss) {

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                shape = RoundedCornerShape(10.dp),
                backgroundColor = Color.White
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(10.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(4.dp),
                        text = stringResource(id = R.string.dialog_clear_title),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.DarkGray
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {

                        Button(
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = LightLightGreen),
                            onClick = onActionSubmit
                        ) {
                            Text(
                                text = stringResource(id = R.string.yes),
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                        }

                        Button(
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = LightLightRed),
                            onClick = onActionDismiss
                        ) {
                            Text(
                                text = stringResource(id = R.string.no),
                                color = Color.Black,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp
                            )
                        }
                    }
                }
            }
        }
    }
}


@Composable
@Preview(showBackground = true)
fun PreviewDialogH() {

    var vis by remember {
        mutableStateOf(true)
    }

    DialogClearHistory(visible = vis, onActionSubmit = {
        vis = !vis
    }, onActionDismiss = {
        vis = !vis
    })
}