package util;

import org.example.entity.Account;
import org.example.entity.AccountType;
import org.example.entity.Address;
import org.example.entity.Bank;
import org.example.entity.Branch;
import org.example.entity.Customer;
import org.example.entity.Loan;
import org.example.entity.LoanType;

import java.util.Date;

public class TestUtils {

    public static final Address ADDRESS = new Address("TestCountry", "TestCity", "TestStreet", "TestPostalCode");

    public static final Bank BANK = new Bank("TestBank", ADDRESS);

    public static final Branch BRANCH = new Branch("TestBranch", ADDRESS);

    public static final Customer CUSTOMER = new Customer(
            "FirstName",
            "LastName",
            new Date(),
            "0000000000000",
            "000000000",
            "test@mail.com");

    public static final String SELECT_BANKS = "SELECT b FROM Bank b";

    public static final String SELECT_BANK_BY_NAME = "SELECT b FROM Bank b WHERE b.name=:name";

    public static final String SELECT_BRANCHES = "SELECT b FROM Branch b";

    public static final String SELECT_BRANCH_BY_NAME = "SELECT b FROM Branch b WHERE b.name = :name";

    public static final String SELECT_CUSTOMER_BY_EMAIL = "SELECT c FROM Customer c WHERE c.email = :email";

    public static final Loan LOAN = new Loan(LoanType.BUSINESS);

    public static final Account ACCOUNT = new Account(AccountType.BUSINESS);

    public static final String SELECT_LOAN_BY_ID = "SELECT l FROM Loan l WHERE l.id = :id";

    public static final String SELECT_LOANS = "SELECT l FROM Loan l";

    public static final String SELECT_ACCOUNTS = "SELECT a FROM Account a";

    public static final String SELECT_ACCOUNT_BY_ID = "SELECT a FROM Account a WHERE id = :id";
}
