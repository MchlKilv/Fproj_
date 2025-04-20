package AnimalHouse.view.commands;

import AnimalHouse.view.ConsoleUI;

public class Load extends Command {

    public Load(ConsoleUI consoleUI) {
        super(consoleUI);
        description = "Загрузить из файла";
    }

    public void execute(){
        consoleUI.load();
    }
}
