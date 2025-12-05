package com.olr261dn.clock.services.common.alarmSoundManager

import android.content.Context
import android.media.AudioAttributes
import android.media.AudioDeviceInfo
import android.media.AudioManager
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.util.Log

object AlarmSoundManager {
    private var mediaPlayer: MediaPlayer? = null
    private val alarmUri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
    private var audioManager: AudioManager? = null
    private var maxVolume: Int = 7
    private val handler = Handler(Looper.getMainLooper())
    private const val VOLUME_STEP = 1
    private const val DELAY = 1000L
    private const val INITIAL_VOLUME = 0

    fun playAlarmSound(context: Context, increaseVolOverTime: Boolean = true) {
        if (mediaPlayer?.isPlaying == true) stopAlarmSound()
        initialize(context)
        audioManager?.setStreamVolume(AudioManager.STREAM_ALARM, INITIAL_VOLUME, 0)
        handleWhenHeadphonesConnected()
        mediaPlayer?.apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_ALARM)
                    .build()
            )
            setDataSource(context, alarmUri)
            isLooping = true
            prepare()
            start()
        } ?: Log.e("TAG", "Failed To Start Player")
        if (increaseVolOverTime){
            increaseVolumeOverTime()
        } else {
            audioManager?.let { manager ->
                val defaultVolume = maxVolume
                manager.setStreamVolume(
                    AudioManager.STREAM_ALARM,
                    defaultVolume,
                    0
                )
            }
        }
    }

    private fun handleWhenHeadphonesConnected(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val speakerDevice = audioManager?.getDevices(
                AudioManager.GET_DEVICES_OUTPUTS
            )?.firstOrNull {
                it.type == AudioDeviceInfo.TYPE_BUILTIN_SPEAKER
            }
            speakerDevice?.let { audioManager?.setCommunicationDevice(it) }
        } else {
            @Suppress("DEPRECATION")
            audioManager?.isSpeakerphoneOn = true
        }
    }

    private fun increaseVolumeOverTime() {
        val increaseVolumeRunnable = object : Runnable {
            override fun run() {
                audioManager?.let { manager ->
                    val currentVolume = manager.getStreamVolume(AudioManager.STREAM_ALARM)
                    if (currentVolume < maxVolume) {
                        manager.setStreamVolume(
                            AudioManager.STREAM_ALARM,
                            (currentVolume + VOLUME_STEP).coerceAtMost(maxVolume),
                            0
                        )
                        handler.postDelayed(this, DELAY)
                    } else {
                        handler.removeCallbacks(this)
                    }
                }
            }
        }
        handler.post(increaseVolumeRunnable)
    }

    private fun initialize(context: Context) {
        stopAlarmSound()
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer().also {
                audioManager = context.getSystemService(Context.AUDIO_SERVICE) as? AudioManager
                maxVolume = audioManager?.getStreamVolume(AudioManager.STREAM_ALARM) ?: 7
            }
        } else {
            mediaPlayer?.reset()
            stopAlarmSound()
        }
    }

    fun stopAlarmSound() {
        mediaPlayer?.let {
            try {
                if (it.isPlaying) it.stop()
                it.release()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        mediaPlayer = null
        audioManager = null
        handler.removeCallbacksAndMessages(null)
    }
}
