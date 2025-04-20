package AnimalHouse.model.house;

import java.time.LocalDate;

public interface HouseItem {

    LocalDate getBirthdate();

    void setId(int id);

    int getId();

    String getName();

    String getCommands();

    String getType();
}
