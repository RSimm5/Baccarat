import java.util.ArrayList;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.event.EventHandler;


public class BaccaratGame extends Application {

  Button bDeal;
  Button bPlayer;
  Button bBanker;
  Button bDraw;
  Button bAgain;

  TextField txtBalance;
  TextField txtUserBet;
  TextField txtBetOn;
  TextArea txtWonOrLost;
  TextField txtDisplayBalance;

  TextArea txtPlayerCards;
  TextArea txtBankerCards;

  VBox vb1;
  VBox vb2;

  BorderPane bp1;
  BorderPane bp2;

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    launch(args);
  }

  //feel free to remove the starter code from this method
  @Override
  public void start(Stage primaryStage) throws Exception {
    // TODO Auto-generated method stub
    primaryStage.setTitle("Baccarat");
    // BorderPane root = new BorderPane();

    titleScene(primaryStage);
    primaryStage.show();
  }

  private void titleScene(Stage primaryStage) {
    Text title = new Text("Baccarat");
    title.setStyle("-fx-font-size: 36;");  
    Button playBtn = new Button("Play");
    Button rulesBtn = new Button("Rules");

    rulesBtn.setOnAction(e -> rulesScene(primaryStage));
    playBtn.setOnAction(e -> startScene(primaryStage));

    HBox btns = new HBox(20);
    btns.getChildren().addAll(playBtn, rulesBtn);
    btns.setStyle("-fx-alignment: center;"); 

    VBox layout = new VBox(20); 
    layout.getChildren().addAll(title, btns);
    layout.setStyle("-fx-alignment: center;");

      Scene startScene = new Scene(layout, 700,500);
    primaryStage.setScene(startScene);
  }

  private void GameOverScene(Stage primaryStage) {
    MenuBar menu = menu(primaryStage);
    Text gameOverText = new Text("Game Over");
        gameOverText.setStyle("-fx-font-size: 36;"); 
    StackPane root = new StackPane();
        root.getChildren().add(gameOverText);
    Scene gameOverScene = new Scene(root, 700, 500);
    primaryStage.setScene(gameOverScene);
  }

  private void rulesScene(Stage primaryStage) {
    MenuBar menu = menu(primaryStage);
    Text title = new Text("RULES");
    title.setStyle("-fx-font-size: 36;");
    Text body = new Text(rules());
    body.setWrappingWidth(400); 

    Button backButton = new Button("Back");
    backButton.setOnAction(e -> titleScene(primaryStage));

    VBox layout = new VBox(20);
    layout.setStyle("-fx-alignment: center;");
    layout.getChildren().addAll(title, body, backButton);

    Scene rulesScene = new Scene(layout, 700, 500);
    primaryStage.setScene(rulesScene);
  }

  private String rules() {
    String rules = new String();
    rules += 
      "BASICS\n"
    + "• The suits of the cards are irrelevant.\n"
    + "• Face cards and 10\'s count as zero.\n"
    + "• Aces always count as one.\n"
    + "• When card values total more than nine, only the one's place value is used.\n"
    + "• A commission is charged on Banker bets when the Banker wins to even out the odds. No commission is charged on Player bets.\n"
    + "• Ties result in all bets being returned if no one bet on tie.\n\n"
    + "GAMEPLAY\n"
    + "• Cards are shuffled, cut by player, and placed in shoe. First card is drawn and shown.\n"
    + "• Bets can be placed on Player, Banker, or Tie\n"
    + "• Two cards each are dealt to the Player hand and Banker hand.\n"
    + "• If either initial hand is an 8 or 9 (natural) it wins immediately.\n"
    + "• Player hand rules: Stand on 6-7, Draw on 5 or less.\n\n"
    + "ODDS\n"
    + "• All player bets: 1:1 evens\n"
    + "• All bank bets 1:1 evens\r\nbank stores 5% of players win\n"
    + "• All tie bets 8:1\n";

    return rules;
  }

  //returns menu bar for all scenes
  private MenuBar menu(Stage primaryStage) {
    MenuBar menuBar = new MenuBar();
    Menu options = new Menu("Options");

    MenuItem exitItem = new MenuItem("Exit");
    MenuItem freshStartItem = new MenuItem("Fresh Start");


        exitItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();;
            }
        });

        freshStartItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
              txtBalance.setText("1000.0");
              totalWinnings = 1000.0;
            }
        });

    options.getItems().addAll(exitItem, freshStartItem);

        // Add the "Options" menu to the menu bar
        menuBar.getMenus().add(options);

    return menuBar;
  }

  void playAgainScene(Stage primaryStage) {

    bAgain = new Button("Play again");
    bAgain.setPrefSize(150, 150); //width, height

    bAgain.setOnAction(e -> startScene(primaryStage));

    txtWonOrLost = new TextArea("You won/lost " + totalWinnings + "!");
    txtWonOrLost.setEditable(false);
    txtWonOrLost.setPrefSize(200, 250);
    txtWonOrLost.setStyle("-fx-alignment: center;"); 

    txtDisplayBalance = new TextField("Balance: " + totalWinnings);
    txtDisplayBalance.setEditable(false);
    txtDisplayBalance.setPrefSize(200, 50);
    txtDisplayBalance.setAlignment(Pos.CENTER);

    txtPlayerCards = new TextArea("Player's cards: ");
    txtPlayerCards.setEditable(false);
    txtPlayerCards.setPrefHeight(200);
    txtPlayerCards.setStyle("-fx-alignment: center;");

    txtBankerCards = new TextArea("Banker's cards: ");
    txtBankerCards.setEditable(false);
    txtBankerCards.setPrefHeight(200);
    txtBankerCards.setStyle("-fx-alignment: center;");

    bp2 = new BorderPane();
    bp2.setLeft(txtDisplayBalance);
    bp2.setRight(bAgain);
    bp2.setCenter(txtWonOrLost);
    bp2.setTop(txtBankerCards);
    bp2.setBottom(txtPlayerCards);

    Scene scenePlayAgain = new Scene(bp2, 700, 500);
    primaryStage.setScene(scenePlayAgain);
    primaryStage.show();
  }

  void startScene(Stage primaryStage) {
    MenuBar menu = menu(primaryStage);

    txtBalance = new TextField("Current Balance: " + totalWinnings);
    txtBalance.setEditable(false);
    txtBalance.setPrefSize(350, 100);
    txtBalance.setAlignment(Pos.CENTER);

    txtUserBet = new TextField();
    txtUserBet.setPromptText("Enter your bet:");
    txtUserBet.setFocusTraversable(false);
    txtUserBet.setPrefSize(350, 100);
    txtUserBet.setAlignment(Pos.CENTER);

    HBox hb1 = new HBox(20);
    hb1.getChildren().addAll(txtBalance, txtUserBet);

    Text betOn = new Text("Bet on:");
    betOn.setStyle("-fx-font-size: 36;");

    bPlayer = new Button("Player");
    bBanker = new Button("Banker");
    bDraw = new Button("Draw");

    whoToBetOn = "";
    bPlayer.setOnAction(e -> whoToBetOn = "Player");

    bBanker.setOnAction(e -> whoToBetOn = "Banker");

    bDraw.setOnAction(e -> whoToBetOn = "Draw");

    bPlayer.setPrefSize(150, 70);
    bBanker.setPrefSize(150, 70);
    bDraw.setPrefSize(150, 70);

    HBox hb2 = new HBox(40);
    hb2.getChildren().addAll(bPlayer, bBanker, bDraw);

    bDeal = new Button("Deal");

    bDeal.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        if (totalWinnings == 0) { 
          GameOverScene(primaryStage); 
        }
        if (whoToBetOn != "" && checkBet(txtUserBet.getText()) == true) {
          playAgainScene(primaryStage);
          primaryStage.show();
          newGame();
        }
      }
    });

    //center things
    hb1.setStyle("-fx-alignment: center;");
    betOn.setStyle("-fx-alignment: center;");
    hb2.setStyle("-fx-alignment: center;");
    bDeal.setStyle("-fx-alignment: center;");

    vb1 = new VBox(40);
    vb1.getChildren().addAll(menu, hb1, betOn, hb2, bDeal);
    vb1.setStyle("-fx-alignment: TOP-CENTER;");

    Scene sceneStartGame = new Scene(vb1, 700, 500);
    primaryStage.setScene(sceneStartGame);
  }



  ArrayList<Card> playerHand = new ArrayList<Card>();
  ArrayList<Card> bankerHand = new ArrayList<Card>();
  BaccaratDealer theDealer = new BaccaratDealer();
  BaccaratGameLogic gameLogic = new BaccaratGameLogic();
  double currentBet;
  double totalWinnings = 1000.0;

  String whoWon;
  String whoToBetOn;
  boolean victory;
  boolean natural = false;

  // setup
  public void newGame() {
    theDealer.shuffleDeck();
    playerHand = theDealer.dealHand();
    bankerHand = theDealer.dealHand();

    waitSec(1);

    if (gameLogic.handTotal(bankerHand) >= 8 || gameLogic.handTotal(playerHand) >= 8) {
      natural = true;

      results();
    }
    else {
      natural = false;
      Card extraPlayerCard = null;

      if (gameLogic.evaluatePlayerDraw(playerHand) == true) {
        playerHand.add(theDealer.drawOne());
        extraPlayerCard = playerHand.get(2);
        waitSec(1);
      }

      if (gameLogic.evaluateBankerDraw(bankerHand, extraPlayerCard) == true) {
        bankerHand.add(theDealer.drawOne());
        waitSec(1);
      }

      results();
    }
    showCards(playerHand, bankerHand);
    txtDisplayBalance.setText(Double.toString(totalWinnings));
  }

  public void waitSec(int seconds) {
    try {
            Thread.sleep(1000 * seconds);
    } catch (InterruptedException e) {}
  }

  public void results() {
    double currentWinnings = evaluateWinnings();

    String result = "Player Total: " + gameLogic.handTotal(playerHand) 
    + " Banker Total: " + gameLogic.handTotal(bankerHand) + "\n" 
    + whoWon + " wins\n";

    if (!victory) {
      result += "Sorry, you bet " + whoToBetOn + ". You lost your bet\n";
    } else {
      result += "Congrats, you bet " + whoToBetOn + "! You win " + currentWinnings + "\n";
    }

    txtWonOrLost.setText(result);
  }

  public boolean checkBet(String input) {
    try {
            double value = Double.parseDouble(input); // Attempt to convert the string to a double
            currentBet = value;
            return ((value >= 0) && (value <= totalWinnings));
        } 
    catch (NumberFormatException e) {
            // Conversion failed, input is not a valid double
            return false;
        }
  }

  public void showCards(ArrayList<Card> hand1, ArrayList<Card> hand2) {
    String result = "Player's cards:\n";
      // display cards
      for (Card card : hand1) {
        if (card.getValue() <= 10) {
          result += card.getValue();
        }
        else if (card.getValue() == 11) {
          result += "Jack";
        }
        else if (card.getValue() == 12) {
          result += "Queen";
        }
        else if (card.getValue() == 13) {
          result += "King";
        }
        result += " of " + card.getSuite() + "\n";
      }
    txtPlayerCards.setText(result);

    result = "Banker's cards:\n";
    for (Card card : hand2) {
      if (card.getValue() <= 10) {
        result += card.getValue();
      }
      else if (card.getValue() == 11) {
        result += "Jack";
      }
      else if (card.getValue() == 12) {
        result += "Queen";
      }
      else if (card.getValue() == 13) {
        result += "King";
      }
      result += " of " + card.getSuite() + "\n";
    }
    txtBankerCards.setText(result);
  }

  public double evaluateWinnings() {
    whoWon = gameLogic.whoWon(playerHand, bankerHand);

    if (whoToBetOn != whoWon) {
      victory = false;

      totalWinnings -= currentBet;

      return 0;
    }

    victory = true;

    if (whoWon == "Draw") {  // All tie bets 8:1
      totalWinnings += 7 * currentBet; // need to check later

      return 8 * currentBet;
    }
    else if (whoWon == "Player") { // All player bets: 1:1 evens
      totalWinnings += currentBet;

      return 2 * currentBet;
    }
    else { // All bank bets: 1:1 evens
      totalWinnings += currentBet - 0.05 * 2 * currentBet; // bank stores 5% of players win

      return 0.95 * 2 * currentBet;
    }
  }


}
