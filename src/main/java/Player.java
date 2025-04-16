public class Player {
    private final String name;
    private int messageCounter;


     // Create a new player with the specified name.
    public Player(String name) {
        this.name = name;
        this.messageCounter = 0;
    }


    public void sendMessage(String content) {
        messageCounter++;
        System.out.println(name + " sends: " + content + ":" + messageCounter);
    }

    public String receiveMessage(String content) {
        System.out.println(name + " received: " + content);
        return content + ":" + messageCounter;
    }

    /**
     * Gets the number of messages sent by this player.
     * @return The message counter
     */
    public int getMessageCounter() {
        return messageCounter;
    }

    /**
     * Gets the name of this player.
     * @return The player name
     */
    public String getName() {
        return name;
    }
}
