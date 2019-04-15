package ua.tmps;

public class Lab1_TMPS_Factory {
    public interface AnimalSound {
        void say();
    }

    public static class Cat implements AnimalSound {
        @Override
        public void say() {
            System.out.println("Meow");
        }
    }

    public static class Dog implements AnimalSound {
        @Override
        public void say() {
            System.out.println("Gav");
        }
    }

    public static class Cow implements AnimalSound {
        @Override
        public void say() {
            System.out.println("MUUUU");
        }
    }

    public static class Duck implements AnimalSound {
        @Override
        public void say() {
            System.out.println("Krya");
        }
    }

    public static class Elephant implements AnimalSound {
        @Override
        public void say() {
            System.out.println("EEAAAAA");
        }
    }

    public static class AnimalSoundFactory {
        public AnimalSoundFactory() {
        }

        public AnimalSound getSound(String soundType) {
            if (soundType == null) {
                return null;
            }
            if (soundType.equalsIgnoreCase("CAT")) {
                return new Cat();

            } else if (soundType.equalsIgnoreCase("DOG")) {
                return new Dog();

            } else if (soundType.equalsIgnoreCase("COW")) {
                return new Cow();
            } else if (soundType.equalsIgnoreCase("DUCK")) {
                return new Duck();
            } else if (soundType.equalsIgnoreCase("ELEPHANT")) {
                return new Elephant();
            }
            return null;
        }
    }

    public static void main(String[] args) {
        AnimalSoundFactory animalSoundFactory = new AnimalSoundFactory();
        AnimalSound animalSound1 = animalSoundFactory.getSound("CAT");
        animalSound1.say();
        AnimalSound animalSound2 = animalSoundFactory.getSound("DOG");
        animalSound2.say();
        AnimalSound animalSound3 = animalSoundFactory.getSound("COW");
        animalSound3.say();
        AnimalSound animalSound4 = animalSoundFactory.getSound("DUCK");
        animalSound4.say();
        AnimalSound animalSound5 = animalSoundFactory.getSound("ELEPHANT");
        animalSound5.say();

    }
}
