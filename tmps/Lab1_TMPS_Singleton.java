package ua.tmps;

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
