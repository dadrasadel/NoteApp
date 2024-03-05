# NoteApp

NoteApp is a modern note-taking application built for Android using Jetpack Compose, Hilt for dependency injection, Room for local database storage, and following the MVVM + Multi-module architecture.

## Features

- Create, read, update, and delete notes.
- Store notes locally using Room database.
- Modern UI with Jetpack Compose.
- Dependency injection with Hilt.
- Follows MVVM architecture for better separation of concerns.
- Multi-module structure for better code organization and maintainability.

## Requirements

- Android Studio Arctic Fox (2020.3.1) or later.
- Android SDK version 26 or higher.
- Kotlin version 1.5.0 or later.
- Gradle version 7.0.2 or later.

## Getting Started

1. Clone the repository:

[//]: # (```bash)
git clone https://github.com/AdelDdr/NoteApp.git
## Getting Started

1. **Open the project in Android Studio:** Ensure you have Android Studio installed on your machine. Open Android Studio and select "Open an existing Android Studio project". Navigate to the directory where you have cloned the NoteApp repository and select the root folder.

2. **Build and run the application on an Android device or emulator:** Once the project is opened in Android Studio, wait for the Gradle sync to finish. Then, you can build the project by clicking on the green play button in the toolbar and select your desired device or emulator to run the application.

## Modules

The project is organized into several modules:

- **app:** Main Android application module containing UI and presentation logic.
- **data:** Module responsible for data handling, including database operations and data sources.
- **domain:** Module containing business logic and domain entities.
- **common:** Shared module containing utilities, extensions, and common functionalities.

## Architecture

The application follows the MVVM (Model-View-ViewModel) architecture pattern for a clean separation of concerns:

- **Model:** Data and business logic reside in the `domain` and `data` modules.
- **View:** UI components implemented using Jetpack Compose in the `app` module.
- **ViewModel:** Handles UI-related logic and interacts with the domain layer. Implemented in the `app` module.

## Dependencies

- **Jetpack Compose:** Modern UI toolkit for building native Android UIs.
- **Hilt:** Dependency injection library for Android.
- **Room:** Persistence library for local database storage.
- **Coroutines:** Kotlin's asynchronous programming library.
- **Navigation Component:** Android Jetpack's navigation library for managing navigation and deep linking.
- **Lifecycle Components:** Set of libraries to build lifecycle-aware components.
- **ViewModel:** Part of Android Architecture Components for managing UI-related data.
- **Material Components:** Library that provides Material Design components.
- **JUnit & Mockito:** Testing libraries for unit testing and mocking dependencies.

## Contributing

Contributions are welcome! If you want to contribute to NoteApp, please fork the repository and submit a pull request.

## License

This project is licensed under the MIT License.

## Author

Your Name - [Your GitHub Profile](https://github.com/yourusername)
