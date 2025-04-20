package AnimalHouse.view.commands;

import AnimalHouse.view.ConsoleUI;

public class Counter extends Command {

    public Counter(ConsoleUI consoleUI) {
        super(consoleUI);
        description = "Счетчик животных";
    }

    public void execute(){
        consoleUI.counter();
    }
}
