//Game class represents the turn based RPG game
//The game class has a Player object and a Creature object
//TakeNextMove() is the most important function in Game
//TakeNextMove() calls the other function in Game as well as the other classes to create the game flow
//       It finds out the next actor, gets their action, then carries out those actions
public class Game
{
    private int creatureTurnChoice;
    private Player player;
    private int playerTurnChoice;
    private Creature creature;
    private Entity lastAttacker = null;
    private int playerTurnCounter, creaturesDefeated, lastAttackerCombo = 1;
    private Boolean gameOver = false;
    private String recentTurnsText = "Player Turn #1\n";

    //New constructor for GUI version of the game
    //It is no longer necessary to use a Scanner
    Game(String name)
    {
        player = new Player(name);
        setNewCreature();
    }

    public Player getPlayer()
    {
        return player;
    }

    public Creature getCreature()
    {
        return creature;
    }

    //This function generates a random new creature and makes that the new creature for the player to fight
    public void setNewCreature()
    {

        int randNum = (int) (Math.random() * 3);

        if(randNum == 0)
            creature = new Rat(creaturesDefeated);
        else if(randNum == 1)
            creature = new Zombie(creaturesDefeated);
        else if(randNum == 2)
            creature = new Ogre(creaturesDefeated);

        System.out.println("A new creature has been summoned for you to fight!");
        System.out.println("It's a(n) " + creature.getName() + "!\t\t\tDescription:  " + creature.getDescription() + "\n");

        recentTurnsText += "A new creature has been summoned for you to fight!\n";
    }

    public int getPlayerTurnCounter() { return playerTurnCounter; }

    public void incrementTurnCounter() { playerTurnCounter++; }

    public int getCreaturesDefeated() { return creaturesDefeated; }

    public void printGameState()
    {
        System.out.println("Player Turn #" + playerTurnCounter + " ===================================================================================================");
        System.out.println("Player stats: " + player);
        System.out.println("Creature stats: " + creature);
        System.out.println("\n");

        recentTurnsText += ("Player Turn #" + (playerTurnCounter + 1) + " \n");
    }

    //Gets the Actor who will take the next turn
    public Entity getNextActor()
    {
        if(player.currentTurnStatus <= creature.currentTurnStatus)
            return player;
        else
            return creature;
    }

    //This function takes in the entity that takes an action next
    //It subtracts the next Actor's current turn status from the other Actor's current turn status
    //Then, resets the turn status of the next Actor
    public void updateCurrentTurnStatus(Entity nextEntity)
    {
        //Checking if nextEntity is a Player object
        if(nextEntity instanceof Player)
        {
            creature.currentTurnStatus -= player.currentTurnStatus;
            player.resetTurnStatus();
        }
        //Else it is a creature of some type
        else
        {
            player.currentTurnStatus -= creature.currentTurnStatus;
            creature.resetTurnStatus();
        }
    }


    //Finds the number of times an entity attacks consecutively (without the enemy attacking at all)
    public void calculateComboAttacks()
    {
        if(getNextActor() == lastAttacker)
        {
            lastAttackerCombo++;
        }
        else
            lastAttackerCombo = 1;
        lastAttacker = getNextActor();
    }

    //Prints out a different message depending on the number of times an entity attacks consecutively
    public void printComboAttacks()
    {
        if(lastAttackerCombo >= 2)
        {
            System.out.print(lastAttacker.name);
            recentTurnsText += (lastAttacker.name);

            if(lastAttackerCombo == 2)
            {
                System.out.println(" just had a DOUBLE ATTACK");
                recentTurnsText += " just had a DOUBLE ATTACK\n";
            }
            else if(lastAttackerCombo == 3)
            {
                System.out.println(" just had a TRIPLE ATTACK, dang!");
                recentTurnsText += " just had a TRIPLE ATTACK, dang!\n";
            }
            else if(lastAttackerCombo == 4)
            {
                System.out.println(" just had a QUADRUPLE ATTACK, wow!");
                recentTurnsText += " just had a QUADRUPLE ATTACK, wow!\n";
            }
            else if(lastAttackerCombo == 5)
            {
                System.out.println(" just had a QUINTUPLE ATTACK, holy!");
                recentTurnsText += " just had a QUINTUPLE ATTACK, holy!\n";
            }
            else if(lastAttackerCombo == 6)
            {
                System.out.println(" just had a SEXTUPLE ATTACK, you're screwed!");
                recentTurnsText += " just had a SEXTUPLE ATTACK, you're screwed!\n";
            }
            else
            {
                System.out.println(" just attacked " + lastAttackerCombo + " times in a row!");
                recentTurnsText += " just attacked " + lastAttackerCombo + " times in a row!\n";
            }
        }
    }


    //Returns gameOver
    //If gameOver == true then the game ends (either user quit or player health <= 0)
    public boolean isGameOver()
    {
        return gameOver;
    }

    //Prints some descriptions of game mechanics to the console
    //This is no longer used in the current version
    //The handbook text was copied to Main and now outputs to a text area in a new windows
    //Reading the help handbook no longer uses a turn
    public void printHelpInfo()
    {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Help Handbook~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("You don't know why you're here, but you know you must kill as many creatures as possible");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Stats~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Attack - How hard you can hit.  All hits range from 0 up to that entity's attack power.");
        System.out.println("Current Health - If this hits 0, that entity dies.  If your health reaches 0 it's game over.");
        System.out.println("Max Health - The maximum amount of health an entity can have.  You start at max health.");
        System.out.println("Gold - How much gold you have.  It costs 100 gold to use a potion. If you try to use a potion without enough gold you lose your turn.");
        System.out.println("Gold Value - How much gold you earn from killing a creature");
        System.out.println("Turn Frequency - How often an entity takes an action.  The higher the turn frequency slower you are.");
        System.out.println("\t\tThe player has a turn frequency of 100. An entity with a turn frequency 1/2 that of the other entity will attack twice as often");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Tips~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Each creature you kill makes the game harder.  For every 20 creatures you kill new creatures have +100% stats(except for turn frequency)");
        System.out.println("...");
        System.out.println("...");
        System.out.println("...");
        System.out.println("You don't have time to be reading a handbook right now! You're getting attacked by a(n) " + creature.name + "!");
        System.out.println("You wasted 1 turn reading the Help Handbook");
        System.out.println("\n\n");
    }

    public void creatureTurn()
    {
        calculateComboAttacks();
        //Updating the current turn status based on creature having the lower currentTurnStatus
        updateCurrentTurnStatus(creature);
        //nextInput is creature's this time
        creatureTurnChoice = creature.getTurnChoice();

        System.out.println(":::CREATURE TURN:::");


        //Creature Attacking
        if(creatureTurnChoice == 1)
        {
            recentTurnsText += creature.name + " attacks you\n";

            if(player.isDefending)
                recentTurnsText += player.name + " reduced damage taken by 40% by defending\n";
            creature.attack(player);
            printComboAttacks();

            if(player.getIsDead())
            {

                System.out.println("You Died!");
                System.out.println("You were killed by " + creature.name);

                recentTurnsText += ("You Died!\n");
                recentTurnsText += ("You were killed by " + creature.name + "\n");
                gameOver = true;
            }
            return;
        }
        //Creature Defending
        else if(creatureTurnChoice == 2)
        {
            recentTurnsText += creature.name + " is now defending\n";
            creature.defend();
            return;
        }
    }

    public void playerTurn(int playerTurnChoice)
    {
        this.playerTurnChoice = playerTurnChoice;
        calculateComboAttacks();
        //Creature taking turn if theirs comes first
        while(!(getNextActor() instanceof Player))
            creatureTurn();

        //Player taking turn now
        //We only print the game state on player turns
        playerTurnCounter++;
        printGameState();
        updateCurrentTurnStatus(player);

        System.out.println(":::PLAYER TURN:::");


        //Player attacks enemy
        if(playerTurnChoice == 1)
        {
            recentTurnsText += "You attack " + creature.name + "\n";

            if(creature.isDefending)
                recentTurnsText += creature.name + " reduced damage taken by 40% by defending\n";
            player.attack(creature);
            printComboAttacks();

            if(creature.getIsDead())
            {
                creaturesDefeated++;
                System.out.println("Creatures killed this run: " + creaturesDefeated);
                player.addGold(creature.getGoldValue());
                recentTurnsText += "You earned " + creature.getGoldValue() + " gold from killing " + creature.getName() + "\n";
                setNewCreature();
            }
        }
        //Player defends
        else if(playerTurnChoice == 2)
        {
            if(player.isDefending)
                recentTurnsText += "You were already defending...\n";
            else
                recentTurnsText += "You are now defending\n";
            player.defend();
        }
        else if(playerTurnChoice == 3)
        {
            recentTurnsText += "Used a potion. Health restored to maxiumum\n";
            player.drinkPotion();
        }

        //Creature taking turns after player
        while(!(getNextActor() instanceof Player))
            creatureTurn();
    }

    public String getRecentTurnsText()
    {
        int lineCount = 0;

        for(int i = 0; i < recentTurnsText.length(); i++)
        {
            if(recentTurnsText.charAt(i) == '\n')
                lineCount++;
        }

        if(lineCount > 13)
        {
            while(lineCount > 15)
            {
                recentTurnsText = recentTurnsText.substring(recentTurnsText.indexOf('\n', 1));
                lineCount--;
            }
            recentTurnsText = recentTurnsText.substring(1);
        }

        return recentTurnsText;
    }
}
