package utils;

import org.example.entity.Address;
import org.example.entity.Bank;

public class TestUtils {

    public static final Address ADDRESS = new Address("TestCountry", "TestCity", "TestStreet", "TestPostalCode");

    public static final Bank BANK = new Bank("TestBank", ADDRESS);

    public static final String SELECT_BANKS = "SELECT b FROM Bank b";

    public static final String SELECT_BANK_BY_NAME = "SELECT b FROM Bank b WHERE b.name=:name";
}
