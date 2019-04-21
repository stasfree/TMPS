# Лабораторная работа №1
_Подготовил студент группы TI-164, Фридман Станислав_
## Задание 
Целью данной лабораторной работы было имплементировать 5 порождающих шабловнов

## Использованные порождающие шаблоны
1. _Abstract Factory_
2. _Factory Method_
3. _Singleton_
4. _Prototype_
5. _Builder_

## _Abstract Factory_
Абстрактная фабрика — это порождающий паттерн проектирования, который позволяет создавать семейства связанных объектов, не привязываясь к конкретным классам создаваемых объектов.

![alt text](https://refactoring.guru/images/patterns/content/abstract-factory/abstract-factory.png "Logo Title Text 1")

## _Factory Method_
Фабричный метод — это порождающий паттерн проектирования, который определяет общий интерфейс для создания объектов в суперклассе, позволяя подклассам изменять тип создаваемых объектов.

![alt text](https://refactoring.guru/images/patterns/content/factory-method/factory-method.png "Logo Title Text 1")

## _Singleton_
Singleton — это порождающий паттерн проектирования, который гарантирует, что у класса есть только один экземпляр, и предоставляет к нему глобальную точку доступа.

![alt text](https://refactoring.guru/images/patterns/content/singleton/singleton.png "Logo Title Text 1")

## _Prototype_
Prototype - это порождающий паттерн проектирования, который позволяет копировать объекты, не вдаваясь в подробности их реализации.

![alt text](https://refactoring.guru/images/patterns/content/prototype/prototype.png "Logo Title Text 1")

## _Builder_
Builder - это порождающий паттерн проектирования, который позволяет создавать сложные объекты пошагово. Строитель даёт возможность использовать один и тот же код строительства для получения разных представлений объектов.

![alt text](https://refactoring.guru/images/patterns/content/builder/builder.png "Logo Title Text 1")


## Реализация 
Для того, чтобы продемонстрировать реализацию паттернов, я использовал несколько классов в моём проекте :

# Lab1_TMPS_AbstractFactory

Сначала объявлен интерфейс:

```java
public interface AnimalSound {
        void say();
    }
```

Затем создаем классы, имплементриющие этот интерфейс: Cat, Dog, BigCat, BigDog:

```java
public static class Cat implements AnimalSound {
        @Override
        public void say() {
            System.out.println("Meow");
        }
    }
```	

Создаем абстрактный класс, чтобы получить фабрики для созданных выше классов:

```java
public abstract static class AbstractFactory{
        abstract AnimalSound getSound(String soundType);
    }
```

Создаем классы-фабрики, наследующие класс AbstractFactory, чтобы создавать объект конкретного класса, базирующийся на заданной информации:

```java
public static class SmallAnimalSoundFactory extends AbstractFactory{
        @Override
        public AnimalSound getSound(String soundType) {
            if (soundType == null) {
                return null;
            }
            if (soundType.equalsIgnoreCase("CAT")) {
                return new Cat();
            } else if (soundType.equalsIgnoreCase("DOG")) {
                return new Dog();
            }
            return null;
        }
    }
```

Создаем класс - генератор фабрики, чтобы получить фабрики путем передачи таких данных, как название животного:

```java
public static class FactoryProducer{
        public static AbstractFactory getFactory(boolean bigOrNot){
            if(bigOrNot){
                return new BigAnimalSoundFactory();
            }else{
                return new SmallAnimalSoundFactory();
            }
        }
    }
```

Используем класс - генератор фабрик, чтобы получить фабрики конкретных классов, передавая информацию о животном

```java
AbstractFactory smallAnimalSoundFactory = FactoryProducer.getFactory(false);
        AnimalSound animalSound1 = smallAnimalSoundFactory.getSound("CAT");
        animalSound1.say();
        AnimalSound animalSound2 = smallAnimalSoundFactory.getSound("DOG");
        animalSound2.say();
```

Получаем:

```java
Meow
Gav
```

# Lab1_TMPS_Factory

Подобно AbstractFactory мы создаем интерфейс, наследуем от него классы и т.д. Основное различие между методом "factory" и "абстрактным factory" заключается в том, что метод factory является единственным методом, а абстрактный factory является объектом. 
С шаблоном Factory вы создаете реализации (Cat, Dog, Cow и т.д.) определенного интерфейса - в нашем случае AnimalSound. С помощью шаблона Abstract Factory мы создаем реализации конкретного интерфейса Factory. Каждый из них знает, как создавать разные виды звуков животных.

# Lab1_TMPS_Prototype

Подобно примеру выше с AbstractFactory создаем интерфейс и наследуем от него классы. Затем создаем коллекцию, чтобы хранить данные о классах:

```java
private static Hashtable<String, AnimalSound> soundMap = new Hashtable<>();
```

И присваиваем классам определенные идентификаторы(1 - Cat, 2 - Dog):

```java
public static void loadCache() {
            Cat cat = new Cat();
            cat.setId("1");
            soundMap.put(cat.getId(), cat);
            Dog dog = new Dog();
            dog.setId("2");
            soundMap.put(dog.getId(), dog);
        }
```

Метод для того, чтобы вызвать класс по Id:

```java
public static AnimalSound getSound(String soundId) {
            AnimalSound cachedSound = soundMap.get(soundId);
            return (AnimalSound) cachedSound.clone();
        }
```

Затем вызываем базу классов и достаем оттуда объект нужного класса:

```java
			AnimalSoundCache.loadCache();
            AnimalSound clonedSound1 = AnimalSoundCache.getSound("1");
            System.out.println("Animal Sound is: " + clonedSound1.getName());
```

# Lab1_TMPS_Builder

Создаем модель данных: id, имя, фамилия, год рождения, адрес, индекс, страна, номер телефона. Id будет автоинкрементироваться при добавлении новых данных.

```java
public static class Builder {
        private static int id = 1000;
        private String surname;
        private String name;
        private String birthYear;
        private String address;
        private String zipCode;
        private String country;
        private String phoneNumber;

        public Builder() {
        }
```

Информация добавляется следующим образом(подобно сеттеру):

```java
public Builder addSurname(String surname) {
            this.surname = surname;
            return this;
        }
```

метод build() возвращает собранный объект модели данных:

```java
public Lab1_TMPS_Builder.Builder build() {
            Lab1_TMPS_Builder.Builder personalData = new Lab1_TMPS_Builder.Builder();
            personalData.id = this.id;
            personalData.surname = this.surname;
            personalData.name = this.name;
            personalData.birthYear = this.birthYear;
            personalData.address = this.address;
            personalData.zipCode = this.zipCode;
            personalData.country = this.country;
            personalData.phoneNumber = this.phoneNumber;
            return personalData;
        }
```

Builder вызывается следующим образом(параметры методов вводятся заранее с клавиатуры):

```java
Builder personalData = new Builder()
                .addSurname(entered_surname)
                .addName(entered_name)
                .addBirthYear(entered_birthYear)
                .addAddress(entered_address)
                .addCountry(entered_country)
                .addZipCode(entered_zipCode)
                .addPhoneNumber(entered_phonenumber)
                .build();
        return personalData;
```

#Lab1_TMPS_Singleton

SingleObject класс будет иметь свой статичный экземпляр и приватный конструктор. В этом классе есть статичный метод, чтобы получить этот экземпляр.

```java
public class Lab1_TMPS_Singleton {
    public static class Singleton {
        private static Singleton instance = new Singleton();

        private Singleton() {
        }

        public static Singleton getInstance() {
            return instance;
        }

        public void showMessage() {
            System.out.println("This is singleton example. Please approve it!");
        }
    }

    public static void main(String[] args) {
        Singleton singleObject = new Singleton();
        singleObject.showMessage();
    }
}

```



## Вывод
В ходе данной лабораторной работы мы изучили и реализовали порождающие паттерны, они нам упрощают и структурируют код