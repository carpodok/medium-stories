# MediumArticles

<h1> <img src="https://media.giphy.com/media/XGbRD1gREhvBysGial/giphy.gif"  height="30" > PokeApp </h2>


Poke App is designed to help you explore information about various Pokémon using the popular <a href="https://pokeapi.co/" target="_blank">PokeAPI </a>  The app is built
using Kotlin and incorporates several modern Android development tools and libraries. This document will provide an 
overview of the app's features, architecture, libraries used, and how to get started with the project.

<br><br>

<p align="center"> 
	<img src="https://github-production-user-asset-6210df.s3.amazonaws.com/64840495/261874580-786d3139-ad0e-4e7a-9c14-d80a3b72588d.gif" alt="pokeapp" width=100px/> 
</p>

<br>

<h2> <img src="https://media.giphy.com/media/daUNvsWuU3s8WeLnq3/giphy.gif"  height="30" > Features </h2>
The Poke App comes with the following features:
<br>
:small_red_triangle: MVVM Architecture: The app follows the Model-View-ViewModel architectural pattern, which promotes separation of concerns and maintainability.

:small_red_triangle: Retrofit: Retrofit is used to handle network requests and API calls to fetch Pokémon data from the pokeapi.co API.

:small_red_triangle: Coroutines Flow: Coroutines and Flow are utilized to perform asynchronous operations in a structured and efficient manner, ensuring smooth user interactions and background data fetching.

:small_red_triangle: Gson: Gson is used for JSON serialization and deserialization, allowing seamless conversion between JSON responses from the API and Kotlin data models.

:small_red_triangle: Hilt: Hilt is used for dependency injection, making it easier to manage and provide dependencies to different parts of the application.

:small_red_triangle: Navigation: The Navigation component is used to handle in-app navigation, making it straightforward to navigate between different screens and manage the back stack.

<br>

<h2> <img src="https://media.giphy.com/media/XGbRD1gREhvBysGial/giphy.gif"  height="30" > PokeAPI </h2>
<a href="https://pokeapi.co/" target="_blank">PokeAPI </a> is a hub of Pokémon information, meticulously crafted by a community of enthusiasts. 
This online resource offers a wealth of data on Pokémon species, abilities, moves, types, and more. It's a go-to platform for fans, researchers, and developers looking to delve into the intricacies
of the Pokémon world. With its user-friendly interface, PokeAPI is the ultimate source for all things Pokémon.

<br>

<h2> <img src="https://media.giphy.com/media/QvpqIQAAl66EfoTJj8/giphy.gif"  height="30" > Getting Start </h2>

To set up MovieMach on your local machine, follow these steps:

1️⃣  Clone this repository to your preferred directory using the following command:
```
git clone https://github.com/carpodok/MovieMatch
```

2️⃣ Open the cloned project in Android Studio.

3️⃣ Build and run the app on an emulator or a physical device.
