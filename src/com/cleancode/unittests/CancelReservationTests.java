package com.cleancode.unittests;

public class CancelReservationTests {

    /*
    public void GoldCustomer_CancellingMoreThan24Hours_ShouldCancel() {

        var customer = CreateGoldCustomer();
        var reservation = new Reservation(customer, DateTime.Now.AddHours(25));

        reservation.Cancel();
        Assert.IsTrue(reservation.IsCanceled);

        System.out.println("success");
    }
    //GoldCustomer_CancellingMoreThan24Hours_ShouldCancel

    public void GoldCustomer_CancellingLessThan24HoursBefore_ShouldThrowException() {

        var customer = CreateGoldCustomer();
        var reservation = new Reservation(customer, DateTime.Now.AddHours(23));

        try {
            reservation.Cancel();
        }catch(InvalidOperationException e) {
            System.out.println("success");
        }
        //try-catch
    }
    //GoldCustomer_CancellingLessThan24HoursBefore_ShouldThrowException

    public void GoldCustomer_CancellingAfterStartDate_ShouldThrowException() {

        var customer = CreateGoldCustomer();
        var reservation = new Reservation(customer, DateTime.Now.AddDays(-1));

        try {
            reservation.Cancel();
        }catch(InvalidOperationException e) {
            System.out.println("success");
        }
        //try-catch
    }
    //GoldCustomer_CancellingAfterStartDate_ShouldThrowException

    public void RegularCustomer_CancellingMoreThan48HoursBefore_ShouldCancel() {

        var customer = CreateRegularCustomer();
        var reservation = new Reservation(customer, DateTime.Now.AddHours(49));

        reservation.Cancel();
        Assert.IsTrue(reservation.IsCanceled);

        System.out.println("success");
    }
    //RegularCustomer_CancellingMoreThan48HoursBefore_ShouldCancel

    public void RegularCustomer_CancellingLessThan48Hours_ShouldThrowException() {

        var customer = CreateRegularCustomer();
        var reservation = new Reservation(customer, DateTime.Now.AddHours(47));

        try {
            reservation.Cancel();
        }catch(InvalidOperationException e) {
            System.out.println("success");
        }
        //try-catch
    }
    //RegularCustomer_CancellingLessThan48Hours_ShouldThrowException

    public void RegularCustomer_CancellingAfterStartDate_ShouldThrowException() {

        var customer = CreateRegularCustomer();
        var reservation = new Reservation(customer, DateTime.Now.AddHours(-1));

        try {
            reservation.Cancel();
        }catch(InvalidOperationException e) {
            System.out.println("success");
        }
        //try-catch
    }
    //RegularCustomer_CancellingAfterStartDate_ShouldThrowException

    private static Customer CreateGoldCustomer() {

        return new Customer(200);
    }
    //CreateGoldCustomer

    private static Customer CreateRegularCustomer() {

        return new Customer(0);
    }
    //CreateRegularCustomer
    */
}
//CancelReservationTests

//eof
