package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main {

    /**
     * com.example.Main method that can run in either single or multi-process mode.
     *
     * @param args Command line arguments:
     * - No arguments: Run in single-process mode
     * - "multi": Run in multi-process mode
     * - "initiator PORT": Run as initiator in multi-process mode
     * - "responder PORT": Run as responder in multi-process mode
     */
    private static final int MAX_MESSAGES = 10;
    private static final Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) throws Exception {
        if (args.length > 0 && args[0].equals("multi")) {
            if (args.length > 1 && args[1].equals("initiator")) {
                runMultiProcessInitiator();
            } else if (args.length > 1 && args[1].equals("responder")) {
                runMultiProcessResponder();
            } else {
                System.out.println("Invalid arguments. Use 'multi initiator' or 'multi responder'");
            }
        } else {
            runSingleProcess();
        }
        scanner.close();
    }


    // Single process mode
    private static void runSingleProcess() {
        Player p1 = new Player("initiator");
        Player p2 = new Player("responder");

        for (int i = 0; i < MAX_MESSAGES; i++) {
            // Get user input
            System.out.print("Enter message to send: ");
            String userMessage = scanner.nextLine();

            // initiator sends initial message
            String p1Message = p1.sendMessage(userMessage);
            p2.receiveMessage(p1Message);

            // responder responds
            String p2Message = p2.sendMessage(p1Message);
            p1.receiveMessage(p2Message);
        }
    }

    // Multi-process initiator with user input
    private static void runMultiProcessInitiator() throws IOException {
        try (Socket socket = new Socket("localhost", 12345);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            Player player = new Player("Initiator");

            for (int i = 0; i < MAX_MESSAGES; i++) {
                // Get user input
                System.out.print("Enter message to send: ");
                String userMessage = scanner.nextLine();

                // Send message
                player.sendMessage(userMessage);
                out.println(userMessage + ":" + player.getMessageCounter());

                // Receive response
                String response = in.readLine();
                System.out.println(player.receiveMessage(response));
            }
            out.println("STOP");
        }
    }

    // Multi-process responder (still automatic responses)
    private static void runMultiProcessResponder() throws IOException {
        try (ServerSocket server = new ServerSocket(12345);
             Socket socket = server.accept();
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            Player player = new Player("Responder");

            String input;
            while ((input = in.readLine()) != null) {
                if (input.equals("STOP")) break;

                // Receive message
                String received = player.receiveMessage(input);

                // Send response
                player.sendMessage(received);
                out.println(received + ":" + player.getMessageCounter());
            }
        }
    }
}

