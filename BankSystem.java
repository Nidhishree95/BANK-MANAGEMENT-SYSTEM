import java.util.Scanner;

// This is a super class

class BankAccount {
    protected static boolean isAccountCreated = false;
    static float balance = 0.0f;

    // Method for greeting

    void welcoming() {
        System.out.println("----------------------");
        System.out.println("----------------------");
        System.out.println("BANK MANAGEMENT SYSTEM");
        System.out.println("Welcome to the JAVA CONSOLE BANK :)");
    }

    // Method having functionalities of the bank

    void bankOptions() {

        System.out.println("----------------------");
        System.out.println("----------------------");
        System.out.println("1. Create an account");
        System.out.println("2. Deposit amount");
        System.out.println("3. Withdraw amount");
        System.out.println("4: View balance amount");
        System.out.println("5. Delete the account");
        System.out.println("6. Exit");

        Scanner sc = new Scanner(System.in);
        System.out.println("----------------------");
        System.out.println("Enter any choice (from above) : ");
        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1:
                System.out.println("----------------------");
                System.out.println("You have selected 1st option (To create an account)");
                CreateAccount create = new CreateAccount();

                if (isAccountCreated) {
                    System.out.println("Account already exists.. Kindly delete that before proceeding");
                    bankOptions();
                }

                else {
                    try {
                        create.inputData();
                        isAccountCreated = true;
                    } catch (InvalidNameException | InvalidDOBException | InvalidPlaceException
                            | InvalidPasswordException e) {
                        System.out.println(e.getMessage());
                        bankOptions();
                    }
                }
                break;

            case 2:
                System.out.println("----------------------");
                if (isAccountCreated) {
                    System.out.println("You have selected 2nd option (To deposit amount)");
                    DepositAccount deposit = new DepositAccount();
                    deposit.depositAmount();
                } else {
                    System.out.println("Kindly create an account first!");
                }
                bankOptions();
                break;

            case 3:
                System.out.println("----------------------");
                if (isAccountCreated) {
                    System.out.println("You have selected 3rd option (To withdraw amount)");
                    WithdrawAccount withdraw = new WithdrawAccount();
                    withdraw.withdrawAmount();
                } else {
                    System.out.println("Kindly create an account first!");
                }
                bankOptions();
                break;

            case 4:
                System.out.println("----------------------");
                if (isAccountCreated) {
                    System.out.println("You have selected 4th option (To view balance)");
                    BalanceAccount balance = new BalanceAccount();
                    balance.balanceAmount();
                } else {
                    System.out.println("Kindly create an account first!");
                }
                bankOptions();
                break;

            case 5:
                System.out.println("----------------------");
                if (isAccountCreated) {
                    System.out.println("You have selected 5th option (To delete your account)");
                    DeleteAccount del = new DeleteAccount();
                    del.deleteAmount();
                } else {
                    System.out.println("Kindly create an account first");
                }
                bankOptions();
                break;

            case 6:
                ExitAccount exit = new ExitAccount();
                exit.exitAmount();
                break;

            default:
                System.out.println("----------------------");
                System.out.println("Invalid choice");
                break;

        }
    }

    public boolean isAccountCreated() {
        return this.isAccountCreated;
    }

    public void setAccountCreated(boolean status) {
        this.isAccountCreated = status;
    }

    // Method for terminating
    public void terminate() {
        System.exit(0);
    }
}

class InvalidNameException extends Exception {
    public InvalidNameException(String message) {
        super(message);
    }
}

class InvalidDOBException extends Exception {
    public InvalidDOBException(String message) {
        super(message);
    }
}

class InvalidPlaceException extends Exception {
    public InvalidPlaceException(String message) {
        super(message);
    }
}

class InvalidPasswordException extends Exception {
    public InvalidPasswordException(String message) {
        super(message);
    }
}

// Class for creating an account

class CreateAccount extends BankAccount {
    String name;
    String dob;
    String place;
    String password;
    String confirmPassword;

    // Method for inputing user details

    public void inputData()
            throws InvalidNameException, InvalidDOBException, InvalidPlaceException, InvalidPasswordException {
        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("----------------------");
                System.out.println("Enter your name: ");
                name = sc.nextLine();
                if (name.isEmpty() || !name.matches("[a-z A-Z]+")) {
                    throw new InvalidNameException("Invalid name entered");
                } else {
                    break;
                }
            } catch (InvalidNameException e) {
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            try {
                System.out.println("----------------------");
                System.out.println("Enter your DOB (Eg: 25th Jan 2001 or 25/01/2001): ");
                dob = sc.nextLine();

                boolean matchesFirstFormat = dob.matches("\\d{2}/\\d{2}/\\d{4}");
                boolean matchesSecondFormat = dob
                        .matches("\\d{1,2}(st|nd|rd|th) (Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec) \\d{4}");

                if (!matchesFirstFormat && !matchesSecondFormat) {
                    throw new InvalidDOBException("Invalid DOB format.");
                } else {
                    break;
                }
            }

            catch (InvalidDOBException e) {
                System.out.println(e.getMessage());
            }
        }

        boolean isValidInput;
        do {
            try {
                System.out.println("----------------------");

                System.out.println("Enter your state: ");
                String state = sc.nextLine();

                System.out.println("----------------------");
                System.out.println("Enter your country: ");
                String country = sc.nextLine();

                if (state.isEmpty() || country.isEmpty() || !state.matches("[a-z A-Z]+")
                        || !country.matches("[a-z A-Z]+")) {
                    throw new InvalidPlaceException("Invalid state or country entered");
                } else {
                    place = state + " , " + country;
                    isValidInput = true;
                }
            } catch (InvalidPlaceException e) {
                System.out.println(e.getMessage());
                isValidInput = false;
            }
        } while (!isValidInput);

        boolean isValidCond;
        {
            do {
                try {
                    System.out.println("----------------------");
                    System.out.println("Enter your password (characters):");
                    password = sc.nextLine();
                    if (password.isEmpty() || !password.matches("[a-zA-Z0-9!@#$%^&*()_+\\-\\[\\]{};':\",.<>\\/?]+")) {
                        throw new InvalidPasswordException("Invalid password entered");
                    }

                    System.out.println("----------------------");
                    System.out.println("Confirm Password :");
                    confirmPassword = sc.nextLine();

                    if (!password.equals(confirmPassword)) {
                        throw new InvalidPasswordException("Passwords do not match");
                    }

                    else {
                        password = password + "," + confirmPassword;
                        isValidCond = true;
                    }
                }

                catch (InvalidPasswordException e) {
                    System.out.println(e.getMessage());
                    isValidCond = false;
                }
            } while (!isValidCond);

            try {
                Thread.sleep(2000); // Delay for 2 seconds (2000 milliseconds)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("----------------------");
            System.out.println("----------------------");
            System.out.println("Congratulations! Account created successfully");
            setAccountCreated(true);
            System.out.println("Continue.. ");
            bankOptions();
        }
    }
}

// Class for depositing amount

class DepositAccount extends BankAccount {
    public void depositAmount() {

        do {
            float amount;
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the amount: ");
            amount = sc.nextFloat();

            if (amount < 0) {
                System.out.println("----------------------");
                System.out.println("Kindly enter a positive number: ");
                System.out.println("----------------------");
                continue;
            }
            System.out.println("----------------------");
            System.out.println("Rs " + amount + " deposited successfully");
            balance += amount;
            System.out.println("Continue..");
            bankOptions();
        } while (true);
    }
}

// Class for withdrawing amount

class WithdrawAccount extends BankAccount {
    public void withdrawAmount() {
        Scanner sc = new Scanner(System.in);
        System.out.println("----------------------");
        System.out.println("----------------------");
        System.out.println("Enter the amount you want to withdraw: ");
        float amount = sc.nextFloat();

        if (balance < amount) {
            System.out.println("----------------------");
            System.out.println("Insufficient funds. Deposit first");
            DepositAccount dep = new DepositAccount();
            dep.depositAmount();
        }

        else {
            System.out.println("----------------------");
            System.out.println("----------------------");
            System.out.println("Withdrawing required amount....");
            balance -= amount;
            System.out.println("----------------------");
            System.out.println("Rs " + amount + " withdrawn successfully");
            System.out.println("Continue..");
            System.out.println("----------------------");
            sc.nextLine();
            bankOptions();
        }
    }
}

// Class for viewing balance amount

class BalanceAccount extends BankAccount {
    public void balanceAmount() {
        System.out.println("----------------------");
        System.out.println("----------------------");
        System.out.println("Current balance amount: " + "Rs " + balance);
        System.out.println("Continue..");
        System.out.println("----------------------");
        BankAccount bal = new BankAccount();
        bal.bankOptions();
    }
}

// Class for deleting the account

class DeleteAccount extends BankAccount {
    public void deleteAmount() {
        Scanner sc = new Scanner(System.in);
        System.out.println("----------------------");
        System.out.println("----------------------");
        System.out.println("Are you sure you want to delete the account? (Y/N)");
        String reply = sc.nextLine();
        try {
            Thread.sleep(3000); // Delay for 3 seconds (3000 milliseconds)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if ("Y".equalsIgnoreCase(reply)) {
            System.out.println("----------------------");
            System.out.println("Account deleted successfully");
            System.exit(0);
        }

        else if ("N".equalsIgnoreCase(reply)) {
            System.out.println("----------------------");
            System.out.println("Account not deleted");
            System.out.println("----------------------");
            System.out.println("Continue...");
            bankOptions();
        }
    }
}

// Class for exiting the account

class ExitAccount extends BankAccount {
    public void exitAmount() {
        try {
            Thread.sleep(3000); // Delay for 3 seconds (3000 milliseconds)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("----------------------");
        System.out.println("----------------------");
        System.out.println("Exiting from the bank...");
        System.out.println("Thank you for using this console bank!");
        System.out.println("----------------------");
        System.out.println("----------------------");
        System.exit(0);
    }
}

// Main class

class BankSystem {
    public static void main(String[] args) {
        BankAccount acc = new BankAccount();
        acc.welcoming();
        acc.bankOptions();
    }
}
