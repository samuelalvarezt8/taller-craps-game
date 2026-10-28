package com.example.crapsgame262.controllers;

import com.example.crapsgame262.models.AlertBox;
import com.example.crapsgame262.models.Dice;
import com.example.crapsgame262.models.Game;
import com.example.crapsgame262.models.GameAdapter;
import com.example.crapsgame262.models.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class GameController {

    private Player currentPlayer;
    private final Game game = new Game();

    @FXML private ImageView dice1ImageView;
    @FXML private ImageView dice2ImageView;
    @FXML private Label nicknameLabel;
    @FXML private Label rollLabel;
    @FXML private Label pointLabel;
    @FXML private Label winsLabel;
    @FXML private Label lossesLabel;
    @FXML private TextArea messageTextArea;

    /** Clase interna: reacciona a los eventos del modelo. */
    private class GameEvents extends GameAdapter {
        @Override
        public void onWin(Game.Result result, int roll) {
            if (result == Game.Result.WIN_NATURAL) {
                showMessage("Sacaste " + roll + ". ¡Ganaste automáticamente!");
            } else {
                showMessage("Sacaste " + roll + ", ¡lograste tu punto! Ganaste.");
            }
        }

        @Override
        public void onLose(Game.Result result, int roll) {
            if (result == Game.Result.CRAPS) {
                showMessage("Sacaste " + roll + ": ¡Craps! Perdiste.");
            } else {
                showMessage("Sacaste 7 antes de tu punto. Perdiste.");
            }
        }

        @Override
        public void onPointSet(int point) {
            showMessage("Tu punto es " + point + ". Sigue lanzando.");
        }

        @Override
        public void onContinue(int roll, int point) {
            showMessage("Sacaste " + roll + ". Tu punto es " + point + ", sigue lanzando.");
        }
    }

    @FXML
    public void initialize() {
        game.setListener(new GameEvents());
        updateView();
        showMessage("Presiona «Lanzar» para iniciar.");
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
        nicknameLabel.setText(this.currentPlayer.getNickname());
    }

    @FXML
    void onMouseClickedDropButton(MouseEvent event) {
        game.play();
        dice1ImageView.setImage(loadImage(game.getDice1()));
        dice2ImageView.setImage(loadImage(game.getDice2()));
        updateView();
    }

    private Image loadImage(Dice dice) {
        return new Image(getClass().getResourceAsStream(dice.getImagePath()));
    }

    private void updateView() {
        rollLabel.setText(game.getRoll() == 0 ? "-" : String.valueOf(game.getRoll()));
        pointLabel.setText(game.getPoint() == 0 ? "-" : String.valueOf(game.getPoint()));
        winsLabel.setText(String.valueOf(game.getWins()));
        lossesLabel.setText(String.valueOf(game.getLosses()));
    }

    private void showMessage(String text) {
        messageTextArea.appendText(text + "\n");
    }

    @FXML
    void onMouseClickedHelpButton(MouseEvent event) {
        new AlertBox().showAlertBox(
                "Craps Game - Ayuda",
                "¿Cómo se juega?",
                "TIRO DE SALIDA (primer lanzamiento):\n"
                + "  • 7 u 11: ganas automáticamente.\n"
                + "  • 2, 3 o 12 (\"Craps\"): pierdes.\n"
                + "  • 4, 5, 6, 8, 9 o 10: se establece tu \"punto\".\n\n"
                + "SI HAY PUNTO, sigue lanzando:\n"
                + "  • Si sacas tu punto antes que un 7: ganas.\n"
                + "  • Si sacas 7 antes que tu punto: pierdes.");
    }
}
