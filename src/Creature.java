public abstract class Creature extends Entity
{
    protected String description;
    protected int goldValue;

    Creature()
    {
        this(1);
    }

    Creature(double difficultyScaling)
    {
        super();
        //Setting difficulty scaling to a value that can be used as a direct multiplier to stats
        //This default value adds an extra 100% stats every 20
        difficultyScaling = 1 + (difficultyScaling/20);
        name = "Mystery Error Creature";
        description = "You're not sure how you encountered this.";
        attackPower = (int) (1 * difficultyScaling);
        maxHealth = (int) (1 * difficultyScaling);
        currentHealth = maxHealth;
        //turnFrequency does not scale
        turnFrequency = 100;
        //setting the current turn status to the frequency (which is the max currentTurnStatus for any Entity)
        currentTurnStatus = turnFrequency;
        luck = (int) (1 * difficultyScaling);
        goldValue = (int) (1 * difficultyScaling);
    }

    public int getGoldValue()
    {
        return goldValue;
    }

    public String getDescription()
    {
        return description;
    }

    //This method gets the turn choice for a creature
    //The default values here are
    //90% chance to attack - returns 1
    //10% chance to defend
    public int getTurnChoice()
    {
        int weightedChoice = (int) (Math.random() * 100 + 1);

        if (weightedChoice > 90)
            return 2;
        else
            return 1;
    }

    @Override
    public String toString()
    {
        return name + " : { Attack = " + attackPower +  "\t\t Current Health = " + currentHealth + "\t\t Max Health = " + maxHealth
                + "\t\t\n\t\t\t\t\t\t Gold Value = " + goldValue + "\t\t Turn Frequency = " + turnFrequency + " }";
    }
}
