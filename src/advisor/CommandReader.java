package advisor;

import java.util.Scanner;

/**
 * Class to read user command from console
 * @author Patryk Lewczuk
 */

public class CommandReader {

    /**
     *
      * @return String which is representation of command input by user.
     */
    public static String[] readCommand() {
        Scanner scanner = new Scanner(System.in);

        String[] commands = new String[2];

        String command = scanner.nextLine();

        if(command.contains(" ")){
            commands = command.split(" ", 2);
        } else {
            commands[0] = command;
            commands[1] = "";
        }

        return commands;
    }

}
