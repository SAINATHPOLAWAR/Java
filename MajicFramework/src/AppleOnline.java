import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;


public class AppleOnline {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		WebDriver driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://appleonlineuk.mpxltd.co.uk/");
        //driver.manage().window().maximize();
        System.out.println(driver.getTitle());
        WebElement element = driver.findElement(By.name("ctl00$ContentPlaceHolder1$txtIMEIVal"));
        element.sendKeys("352084074114586");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.id("ctl00_ContentPlaceHolder1_imeiValidate")).click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        //Thread.sleep(30000);
        //driver.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_rptModelColor_ctl01_RdBxMasterModelColor']")).click();
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
       
        //driver.findElement(By.xpath("//li[@id='ctl00_ContentPlaceHolder1_rptModelSize_ctl00_LiSizeId']")).click();
       driver.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_rptModelSize_ctl00_a_SizeId']")).click();
       
       /*
        driver.findElement(By.id("ctl00_ContentPlaceHolder1_repCustomerTypeSelection_ctl00_yes")).click();
        driver.findElement(By.id("ctl00_ContentPlaceHolder1_repCustomerTypeSelection_ctl01_yes")).click();
        driver.findElement(By.id("ctl00_ContentPlaceHolder1_repCustomerTypeSelection_ctl02_yes")).click();
        driver.findElement(By.id("ctl00_ContentPlaceHolder1_repCustomerTypeSelection_ctl03_yes")).click();
        driver.findElement(By.id("ctl00_ContentPlaceHolder1_repCustomerTypeSelection_ctl04_yes")).click();
        driver.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_btnSubmit']")).click();
        //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);*/
		

	}

}
