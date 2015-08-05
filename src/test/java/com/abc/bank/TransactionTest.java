package com.abc.bank;

import org.junit.Test;

import com.abc.bank.Transaction;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Date;

public class TransactionTest {
	
    @Test
    public void test_transaction_DEPOSIT() {
    	Date a = new Date();
    	this.halt();
        Transaction t = new Transaction( new BigDecimal("5"), Transaction.Type.DEPOSIT);
        this.halt();
        Date b = new Date();
        assertEquals(t.amount, new BigDecimal("5"));
        assertEquals(t.transactionType, Transaction.Type.DEPOSIT);
        assertTrue(t.transactionDate.compareTo(a) > 0 && t.transactionDate.compareTo(b) < 0);
        
        boolean errorWasThrown = false;
        try {
        	t = new Transaction( new BigDecimal("0"), Transaction.Type.DEPOSIT);
        }
        catch (Exception e) {
        	errorWasThrown = true;
        }
        
        assertEquals(true, errorWasThrown);
        
        errorWasThrown = false;
        try {
        	t = new Transaction( new BigDecimal("-4"), Transaction.Type.DEPOSIT);
        }
        catch (Exception e) {
        	errorWasThrown = true;
        }
        
        assertEquals(true, errorWasThrown);
    }
    
    @Test
    public void test_transaction_INTEREST_IN() {
    	Date a = new Date();
    	this.halt();
        Transaction t = new Transaction( new BigDecimal("500000.000001"), Transaction.Type.INTEREST_IN);
        this.halt();
        Date b = new Date();
        assertEquals(t.amount, new BigDecimal("500000.000001"));
        assertEquals(t.transactionType, Transaction.Type.INTEREST_IN);
        assertTrue(t.transactionDate.compareTo(a) > 0 && t.transactionDate.compareTo(b) < 0);
        
        boolean errorWasThrown = false;
        try {
        	t = new Transaction( new BigDecimal("0"), Transaction.Type.INTEREST_IN);
        }
        catch (Exception e) {
        	errorWasThrown = true;
        }
        
        assertEquals(true, errorWasThrown);
        
        errorWasThrown = false;
        try {
        	t = new Transaction( new BigDecimal("-0.00000001"), Transaction.Type.INTEREST_IN);
        }
        catch (Exception e) {
        	errorWasThrown = true;
        }
        
        assertEquals(true, errorWasThrown);
    }
    
    @Test
    public void test_transaction_WITHDRAWAL() {
    	Date a = new Date();
    	this.halt();
        Transaction t = new Transaction( new BigDecimal("-500000.000001"), Transaction.Type.WITHDRAWAL);
        this.halt();
        Date b = new Date();
        assertEquals(t.amount, new BigDecimal("-500000.000001"));
        assertEquals(t.transactionType, Transaction.Type.WITHDRAWAL);
        assertTrue(t.transactionDate.compareTo(a) > 0 && t.transactionDate.compareTo(b) < 0);
        
        boolean errorWasThrown = false;
        try {
        	t = new Transaction( new BigDecimal("0"), Transaction.Type.WITHDRAWAL);
        }
        catch (Exception e) {
        	errorWasThrown = true;
        }
        
        assertEquals(true, errorWasThrown);
        
        errorWasThrown = false;
        try {
        	t = new Transaction( new BigDecimal("4"), Transaction.Type.WITHDRAWAL);
        }
        catch (Exception e) {
        	errorWasThrown = true;
        }
        
        assertEquals(true, errorWasThrown);
    }
    
    private void halt() {
    	try {
			Thread.sleep(1 * 1000);
		}
		catch (Exception e) {

		}
    }
}
