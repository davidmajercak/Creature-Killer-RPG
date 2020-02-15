import javafx.scene.image.Image;

public abstract class Entity
{
    protected int attackPower, maxHealth, currentHealth;
    protected int turnFrequency, currentTurnStatus, luck;
    protected String name;
    protected boolean isDefending;
    protected final double DEFENDING_MULTIPLIER = 0.6;

    Entity()
    {
        isDefending = false;
    }


    public void attack(Entity entity)
    {
        int attack = 0;
        int rollForCriticalHit = (int) (Math.random() * 100 + 1);

        if(entity.isDefending)
        {
            System.out.println(entity.getName() + " was defending! Reducing damage by: " + (100 * DEFENDING_MULTIPLIER) + "%");
            attack += generateAttackValue() * DEFENDING_MULTIPLIER;
            entity.isDefending = false;
        }
        else
            attack += generateAttackValue();

        System.out.println(name + " is attacking for: " + attack);

        if(this.getLuck() >= rollForCriticalHit)
        {
            System.out.println(name + " rolled for a critical hit! Attack damage was doubled!");
            attack *= 2;
            System.out.println(name + " is now attacking for: " + attack);
        }

        entity.currentHealth -= attack;

        System.out.println(entity.getName() + "'s health remaining: " + entity.getCurrentHealth());
    }

    public  void defend()
    {
        System.out.println(name + " is defending!");
        isDefending = true;
    }

    public boolean getIsDefending()
    {
        return isDefending;
    }

    public boolean getIsDead()
    {
        if(currentHealth <= 0)
            return true;
        else
            return false;
    }

    //Generates an attack value in a range from 0% of attackPower to 100% of attackPower
    public int generateAttackValue()
    {
        int generatedAttackValue = (int) Math.round(((Math.random() * (this.attackPower + 1))));
        return generatedAttackValue;
    }

    @Override
    public String toString()
    {
        return name + " : { Attack = " + attackPower +  ", Current Health = " + currentHealth + ", Max Health = " + maxHealth
                + ", Turn Frequency = " + turnFrequency + " }";
    }

    public int getAttackPower()
    {
        return attackPower;
    }

    public void resetTurnStatus()
    {
        currentTurnStatus = turnFrequency;
    }

    public int getMaxHealth()
    {
        return maxHealth;
    }

    public int getCurrentHealth()
    {
        return currentHealth;
    }

    public int getTurnFrequency()
    {
        return turnFrequency;
    }

    public int getCurrentTurnStatus()
    {
        return currentTurnStatus;
    }

    public int getLuck()
    {
        return luck;
    }

    public String getName()
    {
        return name;
    }

    public boolean getDefending()
    {
        return isDefending;
    }
}
