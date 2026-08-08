package homework.factory;

import homework.animals.Animal;
import homework.animals.birds.Duck;
import homework.animals.cats.Cat;
import homework.animals.dogs.Dog;

public class AnimalFactory {

    public Animal create (AnimalType type){
        if(type == AnimalType.CAT){
            return new Cat();
        }
        if (type == AnimalType.DOG){
            return new Dog();
        }
        if (type == AnimalType.DUCK){
            return new Duck();
        }
        return null;
    }
}
