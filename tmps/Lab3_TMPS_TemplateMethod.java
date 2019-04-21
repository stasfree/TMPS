package ua.tmps;

import java.util.List;

public class Lab3_TMPS_TemplateMethod {
    public static abstract class Game {
        abstract void initialize();

        abstract void startPlay();

        abstract void endPlay();

        //template method
        public final void play() {

            //initialize the game
            initialize();

            //start game
            startPlay();

            //end game
            endPlay();
        }
    }

    public static class Cricket extends Game {

        @Override
        void endPlay() {
            System.out.println("Cricket Game Finished!");
        }

        @Override
        void initialize() {
            System.out.println("Cricket Game Initialized! Start playing.");
        }

        @Override
        void startPlay() {
            System.out.println("Cricket Game Started. Enjoy the game!");
        }
    }

    public static class Football extends Game {

        @Override
        void endPlay() {
            System.out.println("Football Game Finished!");
        }

        @Override
        void initialize() {
            System.out.println("Football Game Initialized! Start playing.");
        }

        @Override
        void startPlay() {
            System.out.println("Football Game Started. Enjoy the game!");
        }
    }

    public static void main(String[] args) {
        Game game = new Cricket();
        game.play();
        System.out.println();
        game = new Football();
        game.play();
    }

}
