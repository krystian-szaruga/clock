package com.olr261dn.clock.components.scaffoldBar.fabContent

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.olr261dn.clock.components.colors.appBar.getAddIconTint

@Composable
fun FabContent(
    fabIcon: ImageVector,
    onTap: () -> Unit) {

    FloatingActionButton(
        modifier = Modifier.size(55.dp),
        onClick = { onTap() },
        shape = RoundedCornerShape(30.dp),
        containerColor = MaterialTheme.colorScheme.secondary,
    ) {
        Icon(
            imageVector = fabIcon,
            contentDescription = "Show/Hide Popup",
            tint = getAddIconTint()
        )
    }
}