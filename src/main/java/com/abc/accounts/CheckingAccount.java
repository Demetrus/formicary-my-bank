package com.abc.accounts;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.abc.helpers.DateProvider;

public class CheckingAccount extends Account {

	public CheckingAccount() {
		super();
	}

	@Override
	public String getAccountType() {
		return "Checking Account";
	}

	@Override
	public void addDailyInteres() {
		synchronized(this.balanceLock) {
			BigDecimal amount = super.getBalance();
			BigDecimal interest = BigDecimal.ZERO;
			int numOfDays = DateProvider.getInstance().getDaysInThisYear();
			BigDecimal dailyRate = new BigDecimal("0.001").divide(new BigDecimal(numOfDays), 10, RoundingMode.HALF_DOWN);

			interest = amount.multiply(dailyRate);

			System.out.println(this.getAccountType() + " " + super.getBalance().multiply(new BigDecimal("0.001")) + " " + super.getBalance());
			super.addInterest(interest);
		}
	}
}
