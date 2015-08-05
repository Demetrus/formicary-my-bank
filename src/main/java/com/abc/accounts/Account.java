package com.abc.accounts;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.abc.bank.Transaction;
import com.abc.helpers.DateProvider;

public abstract class Account {

	protected Object balanceLock = new Object();
	protected List<Transaction> transactions;
	protected Date lastWithdrawal;

	private BigDecimal balance;
	private Date dailyInterestLastAdded;

	public abstract String getAccountType();

	public abstract void addDailyInteres();

	public Account() {
		this.dailyInterestLastAdded = DateProvider.getInstance().justDateTomorrow();
		this.lastWithdrawal = DateProvider.getInstance().now();
		this.balance = BigDecimal.ZERO;
		this.transactions = new ArrayList<Transaction>();
	}

	public BigDecimal getBalanceToTwoDecimals(){
		return balance.setScale(2, RoundingMode.HALF_UP);
	}

	public BigDecimal interestEarned() {
		BigDecimal amount = BigDecimal.ZERO;
		for (Transaction t: this.transactions) {
			if(t.transactionType == Transaction.Type.INTEREST_IN) {
				amount = amount.add(t.amount);
			}
		}
		return amount;
	}

	public BigDecimal sumTransactions() {
		BigDecimal amount = BigDecimal.ZERO;
		for (Transaction t: transactions) {
			amount = amount.add( t.amount);
		}
		return amount.setScale(2, RoundingMode.HALF_UP);
	}

	public List<Transaction> getTransactions() {
		return this.transactions;
	}

	public void deposit(int first, int second) {
		if (first < 0 || second < 0 || (first == 0 && second == 0)) {
			throw new IllegalArgumentException("Amount must be greater than zero");
		} else if (second > 99) {
			throw new IllegalArgumentException("Second parameter cannot be greater than 99");
		} else {
			BigDecimal toDeposit = new BigDecimal(Integer.toString(first) + "." + Integer.toString(second));
			synchronized(this.balanceLock) {
				transactions.add(new Transaction(toDeposit, Transaction.Type.DEPOSIT));
				this.balance = this.balance.add(toDeposit);
			}
		}
	}

	public void withdraw(int first, int second) {
		if (first < 0 || second < 0 || (first == 0 && second == 0)) {
			throw new IllegalArgumentException("amount must be greater than zero");
		} else if (second > 99) {
			throw new IllegalArgumentException("Second parameter cannot be greater than 99");
		} else {
			BigDecimal toWithdraw = new BigDecimal(Integer.toString(first) + "." + Integer.toString(second));
			synchronized(this.balanceLock) {
				if(this.balance.compareTo(toWithdraw) >= 0) {
					transactions.add(new Transaction(toWithdraw.negate(), Transaction.Type.WITHDRAWAL));
					this.balance = this.balance.subtract(toWithdraw);
					this.lastWithdrawal = DateProvider.getInstance().now();
				}
				else {
					throw new UnsupportedOperationException("Cannot withdraw this amount due to insufficient funds!");
				}
			}
		}
	}

	// This is far from ideal
	public void transfer(Account toAccount, int first, int second){
		synchronized(this.balanceLock) {
			try {
				this.withdraw(first, second);
				toAccount.deposit(first, second);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException("Failed to move money with error: " + e.getMessage());
			}

		}
	}

	protected void addInterest(BigDecimal interest) {
		synchronized(this.balanceLock) {
			if(this.dailyInterestLastAdded.compareTo(DateProvider.getInstance().now()) < 0) {
				if(interest.compareTo(BigDecimal.ZERO) > 0) {
					interest = interest.setScale(10, RoundingMode.HALF_DOWN);
					this.balance = this.balance.add(interest);
					this.dailyInterestLastAdded = DateProvider.getInstance().justDateTomorrow();
					this.transactions.add(new Transaction(interest, Transaction.Type.INTEREST_IN));
					System.out.println(interest + " " + this.balance);
				}
			}
		}
	}

	protected BigDecimal getBalance(){
		return balance;
	}
}
