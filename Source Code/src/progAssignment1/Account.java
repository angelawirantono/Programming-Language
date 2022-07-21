package progAssignment1;

import java.util.*;

class BankingException extends Exception {
    BankingException () { super(); }
    BankingException (String s) { super(s); }
} 

interface BasicAccount {
    String name();
    double balance();	
}

interface WithdrawableAccount extends BasicAccount {
    double withdraw(double amount) throws BankingException;	
}

interface DepositableAccount extends BasicAccount {
    double deposit(double amount) throws BankingException;	
}

interface InterestableAccount extends BasicAccount {
    double computeInterest() throws BankingException;	
}

interface FullFunctionalAccount extends WithdrawableAccount,
                                        DepositableAccount,
                                        InterestableAccount {
}

public abstract class Account {
    // protected variables to store common attributes for every bank accounts	
    protected String accountName;
    protected double accountBalance;
    protected double accountInterestRate;
    protected Date openDate;
    protected Date lastInterestDate;
    protected int count;
    
    // public methods for every bank accounts
    public String name() {
    	return(accountName);	
    }	
    
    public double balance() {
        return(accountBalance);
    }
    
    public double deposit(double amount) throws BankingException{
        accountBalance += amount;
        return(accountBalance);
    } 
    
    abstract double withdraw(double amount, Date withdrawDate) throws BankingException;
    
    public double withdraw(double amount) throws BankingException {
        Date withdrawDate = new Date();
        return(withdraw(amount, withdrawDate));
    }
    
    abstract double computeInterest(Date interestDate) throws BankingException;
    
    public double computeInterest() throws BankingException {
        Date interestDate = new Date();
        return(computeInterest(interestDate));
    }
}

//4 account type:

//1. Checking
/*
 *  Derived class: CheckingAccount
 *
 *  Description:
 *      Interest is computed daily; there's no fee for
 *      withdraw; there is a minimum balance of $1000.
 */
                          
class CheckingAccount extends Account implements FullFunctionalAccount {
    CheckingAccount(String s, double firstDeposit) {
        accountName = s;
        accountBalance = firstDeposit;
        accountInterestRate = 0.12;
        openDate = new Date();
        lastInterestDate = openDate;	
    }
    
    CheckingAccount(String s, double firstDeposit, Date firstDate) {
        accountName = s;
        accountBalance = firstDeposit;
        accountInterestRate = 0.12;
        openDate = firstDate;
        lastInterestDate = openDate;	
    }	
    
    public double withdraw(double amount, Date withdrawDate) throws BankingException {
    // minimum balance is 1000, raise exception if violated
        if ((accountBalance  - amount) < 1000) {
            throw new BankingException ("Underfraft from checking account name:" +
                                         accountName);
        } else {
            accountBalance -= amount;	
            return(accountBalance); 	
        }                                        	
    }
    
    public double computeInterest (Date interestDate) throws BankingException {
        if (interestDate.before(lastInterestDate)) {
            throw new BankingException ("Invalid date to compute interest for account name" +
                                        accountName);                            	
        }
        
        int numberOfDays = (int) ((interestDate.getTime() 
                                   - lastInterestDate.getTime())
                                   / 86400000.0);
        System.out.println("Number of days since last interest is " + numberOfDays);
        double interestEarned = (double) numberOfDays / 365.0 *
                                      accountInterestRate * accountBalance;
        System.out.println("Interest earned is " + interestEarned); 
        lastInterestDate = interestDate;
        accountBalance += interestEarned;
        return(accountBalance);                            
    }  	
}           

//2. Saving
class SavingAccount extends Account implements FullFunctionalAccount {
	SavingAccount(String name, double firstDeposit) {
		accountName = name;
		accountBalance = firstDeposit;
		accountInterestRate = 0.12;
		openDate = new Date();
		lastInterestDate = openDate;
		count = 0;
	}
	
	SavingAccount(String name, double firstDeposit, Date firstDate) {
		accountName = name;
		accountBalance = firstDeposit;
		accountInterestRate = 0.12;
		openDate = firstDate;
		lastInterestDate = openDate;
		count = 0;
	}
	
	public double withdraw(double amount, Date withdrawDate) throws BankingException {
		//minimum balance 0
		if((accountBalance - amount) < 0) {
			throw new BankingException("Underfraft from checking account name:" + accountName);
		}
		else {
			//first 3 transaction are free and no minimum balance
			if(count < 3) {
				accountBalance-=amount;
			}
			else { 
				//after 3 transaction, fee is $1 for every transaction
				System.out.println("Transaction fee $1 after more than 3 transaction in a month\n");
				accountBalance-=(amount+1);
			}
			count++;
			return(accountBalance);
		}
	}
	
	public double computeInterest(Date interestDate) throws BankingException {
		if(interestDate.before(lastInterestDate)) {
			throw new BankingException("Invalid date to compute interest for account name" + accountName);
		}
		int numOfDays=(int)((interestDate.getTime()-lastInterestDate.getTime())/86400000.0);
		if(numOfDays >= 30) {
			count = 0; //reset transaction count
			int numOfMonths = numOfDays/30;
			System.out.println("Number of month since last interest is " + (int)numOfMonths);
			double interestEarned = (double) numOfMonths/12.0 * accountInterestRate * accountBalance;
			System.out.println("Interest earned is " + interestEarned);
			lastInterestDate = interestDate;
			accountBalance+=interestEarned;
		}
		return(accountBalance);
	}
	
	public double deposit(double amount) throws BankingException {
		if(count < 3) {
			accountBalance+=amount;
		}
		else {
			//more than 3 transaction a month
			System.out.println("Transaction fee $1 after more than 3 transaction in a month\n");
			accountBalance+=(amount-1);
		}
		count++;
		return (accountBalance);
	}
}

//3. CD
class CDAccount extends Account implements FullFunctionalAccount {
	CDAccount(String name, double firstDeposit) {
		accountName = name;
		accountBalance = firstDeposit;
		accountInterestRate = 0.12;
		openDate = new Date();
		lastInterestDate = openDate;
	}
	
	CDAccount(String name, double firstDeposit, Date firstDate) {
		accountName = name;
		accountBalance = firstDeposit;
		accountInterestRate = 0.12;
		openDate = firstDate;
		lastInterestDate = openDate;
	}
	
	public double withdraw(double amount, Date withdrawDate) throws BankingException {
		//minimum balance 0
		int numOfDays=(int)((withdrawDate.getTime()-openDate.getTime())/86400000.0);
		if((accountBalance - (amount+250)) < 0) {
			throw new BankingException("Underfraft from checking account name:" + accountName);
		}
		else {
			if(numOfDays <= 356) {
				//for 12 months, transaction fee is $250
				System.out.println("Transaction fee $250\n");
				accountBalance-=(amount+250);
			}
			else {
				//at the end of the duration the interest payments stop and you can withdraw w/o fee
				accountBalance-=amount;
			}
		}
		return(accountBalance);
	}
	
	public double computeInterest(Date interestDate) throws BankingException {
		if(interestDate.before(lastInterestDate)) {
			throw new BankingException("Invalid date to compute interest for account name" + accountName);
		}
		int numOfDays=(int)((interestDate.getTime()-lastInterestDate.getTime())/86400000.0);
		if(numOfDays >= 30) {
			count = 0; //reset transaction count
			int numOfMonths = numOfDays/30;
			System.out.println("Number of month since last interest is " + (int)numOfMonths);
			double interestEarned = (double) numOfMonths/12.0 * accountInterestRate * accountBalance;
			System.out.println("Interest earned is " + interestEarned);
			lastInterestDate = interestDate;
			accountBalance+=interestEarned;
		}
		return(accountBalance);
	}
	
	public double deposit(double amount) throws BankingException{
		//can't deposit anything during CD
		throw new BankingException("Not allowed for deposit for account name:" + accountName);
	}
}

//4. Loan
class LoanAccount extends Account implements FullFunctionalAccount {
	LoanAccount(String name, double firstDeposit){
		accountName = name;
		accountBalance = firstDeposit;
		accountInterestRate = 0.12;
		openDate = new Date();
		lastInterestDate = openDate;
	}
	
	LoanAccount(String name, double firstDeposit, Date firstDate){
		accountName = name;
		accountBalance = firstDeposit;
		accountInterestRate = 0.12;
		openDate = firstDate;
		lastInterestDate = openDate;
	}
	
	public double withdraw(double amount, Date withdrawDate) throws BankingException {
		//balance negative so can't withdraw
		throw new BankingException("Cannot make withdraw from loan account name:" + accountName);
	}
	
	public double computeInterest(Date interestDate) throws BankingException {
		if(interestDate.before(lastInterestDate)) {
			throw new BankingException("Invalid date to compute interest for account name" + accountName);
		}
		int numOfDays=(int)((interestDate.getTime()-lastInterestDate.getTime())/86400000.0);
		int numOfMonths = numOfDays/30;
		System.out.println("Number of month since last interest is " + (int)numOfMonths);
		double interestEarned = (double) numOfMonths/12.0 * accountInterestRate * -accountBalance;
		System.out.println("Interest earned is " + interestEarned);
		lastInterestDate = interestDate;
		accountBalance+=interestEarned;
		return(accountBalance);
	}
	
	public double deposit(double amount) throws BankingException {
		if(accountBalance <= 0) {
			//balance is negative
			accountBalance+=amount;
			if(accountBalance>=0)System.out.println("Loan payment has been completed for account name " + accountName);
		}
		else {
			accountBalance+=amount;
			//throw new BankingException("Loan payment has been completed for account name " + accountName);
		}
		return(accountBalance);
	}
}

