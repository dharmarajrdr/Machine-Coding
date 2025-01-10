
import java.util.Random;

public class Dice {

    private int diceCount;

    public Dice(int diceCount) {
        this.diceCount = diceCount;
    }

    private int getRandomDiceValue(int diceCount) {
        if (diceCount == 0) {
            return 0;   // Base case
        }
        int randomDiceValue = new Random().nextInt(6) + 1;
        return randomDiceValue + getRandomDiceValue(diceCount - 1);     // Recursive call
    }

    public int rollDice() {
        return getRandomDiceValue(diceCount);
    }
}
