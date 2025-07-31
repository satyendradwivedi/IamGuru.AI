package com.Gooru.AI.testCases;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import com.Gooru.AI.utilities.ReadConfig;
import com.aventstack.chaintest.plugins.ChainTestListener;

@Listeners(ChainTestListener.class)
public class BaseClass {
	
    ReadConfig readconfig = new ReadConfig();
    public String baseUrlLogin=readconfig.getApplicationURL();
    public String baseUrlRegistration = readconfig.getApplicationRegistrationURL();
    
    public String baseURL = readconfig.getApplicationRegistrationURL();
    public static WebDriver driver;
    public static WebDriverWait wait;
    public static Logger logger;


    public static WebDriver getWebDriver() {
        return driver;
    }

    @Parameters("browser")
    @BeforeClass
    public void setup(String br) {
        //System.setProperty("chaintest.config", "src/test/resources/chaintest.properties");
        //System.setProperty("chaintest.report.path", "target/chaintest-reports/");
        
        logger = Logger.getLogger("E-socio Platform");
        PropertyConfigurator.configure("Log4j.properties");

        if (br.equals("chrome")) {
			// System.setProperty("webdriver.chrome.driver",readconfig.getChromePath());
			driver = new ChromeDriver();
		} else if (br.equals("firefox")) {
			// System.setProperty("webdriver.gecko.driver",readconfig.getFirefoxPath());
			driver = new FirefoxDriver();
		} else if (br.equals("ie")) {
			// System.setProperty("webdriver.ie.driver",readconfig.getIEPath());
			driver = new InternetExplorerDriver();
		}

        // Set timeouts and open the URL
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(120));
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(120));
        driver.manage().deleteAllCookies();
        driver.get(baseURL);
      // driver.get(baseUrlLogin);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            //driver.quit();
        }

    }

    public void captureScreen(WebDriver driver, String tname) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
        FileUtils.copyFile(source, target);
        System.out.println("Screenshot taken: " + tname);
    }
}