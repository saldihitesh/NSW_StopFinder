package com.qa.ahq.nswstopfinder.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.testng.Assert;

import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestUtil {

	
	public static String QueryString="?";
	 /*
    Verify the http response status returned. Check Status Code is 200?
    We can use Rest Assured library's response's getStatusCode method
    */
    public static void checkStatusIs200 (Response res) {
    	System.out.println("****************************");
    	System.out.println("*******Test Output**********");
    	System.out.println("****************************");
    	System.out.println("Status Code:"+res.getStatusCode());
    	
        Assert.assertEquals(200, res.getStatusCode());
    }
    
    public static void getLocationList (JsonPath jp) {
    	ArrayList<String> loc = jp.get("locations.name");
    	String actual_loc=null;
    	for (int j = 0; j < loc.size(); j++) {
            if(loc.get(j).equals("Wynyard Station, Sydney")){
            	actual_loc=loc.get(j);
            }
        }
    	System.out.println("****************************");
    	System.out.println("Actual Location:"+actual_loc);
    	Assert.assertEquals(actual_loc,"Wynyard Station, Sydney" );
    }    
    public static void multipleModesTest (JsonPath jp) {
    	System.out.println("****************************");
    	ArrayList<String> modes = jp.get("locations.modes");
    	System.out.println("Available modes:"+modes.size());
    	System.out.println("****************************");
    	Assert.assertFalse(modes.isEmpty());
    }
    
    public static String readExcelForQueryParameters(String filePath,String fileName,String sheetName) throws IOException{
    	
        //Create an object of File class to open xlsx file
        File file =    new File(filePath+"//"+fileName);
        //Create an object of FileInputStream class to read excel file
        FileInputStream inputStream = new FileInputStream(file);
        Workbook queryParameterbook = null;
        //Find the file extension by splitting file name in substring  and getting only extension name
        String fileExtensionName = fileName.substring(fileName.indexOf("."));
        //Check condition if the file is xlsx file
        if(fileExtensionName.equals(".xlsx")){
        //If it is xlsx file then create object of XSSFWorkbook class
        queryParameterbook = new XSSFWorkbook(inputStream);
        }
        //Check condition if the file is xls file
        else if(fileExtensionName.equals(".xls")){
            //If it is xls file then create object of XSSFWorkbook class
        	queryParameterbook = new HSSFWorkbook(inputStream);	
        }
        //Read sheet inside the workbook by its name
        Sheet stopFinderAPISheet = queryParameterbook.getSheet(sheetName);
        //Find number of rows in excel file
        int rowCount = stopFinderAPISheet.getLastRowNum()-stopFinderAPISheet.getFirstRowNum();
        //Create a loop over all the rows of excel file to read it
        for (int i = 1; i <= rowCount; i++) {
        	//System.out.println("row"+i);
        	if(i!=1){
        		QueryString=QueryString+"&";
        	}
            Row row = stopFinderAPISheet.getRow(i);	
            //Create a loop to print cell values in a row
            for (int j = 0; j < row.getLastCellNum(); j++) {

            	QueryString=QueryString+(row.getCell(j).getStringCellValue());
            	if(j==0)
            	QueryString=QueryString+"=";	
            }   
        }
        return QueryString;    
    }
    public static Properties readPropertyFile(){
    	
        Properties prop = new Properties();
		
		FileInputStream fs =null;
		try {
			fs = new FileInputStream(".//src//test//resources//TestData//config.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		
		try {
			prop.load(fs);
		} catch (IOException e) {
			e.printStackTrace();
		}
		 return prop;
    }
   
    
   
}
