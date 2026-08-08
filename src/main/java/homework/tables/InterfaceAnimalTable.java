package homework.tables;

import homework.animals.Animal;
import homework.factory.AnimalType;

import java.util.List;

public interface InterfaceAnimalTable {
    List<Animal> findAllAnimals();
    List<Animal> find(Integer age, Integer weight, AnimalType type, String name);
    void create(Animal animal);
    void update(Animal animal);
}
