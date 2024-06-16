# Arkanoid Game

This project is a Java implementation of the classic Arkanoid game. The game includes different levels, each with its unique layout and difficulty. The player controls a paddle at the bottom of the screen and must bounce a ball off the paddle to hit and remove a variety of blocks at the top of the screen. The goal is to remove all the blocks without letting the ball fall off the bottom of the screen.

## Project Structure

The project is organized into several packages, each containing classes related to a specific aspect of the game:

- `Sprites`: This package contains classes for different game objects like the paddle and score indicator.
- `Interfaces`: This package contains various interfaces used throughout the game.
- `SetGame`: This package contains classes for managing the game environment and game flow.
- `Levels`: This package contains classes for different levels in the game.
- `Geometry`: This package contains classes for geometric shapes and operations used in the game.

The main class of the game is `Ass6Game.java`, which initializes the game and starts the game flow.

## Key Classes

- `Paddle.java`: Represents the player-controlled paddle.
- `ScoreIndicator.java`: Displays the current score in the game.
- `GameEnvironment.java`: Manages the game environment, including collision detection.
- `Ass6Game.java`: The main class that runs the game.

## Running the Game

The game can be run by executing the `main` method in the `Ass6Game` class. The `main` method accepts an array of integers as command-line arguments, each integer representing a level to be played. If no arguments are provided, all levels will be played in order.

## Building the Project

This project uses Ant as its build system. To build the project, navigate to the project directory and run the `ant` command.

Please note that this project requires Java to be installed on your machine.
