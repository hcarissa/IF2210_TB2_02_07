package com.aetherwars.card;
import java.nio.file.Paths;

public abstract class Card{
    protected String name;
    protected String description;
    protected CardType type;
    protected String imagePath;

    public Card()
    {
        this.name = "";
        this.description = "";
        this.type = CardType.CHARACTER;
        this.imagePath = "";
    }
    
    public Card (String name, String description, CardType type, String imagePath)
    {
        this.name = name;
        this.description = description;
        this.type = type;
        this.imagePath = Paths.get(imagePath).getFileName().toString();
    }

    public String getName()
    {
        return this.name;
    }

    public String getDescription()
    {
        return this.description;
    }

    public CardType getType()
    {
        return this.type;
    }

    public String getImagePath()
    {
        return this.imagePath;
    }

    public abstract CardType getCardType();

    public void printInfo()
    {
        System.out.println("Name       : " + this.name);
        System.out.println("Description: " + this.description);
        System.out.println("Type Kartu : " + this.type);
        System.out.println("Image Path : " + this.imagePath);
    }
}

