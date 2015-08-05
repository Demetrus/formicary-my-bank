package com.abc.account;

import org.junit.Test;

import com.abc.accounts.Account;
import com.abc.accounts.MaxiSavingAccount;
import static org.junit.Assert.assertEquals;

public class MaxiSavingAccountTest {

	@Test
	public void test_getAccountType() {
		Account maxiSavingAccount = new MaxiSavingAccount();
		assertEquals("Maxi Savings Account", maxiSavingAccount.getAccountType());
	}
	
	@Test
	public void test_addDailyInteres() {
		// A long running test or need to use reflection to set values
		throw new UnsupportedOperationException("Test not implemented!");
	}
}
