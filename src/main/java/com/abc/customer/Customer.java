package com.abc.customer;

import java.util.ArrayList;
import java.util.List;

import com.abc.accounts.Account;

import com.abc.bank.Transaction;
import com.abc.helpers.TextHelper;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Customer {
	private String passportId;
	private String name;
	private List<Account> accounts;

	public Customer(String passportId, String name) {
		this.passportId = passportId;
		this.name = name;
		this.accounts = new ArrayList<Account>();
	}

	public String getName() {
		return this.name;
	}

	public void openAccount(Account account) {
		this.accounts.add(account);
	}

	public int getNumberOfAccounts() {
		return this.accounts.size();
	}

	public BigDecimal totalInterestEarned() {
		BigDecimal total = BigDecimal.ZERO;
		for (Account a : this.accounts) {
			total = total.add(a.interestEarned());
		}
		return total.setScale(5, RoundingMode.HALF_DOWN);
	}

	public String getStatement() {
		String statement = "";
		statement = "Statement for " + name + System.getProperty("line.separator");
				BigDecimal total = BigDecimal.ZERO;
				for (Account a : this.accounts) {
					statement += System.getProperty("line.separator") + this.statementForAccount(a) + System.getProperty("line.separator");
					total = total.add(a.sumTransactions());
				}
				statement += System.getProperty("line.separator") + "Total In All Accounts " + TextHelper.toAbsoluteDollars(total);
				return statement;
	}

	public boolean equalCusomerDetails(Customer toCompare) {
		return this.name.equals(toCompare.name) && this.passportId.equals(toCompare.passportId);
	}

	public void refreshAccounts() {
		for (Account a : this.accounts) {
			a.addDailyInteres();
		}
	}

	private String statementForAccount(Account a) {
		String s = "";

		s += a.getAccountType() + System.getProperty("line.separator");

		//Now total up all the transactions
		BigDecimal total = BigDecimal.ZERO;
		for (Transaction t : a.getTransactions()) {
			s += "  " + (t.amount.compareTo(BigDecimal.ZERO) < 0 ? "withdrawal" : "deposit") + " " + TextHelper.toAbsoluteDollars(t.amount) + System.getProperty("line.separator");
			total = total.add(t.amount);
		}
		s += "Total " + TextHelper.toAbsoluteDollars(total);
		return s;
	}
}
