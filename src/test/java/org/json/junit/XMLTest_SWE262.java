package org.json.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.json.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class XMLTest_SWE262 {
    /**
     * JUnit supports temporary files and folders that are cleaned up after the test.
     * https://garygregory.wordpress.com/2010/01/20/junit-tip-use-rules-to-manage-temporary-files-and-folders/
     */
    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();


    /**
     * Empty JSONObject from a non-XML string.
     */
    @Test
    public void testMethodOne_singleValue() {

        System.out.println("Rahul Hello World");

        String expectedStr =
                "{\"author\":\"Gambardella, Matthew\"}";
        String actualStr = "";

        try {
            FileReader filereader = new FileReader("src/test/resources/Catalog.xml");
            JSONObject jo = XML.toJSONObject(filereader, new JSONPointer("/catalog/book/0/author"));

            actualStr = jo.toString();

            System.out.println("Actual: " + actualStr);
            System.out.println("Expected: " + expectedStr);
            assertEquals(expectedStr, actualStr);

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
    }

    /**
     * Empty JSONObject from a non-XML string.
     */
    @Test
    public void testMethodOne_outerTag() {

        System.out.println("Rahul Hello World");

        String expectedStr =
                        "       <author>Ralls, Kim</author>\n"+
                        "       <title>Midnight Rain</title>\n"+
                        "       <genre>Fantasy</genre>\n"+
                        "       <price>5.95</price>\n"+
                        "       <publish_date>2000-12-16</publish_date>\n"+
                        "       <description>A former architect battles corporate zombies, " +
                                "an evil sorceress, and her own childhood to become queen " +
                                "of the world.</description>\n" +
                        "       <id>bk102</id>\n";

        expectedStr = XML.toJSONObject(expectedStr).toString();
        //expectedStr = expectedStr.replaceAll("\\n", "");
        String actualStr = "";

        try {
            //FileReader filereader = new FileReader("src/test/resources/file.xml");
            //JSONObject jo = XML.toJSONObject(filereader, new JSONPointer("/clinical_study/sponsors/lead_sponsor/agency_class"));
            FileReader filereader = new FileReader("src/test/resources/Catalog.xml");
            JSONObject jo = XML.toJSONObject(filereader, new JSONPointer("/catalog/book/1"));

            actualStr = jo.toString();
            //actualStr = actualStr.replaceAll((\\r\\n|\\n|\\r), "");

            System.out.println("Actual: " + actualStr);
            System.out.println("Expected: " + expectedStr);
            assertEquals(expectedStr, actualStr);

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
    }

    @Test
    public void testMethodTwo_replaceTag()
    {
        System.out.println("\nIn Test Method 2");
        String expectedStr = "{\"catalog\":{\"tname\":\"rahul jain\"}}";

        try {
            FileReader filereader = new FileReader("src/test/resources/Catalog.xml");
            JSONObject jo = XML.toJSONObject(filereader, new JSONPointer("/catalog/book"), XML.toJSONObject("<tname>rahul jain</tname>"));

            String actualStr = jo.toString();
            assertEquals(expectedStr, actualStr);

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
    }

    @Test
    public void testMethodTwo_replaceAll()
    {
        System.out.println("\nIn Test Method 2");
        String expectedStr = "{\"tname\":\"rahul jain\"}";

        try {
            FileReader filereader = new FileReader("src/test/resources/Catalog.xml");
            JSONObject jo = XML.toJSONObject(filereader, new JSONPointer("/"), XML.toJSONObject("<tname>rahul jain</tname>"));

            String actualStr = jo.toString();
            assertEquals(expectedStr, actualStr);

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
    }

    @Test
    public void testMethodThree_replaceKey()
    {
        System.out.println("\nIn Test Method three");
        String expectedStr = "<?xml version=\"1.0\"?>\n" +
                "<RJ_catalog>\n" +
                "    <RJ_book id=\"bk101\">\n" +
                "        <RJ_author>Gambardella, Matthew</RJ_author>\n" +
                "        <RJ_title>XML Developer's Guide</RJ_title>\n" +
                "        <RJ_genre>Computer</RJ_genre>\n" +
                "        <RJ_price>44.95</RJ_price>\n" +
                "        <RJ_publish_date>2000-10-01</RJ_publish_date>\n" +
                "        <RJ_description>An in-depth look at creating applications\n" +
                "            with XML.</RJ_description>\n" +
                "    </RJ_book>\n" +
                "    <RJ_book id=\"bk102\">\n" +
                "        <RJ_author>Ralls, Kim</RJ_author>\n" +
                "        <RJ_title>Midnight Rain</RJ_title>\n" +
                "        <RJ_genre>Fantasy</RJ_genre>\n" +
                "        <RJ_price>5.95</RJ_price>\n" +
                "        <RJ_publish_date>2000-12-16</RJ_publish_date>\n" +
                "        <RJ_description>A former architect battles corporate zombies, an evil sorceress, and her own childhood to become queen of the world.</RJ_description>\n" +
                "    </RJ_book>\n" +
                "    <RJ_book id=\"bk103\">\n" +
                "        <RJ_author>Corets, Eva</RJ_author>\n" +
                "        <RJ_title>Maeve Ascendant</RJ_title>\n" +
                "        <RJ_genre>Fantasy</RJ_genre>\n" +
                "        <RJ_price>5.95</RJ_price>\n" +
                "        <RJ_publish_date>2000-11-17</RJ_publish_date>\n" +
                "        <RJ_description>After the collapse of a nanotechnology\n" +
                "            society in England, the young survivors lay the\n" +
                "            foundation for a new society.</RJ_description>\n" +
                "    </RJ_book>\n" +
                "    <RJ_book id=\"bk104\">\n" +
                "        <RJ_author>Corets, Eva</RJ_author>\n" +
                "        <RJ_title>Oberon's Legacy</RJ_title>\n" +
                "        <RJ_genre>Fantasy</RJ_genre>\n" +
                "        <RJ_price>5.95</RJ_price>\n" +
                "        <RJ_publish_date>2001-03-10</RJ_publish_date>\n" +
                "        <RJ_description>In post-apocalypse England, the mysterious\n" +
                "            agent known only as Oberon helps to create a new life\n" +
                "            for the inhabitants of London. Sequel to Maeve\n" +
                "            Ascendant.</RJ_description>\n" +
                "    </RJ_book>\n" +
                "    <RJ_book id=\"bk105\">\n" +
                "        <RJ_author>Corets, Eva</RJ_author>\n" +
                "        <RJ_title>The Sundered Grail</RJ_title>\n" +
                "        <RJ_genre>Fantasy</RJ_genre>\n" +
                "        <RJ_price>5.95</RJ_price>\n" +
                "        <RJ_publish_date>2001-09-10</RJ_publish_date>\n" +
                "        <RJ_description>The two daughters of Maeve, half-sisters,\n" +
                "            battle one another for control of England. Sequel to\n" +
                "            Oberon's Legacy.</RJ_description>\n" +
                "    </RJ_book>\n" +
                "    <RJ_book id=\"bk106\">\n" +
                "        <RJ_author>Randall, Cynthia</RJ_author>\n" +
                "        <RJ_title>Lover Birds</RJ_title>\n" +
                "        <RJ_genre>Romance</RJ_genre>\n" +
                "        <RJ_price>4.95</RJ_price>\n" +
                "        <RJ_publish_date>2000-09-02</RJ_publish_date>\n" +
                "        <RJ_description>When Carla meets Paul at an ornithology\n" +
                "            conference, tempers fly as feathers get ruffled.</RJ_description>\n" +
                "    </RJ_book>\n" +
                "    <RJ_book id=\"bk107\">\n" +
                "        <RJ_author>Thurman, Paula</RJ_author>\n" +
                "        <RJ_title>Splish Splash</RJ_title>\n" +
                "        <RJ_genre>Romance</RJ_genre>\n" +
                "        <RJ_price>4.95</RJ_price>\n" +
                "        <RJ_publish_date>2000-11-02</RJ_publish_date>\n" +
                "        <RJ_description>A deep sea diver finds true love twenty\n" +
                "            thousand leagues beneath the sea.</RJ_description>\n" +
                "    </RJ_book>\n" +
                "    <RJ_book id=\"bk108\">\n" +
                "        <RJ_author>Knorr, Stefan</RJ_author>\n" +
                "        <RJ_title>Creepy Crawlies</RJ_title>\n" +
                "        <RJ_genre>Horror</RJ_genre>\n" +
                "        <RJ_price>4.95</RJ_price>\n" +
                "        <RJ_publish_date>2000-12-06</RJ_publish_date>\n" +
                "        <RJ_description>An anthology of horror stories about roaches,\n" +
                "            centipedes, scorpions  and other insects.</RJ_description>\n" +
                "    </RJ_book>\n" +
                "    <RJ_book id=\"bk109\">\n" +
                "        <RJ_author>Kress, Peter</RJ_author>\n" +
                "        <RJ_title>Paradox Lost</RJ_title>\n" +
                "        <RJ_genre>Science Fiction</RJ_genre>\n" +
                "        <RJ_price>6.95</RJ_price>\n" +
                "        <RJ_publish_date>2000-11-02</RJ_publish_date>\n" +
                "        <RJ_description>After an inadvertant trip through a Heisenberg\n" +
                "            Uncertainty Device, James Salway discovers the problems\n" +
                "            of being quantum.</RJ_description>\n" +
                "    </RJ_book>\n" +
                "    <RJ_book id=\"bk110\">\n" +
                "        <RJ_author>O'Brien, Tim</RJ_author>\n" +
                "        <RJ_title>Microsoft .NET: The Programming Bible</RJ_title>\n" +
                "        <RJ_genre>Computer</RJ_genre>\n" +
                "        <RJ_price>36.95</RJ_price>\n" +
                "        <RJ_publish_date>2000-12-09</RJ_publish_date>\n" +
                "        <RJ_description>Microsoft's .NET initiative is explored in\n" +
                "            detail in this deep programmer's reference.</RJ_description>\n" +
                "    </RJ_book>\n" +
                "    <RJ_book id=\"bk111\">\n" +
                "        <RJ_author>O'Brien, Tim</RJ_author>\n" +
                "        <RJ_title>MSXML3: A Comprehensive Guide</RJ_title>\n" +
                "        <RJ_genre>Computer</RJ_genre>\n" +
                "        <RJ_price>36.95</RJ_price>\n" +
                "        <RJ_publish_date>2000-12-01</RJ_publish_date>\n" +
                "        <RJ_description>The Microsoft MSXML3 parser is covered in\n" +
                "            detail, with attention to XML DOM interfaces, XSLT processing,\n" +
                "            SAX and more.</RJ_description>\n" +
                "    </RJ_book>\n" +
                "    <RJ_book id=\"bk112\">\n" +
                "        <RJ_author>Galos, Mike</RJ_author>\n" +
                "        <RJ_title>Visual Studio 7: A Comprehensive Guide</RJ_title>\n" +
                "        <RJ_genre>Computer</RJ_genre>\n" +
                "        <RJ_price>49.95</RJ_price>\n" +
                "        <RJ_publish_date>2001-04-16</RJ_publish_date>\n" +
                "        <RJ_description>Microsoft Visual Studio 7 is explored in depth,\n" +
                "            looking at how Visual Basic, Visual C++, C#, and ASP+ are\n" +
                "            integrated into a comprehensive development\n" +
                "            environment.</RJ_description>\n" +
                "    </RJ_book>\n" +
                "</RJ_catalog>";

        expectedStr = XML.toJSONObject(expectedStr).toString();
        try {
            //Define the function
            Function<String, String> keyTransformer= (x) -> ("RJ_"+x);
            FileReader filereader = new FileReader("src/test/resources/Catalog.xml");
            JSONObject jo = XML.toJSONObject(filereader, keyTransformer);

           System.out.println(XML.toString(jo));
           XML.toStream(jo);

            String actualStr = jo.toString();
            //System.out.println(actualStr);
            assertEquals(expectedStr, actualStr);

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
    }

    @Test
    public void testReplaceKey2()
    {
        String expectedStr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<SWE262_name>\n" +
                "    <SWE262_email>rajihul@gmail.com</SWE262_email>\n" +
                "</SWE262_name>";
        expectedStr = XML.toJSONObject(expectedStr).toString();

        try {
            //Define the function
            Function<String, String> keyTransformer= (x) -> ("SWE262_"+x);
            FileReader filereader = new FileReader("src/test/resources/TransformerTest.xml");
            JSONObject jo = XML.toJSONObject(filereader, keyTransformer);

            String actualStr = jo.toString();
            //System.out.println(actualStr);
            assertEquals(expectedStr, actualStr);

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }

    }

    @Test
    public void testStream()
    {
        String expectedStr = "<SWE262_email>rajihul@gmail.com</SWE262_email>";

        String Str = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<SWE262_name>\n" +
                "    <SWE262_email>rajihul@gmail.com</SWE262_email>\n" +
                "</SWE262_name>";

        //expectedStr = XML.toJSONObject(expectedStr).toString();
        String actualStr = "";

        try {
            //Define the function
            //Function<String, String> keyTransformer= (x) -> ("SWE262_"+x);
            FileReader filereader = new FileReader("src/test/resources/TransformerTest.xml");
            JSONObject jo = XML.toJSONObject(Str);

            List<String> trstr = XML.toStream(jo)
                   // .forEach(x -> x.replace("SWE262","RJ"))
                    .filter(x->x.contains("SWE262_email"))
                    .collect(Collectors.toList());

            for(String i:trstr)
                actualStr +=  i;

            //System.out.println(actualStr);
            //System.out.println(actualStr);
            assertEquals(expectedStr, actualStr);

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
    }
    @Test
    public void testStream2()
    {
        String expectedStr = "<SWE262_name></SWE262_name>";

        String Str = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<SWE262_name>\n" +
                "    <SWE262_email>rajihul@gmail.com</SWE262_email>\n" +
                "</SWE262_name>";

        //expectedStr = XML.toJSONObject(expectedStr).toString();
        String actualStr = "";

        try {
            //Define the function
            //Function<String, String> keyTransformer= (x) -> ("SWE262_"+x);
            FileReader filereader = new FileReader("src/test/resources/TransformerTest.xml");
            JSONObject jo = XML.toJSONObject(Str);

            List<String> trstr = XML.toStream(jo)
                    // .forEach(x -> x.replace("SWE262","RJ"))
                    .filter(x->!x.contains("SWE262_email"))
                    .collect(Collectors.toList());

            for(String i:trstr)
                actualStr +=  i;

            System.out.println(actualStr);
            //System.out.println(actualStr);
            assertEquals(expectedStr, actualStr);

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
    }

    @Test
    public void async()
    {
        String expectedStr = "";
        //Generate output.txt
        try
        {
            FileReader filereader = new FileReader("src/test/resources/Catalog.xml");
            FileWriter filewriter = new FileWriter("output.json");
            XML.toJSONObject(filereader,  (JSONObject joo) -> joo.write(filewriter), true);
            //XML.toJSONObject();
            expectedStr = XML.toJSONObject(filereader).toString();


            //Give thread adequate time to finish
            try{
                Thread.sleep(1000);

            }catch(InterruptedException e)
            {
                e.printStackTrace();
            }

            //filewriter.flush();
            filewriter.close();
        }catch(IOException e)
        {
            e.printStackTrace();
        }

        //Compare the output.txt to the Catalog.xml JSON
        try
        {
            FileReader filereader = new FileReader("output.json");
            JSONObject actualJSON = XML.toJSONObject(filereader);
            assertEquals(expectedStr, actualJSON.toString());

        }catch(IOException e)
        {
            e.printStackTrace();
        }


    }

}
