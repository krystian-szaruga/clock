package com.olr261dn.clock.components.textField

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.olr261dn.clock.components.colors.common.getLabelTextColor
import com.olr261dn.clock.components.colors.common.getTextFieldColors
import com.olr261dn.clock.utils.fontSize.nonScaledSp

@Composable
fun CreateLabelField(
    value: String,
    labelText: String,
    modifier: Modifier = Modifier,
    readOnly: Boolean = false,
    keyboardAction: ImeAction = ImeAction.Default,
    trailingIcon : @Composable () -> Unit = {},
    onValueChange: (String) -> Unit = {}) {

    TextField(
        colors = getTextFieldColors(),
        maxLines = 1,
        value = value,
        readOnly = readOnly,
        onValueChange = { onValueChange(it) },
        textStyle = TextStyle(textAlign = TextAlign.Center),
        label = {
            Text(
                fontSize = 14.nonScaledSp,
                color = getLabelTextColor(),
                fontStyle = FontStyle.Italic,
                text = labelText
            )
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = keyboardAction
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp),
        trailingIcon = trailingIcon
    )
}
