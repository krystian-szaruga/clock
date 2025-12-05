package com.olr261dn.clock.components.surfaceWithContent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.olr261dn.clock.components.colors.common.getBackgroundColor
import com.olr261dn.clock.components.surfaceWithContent.utils.SurfaceWithContent


@Composable
fun SurfaceWithColumn(
    modifier: Modifier = Modifier,
    padding: Dp = 10.dp,
    columnPadding: Dp = 16.dp,
    borderWidth: Float = 8f,
    customBackgroundColor: Color = getBackgroundColor(),
    dropDownAction: (() -> Unit)? = null,
    dropDownIcon: ImageVector = Icons.Filled.ArrowDropDown,
    composableContent: @Composable () -> Unit){

    SurfaceWithContent(
        modifier = modifier,
        padding = padding,
        borderWidth = borderWidth,
        customBackgroundColor = customBackgroundColor,
        composableContent = {
            Column(
                modifier = Modifier
                    .padding(columnPadding)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                composableContent.invoke()
                dropDownAction?.let {
                    IconButton(
                        modifier = Modifier
                            .size(20.dp)
                            .align(Alignment.CenterHorizontally),
                        onClick = {
                            it.invoke() }) {
                        Icon(
                            imageVector = dropDownIcon,
                            contentDescription = "Dropdown Icon")
                    }
                }
            }
        },
    )
}
