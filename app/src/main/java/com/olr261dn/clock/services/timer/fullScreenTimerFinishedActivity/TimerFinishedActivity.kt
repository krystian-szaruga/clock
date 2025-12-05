package com.olr261dn.clock.services.timer.fullScreenTimerFinishedActivity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.EaseInOutCubic
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.olr261dn.clock.components.colors.common.getButtonColors
import com.olr261dn.clock.components.colors.common.getOnBackgroundColor
import com.olr261dn.clock.components.scaffoldBar.topBar.AppBarTop
import com.olr261dn.clock.services.common.fullScreenActivity.BaseFullScreenActivity
import com.olr261dn.clock.services.timer.receiver.DismissTimerReceiver
import com.olr261dn.clock.ui.theme.ClockTheme

class TimerFinishedActivity : BaseFullScreenActivity(CANCEL_TIMER_ACTION) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setShowWhenLocked(true)
        setTurnScreenOn(true)
        enableEdgeToEdge()
        setContent {
            ClockTheme {
                TimerFinishedScreen {
                    launchReceiver(DismissTimerReceiver::class.java)
                }
            }
        }
    }
    companion object {
        const val CANCEL_TIMER_ACTION =
            "com.olr261dn.clock.CANCEL_TIMER_FULL_SCREEN"
    }

}


@Composable
fun TimerFinishedScreen(
    onDismiss: () -> Unit
) {
    Scaffold(topBar = { AppBarTop(title = "Timer") {} }) { padding ->
        Surface(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        )
        {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(20.dp)
                ) {
                    AnimatedTimerText()
                    Button(
                        colors = getButtonColors(),
                        onClick = onDismiss,
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(text = "Dismiss", fontSize = 18.sp)
                    }
                }
            }
        }
    }
}
@Composable
fun AnimatedTimerText() {
    val infiniteTransition = rememberInfiniteTransition(label = "Text")
    val animatedScale = infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = EaseInOutCubic),
            repeatMode = RepeatMode.Reverse
        ), label = "Text"
    )
    Text(
        text = "Timer Finished!",
        fontSize = 40.sp,
        fontWeight = FontWeight.Bold,
        color = getOnBackgroundColor(),
        modifier = Modifier
            .scale(animatedScale.value)
            .padding(16.dp)
    )
}


