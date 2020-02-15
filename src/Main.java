// David Majercak
// 12/21/2019
// Program Name : Creature Killer
// This game allows the user to select the next action
// Try to kill as many creatures as possible

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

//Main function for the RPG
public class Main extends Application
{
    public static void main(String[] args)
    {
        Application.launch(args);
    }

    public void start(Stage primaryStage)
    {
        //Creating a Font for better Readability
        Font biggerFont = new Font ("Impact", 20);
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        //Creating the Intro Scene
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        //Creating Panes for the First Scene (Getting Player Name)
        BorderPane introPane = new BorderPane();
        FlowPane introFlowPane = new FlowPane();
        introFlowPane.setHgap(10);

        TextField tfName = new TextField();

        //Most of this GUI creation is pretty straightforward, just wordy
        TextArea introDescription = new TextArea();
        introDescription.setFont(biggerFont);
        introDescription.setText("You fight your demons one more time...\n\n\n\t\tType in your name and press Enter");
        introDescription.setEditable(false);

        //Creating a label for the name
        Label nameLabel = new Label("Name: ");
        nameLabel.setFont(biggerFont);
        introFlowPane.getChildren().addAll(nameLabel, tfName);
        introFlowPane.setAlignment(Pos.CENTER);

        //Adding introFlowPane and introDescription into introPane
        introPane.setBottom(introFlowPane);
        introPane.setCenter(introDescription);

        //Setting scene
        Scene introScene = new Scene(introPane, 450,  150);
        primaryStage.setTitle("Creature Killer");
        primaryStage.setScene(introScene);
        primaryStage.show();


        //When user enters text into tfName and presses enter, the game is started and the scene is changed
        tfName.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER && tfName.getText().length() > 0)
            {
                String temp = "";
                if(tfName.getText().length() > 15)
                    temp = tfName.getText().substring(0, 15);
                else
                    temp = tfName.getText();
                Game game = new Game(temp);
                try
                {
                    //Calling rungame, which launches the main game window
                    runGame(game, primaryStage);
                } catch (InterruptedException ex)
                {
                    ex.printStackTrace();
                }
            }
        });


    }

    private static void runGame(Game game, Stage primaryStage) throws InterruptedException
    {
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        //Creating the Game Scene
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        //Creating the necessary elements of the game Scene
        Font nameFont = new Font( 16);
        BorderPane gameBorderPane = new BorderPane();
        FlowPane userChoicesPane = new FlowPane();
        FlowPane playerPane = new FlowPane();
        GridPane playerStats = new GridPane();
        FlowPane creaturePane = new FlowPane();
        GridPane creatureStats = new GridPane();
        FlowPane bottomPane = new FlowPane();
        GridPane creatureDescription = new GridPane();
        GridPane creaturesKilled = new GridPane();


        bottomPane.setOrientation(Orientation.VERTICAL);

        //Creating the playerStats Pane
        Label playerNameLabel = new Label(game.getPlayer().getName());
        playerNameLabel.setFont(nameFont);
        playerStats.add(playerNameLabel, 0, 0);

        //Creating the textfiels to display player stats at all times in the window
        //None of these textfields are editable
        //The user can only use the buttons to interact with the game after entering their name
        //Also adding these textfields into the playerstats pane
        playerStats.add(new Label("Attack: "), 0, 1);
        TextField tfPlayerAttack = new TextField(Integer.toString(game.getPlayer().getAttackPower()));
        tfPlayerAttack.setEditable(false);
        playerStats.add(tfPlayerAttack, 1, 1);

        playerStats.add(new Label("Health: "), 0, 2);
        TextField tfplayerHealth = new TextField(Integer.toString(game.getPlayer().getCurrentHealth()));
        tfplayerHealth.setEditable(false);
        playerStats.add(tfplayerHealth, 1 , 2);

        playerStats.add(new Label("Max Health: "), 0, 3);
        TextField tfplayerMaxHealth = new TextField(Integer.toString(game.getPlayer().getMaxHealth()));
        tfplayerMaxHealth.setEditable(false);
        playerStats.add(tfplayerMaxHealth, 1 , 3);

        playerStats.add(new Label("Gold: "), 0, 4);
        TextField tfplayerGold = new TextField(Integer.toString(game.getPlayer().getGold()));
        tfplayerGold.setEditable(false);
        playerStats.add(tfplayerGold, 1 , 4);

        playerStats.add(new Label("Turn Frequency: "), 0, 5);
        TextField tfplayerTurnFrequency = new TextField(Integer.toString(game.getPlayer().getTurnFrequency()));
        tfplayerTurnFrequency.setEditable(false);
        playerStats.add(tfplayerTurnFrequency, 1 , 5);

        ImageView playerImageView = new ImageView(new Image("Images/Player.png"));
        playerImageView.setFitHeight(150);
        playerImageView.setFitWidth(120);


        //Adding playerStats and playerImageView into the larger playerPane
        playerPane.getChildren().addAll(playerStats, playerImageView);


        //Creating the creatureStats Pane
        //Essentially the same thing as the player textFields creation
        Label creatureNameLabel = new Label(game.getCreature().getName());
        creatureNameLabel.setFont(nameFont);
        creatureStats.add(creatureNameLabel, 0, 0);


        creatureStats.add(new Label("Attack: "), 0, 1);
        TextField tfCreatureAttack = new TextField(Integer.toString(game.getCreature().getAttackPower()));
        tfCreatureAttack.setEditable(false);
        creatureStats.add(tfCreatureAttack, 1, 1);

        creatureStats.add(new Label("Health: "), 0, 2);
        TextField tfCreatureHealth = new TextField(Integer.toString(game.getCreature().getCurrentHealth()));
        tfCreatureHealth.setEditable(false);
        creatureStats.add(tfCreatureHealth, 1 , 2);

        creatureStats.add(new Label("Max Health: "), 0, 3);
        TextField tfCreatureMaxHealth = new TextField(Integer.toString(game.getCreature().getMaxHealth()));
        tfCreatureMaxHealth.setEditable(false);
        creatureStats.add(tfCreatureMaxHealth, 1 , 3);

        creatureStats.add(new Label("Gold Value: "), 0, 4);
        TextField tfCreatureGoldValue = new TextField(Integer.toString(game.getCreature().getGoldValue()));
        tfCreatureGoldValue.setEditable(false);
        creatureStats.add(tfCreatureGoldValue, 1 , 4);

        creatureStats.add(new Label("Turn Frequency: "), 0, 5);
        TextField tfCreatureTurnFrequency = new TextField(Integer.toString(game.getCreature().getTurnFrequency()));
        tfCreatureTurnFrequency.setEditable(false);
        creatureStats.add(tfCreatureTurnFrequency, 1 , 5);

        ImageView creatureImageView = new ImageView(new Image("Images/" + game.getCreature().getName() + ".png"));
        creatureImageView.setFitHeight(150);
        creatureImageView.setFitWidth(120);

        creaturePane.getChildren().addAll(creatureImageView, creatureStats);

        //Creature Description
        //This will display a short description of the creature
        TextField tfCreatureDescription = new TextField(game.getCreature().getName() + ": " + game.getCreature().getDescription());
        tfCreatureDescription.setPrefColumnCount(40);
        tfCreatureDescription.setEditable(false);

        creatureDescription.add(new Label("Creature Description: "), 0, 0);
        creatureDescription.add(tfCreatureDescription, 1, 0);

        TextField tfCreaturesKilled = new TextField(Integer.toString(game.getCreaturesDefeated()));
        creaturesKilled.add(new Label("Creatures Killed: "), 0, 0);
        creaturesKilled.add(tfCreaturesKilled, 1, 0);

        //Creating buttons for userChoicesPane
        Button btAttack = new Button("Attack");
        Button btDefend = new Button("Defend");
        Button btPotion = new Button("Use Potion (costs 100 Gold)");
        Button btHelp = new Button("Help");

        userChoicesPane.setHgap(20);
        userChoicesPane.getChildren().addAll(btAttack, btDefend, btPotion, btHelp);

        TextArea taRecentTurns = new TextArea("Player Turn #1 " + "\nA new creature has been summoned for you to fight!");
        taRecentTurns.setPrefRowCount(15);
        taRecentTurns.setPrefColumnCount(60);
        taRecentTurns.setEditable(false);

        bottomPane.getChildren().addAll(creatureDescription, creaturesKilled, userChoicesPane, new Label("Recent Information: "), taRecentTurns);
        bottomPane.setVgap(10);
        BorderPane.setMargin(bottomPane, new Insets(10));


        //Creating overall scene using smaller panes to make up a larger scene
        gameBorderPane.setBottom(bottomPane);
        gameBorderPane.setLeft(playerPane);
        gameBorderPane.setRight(creaturePane);

        Scene gameScene = new Scene(gameBorderPane, 800, 660);

        primaryStage.setScene(gameScene);
        primaryStage.show();

        //Event handler for Attack Button
        //These events use the Game class to carry out the game action given the user input
        //Then the window is updated with the updateGUI() function
        btAttack.setOnAction((e) -> {
            if(!game.isGameOver())
            {
                game.playerTurn(1);

                updateGUI(game, tfPlayerAttack, tfplayerHealth, tfplayerMaxHealth, tfplayerGold, tfplayerTurnFrequency, tfCreatureAttack, tfCreatureHealth, tfCreatureMaxHealth,
                        tfCreatureGoldValue, tfCreatureTurnFrequency, creatureImageView, tfCreatureDescription, tfCreaturesKilled, taRecentTurns, creatureNameLabel);
            }
        });

        //Event Handler for Defend Button
        btDefend.setOnAction((e) -> {
            if(!game.isGameOver())
            {
                game.playerTurn(2);

                updateGUI(game, tfPlayerAttack, tfplayerHealth, tfplayerMaxHealth, tfplayerGold, tfplayerTurnFrequency, tfCreatureAttack, tfCreatureHealth, tfCreatureMaxHealth,
                        tfCreatureGoldValue, tfCreatureTurnFrequency, creatureImageView, tfCreatureDescription, tfCreaturesKilled, taRecentTurns, creatureNameLabel);
            }

        });

        //Even Handler for Potion Button
        btPotion.setOnAction((e) -> {
            if(!game.isGameOver())
            {
                game.playerTurn(3);

                updateGUI(game, tfPlayerAttack, tfplayerHealth, tfplayerMaxHealth, tfplayerGold, tfplayerTurnFrequency, tfCreatureAttack, tfCreatureHealth, tfCreatureMaxHealth,
                        tfCreatureGoldValue, tfCreatureTurnFrequency, creatureImageView, tfCreatureDescription, tfCreaturesKilled , taRecentTurns, creatureNameLabel);

            }

        });

        //Even Handler for Help Button
        //Creates a popup window that displays some helpful information about the game
        btHelp.setOnAction((e) -> {
            Stage helpPopupStage = new Stage();
            TextArea taHelpText = new TextArea();
            taHelpText.setEditable(false);
            taHelpText.setText("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
            "Help Handbook~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
            "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
            "You don't know why you're here, but you know you must kill as many creatures as possible\n" +
            "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
            "Stats~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
            "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
            "Attack - How hard you can hit.  All hits range from 0 up to that entity's attack power.\n" +
            "Current Health - If this hits 0, that entity dies.  If your health reaches 0 it's game over.\n" +
            "Max Health - The maximum amount of health an entity can have.  You start at max health.\n" +
            "Gold - How much gold you have.  It costs 100 gold to use a potion. If you try to use a potion without enough gold you lose your turn.\n" +
            "Gold Value - How much gold you earn from killing a creature\n" +
            "Turn Frequency - How often an entity takes an action.  The higher the turn frequency slower you are.\n" +
            "\t\tThe player has a turn frequency of 100. An entity with a turn frequency 1/2 that of the other entity will attack twice as often\n" +
            "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                    "Tips~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
            "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
            "Each creature you kill makes the game harder.\n" +
                    "Each creature you kill raises the stats of new creatures by 5%(except for turn frequency)\n" +
            "Using defend is good against ogres and sometimes good against zombies\n" +
            "\tDefending against rats is not worth it\n\n" +
                    "CLOSE THIS WINDOW TO CONTINUE");
            helpPopupStage.initModality(Modality.APPLICATION_MODAL);
            Scene helpPopupScene = new Scene(taHelpText, 800,410);
            helpPopupStage.setScene(helpPopupScene);
            helpPopupStage.setTitle("The Help Handbook (CLOSE THIS WINDOW TO CONTINUE)");
            helpPopupStage.show();
        });
    }

    //This method updates the GUI
    //There are a lot of fields to pass in but I think this is still more readable than repeating the code three times
    private static void updateGUI(Game game, TextField tfPlayerAttack, TextField tfplayerHealth, TextField tfplayerMaxHealth, TextField tfplayerGold, TextField tfplayerTurnFrequency,
                                  TextField tfCreatureAttack, TextField tfCreatureHealth, TextField tfCreatureMaxHealth, TextField tfCreatureGoldValue, TextField tfCreatureTurnFrequency,
                                  ImageView creatureImageView, TextField tfCreatureDescription, TextField tfCreaturesKilled, TextArea taRecentTurns, Label creatureNameLabel)
    {
        tfPlayerAttack.setText(Integer.toString(game.getPlayer().getAttackPower()));
        tfplayerGold.setText(Integer.toString(game.getPlayer().getGold()));
        tfplayerHealth.setText(Integer.toString(game.getPlayer().getCurrentHealth()));
        tfplayerMaxHealth.setText(Integer.toString(game.getPlayer().getMaxHealth()));
        tfplayerTurnFrequency.setText(Integer.toString(game.getPlayer().getTurnFrequency()));

        tfCreatureAttack.setText(Integer.toString(game.getCreature().getAttackPower()));
        tfCreatureGoldValue.setText(Integer.toString(game.getCreature().getGoldValue()));
        tfCreatureHealth.setText(Integer.toString(game.getCreature().getCurrentHealth()));
        tfCreatureMaxHealth.setText(Integer.toString(game.getCreature().getMaxHealth()));
        tfCreatureTurnFrequency.setText(Integer.toString(game.getCreature().getTurnFrequency()));
        tfCreatureDescription.setText(game.getCreature().getName() + ": " + game.getCreature().getDescription());
        tfCreaturesKilled.setText(Integer.toString(game.getCreaturesDefeated()));
        creatureNameLabel.setText(game.getCreature().getName());

        taRecentTurns.setText(game.getRecentTurnsText());

        creatureImageView.setImage(new Image("Images/" + game.getCreature().getName() + ".png"));
        creatureImageView.setFitHeight(150);
        creatureImageView.setFitWidth(120);

    }

}
