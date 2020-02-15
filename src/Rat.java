public class Rat extends Creature
{
    Rat()
    {
        this(1);
    }

    Rat(double difficultyScaling)
    {
        super();
        difficultyScaling = 1 + (difficultyScaling/20);
        name = "Rat";
        description = "A small, dirty creature. It's not very strong, but it's very fast.";
        attackPower = (int) (2 * difficultyScaling);
        maxHealth = (int) (33 * difficultyScaling);
        currentHealth = maxHealth;
        turnFrequency = 33;
        //setting the current turn status to the frequency (which is the max currentTurnStatus for any Entity)
        currentTurnStatus = turnFrequency;
        luck = (int) (3 * difficultyScaling);
        goldValue = (int) (5 * difficultyScaling);
    }
}
