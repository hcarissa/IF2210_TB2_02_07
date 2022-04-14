package com.aetherwars.card;
import java.nio.file.Paths;

public abstract class Card
{
    protected String name;
    protected String description;
    protected Type type;
    protected String imagePath;

    public card()
    {
        this.name = "";
        this.description = "";
        this.type = Type.OVERWORLD;
        this.imagePath = "";
    }
    
    public card (String name, String description, Type type, String imagePath)
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

    public Type getType()
    {
        return this.type;
    }

    public String getImagePath()
    {
        return this.imagePath;
    }

    public abstract CardType getCardType();

    @Override
    public void printInfo()
    {
        System.out.println("Name       : " + this.name);
        System.out.println("Description: " + this.description);
        System.out.println("Type       : " + this.type);
    }
}

