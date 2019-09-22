import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.Glow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;

/*

Battleship!

KU EECS 448 project 1

Team Name:
Poor Yorick

Team members:
Max Goad
Jace Bayless
Tri Pham
Apurva Rai
Meet Kapadia
*/

//This class serves as a the executive class of Battleship as well as making basic gui elements.

public class WinGUI implements OverScene, EventHandler<ActionEvent>{

    private Button playAgain, exit;
    private Label winner;
    private Scene win;
    private Scene scene;

    // Implementation influenced from https://github.com/maxdgoad/othello-max/

    /*
    * @ pre none
    *	@ param stage, font, number of ships for each players, and two strings
    *	@ post constuctor
    * @ return none
    */
    public WinGUI(Stage s, Font f, int p1NumOfShips, int p2NumOfShips, String p1, String p2){

        s.centerOnScreen();

        f = new Font(f.getName(), 50);

        if(p2NumOfShips == 0 && p1NumOfShips > 0){
            winner = new Label(p1 + " wins");
            winner.setFont(f);
            scene.getChildren().add(winner);
            scene.setAlignment(Pos.CENTER);
        }

        else if (p1NumOfShips == 0 && p2NumOfShips > 0){
            winner = new Label(p2 + " wins");
            winner.setFont(f);
            scene.getChildren().add(winner);
            scene.setAlignment(Pos.CENTER);
        }

        playAgain = new Button();
        playAgain.setText("Play Again");
        playAgain.setOnAction(this);
        playAgain.setFont(f);

        exit = new Button();
        exit.setText("Exit");
        exit.setOnAction(e -> s.close());
        exit.setFont(f);

        StackPane panes =  new StackPane();
        VBox buttons = new VBox(20);
        BorderPane border = new BorderPane();

        Glow g = new Glow();
        g.setLevel(.5);
        scene.setEffect(g);
        border.setStyle("-fx-background-color: lime;");

        buttons.getChildren().addAll(playAgain, exit);
        buttons.setAlignment(Pos.CENTER);

        panes.getChildren().addAll(border, buttons);
        win = new Scene(panes, 500, 500);

    }

    /*
  	* @ pre none
  	*	@ param none
  	*	@ post Gets the scene
  	* @ return the win screen
  	*/
    @Override
    public Scene getScene(){ return win; }

    /*
  	* @ pre game ended
  	*	@ param action event / click a button
  	*	@ post play again = starts new game
  	* @ return none
  	*/
    @Override
    public void handle(ActionEvent e) {
        if(e.getSource() == playAgain){
            // this mess uses the static fields and methods from the Menu clas to set the next appropriate scene
            System.out.println("working");
            BattleshipGUI.nextScene(new PlayerOptionsGUI("pogui", BattleshipGUI.getStage(), BattleshipGUI.getFont()).getScene(), 9);
        }
    }

}