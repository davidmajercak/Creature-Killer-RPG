public class Zombie extends Creature
{
    Zombie()
    {
        this(1);
    }

    Zombie(double difficultyScaling)
    {
        super();
        difficultyScaling = 1 + (difficultyScaling/20);
        name = "Zombie";
        description = "A rotten, bloodless creature. It's slow but it hits hard for its size.";
        attackPower = (int) (25 * difficultyScaling);
        maxHealth = (int) (130 * difficultyScaling);
        currentHealth = maxHealth;
        turnFrequency = 200;
        //setting the current turn status to the frequency (which is the max currentTurnStatus for any Entity)
        currentTurnStatus = turnFrequency;
        luck = (int) (3 * difficultyScaling);
        goldValue = (int) (12 * difficultyScaling);
    }
}
