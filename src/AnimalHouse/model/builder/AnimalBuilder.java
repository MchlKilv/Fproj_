package AnimalHouse.model.builder;

import AnimalHouse.model.animals.Animal;
import AnimalHouse.model.animals.packAnimals.Camel;
import AnimalHouse.model.animals.packAnimals.Donkey;
import AnimalHouse.model.animals.packAnimals.Horse;
import AnimalHouse.model.animals.pets.Cat;
import AnimalHouse.model.animals.pets.Dog;
import AnimalHouse.model.animals.pets.Hamster;

import java.time.LocalDate;

public class AnimalBuilder {

    public Animal build(int type, String name, LocalDate birthdate) {
        switch (type) {
            case 1 -> {
                return new Cat(name, birthdate);
            }
            case 2 -> {
                return new Dog(name, birthdate);
            }
            case 3 -> {
                return new Hamster(name, birthdate);
            }
            case 4 -> {
                return new Camel(name, birthdate);
            }
            case 5 -> {
                return new Donkey(name, birthdate);
            }
            case 6 -> {
                return new Horse(name, birthdate);
            }
        }
        return null;
    }
}
