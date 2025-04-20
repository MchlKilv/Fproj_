package AnimalHouse.model.house;

import java.util.Iterator;
import java.util.List;

public class AnimalIterator<E> implements Iterator<E> {

    private int index;
    private List<E> animalList;

    public AnimalIterator(List<E> animalList) {
        this.animalList = animalList;
    }

    @Override
    public boolean hasNext() {
        return index < animalList.size();
    }

    @Override
    public E next() {
        return animalList.get(index++);
    }
}
