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
    public void testMethodOne() {

        System.out.println("Rahul Hello World");

        try {
            FileReader filereader = new FileReader("src/test/resources/file.xml");
            JSONObject jo = XML.toJSONObject(filereader, new JSONPointer("/clinical_study/required_header/1"));

            System.out.println(jo);

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
        //String xmlStr = "{ \"this is\": \"not xml\"}";
        //JSONObject jsonObject = XML.toJSONObject(xmlStr);
        //assertTrue("xml string should be empty", jsonObject.isEmpty());
    }

    @Test
    public void testMethodTwo()
    {
        System.out.println("\nIn Test Method 2");

        try {
            FileReader filereader = new FileReader("src/test/resources/Catalog.xml");
            JSONObject jo = XML.toJSONObject(filereader, new JSONPointer("/catalog/book"), XML.toJSONObject("<tname>rahul jain</tname>"));

            System.out.println(jo);

            System.out.println("Testing out JSON to XML Conversion");

            System.out.println(XML.toString(jo));

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }

    }
}
