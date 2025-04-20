package AnimalHouse.view.commands;

import AnimalHouse.view.ConsoleUI;

public class DeleteAnimal extends Command {

    public DeleteAnimal(ConsoleUI consoleUI) {
        super(consoleUI);
        description = "Удалить животное";
    }

    public void execute(){
        consoleUI.deleteAnimal();
    }
}
