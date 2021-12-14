package com.qa.restAssured.jsonAssert;

import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.comparator.CustomComparator;
import org.skyscreamer.jsonassert.comparator.JSONComparator;
import org.testng.annotations.Test;

public class IgnoreFieldsFromSimpleJsonObjects {

    @Test
    public void ignoreJsonFieldsForComparison(){
        String s1 = """
                {
                  "id": 1,
                  "first_name": "Deepak",
                  "last_name": "Rai",
                  "married": false,
                  "salary": 123.45
                }""";

        String s2 = """
                {
                  "id": 1,
                  "first_name": "Deepak",
                  "last_name": "Rai",
                  "married": false,
                  "salary": 125.45
                }""";

        JSONComparator comparator= new CustomComparator(JSONCompareMode.LENIENT, new Customization("salary",(o1,o2)->true));
        JSONAssert.assertEquals(s1,s2,comparator);
    }

    @Test
    public void ignoreJsonFieldsInNestedJsonJsonObjectForComparison(){
        String s1 = """
                {
                  "id": 1,
                  "first_name": "Amod",
                  "last_name": "Mahajan",
                  "married": false,
                  "salary": 123.45,
                  "address":{
                    "permanent" : "KA",
                    "city": "Bengaluru"
                  }
                }""";

        String s2 = """
                {
                  "id": 1,
                  "first_name": "Amod",
                  "last_name": "Mahajan",
                  "married": false,
                  "salary": 123.45,
                  "address":{
                    "permanent" : "KA",
                    "city": "Katihar"
                  }
                }""";

        JSONComparator comparator= new CustomComparator(JSONCompareMode.LENIENT, new Customization("address.city",(o1,o2)->true));
        JSONAssert.assertEquals(s1,s2,comparator);
    }

    @Test
    public void ignoreJsonFieldsInJsonArrayForComparison(){
        String s1 = """
                [
                  {
                    "id": 1,
                    "first_name": "Deepak",
                    "last_name": "Rai",
                    "married": false,
                    "salary": 123.45
                  },
                  {
                    "id": 2,
                    "first_name": "Animesh",
                    "last_name": "Prashant",
                    "married": true,
                    "salary": 223.45
                  }
                ]""";

        String s2 = """
                [
                  {
                    "id": 10,
                    "first_name": "Deepak",
                    "last_name": "Rai",
                    "married": false,
                    "salary": 123.45
                  },
                  {
                    "id": 2,
                    "first_name": "Animesh",
                    "last_name": "Prashant",
                    "married": true,
                    "salary": 223.45
                  }
                ]""";

        JSONComparator comparator= new CustomComparator(JSONCompareMode.STRICT, new Customization("[0].id",(o1,o2)->true));
        JSONAssert.assertEquals(s1,s2,comparator);
    }

    @Test
    public void ignoreJsonFieldsInNestedJsonArrayForComparison(){
        String s1 = """
                [
                   {
                     "id": 1,
                     "first_name": "Amod",
                     "last_name": "Mahajan",
                     "married": false,
                     "salary": 123.45,
                     "mob": [
                       {
                         "type": "personal",
                         "number": "1234566"
                       },
                       {
                         "type": "business",
                         "number": "987654321"
                       }
                     ]
                   },
                   {
                     "id": 2,
                     "first_name": "Animesh",
                     "last_name": "Prashant",
                     "married": true,
                     "salary": 223.45,
                     "mob": [
                       {
                         "type": "personal",
                         "number": "1234566"
                       },
                       {
                         "type": "business",
                         "number": "987654321"
                       }
                     ]
                   }
                 ]""";

        String s2 = """
               [
                 {
                   "id": 1,
                   "first_name": "Amod",
                   "last_name": "Mahajan",
                   "married": false,
                   "salary": 123.45,
                   "mob": [
                     {
                       "type": "personal",
                       "number": "1234566"
                     },
                     {
                       "type": "business",
                       "number": "34545646"
                     }
                   ]
                 },
                 {
                   "id": 2,
                   "first_name": "Animesh",
                   "last_name": "Prashant",
                   "married": true,
                   "salary": 223.45,
                   "mob": [
                     {
                       "type": "personal",
                       "number": "1234566"
                     },
                     {
                       "type": "business",
                       "number": "987654321"
                     }
                   ]
                 }
               ]""";

        System.out.println(s1);
        System.out.println(s2);
        JSONComparator comparator= new CustomComparator(JSONCompareMode.STRICT, new Customization("[0].mob[1].number",(o1,o2)->true));
        JSONAssert.assertEquals(s1,s2,comparator);
    }
}
