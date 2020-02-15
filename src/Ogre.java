public class Ogre extends Creature
{
    Ogre()
    {
        this(1);
    }

    Ogre(double difficultyScaling)
    {
        super();
        difficultyScaling = 1 + (difficultyScaling/20);
        name = "Ogre";
        description = "A huge, faceless creature.  Its club is bigger than you.";
        attackPower = (int) (40 * difficultyScaling);
        maxHealth = (int) (220 * difficultyScaling);
        currentHealth = maxHealth;
        turnFrequency = 250;
        //setting the current turn status to the frequency (which is the max currentTurnStatus for any Entity)
        currentTurnStatus = turnFrequency;
        luck = (int) (2 * difficultyScaling);
        goldValue = (int) (45 * difficultyScaling);
    }
}
