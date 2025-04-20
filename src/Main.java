import AnimalHouse.model.writer.FIleHandler;
import AnimalHouse.view.ConsoleUI;

public class Main {
    public static void main(String[] args) {

        ConsoleUI test = new ConsoleUI();
        test.setWritable(new FIleHandler());
        test.start();

    }
}
