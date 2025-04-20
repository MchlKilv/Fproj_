package AnimalHouse.model.service;

import AnimalHouse.model.animals.Animal;
import AnimalHouse.model.builder.AnimalBuilder;
import AnimalHouse.model.house.House;
import AnimalHouse.model.writer.Writable;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;

public class Service {

    private House house;
    private AnimalBuilder builder;
    private Writable writable;

    public Service() {
        house = new House();
        builder = new AnimalBuilder();
    }

    public void setWritable(Writable writable) {
        this.writable = writable;
    }

    public void load() throws IOException {
        house = (House) writable.read("output.data");
    }

    public LocalDate checkDate(int year, int month, int day) {
        LocalDate date = null;
        if (dateIsValid(year, month, day)) {
            date = LocalDate.of(year, month, day);
        }
        return date;
    }

    public boolean dateIsValid(int year, int month, int day) {
        try {
            LocalDate date = LocalDate.of(year, month, day);
            return true;
        } catch (DateTimeException e) {
            return false;
        }
    }

    public Animal addAnimal(int type, String name, LocalDate birthdate) {
        Animal animal = builder.build(type, name, birthdate);
        house.addNew(animal);
        return animal;
    }

    public boolean checkId(int id) {
        Animal animal = (Animal) house.findInHouse(id);
        return animal != null;
    }

    public boolean save() {
        return writable.write(house, "output.data");
    }

    public String commandsByID(int id) {
        return house.commandsById(id);
    }

    public int counter() {
        return house.getCounter();
    }

    public void addCommand(int animalId, String command) {
        Animal animal = (Animal) house.findInHouse(animalId);
        animal.addCommand(command);
    }

    public void sortBirthDate() {
        house.sortBirthDate();
    }

    public String getAnimals() {
        return house.getAnimals();
    }

    public boolean deleteAnimal(int id) {
        Animal animal = (Animal) house.findInHouse(id);
        if (animal != null) {
            house.deleteAnimal(id);
            return true;
        }
        return false;
    }
}
