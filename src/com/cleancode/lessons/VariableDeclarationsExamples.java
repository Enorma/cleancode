package com.cleancode.lessons;

public class VariableDeclarationsExamples {

    public static void runExamples() {

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("LESSON 6: VARIABLE DECLARATIONS\n");

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("Variable declarations at the top\n");

        //we don't really need to declare
        //all our variables at the top of methods.

        //if we declare them right where they'll be used
        //the reader will know why it was declared and why it's there.

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("Exercise 5\n");

        //have a look at this code and try to determine
        //if the variable declarations at the top
        //would be better in some other place:

        /*
        public float calcGross(float rate, float hours) {

            //should these declarations be here?
            //let's check each one, one by one:

            float overtimeHours = 0; //used in multiple places, so best keep it here
            float regularHours  = 0; //used in multiple places, so best keep it here
            float regularPay    = 0; //used only at the end, we could move it there!
            float overtimePay   = 0; //used only at the end, we could move it there!
            float grossPay      = 0; //written only once and returned, we could even remove it!

            if( payFrequency == Frequencies.FORTNIGHTLY ) {

                if( hours > 80 ) {
                    overtimeHours = hours - 80;
                    regularHours = 80;
                }else {
                    regularHours = hours;
                }
                //if-else
            }
            else if( payFrequency == Frequencies.WEEKLY ) {

                if( hours > 40 ) {
                    overtimeHours = hours - 40;
                    regularHours = 40;
                }else {
                    regularHours = hours;
                }
                //if-else
            }
            //if-else

            if( overtimeHours > 0 ) overtimePay += overtimeHours * (rate * 1.5);

            regularPay = (rate * regularHours);
            grossPay = regularPay + overtimePay;

            return grossPay;
        }
        //calcGross
        */

        //now, having analyzed the previous code,
        //here's how it would look
        //with its variables declared where they should:

        /*
        public float calcGross(float rate, float hours) {

            float overtimeHours = 0; //used in multiple places, so best keep it here
            float regularHours  = 0; //used in multiple places, so best keep it here

            if( payFrequency == Frequencies.FORTNIGHTLY ) {

                if( hours > 80 ) {
                    overtimeHours = hours - 80;
                    regularHours = 80;
                }else {
                    regularHours = hours;
                }
                //if-else
            }
            else if( payFrequency == Frequencies.WEEKLY ) {

                if( hours > 40 ) {
                    overtimeHours = hours - 40;
                    regularHours = 40;
                }else {
                    regularHours = hours;
                }
                //if-else
            }
            //if-else

            //if not declared inside scoped blocks
            //(like the if block below)
            //and if we intend to initialize a variable
            //at the moment of declaration,
            //it's better to declare and initialize variables inline:

            float overtimePay = 0; //used right here, so it should be declared here!

            float regularPay = (rate * regularHours); //used right here, so it should be declared here!
            if( overtimeHours > 0 ) overtimePay += overtimeHours * (rate * 1.5);

            return regularPay + overtimePay; //the grossPay variable is gone!
        }
        //calcGross
        */

        //now, the method has less noise at the top.

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("Summary\n");

        //Declare variables as close to their usage as possible.
        //if not possible, it's ok to keep them at the top.

        //it's better to initialize variables inline at the time of declaration.

        //however, if the variable is intended to be assigned a value
        //either conditionally or inside some exclusive scope
        //(like inside an if block)
        //it's better to initialize them with a default value
        //right before said condition or scope.

        //otherwise, initialize them inline with the intended value right away!

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("END OF LESSON 6!\n");

        //
    }
    //runExamples
}
//VariableDeclarationsExamples

//eof
