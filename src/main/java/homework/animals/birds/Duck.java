package homework.animals.birds;

import homework.animals.Animal;
import homework.factory.AnimalType;

public class Duck extends Animal implements Flying {

    @Override
    public void say() {
        System.out.println("Кря");
    }

    @Override
    public void fly() {
        System.out.println("Я лечу");
    }

    @Override
    public AnimalType getType() {
        return AnimalType.DUCK;
    }
}
