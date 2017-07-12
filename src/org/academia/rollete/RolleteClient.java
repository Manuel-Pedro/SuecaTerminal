package org.academia.rollete;

import org.academia.server.ClientPOJO;
import org.academia.server.GameClient;

import java.io.IOException;

public class RolleteClient extends GameClient {

    private RoletteColors play;
    private int bet;

    public RolleteClient(ClientPOJO client) {
        super(client);
    }

    public void sayToPlayer(String msg) {
        out.println(msg);
    }

    public RoletteColors getPlay() {
        return play;
    }

    public int getBet() {
        return bet;
    }

    public void askPlay() {

        boolean validPlay = false;
        boolean validAmount = false;

        while (!validPlay) {

            out.println();
            out.println("Witch color do you want to bet?");
            out.println(RoletteColors.RED + "\u25A8" + " " + RoletteColors.GREEN + "\u25A8 " + RoletteColors.BLACK + "\u25A8");
            out.println("1 2 3");

            try {
                validPlay = validatePlay(in.readLine());
            } catch (IOException e) {
                System.err.println("Error: " + e.getMessage());
                System.exit(1);
            }

            if (!validPlay) {
                out.println("Invalid Play! Please type again!");
            }

        }

        while (!validAmount) {

            out.println();
            out.println("Your chips: " + getChips());
            out.println("How many chips you want to bet? ");

            try {
                validAmount = validateAmount(in.readLine());
            } catch (IOException e) {
                System.err.println("Error: " + e.getMessage());
                System.exit(1);
            }

            if (!validPlay) {
                out.println("Invalid Amount! Please type again!");
            }
        }

    }

    private boolean validateAmount(String amount) {

        int number;

        try {
            number = Integer.parseInt(amount);
        } catch (NumberFormatException nfe) {
            return false;
        }

        if (getChips() < number) {
            return false;
        }

        removeChips(number);
        out.println(" You bet is : " + getChips() + " chips!");
        return true;
    }

    private boolean validatePlay(String play) {

        int number;

        try {
            number = Integer.parseInt(play);
        } catch (NumberFormatException nfe) {
            return false;
        }

        if (number != 1 || number != 2 || number != 3) {
            return false;
        }

        switch (number) {
            case 1:
                this.play = RoletteColors.RED;
                break;
            case 2:
                this.play = RoletteColors.GREEN;
                break;
            case 3:
                this.play = RoletteColors.BLACK;
                break;
        }

        return true;
    }

    public boolean askOut() {

        String option = null;


        while (true) {

            out.println("Do you want to continue? (y/n)");

            try {
                option = in.readLine();
            } catch (IOException e) {
                System.err.println("Error: "+ e.getMessage());
                System.exit(1);
            }

            if (option.equals("y")){
                return true;
            }

            if (option.equals("n")){
                return false;
            }

            out.println("Invalid answer! Type again!");
        }
    }
}
