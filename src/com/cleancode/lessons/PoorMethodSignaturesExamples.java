package com.cleancode.lessons;

import java.util.List;

public class PoorMethodSignaturesExamples {

    public static void runExamples() {

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("LESSONS 3-5: POOR METHOD SIGNATURES\n");

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("Mysterious Names\n");

        //method signatures should convey
        //the purpose and intention of the method clearly.

        //therefore, the method signature should make sense.

        //for example, this method signature makes no sense at all:
        //Orange getCustomer(int airplane);
        //a method for getting a customer should return A CUSTOMER, not an orange!!!
        //how exactly is an airplane an integer?
        //why do we need an airplane to find a customer?

        //here's another example:
        //void parse(int conmmand);
        //usually, to parse is to get some value from a string.
        //however, the arg is an integer.
        //if we expected to parse an integer from a string, that's fine
        //but why is the return type void then?
        //this is way better:
        //int parse(String command);

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("Exercise 2\n");

        //have a look at this exercise:

        /*
        public class UserService {

            private userDbContext _dbContext = new UserDbContext();

            public User getUser(String username, String password, boolean login) {

                if(login) { //we only use the 3rd arg for this if-else branching

                    //check if user exists by username/password
                    //if yes, set last login day and return user

                    var user = _dbContext.Users.singleOrDefault( u -> u.username==username && u.password==password );
                    if( user != null ) user.lastLogin = DateTime.now();
                    return user;
                }else {

                    //check if user exists by username
                    //if yes, return user

                    var user = _dbContext.Users.singleOrDefault( u -> u.username==username );
                    return user;
                }
                //if-else
            }
            //getUser
        }
        //UserService

        public class PoorMethodSignatures {

            public void run() {

                var userService = new UserService();
                var user    = userService.getUser("username", "password", true); //last arg is mysterious
                var another = userService.getUser("username", null, false); //last arg is mysterious
            }
            //run
        }
        //PoorMethodSignatures
        */

        //that code called a method called getUser() twice.
        //getUser()'s last arg is a boolean flag, which seems
        //mysterious in the method call.

        //we just see:
        //getUser("username", "password", true);
        //and have no idea what true/false will do, at first glance.
        //for this reason, boolean flags in method signatures are a bad practice.

        //how do we fix it?
        //notice in the getUser() method, the flag
        //just does a block of if-else branching.
        //if both branches become their own methods,
        //then we can call the one we want directly.

        //in this way, we will end up with leaner methods
        //with clearer and more intention-revealing signatures.

        //here's the final result:

        /*
        public class UserService {

            private userDbContext _dbContext = new UserDbContext();

            public User login(String username, String password) {

                //check if user exists by username/password
                //if yes, set last login day and return user

                var user = _dbContext.Users.singleOrDefault( u -> u.username==username && u.password==password );
                if( user != null ) user.lastLogin = DateTime.now();
                return user;
            }
            //login

            public User getUser(String username) {

                //check if user exists by username
                //if yes, return user

                var user = _dbContext.Users.singleOrDefault( u -> u.username==username );
                return user;
            }
            //getUser
        }
        //UserService

        public class PoorMethodSignatures {

            public void run() {

                //the method names for login() and getUser()
                //are intention-revealing now!

                var userService = new UserService();
                var user    = userService.login("username", "password"); //every arg is meaningful!
                var another = userService.getUser("username"); //every arg is meaningful!
            }
            //run
        }
        //PoorMethodSignatures
        */

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("Summary\n");

        //when writing a method, always triple check...

        //the method name
        //the return type
        //the args

        //make sure they are clearly, simply and logically related
        //and make sure they convey their meaning and intention at first glance.

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("Long Parameter Lists\n");

        //dare you decrypt the meaning of this method signature
        //just by looking at it:
        //checkNotifications( null, 1, true, false, DateTime.now() );

        //once again, the args don't instantly convey their intention
        //plus there are many of them, which probably means
        //the method is bulky and does many things.

        //methods, however, should be lean, do exactly one thing (but do it very well),
        //and be clear about their intentions at first glance.

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("Exercise 3\n");

        //have a look at this example:

        /*
        private class ReservationDefinition {

            //...
        }
        //ReservationDefinition

        public class LongParameterList {

            public IEnumerable<Reservation> getReservations(
                DateTime dateFrom,
                DateTime dateTo,
                User user,
                int locationId,
                LocationType locationType,
                int customerId
            ) {
                if( dateFrom >= DateTime.now() ) throw new ArgumentNullException("dateFrom");
                if( dateTo   <= DateTime.now() ) throw new ArgumentNullException("dateTo");
                throw new NotImplementedException();
            }
            //getReservations

            public IEnumerable<Reservation> getUpcomingReservations(
                DateTime dateFrom,
                DateTime dateTo,
                User user,
                int locationId,
                LocationType locationType
            ) {
                if( dateFrom >= DateTime.now() ) throw new ArgumentNullException("dateFrom");
                if( dateTo   <= DateTime.now() ) throw new ArgumentNullException("dateTo");
                throw new NotImplementedException();
            }
            //getUpcomingReservations

            private static Tuple<DateTime,DateTime> getReservationDateRange(
                DateTime dateFrom,
                DateTime dateTo,
                ReservationDefinition sd
            ) {
                if( dateFrom >= DateTime.now() ) throw new ArgumentNullException("dateFrom");
                if( dateTo   <= DateTime.now() ) throw new ArgumentNullException("dateTo");
                throw new NotImplementedException();
            }
            //getReservationDateRange

            public void createReservation (
                DateTime dateFrom,
                DateTime dateTo,
                int locationId
            ) {
                if( dateFrom >= DateTime.now() ) throw new ArgumentNullException("dateFrom");
                if( dateTo   <= DateTime.now() ) throw new ArgumentNullException("dateTo");
                throw new NotImplementedException();
            }
            //createReservation
        }
        //LongParameterList
        */

        //as we can see, all of the methods above take the args
        //DateTime dateFrom and DateTime dateTo.

        //we can encapsulate both into a new class
        //called DateRange, and so, we'd now only pass
        //one DateRange arg to every method.

        //and, since DateRange would be useful for many purposes,
        //it should be declared outside our existing class.

        //we have a similar case considering the arg lists
        //for the methods getReservations() and getUpcomingReservations()
        //are basically the same.

        //we can extract a new class called ReservationsQuery
        //in a similar way.

        //the final result would be:

        /*
        public class DateRange {

            private DateTime dateFrom;
            private DateTime dateTo;

            public DateTime getDateFrom() { return this.dateFrom; }
            public DateTime getDateTo() { return this.dateTo; }
        }
        //DateRange

        public class ReservationsQuery {

            private DateRange dateRange;
            private User user;
            private int locationId;
            private LocationType locationType;

            public DateRange getDateRange() { return this.dateRange; }
            public User getUser() { return this.user; }
            public int getLocationId() { return this.locationId; }
            public LocationType getLocationType() { return this.locationType; }
        }
        //ReservationsQuery

        private class ReservationDefinition {

            //...
        }
        //ReservationDefinition

        public class LongParameterList {

            public IEnumerable<Reservation> getReservations(
                ReservationsQuery query,
                int customerId
            ) {
                if( query.getDateRange().getDateFrom() >= DateTime.now() ) throw new ArgumentNullException("dateFrom");
                if( query.getDateRange().getDateTo()   <= DateTime.now() ) throw new ArgumentNullException("dateTo");
                throw new NotImplementedException();
            }
            //getReservations

            public IEnumerable<Reservation> getUpcomingReservations(
                ReservationsQuery query
            ) {
                if( query.getDateRange().getDateFrom() >= DateTime.now() ) throw new ArgumentNullException("dateFrom");
                if( query.getDateRange().getDateTo()   <= DateTime.now() ) throw new ArgumentNullException("dateTo");
                throw new NotImplementedException();
            }
            //getUpcomingReservations

            private static Tuple<DateTime,DateTime> getReservationDateRange(
                DateRange dateRange,
                ReservationDefinition sd
            ) {
                if( dateRange.getDateFrom() >= DateTime.now() ) throw new ArgumentNullException("dateFrom");
                if( dateRange.getDateTo()   <= DateTime.now() ) throw new ArgumentNullException("dateTo");
                throw new NotImplementedException();
            }
            //getReservationDateRange

            public void createReservation (
                DateRange dateRange,
                int locationId
            ) {
                if( dateRange.getDateFrom() >= DateTime.now() ) throw new ArgumentNullException("dateFrom");
                if( dateRange.getDateTo()   <= DateTime.now() ) throw new ArgumentNullException("dateTo");
                throw new NotImplementedException();
            }
            //createReservation
        }
        //LongParameterList
        */

        //now our methods have at most two args!
        //that's way leaner!

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("Summary\n");

        //methods should have at most 3 args.
        //if we can't have less than that,
        //we probably can (and should) refactor something somewhere.

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("Output Parameters\n");

        //this mistake should be avoided at all costs.

        //let's say we intend that one of the method args
        //is an object that will be accessed by reference
        //and modified inside the method,
        //so that the changes made to it are auto-saved permanently
        //within it, without the need to return it.

        //this makes it necessary to have the object
        //pre-declared and pre-initialized in some way
        //that the method expects.

        //first of all, it's weird to think of a method's output
        //coming out of it from the args instead of the return value.

        //and it gets worse if we have many args designed in this way.
        //it becomes unclear what the method returns.

        //for example:

        //Integer count = 0;
        //Boolean more = false;
        //var customers = getCustomers(index, count, more);

        //at first glance, it only returns customers,
        //but it also returns the count and more objects, updated.
        //plus, we need to pre-initialize those as 0 and false
        //or the method won't work as expected.

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("Exercise 4\n");

        //consider this code:

        /*
        public class OutputParameters {

            public IEnumerable<Customer> getCustomers(int pageIndex, Integer totalCount) {
                //here we mock the logic of actually getting customer data from a DB
                totalCount = 100; //updates the arg object, not a copy of it, permanently
                return new List<Customer>(); //return a dummy list
            }
            //getCustomers

            public void displayCustomers() {
                Integer totalCount = 0; //forced to pre-initialize as zero
                var customers = getCustomers(1, totalCount); //expects initialized object
                System.out.println("Total customers: " + totalCount); //supposes the object is now updated
                for(var c : customers) System.out.println(c);
            }
            //displayCustomers
        }
        //OutputParameters
        */

        //notice all the assumptions being made
        //concerning the totalCount variable.

        //only the programmer knows what to expect.
        //other programmers won't have a clue!

        //now, clearly we need to update totalCount inside getCustomers
        //and get the updated totalCount plus a list of customers, as a result.

        //it's better to make a new class designed to hold
        //both pieces of data, and return an object made from it
        //with the data we want:

        /*
        public class CustomersReport {

            //fields
            //these two fields are the data we expect out of the getCustomers() method!
            private List<Customer>() customers;
            private int totalCount;

            //constructor
            public CustomersReport(List<Customer> _customers, int _totalCount) {
                this.customers = _customers;
                this.totalCount = _totalCount;
            }
            //CustomersReport

            //getters
            public List<Customer> getCustomers() { return this.customers; }
            public int getTotalCount() { return this.totalCount; }
        }
        //CustomersReport

        public class OutputParameters {

            public CustomersReport getCustomers(int pageIndex) { //we don't need totalCount as arg anymore!
                //here we mock the logic of actually getting customer data from a DB
                return new CustomersReport( new List<Customer>(), 100 ); //now both values are properly returned!
            }
            //getCustomers

            public void displayCustomers() {
                var customers = getCustomers(1); //no need to initialize anything before this!
                System.out.println( "Total customers: " + customers.getTotalCount() );
                for( var c : customers.getCustomers() ) System.out.println(c);
            }
            //displayCustomers
        }
        //OutputParameters
        */

        //see?
        //output parameters in methods are always a bad practice.
        //it's always better to make all output come out
        //as the return value

        //even if we need to create a new structure (like a class) for that.

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("END OF LESSONS 3-5!\n");

        //
    }
    //runExamples
}
//PoorMethodSignaturesExamples

//eof
