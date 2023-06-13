package com.cleancode.unittests;

public class MonthlyStatementTests {

    //these are the tests for before the refactor:

    /*
    public void PayAsYouGoCustomer_IsChargedBasedOnTheSumOfCostOfCallAndSms() {

        var customer  = new Customer(CustomerType.PAY_AS_YOU_GO);
        var usage     = new MonthlyUsage(customer, 100, 100);
        var statement = new MonthlyStatement();

        statement.Generate(usage);

        Assert.AreEqual(12.0, statement.callCost);
        Assert.AreEqual(12.0, statement.smsCost);
        Assert.AreEqual(24.0, statement.totalCost);
    }
    //PayAsYouGoCustomer_IsChargedBasedOnTheSumOfCostOfCallAndSms

    public void UnlimitedCustomer_IsChargedAFlatRatePerMonth() {

        var customer  = new Customer(CustomerType.UNLIMITED);
        var usage     = new MonthlyUsage(customer, 100, 100);
        var statement = new MonthlyStatement();

        statement.Generate(usage);

        Assert.AreEqual(0, statement.callCost);
        Assert.AreEqual(0, statement.smsCost);
        Assert.AreEqual(54.90, statement.totalCost);
    }
    //UnlimitedCustomer_IsChargedAFlatRatePerMonth
    */

    //these are the tests for during and after the refactor:

    /*
    public void PayAsYouGoCustomer_IsChargedBasedOnTheSumOfCostOfCallAndSms2() {

        var customer  = new PayAsYouGoCustomer();
        customer.setMonthlyUsage(100, 100);
        var statement = customer.generateStatement();

        Assert.AreEqual(12.0, statement.callCost);
        Assert.AreEqual(12.0, statement.smsCost);
        Assert.AreEqual(24.0, statement.totalCost);
    }
    //PayAsYouGoCustomer_IsChargedBasedOnTheSumOfCostOfCallAndSms2

    public void UnlimitedCustomer_IsChargedAFlatRatePerMonth2() {

        var customer  = new UnlimitedCustomer();
        customer.setMonthlyUsage(100, 100);
        var statement = customer.generateStatement();

        Assert.AreEqual(0, statement.callCost);
        Assert.AreEqual(0, statement.smsCost);
        Assert.AreEqual(54.90, statement.totalCost);
    }
    //UnlimitedCustomer_IsChargedAFlatRatePerMonth2
    */
}
//MonthlyStatementTests

//eof
