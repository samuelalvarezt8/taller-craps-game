package com.example.crapsgame262.controllers;

import com.example.crapsgame262.models.Dice;
import com.example.crapsgame262.models.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class GameController {

    private Player currentPlayer;

    @FXML
    private ImageView dice1ImageView;

    @FXML
    private ImageView dice2ImageView;

    @FXML
    private Label nicknameLabel;

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
        nicknameLabel.setText(this.currentPlayer.getNickname());
    }

    @FXML
    void onMouseClickedDropButton(MouseEvent event) {
        Dice dice1 = new Dice();
        dice1.roll();

        dice1ImageView.setImage(
                new Image(
                        getClass().getResourceAsStream(
                                dice1.getImagePath()
                        )
                )
        );

        Dice dice2 = new Dice();
        dice2.roll();

        dice2ImageView.setImage(
                new Image(
                        getClass().getResourceAsStream(
                                dice2.getImagePath()
                        )
                )
        );
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
