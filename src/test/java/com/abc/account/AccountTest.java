package com.abc.account;

import org.junit.Ignore;
import org.junit.Test;

import com.abc.accounts.Account;
import com.abc.accounts.CheckingAccount;
import com.abc.accounts.SavingAccount;
import com.abc.bank.Transaction;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.List;

public class AccountTest {

	@Test
	public void test_getBalanceToTwoDecimals() {
		Account checkingAccount = new CheckingAccount();
		checkingAccount.deposit(2000, 67);
		assertEquals(new BigDecimal("2000.67"), checkingAccount.getBalanceToTwoDecimals());
	}
	
	@Test
	public void test_interestEarned() {
		// A long running test or need to use reflection to set values
		throw new UnsupportedOperationException("Test not implemented!");
	}
	
	@Test
	public void test_sumTransactions() {
		Account checkingAccount = new CheckingAccount();
		checkingAccount.deposit(2000, 67);
		checkingAccount.withdraw(2000, 65);
		checkingAccount.deposit(45, 99);
		assertEquals(new BigDecimal("46.01"), checkingAccount.sumTransactions());
	}
	
	@Test
	public void test_getTransactions() {
		Account checkingAccount = new CheckingAccount();
		checkingAccount.deposit(2000, 67);
		checkingAccount.withdraw(2000, 65);
		checkingAccount.deposit(45, 99);
		
		List<Transaction> transactions = checkingAccount.getTransactions();
		assertEquals(3, transactions.size());
	}
	
	@Test
	public void test_deposit() {
		Account checkingAccount = new CheckingAccount();
		
        checkingAccount.deposit(2000, 99);
        assertEquals(new BigDecimal("2000.99"), checkingAccount.getBalanceToTwoDecimals());
        
        checkingAccount.deposit(2000, 00);
        assertEquals(new BigDecimal("4000.99"), checkingAccount.getBalanceToTwoDecimals());
        
        checkingAccount.deposit(0, 99);
        assertEquals(new BigDecimal("4001.98"), checkingAccount.getBalanceToTwoDecimals());
        
        boolean errorWasThrown = false;
        try {
        	checkingAccount.deposit(0, 0);
        }
        catch (Exception e) {
        	errorWasThrown = true;
        }
        assertEquals(true, errorWasThrown);
        assertEquals(new BigDecimal("4001.98"), checkingAccount.getBalanceToTwoDecimals());
        
        errorWasThrown = false;
        try {
        	checkingAccount.deposit(0, -1);
        }
        catch (Exception e) {
        	errorWasThrown = true;
        }
        assertEquals(true, errorWasThrown);
        assertEquals(new BigDecimal("4001.98"), checkingAccount.getBalanceToTwoDecimals());
        
        errorWasThrown = false;
        try {
        	checkingAccount.deposit(-1, 0);
        }
        catch (Exception e) {
        	errorWasThrown = true;
        }
        assertEquals(true, errorWasThrown);
        assertEquals(new BigDecimal("4001.98"), checkingAccount.getBalanceToTwoDecimals());
        
        errorWasThrown = false;
        try {
        	checkingAccount.deposit(-1, -1);
        }
        catch (Exception e) {
        	errorWasThrown = true;
        }
        assertEquals(true, errorWasThrown);
        assertEquals(new BigDecimal("4001.98"), checkingAccount.getBalanceToTwoDecimals());
        
        errorWasThrown = false;
        try {
        	checkingAccount.deposit(5, 100);
        }
        catch (Exception e) {
        	errorWasThrown = true;
        }
        assertEquals(true, errorWasThrown);
        assertEquals(new BigDecimal("4001.98"), checkingAccount.getBalanceToTwoDecimals());
	}
	
	@Test
	public void test_withdraw() {
        Account checkingAccount = new CheckingAccount();
		
        boolean errorWasThrown = false;
        try {
        	checkingAccount.withdraw(100, 0);
        }
        catch (Exception e) {
        	errorWasThrown = true;
        }
        assertEquals(true, errorWasThrown);
        assertEquals(new BigDecimal("0.00"), checkingAccount.getBalanceToTwoDecimals());
        
        checkingAccount.deposit(1000, 98);
        checkingAccount.withdraw(100, 0);
        assertEquals(new BigDecimal("900.98"), checkingAccount.getBalanceToTwoDecimals());
        
        checkingAccount.withdraw(300, 00);
        assertEquals(new BigDecimal("600.98"), checkingAccount.getBalanceToTwoDecimals());
        
        checkingAccount.withdraw(0, 99);
        assertEquals(new BigDecimal("599.99"), checkingAccount.getBalanceToTwoDecimals());
        
        errorWasThrown = false;
        try {
        	checkingAccount.withdraw(0, 0);
        }
        catch (Exception e) {
        	errorWasThrown = true;
        }
        assertEquals(true, errorWasThrown);
        assertEquals(new BigDecimal("599.99"), checkingAccount.getBalanceToTwoDecimals());
        
        errorWasThrown = false;
        try {
        	checkingAccount.withdraw(0, -1);
        }
        catch (Exception e) {
        	errorWasThrown = true;
        }
        assertEquals(true, errorWasThrown);
        assertEquals(new BigDecimal("599.99"), checkingAccount.getBalanceToTwoDecimals());
        
        errorWasThrown = false;
        try {
        	checkingAccount.withdraw(-1, 0);
        }
        catch (Exception e) {
        	errorWasThrown = true;
        }
        assertEquals(true, errorWasThrown);
        assertEquals(new BigDecimal("599.99"), checkingAccount.getBalanceToTwoDecimals());
        
        errorWasThrown = false;
        try {
        	checkingAccount.withdraw(-1, -1);
        }
        catch (Exception e) {
        	errorWasThrown = true;
        }
        assertEquals(true, errorWasThrown);
        assertEquals(new BigDecimal("599.99"), checkingAccount.getBalanceToTwoDecimals());
        
        errorWasThrown = false;
        try {
        	checkingAccount.withdraw(5, 100);
        }
        catch (Exception e) {
        	errorWasThrown = true;
        }
        assertEquals(true, errorWasThrown);
        assertEquals(new BigDecimal("599.99"), checkingAccount.getBalanceToTwoDecimals());
	}
	
	@Test
	public void test_transfer() {
		Account checkingAccount = new CheckingAccount();
        Account savingsAccount = new SavingAccount();

        checkingAccount.deposit(2000, 0);
        savingsAccount.deposit(4000, 0);
        
        savingsAccount.transfer(checkingAccount, 1500, 0);
        
        assertEquals(new BigDecimal("3500.00"), checkingAccount.getBalanceToTwoDecimals());
        assertEquals(new BigDecimal("2500.00"), savingsAccount.getBalanceToTwoDecimals());
	}
	
    @Test
    public void testMoveSuccess(){

        Account checkingAccount = new CheckingAccount();
        Account savingsAccount = new SavingAccount();

        checkingAccount.deposit(2000, 0);
        savingsAccount.deposit(4000, 0);

        savingsAccount.transfer(checkingAccount, 1500, 0);
        assertEquals(new BigDecimal("3500.00"), checkingAccount.getBalanceToTwoDecimals());
        assertEquals(new BigDecimal("2500.00"), savingsAccount.getBalanceToTwoDecimals());
        
        boolean errorWasThrown = false;
        try {
            savingsAccount.transfer(checkingAccount, 5000, 0);
        }
        catch (Exception e) {
        	errorWasThrown = true;
        }
        assertEquals(true, errorWasThrown);
        assertEquals(new BigDecimal("3500.00"), checkingAccount.getBalanceToTwoDecimals());
        assertEquals(new BigDecimal("2500.00"), savingsAccount.getBalanceToTwoDecimals());
        
        errorWasThrown = false;
        try {
            savingsAccount.transfer(checkingAccount, -500, 0);
        }
        catch (Exception e) {
        	errorWasThrown = true;
        }
        assertEquals(true, errorWasThrown);
        assertEquals(new BigDecimal("3500.00"), checkingAccount.getBalanceToTwoDecimals());
        assertEquals(new BigDecimal("2500.00"), savingsAccount.getBalanceToTwoDecimals());
    }
}
