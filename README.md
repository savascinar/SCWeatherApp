# SCWeatherApp

A weather app built as part of an Android take-home assignment, demonstrating skills in **Kotlin**, **Jetpack Compose**, and **clean architecture**. The app allows users to search for a city, view its weather, and persist their selection for future use.

---

## Features

1. **Home Screen**:
    - Displays weather for a single saved city:
        - **City name**.
        - **Temperature**.
        - **Weather condition** with corresponding icon.
        - **Humidity** (%).
        - **UV index**.
        - **Feels like** temperature.
    - Prompts the user to search for a city if none is saved.
    - Includes a **search bar** for querying new cities.

2. **Search Behavior**:
    - Displays search results in a card format.
    - Allows users to tap a result to update the home screen and persist their selection.

3. **Persistence**:
    - The selected city is saved locally using **DataStore**.
    - The app reloads the city’s weather data upon launch.

4. **Error Handling**:
    - Handles invalid city searches and network errors gracefully.

---

## Tech Stack

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Architecture**: MVVM (Model-View-ViewModel)
- **Dependency Injection**: Hilt
- **Networking**: Retrofit with WeatherAPI.com integration
- **Local Storage**: DataStore
- **Minimum SDK**: 28
- **Target SDK**: 34

---

## API Integration

The app integrates with [WeatherAPI.com](https://www.weatherapi.com/docs/) to fetch weather data, including:

- **Temperature**.
- **Weather condition** (including icon URL).
- **Humidity** (%).
- **UV index**.
- **Feels like** temperature.

---

## Design

The app adheres to the provided [Figma design specifications](https://www.figma.com/design/0zySCKWbyeRO805ifaz1lr/Weather-App-Test-Task?node-id=0-1) with **95% visual accuracy**.

---

## How to Run the App

1. Clone the repository:
   ```bash
   git clone git@github.com:savascinar/SCWeatherApp.git
   cd SCWeatherApp
2.	Open the project in Android Studio:
	•	Ensure you are using Android Studio Flamingo (or newer).
	•	Sync the Gradle files.
3. Build and run the project:
	•	Use a device or emulator with minimum SDK 28.