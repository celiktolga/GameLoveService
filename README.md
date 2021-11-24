# Game Love Service

In this assignment, we look forward to test your skills in software development. Please, be pragmatic and avoid over engineering your solution.

## Problem Statement:

We want you to implement a game love service that keeps track of the games that the player loves.

* Design a REST API with the following:
    * It should be possible to create a new entry by feeding it with the following:
        * The player that loved the game.
        * The game it loved.
    * It should be possible to unlove games.
    * It should be possible to fetch all games a player have loved.
    * It should be possible to fetch the most loved games.
        * The list should contain the x top loved games, where x should be possible to define in every request.
        * Each item in this list should contain:
            * The game identifier.
            * Number of loves the game has.


## How to
Api controllers can be accessed under `http://localhost:8080/swagger-ui/index.html` and look for `game-controller` and `player-controller`.
Add player and game , accordingly a player can add a loved games or unlove the game which belongs to his loved games.

 