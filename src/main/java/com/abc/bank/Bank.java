package com.abc.bank;

import java.math.BigDecimal;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.abc.customer.Customer;
import com.abc.helpers.TextHelper;

public class Bank {
	private CopyOnWriteArrayList<Customer> customers;
	// TODO: Need to re-think use of this scheduler as currently it does
	// get destroyed when references to a bank object are removed
	private final ScheduledExecutorService scheduler =
			Executors.newScheduledThreadPool(1);

	public Bank() {
		this.customers = new CopyOnWriteArrayList<Customer>();
		this.startAddInterestScheduler();
	}

	public void addCustomer(Customer customer) {
		if(this.customerAlreadyExists(customer)){
			throw new UnsupportedOperationException("This customer already exists in the system!");
		}
		this.customers.add(customer);
	}

	public BigDecimal totalInterestPaid() {
		BigDecimal total = BigDecimal.ZERO;
		for(Customer c: this.customers) {
			total = total.add(c.totalInterestEarned());
		}
		return total;
	}

	public String customerSummary() {
		String summary = "Customer Summary";
		for (Customer c : this.customers){
			summary += "\n - " + c.getName() + " (" + TextHelper.formatToSingularOrPlural(c.getNumberOfAccounts(), "account") + ")";
		}
		return summary;
	}

	@Override
	protected void finalize() throws Throwable {
		this.scheduler.shutdownNow();
	}

	private boolean customerAlreadyExists(Customer customer){
		for(Customer c: this.customers){
			if(c.equalCusomerDetails(customer)){
				return true;
			}	
		}
		return false;
	}

	private void startAddInterestScheduler() {
		class AddInterestRunnable implements Runnable {
			public void run() {
				workOnAddInterest();
			}
		}

		AddInterestRunnable addInterestRunnable = new AddInterestRunnable();
		scheduler.scheduleAtFixedRate(addInterestRunnable, 1, 1, TimeUnit.DAYS);
	}

	private void workOnAddInterest() {
		for (Customer c : this.customers) {
			c.refreshAccounts();
		}
	}
}
