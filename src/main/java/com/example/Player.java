package com.example;

public class Player {
    private final String name;
    private int sendCounter = 0;
    private int receiveCounter = 0;


     // Create a new player with the specified name.
    public Player(String name) {
        this.name = name;
    }


    public String sendMessage(String content) {
        sendCounter++;
        String messageWithCounter = content + ":" + sendCounter;
        System.out.println(name + " sends: " + messageWithCounter);
        return messageWithCounter;
    }

    public String receiveMessage(String message) {
        System.out.println(name + " received: " + message);
        return message;
    }

    /**
     * Gets the number of messages sent by this player.
     * @return The message counter
     */
    public int getMessageCounter() {
        return sendCounter;
    }

    /**
     * Gets the name of this player.
     * @return The player name
     */
    public String getName() {
        return name;
    }
}
