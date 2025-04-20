package AnimalHouse.view.commands;

import AnimalHouse.view.ConsoleUI;

public class Save extends Command {

    public Save(ConsoleUI consoleUI) {
        super(consoleUI);
        description = "Сохранить в файл";
    }

    public void execute(){
        consoleUI.save();
    }
}
