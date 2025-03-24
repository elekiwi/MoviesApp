# MoviesApp
Movies app to keep track of already seen movies.

## Functionalities

- **Add movies** to the app.
- List **all** movies.
- List **seen** movies. 
- List **to see** movies.
- **Detailed screen** to see movie info.
- **Rate** the movie.
- **Add your opinion** about the movie..
- **Edit** the movies.

## Instalation

##### 1️⃣ Clone the repository
```bash
git clone https://github.com/elekiwi/MoviesApp.git
```
##### 2️⃣ Open the project in Android Studio.

##### 3️⃣ Sync the project with Gradle.
##### 4️⃣ Run the app on an emulator or physical device.

## Tech stack

- **Minimum SDK:** 24.  
- Made in [Kotlin](https://kotlinlang.org/), using [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asyncronous operations.  

- **Jetpack Compose:** Modern Android Toolkit for Declarative UI Development.  
- **Lifecycle:** Observe Android lifecycles and manage UI states upon lifecycle changes.  
- **ViewModel:** Manages UI-related data and is lifecycle-aware, ensuring data persistence across configuration changes. 
- **Navigation:** Facilitates navigation between screens, complemented with [Hilt Navigation Compose](https://developer.android.com/jetpack/compose/libraries#hilt) for dependency injection.  
- **Room:** It allows you to build a database with an abstraction layer on top of SQLite for efficient data access.  
- **[Hilt](https://dagger.dev/hilt/):** Simplifies dependency injection into your application.
- **MVVM Architecture (View - ViewModel - Model):** Promotes separation of responsibilities and improves code maintenance.
- **Repository pattern:** It acts as a mediator between different data sources and the application's business logic.  
- **[Kotlin Serialization](https://github.com/Kotlin/kotlinx.serialization):** Reflection-free serialization for multiple platforms and formats in Kotlin.
- **[ksp](https://github.com/google/ksp):** Kotlin symbol processing API for code generation and analysis.

## Architecture
**MoviesApp** is structured using MVVM and Clean Architecture, which facilitates separation of concerns and improves code testability.

**data:** Contains the data models and access to local and remote data.

  * **local:** Contains the data models and the implementation of the local database (Room).
  * **remote:** Contains the services to access Firebase and get the data.
  * **repository**: Follows the "single source of truth" principle, ensuring that all data operations are centralized and consistent and manages data persistence and communication with external sources.
    
**domain:** Contains the use cases that encapsulate the business logic.

  * **use case:** for specifying the requirements of a system.

**presentation:** Contains the application screens, their respective ViewModels and the states.
