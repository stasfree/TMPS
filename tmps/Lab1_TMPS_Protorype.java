package ua.tmps;

import java.util.Hashtable;

public class Lab1_TMPS_Protorype {
    public static abstract class AnimalSound implements Cloneable {
        private String id;
        protected String name;

        abstract void say();

        public String getName() {
            return name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object clone() {
            Object clone = null;
            try {
                clone = super.clone();
            } catch (CloneNotSupportedException ex) {
                ex.printStackTrace();
            }

            return clone;
        }
    }

    public static class Cat extends AnimalSound {
        public Cat() {
            name = "Cat";
        }

        @Override
        public void say() {
            System.out.println("MEEOOWW");
        }
    }

    public static class Dog extends AnimalSound {
        public Dog() {
            name = "Dog";
        }

        @Override
        public void say() {
            System.out.println("GAAAVVV");
        }
    }

    public static class AnimalSoundCache {
        private static Hashtable<String, AnimalSound> soundMap = new Hashtable<>();

        public static AnimalSound getSound(String soundId) {
            AnimalSound cachedSound = soundMap.get(soundId);
            return (AnimalSound) cachedSound.clone();
        }

        public static void loadCache() {
            Cat cat = new Cat();
            cat.setId("1");
            soundMap.put(cat.getId(), cat);
            Dog dog = new Dog();
            dog.setId("2");
            soundMap.put(dog.getId(), dog);
        }
    }

    public static class PrototypePattern {
        public static void main(String[] args) {
            AnimalSoundCache.loadCache();
            AnimalSound clonedSound1 = AnimalSoundCache.getSound("1");
            System.out.println("Animal Sound is: " + clonedSound1.getName());
            AnimalSound clonedSound2 = AnimalSoundCache.getSound("2");
            System.out.println("Animal Sound is: " + clonedSound2.getName());
        }
    }
}
