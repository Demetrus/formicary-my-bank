package com.abc.accounts;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

import com.abc.helpers.DateProvider;

public class MaxiSavingAccount extends Account {

	public MaxiSavingAccount() {
		super();
	}

	@Override
	public String getAccountType() {
		return "Maxi Savings Account";
	}

	@Override
	public void addDailyInteres() {
		synchronized(this.balanceLock) {
			BigDecimal amount = super.getBalance();
			BigDecimal interest = BigDecimal.ZERO;
			int numOfDays = DateProvider.getInstance().getDaysInThisYear();

			BigDecimal dailyRate = new BigDecimal("0.001").divide(new BigDecimal(numOfDays), 10, RoundingMode.HALF_DOWN);
			Date cutoffDate = DateProvider.getInstance().addDaysToDate(super.lastWithdrawal, 10);
			if(cutoffDate.compareTo(DateProvider.getInstance().now()) < 0) {
				dailyRate = new BigDecimal("0.05").divide(new BigDecimal(numOfDays), 10, RoundingMode.HALF_DOWN);
			}

			interest = amount.multiply(dailyRate);

			System.out.println(this.getAccountType() + " " + interest + " " + super.getBalance());
			super.addInterest(interest);
		}
	}
}
