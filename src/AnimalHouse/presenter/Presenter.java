package AnimalHouse.presenter;

import AnimalHouse.model.service.Service;
import AnimalHouse.model.writer.Writable;
import AnimalHouse.view.View;

import java.io.IOException;
import java.time.LocalDate;

public class Presenter {


    private View view;
    private Service service;

    public Presenter(View view) {
        this.view = view;
        service = new Service();
    }

    public void setWritable(Writable writable) {
        service.setWritable(writable);
    }

    public void load() throws IOException {
        service.load();
    }

    public int counter() {
        return service.counter();
    }

    public boolean dateIsValid(int year, int month, int day) {
        return service.dateIsValid(year, month, day);
    }

    public LocalDate setBirthDate(int year, int month, int day) {
        if (dateIsValid(year, month, day)) {
            return service.checkDate(year, month, day);
        }
        return null;
    }

    public void addAnimal(int type, String name, LocalDate birthdate) {
        view.printAnswer(service.addAnimal(type, name, birthdate).toString());
    }

    public boolean checkId(int id) {
        return service.checkId(id);
    }

    public boolean save() {
        return service.save();
    }

    public void viewCommands(int id) {
        view.printAnswer(service.commandsByID(id));
    }

    public void addCommand(int animalId, String command) {
        service.addCommand(animalId, command);
    }

    public void sortBirthDate() {
        service.sortBirthDate();
        view.printAnswer(service.getAnimals());
    }

    public boolean deleteAnimal(int id) {
        return service.deleteAnimal(id);
    }
}
