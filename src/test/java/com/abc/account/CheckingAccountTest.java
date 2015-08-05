package com.abc.account;

import org.junit.Test;

import com.abc.accounts.Account;
import com.abc.accounts.CheckingAccount;
import static org.junit.Assert.assertEquals;


public class CheckingAccountTest {

	@Test
	public void test_getAccountType() {
		Account checkingAccount = new CheckingAccount();
		assertEquals("Checking Account", checkingAccount.getAccountType());
	}
	
	@Test
	public void test_addDailyInteres() {
		// A long running test or need to use reflection to set values
		throw new UnsupportedOperationException("Test not implemented!");
	}
}
