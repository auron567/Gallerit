# Gallerit
![](media/GalleritHeader.png)
<br><br>
<b>Gallerit</b> is a small demo application that presents a modern approach to Android development with up to date tech-stack. The goal of the project is to demonstrate best practices using modern Android development tools and presenting an architecture that is scalable, maintainable, and testable.
## Project characteristics
- 100% [Kotlin](https://kotlinlang.org/)
- Model-View-ViewModel
- Repository Pattern
- A single activity architecture
- [Android Jetpack](https://developer.android.com/jetpack)
- Reactive UI
- Testing
- Dependency Injection
- [Gradle Kotlin DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html)
- Material Design
- [GitHub Actions](https://github.com/features/actions)
## Libraries
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - For managing background threads.
- [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/-flow/) - A cold asynchronous data stream that sequentially emits values.
- [StateFlow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/-state-flow/) - A Flow that represents a state with a single updatable data value.
- Jetpack
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Store UI-related data that isn't destroyed on app rotations.
  - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Build data objects that notify views when the underlying database changes.
  - [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - Create a UI that automatically responds to lifecycle events.
  - [Navigation](https://developer.android.com/guide/navigation) - Handle everything needed for in-app navigation.
  - [Room](https://developer.android.com/topic/libraries/architecture/room) - Access your app's SQLite database with in-app objects and compile-time checks.
- [Retrofit2](https://square.github.io/retrofit/) - A type-safe HTTP client.
- [Glide](https://bumptech.github.io/glide/) - An image loading and caching library.
- [PhotoView](https://github.com/chrisbanes/PhotoView) - Implementation of ImageView that supports zooming.
- [Koin](https://insert-koin.io/) - A pragmatic lightweight dependency injection framework.
- [Timber](https://github.com/JakeWharton/timber) - A logger with a small, extensible API.
- [MockK](https://mockk.io/) - Mocking library for Kotlin.
- [Kotest](https://github.com/kotest/kotest) - Powerful, elegant and flexible test framework for Kotlin.
- [and more...](buildSrc/src/main/java/Dependencies.kt)
## Architecture
Gallerit is based on [MVVM](https://developer.android.com/jetpack/guide#recommended-app-arch) architecture, fetching data from the network and integrating persisted data in the database via repository pattern.
<br><br>
![](media/GalleritArchitecture.png)
## Reddit API
Gallerit uses the [Reddit API](https://www.reddit.com/dev/api/) to load images on the gallery screen.
## Getting started
There are a few ways to open this project.
### Android Studio
1. Android Studio -> File -> New -> Project from Version Control
2. Enter `https://github.com/auron567/Gallerit.git` into URL field
### Command line + Android Studio
1. Run `git clone https://github.com/auron567/Gallerit.git`
2. Android Studio -> File -> Open
## Contribute
If you want to contribute to this application, you're always welcome! You can contribute by filing issues, bugs and PRs.