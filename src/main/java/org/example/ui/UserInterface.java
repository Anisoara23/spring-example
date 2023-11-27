package org.example.ui;

import org.example.entity.Account;
import org.example.entity.AccountType;
import org.example.entity.Address;
import org.example.entity.Bank;
import org.example.entity.Branch;
import org.example.entity.Customer;
import org.example.entity.CustomerAccount;
import org.example.entity.CustomerInfo;
import org.example.entity.Loan;
import org.example.entity.LoanType;
import org.example.pojo.CustomerFinancialProfile;
import org.example.service.AccountService;
import org.example.service.BankService;
import org.example.service.BranchService;
import org.example.service.CustomerService;
import org.example.service.FinancialProfileService;
import org.example.service.LoanService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static org.example.entity.Gender.FEMALE;
import static org.example.entity.Gender.MALE;
import static org.example.ui.TypeManager.getType;

@Component
public class UserInterface {

    private final BankService bankService;

    private final BranchService branchService;

    private final AccountService accountService;

    private final LoanService loanService;

    private final FinancialProfileService financialProfileService;

    private final CustomerService customerService;

    private final Scanner scanner;

    public UserInterface(BankService bankService,
                         BranchService branchService,
                         AccountService accountService,
                         LoanService loanService,
                         FinancialProfileService financialProfileService,
                         CustomerService customerService,
                         Scanner scanner) {
        this.bankService = bankService;
        this.branchService = branchService;
        this.accountService = accountService;
        this.loanService = loanService;
        this.financialProfileService = financialProfileService;
        this.customerService = customerService;
        this.scanner = scanner;
    }

    public void displayUserInterface() {
        boolean continueLoop = true;

        while (continueLoop) {
            try {
                System.out.println("\nPlease choose an option: ");
                System.out.println("1. Apply for a Loan;");
                System.out.println("2. Apply for an Account;");
                System.out.println("3. Remove a Loan;");
                System.out.println("4. Remove an Account;");
                System.out.println("5. Update Loan amount;");
                System.out.println("6. Update Account amount;");
                System.out.println("7. Exit.");

                String option = scanner.nextLine();

                switch (option) {
                    case "1":
                        createLoan();
                        break;
                    case "2":
                        createAccount();
                        break;
                    case "3":
                        removeLoan();
                        break;
                    case "4":
                        removeAccount();
                        break;
                    case "5":
                        updateLoanAmount();
                        break;
                    case "6":
                        updateAccountAmount();
                        break;
                    case "7":
                        continueLoop = false;
                        break;
                    default:
                        System.out.println("No such option!");
                }
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private void createAccount() {
        Account account = new Account(getType(AccountType.class));

        BigDecimal amount = getAmount();
        account.setAmount(amount);

        Branch branch = getBranch();
        account.setBranch(branch);
        branch.getFinancialProfiles().add(account);

        Customer customer = getCustomer();
        customerService.add(customer);

        CustomerAccount customerAccount = new CustomerAccount(new Date());
        customerAccount.setAccount(account);
        customerAccount.setCustomer(customer);

        account.getCustomers().add(customerAccount);
        customer.getAccounts().add(customerAccount);
        accountService.add(account);
    }

    private void createLoan() {
        Loan loan = new Loan(getType(LoanType.class));

        BigDecimal amount = getAmount();
        loan.setAmount(amount);

        Branch branch = getBranch();
        loan.setBranch(branch);
        branch.getFinancialProfiles().add(loan);

        Customer customer = getCustomer();
        customerService.add(customer);

        customer.getLoans().add(loan);
        loan.getCustomers().add(customer);

        loanService.add(loan);
    }

    private void removeLoan() {
        printIdsWithCustomers(
                "Select loan id to be deleted: ",
                loanService.getCustomersWithLoansIds());
        String loanId = scanner.nextLine();

        loanService.remove(loanId);
    }

    private void removeAccount() {
        printIdsWithCustomers(
                "Select account id to be deleted: ",
                accountService.getCustomersWithAccountIds());
        String accountId = scanner.nextLine();

        accountService.remove(accountId);
    }

    private void updateLoanAmount() {
        List<CustomerFinancialProfile> customersWithLoansIds = loanService.getCustomersWithLoansIds();

        printIdsWithCustomers(
                "Select Loan to be updated:",
                customersWithLoansIds);

        updateAmount();
    }

    private void updateAccountAmount() {
        List<CustomerFinancialProfile> customersWithAccountIds = accountService.getCustomersWithAccountIds();

        printIdsWithCustomers(
                "Select Account to be updated:",
                customersWithAccountIds);

        updateAmount();
    }

    private void updateAmount() {
        String id = scanner.nextLine();

        BigDecimal amount = financialProfileService.getLoanAmount(id);
        System.out.println("Initial amount = " + amount);

        System.out.println("Introduce new amount: ");
        BigDecimal newAmount = new BigDecimal(scanner.nextLine());

        financialProfileService.updateAmount(id, newAmount);
    }

    private void printIdsWithCustomers(
            String message,
            List<CustomerFinancialProfile> customerFinancialProfiles
    ) {
        System.out.println(message);
        customerFinancialProfiles.forEach(System.out::println);
    }

    private BigDecimal getAmount() {
        System.out.println("\nType amount: ");
        String balance = scanner.nextLine();
        return balance.equals("") ? null : new BigDecimal(balance);
    }

    private Customer getCustomer() {
        System.out.println("\nPlease choose an option: ");
        System.out.println("1. Add new customer;");
        System.out.println("2. Select customer from existing one;");

        String option = scanner.nextLine();

        return switch (option) {
            case "1" -> createNewCustomer();
            case "2" -> selectExistingCustomer();
            default -> throw new IllegalStateException("Unexpected value: " + option);
        };
    }

    private Customer createNewCustomer() {
        System.out.println("\nCustomer details: ");
        System.out.println("First Name: ");
        String firstName = scanner.nextLine();

        System.out.println("Last Name: ");
        String lastName = scanner.nextLine();

        System.out.println("Date of birth(dd-MM-yyyy): ");
        String dateOfBirth = scanner.nextLine();

        System.out.println("IDNP: ");
        String idnp = scanner.nextLine();

        System.out.println("Phone Number: ");
        String phoneNumber = scanner.nextLine();

        System.out.println("Email: ");
        String email = scanner.nextLine();

        CustomerInfo customerInfo = getCustomerInfo();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

        try {
            Customer customer = new Customer(
                    firstName,
                    lastName,
                    simpleDateFormat.parse(dateOfBirth),
                    idnp,
                    phoneNumber,
                    email
            );
            customer.setInfo(customerInfo);
            customerInfo.setCustomer(customer);

            return customer;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private CustomerInfo getCustomerInfo() {
        System.out.println("Country: ");
        String country = scanner.nextLine();

        System.out.println("City: ");
        String city = scanner.nextLine();

        System.out.println("Postal code: ");
        String postalCode = scanner.nextLine();

        System.out.println("Street: ");
        String street = scanner.nextLine();

        System.out.println("Occupation: ");
        String occupation = scanner.nextLine();

        System.out.println("Citizenship: ");
        String citizenship = scanner.nextLine();

        System.out.println("Gender(M/F): ");
        String gender = scanner.nextLine();

        Address address = new Address(country, city, street, postalCode);
        return new CustomerInfo(
                address,
                occupation,
                citizenship,
                gender.equalsIgnoreCase("M") ? MALE : FEMALE
        );
    }

    private Customer selectExistingCustomer() {
        List<String> emails = customerService.getEmails();
        System.out.println("Select the customer email: ");

        emails.forEach(System.out::println);
        String email = scanner.nextLine();

        return customerService.getCustomerByEmail(email);
    }

    private Branch getBranch() {
        System.out.println("\nPlease choose an option: ");
        System.out.println("1. Add new branch;");
        System.out.println("2. Select a branch from existing one;");

        String option = scanner.nextLine();

        return switch (option) {
            case "1" -> createNewBranch();
            case "2" -> selectExistingBranch();
            default -> throw new IllegalStateException("Unexpected value: " + option);
        };
    }

    private Branch createNewBranch() {
        System.out.println("\nBranch details: ");
        System.out.println("Branch Name: ");
        String name = scanner.nextLine();

        System.out.println("Branch Country: ");
        String country = scanner.nextLine();

        System.out.println("Branch City: ");
        String city = scanner.nextLine();

        System.out.println("Branch Postal code: ");
        String postalCode = scanner.nextLine();

        System.out.println("Branch Street: ");
        String street = scanner.nextLine();

        Bank bank = getBank();

        Address address = new Address(country, city, street, postalCode);
        Branch branch = new Branch(name, address);
        branch.setBank(bank);
        bank.getBranches().add(branch);

        return branch;
    }

    private Branch selectExistingBranch() {
        System.out.println("Select branch code: ");
        List<Map<Integer, String>> branches = branchService.getBranches();

        branches.forEach(System.out::println);
        String id = scanner.nextLine();

        return branchService.getBranchById(Integer.parseInt(id));
    }

    private Bank getBank() {
        System.out.println("\nPlease choose an option: ");
        System.out.println("1. Add a new bank;");
        System.out.println("2. Select existing bank;");
        String option = scanner.nextLine();

        return switch (option) {
            case "1" -> createNewBank();
            case "2" -> selectExistingBank();
            default -> throw new IllegalStateException("Unexpected value: " + option);
        };
    }

    private Bank createNewBank() {
        System.out.println("\nBank details: ");

        System.out.println("Bank name: ");
        String name = scanner.nextLine();

        System.out.println("Branch Country: ");
        String country = scanner.nextLine();

        System.out.println("Branch City: ");
        String city = scanner.nextLine();

        System.out.println("Branch Postal code: ");
        String postalCode = scanner.nextLine();

        System.out.println("Branch Street: ");
        String street = scanner.nextLine();

        Address address = new Address(country, city, street, postalCode);

        return new Bank(name, address);
    }

    private Bank selectExistingBank() {
        System.out.println("Select bank code: ");
        List<Map<String, String>> banks = bankService.getBankCodes();

        banks.forEach(System.out::println);
        String code = scanner.nextLine();

        return bankService.getBankByCode(code);
    }
}
