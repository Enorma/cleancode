package com.cleancode.lessons;

public class MagicNumbersExamples {

    public static void runExamples() {

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("LESSON 7: MAGIC NUMBERS\n");

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("Magic Numbers\n");

        //magic numbers are literal number values
        //which, because they're literal, they don't go by a name.

        //if they were stored in a named variable,
        //such a variable's name could be self-explanatory and verbose,
        //making code clearer.

        //but instead, we get literal number values
        //whose meanings are mysterious and "magic"
        //and programmers reading the code will have to guess what they represent.

        //the best practice is to always replace magic numbers
        //with named constants, or,
        //if they represent different options for the same concept,
        //it's even better to use an enum.

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("Exercise 6\n");

        //have a look at this code.
        //can you spot all the places where a more verbose
        //constant/enum name could be used instead of a number literal?:

        /*
        public class MagicNumbers {

            public void approveDocument(int status) {

                //1 and 2 are magic numbers
                //because we know they represent some status
                //but we don't know which status.

                if( status == 1 ) System.out.println("approved with status 1!");
                else if( status == 2 ) System.out.println("approved with status 2!");
            }
            //approveDocument

            public void rejectDocument(String status) {

                switch(status) {

                    //"1" and "2" are magic numbers (even though they're strings!)
                    //because we know they represent some status
                    //but we don't know which status.

                    case "1": {
                        System.out.println("rejected with status 1!");
                        break;
                    }case "2": {
                        System.out.println("rejected with status 2!");
                        break;
                    }
                    //case
                }
                //switch
            }
            //rejectDocument
        }
        //MagicNumbers
        */

        //this is after replacing all magic numbers
        //with an enum, as a best practice:

        /*
        public enum DocumentStatus {

            //with an enum, we use named options instead of magic numbers
            //so their meanings are not mysterious anymore!

            DRAFT,
            LODGED
        }
        //DocumentStatus

        public class MagicNumbers {

            //now we'll use our enum instead of magic numbers,
            //it all becomes clearer and self-explanatory!

            public void approveDocument(DocumentStatus status) {
                //this is how we use an enum in an if-else block:
                if( status == DocumentStatus.DRAFT ) System.out.println("Draft document approved!");
                else if( status == DocumentStatus.LODGED ) System.out.println("Lodged document approved!");
            }
            //approveDocument

            public void rejectDocument(DocumentStatus status) {

                //this is how we use an enum in a switch-case block:

                switch(status) {
                    case DRAFT -> System.out.println("Draft document rejected!");
                    case LODGED -> System.out.println("Lodged document rejected!");
                }
                //switch

                //(case blocks with arrow notation have an implicit break statement at the end)
            }
            //rejectDocument
        }
        //MagicNumbers
        */

        //much more readable, right?

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("Summary\n");

        //use constants or enums instead of magic numbers.

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("END OF LESSON 7!\n");

        //
    }
    //runExamples
}
//MagicNumbersExamples

//eof
