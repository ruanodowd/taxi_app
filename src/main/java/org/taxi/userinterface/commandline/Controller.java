package org.taxi.userinterface.commandline;

public class Controller {
    //singleton pattern
    private static Controller instance = new Controller();
    private CommandLine cli;
    private Controller() {
        this.cli = CommandLine.getCommandLine();
    }
    public static Controller getInstance() {
        return instance;
    }
    public void startProgram(){
        cli.showWelcomeScreen();

    }
}
