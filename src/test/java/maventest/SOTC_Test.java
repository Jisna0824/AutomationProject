package maventest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import mavenpage.SOTC_Page;

public class SOTC_Test 
{
	WebDriver driver;
    @BeforeTest
    public void setup()
    {
    driver=new ChromeDriver();
    } 
    @BeforeMethod
    public void url() 
    {
    driver.get("https://www.sotc.in/");
    }
    @Test
    public void Test() throws Exception
    {
    
    	SOTC_Page obj=new SOTC_Page(driver);
    	driver.manage().window().maximize();
    	obj.pop();
    	obj.titleverify();
     	obj.content();
    	obj.logodisplay();
    	obj.login();
    	obj.screenshots();
    	obj.scrolldown();
    	obj.mouseover();
    	obj.windowhandle();
        obj.datepicker();
    	obj.upload();	
    }
    
}
