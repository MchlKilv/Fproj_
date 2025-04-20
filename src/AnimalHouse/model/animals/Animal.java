package AnimalHouse.model.animals;

import AnimalHouse.model.house.HouseItem;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Animal implements Serializable, HouseItem {

    private int id;
    private String name;
    public String type;
    private LocalDate birthdate;
    public List<String> commands;

    public Animal(String name, LocalDate birthdate) {
        this.name = name;
        this.birthdate = birthdate;
        this.type = "Животное";
        this.commands = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public String getCommands() {
        if (!(this.commands).isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder("Команды: ");
            stringBuilder.append(this.commands);
            return stringBuilder.toString();
        }
        return null;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }


    public void addCommand(String command) {
        this.commands.add(command);
    }

    @Override
    public String toString() {
        return "ID:" + id + ", тип: " + type + ", кличка: " + name + ", дата рождения: " + birthdate + "\n";
    }
}
