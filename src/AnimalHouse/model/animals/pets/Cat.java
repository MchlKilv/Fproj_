package AnimalHouse.model.animals.pets;

import AnimalHouse.model.animals.Animal;

import java.time.LocalDate;

public class Cat extends Pets {
    public Cat(String name, LocalDate birthdate) {
        super(name, birthdate);
        this.type += ", кот";
    }

}
