package homework.animals.cats;

import homework.animals.Animal;
import homework.factory.AnimalType;

public class Cat extends Animal {

    @Override
    public void say() {
        System.out.println("Мяу");
    }
    @Override
    public AnimalType getType() {
        return AnimalType.CAT;
    }
}
