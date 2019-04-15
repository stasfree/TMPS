package ua.tmps;

public class Lab2_TMPS_Decorator {
    public interface Computer {
        void assemble();
    }
    public static class BasicComputer implements Computer {
        @Override
        public void assemble() {
            System.out.print("Assembling a basic computer.");
        }
    }
    public static abstract class ComputerDecorator implements Computer {
        protected Computer computer;

        public ComputerDecorator(Computer computer) {
            this.computer = computer;
        }

        @Override
        public void assemble() {
            this.computer.assemble();
        }
    }
    public static class GamingComputer extends ComputerDecorator {
        public GamingComputer(Computer computer) {
            super(computer);
        }

        @Override
        public void assemble() {
            super.assemble();
            System.out.print(" Adding characteristics of a gaming computer! ");
        }
    }
    public static class WorkComputer extends ComputerDecorator {
        public WorkComputer(Computer computer) {
            super(computer);
        }

        @Override
        public void assemble() {
            super.assemble();
            System.out.print(" Adding characteristics of a work computer! ");
        }
    }
    public static class Main {
        public static void main(String[] args) {
            Computer gamingComputer = new GamingComputer(new BasicComputer());
            gamingComputer.assemble();
            System.out.println("\n");

            Computer workComputer = new WorkComputer(new GamingComputer(new BasicComputer()));
            workComputer.assemble();
        }
    }
}
