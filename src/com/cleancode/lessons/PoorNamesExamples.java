package com.cleancode.lessons;

public class PoorNamesExamples {

    public static void runExamples() {

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("LESSONS 1-2: POOR NAMES\n");

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("Mysterious Names\n");

        //some variable names are mysterious
        //any variable name with a number, like: button1
        //is poor, because there's no way to tell
        //what the button does just by its name.

        //instead, they force the programmer to look at
        //documentation, which wastes time.

        //every variable name should reveal
        //its intention and purpose.

        //here are some examples:

        //POOR:
        //SqlDataReader dr1;
        //GOOD:
        //SqlDataReader dataReader;

        //POOR:
        //int od;
        //GOOD:
        //int overdueDays;

        //POOR:
        //void Button1_Click();
        //GOOD:
        //void checkAvailability_Click();

        //POOR:
        //class Page1 {...};
        //GOOD:
        //class ViewCustomerPage {...};

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("Meaningless Names\n");

        //can you tell what this method does?
        //void BeginCheckFunctionality_StoreClientSideCheckboxIDsArray();

        //this happens when methods include too much code.
        //the intention of the method becomes convoluted
        //and hard to explain briefly.

        //so keep your methods short and make them do
        //only one thing, so their names will be simpler, too.

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("Encodings In Names\n");

        //there's no need to prefix variable names
        //with their datatype, their encoding, or anything.

        //our IDEs tell us all that info at a glance,
        //so that's an old practice.

        //here are a few examples:

        //int iMaxRequests;
        //the leading i indicates this variable will hold an integer
        //but is that really necessary? no. it's better to just do:
        //int maxRequests;

        //var m_objCollection = new StringCollection();
        //the leading "m_obj" means it holds objects.
        //Which tells us nothing about what ARE those objects.
        //If it holds the names of countries (strings), then instead do:
        //var countryNames = new StringCollection();

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("Ambiguous Names\n");

        //variable names should reveal the intentions
        //of what they identify, very clearly.
        //we shouldn't be guessing what they do!

        //here's some examples:

        //what could this method do?
        //boolean MultiSelect() {...}
        //it could tell us if we're enabled to select multiple items,
        //or if multiple items are selected.

        //what exactly is this variable for?
        //int incidentNameId
        //is it a Name or an ID?
        //it's most likely an ID, since its an int
        //then why is "Name" in there?

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("Noisy Names\n");

        //some names are just outright redundant
        //so we could make them shorter without losing their meaning:

        //Customer theCustomer;
        //could become:
        //Customer customer;

        //List<Customer> listOfApprovedCustomers;
        //could become:
        //List<Customer> approvedCustomers;

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("Exercise 1\n");

        //we have this method, which generates an image from a string:

        /*
        public Bitmap Method1(String n) {

            var b = new Bitmap(n);
            var g = Graphics.fromImage(b);

            g.drawString( "a", SystemFonts.defaultFont, SystemBrushes.desktop, new PointF(0,0)  );
            g.drawString( "b", SystemFonts.defaultFont, SystemBrushes.desktop, new PointF(0,20) );
            g.drawString( "c", SystemFonts.defaultFont, SystemBrushes.desktop, new PointF(0,30) );

            return b;
        }
        //Method1
        */

        //first of all, Method1 is a poor name.
        //if this method generates an image, let's just call it generateImage.

        //but, make sure to do so with a refactoring tool
        //in order to not break other parts of the code.

        //ok, now, the arg is just called "n".
        //yet another very poor name.
        //we can't tell what it means!

        //we might look at the documentation and eventually learn
        //that "n" is a variable for the file path of the image.
        //so let's just call it path:

        //similar case with the variable b.
        //it clearly holds a bitmap, so why not just call it bitmap?

        //and we have the same scenario yet again with variable g.
        //it holds a graphics object. just call it graphics.

        //the refactored code reads much easier now!

        /*
        public Bitmap generateImage(String path) {

            var bitmap = new Bitmap(path);
            var graphics = Graphics.fromImage(bitmap);

            graphics.drawString( "a", SystemFonts.defaultFont, SystemBrushes.desktop, new PointF(0,0)  );
            graphics.drawString( "b", SystemFonts.defaultFont, SystemBrushes.desktop, new PointF(0,20) );
            graphics.drawString( "c", SystemFonts.defaultFont, SystemBrushes.desktop, new PointF(0,30) );

            return bitmap;
        }
        //generateImage
        */

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("Summary\n");

        //names should be:
        //not too short.
        //not too long.
        //meaningful.
        //intention-revealing.
        //purpose-revealing.
        //chosen from a high-level view of the problem to solve.

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("Poor Naming Conventions\n");

        //NOTE: this lesson is C#-specific at some points.

        //naming conventions are standardized for each language.
        //they must be followed, lest the programmers be confused
        //at first glance. for example,
        //notice the lack of consistency in these 3 names:

        //Customer GetCustomer(int _id); //wrong
        //methods should be in camelcase starting with lowercase!
        //also why does the arg start with an underscore?
        //Customer getCustomer(int id); //right

        //Customer saveCustomer(Customer Customer); //wrong
        //the arg Customer is spelled exactly like its class name!
        //Customer saveCustomer(Customer Customer); //right

        //private int _customerId; //wrong
        //private fields start with lowercase ONLY in C#, not Java
        //private int customerId; //right

        //in short, code blocks (and entire codebases)
        //should read like one single person wrote them in a single session,
        //even though they're actually written by many different programmers
        //over a span of many years.

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("END OF LESSONS 1-2!\n");

        //
    }
    //runExamples
}
//PoorNamesExamples

//eof
