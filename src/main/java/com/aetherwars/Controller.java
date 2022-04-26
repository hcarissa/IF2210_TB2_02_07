package com.aetherwars;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import com.aetherwars.board.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Controller {
    private Board board;

    @FXML
    private Rectangle drawTab, planTab, attackTab, endTab;

    @FXML
    private Color active = new Color(1.0, 0.2431, 0.1216, 1.0);
    @FXML
    private Color inactive = new Color(0.5216, 0.6353, 0.8314, 1.0);

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void changePhase(ActionEvent event) {
        if (board.getPhase() == Phase.DRAW) {
            // deactivate draw label, activate plan label
            this.drawTab.setFill(inactive);
            this.planTab.setFill(active);
            board.setPhase(Phase.PLAN);
        }
        else if (board.getPhase() == Phase.PLAN) {
            // deactivate plan label, activate attack label
            this.planTab.setFill(inactive);
            this.attackTab.setFill(active);
            board.setPhase(Phase.ATTACK);
        }
        else if (board.getPhase() == Phase.ATTACK) {
            // deactivate attack label, activate end label
            this.attackTab.setFill(inactive);
            this.endTab.setFill(active);
            board.setPhase(Phase.END);
        }
        else {
            // deactivate end label, change turn, activate draw label
            this.endTab.setFill(inactive);
            this.drawTab.setFill(active);
            board.setPhase(Phase.DRAW);
            board.switchTurn();
        }
    }

    @FXML
    void initialize() {

    }

}
