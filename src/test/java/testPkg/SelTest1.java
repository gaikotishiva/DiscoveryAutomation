package testPkg;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.codehaus.plexus.util.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.Test;


/** 
 * @author Siva Gaikoti
 * @category Selenium Automation
 * @version 1.0
 * Description: Automation of Discover website and adding the videos to favorite list
 * Conventions: vyml = Videos you may like ; wl = Watch Later
 *
 **/

public class SelTest1 {
	
	public WebDriver driver = null;
	public File src = null;
	public String upath;
	public static String URL = "https://www.discovery.com";
	public String parentWindow = null;
	public Actions actions = null;
	public String childWindow = null;
	public String vyml_sectionName = null;
	public String wl_sectionName = null;
	public String vyml_str = "VIDEOS YOU MIGHT LIKE";
	public String wl_str = "WATCH LATER";
	
	//Home Page
	
	public ArrayList<String> vyml_list = new ArrayList<String> ();
	public ArrayList<String>  wl_list = new ArrayList<String> ();
	
	@FindBy(xpath="//div[contains(@id,'google_ads_iframe')]")
	public WebElement googleAds_elem;
	
	@FindBy(xpath="//span[@class='o-Header__a-NavLink']")
	public WebElement HomePg_More_elem;
	
	@FindBy(xpath="//a[contains(text(),'My Videos')]")
	public WebElement HomePg_MyVideos_elem;
	
	//My Videos Page
	
	@FindBy(xpath="//section[1]/h2[1]/div[1]")
	public WebElement MyVideosPg_VymlSection_elem;
	
	@FindBy(xpath="//div[@class='content carousel__content thumbnailCarousel__content'][1]/div[@class='carousel-tile-wrapper carousel__tileWrapper']")
	public List<WebElement> MyVideosPg_VymlVideos_elems;
	
	@FindBy(xpath="//section[1]//div[@class='content carousel__content thumbnailCarousel__content'][1]/div[@class='carousel-tile-wrapper carousel__tileWrapper'][1]")
	public WebElement MyVideosPg_VymlVideo1_elem;
	
	@FindBy(xpath="//section[1]//div[@class='content carousel__content thumbnailCarousel__content'][1]/div[@class='carousel-tile-wrapper carousel__tileWrapper'][1]//h4[@class='overlayInner__title']")
	public WebElement MyVideosPg_VymlVideo1_Title_elem;
	
	@FindBy(xpath="//section[1]//div[@class='content carousel__content thumbnailCarousel__content'][1]/div[@class='carousel-tile-wrapper carousel__tileWrapper'][1]//div[@class='overlayInner__overlayDescription description']")
	public WebElement MyVideosPg_VymlVideo1_Description_elem;
	
	@FindBy(xpath="//section[1]//div[@class='content carousel__content thumbnailCarousel__content'][1]/div[@class='carousel-tile-wrapper carousel__tileWrapper'][1]//i[@class='flipIconCore__icon icon-plus ']")
	public WebElement MyVideosPg_VymlVideo1_add_elem;
		
	@FindBy(xpath="//section[1]//div[@class='content carousel__content thumbnailCarousel__content'][1]/div[@class='carousel-tile-wrapper carousel__tileWrapper'][2]")
	public WebElement MyVideosPg_VymlVideo2_elem;
	
	@FindBy(xpath="//section[1]//div[@class='content carousel__content thumbnailCarousel__content'][1]/div[@class='carousel-tile-wrapper carousel__tileWrapper'][2]//h4[@class='overlayInner__title']")
	public WebElement MyVideosPg_VymlVideo2_Title_elem;
	
	@FindBy(xpath="//section[1]//div[@class='content carousel__content thumbnailCarousel__content'][1]/div[@class='carousel-tile-wrapper carousel__tileWrapper'][2]//div[@class='overlayInner__overlayDescription description']")
	public WebElement MyVideosPg_VymlVideo2_Description_elem;
	
	@FindBy(xpath="//section[1]//div[@class='content carousel__content thumbnailCarousel__content'][1]/div[@class='carousel-tile-wrapper carousel__tileWrapper'][2]//i[@class='flipIconCore__icon icon-plus ']")
	public WebElement MyVideosPg_VymlVideo2_add_elem;
	
	@FindBy(xpath="//section[3]/div[1]/h2[1]")
	public WebElement MyVideosPg_WlSection_elem;
	
	@FindBy(xpath="//div[@class='localStorageCarousel__wrapper']//div[@class='content carousel__content thumbnailCarousel__content'][1]/div[@class='carousel-tile-wrapper carousel__tileWrapper']")
	public List<WebElement> MyVideosPg_WlVideos_elems;
	
	@FindBy(xpath="//div[@class='localStorageCarousel__wrapper']//div[@class='content carousel__content thumbnailCarousel__content'][1]/div[@class='carousel-tile-wrapper carousel__tileWrapper'][1]")
	public WebElement MyVideosPg_WlVideo1_elem;
	
	@FindBy(xpath="//div[@class='localStorageCarousel__wrapper']//div[@class='content carousel__content thumbnailCarousel__content'][1]/div[@class='carousel-tile-wrapper carousel__tileWrapper'][1]//h4[@class='overlayInner__title']")
	public WebElement MyVideosPg_WlVideo1_Title_elem;
	
	@FindBy(xpath="//div[@class='localStorageCarousel__wrapper']//div[@class='content carousel__content thumbnailCarousel__content'][1]/div[@class='carousel-tile-wrapper carousel__tileWrapper'][1]//div[@class='overlayInner__overlayDescription description']")
	public WebElement MyVideosPg_WlVideo1_Description_elem;
		
	@FindBy(xpath="//div[@class='localStorageCarousel__wrapper']//div[@class='content carousel__content thumbnailCarousel__content'][1]/div[@class='carousel-tile-wrapper carousel__tileWrapper'][2]")
	public WebElement MyVideosPg_WlVideo2_elem;
	
	@FindBy(xpath="//div[@class='localStorageCarousel__wrapper']//div[@class='content carousel__content thumbnailCarousel__content'][1]/div[@class='carousel-tile-wrapper carousel__tileWrapper'][2]//h4[@class='overlayInner__title']")
	public WebElement MyVideosPg_WlVideo2_Title_elem;
	
	@FindBy(xpath="//div[@class='localStorageCarousel__wrapper']//div[@class='content carousel__content thumbnailCarousel__content'][1]/div[@class='carousel-tile-wrapper carousel__tileWrapper'][2]//div[@class='overlayInner__overlayDescription description']")
	public WebElement MyVideosPg_WlVideo2_Description_elem;

	
	
	@Parameters("browser")
	@Test
	public void test1(@Optional("chrome") String browser) throws IOException {
		
		try {
			
			upath = System.getProperty("user.dir");
			
			if(browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", upath+"/Resources/chromedriver_v66");
				//WebDriverManager.chromedriver().setup();				
				driver = new ChromeDriver();
			}else {
				throw new Exception("Browser should be chrome or firefox but provided as "+browser);
			}
			
			PageFactory.initElements(driver, SelTest1.this);
			
			driver.manage().window().maximize();
			
			driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driver.manage().timeouts().setScriptTimeout(120, TimeUnit.SECONDS);
			
			WebDriverWait wait = new WebDriverWait(driver,90);			
			//Wait<WebDriver> fwait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(90000)).pollingEvery(Duration.ofSeconds(10000));
	
			driver.get(URL);
			// or we can use this url to move directly to videos section so that no switching is required : driver.get("https://go.discovery.com/my-videos");
			
			wait.until(ExpectedConditions.visibilityOf(googleAds_elem));
			
			parentWindow = driver.getWindowHandle();
			System.out.println("Parent window handle: "+parentWindow);
				
			actions = new Actions(driver);
			
			wait.until(ExpectedConditions.visibilityOf(HomePg_More_elem));
		
			actions.moveToElement(HomePg_More_elem).build().perform();
			
			wait.until(ExpectedConditions.visibilityOf(HomePg_MyVideos_elem));

			actions.moveToElement(HomePg_MyVideos_elem).click().build().perform();
			
			Thread.sleep(5000);
			
			Set<String> windowHandles = driver.getWindowHandles();
			
			for(String hwindow: windowHandles) {

				System.out.println("windows: "+hwindow);
				if(!(hwindow.equals(parentWindow))) {
					childWindow = hwindow;
				}
			}
			
			System.out.println("Child window handle: "+childWindow);

			driver.switchTo().window(childWindow);
			
			wait.until(ExpectedConditions.visibilityOf(googleAds_elem));
			
			wait.until(ExpectedConditions.visibilityOf(MyVideosPg_VymlSection_elem));
			
			vyml_sectionName = MyVideosPg_VymlSection_elem.getText();     
			
			System.out.println("sectionName: "+vyml_sectionName);
			
			int vyml_VideosCount = 0;	
			
			String vyml_Video1_Title = null;
			String vyml_Video1_Description = null;
			
			String vyml_Video2_Title = null;
			String vyml_Video2_Description = null;
			
			if(vyml_sectionName.equalsIgnoreCase(vyml_str)) {
				
				vyml_VideosCount = MyVideosPg_VymlVideos_elems.size();
				System.out.println("vyml_VideosCount: "+vyml_VideosCount);
				
				
				if (vyml_VideosCount>0) {
					
					wait.until(ExpectedConditions.visibilityOf(MyVideosPg_VymlVideo1_elem));
					actions.moveToElement(MyVideosPg_VymlVideo1_elem).build().perform();
					
					wait.until(ExpectedConditions.visibilityOf(MyVideosPg_VymlVideo1_Title_elem));
					vyml_Video1_Title = MyVideosPg_VymlVideo1_Title_elem.getText();
					System.out.println("vyml_Video1_Title: "+vyml_Video1_Title);
					
					wait.until(ExpectedConditions.visibilityOf(MyVideosPg_VymlVideo1_Description_elem));
					vyml_Video1_Description = MyVideosPg_VymlVideo1_Description_elem.getText();
					System.out.println("vyml_Video1_Description: "+vyml_Video1_Description);
					
					wait.until(ExpectedConditions.visibilityOf(MyVideosPg_VymlVideo1_add_elem));
					MyVideosPg_VymlVideo1_add_elem.click();
					
					wait.until(ExpectedConditions.visibilityOf(MyVideosPg_VymlVideo2_elem));
					actions.moveToElement(MyVideosPg_VymlVideo2_elem).build().perform();

					wait.until(ExpectedConditions.visibilityOf(MyVideosPg_VymlVideo2_Title_elem));
					vyml_Video2_Title = MyVideosPg_VymlVideo2_Title_elem.getText();
					System.out.println("vyml_Video2_Title: "+vyml_Video2_Title);
	
					wait.until(ExpectedConditions.visibilityOf(MyVideosPg_VymlVideo2_Description_elem));
					vyml_Video2_Description = MyVideosPg_VymlVideo2_Description_elem.getText();
					System.out.println("vyml_Video2_Description: "+vyml_Video2_Description);
					
					wait.until(ExpectedConditions.visibilityOf(MyVideosPg_VymlVideo2_add_elem));
					MyVideosPg_VymlVideo2_add_elem.click();

					vyml_list.add(vyml_Video1_Title);
					vyml_list.add(vyml_Video1_Description);
					vyml_list.add(vyml_Video2_Title);
					vyml_list.add(vyml_Video2_Description);
					
				}else {
					throw new Exception("Videos You May Like are zero");
				}
				
			}
			
			wait.until(ExpectedConditions.visibilityOf(MyVideosPg_WlSection_elem));
			wl_sectionName = MyVideosPg_WlSection_elem.getText(); 
			System.out.println("Watch_sectionName: "+wl_sectionName);
			
			int wl_VideosCount = 0;
			
			String wl_Video1_Title = null;
			String wl_Video1_Description = null;
			
			String wl_Video2_Title = null;
			String wl_Video2_Description = null;
			
			if(wl_sectionName.equalsIgnoreCase(wl_str)) {
				
				wl_VideosCount = MyVideosPg_WlVideos_elems.size();
				System.out.println("Watch_VideosCount: "+wl_VideosCount);
				
				wait.until(ExpectedConditions.visibilityOf(MyVideosPg_WlVideo1_elem));
				actions.moveToElement(MyVideosPg_WlVideo1_elem).build().perform();
				
				wait.until(ExpectedConditions.visibilityOf(MyVideosPg_WlVideo1_Title_elem));
				wl_Video1_Title = MyVideosPg_WlVideo1_Title_elem.getText();
				System.out.println("WatchVideos_Video1_Title: "+wl_Video1_Title);
				
				//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='localStorageCarousel__wrapper']//div[@class='content carousel__content thumbnailCarousel__content'][1]/div[@class='carousel-tile-wrapper carousel__tileWrapper'][1]")));
				//actions.moveToElement(driver.findElement(By.xpath("//div[@class='localStorageCarousel__wrapper']//div[@class='content carousel__content thumbnailCarousel__content'][1]/div[@class='carousel-tile-wrapper carousel__tileWrapper'][1]"))).build().perform();
				
				wait.until(ExpectedConditions.visibilityOf(MyVideosPg_WlVideo1_Description_elem));
				wl_Video1_Description = MyVideosPg_WlVideo1_Description_elem.getText();
				System.out.println("WatchVideos_Video1_Description: "+wl_Video1_Description);
					
				wait.until(ExpectedConditions.visibilityOf(MyVideosPg_WlVideo2_elem));
				actions.moveToElement(MyVideosPg_WlVideo2_elem).build().perform();
				
				wait.until(ExpectedConditions.visibilityOf(MyVideosPg_WlVideo2_Title_elem));
				wl_Video2_Title = MyVideosPg_WlVideo2_Title_elem.getText();
				System.out.println("WatchVideos_Video2_Title: "+wl_Video2_Title);
				
				//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='localStorageCarousel__wrapper']//div[@class='content carousel__content thumbnailCarousel__content'][1]/div[@class='carousel-tile-wrapper carousel__tileWrapper'][2]")));
				//actions.moveToElement(driver.findElement(By.xpath("//div[@class='localStorageCarousel__wrapper']//div[@class='content carousel__content thumbnailCarousel__content'][1]/div[@class='carousel-tile-wrapper carousel__tileWrapper'][2]"))).build().perform();
				
				wait.until(ExpectedConditions.visibilityOf(MyVideosPg_WlVideo2_Description_elem));
				wl_Video2_Description = MyVideosPg_WlVideo2_Description_elem.getText();
				System.out.println("WatchVideos_Video2_Description: "+wl_Video2_Description);
	
				wl_list.add(wl_Video1_Title);
				wl_list.add(wl_Video1_Description);
				wl_list.add(wl_Video2_Title);
				wl_list.add(wl_Video2_Description);
				
			}else {
				throw new Exception("Watch Videos are zero, videos are not added properly to the favorites list");
			}
			
			System.out.println("Vidoes you may like List: "+vyml_list);
			System.out.println("Watch later List: "+wl_list);

	        if (vyml_list.equals(wl_list)) { 
	            System.out.println("Videos are added properly in Watch List"); 
	        }
	        else {
	            System.out.println("Videos are not added properly in Watch List"); 
	            throw new Exception("Videos are not added properly in Watch List");
	        }
			
			driver.quit();
			System.out.println("Test Case Passed");
			
		}catch(Exception e) {
			System.out.println("Test Case Failed due to Exception : "+e.getMessage());
			
			if(driver!=null) {
				src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(src, new File(upath+"/Screenshots/"+System.currentTimeMillis()+".png"));
				driver.quit();
			}
		}
		
	}

}
