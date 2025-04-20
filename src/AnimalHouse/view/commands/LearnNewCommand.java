package AnimalHouse.view.commands;

import AnimalHouse.view.ConsoleUI;

public class LearnNewCommand extends Command {

    public LearnNewCommand(ConsoleUI consoleUI) {
        super(consoleUI);
        description = "Обучить новой команде";
    }

    public void execute(){
        consoleUI.addCommand();
    }
}
