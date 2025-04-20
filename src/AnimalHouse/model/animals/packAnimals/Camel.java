package AnimalHouse.model.animals.packAnimals;

import java.time.LocalDate;

public class Camel extends PackAnimals {
    public Camel(String name, LocalDate birthdate) {
        super(name, birthdate);
        this.type += ", верблюд";
    }
}
