package DriverScript;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import GenericLib.GenericLibrary;

public class DriverSCR {
	
	public static WebDriver driver;
	public static String vBrowser,vProjName,vProjectUrl,vModulesFile,vScenarioFile,vObjectFile,vModName,vTCName,vKeyWords;

	/**
	 * @param args
	 * @throws Throwable 
	 */
	
	public static void main(String[] args) throws Throwable {
		// TODO Auto-generated method stub
		
		//System.out.println(System.getProperty("user.dir"));
		
		Xls_Reader vProjExc = new Xls_Reader(System.getProperty("user.dir")+"\\src\\DriverFiles\\ProjectDriver.xlsx");
		Xls_Reader vTCDrExc = new Xls_Reader(System.getProperty("user.dir")+"\\src\\DriverFiles\\vTigerModules.xlsx");
		Xls_Reader vScenExc = new Xls_Reader(System.getProperty("user.dir")+"\\src\\Scenario\\vTigerScenario.xlsx");
		int vProjRowCount = vProjExc.getRowCount("Projects");
		//System.out.println(vProjRowCount);
		
		//System.out.println(vProjRowCount);
		
		driver = new FirefoxDriver ();
		
		for (int i=2; i<=vProjRowCount; i++){
			
			String vProjRun = vProjExc.getCellData("Projects", "Run", i);
			//System.out.println(vProjRun);
			if (vProjRun.equalsIgnoreCase("ON"))
				
			{
				vBrowser = vProjExc.getCellData("Projects", "Browser", i);
				 vProjName = vProjExc.getCellData("Projects", "ProjectName", i);
				 //System.out.println(vProjName);
				//System.out.println(vProjName);
				vProjectUrl = vProjExc.getCellData("Projects", "ProjectUrl", i);
				vModulesFile = vProjExc.getCellData("Projects", "ModulesFile", i);
				vScenarioFile = vProjExc.getCellData("Projects", "ScenarioFile", i);
				vObjectFile = vProjExc.getCellData("Projects", "ObjectFile", i);
				 
				System.out.println("Project Browser : " + vBrowser );
				System.out.println("ProjectName : " + vProjName);
				System.out.println("ProjectUrl : " + vProjectUrl);
				System.out.println("ModulesFile : " + vModulesFile);
				System.out.println("ScenarioFile : " + vScenarioFile);
				System.out.println("ObjectFile : " + vObjectFile);
				
				int ModRowCount = vProjExc.getRowCount(vProjName);
				//(vProjName)
				System.out.println(ModRowCount);
				for (int j = 2; j<=ModRowCount; j++){
				String vModRun = vProjExc.getCellData(vProjName, "Run", j);
				if (vModRun.equalsIgnoreCase("ON")){
					
					
					
				vModName = 	vProjExc.getCellData(vProjName, "Modules", j);
				System.out.println(vModName);
				
				int vTCRowCount = vTCDrExc.getRowCount(vModName);
				for (int k=2; k<=vTCRowCount; k++){
				String vTCRun = vTCDrExc.getCellData(vModName, "Run", k);
				if (vTCRun.equalsIgnoreCase("ON")){
					vTCName = vTCDrExc.getCellData(vModName, "TCName", k);
					System.out.println(vTCName);
							
						
				int vKeyWCount = vScenExc.getRowCount(vModName);
				int flag =0;
				int rownum=0;
				for (int m=2; m<=vKeyWCount; m++){
					String vText = vScenExc.getCellData(vModName, "Keywords", m).trim();
					if (vTCName.equals(vText))
					{
						String vTCDesc = vScenExc.getCellData(vModName, "Keywords", m-1);
						flag=1;
						rownum = m;
					}
					if ((flag==1)&&(m>rownum))
					{
						String vKeyW = vScenExc.getCellData(vModName, "Keywords", m);
						if (vKeyW.contains("//"))
						{
							break;
						}
						else
						{
							vKeyWords = vScenExc.getCellData(vModName, "Keywords", m);
							System.out.println(vKeyWords);
							GenericLibrary gb = new GenericLibrary ();
							gb.KeywordDriver(vKeyWords,vScenExc,m);
									}
						
					}
						}
							}
						}
					}
				
				}
			}
	
		}
	}

}
