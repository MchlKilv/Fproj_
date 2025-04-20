package AnimalHouse.model.animals.packAnimals;

import AnimalHouse.model.animals.Animal;
import java.time.LocalDate;

public abstract class PackAnimals extends Animal {

    public PackAnimals(String name, LocalDate birthdate) {
        super(name, birthdate);
        this.type = "Вьючное животное";
    }

}
