package com.aetherwars.util;

import com.aetherwars.card.*;
import javafx.scene.control.Alert;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class CardReader {
    private static CardReader instance;
    private CardCollection characterCardCollection;
    private CardCollection levelCardCollection;
    private CardCollection swapCardCollection;
    private CardCollection potionCardCollection;
    private CardCollection morphCardCollection;
    private static final String CHARACTER_CARD_FILE_NAME = "/com/aetherwars/resources/card/data/character.csv";
    //private static final String LEVEL_CARD_FILE_NAME = "/com/aetherwars/resources/cards/data/spell_level.csv";
    private static final String SWAP_CARD_FILE_NAME = "/com/aetherwars/resources/card/spell_swap.csv";
    private static final String POTION_CARD_FILE_NAME = "/com/aetherwars/resources/card/spell_ptn.csv";
    private static final String MORPH_CARD_FILE_NAME = "/com/aetherwars/resources/card/spell_morph.csv";

    /**
     * Constructor of CardReader
     */
    private CardReader() {
        try {
            loadCards();
        } catch (Exception e) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Card loading failed");
            errorAlert.setContentText("Failed to load cards: " + e);
            errorAlert.showAndWait();
        }
    }

    public static CardReader getInstance() {
        if (instance == null) {
            instance = new CardReader();
        }
        return instance;
    }

    /**
     * Loads the cards from the files
     * @throws IOException
     * @throws URISyntaxException
     */

     public void loadCards() throws IOException, URISyntaxException {
        characterCardCollection = new CardCollection();
        levelCardCollection = new CardCollection();
        swapCardCollection = new CardCollection();
        potionCardCollection = new CardCollection();
        morphCardCollection = new CardCollection();
        File characterCSVFile = new File(getClass().getResource(CHARACTER_CARD_FILE_NAME).toURI());
        //File levelCSVFile = new File(getClass().getResource(LAND_CSV_FILE_PATH).toURI());
        File swapCSVFile = new File(getClass().getResource(SWAP_CARD_FILE_NAME).toURI());
        File potionCSVFile = new File(getClass().getResource(POTION_CARD_FILE_NAME).toURI());
        File morphCSVFile = new File(getClass().getResource(MORPH_CARD_FILE_NAME).toURI());

        CSVReader characterReader = new CSVReader(characterCSVFile, "\t");
        characterReader.setSkipHeader(true);
        List<String[]> characterRows = characterReader.read();
        for (String[] row : characterRows) {
            CharacterCard l = new CharacterCard(row[1], row[3], row[4],Integer.parseInt(row[8]), Integer.parseInt(row[9]), Integer.parseInt(row[5]), Integer.parseInt(row[6]), Integer.parseInt(row[0]), CharacterType.valueOf(row[7]));
            characterCardCollection.addCard(l);
        }

        CSVReader swapReader = new CSVReader(swapCSVFile, "\t");
        swapReader.setSkipHeader(true);
        List<String[]> swapRows = swapReader.read();
        for (String[] row : swapRows) {
            SpellSwap l = new SpellSwap(row[1], row[2], row[3], Integer.parseInt(row[5]), Integer.parseInt(row[4]));
            swapCardCollection.addCard(l);
        }

        CSVReader potionReader = new CSVReader(potionCSVFile, "\t");
        potionReader.setSkipHeader(true);
        List<String[]> potionRows = potionReader.read();
        for (String[] row : potionRows) {
            SpellPotion l = new SpellPotion(row[1], row[2], row[3], Integer.parseInt(row[6]), Integer.parseInt(row[4]), Integer.parseInt(row[5]), Integer.parseInt(row[7]));
            potionCardCollection.addCard(l);
        }

        //CSVReader morphReader = new CSVReader(morphCSVFile, "\t");
        //morphReader.setSkipHeader(true);
        //List<String[]> morphRows = morphReader.read();
        //for (String[] row : morphRows) {
        //    SpellMorph l = new SpellMorph(row[1], row[2], row[3], Integer.parseInt(row[5]), Integer.parseInt(row[4]), );
        //    morphCardCollection.addCard(l);
        //}


    }

    public CardCollection getCharacterCardCollection() {
        return characterCardCollection;
    }

    public CardCollection getLevelCardCollection() {
        return levelCardCollection;
    }

    public CardCollection getSwapCardCollection() {
        return swapCardCollection;
    }

    public CardCollection getPotionCardCollection() {
        return potionCardCollection;
    }

    public CardCollection getMorphCardCollection() {
        return morphCardCollection;
    }
}
