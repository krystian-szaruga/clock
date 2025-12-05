package com.olr261dn.clock.navigation

enum class ClockScreens {
    NotificationPermission,
    Clock,
    Alarm,
    WorldClocks,
    Timer,
    Stopwatch,
    Settings,
    Splash;

    companion object{
        fun fromRoute(route: String?): ClockScreens =
            when(route?.substringBefore("/")){
                NotificationPermission.name -> NotificationPermission
                Clock.name -> Clock
                Alarm.name -> Alarm
                WorldClocks.name -> WorldClocks
                Timer.name -> Timer
                Stopwatch.name -> Stopwatch
                Settings.name -> Settings
                null -> Clock
                else -> throw IllegalArgumentException(
                    "Route $route is not recognized")
            }
    }
}