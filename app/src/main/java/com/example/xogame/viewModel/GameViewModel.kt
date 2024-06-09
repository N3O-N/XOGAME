package com.example.xogame.viewModel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.annotation.Size
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.xogame.model.model
import com.google.firebase.Firebase
import com.google.firebase.database.database
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


class GameViewModel : ViewModel() {
    //db config
    val db = Firebase.firestore
    val gameHistoryCollection = db.collection("Game_History")

    val _board = MutableLiveData<model.GameBoard>()
    val _currentPlayer = MutableLiveData("X")
    val _winner = MutableLiveData<String?>(null)
    val _move = mutableListOf<model.Move>()

    val board: LiveData<model.GameBoard> get() = _board
    val currentPlayer: LiveData<String> get() = _currentPlayer
    val winner: LiveData<String?> get() = _winner

    fun createGameBoard(size: Int): List<List<model.Cell>> {
        return List(size) { List(size) { model.Cell() } }
    }

    fun setBoardSize(size: Int) {
        val cells = createGameBoard(size)
        _board.value = model.GameBoard(size, cells)
    }

    fun playerMove(row: Int , col: Int ,size: Int) {
        val currentBoard = if (_board.value == null)  return  else _board.value
        //เช็คว่ายังเล่นต่อได้ไหม
        if (currentBoard!!.cells[row][col].value.isNotEmpty() || _winner.value != null) return

        currentBoard.cells[row][col].value = _currentPlayer.value!!
        _board.value = currentBoard
        _move.add(model.Move(_currentPlayer.value!!,row,col))

        if (checkWinner(currentBoard)) {
            _winner.value = _currentPlayer.value
            GenerateGamePlay(_move.toList(),size)
        }else if (currentBoard.cells.all { row -> row.all { cell -> cell.value.isNotEmpty() } }) {
            _winner.value = "Draw"
            GenerateGamePlay(_move.toList(),size)
        }else _currentPlayer.value = if (_currentPlayer.value == "X") "O" else "X"
    }

    fun checkWinner(board: model.GameBoard): Boolean {

        val size = board.size
        val winCondition = if (size == 3) 3 else 4

        for (i in 0 until size) {
            // นอน
            for (j in 0..(size - winCondition)) {
                if ((j until j + winCondition).all {
                        board.cells[i][it].value == board.cells[i][j].value && board.cells[i][it].value.isNotEmpty()
                    }) {
                    return true
                }
            }

            // ตั้ง
            for (j in 0..(size - winCondition)) {
                if ((j until j + winCondition).all {
                        board.cells[it][i].value == board.cells[j][i].value && board.cells[it][i].value.isNotEmpty()
                    }) {
                    return true
                }
            }
        }

        // แทยงซ้ายลงขวา
        for (i in 0..(size - winCondition)) {
            for (j in 0..(size - winCondition)) {
                if ((0 until winCondition).all {
                        board.cells[i + it][j + it].value == board.cells[i][j].value && board.cells[i + it][j + it].value.isNotEmpty()
                    }) {
                    return true
                }
            }
        }

        // ทแยงขวาลงซ้าย
        for (i in 0..(size - winCondition)) {
            for (j in winCondition - 1 until size) {
                if ((0 until winCondition).all {
                        board.cells[i + it][j - it].value == board.cells[i][j].value && board.cells[i + it][j - it].value.isNotEmpty()
                    }) {
                    return true
                }
            }
        }

        return false
    }

    fun regame(size: Int){
        _board.value = model.GameBoard(size, createGameBoard(size))
        _winner.value = null
        _currentPlayer.value = "X"
        _move.clear()
    }

    fun GenerateGamePlay(move: List<model.Move> ,boardSize: Int){

        gameHistoryCollection.get().addOnSuccessListener {
                result ->  val existingGameIds = result.documents.map { documentSnapshot -> documentSnapshot.id }.toList()
            var IdNumber = 1
            var gameId: String

            do {
                gameId = "gameId$IdNumber"
                IdNumber++
            }while (existingGameIds.contains(gameId))

            saveGamePlay(gameId,boardSize,move)


        }.addOnFailureListener {  }
    }

    fun saveGamePlay(id: String ,boardSize: Int,move: List<model.Move> ,){

        val gameHistory = model.GameHistory(
            gameId = id,
            winner = _winner.value,
            boardSize = boardSize,
            moves = move
        )

        gameHistoryCollection.document(id).set(gameHistory)
            .addOnSuccessListener { println("เขียนข้อมูลสำเร็จ") }
            .addOnFailureListener { e -> println("เขียนข้อมูลล้มเหลว: ${e.message}") }

    }

    fun fetchGameHistories(onComplete: (List<model.GameHistory>) -> Unit) {
        gameHistoryCollection
            .get()
            .addOnSuccessListener { result ->
                val gameHistories = result.map { document ->
                    val gameId = document.getString("gameId") ?: ""
                    val winner = document.getString("winner")
                    val boardSize = document.getLong("boardSize")?.toInt() ?: 0
                    val moves = document.get("moves") as List<Map<String, Any>>

                    val moveList = moves.map { moveData ->
                        model.Move(
                            player = moveData["player"] as String,
                            row = (moveData["row"] as Long).toInt(),
                            col = (moveData["col"] as Long).toInt()
                        )
                    }

                    model.GameHistory(gameId, winner, boardSize, moveList)
                }
                onComplete(gameHistories)
            }
            .addOnFailureListener { e ->
                println("ดึงข้อมูลล้มเหลว: ${e.message}")
                onComplete(emptyList())
            }
    }

}