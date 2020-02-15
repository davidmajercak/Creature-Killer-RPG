
import java.util.Scanner;

public class Player extends Entity
{
    protected int gold;

    //I am balancing the values for different parameters based on Player
    //Player has 100 base for all stats except luck because it is used as a percentage
    Player()
    {
        this("The man with no name");
    }

    Player (String name)
    {
        super();
        if(name != "")
            this.name = name;
        else
            this.name = "The man with no name";
        attackPower = 100;
        maxHealth = 100;
        currentHealth = maxHealth;
        turnFrequency = 100;
        //setting the current turn status to the frequency (which is the max currentTurnStatus for any Entity)
        currentTurnStatus = turnFrequency;
        //Player has pretty high luck.  Should have a critical hit 1/5 of the time
        luck = 20;
        gold = 25;
    }


    public void addGold(int gold)
    {
        this.gold += gold;
    }

    public int getGold() { return gold; }



    @Override
    public String toString()
    {
        return name + " : { Attack = " + attackPower + "\t\t Current Health = " + currentHealth +  "\t\t Max Health = " + maxHealth
                + "\t\t\n\t\t\t\t\t\t Gold = " + gold + "\t\t Turn Frequency = " + turnFrequency + " }";
    }


    //Drinks a potion
    //Sets current health back to maximum health
    //Reduces gold by 100
    //Does nothing if player does not have at least 100 gold
    public void drinkPotion()
    {
        if(gold >= 100)
        {
            currentHealth = maxHealth;
            gold -= 100;
            System.out.println("Drank a potion! Health is set back to maximum! (" + maxHealth + ")");
        }
        else
        {
            System.out.println("You don't have enough gold to use a potion!");
            System.out.println("You wasted a turn!");
        }
    }

}
