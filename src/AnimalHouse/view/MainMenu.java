package AnimalHouse.view;

import AnimalHouse.view.commands.*;

import java.util.ArrayList;
import java.util.List;

public class MainMenu {

    private List<Command> commandList;

    public MainMenu(ConsoleUI consoleUI) {
        commandList = new ArrayList<>();
        commandList.add(new Load(consoleUI));
        commandList.add(new AddAnimal(consoleUI));
        commandList.add(new AnimalCommands(consoleUI));
        commandList.add(new LearnNewCommand(consoleUI));
        commandList.add(new Counter(consoleUI));
        commandList.add(new SortByBirthDate(consoleUI));
        commandList.add(new DeleteAnimal(consoleUI));
        commandList.add(new Save(consoleUI));
        commandList.add(new Finish(consoleUI));
    }

    public String menu(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < commandList.size(); i++) {
            sb.append(i+1).append(". ").append(commandList.get(i).getDescription()).append("\n");
        }
        return sb.toString();
    }

    public void execute(int choice){
        Command command = commandList.get(choice-1);
        command.execute();
    }

    public int getSize(){
        return commandList.size();
    }
}