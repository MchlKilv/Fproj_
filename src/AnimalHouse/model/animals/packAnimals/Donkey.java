package AnimalHouse.model.animals.packAnimals;

import java.time.LocalDate;

public class Donkey extends PackAnimals {
    public Donkey(String name, LocalDate birthdate) {
        super(name, birthdate);
        this.type += ", осел";
    }
}
