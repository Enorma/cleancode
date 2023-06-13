package com.cleancode.lessons;

import java.util.ArrayList;
import java.util.List;

public class CommentsExamples {

    public static void runExamples() {

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("LESSON 11: COMMENTS\n");

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("Comments Stating the Obvious\n");

        //if a comment states something that can be obviously inferred,
        //then, instead of being helpful, it becomes noise.

        //for example:

        /*
        public List<Customer> getCustomers() {...} //this returns a list of customers

        //or...

        var connection = new SQLConnection(); //this creates a new connection to the DB

        //or...

        if( overdueDays > 5 ) notifyCustomer(); //if customer is overdue by less than 5 days, just notify him
        else issueFine(); //otherwise, issue a fine.
        */

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("Comments Not Updated\n");

        //it is also possible that our code might change
        //but the programmer forgets to also change its comments accordingly.

        //that would be very dangerous, because
        //comments would not be truthful and dependable documentation.

        //for example:

        /*
        //car will auto-brake if it exceeds the 80 km/h speed limit
        final int SPEED_LIMIT = 120;
        if( car.getCurrentSpeed() > SPEED_LIMIT ) car.autoBrake();
        */

        //imagine feeling safe because you think your car will auto-brake when it hits 80!

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("The Best Comments\n");

        //code, by itself, should be extremely...
        //clear,
        //clean,
        //concise,
        //noise-free,
        //non-ambiguous,
        //self-explanatory,
        //purpose-revealing,
        //intention-revealing,
        //sensibly related to the context around it, and
        //agnostic of the code outside its context.

        //the ultimate comment for the code is...
        //the code itself!

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("Version History\n");

        //there really is no need to comment how old/new is a piece of code.
        //there is also no need to comment who is the programmer
        //who introduced the piece of code or who edited it last.

        //we have versioning tools like git for that.

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("Comments for Clarifying the Code\n");

        //some programmers prefer to use unintelligible names for variables
        //and then comment their meaning super clearly, like:

        /*
        var pf = 10; //pay frequency
        */

        //instead of just naming it super clearly outright!

        /*
        var payFrequency = 10;
        */

        //imagine having to write that comment in every line with pf on it!!!!

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("Dead Code\n");

        //code that is not needed anymore should be deleted, not commented out.
        //dead code is nothing more than noise.

        //if it's eventually needed back, be not afraid of having lost it,
        //versioning tools like git can be used as a backup,
        //so nothing is ever really lost!

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("TODO Comments\n");

        //comments starting with "TODO" (as in, thing to do),
        //are a kind of comment which state stuff that is not implemented yet,
        //but definitely should be implemented asap.

        //it's fine to have them, especially if it's just a reminder
        //written by a programmer to be read by him/herself later
        //and acted upon in the same code sprint.

        //they are commonly used as a temporary reminder
        //for implementing some idea,
        //while the programmer is in the middle of writing some other piece of code.

        //however, if a TODO comment
        //remains in the codebase for anything more than a sprint or two,
        //then it becomes noise.

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("Constraints\n");

        //another kind of comment that is usually fine
        //are constraints.

        //these are explanations on why the code is written the way it is
        //for example, if the code needs to comply with some condition
        //that's outside of our control.

        //like the datatype a closed-source dependency expects, or
        //maintaining backwards compatibility with a previous version, or
        //the choice of an encryption algorithm as enforced by some law.

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("Exercise 10\n");

        /*
        public class Customer {

            public int countryCode;
            public String email;

            public Customer(int _countryCode, String _email) {
                this.countryCode = _countryCode;
                this.email       = _email;
            }
            //Customer
        }
        //Customer

        public class Order {

            public Customer customer;

            public Order(Customer _customer) {

                this.customer = _customer;
            }
            //Order
        }
        //Order

        public class CustomersDbContext {

            public List<Order> orders;

            public CustomersDbContext() {

                this.orders = new ArrayList<>();
            }
            //CustomersDbContext

            public void saveChanges() {

                //some DB related logic goes here...
            }
            //saveChanges
        }
        //CustomersDbContext

        public class MailMessage {

            public String fromAddress;
            public String toAddress;
            public String emailContent;

            public MailMessage(String _fromAddress, String _toAddress, String _emailContent) {
                this.fromAddress  = _fromAddress;
                this.toAddress    = _toAddress;
                this.emailContent = _emailContent;
            }
            //MailMessage
        }
        //MailMessage

        public class SmtpClient {

            public void send(MailMessage msg) {

                //some email related logic goes here...
            }
            //send
        }
        //SmtpClient

        public class Comments {

            private int pf; //pay frequency
            private CustomersDbContext dbContext;

            public Comments() {

                this.dbContext = new CustomersDbContext();
            }
            //Comments

            //returns list of customers in a country
            public List<Customer> getCustomers(int countryCode) {

                //TODO: we need to get rid of abcd once we revisit this method.
                //      Currently it's creating a coupling between x and y
                //      and because of that, we're not able to do xyz.

                throw new NotImplementedException();
            }
            //getCustomers

            public void submitOrder(Order order) {

                //save the order in the DB
                this.dbContext.orders.add(order);
                this.dbContext.saveChanges();

                //send email to the customer
                var client = new SmtpClient();
                var mail   = new MailMessage("a@b.c", order.customer.email, "order placed.");
                client.send(mail);
            }
            //submitOrder
        }
        //Comments
        */

        //there are so many refactorings possible in here.
        //also, so many comments we could change or remove.

        //however, notice that the Comments class
        //is the only one that needs some work.
        //the rest are auxiliary classes which look OK
        //and can be left as-is.

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("Refactoring Exercise 10\n");

        //we'll refactor the Comments class here:

        //start with the obvious: rename pf to payFrequency
        //and remove the comment there.

        //next, remove the comment explaining
        //the purpose of the getCustomers() method.
        //the method signature is self-explanatory by itself.

        //inside it, it's fine to leave the comment with "TODO".

        //next, the submitOrder() method has some
        //too obvious comments inside.

        //however, we may notice it is performing TWO tasks, not one:
        //saving an order in the DB, and
        //sending an email to the customer.

        //we should refactor methods that do more than one task
        //into one method per task.

        //so we extract the saveOrder() and notifyCustomer() methods.
        //and of course, remove their comments.

        //the Comments class now looks like this:

        /*
        public class Comments {

            private int payFrequency;
            private CustomersDbContext dbContext;

            public Comments() {

                this.dbContext = new CustomersDbContext();
            }
            //Comments

            public List<Customer> getCustomers(int countryCode) {

                //TODO: we need to get rid of abcd once we revisit this method.
                //      Currently it's creating a coupling between x and y
                //      and because of that, we're not able to do xyz.

                throw new NotImplementedException();
            }
            //getCustomers

            public void saveOrder(Order order) {
                this.dbContext.orders.add(order);
                this.dbContext.saveChanges();
            }
            //saveOrder

            public void notifyCustomer(Customer customer) {
                var client = new SmtpClient();
                var mail   = new MailMessage("a@b.c", customer.email, "order placed.");
                client.send(mail);
            }
            //notifyCustomer

            public void submitOrder(Order order) {
                saveOrder(order);
                notifyCustomer(order.customer);
            }
            //submitOrder
        }
        //Comments
        */

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("Summary\n");

        //comments should explain the "why" and the "how"
        //if those can't be made super clear by the code itself.

        //comments should never explain the "what".
        //the code itself should be clear enough to do that!

        //if tempted to write a comment
        //try first to re-write your code in the best way!

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("END OF LESSON 11!\n");

        //
    }
    //runExamples
}
//CommentsExamples

//eof
