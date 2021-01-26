package org.json.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

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

}
