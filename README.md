Concerns
========

There are several important features/ architectural changes need to be implemented for this project, however they miss from the specification and are not defined. The following list of points are of the major concern for the project:

1) Support for concurrency.
All 'Account' classes need to be thread-safe. Basic locking mechanism is in place, however the 'transfer' method may lock on itself.
All collections in the program that can be accessed simultaneously from different threads must implement as thread-safe collections similar to the one used for the 'customers' field in the 'Bank' class.

2) Accounts should not be a encapsulated in the 'Customer' class but in the 'Bank' class itself. The 'Bank' should implement and expose methods to perform actions on accounts to clients. However that would require some form of authentication.

3) Security should be implemented on all levels for every operation. At the moment there are only two roles to define - customer and bank manager. Both should be able to authenticate to the bank and bank should have authorisation policies for both.

4) Need to log every action performed.

5) Need to have a strategy for testing interest rates - probably should be long running tests. Another way to test is to introduce environment variables, however it is not very safe as some of the debugging code may make its way to the production. Also reflection can be used to set protected/private field, however that will break the normal flow of the problem and affect the correctness of tests.


Programming Test
========

This is a dummy application to be used as part of a software development interview.

instructions
--------

* Treat this code as if you owned this application, do whatever you feel is necessary to make this your own.
* There are several deliberate design, code quality and test issues that should be identified and resolved.
* Below is a list of the current features supported by the application; as well as some additional features that have been requested by the business owner.
* In order to work on this take a fork into your own GitHub area; make whatever changes you feel are necessary and when you are satisfied submit back via a pull request. See details on GitHub's [Fork & Pull](https://help.github.com/articles/using-pull-requests) model
* The project uses maven to resolve dependencies however if you want to avoid maven configuration the only external JAR that's required is junit-4.11.
* Refactor and add features (from the below list) as you see fit; there is no need to add all the features in order to "complete" the exercise. Keep in mind that code quality is the critical measure and there should be an obvious focus on testing.
* You'll notice there is no database or UI; these are not needed - the exercise deliberately avoids these requirements.
* REMEMBER: this is YOUR code, make any changes you feel are necessary.
* You're welcome to spend as much time as you like; however it's anticipated that this should take about 2 hours.
* The code will be a representation of your work, so it's important that all the code--new and pre-existing--is how you want your work to be seen.  Please make sure that you are happy with ALL the code.

abc-bank
--------

A dummy application for a bank; should provide various functions of a retail bank.

### Current Features

* A customer can open an account
* A customer can deposit / withdraw funds from an account
* A customer can request a statement that shows transactions and totals for each of their accounts
* Different accounts have interest calculated in different ways
  * **Checking accounts** have a flat rate of 0.1%
  * **Savings accounts** have a rate of 0.1% for the first $1,000 then 0.2%
  * **Maxi-Savings accounts** have a rate of 2% for the first $1,000 then 5% for the next $1,000 then 10%
* A bank manager can get a report showing the list of customers and how many accounts they have
* A bank manager can get a report showing the total interest paid by the bank on all accounts

### Additional Features

* A customer can transfer between their accounts
* Change **Maxi-Savings accounts** to have an interest rate of 5% assuming no withdrawals in the past 10 days otherwise 0.1%
* Interest rates should accrue and compound daily (incl. weekends), rates above are per-annum
