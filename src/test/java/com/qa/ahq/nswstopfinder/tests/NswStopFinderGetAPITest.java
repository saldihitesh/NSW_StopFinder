package com.qa.ahq.nswstopfinder.tests;

import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import com.qa.ahq.nswstopfinder.common.RestUtil;
import com.qa.ahq.nswstopfinder.common.TestUtil;

public class NswStopFinderGetAPITest {

	//declared Response and JsonPath objects.
    private Response res = null; //Response object
    private JsonPath jp = null; //JsonPath object
    private String QueryPath = null;
    private Properties propFile = null;
    
    /*
    ** we should do setup operations, get JSON response from the API and put it into JsonPath object
    ** Then we will do query and manipulations with JsonPath class’s methods.
    */
    @BeforeMethod
    public void setUp () {
        //Test Setup
        RestUtil.setBaseURI("https://www.transportnsw.info/web/XML_STOPFINDER_REQUEST"); //Setup Base URI
        propFile =TestUtil.readPropertyFile();
        try {
        	 QueryPath=TestUtil.readExcelForQueryParameters(propFile.getProperty("filepath"), propFile.getProperty("filename"), propFile.getProperty("sheetname"));
		} catch (IOException e) {
			e.printStackTrace();
		}
        RestUtil.createSearchQueryPath("web", "XML_STOPFINDER_REQUEST", QueryPath); 
        res = RestUtil.getResponse(); //Get response
        jp = RestUtil.getJsonPath(res); //Get JsonPath
    }
 
    @Test(priority=1)
    public void statusCodeTest() {
    	TestUtil.checkStatusIs200(res);
    }
    @Test(priority=2)
    public void stopNameTest(){
    	TestUtil.getLocationList(jp);
    }
    @Test(priority=3)
    public void multipleModesTest(){
    	TestUtil.multipleModesTest(jp);
    }
    
    @AfterTest
    public void afterTest (){
        //Reset Value
        RestUtil.resetBaseURI();
    }  
    
}
