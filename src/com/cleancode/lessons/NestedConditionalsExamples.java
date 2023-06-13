package com.cleancode.lessons;

public class NestedConditionalsExamples {

    public static void runExamples() {

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("LESSON 8: NESTED CONDITIONALS\n");

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("Nested Conditionals\n");

        //in short, nested if or switch blocks are ugly.

        //the more nested conditions, the more execution paths.
        //this means there are more scenarios to consider and test
        //and also, more exceptions are possible
        //(which would also force us to introduce nested try-catch blocks!).

        //the "happy path" in nested conditionals
        //is a fortunate combination of values.
        //if any one value is different, some other path will be taken!

        //in these section we'll explore better alternatives.

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("Ternary Expressions\n");

        //when we have an if-else block
        //that only decides which value out of two will be selected,
        //or which expression out of two will be executed
        //we can abbreviate the block via a ternary expression.

        //contrary to if-else blocks,
        //ternary expressions always return a value and thus,
        //they can be assigned, used in other expressions
        //or passed to a method.

        //for example, this block:

        /*
        if(a) c = 0;
        else c = 1;
        */

        //can become this expression:

        /*
        c = a ? 0 : 1;
        */

        //here's a real-world example:

        /*
        if( customer.totalOrders > 50 ) discount = 0.1;
        else discount = 0.01;
        */

        //which becomes:

        /*
        discount = ( customer.totalOrders > 50 ) ? 0.1 : 0.01;
        */

        //in this example, a ternary is used to determine the return value:

        /*
        public float method() {
            if( customer.totalOrders > 50 ) return 0.1;
            else return 0.01;
        }
        //method
        */

        //which becomes:

        /*
        public float method() {
            return ( customer.totalOrders > 50 ) ? 0.1 : 0.01;
        }
        //method
        */

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("Nested Inline Ternary Expressions\n");

        //ternary expressions can also be nested inline,
        //consider this block:

        /*
        if(a) c = b;
        else if(d) c = e;
        else c = f;
        */

        //becomes this nested ternary:

        /*
        c = a ? b : d ? e : f;
        */

        //is that recommended?
        //absolutely NOT!

        //nested ternaries are just as unreadable and unmaintainable
        //as nested conditional blocks.

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("Simplified Strictly Boolean Conditions\n");

        //in Java, the only allowed datatypes for any condition are boolean and Boolean.

        //any other datatype (regardless if coming from a literal, a variable or an expression)
        //will crash at compile-time.

        //this means there is ALWAYS a true or false value
        //in the parentheses of every if statement.

        //when all we do in the if/else clauses
        //is to assign a boolean value to some variable,
        //we're essentially just propagating the condition's boolean value
        //to some variable.

        //this means that, this:

        /*
        if(a) b = true;
        else b = false;
        */

        //can become this:

        /*
        b = a;
        */

        //or, in reverse:

        /*
        if(a) b = false;
        else b = true;
        */

        //becomes:

        /*
        b = !a;
        */

        //here's a real-world example. this:

        /*
        if( customer.totalOrders > 50 ) isGoldCustomer = true;
        else isGoldCustomer = false;
        */

        //becomes this:

        /*
        isGoldCustomer = ( customer.totalOrders > 50 );
        */

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("Combining Nested Conditions\n");

        //often, the AND and OR operators can be used
        //to avoid nesting if blocks.

        //consider this block:

        /*
        if(a) {
            if(b) {
                doSomething();
            }
            //if
        }
        //if
        */

        //which can be turned into:

        /*
        if(a && b) doSomething();
        */

        //or this block:

        /*
        if(a) {
            doSomething();
        }else if(b) {
            doSomething();
        }
        //if-else
        */

        //can become:

        /*
        if(a || b) doSomething();
        */

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("Guarding and Early Exit\n");

        //assuming all our code is inside small methods,
        //we could prevent the code path from going
        //too far, into dangerous behavior, when
        //we find out a variable is in a dangerous state.

        //we do this by testing for an unfavorable condition,
        //which, if true, should stop the happy path and maybe
        //abort the method, return a default value, throw an exception, etc.

        //we call this: "guarding" the happy path
        //by "early-exiting" the method.

        //here's an example:

        /*
        void method() {
            if(a) {
                if(b) {
                    doSomething(a,b);
                }
                //if
            }
            //if
        }
        //method
        */

        //which becomes:

        /*
        void method() {
            if(!a) return; //this is a guard statement
            if(!b) return; //this is another guard statement
            doSomething(a,b); //code path never reaches here unless a,b are in the right state.
        }
        //method
        */

        //or even better:

        /*
        void method() {
            if(!a || !b) return; //this is a guard statement
            doSomething(a,b); //code path never reaches here unless a,b are in the right state.
        }
        //method
        */

        //notice that, in guard statements, the condition gets inverted
        //because we're testing for the UNFAVORABLE conditions, not the happy path

        //let's see a real-world example:

        /*
        void withdrawPreCheck(float amount) {
            if( accountExists(user) ) {
                if( user.balance >= amount ) { //here we use "greater than or equal"
                    withdraw(user, amount);
                }
                //if
            }
            //if
        }
        //withdrawPreCheck
        */

        //which becomes:

        /*
        void withdrawPreCheck(float amount) {
            if( !accountExists(user) || (user.balance < amount) ) return; //here we use "less than"
            withdraw(user, amount);
        }
        //withdrawPreCheck
        */

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("Swapping the Order\n");

        //if we have conditions that are
        //common to many branches of a nested if-else block
        //those should be extracted to an outer nesting level.

        //for example:

        /*
        if(a) {
            if(b) {
                isValid = true;
            }
            //if
        }else if(c) {
            if(b) {
                isValid = true;
            }
            //if
        }
        //if-else
        */

        //we see the inner level in both branches
        //is a condition testing b.
        //so we extract it to an outer level:

        /*
        if(b) {
            if(a) {
                isValid = true;
            }else if(c) {
                isValid = true;
            }
            //if-else
        }
        //if
        */

        //we've kept the same behavior,
        //and our code looks better, but not perfect.

        //let's apply the combining technique:

        /*
        if(b) {
            if(a || c) {
                isValid = true;
            }
            //if-else
        }
        //if
        */

        //that's better, but we can combine further!

        /*
        if( b && (a || c) ) isValid = true;
        */

        //looks super clean!
        //...but we can still apply the simplification technique!

        /*
        isValid = ( b && (a || c) );
        */

        //ok, now we're done.

        //just keep in mind that, if we had a great many variables
        //even a one-liner like that could become unreadable.
        //imagine something like:

        /*
        isValid = ( a && (b || c) && !d || e && ( f && !g || h ) );
        */

        //that was an exaggeration, but
        //sometimes it's not so easy to judge how readable code is.

        //whenever we're unsure if our code is readable,
        //we should ask other programmers for their opinion.

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("Exercise 7\n");

        //have a look at this code,
        //find the nested conditionals
        //and refactor them into something better:

        /*
        public class Customer {

            public int loyaltyPoints;

            public Customer(int _loyaltyPoints) {

                this.loyaltyPoints = _loyaltyPoints;
            }
            //Customer
        }
        //Customer

        public class Reservation {

            public Customer customer;
            public DateTime from;
            public boolean isCanceled;

            public Reservation(Customer _customer, DateTime _from) {
                this.customer   = _customer;
                this.from       = _from;
                this.isCanceled = false;
            }
            //Reservation

            public void cancel() {

                //gold customers can cancel up to 24 hours before
                if( Customer.loyaltyPoints > 100 ) {

                    //if reservation already started, throw exception

                    if( this.from < DateTime.now() ) {

                        throw new InvalidOperationException("too late to cancel");
                    }
                    //if

                    if( (this.from - DateTime.now()).totalHours < 24 ) {

                        throw new InvalidOperationException("too late to cancel");
                    }
                    //if

                    this.isCanceled = true;
                }
                else {

                    //regular customers can cancel up to 48 hours before

                    if( this.from < DateTime.now() ) {

                        throw new InvalidOperationException("too late to cancel");
                    }
                    //if

                    if( (this.from - DateTime.now()).totalHours < 48 ) {

                        throw new InvalidOperationException("too late to cancel");
                    }
                    //if

                    this.isCanceled = true;
                }
                //if-else
            }
            //cancel
        }
        //Reservation
        */

        //the cancel() method declared before
        //can be unit-tested with the CancelReservationTests class.

        //we can use those unit tests to verify
        //that our code still works after every refactor we make.

        //now let's refactor.

        /*
        public class Customer {

            public int loyaltyPoints;

            public Customer(int _loyaltyPoints) {

                this.loyaltyPoints = _loyaltyPoints;
            }
            //Customer

            //with this method we can test if the customer
            //passes the threshold to be a gold customer (over 100 loyalty points)
            public boolean isGoldCustomer() {

                return this.loyaltyPoints > 100;
            }
            //isGoldCustomer
        }
        //Customer

        public class Reservation {

            public Customer customer;
            public DateTime from;
            public boolean isCanceled;

            public Reservation(Customer _customer, DateTime _from) {
                this.customer   = _customer;
                this.from       = _from;
                this.isCanceled = false;
            }
            //Reservation

            //with this method, we check if
            //the time elapsed from the reservation time until now
            //exceeds some limit.
            public boolean nowIsLessThan(int maxHours) {

                return ( this.from - DateTime.now() ).totalHours < maxHours;
            }
            //nowIsLessThan

            //with this method, we check if
            //the cancellable time period for a reservation
            //has already ended.
            private boolean isCancellablePeriodOver() {
                //here we consider both paths: gold customer or not!
                return ( ( this.customer.isGoldCustomer() && this.nowIsLessThan(24) ) ||
                    (     !this.customer.isGoldCustomer() && this.nowIsLessThan(48) )
                );
                //return
            }
            //isCancellablePeriodOver

            public void cancel() {

                //if attempting to cancel out of cancellable period,
                //it doesn't matter if the reservation period has already started or not
                if( this.isCancellablePeriodOver() ) throw new InvalidOperationException("too late to cancel");

                //all invalid code paths except the happy path
                //have been considered and controlled at this point!

                this.isCanceled = true; //this is the happy path!
            }
            //cancel
        }
        //Reservation
        */

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("Summary\n");

        //keep checking by running unit tests constantly
        //whenever planning to do refactoring.

        //it's the only way to become aware of any breaking changes
        //we've introduced.

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("END OF LESSON 8!\n");

        //
    }
    //runExamples
}
//NestedConditionalsExamples

//eof
