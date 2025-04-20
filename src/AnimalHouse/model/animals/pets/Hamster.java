package AnimalHouse.model.animals.pets;

import java.time.LocalDate;

public class Hamster extends Pets{
    public Hamster(String name, LocalDate birthdate) {
        super(name, birthdate);
        this.type += ", хомяк";
    }
}
