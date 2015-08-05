package com.abc.accounts;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.abc.helpers.DateProvider;

public class SavingAccount extends Account {

	public SavingAccount() {
		super();
	}

	@Override
	public String getAccountType() {
		return "Savings Account";
	}

	@Override
	public void addDailyInteres() {
		synchronized(this.balanceLock) {
			BigDecimal amount = super.getBalance();
			BigDecimal interest = BigDecimal.ZERO;
			int numOfDays = DateProvider.getInstance().getDaysInThisYear();

			if (amount.compareTo(new BigDecimal(1000)) <= 0) {
				BigDecimal dailyRate = new BigDecimal("0.001").divide(new BigDecimal(numOfDays), 10, RoundingMode.HALF_DOWN);
				interest = amount.multiply(dailyRate);
			}
			else {
				interest = new BigDecimal("1.00").divide(new BigDecimal(numOfDays), 10, RoundingMode.HALF_DOWN);
				amount = amount.subtract(new BigDecimal("1000.00"));

				BigDecimal dailyRate = new BigDecimal("0.002").divide(new BigDecimal(numOfDays), 10, RoundingMode.HALF_DOWN);
				interest = interest.add(amount.multiply(dailyRate));
			}
			System.out.println(this.getAccountType() + " " + interest + " " + super.getBalance());
			super.addInterest(interest);
		}
	}
}
