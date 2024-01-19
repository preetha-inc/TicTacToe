package com.example.tictactoe

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var playerOneScore: TextView
    private lateinit var playerTwoScore: TextView
    private lateinit var playerStatus: TextView
    private lateinit var reset: Button
    private lateinit var playAgain: Button
    private var gameState: Array<Int> =arrayOf(-1, -1, -1, -1, -1, -1, -1, -1, -1)
    private val winningPositions: Array<Array<Int>> = arrayOf(arrayOf(0, 1, 2), arrayOf(3, 4, 5), arrayOf(6, 7, 8),
                                                              arrayOf(0, 3, 6), arrayOf(1, 4, 7), arrayOf(2, 5, 8),
                                                              arrayOf(0, 4, 8), arrayOf(2, 4, 6))
    private var rounds=0
    private var playerOneScoreCount=0
    private var playerTwoScoreCount=0
    private var playerOneActive= true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playerOneScore=findViewById(R.id.tvPlayer1score)
        playerTwoScore=findViewById(R.id.tvPlayer2score)
        playerStatus=findViewById(R.id.tvStatus)
        reset=findViewById(R.id.btnResetGame)
        playAgain=findViewById(R.id.btnPlayAgain)

        val buttons= Array<Button>(9){Button(this)}

        buttons[0]=findViewById(R.id.btn0)
        buttons[1]=findViewById(R.id.btn1)
        buttons[2]=findViewById(R.id.btn2)
        buttons[3]=findViewById(R.id.btn3)
        buttons[4]=findViewById(R.id.btn4)
        buttons[5]=findViewById(R.id.btn5)
        buttons[6]=findViewById(R.id.btn6)
        buttons[7]=findViewById(R.id.btn7)
        buttons[8]=findViewById(R.id.btn8)

        for(i in buttons.indices){
            buttons[i].setOnClickListener(this)
        }

        rounds=0
        playerOneScoreCount=0
        playerTwoScoreCount=0
        playerOneActive= true

        reset.setOnClickListener{
//            playAgainFunction()
            rounds=0
            playerOneActive = true
            for(i in buttons.indices){
                gameState[i]=-1
                buttons[i].text=""
            }

            playerStatus.text="Status bar"
            playerOneScoreCount=0
            playerOneScore.text=playerOneScoreCount.toString()
            playerTwoScoreCount=0
            playerTwoScore.text=playerTwoScoreCount.toString()
        }

        playAgain.setOnClickListener{
//            playAgainFunction()
            rounds=0
            playerOneActive = true
            for(i in buttons.indices){
                gameState[i]=-1
                buttons[i].text=""
            }
            playerStatus.text="Status bar"
        }

    }

    override fun onClick(view: View?) {
        if((view as Button).text.toString()!=""){
            return
        }
        else if(checkWinner()){
            return
        }
        val buttonID:String = resources.getResourceName(view.id)
        val gameStatePointer= buttonID?.substring(buttonID.length - 1, buttonID.length)?.toInt()
        println("buttonID is $buttonID")
        println("gameStatePointer = $gameStatePointer")

        if(playerOneActive){
            view.text = "x"
            view.setTextColor(Color.parseColor("#DA4343"))
            gameState[gameStatePointer!!]=0
        }
        else{
            view.text = "o"
            view.setTextColor(Color.parseColor("#8BC34A"))
            gameState[gameStatePointer!!]=1
        }

        rounds++

        if(checkWinner()){
            if(playerOneActive){
                playerOneScoreCount++
                playerOneScore.text=playerOneScoreCount.toString()
                playerStatus.text= "Player 1 has won"
            }
            else{
                playerTwoScoreCount++
                playerTwoScore.text=playerTwoScoreCount.toString()
                playerStatus.text= "Player 2 has won"
            }
        }
        else if(rounds==9){
            playerStatus.text= "It's a draw"
        }
        else{
            playerOneActive = !playerOneActive
        }

    }

    private fun checkWinner(): Boolean {
        var winnerResults = false
        for(it in winningPositions){
            if(gameState[it[0]] == gameState[it[1]] &&
                gameState[it[1]]==gameState[it[2]] &&
                gameState[it[0]]!=-1
                ){
                winnerResults = true
            }
        }
        return winnerResults
    }
}


