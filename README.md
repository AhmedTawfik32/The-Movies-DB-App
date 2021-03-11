# The Movies App
A simple Android application based on Kotlin MVVM architecture for showing movies from the The Movie Database ([TMDb](https://developers.themoviedb.org/3/getting-started/introduction)) API.

# Android Architecture Components
Android architecture components are a collection of libraries that help you design robust, testable, and maintainable apps. 
Start with classes for managing your UI component lifecycle and handling data persistence.
The following diagram, which shows how all the modules should interact with one another after designing the app:

![](https://github.com/AhmedTawfik32/The-Movies-DB-App/blob/master/Attachments/arccomponents.png)

## The Architecture Components of The Movies App
* Lifecycle components help to manage the activity and fragment lifecycles, survive configuration changes, avoid memory leaks and easily load data into your UI.
* LiveData to build data objects that notify views when the underlying database changes.
* ViewModel to store the UI-related data that isn't destroyed on app rotations.
* Room is a SQLite object mapping library used to avoid boilerplate code and easily convert SQLite table data to Java objects. Room provides compile time checks of SQLite statements and can return RxJava, Flowable and LiveData observables.
* Navigation to handle everything needed for in-app navigation.
* Repository modules handle data operations. They provide a clean API so that the rest of the app can retrieve this data easily.
* Dependency injection (DI): Koin is used as a dependency injection yo allow classes to define their dependencies without constructing them. At runtime, another class is responsible for providing these dependencies.
* Kotlin coroutine is a concurrency design pattern that you can use on Android to simplify code that executes asynchronously.

# Screens of The Movies App
![](https://github.com/AhmedTawfik32/The-Movies-DB-App/blob/master/Attachments/01.jpeg)![](https://github.com/AhmedTawfik32/The-Movies-DB-App/blob/master/Attachments/02.jpeg)![](https://github.com/AhmedTawfik32/The-Movies-DB-App/blob/master/Attachments/03.jpeg)
![](https://github.com/AhmedTawfik32/The-Movies-DB-App/blob/master/Attachments/04.jpeg)![](https://github.com/AhmedTawfik32/The-Movies-DB-App/blob/master/Attachments/05.jpeg) ![](https://github.com/AhmedTawfik32/The-Movies-DB-App/blob/master/Attachments/06.jpeg)
![](https://github.com/AhmedTawfik32/The-Movies-DB-App/blob/master/Attachments/07.jpeg)![](https://github.com/AhmedTawfik32/The-Movies-DB-App/blob/master/Attachments/08.jpeg) ![](https://github.com/AhmedTawfik32/The-Movies-DB-App/blob/master/Attachments/09.jpeg)
![](https://github.com/AhmedTawfik32/The-Movies-DB-App/blob/master/Attachments/10.jpeg)

# The Movies App (APK)
[Download the APK](https://github.com/AhmedTawfik32/The-Movies-DB-App/blob/master/Attachments/The%20Movies%20DB%20V1.0.0.apk)
