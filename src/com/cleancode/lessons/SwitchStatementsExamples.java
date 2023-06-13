package com.cleancode.lessons;

public class SwitchStatementsExamples {

    public static void runExamples() {

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("LESSON 9: SWITCH STATEMENTS\n");

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("Switch Statements\n");

        //switch statements are flow control structures.
        //so, they care only about the behavior, not the data
        //which is very procedural / structured / imperative.

        //overusing them in Java is bad, because in Java
        //we should be using a data-driven flow, since Java uses OOP.

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("Exercise 8a\n");

        //have a look at this code.
        //it mocks a phone service customer and his payments:

        /*
        public enum CustomerType {
            PAY_AS_YOU_GO,
            UNLIMITED
        }
        //CustomerType

        public class Customer {

            public CustomerType type;

            public Customer(CustomerType _type) {

                this.type = _type;
            }
            //Customer
        }
        //Customer

        public class MonthlyUsage {

            public Customer customer;
            public int callMinutes;
            public int smsCount;

            public MontlyUsage(Customer _customer, int _callMinutes, int _smsCount) {

                this.customer    = _customer;
                this.callMinutes = _callMinutes;
                this.smsCount    = _smsCount;
            }
            //MontlyUsage
        }
        //MonthlyUsage

        public class MonthlyStatement {

            public double callCost;
            public double smsCost;
            public double totalCost;

            public void generate(MonthlyUsage usage) {

                switch(usage.customer.type) {

                    case CustomerType.PAY_AS_YOU_GO -> {
                        callCost  = usage.callMinutes * 0.12; //cost is 12 cents per minute or per sms
                        smsCost   = usage.smsCount * 0.12;
                        totalCost = callCost + smsCost;
                    }
                    //PAY_AS_YOU_GO

                    case CustomerType.UNLIMITED -> totalCost = 54.90;

                    default -> throw new NotSupportedException( "Customer type not supported." );
                }
                //switch
            }
            //generate
        }
        //MonthlyStatement
        */

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("Problems found in Exercise 8a\n");

        //if we added a new kind of customer,
        //we'd have to recompile the MonthlyStatement class,
        //and every class which used it.

        //plus, the switch statement, the generate() method
        //and the MonthlyStatement class would grow a lot.

        //and the biggest problem is that
        //(in a general form)
        //we have a switch statement that defines cases
        //based on different options which represent
        //the possible types of something.

        //in this case, the type of customer.

        //it's very likely that, in a real application,
        //we'd have to repeat such a switch block
        //for every different procedure that depends on the customer type.

        //like, here we use the switch block to calculate the cost,
        //in some other class we might use it to calculate the coverage,
        //and somewhere else, we could use it to determine the benefits.
        //imagine maintaining N copies of (basically) the same switch block!

        //the better way to do this, is to have several
        //different subclasses of Customer, each encapsulating its own logic
        //for every one of its procedures.

        //then, just have a polymorphic Customer variable
        //call the necessary method directly for every case.

        //that's what we'll do,
        //but before we start refactoring,
        //we need to make sure we have unit tests
        //(which we have, on the MonthlyStatementTests class).

        //be sure to re-run those tests
        //after every change (no matter how tiny)
        //that we make while refactoring.

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("Exercise 8b\n");

        //let's refactor!

        //first, we notice how the generate() method
        //uses the usage arg (a MonthlyUsage object) a lot.
        //which is a dead giveaway that it would be better
        //as an instance method of the MonthlyUsage class.

        //but after doing that refactor,
        //we realize the switch statement inside generate() depends on the type of customer.

        //this allows us to do several things:
        //let Customer hold an instance of MonthlyUsage as a field.
        //remove the customer field of MonthlyUsage. It's irrelevant now.
        //move generate() yet again, this time to the Customer class.
        //remove generate()'s arg because the MonthlyUsage is now accessible as a field of Customer.

        //also, the name generate() made sense inside the MonthlyStatement class,
        //but inside Customer it would be better to rename it to generateStatement().

        //with this, we've streamlined MonthlyStatement and MonthlyUsage a lot.
        //now, let's deal with the switch statement.

        //we realize the switch statement performs different logic depending on the Customer type.
        //it is at this point where we make the Customer class abstract
        //and create its PayAsYouGoCustomer and UnlimitedCustomer subclasses.

        //at this point we don't need the Customer.type field,
        //nor the CustomerType enum anymore, so we remove them.

        //we declare the generateStatement() method as abstract in the Customer class
        //and implement it in each subclass, with only the logic each one needs,
        //so at this point, we'll remove the switch block in both.

        //so we've replaced a switch block, which branched on the customer type
        //with two subclasses of Customer!
        //what we've just done is called "polymorphic dispatch".

        //one final thing: since now we're actually generating a monthly statement,
        //we need to add a constructor to the MonthlyStatement class.

        //here's the refactored code:

        /*
        public class MonthlyUsage {

            public int callMinutes;
            public int smsCount;

            public MonthlyUsage(int _callMinutes, int _smsCount) {
                this.callMinutes = _callMinutes;
                this.smsCount    = _smsCount;
            }
            //MonthlyUsage
        }
        //MonthlyUsage

        public class MonthlyStatement {

            public double callCost;
            public double smsCost;
            public double totalCost;

            public MonthlyStatement(double _callCost, double _smsCost, double _totalCost) {
                this.callCost  = _callCost;
                this.smsCost   = _smsCost;
                this.totalCost = _totalCost;
            }
            //MonthlyStatement
        }
        //MonthlyStatement

        public abstract class Customer {

            //pay-as-you-go customers pay 12 cents per minute and per message
            public static final double CALL_SMS_RATE = 0.12;

            //unlimited customers only pay a flat 54.90 monthly fee
            public static final double FLAT_RATE = 54.9;

            public MonthlyUsage monthlyUsage;

            public Customer() {

                this.monthlyUsage = new MonthlyUsage(0, 0);
            }
            //Customer

            public void setMonthlyUsage(int _callMinutes, int _smsCount) {

                this.monthlyUsage = new MonthlyUsage(_callMinutes, _smsCount);
            }
            //setMonthlyUsage

            public abstract MonthlyStatement generateStatement();
        }
        //Customer

        public class PayAsYouGoCustomer extends Customer {

            public PayAsYouGoCustomer() {super();}

            @Override
            public MonthlyStatement generateStatement() {

                double callCost  = CALL_SMS_RATE * this.monthlyUsage.callMinutes;
                double smsCost   = CALL_SMS_RATE * this.monthlyUsage.smsCount;
                double totalCost = callCost + smsCost;

                return new MonthlyStatement(callCost, smsCost, totalCost);
            }
            //generateStatement
        }
        //PayAsYouGoCustomer

        public class UnlimitedCustomer extends Customer {

            public UnlimitedCustomer() {super();}

            @Override
            public MonthlyStatement generateStatement() {

                double callCost  = 0;
                double smsCost   = 0;
                double totalCost = FLAT_RATE;

                return new MonthlyStatement(callCost, smsCost, totalCost);
            }
            //generateStatement
        }
        //UnlimitedCustomer
        */

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("Summary\n");

        //what did we accomplish?
        //there are several advantages to what we just did:

        //first of all, the generateStatement() method is subclass-agnostic,
        //we can call it from any abstract Customer object.

        //also, a Customer now holds its own MonthlyUsage object as a local field,
        //so it can access its call and sms usage values if needed.

        //plus, we got rid of the CustomerType enum
        //and most importantly, we don't have the switch block anymore!

        //so...
        //whenever we see a switch statement branching on something's type
        //replace it with polymorphic dispatch!

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("END OF LESSON 9!\n");

        //
    }
    //runExamples
}
//SwitchStatementsExamples

//eof
