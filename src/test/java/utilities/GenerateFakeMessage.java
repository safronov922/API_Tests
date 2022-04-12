package utilities;

import com.github.javafaker.Faker;

public class GenerateFakeMessage {


    public static String getValidZipcode() {
        Faker faker = new Faker();
        return faker.numerify("23###");
    }

    public static String getSixDigitsZipcode() {
        Faker faker = new Faker();
        return faker.numerify("23####");
    }

    public static String getFirstName() {
        Faker faker = new Faker();
        return faker.name().firstName();
    }

    public static String getLastName() {
        Faker faker = new Faker();
        return faker.name().lastName();
    }

    public static String getEmail() {
        Faker faker = new Faker();
        return faker.internet().emailAddress();
    }

    public static String getPassword() {
        Faker faker = new Faker();
        return faker.numerify("string@##");
    }

    public static String getAccountName() {
        Faker faker = new Faker();
        return faker.beer().name();
    }

    public static String getPhone() {
        Faker faker = new Faker();
        return faker.phoneNumber().phoneNumber();
    }
    public static String getJob(){
        Faker faker = new Faker();
        return faker.internet().emailAddress();
    }


}
