package AnimalHouse.model.house;

import AnimalHouse.model.AnimalComparatorByBirthDate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class House<E extends HouseItem> implements Serializable, Iterable<E> {

    private List<E> animalsInHouse;
    private int oneId = 1; // хочу нумерацию с 1
    private int counter = 0;

    public House() {
        animalsInHouse = new ArrayList<>();
    }

    public void addNew(E animal) {
        animal.setId(oneId++);
        animalsInHouse.add(animal);
        counter++;
    }

    public int getCounter() {
        return counter;
    }

    public E findInHouse(int id) {
        for (E one : animalsInHouse) {
            if (one.getId() == id) {
                return one;
            }
        }
        return null;
    }

    public String commandsById(int id) {
        StringBuilder sb = new StringBuilder("Id - ");
        sb.append(id).append("\n").append("\n");
        E animal = findInHouse(id);
        if (animal != null) {
            sb.append("Кличка: ").append(animal.getName() + "\n");
            sb.append("Тип: ").append(animal.getType() + "\n");
            sb.append(animal.getCommands() + "\n");
            return sb.toString();
        }
        return "Данных нет.";
    }

    public void sortBirthDate() {
        animalsInHouse.sort(new AnimalComparatorByBirthDate<>());
    }

    public String getAnimals() {
        StringBuilder stringBuilder = new StringBuilder();
        for (E one : animalsInHouse) {
            stringBuilder.append(one);
        }
        return stringBuilder.toString();
    }

    public void deleteAnimal(int id) {
        E animal = findInHouse(id);
        animalsInHouse.remove(animal);
        counter--;
    }

    @Override
    public Iterator<E> iterator() {
        return new AnimalIterator<>(animalsInHouse);
    }

}
