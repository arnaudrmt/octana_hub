# OctanaHub 🚀

<br/>

**OctanaHub** serves as the front-end interface for OctanaAPI, providing players with a seamless experience and access to all the functionalities of the API. It acts as the central hub for displaying OctanaAPI functions, including join messages, scoreboards, menus, and more. OctanaHub integrates both the OctanaAPI and BungeeAPI to offer a comprehensive and immersive gameplay experience.

<br/>

## Key Features 🌟📊

- **Front-End Interface**: OctanaHub serves as the front-end interface for OctanaAPI, allowing players to interact with and utilize its various functionalities.

- **Join Messages**: OctanaHub displays custom join messages to players, retrieving the rank of each player from OctanaAPI.

- **Scoreboards**: The scoreboard in OctanaHub showcases essential information such as ranks and coins, providing players with a quick overview of their progress and achievements.

- **Menu System**: OctanaHub's menu system integrates seamlessly with OctanaAPI, offering players a wide range of game options and displaying game stats. It acts as a central hub for navigating and accessing various game functionalities.

- **BungeeAPI Integration**: OctanaHub leverages the BungeeAPI to create servers on demand. When a player requests to join a game from the OctanaHub menu, a plugin message is sent to the BungeeAPI. The BungeeAPI maintains a list of players and starts a Docker container with the requested game once enough players have joined, teleporting everyone to the game instance.

- **NPCs**: OctanaHub incorporates custom NPCs created with NMS to enrich player interaction. These NPCs enable players to request to join a game, and their requests are seamlessly sent to OctanaAPI via Redis Plugin Message. This crucial integration ensures smooth communication and streamlined gameplay.

<br/>

## Usage 📝

OctanaHub is designed to work seamlessly with OctanaAPI and BungeeAPI. By integrating these two powerful back-end systems, OctanaHub offers players a smooth and immersive front-end experience. Players can enjoy the various functionalities and features provided by OctanaAPI, including join messages, scoreboards, and game menus.

Please note that OctanaHub is specifically designed to work with the OctanaAPI and BungeeAPI and may require additional configuration and setup to ensure proper functionality within your Minecraft server environment.

<br/>

## Contributing 🤝

As OctanaHub is an integral part of the OctanaAPI and BungeeAPI ecosystem, contributions are not open to the public at this time. The repository serves as an example of how the OctanaHub can be used as a front-end interface for the back-end functionalities of the OctanaAPI and BungeeAPI.

<br/>

## Contact ✉️

If you have any questions or would like to discuss the project, please feel free to contact the project developer at [arnaud.romatet@yahoo.fr](mailto:arnaud.romatet@yahoo.fr).

Thank you for your interest in OctanaHub and for utilizing its powerful front-end interface to enhance your Minecraft server experience!
