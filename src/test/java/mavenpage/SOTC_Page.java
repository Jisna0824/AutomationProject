package mavenpage;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Set;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SOTC_Page
{
	WebDriver driver;
	
	By logo=By.xpath("//*[@id=\"top\"]/div/div/a");
	By Mouseover=By.xpath("//*[@id=\"india-holidays\"]/a");
	By kerala=By.xpath("//*[@id=\"india-holidays\"]/ul/div[1]/li[1]/ul/li[4]/a/span");
	By window=By.xpath("//*[@id=\"BodyContainer\"]/section[5]/div/div/div/div[1]/div/div[3]/div/a/picture/img");
	By add=By.xpath("//*[@id=\"extra-panel\"]/a/span[1]");
	By aboutUs=By.xpath("//*[@id=\"extra-panel\"]/ul/div/li/ul/li[1]/a");
	
	By login=By.xpath("//*[@id=\"LoginLogoutToggel\"]");
	By loginbutton=By.xpath("//*[@id=\"mainLogIn\"]");
	By user=By.xpath("//*[@id=\"loginId\"]");
	By pass=By.xpath("//*[@id=\"existloginPass\"]");
	By loginnew=By.xpath("//*[@id=\"loginButton\"]");
	
	By flight=By.xpath("//*[@id=\"flights\"]");
	By cityfrom=By.xpath("//*[@id=\"input-search-from\"]");
	By cityto=By.xpath("//*[@id=\"input-search-to\"]");
	By depDate=By.xpath("//*[@id=\"flight-search\"]/div[3]/div[1]");
    By search=By.xpath("//*[@id=\"search-button\"]");
  
	By viewAccount=By.xpath("//*[@id=\"viewAccount\"]");
	By close=By.xpath("//*[@id=\"btj-bubble\"]");
	By edit=By.xpath("//*[@id=\"profile-body\"]/div/div[3]/div[2]/div[1]/span[3]");
	By upload=By.xpath("//*[@id=\"editProfile\"]/div/div/div[2]/form/div[1]/div[1]/div[1]/div");
	By save=By.xpath("//*[@id=\"myprofile_save\"]");
	
	public SOTC_Page(WebDriver driver)
	{
		this.driver=driver;
	}
	
public void pop() throws InterruptedException
{
	 System.out.println("please close the Ad within 7 sec. Timer Started");
		 Thread.sleep(7000);
	 System.out.println("Timer stoped");
		
}
	
	//Title verification//
	
	public void titleverify()
	{
    String actualtitle=driver.getTitle();
    System.out.println(actualtitle);
    String expectedtitle="SOTC";
    if(actualtitle.equals(expectedtitle))
      {
	    System.out.println("Title pass");	
      }
    else
      {
	    System.out.println("Title fail");	
      }
	}
    // Content Verification//
    public void content() {
    String abc=driver.getPageSource();
    String s="sotc";
      if(abc.contains(s))
       {
         System.out.println("Content pass");	
       }
      else
       {
         System.out.println("Content fail");	
       }
	}
      //logo display//
      
     public void logodisplay()
     {
      WebElement li = driver.findElement(logo);
      boolean l=li.isDisplayed();
		if(l)
		{
			System.out.println("Logo is Displayed");
		}
		else
		{
			System.out.println("Logo not displayed");
		}
	
     }
     
    
	
	//mousehover//
     public void mouseover() {
     Actions act = new Actions(driver);
     WebElement ai = driver.findElement(Mouseover);
     act.moveToElement(ai);
     act.perform();
     driver.findElement(kerala).click();
     driver.navigate().back();
     }
    
     //scrollDown//
     
     public void scrolldown()
     {
     JavascriptExecutor js=(JavascriptExecutor) driver;
     js.executeScript("window.scrollBy(0,5000)","");
     }
     
    //window handle//
    
    public void windowhandle()
    {
   	String parentWindow=driver.getWindowHandle();
		driver.findElement(window).click();
		
		Set<String>allwindowhandles=driver.getWindowHandles();
		for(String handle:allwindowhandles)
		{
			System.out.println(handle);
			if(!handle.equalsIgnoreCase(parentWindow))
			{
				 driver.switchTo().window(handle);
				 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
				 Actions a = new Actions(driver);
			     WebElement ai = driver.findElement(add);
			     a.moveToElement(ai);
			     a.perform();
			     driver.findElement(aboutUs).click();
				 driver.close();
			}
			driver.switchTo().window(parentWindow);
		}
    }
     
     //login//
     
     public void login() throws IOException
	   {
    	 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    	 driver.findElement(login).click();
    	 driver.findElement(loginbutton).click();
		   File f=new File("D:\\sotc.xlsx");
		   		FileInputStream fi=new FileInputStream(f);
		   		XSSFWorkbook wb= new XSSFWorkbook(fi);
		   		XSSFSheet sh=wb.getSheet("sheet1");
		   		System.out.println(sh.getLastRowNum());
		   		
		   		for(int i=1;i<=sh.getLastRowNum();i++)
		   		{
		   			
					String username=sh.getRow(i).getCell(0).getStringCellValue();
		   			System.out.println("username=" +username);
		   			String paswd=sh.getRow(i).getCell(1).getStringCellValue();
		   			System.out.println("password=" +paswd);
		   			driver.findElement(user).clear();
		   			driver.findElement(user).sendKeys(username);
		   			driver.findElement(pass).clear();
		   			driver.findElement(pass).sendKeys(paswd);
		   			driver.findElement(loginnew).click();		   			
		   		}
		   	 System.out.println("Login Success");
	   }
     
     //screenshot//
     
     public void screenshots() throws Exception 
     {
 		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
 		FileHandler.copy(src,new File("D://sotcScreenshot.png"));
 		 System.out.println("Screenshot is Taken");
     }
      
     //Date Picker//
     
     public void datepicker() throws InterruptedException
       {   
    	 driver.findElement(flight).click();
    	 driver.findElement(cityfrom).sendKeys("cok");
    	  Thread.sleep(2000);
    	 driver.findElement(By.xpath("//*[@id=\"autoSuggestion\"]/li/a")).click();
    	 driver.findElement(cityto).sendKeys("del");
    	 driver.findElement(depDate).click();  	
 
    	 WebElement month = driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div[1]/div/div/span[1]"));
		 String month1=month.getText();	
		 System.out.println(month1);
		 List<WebElement> alldates = driver.findElements(By.xpath("//*[@id=\"ui-datepicker-div\"]/div[1]/table/tbody/tr/td/a"));
			 for(WebElement dateelement:alldates)
			 {
				 String depdate=dateelement.getText();
				 System.out.println(depdate);
				 if(depdate.equals("16"))
				 {
					 dateelement.click();
					break;
				 }
			 }
			 
    	System.out.println("***********************");
    	
    	 List<WebElement> alldates1 = driver.findElements(By.xpath("//*[@id=\"ui-datepicker-div\"]/div[1]/table/tbody/tr/td/a"));
		 for(WebElement dateelement:alldates1)
		 {
			 String depdate=dateelement.getText();
			 System.out.println(depdate);
			 if(depdate.equals("18"))
			 {
				 dateelement.click();
				break;
			 }
		 }
	   
    	driver.findElement(search).click(); 
 
     }
    	   
     //Fileupload//
     
     public void upload () throws Exception
 		{
    	 Thread.sleep(50000);
 		driver.findElement(login).click();
 		driver.findElement(viewAccount).click(); 
 		 System.out.println("Chatbot is open You have 10 sec to close. Time Started");
		 Thread.sleep(20000);
	     System.out.println("Chatbot is Closed");
 		driver.findElement(edit).click();
 		Thread.sleep(20000);
 		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
 				
 		try {
 		 WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"editProfile\"]/div/div/div[2]/div/div[1]/div")));
 		 button.click();
 	    } catch (ElementNotInteractableException e) {
 	      System.out.println("Element not interactable: " + e.getMessage());
 	    }
 		
 		fileUpload("C:\\Users\\Administrator\\Desktop\\sotcHome.png");
 		System.out.println("photo is uploaded");
 		driver.findElement(save).click();
 		driver.quit();
 		}
      public void fileUpload(String f) throws AWTException
 	{
 		StringSelection str=new StringSelection(f);
 		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);
 		Robot robot =new Robot();
 		robot.delay(3000);
 		
 		robot.keyPress(KeyEvent.VK_CONTROL);
 		robot.keyPress(KeyEvent.VK_V);
 		robot.keyRelease(KeyEvent.VK_V);
 		robot.keyRelease(KeyEvent.VK_CONTROL);
 		robot.keyPress(KeyEvent.VK_ENTER);
 		robot.keyRelease(KeyEvent.VK_ENTER);
 		
 	}
    
}
    
