package AnimalHouse.view.commands;

import AnimalHouse.view.ConsoleUI;

public class Finish extends Command {

    public Finish(ConsoleUI consoleUI) {
        super((consoleUI));
        description = "Завершить работу";
    }

    public void execute(){
        consoleUI.finish();
    }
}
