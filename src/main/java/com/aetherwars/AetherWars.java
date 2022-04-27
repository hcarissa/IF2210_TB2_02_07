package com.aetherwars;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import com.aetherwars.card.*;
import com.aetherwars.player.*;
import com.aetherwars.board.*;
import com.aetherwars.util.CSVReader;

public class AetherWars extends Application {
  private static final String CHARACTER_CSV_FILE_PATH = "card/data/character.csv";

  private Controller ctrl;
  private Player p1, p2;

  public void loadCards() throws IOException, URISyntaxException {
    File characterCSVFile = new File(getClass().getResource(CHARACTER_CSV_FILE_PATH).toURI());
    CSVReader characterReader = new CSVReader(characterCSVFile, "\t");
    characterReader.setSkipHeader(true);
    List<String[]> characterRows = characterReader.read();
    for (String[] row : characterRows) {
//      Character c = new CharacterCard(row[1], row[3], Type.valueOf(row[2])); TODO
//      System.out.println(c);
    }
  }

  public boolean checkWinner() {
    if (p1.getHp() <= 0 || p2.getHp() <= 0) {
      return true;
    }
    else if (p1.getDeckCount() == 0 || p2.getDeckCount() == 0) {
      return true;
    }
    else {
      return false;
    }
  }

  @Override
  public void start(Stage stage) {
    p1 = new Player("Player 1");
    p2 = new Player("Player 2");

    Parent root = null;
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("AetherWars.fxml"));
      root = loader.load();
      ctrl = loader.getController();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    ctrl.setBoard(new Board(p1, p2));
    ctrl.loadHand();

    Scene scene = new Scene(root);
    stage.setTitle("Minecraft: Aether Wars");
    stage.setScene(scene);
    stage.show();

    try {
      this.loadCards();
//      text.setText("Minecraft: Aether Wars!");
    } catch (Exception e) {
//      text.setText("Failed to load cards: " + e);
    }

  }

  public static void main(String[] args) {
    launch();
  }
}
