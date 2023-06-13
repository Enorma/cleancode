package com.cleancode.lessons;

public class LongMethodsExamples {

    public static void runExamples() {

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("LESSON 12: LONG METHODS\n");

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("Problems With Long Methods\n");

        //methods should be ideally around 5 lines long.
        //10 lines at most.

        //any method over 10 lines is bad code.

        //long methods are hard to understand, to change and to reuse.

        //a long method is most likely doing many things.
        //but, methods are better if they specialize in only one thing.

        //we should have methods where every line is tightly related.
        //anything loosely related or outright unrelated
        //should be extracted into another method.

        //according to the cohesion principle:
        //related things should be together,
        //unrelated things should not.

        //this principle applies both to methods and to classes.

        //also, there's the single-responsibility principle, which states:
        //a class/method should do exactly one thing, but do it very well.
        //which is also known as "the UNIX way".

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("Exercise 11\n");

        //have a look at this code:

        /*
        public class Download extends WebPage {

            public System.IO.MemoryStream createMemoryFile() {

                //create a memory stream which will be the response
                MemoryStream returnStream = new MemoryStream();

                try {

                    //get the data from the database
                    String strConn = ConfigurationManager.connectionStrings["FooFooConnectionString"].toString();
                    SqlConnection conn = new SqlConnection(strConn);
                    SqlDataAdapter da = new SqlDataAdapter("SELECT * FROM [FooFoo] ORDER BY id ASC", conn);
                    DataSet ds = new DataSet();
                    da.Fill(ds, "FooFoo");
                    DataTable dt = ds.Tables["FooFoo"];

                    //Create a stream writer to write to the memory stream
                    StreamWriter sw = new StreamWriter(ReturnStream);

                    int iColCount = dt.columns.count;

                    //iterate on the data columns
                    //and write them to the memory stream
                    for(int i=0; i<iColCount; i++) {
                        sw.write(dt.columns[i]);
                        if( i < iColCount-1 ) sw.write(",");
                    }
                    //for

                    sw.writeLine();
                    int intRows = dt.rows.count;

                    //write all the rows to the memory stream
                    for(DataRow dr : dt.rows) {

                        for(int i=0; i<iColCount; i++) {

                            if( !convert.isDbNull(dr[i]) ) {

                                String str = String.format( "\"{0:c}\"", dr[i].toString() );
                                str = str.replace("\r\n", " ");
                                sw.write(str);
                            }
                            else {

                                sw.write("");
                            }
                            //if-else

                            if( i < iColCount-1 ) sw.write(",");
                        }
                        //for

                        sw.writeLine();
                    }
                    //foreach

                    sw.flush();
                    sw.close();
                }catch(Exception e) {

                    throw e;
                }
                //try-catch

                return returnStream;
            }
            //createMemoryFile

            protected void pageLoad(Object sender, EventArgs e) {

                //reads a table from DB and keeps it as CSV
                System.IO.MemoryStream ms = createMemoryFile();

                //convert the CSV to byte array
                byte[] byteArray = ms.toArray();
                ms.flush();
                ms.close();

                //build the response:
                //(response is a field of Download, inherited from WebPage)

                //initialize the response
                response.clear();
                response.clearContent();
                response.clearHeaders();
                response.cookies.clear();

                //set up the cache
                response.cache.setCacheability(HttpCacheability.PRIVATE);
                response.cacheControl = "private";
                response.appendHeader("Pragma", "cache");
                response.appendHeader("Expires", "60");

                //set up the content
                response.charset = System.Text.UTF8Encoding.UTF8.WebName;
                response.contentEncoding = System.Text.UTF8Encoding.UTF8;
                response.contentType = "text/comma-separated-values";
                response.addHeader("Content-Disposition", "attachment; filename=FooFoo.csv");
                response.addHeader("Content-Length", byteArray.length.toString());

                //write out the response
                response.binaryWrite(byteArray);
            }
            //pageLoad
        }
        //Download
        */

        //clearly, the pageLoad() method is pretty big
        //but the createMemoryFile() is HUGE!

        //plus, it has nested conditions, loops and try-catches,
        //so refactoring it is urgent.

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("Refactoring Exercise 11, part 1\n");

        //first of all, our class Download is a subclass of WebPage.
        //a WebPage concerns only the presentation of a website view.

        //so it should concern only how to lay the data out on the page,
        //not how to query it from a DB or how to reformat it.

        //the createMemoryFile() method is concerned with
        //querying a DB for data and building a CSV table with it.
        //said CSV table is intended to be fed
        //to the presentation layer afterwards.

        //so, the createMemoryFile() method has nothing to do there.
        //we need to extract it into its own class.

        //so we create the new class MemoryFileCreator, then move
        //the createMemoryFile() method in there, and then
        //fix the reference to it inside pageLoad().

        //now we can begin modularizing the methods.
        //we start with the smaller one, pageLoad().

        //we see pageLoad() does many things:
        //setting up a memory stream,
        //setting up a byte array,
        //initializing the response by cleaning and clearing it,
        //configuring the cache,
        //configuring the content, and, finally
        //writing the response.

        //we'll extract most of that into their own methods:
        //getCSV(), clearResponse(), setCacheability() and setContentForResponse().

        //since clearResponse() and setCacheability()
        //aren't concerned with the response byte array,
        //they go together with each other.

        //and since getCSV() returns the response byte array,
        //which is the arg for setContentForResponse(),
        //they go together with each other too.

        //also, we remove pageLoad()'s args since they aren't used there anymore.
        //and we remove the too-obvious comments.

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("Refactoring Exercise 11, part 2\n");

        //now that we're done with the Download class,
        //let's switch to the MemoryFileCreator class.

        //right away, we can see the first few lines inside the try block
        //are all concerned with querying the DB for data and building a table with it.
        //so we extract that into the new method getDataTable().

        //we can already start removing some too-obvious comments,
        //as well as grouping together some related lines.

        //the iColCount variable is in hungarian notation,
        //meaning, it is prepended with its datatype ("i" for integer)
        //plus, its usage is spread all over our code.

        //so, considering it's a simple integer value,
        //it's better to just re-calculate it and inline it
        //wherever it's used. So we can remove it.

        //inside the first for loop, the columns of the first row are read,
        //this gets the column names and builds the table header from them.

        //the loop depends on the sw and dt variables we have from earlier
        //but at least it doesn't depend on iColCount anymore.

        //this means we can extract the loop into a method
        //which takes sw and dt as args.
        //this will be the writeHeader() method.

        //writeHeader() should include the writeLine() method
        //which appears right below the for loop.

        //next, the intRows variable is not even used anywhere
        //so just remove it.

        //next, we have a big and ugly for loop.
        //it writes every row to the stream.
        //so it should be extracted entirely into a new method,
        //which we'll call writeRows().

        //a bit further below, we see the catch clause
        //but realize it only rethrows the exception
        //so it doesn't actually protect our code against a crash
        //which makes it useless, so we'll remove it.

        //and we'll also remove the try clause,
        //but not the code inside it.

        //our createMemoryFile() method is looking good!
        //it's much slimmer now.

        //one thing to take note of is
        //the getDataTable() is concerned just with
        //accessing and querying a DB for some data.
        //it doesn't build a CSV table like the
        //writeHeader() or writeRows() methods.

        //so it's not very cohesive to keep it inside the MemoryFileCreator class.

        //plus, if we extract it, we can reuse it
        //to query for any data, which could then be fed
        //to the createMemoryFile() method to build a CSV table with it.

        //we'll do just that, by extracting getDataTable()
        //into a new class, TableReader.

        //we'll also have the DataTable dt
        //as an arg of createMemoryFile() now.

        //we'll need to introduce a TableReader object
        //as a field of the Download class now.
        //this allows us to pass a DataTable to the createMemoryFile() method call
        //inside Download.getCSV(), by calling the TableReader object's getDataTable() method.

        //at this point, our MemoryFileCreator class is only
        //concerned with taking a data table and building a CSV table from it.
        //so it would be better to rename it to TableToCSVMapper.
        //also, we'll rename the createMemoryFile() method to mapToCSV().

        //at this point, the only method which is still big
        //is the writeRows() method inside TableToCSVMapper.

        //it iterates through the rows of the data table
        //and performs some action (an inner for loop) on every single row.

        //this inner for loop can be extracted into a new method
        //for writing a single row. We'll call it writeSingleRow().

        //in a very similar fashion, the writeSingleRow() method
        //iterates over each cell of the row
        //and performs some action (an inner if-else statement) on it.

        //that if-else statement can be extracted into a new method
        //for writing a single cell. We'll call it writeSingleCell().

        //we can also change the if-else inside it to a guard statement.

        //at this point, we can rename all the bad variable names
        //(sw, dt, dr, etc...) into something meaningful.

        //we can also reorder them in the method signatures for consistency.

        //finally, we can notice in writeSingleCell() that
        //we don't need the whole row, just a cell,
        //so instead of passing the row and the column index,
        //we just pass the cell itself.
        //this reduces the amount of args.

        //the final form of our code is:

        /*
        public class TableReader {

            public DataTable getDataTable() {

                String strConn = ConfigurationManager.connectionStrings["FooFooConnectionString"].toString();
                SqlConnection conn = new SqlConnection(strConn);
                SqlDataAdapter da = new SqlDataAdapter("SELECT * FROM [FooFoo] ORDER BY id ASC", conn);
                DataSet ds = new DataSet();
                da.Fill(ds, "FooFoo");

                return ds.Tables["FooFoo"];
            }
            //getDataTable
        }
        //TableReader

        public class TableToCSVMapper {

            public void writeHeader(DataTable table, StreamWriter writer) {

                //iterate on the data columns
                //writing every column name to the memory stream
                //to build a table header

                for(int i=0; i<table.columns.count; i++) {

                    writer.write(table.columns[i]);
                    if( i < table.columns.count-1 ) writer.write(",");
                }
                //for

                writer.writeLine();
            }
            //writeHeader

            public void writeSingleCell(DataCell cell, StreamWriter writer) {

                if( convert.isDbNull(row[columnIndex]) ) {
                    writer.write("");
                    return;
                }
                //if

                String str = String.format( "\"{0:c}\"", row[columnIndex].toString() );
                str = str.replace("\r\n", " ");
                writer.write(str);
            }
            //writeSingleCell

            public void writeSingleRow(DataRow row, DataTable table, StreamWriter writer) {

                for(int i=0; i<table.columns.count; i++) {

                    writeSingleCell(row[i], writer);
                    if( i < table.columns.count-1 ) writer.write(","); //write a separator if required
                }
                //for
            }
            //writeSingleRow

            public void writeRows(DataTable table, StreamWriter writer) {

                for(DataRow row : table.rows) {

                    writeSingleRow(row, table, writer);
                    writer.writeLine();
                }
                //foreach
            }
            //writeRows

            public System.IO.MemoryStream mapToCSV(DataTable table) {

                MemoryStream returnStream = new MemoryStream();
                StreamWriter writer = new StreamWriter(ReturnStream);

                writeHeader(table, writer);
                writeRows(table, writer);

                writer.flush();
                writer.close();

                return returnStream;
            }
            //mapToCSV
        }
        //TableToCSVMapper

        public class Download extends WebPage {

            private TableToCSVMapper mapper = new TableToCSVMapper();
            private TableReader reader = new TableReader();

            public void clearResponse() {

                response.clear();
                response.clearContent();
                response.clearHeaders();
                response.cookies.clear();
            }
            //clearResponse

            public void setCacheability() {

                response.cache.setCacheability(HttpCacheability.PRIVATE);
                response.cacheControl = "private";
                response.appendHeader("Pragma", "cache");
                response.appendHeader("Expires", "60");
            }
            //setCacheability

            public byte[] getCSV() {

                //reads a table from DB and stores it as CSV-formatted memory stream
                System.IO.MemoryStream ms = mapper.mapToCSV( reader.getDataTable() );

                //convert the memory stream to byte array
                byte[] byteArray = ms.toArray();

                //clean and close the memory stream since we don't need it anymore
                ms.flush();
                ms.close();

                return byteArray;
            }
            //getCSV

            public void setContentForResponse(byte[] byteArray) {

                response.charset = System.Text.UTF8Encoding.UTF8.WebName;
                response.contentEncoding = System.Text.UTF8Encoding.UTF8;
                response.contentType = "text/comma-separated-values";
                response.addHeader("Content-Disposition", "attachment; filename=FooFoo.csv");
                response.addHeader("Content-Length", byteArray.length.toString());
            }
            //setContentForResponse

            public void pageLoad() {

                clearResponse();
                setCacheability();

                byte[] byteArray = getCSV();
                setContentForResponse(byteArray);

                response.binaryWrite(byteArray);
            }
            //pageLoad
        }
        //Download
        */

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("Summary\n");

        //now our example code has only short and highly cohesive methods.

        //remember, methods should be 10 lines long at most
        //and they should do exactly ONE thing.

        //if a method has only one clear and concise purpose
        //it becomes self-explanatory,
        //which means we can remove noisy comments!

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("END OF LESSON 12!\n");

        //
    }
    //runExamples
}
//LongMethodsExamples

//eof
