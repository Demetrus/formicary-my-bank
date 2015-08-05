package com.abc.customer;

import org.junit.Test;

import com.abc.accounts.Account;
import com.abc.accounts.CheckingAccount;
import com.abc.accounts.MaxiSavingAccount;
import com.abc.accounts.SavingAccount;
import com.abc.customer.Customer;

import static org.junit.Assert.assertEquals;

public class CustomerTest {

	@Test
    public void test_getName() {
        Customer oscar = new Customer("LV983601KL", "Oscar");
        assertEquals("Oscar", oscar.getName());
    }
	
	@Test
    public void test_openAccount() {
        Customer oscar = new Customer("LV983601KL", "Oscar");
        oscar.openAccount(new SavingAccount());
        oscar.openAccount(new CheckingAccount());
        oscar.openAccount(new MaxiSavingAccount());
    }
	
	@Test
    public void test_getNumberOfAccounts() {
        Customer oscar = new Customer("LV983601KL", "Oscar");
        assertEquals(0, oscar.getNumberOfAccounts());
        
        oscar.openAccount(new SavingAccount());
        assertEquals(1, oscar.getNumberOfAccounts());
        
        oscar.openAccount(new SavingAccount());
        oscar.openAccount(new CheckingAccount());
        assertEquals(3, oscar.getNumberOfAccounts());
    }
    
    @Test
    public void test_getStatement(){
        Account checkingAccount = new CheckingAccount();
        Account savingsAccount = new SavingAccount();

        Customer henry = new Customer("LV395813RD", "Henry");
        henry.openAccount(checkingAccount);
        henry.openAccount(savingsAccount);

        checkingAccount.deposit(100, 0);
        savingsAccount.deposit(4000, 0);
        savingsAccount.withdraw(200, 0);

        System.out.println(henry.getStatement());
        assertEquals("Statement for Henry" + System.getProperty("line.separator") +
        		System.getProperty("line.separator") +
                "Checking Account" + System.getProperty("line.separator") +
                "  deposit $100.00" + System.getProperty("line.separator") +
                "Total $100.00" + System.getProperty("line.separator") +
                System.getProperty("line.separator") +
                "Savings Account" + System.getProperty("line.separator") +
                "  deposit $4,000.00" + System.getProperty("line.separator") +
                "  withdrawal $200.00" + System.getProperty("line.separator") +
                "Total $3,800.00" + System.getProperty("line.separator") +
                System.getProperty("line.separator") +
                "Total In All Accounts $3,900.00", henry.getStatement());
    }
    
    @Test
    public void test_totalInterestEarned(){
    	// A long running test or need to use reflection to set values
    	throw new UnsupportedOperationException("Test not implemented!");
    }

    @Test
    public void test_equalCusomerDetails() {
    	Customer oscar1 = new Customer("LV983601KL", "Oscar");
    	Customer oscar2 = new Customer("LV983601KL", "Oscar");
    	assertEquals(true, oscar1.equalCusomerDetails(oscar2));
    	
    	oscar2 = new Customer("LV983601KL", "Oscar2");
    	assertEquals(false, oscar1.equalCusomerDetails(oscar2));
    	
    	oscar2 = new Customer("LV983601KZ", "Oscar");
    	assertEquals(false, oscar1.equalCusomerDetails(oscar2));
    }
    
    @Test
    public void test_refreshAccounts() {
    	// A long running test or need to use reflection to set values
    	throw new UnsupportedOperationException("Test not implemented!");
    }
}
