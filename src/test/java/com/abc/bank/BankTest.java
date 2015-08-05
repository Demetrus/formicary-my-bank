package com.abc.bank;

import org.junit.Test;

import com.abc.accounts.Account;
import com.abc.accounts.CheckingAccount;
import com.abc.accounts.MaxiSavingAccount;
import com.abc.accounts.SavingAccount;
import com.abc.bank.Bank;
import com.abc.customer.Customer;
import com.abc.helpers.DateProvider;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

public class BankTest {

	// test_addCustomer
	@Test
	public void test_readdSameCustomer() {
		Bank bank = new Bank();
		Customer john = new Customer("LV223678JK", "John");
		bank.addCustomer(john);

		String errorMessage = "";
		try {
			john = new Customer("LV223678JK", "John");
			bank.addCustomer(john);
		}
		catch (Exception e) {
			errorMessage = e.getMessage();
		}

		assertEquals("This customer already exists in the system!", errorMessage);
	}
	
	// test_customerSummary
	@Test
	public void test_customerSummaryOneCustomerVariousAccounts() {
		Bank bank = new Bank();
		Customer john = new Customer("LV223678JK", "John");
		bank.addCustomer(john);

		assertEquals("Customer Summary\n - John (0 accounts)", bank.customerSummary());
		
		john.openAccount(new CheckingAccount());
		
		assertEquals("Customer Summary\n - John (1 account)", bank.customerSummary());
		
		for(int i = 0; i < 10; i++) {
			john.openAccount(new SavingAccount());
		}
		
		assertEquals("Customer Summary\n - John (11 accounts)", bank.customerSummary());
		
		for(int i = 0; i < 20; i++) {
			john.openAccount(new MaxiSavingAccount());
		}
		
		assertEquals("Customer Summary\n - John (31 account)", bank.customerSummary());
		
		for(int i = 0; i < 4; i++) {
			john.openAccount(new MaxiSavingAccount());
		}
		
		assertEquals("Customer Summary\n - John (35 accounts)", bank.customerSummary());
	}

	@Test
	public void test_customerSummaryThreeCustomersVariousAccounts() {
		Bank bank = new Bank();
		Customer customer = new Customer("LV223678JK", "John1");
		customer.openAccount(new CheckingAccount());
		bank.addCustomer(customer);

		customer = new Customer("LV223678JL", "John2");
		customer.openAccount(new CheckingAccount());
		customer.openAccount(new SavingAccount());
		customer.openAccount(new CheckingAccount());
		customer.openAccount(new MaxiSavingAccount());
		customer.openAccount(new MaxiSavingAccount());
		bank.addCustomer(customer);

		customer = new Customer("LV223678JM", "John3");
		customer.openAccount(new CheckingAccount());
		customer.openAccount(new SavingAccount());
		customer.openAccount(new CheckingAccount());
		bank.addCustomer(customer);

		customer = new Customer("LV223678JN", "John4");
		bank.addCustomer(customer);

		assertEquals("Customer Summary\n - John1 (1 account)\n - John2 (5 accounts)\n - John3 (3 accounts)\n - John4 (0 accounts)", bank.customerSummary());
	}

	// test_totalInterestPaid
	@Test
	public void test_checkingAccount() {
		// A long running test or need to use reflection to set values
		//throw new UnsupportedOperationException("Test not implemented!");
		
		Bank bank = new Bank();
		Account checkingAccount = new CheckingAccount();
		Customer bill = new Customer("LV390392TU", "Bill");
		bill.openAccount(checkingAccount);
		bank.addCustomer(bill);

		checkingAccount.deposit(10000, 0);

		try {
			Thread.sleep(49 * 60 * 60 * 1000);
		}
		catch (Exception e) {

		}

		if(DateProvider.getInstance().getDaysInThisYear() == 365) {
			assertEquals(new BigDecimal("0.05479"), bank.totalInterestPaid());
		}
		else {
		    assertEquals(new BigDecimal("0.05464"), bank.totalInterestPaid());
		}
	}

	@Test
	public void savingsAccount() {
		// A long running test or need to use reflection to set values
		//throw new UnsupportedOperationException("Test not implemented!");
		
		Bank bank = new Bank();
		Account savingAccount = new SavingAccount();
		Customer bill = new Customer("LV390392TU", "Bill");
		bill.openAccount(savingAccount);
		bank.addCustomer(bill);

		savingAccount.deposit(1500, 0);
		try {
			Thread.sleep(25 * 60 * 60 * 1000);
		}
		catch (Exception e) {

		}

		if(DateProvider.getInstance().getDaysInThisYear() == 365) {
			assertEquals(new BigDecimal("0.00548"), bank.totalInterestPaid());
		}
		else {
			assertEquals(new BigDecimal("0.00546"), bank.totalInterestPaid());
		}
	}

	@Test
	public void maxiSavingsAccount() {
		// A long running test or need to use reflection to set values
		//throw new UnsupportedOperationException("Test not implemented!");
		
		Bank bank = new Bank();
		Account maxiSavingAccount = new MaxiSavingAccount();
		Customer bill = new Customer("LV390392TU", "Bill");
		bill.openAccount(maxiSavingAccount);
		bank.addCustomer(bill);

		maxiSavingAccount.deposit(3000, 0);
		try {
			Thread.sleep(25 * 60 * 60 * 1000);
		}
		catch (Exception e) {

		}

		if(DateProvider.getInstance().getDaysInThisYear() == 365) {
			assertEquals(new BigDecimal("0.00822"), bank.totalInterestPaid());
		}
		else {
			assertEquals(new BigDecimal("0.00817"), bank.totalInterestPaid());
		}
	}
}
