package progAssignment1;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Application {
	
	public static void main( String args []) {
		Account a;
    	Date d;
    	double ret;
    	String res;
    	try {
    		FileWriter myFile = new FileWriter("B10815013_test_output_result.txt");
    		//Test for 4 type of account:
    		
    		//1. Checking
    		a = new CheckingAccount("John Smith", 1500.0);
    
    		try {
    			ret = a.withdraw(100.00);
    			System.out.println ("Account <" + a.name() + "> now has $" + ret + " balance after withdraw\n");
    			myFile.write("Account <" + a.name() + "> now has $" + ret + " balance after withdraw\n");
    		} catch (Exception e) {
    			stdExceptionPrinting(e, a.balance());	
    		}
    
    		a = new CheckingAccount("John Smith", 1500.0);
    
    		try {
    			//there is a minimum balance of $1000
    			//will throw exception
    			ret = a.withdraw(600.00);
    			System.out.println ("Account <" + a.name() + "> now has $" + ret + " balance after withdraw\n");
    			myFile.write("Account <" + a.name() + "> now has $" + ret + " balance after withdraw\n");
    		} catch (Exception e) {
    			stdExceptionPrinting(e, a.balance());	
    		}

    		try {
    			ret = a.deposit(600.00);
    			System.out.println ("Account <" + a.name() + "> now has $" + ret + " balance after deposit\n");
    			myFile.write("Account <" + a.name() + "> now has $" + ret + " balance after deposit\n");
    		} catch (Exception e) {
    			stdExceptionPrinting(e, a.balance());	
    		}
    		
    		try {
    			//show balance
    			ret = a.balance();
    			System.out.println ("Account <" + a.name() + "> now has $" + ret + " balance\n");
    			myFile.write("Account <" + a.name() + "> now has $" + ret + " balance\n");
    		} catch (Exception e) {
    			stdExceptionPrinting(e, a.balance());	
    		}
    		
    		try {
    			Calendar c = Calendar.getInstance();
    			c.set(Calendar.MONTH, 11);
    			c.set(Calendar.DATE, 25);
    			c.set(Calendar.YEAR, 2021);
    			Date interestdate = c.getTime();
    			ret = a.computeInterest(interestdate);
    			System.out.println ("Account <" + a.name() + "> now has $" + ret + " balance after interest\n");
    			myFile.write("Account <" + a.name() + "> now has $" + ret + " balance after interest\n");
    		} catch (Exception e) {
    			stdExceptionPrinting(e, a.balance());	
    		}
    		
    		//2. Saving 
    		a = new SavingAccount("William Hurt", 1200.0);
    		
    		try {
    			//first 3 transaction are free and no minimum balance
    			ret = a.withdraw(100.00);
    			System.out.println ("Account <" + a.name() + "> now has $" + ret + " balance after withdraw\n");
    			myFile.write("Account <" + a.name() + "> now has $" + ret + " balance after withdraw\n");
    			
    			ret = a.deposit(100.00);
    			System.out.println ("Account <" + a.name() + "> now has $" + ret + " balance after deposit\n");
    			myFile.write("Account <" + a.name() + "> now has $" + ret + " balance after deposit\n");
    			
    			ret = a.deposit(100.00);
    			System.out.println ("Account <" + a.name() + "> now has $" + ret + " balance after deposit\n");
    			myFile.write("Account <" + a.name() + "> now has $" + ret + " balance after deposit\n");
    			
    			//after 3 transaction, fee is $1 for every transaction
    			ret = a.deposit(100.00);
    			System.out.println ("Account <" + a.name() + "> now has $" + ret + " balance after deposit\n");
    			myFile.write("Account <" + a.name() + "> now has $" + ret + " balance after deposit\n");
    			
    			ret = a.withdraw(1000.0);
    			System.out.println ("Account <" + a.name() + "> now has $" + ret + " balance after withdraw\n");
    			myFile.write("Account <" + a.name() + "> now has $" + ret + " balance after withdraw\n");
    			
    			//making error by withdraw more money than what available
    			ret = a.withdraw(1000.0);
    			System.out.println ("Account <" + a.name() + "> now has $" + ret + " balance after withdraw\n");
    			myFile.write("Account <" + a.name() + "> now has $" + ret + " balance after withdraw\n");
    		}catch (Exception e) {
    			stdExceptionPrinting(e, a.balance());	
    		}
    		
    		try {
    			//show account name
    			res = a.name();
    			System.out.println("Account name: " + res + "\n");
    			myFile.write("Account name: " + res + "\n");
    			
    			//show balance
    			ret = a.balance();
    			System.out.println ("Account <" + a.name() + "> now has $" + ret + " balance\n");
    			myFile.write("Account <" + a.name() + "> now has $" + ret + " balance\n");
    			
    			//show monthly interest
    			Calendar c = Calendar.getInstance();
    			c.set(Calendar.MONTH, 12);
    			c.set(Calendar.DATE, 25);
    			c.set(Calendar.YEAR, 2021);
    			Date interestdate = c.getTime();
    			ret = a.computeInterest(interestdate);
    			System.out.println ("Account <" + a.name() + "> now has $" + ret + " balance after interest\n");
    			myFile.write("Account <" + a.name() + "> now has $" + ret + " balance after interest\n");
    			
    			//free for the first three transaction per month
    			ret = a.withdraw(100.00);
    			System.out.println ("Account <" + a.name() + "> now has $" + ret + " balance after withdraw\n");
    			myFile.write("Account <" + a.name() + "> now has $" + ret + " balance after withdraw\n");
    			
    			ret = a.deposit(600.00);
    			System.out.println ("Account <" + a.name() + "> now has $" + ret + " balance after deposit\n");
    			myFile.write("Account <" + a.name() + "> now has $" + ret + " balance after deposit\n");
    		}catch (Exception e) {
    			stdExceptionPrinting(e, a.balance());	
    		}
    		
    		//3. CD
    		//you can open 1 12-month CD for $5000
			a = new CDAccount("Woody Allison", 1000.0);
			
    		try {
    			//withdraw fee $250
    			ret = a.withdraw(500.00);
    			System.out.println ("Account <" + a.name() + "> now has $" + ret + " balance after withdraw\n");
    			myFile.write("Account <" + a.name() + "> now has $" + ret + " balance after withdraw\n");
    			
    			//the next 12 months you can't deposit anything
    			//throw error
    			ret = a.deposit(600.00);
    			System.out.println ("Account <" + a.name() + "> now has $" + ret + " balance after deposit\n");
    			myFile.write("Account <" + a.name() + "> now has $" + ret + " balance after deposit\n");
    		}catch (Exception e) {
    			stdExceptionPrinting(e, a.balance());	
    		}
    		
    		try {
    			//compute interest 1 month
    			Calendar c = Calendar.getInstance();
    			c.set(Calendar.MONTH, 11);
    			c.set(Calendar.DATE, 25);
    			c.set(Calendar.YEAR, 2021);
    			Date interestdate = c.getTime();
    			ret = a.computeInterest(interestdate);
    			System.out.println ("Account <" + a.name() + "> now has $" + ret + " balance after interest\n");
    			myFile.write("Account <" + a.name() + "> now has $" + ret + " balance after interest\n");
    		}catch (Exception e) {
    			stdExceptionPrinting(e, a.balance());	
    		}
    		
    		a = new CDAccount("Woody Allison", 1000.0);
    		
    		try {
    			//compute interest 1 year
    			Calendar c = Calendar.getInstance();
    			c.set(Calendar.MONTH, 11);
    			c.set(Calendar.DATE, 25);
    			c.set(Calendar.YEAR, 2022);
    			Date interestdate = c.getTime();
    			ret = a.computeInterest(interestdate);
    			System.out.println ("Account <" + a.name() + "> now has $" + ret + " balance after interest\n");
    			myFile.write("Account <" + a.name() + "> now has $" + ret + " balance after interest\n");
    			
    			//after 12 months, can withdraw w/o fee
    			ret = a.withdraw(500, interestdate);
    			System.out.println ("Account <" + a.name() + "> now has $" + ret + " balance after withdraw\n");
    			myFile.write("Account <" + a.name() + "> now has $" + ret + " balance after withdraw\n");
    		}catch (Exception e) {
    			stdExceptionPrinting(e, a.balance());	
    		}
    		
    		//4. Loan
    		a = new LoanAccount("Judi Foster", -1500.0);
    		
    		try {
    			//withdraw
    			//balance negative so can't withdraw, will throw exception
    			ret = a.withdraw(100.00);
    			System.out.println ("Account <" + a.name() + "> now has $" + ret + " balance after withdraw\n");
    			myFile.write("Account <" + a.name() + "> now has $" + ret + " balance after withdraw\n");
    		}catch (Exception e) {
    			stdExceptionPrinting(e, a.balance());	
    		}
    		
    		try {
    			//compute interest
    			Calendar c = Calendar.getInstance();
    			c.set(Calendar.MONTH, 11);
    			c.set(Calendar.DATE, 25);
    			c.set(Calendar.YEAR, 2022);
    			Date interestdate = c.getTime();
    			ret = a.computeInterest(interestdate);
    			System.out.println ("Account <" + a.name() + "> now has $" + ret + " balance after interest\n");
    			myFile.write("Account <" + a.name() + "> now has $" + ret + " balance after interest\n");
    			
    			//deposit reduce loan
    			ret = a.deposit(1000.00);
    			System.out.println ("Account <" + a.name() + "> now has $" + ret + " balance after deposit\n");
    			myFile.write("Account <" + a.name() + "> now has $" + ret + " balance after deposit\n");
    			
    			//deposit to pay all loan
    			ret = a.deposit(500.00);
    			System.out.println ("Account <" + a.name() + "> now has $" + ret + " balance after deposit\n");
    			myFile.write("Account <" + a.name() + "> now has $" + ret + " balance after deposit\n");
    			
    			//deposit after loan is paid
    			ret = a.deposit(500.00);
    			System.out.println ("Account <" + a.name() + "> now has $" + ret + " balance after deposit\n");
    			myFile.write("Account <" + a.name() + "> now has $" + ret + " balance after deposit\n");
    		}catch (Exception e) {
    			stdExceptionPrinting(e, a.balance());	
    		}
    		
    		myFile.close();
    	}catch(IOException e){
			
		}
    
    		/* put your own tests here ....... */
    		/* if your implementaion is correct, you can do the following with polymorphic array accountList
				public Account[] accountList;
		
				accountList = new Account[4];
		
				// buid 4 different accounts in the same array
				accountList[0] = new CheckingAccount("John Smith", 1500.0);
				accountList[1] = new SavingAccount("William Hurt", 1200.0);
				accountList[2] = new CDAccount("Woody Allison", 1000.0);
				accountList[3] = new LoanAccount("Judi Foster", -1500.0);
		
				// compute interest for all accounts
				for (int count = 0; count < accountList.length; count++) {
					double newBalance = accountList[count].computeInterest();
					System.out.println ("Account <" + a.name() + "> now has $" + newBalance + " balance\n");
				}
    		 */
	}
	static void stdExceptionPrinting(Exception e, double balance) {
		System.out.println("EXCEPTION: Banking system throws a " + e.getClass() +
                       " with message: \n\t" +
                       "MESSAGE: " + e.getMessage());
		System.out.println("\tAccount balance remains $" + balance + "\n");
	}  
}

