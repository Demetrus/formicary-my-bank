package com.abc.account;

import org.junit.Test;
import com.abc.accounts.Account;
import com.abc.accounts.SavingAccount;
import static org.junit.Assert.assertEquals;

public class SavingAccountTest {

	@Test
	public void test_getAccountType() {
		Account savingAccount = new SavingAccount();
		assertEquals("Savings Account", savingAccount.getAccountType());
	}
	
	@Test
	public void test_addDailyInteres() {
		// A long running test or need to use reflection to set values
		throw new UnsupportedOperationException("Test not implemented!");
	}
}
