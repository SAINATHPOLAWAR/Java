package GenericLib;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import DriverScript.DriverSCR;
import DriverScript.DriverSCR;
import DriverScript.Xls_Reader;


public class GenericLibrary {
	DriverSCR ab = new DriverSCR();
	String vObjString,vVal;
	List<WebElement> elems;
	public void KeywordDriver(String vKeyWor,Xls_Reader vScenExc,int m) throws Throwable
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\ObjectRepository\\ObjectRepository.Properties");
		prop.load(fis);
		switch(vKeyWor)
		{
		case"Fn_LaunchApp":
			Fn_LaunchApp();
			break;
			
	
		case"Fn_verifyTitle":
			String vExp =vScenExc.getCellData(DriverSCR.vModName, "Param1", m) ;
			Fn_verifyTitle(vExp);
			break;
			
		case"Fn_verifyObjectExist":
			vObjString =prop.getProperty(vScenExc.getCellData(DriverSCR.vModName, "Object1", m));
			System.out.println(vObjString);
			elems = ExtractLocator(vObjString);
			Fn_verifyObjectExist(elems);
			break;
			
		case "Fn_SetVal":
			vObjString=prop.getProperty(vScenExc.getCellData(DriverSCR.vModName, "Object1", m));
			elems=ExtractLocator(vObjString);	
			vVal=vScenExc.getCellData(DriverSCR.vModName, "Param1", m).trim();
			Fn_SetVal(elems,vVal);
			break;
		/*case "Fn_SelectVal":
			vObjString = prop.getProperty(vScenExc.getCellData(DriverSCR.vModName, "Object1", m));
			elems = ExtractLocator(vObjString);
			vVal=vScenExc.getCellData(DriverSCR.vModName, "Param1", m).trim();
			Fn_SetVal(elems,vVal);
			break;*/
		case "Fn_Click":
			vObjString = prop.getProperty(vScenExc.getCellData(DriverSCR.vModName, "Object1", m));
			elems = ExtractLocator(vObjString);
			Fn_Click(elems);
			break;
		
		}
	}
	
	public List<WebElement> ExtractLocator(String Obj)
	{
		List<WebElement> elem =null;
		if (Obj.length()>0)
		{
			String[] Str =Obj.split("@@@");
			switch(Str[0])
			{
				case "xpath":
					elem =DriverSCR.driver.findElements(By.xpath(Str[1]));
					break;
				case "name":
					elem =DriverSCR.driver.findElements(By.name(Str[1]));
					break;
				case "linkText":
					elem = DriverSCR.driver.findElements(By.linkText(Str[1]));
					break;
					
				case "partialLinkText":
					elem = DriverSCR.driver.findElements(By.partialLinkText(Str[1]));
					break;
				case "className":
					elem = DriverSCR.driver.findElements(By.className(Str[1]));
					break;
					
				case "cssSelector":
					elem = DriverSCR.driver.findElements(By.cssSelector(Str[1]));
					break;
					default:
						System.out.println("Object Not Found");
			}
		}
		return elem;
		
	}
	public void Fn_Click(List<WebElement> elems)
	{
		if(elems.size()>0)
		{
			elems.get(0).click();
			System.out.println("PASSED");
		}
		else
		{
			System.out.println("Failed");
		}
	}
	
	public void Fn_SelectVal(List<WebElement> elems,String vVal){
	if (elems.size()==1)
	{
		elems.get(0).sendKeys(vVal);
		String vActVal=elems.get(0).getAttribute("value");
		if (vActVal.equals(vVal))
		{
			System.out.println("PASEED");
		}
		else
		{
			System.out.println("FAILED");
		}
	}
	else 
	{
		System.out.println("Object Not Found");
	}
}

	
	
	public void Fn_LaunchApp()
	{
		
		DriverSCR.driver.get(DriverSCR.vProjectUrl);
	}
	
	
	public void Fn_verifyTitle(String ExpTitle)
	{
		String vAct =DriverSCR.driver.getTitle().trim();
		if (vAct.equals(ExpTitle))
				{
			     System.out.println("Pass");
				}
		else
		{
			System.out.println("Fail");
		}
		
	}
	
	public void Fn_verifyObjectExist(List<WebElement> elems)
	{
		if(elems.size()==1)
		{
			System.out.println("Pass");
		}
		else
		{
			System.out.println("Fail");
		}
	}
	
	public void Fn_SetVal(List<WebElement> elems,String vVal)
	{
		if(elems.size()==1)
		{
			elems.get(0).clear();
			elems.get(0).sendKeys(vVal);
			String vActVal=elems.get(0).getAttribute("value");
			if(vActVal.equals(vVal))
			{
				System.out.println("PASSED");
				//DriverSCRdriver.fgInsertResult(Script.ResFilePath, "Fn_SetVal", vVal+" Value should be entered", vVal+" get entered", "PASSED");
			}
			else
			{
				System.out.println("FAILED");
				//Script.hr.fgInsertResult(Script.ResFilePath, "Fn_SetVal", vVal+" Value should be entered", vVal+ " not entered", "FAILED");
			}
		}
		else
		{
			System.out.println("Object not found");
			//Script.hr.fgInsertResult(Script.ResFilePath, "Fn_SetVal", vVal+" Value should be entered", "Object not found", "FAILED");
		}
	}
}
