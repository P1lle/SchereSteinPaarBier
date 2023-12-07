import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Setze den OnClickListener für die Schaltflächen
        buttonRock.setOnClickListener(this)
        buttonPaper.setOnClickListener(this)
        buttonScissors.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.buttonRock -> playGame(Choice.ROCK)
            R.id.buttonPaper -> playGame(Choice.PAPER)
            R.id.buttonScissors -> playGame(Choice.SCISSORS)
        }
    }

    private fun playGame(playerChoice: Choice) {
        val computerChoice = generateComputerChoice()
        val result = determineWinner(playerChoice, computerChoice)

        displayChoices(playerChoice, computerChoice)
        displayResult(result)
    }

    private fun generateComputerChoice(): Choice {
        val random = Random()
        val values = Choice.entries
        return values[random.nextInt(values.size)]
    }

    private fun determineWinner(playerChoice: Choice, computerChoice: Choice): Result {
        return when {
            playerChoice == computerChoice -> Result.DRAW
            (playerChoice == Choice.ROCK && computerChoice == Choice.SCISSORS) ||
                    (playerChoice == Choice.PAPER && computerChoice == Choice.ROCK) ||
                    (playerChoice == Choice.SCISSORS && computerChoice == Choice.PAPER) ->
                Result.WIN
            else -> Result.LOSE
        }
    }

    private fun displayChoices(playerChoice: Choice, computerChoice: Choice) {
        textViewPlayerChoice.text = "Deine Wahl: ${playerChoice.name}"
        textViewComputerChoice.text = "Computer Wahl: ${computerChoice.name}"
    }

    private fun displayResult(result: Result) {
        val message = when (result) {
            Result.WIN -> "Du gewinnst!"
            Result.LOSE -> "Du verlierst!"
            Result.DRAW -> "Unentschieden!"
        }

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    enum class Choice {
        ROCK, PAPER, SCISSORS
    }

    enum class Result {
        WIN, LOSE, DRAW
    }
}
