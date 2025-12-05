package com.olr261dn.clock.components.buttons

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.olr261dn.clock.components.colors.common.getButtonColors
import com.olr261dn.clock.utils.fontSize.nonScaledSp


@Composable
fun CreateButton(
    modifier: Modifier = Modifier,
    text: String,
    buttonColors: ButtonColors = getButtonColors(),
    style: TextStyle = MaterialTheme.typography.headlineSmall,
    size: TextUnit = 11.nonScaledSp,
    textAlign: TextAlign = TextAlign.Center,
    enabled: Boolean = true,
    onClick: () -> Unit)
{
    Button(
        modifier = modifier,
        enabled = enabled,
        shape = RoundedCornerShape(28.dp),
        colors = buttonColors,
        onClick = { onClick.invoke() }
    ) {
        Text(
            textAlign = textAlign,
            style = style,
            fontSize = size,
            text = text,
            overflow = TextOverflow.Clip
        )
    }
}