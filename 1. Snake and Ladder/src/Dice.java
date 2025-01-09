
public class Dice {

    private int diceCount;

    public Dice(int diceCount) {
        this.diceCount = diceCount;
    }

    public int rollDice() {
        int maxValue = (6 * diceCount) - (1 * diceCount);
        double randomValue = Math.random();
        int diceResult = (int) (randomValue * maxValue) + 1;
        return diceResult;
    }
}
