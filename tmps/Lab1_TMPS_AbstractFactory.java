package ua.tmps;

public class Lab1_TMPS_AbstractFactory {
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

    public static class BigCat implements AnimalSound {
        @Override
        public void say() {
            System.out.println("Meeeeeoooooooow!");
        }
    }

    public static class BigDog implements AnimalSound {
        @Override
        public void say() {
            System.out.println("Gaaaaaaaaaaav!");
        }
    }

    public abstract static class AbstractFactory{
        abstract AnimalSound getSound(String soundType);
    }

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

    public static class BigAnimalSoundFactory extends AbstractFactory{
        @Override
        public AnimalSound getSound(String soundType) {
            if (soundType == null) {
                return null;
            }
            if (soundType.equalsIgnoreCase("CAT")) {
                return new BigCat();
            }
            else if (soundType.equalsIgnoreCase("DOG")) {
                return new BigDog();
            }
            return null;
        }
    }

    public static class FactoryProducer{
        public static AbstractFactory getFactory(boolean bigOrNot){
            if(bigOrNot){
                return new BigAnimalSoundFactory();
            }else{
                return new SmallAnimalSoundFactory();
            }
        }
    }

    public static void main(String[] args) {
        AbstractFactory smallAnimalSoundFactory = FactoryProducer.getFactory(false);
        AnimalSound animalSound1 = smallAnimalSoundFactory.getSound("CAT");
        animalSound1.say();
        AnimalSound animalSound2 = smallAnimalSoundFactory.getSound("DOG");
        animalSound2.say();
        AbstractFactory bigAnimalSoundFactory = FactoryProducer.getFactory(true);
        AnimalSound animalSound3 = bigAnimalSoundFactory.getSound("CAT");
        animalSound3.say();
        AnimalSound animalSound4 = bigAnimalSoundFactory.getSound("DOG");
        animalSound4.say();
    }
}
