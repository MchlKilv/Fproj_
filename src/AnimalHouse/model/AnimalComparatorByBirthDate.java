package AnimalHouse.model;

import AnimalHouse.model.house.HouseItem;

public class AnimalComparatorByBirthDate<E extends HouseItem> implements java.util.Comparator<E> {
    @Override
    public int compare(E o1, E o2) {
        return o1.getBirthdate().compareTo(o2.getBirthdate());
    }
}

