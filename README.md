# Chuck Norris Facts Application
[![Build Status](https://app.bitrise.io/app/f04e02a60b1a154e/status.svg?token=qshry3E1gJcmkmMwnjjUcQ)](https://app.bitrise.io/app/f04e02a60b1a154e)

App made using the [Chuck Norris API](https://api.chucknorris.io) for searching Chuck Norris facts.

# About this app
- This app makes use of [Fastlane](https://fastlane.tools/) for tasks automation so, you'll have to install fastlane if you want to run these lanes locally.
- There's also a continuous integration service running at [Bitrise](https://app.bitrise.io/app/f04e02a60b1a154e).
- It was used the [Koin](https://github.com/InsertKoinIO/koin) for handle dependency injection.

# Info
This app uses the Antonio Leiva's clean architecture based on [this](https://antonioleiva.com/clean-architecture-android) article.

# Details
This app has 5 modules, which are *domain, data, usecases, framework and app*, which will be described below:

- **Domain**: Holds the app's entities

- **Data**: Holds the different datasources for the project as well as their interfaces

- **Usecases**: Holds the user actions and interactions

- **Framework**: Is an Android module and implements mainly the dependencies that will be used at the other layers

- **App**: Another Android module that holds the UI, ViewModels and components for the application

# Tests
This app implements unit and instrumentation tests for the modules below:

- Data: Test the repositories and if the data is correct

- Usecases: Test the user actions

- Framework: Test for all dependencies that are used on the application (e.g. Room Persistence Database for caching)

- App: Contains instrumentation tests to test ViewModels and Activities as well as UI interactions with the application

# Observations
- This app persists locally the suggestions section (categories) and past searches

- At the first time on the app it will display a welcome text that guides the user to search facts

- The categories are fetched at the first running and stored locally for further use

- It was necessary to keep some cache (local persistence) interfaces at the data module to be used at the repositories, bute their real implementation still on the *framework* layer.

# Libraries
- [Room Persistence Database](https://developer.android.com/topic/libraries/architecture/room)
- [RxJava](https://github.com/ReactiveX/RxJava)
- [RxAndroid](https://github.com/ReactiveX/RxAndroid)
- [Koin](https://github.com/InsertKoinIO/koin)
- [Retrofit](https://square.github.io/retrofit/)
- [Timber](https://github.com/JakeWharton/timber)
- [Stheto](https://github.com/facebook/stetho)
- [Espresso](https://developer.android.com/training/testing/espresso)
- [Mockito](https://github.com/mockito/mockito)
- [JUnit4](https://github.com/junit-team/junit4)

# References
- [Android Arch Google Samples Repository](https://github.com/googlesamples/android-architecture)
- [Clean architecture for Android with Kotlin: a pragmatic approach for starters](https://antonioleiva.com/clean-architecture-android/)
- [Antonio Leiva Clean Architecture Repositories](https://github.com/antoniolg/clean-architecture)

