package ua.tmps;

public class Lab2_TMPS_Bridge {
    public interface FeedingAPI {
        public void feed(int timesADay, int amount, String typeOfFood);
    }

    public static class BigDog implements FeedingAPI {
        @Override
        public void feed(int timesADay, int amount, String typeOfFood) {
            System.out.println("Feeding a big dog, " + timesADay + " times a day with " +
                    amount + " g of " + typeOfFood);
        }
    }

    public static class SmallDog implements FeedingAPI {
        @Override
        public void feed(int timesADay, int amount, String typeOfFood) {
            System.out.println("Feeding a small dog, " + timesADay + " times a day with " +
                    amount + " g of " + typeOfFood);
        }
    }

    public static abstract class Animal {
        protected FeedingAPI feedingAPI;

        protected Animal(FeedingAPI feedingAPI) {
            this.feedingAPI = feedingAPI;
        }

        public abstract void feed();
    }

    public static class Dog extends Animal {
        private int timesADay, amount;
        private String typeOfFood;

        public Dog(int timesADay, int amount, String typeOfFood, FeedingAPI feedingAPI) {
            super(feedingAPI);
            this.timesADay = timesADay;
            this.amount = amount;
            this.typeOfFood = typeOfFood;
        }

        public void feed() {
            feedingAPI.feed(timesADay, amount, typeOfFood);
        }
    }

    public static class Main {
        public static void main(String[] args) {
            Animal bigDog = new Dog(3, 500, "Meat", new BigDog());
            Animal smallDog = new Dog(2, 250, "Granules", new SmallDog());

            bigDog.feed();
            smallDog.feed();
        }
    }
}
