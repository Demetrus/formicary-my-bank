package com.abc.bank;

import java.math.BigDecimal;
import java.util.Date;

import com.abc.helpers.DateProvider;

public class Transaction {
	
	public final BigDecimal amount;
	public final Date transactionDate;
	public final Type transactionType;

	public Transaction(BigDecimal amount, Type transactionType) {
		if(amount.compareTo(new BigDecimal("0")) <= 0 && (transactionType == Type.DEPOSIT || transactionType == Type.INTEREST_IN)) {
			throw new IllegalArgumentException("Amount must be greater than zero for this type");
		}
		if(amount.compareTo(new BigDecimal("0")) >= 0 && transactionType == Type.WITHDRAWAL) {
			throw new IllegalArgumentException("Amount must be less than zero for this type");
		}
		this.amount = amount;
		this.transactionDate = DateProvider.getInstance().now();
		this.transactionType = transactionType;
	}

	public enum Type {
		DEPOSIT, WITHDRAWAL, INTEREST_IN
	}
}
