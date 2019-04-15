package ua.tmps;

import java.util.ArrayList;
import java.util.Scanner;

public class Lab1_TMPS_Builder {

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

        public Builder addSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public Builder addName(String name) {
            this.name = name;
            return this;
        }

        public Builder addBirthYear(String birthYear) {
            this.birthYear = birthYear;
            return this;
        }

        public Builder addAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder addZipCode(String zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        public Builder addCountry(String country) {
            this.country = country;
            return this;
        }

        public Builder addPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

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
    }

    private Lab1_TMPS_Builder() {
    }

    public static String readData() {
        Scanner reader = new Scanner(System.in);
        if (reader.nextLine().equals("")) {
            return null;
        } else return reader.nextLine();
    }

    public static int readNumber() {
        Scanner reader = new Scanner(System.in);
        if (reader.nextLine().equals("")) {
            return 0;
        } else return reader.nextInt();
    }

    public static boolean intToBool(int boolNumber) {
        if (boolNumber == 1) {
            return true;
        } else return false;
    }

    public static boolean decideToContinue() {
        System.out.println("------------------------------------------------------");
        System.out.println("Do you want to add more user data: \n");
        System.out.println("1 - Yes");
        System.out.println("0 - No");
        int switcher = readNumber();
        return intToBool(switcher);
    }

    public static Builder addPersonalData() {
        System.out.println("Please, specify surname: ");
        String entered_surname = readData();
        System.out.println("Please, specify name: ");
        String entered_name = readData();
        System.out.println("Please, specify year of birth");
        String entered_birthYear = readData();
        System.out.println("Please, specify address: ");
        String entered_address = readData();
        System.out.println("Please, specify zip code: ");
        String entered_zipCode = readData();
        System.out.println("Please, specify country: ");
        String entered_country = readData();
        System.out.println("Please, specify phone number: ");
        String entered_phonenumber = readData();
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
    }

    static ArrayList<Builder> personalDataList = new ArrayList<Builder>();

    public static ArrayList<Builder> addToPersonalDataList(Builder personData) {
        personalDataList.add(personData);
        return personalDataList;
    }

    public static ArrayList<Builder> processAllData() {
        ArrayList<Builder> dataOfAllPersons = null;
        while (decideToContinue()) {
            Builder dataOfOnePerson = addPersonalData();
            dataOfAllPersons = addToPersonalDataList(dataOfOnePerson);
        }
        return dataOfAllPersons;
    }

    public static void printPersonalData(ArrayList<Builder> personalDataList) {
        System.out.println("Id      Surname     Name        Birth Year      Address     Zip Code        Country     Phone Number" + "\n");
        for (Builder item : personalDataList) {
            System.out.println(item.id + "        " + item.surname + "        " + item.name + "       " + item.birthYear + "      " + item.address + "        " + item.zipCode + "        " + item.country + "        " + item.phoneNumber);
            item.id++;
        }
    }

    public static void main(String[] args) {
        System.out.println("------------------------------------------------------");
        System.out.println("Please, enter some user data: \n");
        ArrayList<Builder> allPersonalDataList = processAllData();
        printPersonalData(allPersonalDataList);
    }
}
