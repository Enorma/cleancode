package com.cleancode.lessons;

public class DuplicatedCodeExamples {

    public static void runExamples() {

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("LESSON 10: DUPLICATED CODE\n");

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("Duplicated Code\n");

        //there is a principle called DRY
        //which stands for: "Don't Repeat Yourself".

        //the opposite of which is WET
        //which stands for...
        //"Write Everything Twice",
        //"Write Every Time",
        //"We Enjoy Typing",
        //"Waste Everyone's Time", etc.

        //Code duplication is basically writing one
        //piece of code, more than once.

        //having more than one instance of the same piece of code
        //immediately introduces complexity,
        //because whenever some code calls the repeated code
        //there's ambiguity as to which of the instances was called.

        //also, if there's a modification to make in one of those instances,
        //more than likely it will need to be made in the other ones as well,
        //leading to more repeated code and wasted time.

        //imagine spending a lot of time debugging a piece of code,
        //with no good results,
        //just to realize you've been debugging the wrong instance
        //of the same piece of code!

        //or, imagine releasing a version of your software
        //which supposedly has a bugfix in a method,
        //but then, some parts of the software still hit the bug
        //because there's another instance of said method
        //where the bug wasn't fixed.

        //that is why there needs to be a single-source-of-truth
        //when it comes to code.

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("Exercise 9\n");

        //take a look at this real-world example:

        /*
        public class DuplicatedCode {

            public void admitGuest(String name, String admissionDateTime) {

                //some more logic could be here,
                //but it's not important for this example.

                int time    = 0;
                int hours   = 0;
                int minutes = 0;

                if( admissionDateTime != null && !admissionDateTime.equals("") ) {

                    String admissionNoColon = admissionDateTime.replace(":", "");

                    try {

                        time = Integer.parseInt(admissionNoColon);
                    }
                    catch(NumberFormatException e) {

                        throw new IllegalArgumentException("bad admissionDateTime");
                    }
                    //try-catch

                    hours   = time / 100;
                    minutes = time % 100;
                }
                else {

                    throw new IllegalArgumentException("bad admissionDateTime");
                }
                //if-else

                //some more logic...
            }
            //admitGuest

            public void updateAdmission(String name, String admissionDateTime, int admissionId) {

                //some more logic...

                int time    = 0;
                int hours   = 0;
                int minutes = 0;

                if( admissionDateTime != null && !admissionDateTime.equals("") ) {

                    String admissionNoColon = admissionDateTime.replace(":", "");

                    try {

                        time = Integer.parseInt(admissionNoColon);
                    }
                    catch(NumberFormatException e) {

                        throw new IllegalArgumentException("bad admissionDateTime");
                    }
                    //try-catch

                    hours   = time / 100;
                    minutes = time % 100;
                }
                else {

                    throw new IllegalArgumentException("bad admissionDateTime");
                }
                //if-else

                //some more logic...
            }
            //updateAdmission
        }
        //DuplicatedCode
        */

        //both methods run the exact same code!!!!
        //we obviously need to refactor them.

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("Refactoring Exercise 9\n");

        //the first and most obvious thing to do
        //is to extract the repeated logic into its own method.
        //so we now have the extracted getAdmissionTime() method,
        //which takes an admissionDateTime string
        //and parses the hours and minutes (both integers) from it.

        //the trouble is, we need both hours and minutes to be returned
        //and Java can only return one value at a time.
        //so we'll create the AdmissionTime class for that.
        //the getAdmissionTime() method will then return an AdmissionTime object.

        //some other best practices can be applied at this point:
        //we can refactor the big if-else statement in getAdmissionTime()
        //by turning it into a guard statement.
        //we can also lower or inline the variable declarations
        //instead of having them at the top.

        //however, at this point the getAdmissionTime() method, in fact
        //knows nothing about admissions like admitGuest() and updateAdmission() do!
        //it's basically just a string parser method.

        //so it's actually better to make it agnostic of who is using it
        //by extracting it into the AdmissionTime class
        //and then rename everything in that class to something
        //independent of the admissions:

        //the AdmissionTime class becomes CustomTime,
        //the getAdmissionTime() method becomes parseTime(),
        //the admissionDateTime arg becomes dateTimeString.

        //the code finally looks like this:

        /*
        public class CustomTime {

            public int hours;
            public int minutes;

            public CustomTime(int _hours, int _minutes) {
                this.hours   = _hours;
                this.minutes = _minutes;
            }
            //CustomTime

            public static CustomTime parseTime(String dateTimeString) {

                if( dateTimeString == null || dateTimeString.equals("") ) {

                    throw new IllegalArgumentException("bad dateTimeString");
                }
                //if

                String dateTimeNoColon = dateTimeString.replace(":", "");

                int time = 0;
                try {

                    time = Integer.parseInt(dateTimeNoColon);
                }
                catch(NumberFormatException e) {

                    throw new IllegalArgumentException("bad dateTimeString");
                }
                //try-catch

                int hours   = time / 100;
                int minutes = time % 100;

                return new CustomTime(hours, minutes);
            }
            //parseTime
        }
        //CustomTime

        public class DuplicatedCode {

            public void admitGuest(String name, String admissionDateTime) {

                //some more logic could be here,
                //but it's not important for this example.

                //this was a whole repeated block, now we just call one method!
                CustomTime time = CustomTime.parseTime(admissionDateTime);

                //some more logic for admitting a guest...
            }
            //admitGuest

            public void updateAdmission(String name, String admissionDateTime, int admissionId) {

                //some more logic...

                //this was a whole repeated block, now we just call one method!
                CustomTime time = CustomTime.parseTime(admissionDateTime);

                //some more logic for updating a guest's admission...
            }
            //updateAdmission
        }
        //DuplicatedCode
        */

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("Summary\n");

        //don't repeat yourself!
        //be not afraid of extracting repeated code
        //into new classes or methods.

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("END OF LESSON 10!\n");

        //
    }
    //runExamples
}
//DuplicatedCodeExamples

//eof
