# Clock App

## Overview

Clock App is a modern and intuitive Android application built using **Kotlin** and **Jetpack Compose**. It provides essential time management features, including alarms, timers, a stopwatch, and world clocks. The app also includes a customizable home screen widget displaying time, date, and upcoming alarms.

## 📚 Learning Purpose Project

This is an **educational project** created to learn and practice Android development with modern tools and architecture patterns.

## ⚠️ Architecture Note

This is one of my earlier Android projects. While the app is fully functional and implements clean architecture patterns (Repository, DAO, ViewModel, Hilt), **all code resides in a single module**.
At the time of development, this single-module approach was appropriate for my learning stage. However, with my current knowledge, I would choose multi-modular architecture for future projects to improve scalability and separation of concerns.

## Features

### ⏰ Alarm Clock

- Set multiple alarms with customizable settings.
- Choose specific days for the alarm to repeat.
- Enable **"Gradually Increase Volume"** for a smooth wake-up experience.
- Snooze or dismiss alarms easily.

### ⏳ Timers

- Create and manage multiple countdown timers.
- Set custom durations for each timer.

### ⏱ Stopwatch

- Start, stop, and reset the stopwatch.
- Track laps and view detailed lap times.

### 🕒 Display Clock

- View the current time in an elegant, customizable UI.
- Show **world clocks** for different time zones.

### 📌 Home Screen Widget

- Display time (with or without seconds, based on user settings).
- Show the current date and upcoming alarms.
- Customize widget appearance to fit user preferences.

## Technology Stack

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **State Management**: Jetpack ViewModel
- **Navigation**: Jetpack Navigation
- **Persistence**: Room Database (for alarms and timers)
- **Dependency Injection**: Hilt
- **Work Manager**: Background task scheduling


## License
This project is licensed under the MIT License.