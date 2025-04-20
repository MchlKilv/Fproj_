package AnimalHouse.view;

import AnimalHouse.model.writer.Writable;
import AnimalHouse.presenter.Presenter;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class ConsoleUI implements View {
    private Presenter presenter;
    private Scanner scanner;
    private boolean work;
    private MainMenu menu;

    public ConsoleUI() {
        scanner = new Scanner(System.in);
        presenter = new Presenter(this);
        work = true;
        menu = new MainMenu(this);
    }

    private void hello() {
        System.out.println("Добро пожаловать!");
    }

    private void choice() {
        String value = scanner.nextLine();
        if (checkMenu(value)) {
            int num = Integer.parseInt(value);
            menu.execute(num);
        }
    }

    public void finish() {
        System.out.println("Работа приложения завершена.");
        scanner.close();
        work = false;
    }

    private void printMenu() {
        System.out.println("Выберите пункт меню.");
        System.out.println(menu.menu());
    }

    public void setWritable(Writable writable) {
        presenter.setWritable(writable);
    }

    public void load() {
        try {
            presenter.load();
        } catch (RuntimeException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addAnimal() {
        System.out.println("Какое животное Вы хотите создать? \n 1 - Кот \n 2 - Собака \n 3 - Хомяк \n 4 - Верблюд \n 5 - Осел \n 6 - Лошадь \n Введите число: ");
        int type = checkAnimalType();
        System.out.println("Введите имя: ");
        String name = scanner.nextLine();
        System.out.println("Введите год рождения");
        int year = checkInt();
        System.out.println("Введите месяц рождения");
        int month = checkInt();
        System.out.println("Введите день рождения");
        int day = checkInt();
        LocalDate birthdate = null;
        if (presenter.dateIsValid(year, month, day)) {
            birthdate = presenter.setBirthDate(year, month, day);
        } else {
            System.out.println("Дата указана неверно!");
            error();
        }
        presenter.addAnimal(type, name, birthdate);
    }

    private int checkInt() {
        int value = 0;
        boolean i = true;
        while (i) {
            String text = scanner.nextLine();
            if (text.matches("[0-9]+")) {
                value = Integer.parseInt(text);
                i = false;
            } else {
                System.out.println("Неверное значение! Введите целое число.");
            }
        }
        return value;
    }

    private int checkAnimalType() {
        int value = 0;
        boolean i = true;
        while (i) {
            String text = scanner.nextLine();
            if (text.matches("[0-6]+")) {
                value = Integer.parseInt(text);
                i = false;
            } else {
                System.out.println("Неверное значение! Введите число от 1 до 6.");
            }
        }
        return value;
    }

    private int checkId() {
        boolean i = true;
        int id = 0;
        while (i) {
            id = checkInt();
            if (presenter.checkId(id)) {
                i = false;
                return id;
            } else System.out.println("Животное с таким Id не найдено. Введите другое значение.");
        }
        return id;
    }

    private boolean checkMenu(String text) {
        try {
            if (Integer.parseInt(text) > 0 && Integer.parseInt(text) <= menu.getSize()) {
                return true;
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Вы ввели неверное значение!");
        return false;
    }

    public void save() {
        if (presenter.save()) {
            success();
        } else {
            error();
        }
    }

    private void success() {
        System.out.println("Данные сохранены.");
    }

    private void error() {
        System.out.println("Данные не записаны!");
    }


    public void viewCommands() {
        System.out.println("Введите ID: ");
        int id = checkId();
        presenter.viewCommands(id);
    }

    public void counter() {
        System.out.println("Всего создано животных: " + Integer.toString(presenter.counter()));
    }

    public void addCommand() {
        System.out.println("Введите ID животного: ");
        int animalId = checkId();
        System.out.println("Введите команду: ");
        String command = scanner.nextLine();
        presenter.addCommand(animalId, command);
    }

    public void sortBirthDate() {
        presenter.sortBirthDate();
    }

    public void deleteAnimal() {
        System.out.println("Введите ID животного: ");
        int animalId = checkId();
        if (presenter.deleteAnimal(animalId)) {
            success();
        }
        else {
            error();
        }
    }

    @Override
    public void start() {
        hello();
        while (work) {
            printMenu();
            choice();
        }
    }

    @Override
    public void printAnswer(String answer) {
        System.out.println(answer);
    }
}
