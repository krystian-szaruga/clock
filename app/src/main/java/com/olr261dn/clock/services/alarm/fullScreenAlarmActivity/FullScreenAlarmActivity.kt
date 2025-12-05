package com.olr261dn.clock.services.alarm.fullScreenAlarmActivity

import android.os.Bundle
import androidx.activity.compose.setContent
import com.olr261dn.clock.services.alarm.fullScreenAlarmActivity.screen.AlarmOnLockScreen
import com.olr261dn.clock.services.alarm.receivers.alarmReceivers.DismissAlarmReceiver
import com.olr261dn.clock.services.alarm.receivers.alarmReceivers.SnoozeDismissReceiver
import com.olr261dn.clock.services.common.fullScreenActivity.BaseFullScreenActivity
import com.olr261dn.clock.ui.theme.ClockTheme

class FullScreenAlarmActivity : BaseFullScreenActivity(CANCEL_ALARM_ACTION){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val id = intent.getIntExtra("Id", 0)
        val name = intent.getStringExtra("Name")
        val time = intent.getStringExtra("Time")
        setContent {
            ClockTheme {
                AlarmOnLockScreen(
                    label = name ?: "Alarm",
                    time = time ?: " ",
                    onDismiss = { launchReceiver(
                        actionReceiver = DismissAlarmReceiver::class.java,
                        id = id) },
                    onSnooze = { launchReceiver(
                        actionReceiver = SnoozeDismissReceiver::class.java,
                        id = id) }
                )
            }
        }
    }
    companion object {
        const val CANCEL_ALARM_ACTION =
            "com.olr261dn.clock.CANCEL_ALARM_FULL_SCREEN"
    }
}