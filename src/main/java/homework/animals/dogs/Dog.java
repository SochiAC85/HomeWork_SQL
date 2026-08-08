package homework.animals.dogs;

import homework.animals.Animal;
import homework.factory.AnimalType;

public class Dog extends Animal {

    @Override
    public AnimalType getType() {
        return AnimalType.DOG;
    }

    @Override
    public void say() {
        System.out.println("Гав");
    }
}
