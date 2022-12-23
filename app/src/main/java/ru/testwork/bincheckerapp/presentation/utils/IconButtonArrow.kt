package ru.testwork.bincheckerapp.presentation.utils

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import ru.testwork.bincheckerapp.R


@Preview
@Composable
fun CardArrow(
    modifier: Modifier = Modifier,
    modifierIcon: Modifier = Modifier,
    degrees: Float = 0f,
    onClick: () -> Unit = {}
) {
    IconButton(
        modifier = modifier
            .wrapContentWidth()
            .wrapContentHeight(),
        onClick = onClick,
        content = {
            Icon(
                modifier = modifierIcon
                    .fillMaxSize()
                    .rotate(degrees)
                    .aspectRatio(1f),
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_expand_more),
                contentDescription = ""
            )
        }
    )
}