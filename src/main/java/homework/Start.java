package homework;

import homework.animals.Animal;
import homework.animals.Color;
import homework.animals.birds.Flying;
import homework.db.IDBConnectionManager;
import homework.factory.AnimalFactory;
import homework.factory.AnimalType;
import homework.factory.DBFactory;
import homework.tables.AnimalTable;
import homework.tables.InterfaceAnimalTable;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Start {

   private static final Scanner scanner = new Scanner(System.in);

   public static void main(String[] args) throws SQLException {
      IDBConnectionManager connectionManager = new DBFactory().getConnectionManager("SQL_DB");
      AnimalFactory factory = new AnimalFactory();
      InterfaceAnimalTable animalTable = new AnimalTable(factory, connectionManager);

      Command currentCommand;
      do {
         currentCommand = askCommand();

         if (currentCommand == Command.LIST) {
            List<Animal> animalsFromDb = animalTable.findAllAnimals();
            if (animalsFromDb.isEmpty()) {
               System.out.println("Список пуст");
            }
            for (Animal animal : animalsFromDb) {
               System.out.println(animal);
            }
         } else if (currentCommand == Command.ADD) {
            AnimalType animalType = askAnimalTye();
            Animal animal = factory.create(animalType);


            animal.setName(askName());
            animal.setAge(askAge());
            animal.setWeight(askWeight());
            animal.setColor(askColor());
            animal.say();
            animalTable.create(animal);
            if (animal instanceof Flying) {
               ((Flying) animal).fly();
            }
         }
      } while (currentCommand != Command.EXIT);
      scanner.close();
   }

   private static Command askCommand() {
      String input = null;
      do {
         if (input != null) {
            System.out.println("Введена неверная команда, попробуйте снова ");
         }
         System.out.printf("Введите одну из команд (%s) ", String.join("/", Command.VALUES));
         input = scanner.next();
      } while (Command.Validity(input));
      return Command.fromString(input);
   }

   private static AnimalType askAnimalTye() {
      String input = null;
      do {
         if (input != null) {
            System.out.println("Введен неверный тип, попробуйте снова ");
         }
         System.out.printf("Введите тип животного (%s) ", String.join("/", AnimalType.VALUES));
         input = scanner.next();
      } while (AnimalType.Validity(input));
      return AnimalType.fromString(input);
   }

   private static String askName() {
      String input;
      do {
         System.out.println("Введите имя животного");
         input = scanner.next().trim();
         if (input.isEmpty()) {
            System.out.println("Имя не введено, попробуйте снова");
         }
         if (isDigit(input)) {
            System.out.println("Имя не может состоять из цифр");
         }
      } while (input.isEmpty() || isDigit(input));

      return input;
   }

   private static boolean isDigit(String s) {
      try {
         Integer.parseInt(s);
         return true;
      } catch (NumberFormatException e) {
         return false;
      }
   }

   private static int askAge() {
      int input = 0;
      do {
         System.out.println("Введите возраст животного");
         try {
            String raw = scanner.next().trim();
            input = Integer.parseInt(raw);
            if (input <= 0) {
               System.out.println("Введен неверный возраст, попробуйте снова");
            }
         } catch (NumberFormatException e) {
            System.out.println("Укажите возраст цифрами");
         }
      } while (input <= 0);
      return input;
   }

   private static int askWeight() {
      int input = 0;
      do {
         System.out.println("Введите вес животного");
         try {
            String raw = scanner.next().trim();
            input = Integer.parseInt(raw);
            if (input <= 0) {
               System.out.println("Введен неверный вес, попробуйте снова");
            }
         } catch (NumberFormatException e) {
            System.out.println("Укажите вес цифрами");
         }
      } while (input <= 0);
      return input;
   }

   private static Color askColor() {
      String input;
      do {
         System.out.printf("Введите цвет животного, доступные цвета (%s) ", String.join("/", Color.VALUES));
         input = scanner.next().trim();
         if (input.isEmpty()) {
            System.out.println("Цвет не введён, попробуйте снова");
         }
         Color selectColor = findColor(input);
         if (selectColor != Color.UNDEFINED) {
            return selectColor;
         } else {
            System.out.println("Цвет '" + input + "' не найден. Доступные цвета: Белый, Чёрный, Коричневый, Рыжий. Попробуйте снова.");
         }
      } while (true);
   }

   private static Color findColor(String value) {
      for (Color color : Color.values()) {
         if (color.getValue().equalsIgnoreCase(value)) {
            return color;
         }
      }
      return Color.UNDEFINED;
   }


}
