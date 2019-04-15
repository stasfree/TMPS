package ua.tmps;

public class Lab2_TMPS_Adapter {
    public interface Builder {
        public void build(String type, String location);
    }

    public interface AdvancedBuilder {
        public void buildHouse(String location);

        public void buildSkyscrapper(String location);
    }

    public static class HouseBuilder implements AdvancedBuilder {
        @Override
        public void buildHouse(String location) {
            System.out.println("Building a house located in the " + location + "area!");
        }

        @Override
        public void buildSkyscrapper(String location) {
            //don't implement
        }
    }

    public static class SkyscrapperBuilder implements AdvancedBuilder {
        @Override
        public void buildSkyscrapper(String location) {
            System.out.println("Building a skyscrapper in the " + location + "area!");
        }

        @Override
        public void buildHouse(String location) {
            //don't implement
        }
    }

    public static class BuilderAdapter implements Builder {
        AdvancedBuilder advancedBuilder;

        public BuilderAdapter(String type) {
            if (type.equalsIgnoreCase("House")) {
                advancedBuilder = new HouseBuilder();
            } else if (type.equalsIgnoreCase("Skyscrapper")) {
                advancedBuilder = new SkyscrapperBuilder();
            }
        }

        @Override
        public void build(String type, String location) {
            if (type.equalsIgnoreCase("House")) {
                advancedBuilder.buildHouse(location);
            } else if (type.equalsIgnoreCase("Skyscrapper")) {
                advancedBuilder.buildSkyscrapper(location);
            }
        }
    }

    public static class BuilderImplementation implements Builder {
        BuilderAdapter builderAdapter;

        @Override
        public void build(String type, String location) {
            if (type.equalsIgnoreCase("House") || type.equalsIgnoreCase("Skyscrapper")) {
                builderAdapter = new BuilderAdapter(type);
                builderAdapter.build(type, location);
            } else {
                System.out.println("Invalid building type.");
            }
        }
    }

    public static class Main {
        public static void main(String[] args) {
            BuilderImplementation builderImpl = new BuilderImplementation();

            builderImpl.build("house", "Downtown");
            builderImpl.build("Skyscrapper", "City Center");
            builderImpl.build("Skyscrapper", "Outskirts");
            builderImpl.build("Hotel", "City Center");
        }
    }
}
