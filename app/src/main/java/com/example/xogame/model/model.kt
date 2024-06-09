package com.example.xogame.model

data object model{
    //board create
    data class Cell(var value: String = "")

    data class GameBoard(val size: Int, val cells: List<List<Cell>>) {
        init {
            require(cells.size == size)
            cells.forEach { require(it.size == size)}
        }
    }

    //play move
    data class Move(
        val player: String,
        val row: Int ,
        val col: Int
    )


    data class GameHistory(
        val gameId: String,
        val winner: String?,
        val boardSize: Int,
        val moves: List<Move>
    )

}