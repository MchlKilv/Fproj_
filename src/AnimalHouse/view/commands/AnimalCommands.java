package AnimalHouse.view.commands;

import AnimalHouse.view.ConsoleUI;

public class AnimalCommands extends Command {

    public AnimalCommands(ConsoleUI consoleUI) {
        super(consoleUI);
        description = "Просмотреть команды";
    }

    public void execute(){
        consoleUI.viewCommands();
    }
}