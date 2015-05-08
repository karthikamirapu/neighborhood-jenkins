package HomeUnion;

/**
 * Created by Karthik on 1/20/15.
 */

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;


/**
 * Simple {@link org.openqa.selenium.remote.RemoteWebDriver} test that demonstrates how to run your Selenium tests with <a href="http://saucelabs.com/ondemand">Sauce OnDemand</a>.
 * *
 * @author Ross Rowe
 */
public class Neighborhood_tab {

    private WebDriver driver;
    private WebElement element;
    boolean fail_status = false;

    //public SauceOnDemandAuthentication authentication = new SauceOnDemandAuthentication(System.getenv("SAUCE_USERNAME"), System.getenv("SAUCE_ACCESS_KEY"));


    @Before
    public void setUp() throws Exception {

        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("version", "37");
        capabilities.setCapability("platform", Platform.XP);
        capabilities.setCapability("name","ngbhd");
        this.driver = new RemoteWebDriver(
                new URL("http://karthikamirapu:75ff59d4-4d26-429a-bb15-f10ccb6c7383@ondemand.saucelabs.com:80/wd/hub"),
                capabilities);
        //System.setProperty("webdriver.chrome.driver", "/Users/Karthik/Downloads/chromedriver");
        //this.driver = new ChromeDriver();

        Dimension d = new Dimension(1280,800);
        driver.manage().window().setSize(d);
        //this.driver = new FirefoxDriver();
    }

    @Rule
    public TestWatcher watchman = new TestWatcher() {
        @Override
        protected void failed(Throwable e,Description d) {
            System.out.println("FAILED: "+d.getClassName()+"->"+d.getMethodName());
        }

        @Override
        protected void succeeded(Description d) {
            System.out.println("PASSED: "+d.getClassName()+"->"+d.getMethodName());
        }
    };


    @Test
    public void Balanced() throws Exception {

        String property_address = "";
        String investment_amount_str;
        String investment_portfolio;
        String return_filter;
        String appr_filter;
        String property_class_filter;
        String property_price_min_filter;
        String property_price_max_filter;
        String year_built_min_filter;
        String year_built_max_filter;
        String HIL_list[];

        int i=0;
        int temp_investment;
        int sizeofoddrows=0;
        int sizeofevenrows=0;

        driver.get("https://investor.homeunion.com");
        try { Thread.sleep(8000l); } catch (Exception e) { throw new RuntimeException(e); }
        //driver.manage().window().maximize();

        driver.findElement(By.id("open_login_modal")).click();
        try { Thread.sleep(1000l); } catch (Exception e) { throw new RuntimeException(e); }

        //Enter Username
        driver.findElement(By.id("login-username-input")).sendKeys("solutionspecialist@outlook.com");
        //Enter password
        driver.findElement(By.id("login-password-input")).sendKeys("123456");
        // what are we waiting for??? Time to login
        driver.findElement(By.id("login-btn")).click();
        //wait for sometime till the page loads
        try { Thread.sleep(3000l); } catch (Exception e) { throw new RuntimeException(e); }

        //go to property search
        driver.findElement(By.linkText("Property Search")).click();
        try { Thread.sleep(5000l); } catch (Exception e) { throw new RuntimeException(e); }

        //Set location to 'Any'
        driver.findElement(By.cssSelector("label.sm-label")).click();
        if (!driver.findElement(By.id("hil_checkbox1")).isSelected()) {
            driver.findElement(By.id("hil_checkbox1")).click();
        }

        //san -antonio
        /*driver.findElement(By.xpath("//div[@id='hil_div']//label[.='Nashville']")).click();
        if (!driver.findElement(By.id("hil_checkbox10")).isSelected()) {
            driver.findElement(By.cssSelector("#hil_checkbox10")).click();
        }
        try { Thread.sleep(1000l); } catch (Exception e) { throw new RuntimeException(e); }
        */
        ////

        //Set rental economy to 'Any'
        driver.findElement(By.cssSelector("div.col-sm-12.col-md-8  > div.checkbox.custom-checkbox > label.sm-label")).click();
        if (!driver.findElement(By.id("econ_checkbox1")).isSelected()) {
            driver.findElement(By.id("econ_checkbox1")).click();
        }
        try { Thread.sleep(1000l); } catch (Exception e) { throw new RuntimeException(e); }

        //Set Investment goal to 'Balanced'
        driver.findElement(By.xpath("//form[@id='asset_prop_srch_form']/div[1]/div/div[3]/div/div[2]/div[2]/div/label[2]")).click();
        try { Thread.sleep(1000l); } catch (Exception e) { throw new RuntimeException(e); }

        //Search....
        driver.findElement(By.cssSelector("#prop_srch_btn")).click();
        //wait a sec for page to load
        try { Thread.sleep(3000l); } catch (Exception e) { throw new RuntimeException(e); }

        String match_found="";
        boolean final_page = false;
        int page = 0;


        do {
            page++;
             //Check odd rows in search results
             List<WebElement> odd_rows = driver.findElements(By.xpath("//tr[@class='odd']//a[@class='wishl-thumb pull-left pointer']"));
             sizeofoddrows = odd_rows.size();
             List<WebElement> even_rows = driver.findElements(By.xpath("//tr[@class='even']//a[@class='wishl-thumb pull-left pointer']"));
             sizeofevenrows = even_rows.size();
            ((JavascriptExecutor) driver).executeScript("scroll(250,0);");

            try { Thread.sleep(3000l); } catch (Exception e) { throw new RuntimeException(e); }
            //driver.findElement(By.xpath("//table[@id='tbl_search_results']/tbody/tr[1]/td[1]/div/a/img")).click();
            driver.findElement(By.cssSelector("img.propImage")).click();


            for(i = 0; i < (sizeofoddrows+sizeofevenrows); i++) {
                //Wait for the PDP to load
                WebDriverWait wait = new WebDriverWait(driver, 20);
                WebElement element = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#tab3 > a")));

                // Go to neighborhood page
                driver.findElement(By.cssSelector("#tab5 > a")).click();
                try {
                    Thread.sleep(2500l);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                property_address = driver.findElement(By.cssSelector("h3")).getText();

                //switch to neighborhood frame
                driver = (ChromeDriver) driver.switchTo().frame("nbrhdInfo");

                if (driver.findElement(By.tagName("html")).getText().contains("No Neighborhood Found.")) {
                    System.out.print("Neighbourhood data not present for -");
                    System.out.println(property_address);
                }

                driver = (ChromeDriver) driver.switchTo().defaultContent();



                //check if we have finished testing all the properties in this page
                if(i == (sizeofoddrows+sizeofevenrows-1))
                {
                    // Yes we are done with this page. Go back to search results and navigate to next page
                    driver.findElement(By.xpath("//*[@id=\"back_to_search\"]")).click();
                    try { Thread.sleep(9000l); } catch (Exception e) { throw new RuntimeException(e); }
                }
                else
                {
                    //no, we are not done yet. Go to next property
                    driver.findElement(By.id("next_property")).click();
                }
            }

            //Below code is to check whether this is the last page
            //WebElement next_page_element = driver.findElement(By.xpath("//ul[contains(@class, 'pagination')]/li[contains(@class,'next')]"));
            //match_found = next_page_element.getAttribute("class");
            //if(match_found.matches("(.*)disabled"))
            if(sizeofoddrows+sizeofevenrows < 20)
                final_page = true; //yep, this is the last page
            else {
                for(int k=0; k<page;k++) {
                    driver.findElement(By.cssSelector("i.ico.ico-next")).click(); //too bad, still few more pages to check
                    //wait a sec for page to load
                    try {
                        Thread.sleep(6000l);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }

        }while (final_page == false);

        /*if(fail_status == true)
        {
            Assert.fail("Financial calculations test is failed ");
        }*/
    }

    @Test
    public void Income() throws Exception {

        String property_address = "";
        String investment_amount_str;
        String investment_portfolio;
        String return_filter;
        String appr_filter;
        String property_class_filter;
        String property_price_min_filter;
        String property_price_max_filter;
        String year_built_min_filter;
        String year_built_max_filter;
        String HIL_list[];

        int i=0;
        int temp_investment;
        int sizeofoddrows=0;
        int sizeofevenrows=0;

        driver.get("https://investor.homeunion.com");
        try { Thread.sleep(8000l); } catch (Exception e) { throw new RuntimeException(e); }
        //driver.manage().window().maximize();

        driver.findElement(By.id("open_login_modal")).click();
        try { Thread.sleep(1000l); } catch (Exception e) { throw new RuntimeException(e); }

        //Enter Username
        driver.findElement(By.id("login-username-input")).sendKeys("solutionspecialist@outlook.com");
        //Enter password
        driver.findElement(By.id("login-password-input")).sendKeys("123456");
        // what are we waiting for??? Time to login
        driver.findElement(By.id("login-btn")).click();
        //wait for sometime till the page loads
        try { Thread.sleep(3000l); } catch (Exception e) { throw new RuntimeException(e); }

        //go to property search
        driver.findElement(By.linkText("Property Search")).click();
        try { Thread.sleep(5000l); } catch (Exception e) { throw new RuntimeException(e); }

        //Set location to 'Any'
        driver.findElement(By.cssSelector("label.sm-label")).click();
        if (!driver.findElement(By.id("hil_checkbox1")).isSelected()) {
            driver.findElement(By.id("hil_checkbox1")).click();
        }

        //san -antonio
        /*driver.findElement(By.xpath("//div[@id='hil_div']//label[.='Nashville']")).click();
        if (!driver.findElement(By.id("hil_checkbox10")).isSelected()) {
            driver.findElement(By.cssSelector("#hil_checkbox10")).click();
        }
        try { Thread.sleep(1000l); } catch (Exception e) { throw new RuntimeException(e); }
        */
        ////

        //Set rental economy to 'Any'
        driver.findElement(By.cssSelector("div.col-sm-12.col-md-8  > div.checkbox.custom-checkbox > label.sm-label")).click();
        if (!driver.findElement(By.id("econ_checkbox1")).isSelected()) {
            driver.findElement(By.id("econ_checkbox1")).click();
        }
        try { Thread.sleep(1000l); } catch (Exception e) { throw new RuntimeException(e); }

        //Set Investment goal to 'Income'
        driver.findElement(By.cssSelector("label.btn.btn-default")).click();
        try {
            Thread.sleep(1000l);
        } catch (Exception e) { throw new RuntimeException(e); }

        //Search....
        driver.findElement(By.cssSelector("#prop_srch_btn")).click();
        //wait a sec for page to load
        try { Thread.sleep(3000l); } catch (Exception e) { throw new RuntimeException(e); }

        String match_found="";
        boolean final_page = false;
        int page = 0;


        do {
            page++;
            //Check odd rows in search results
            List<WebElement> odd_rows = driver.findElements(By.xpath("//tr[@class='odd']//a[@class='wishl-thumb pull-left pointer']"));
            sizeofoddrows = odd_rows.size();
            List<WebElement> even_rows = driver.findElements(By.xpath("//tr[@class='even']//a[@class='wishl-thumb pull-left pointer']"));
            sizeofevenrows = even_rows.size();
            ((JavascriptExecutor) driver).executeScript("scroll(250,0);");

            try { Thread.sleep(3000l); } catch (Exception e) { throw new RuntimeException(e); }
            //driver.findElement(By.xpath("//table[@id='tbl_search_results']/tbody/tr[1]/td[1]/div/a/img")).click();
            driver.findElement(By.cssSelector("img.propImage")).click();


            for(i = 0; i < (sizeofoddrows+sizeofevenrows); i++) {
                //Wait for the PDP to load
                WebDriverWait wait = new WebDriverWait(driver, 100);
                WebElement element = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#tab3 > a")));

                // Go to neighborhood page
                driver.findElement(By.cssSelector("#tab5 > a")).click();
                try {
                    Thread.sleep(2500l);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                property_address = driver.findElement(By.cssSelector("h3")).getText();

                //switch to neighborhood frame
                driver = (ChromeDriver) driver.switchTo().frame("nbrhdInfo");

                if (driver.findElement(By.tagName("html")).getText().contains("No Neighborhood Found.")) {
                    System.out.print("Neighbourhood data not present for -");
                    System.out.println(property_address);
                }

                driver = (ChromeDriver) driver.switchTo().defaultContent();



                //check if we have finished testing all the properties in this page
                if(i == (sizeofoddrows+sizeofevenrows-1))
                {
                    // Yes we are done with this page. Go back to search results and navigate to next page
                    driver.findElement(By.xpath("//*[@id=\"back_to_search\"]")).click();
                    try { Thread.sleep(4000l); } catch (Exception e) { throw new RuntimeException(e); }
                }
                else
                {
                    //no, we are not done yet. Go to next property
                    driver.findElement(By.id("next_property")).click();
                }
            }

            //Below code is to check whether this is the last page
            //WebElement next_page_element = driver.findElement(By.xpath("//ul[contains(@class, 'pagination')]/li[contains(@class,'next')]"));
            //match_found = next_page_element.getAttribute("class");
            //if(match_found.matches("(.*)disabled"))
            if(sizeofoddrows+sizeofevenrows < 20)
                final_page = true; //yep, this is the last page
            else {
                for(int k=0; k<page;k++) {
                    driver.findElement(By.cssSelector("i.ico.ico-next")).click(); //too bad, still few more pages to check
                    //wait a sec for page to load
                    try {
                        Thread.sleep(6000l);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }

        }while (final_page == false);

        /*if(fail_status == true)
        {
            Assert.fail("Financial calculations test is failed ");
        }*/
    }

    @Test
    public void Growth() throws Exception {

        String property_address = "";
        String investment_amount_str;
        String investment_portfolio;
        String return_filter;
        String appr_filter;
        String property_class_filter;
        String property_price_min_filter;
        String property_price_max_filter;
        String year_built_min_filter;
        String year_built_max_filter;
        String HIL_list[];

        int i=0;
        int temp_investment;
        int sizeofoddrows=0;
        int sizeofevenrows=0;

        driver.get("https://investor.homeunion.com");
        try { Thread.sleep(8000l); } catch (Exception e) { throw new RuntimeException(e); }
        //driver.manage().window().maximize();

        driver.findElement(By.id("open_login_modal")).click();
        try { Thread.sleep(1000l); } catch (Exception e) { throw new RuntimeException(e); }

        //Enter Username
        driver.findElement(By.id("login-username-input")).sendKeys("solutionspecialist@outlook.com");
        //Enter password
        driver.findElement(By.id("login-password-input")).sendKeys("123456");
        // what are we waiting for??? Time to login
        driver.findElement(By.id("login-btn")).click();
        //wait for sometime till the page loads
        try { Thread.sleep(3000l); } catch (Exception e) { throw new RuntimeException(e); }

        //go to property search
        driver.findElement(By.linkText("Property Search")).click();
        try { Thread.sleep(3000l); } catch (Exception e) { throw new RuntimeException(e); }

        //Set location to 'Any'
        driver.findElement(By.cssSelector("label.sm-label")).click();
        if (!driver.findElement(By.id("hil_checkbox1")).isSelected()) {
            driver.findElement(By.id("hil_checkbox1")).click();
        }

        //san -antonio
        /*driver.findElement(By.xpath("//div[@id='hil_div']//label[.='Nashville']")).click();
        if (!driver.findElement(By.id("hil_checkbox10")).isSelected()) {
            driver.findElement(By.cssSelector("#hil_checkbox10")).click();
        }
        try { Thread.sleep(1000l); } catch (Exception e) { throw new RuntimeException(e); }
        */
        ////

        //Set rental economy to 'Any'
        driver.findElement(By.cssSelector("div.col-sm-12.col-md-8  > div.checkbox.custom-checkbox > label.sm-label")).click();
        if (!driver.findElement(By.id("econ_checkbox1")).isSelected()) {
            driver.findElement(By.id("econ_checkbox1")).click();
        }
        try { Thread.sleep(1000l); } catch (Exception e) { throw new RuntimeException(e); }

        //Set Investment goal to 'Growth'
        driver.findElement(By.xpath("//form[@id='asset_prop_srch_form']/div[1]/div/div[3]/div/div[2]/div[2]/div/label[3]")).click();
        try {
            Thread.sleep(1000l);
        } catch (Exception e) { throw new RuntimeException(e); }

        //Search....
        driver.findElement(By.cssSelector("#prop_srch_btn")).click();
        //wait a sec for page to load
        try { Thread.sleep(8000l); } catch (Exception e) { throw new RuntimeException(e); }

        String match_found="";
        boolean final_page = false;
        int page = 0;


        do {
            page++;
            System.out.print("Page -");
            System.out.println(page);
            //Check odd rows in search results
            List<WebElement> odd_rows = driver.findElements(By.xpath("//tr[@class='odd']//a[@class='wishl-thumb pull-left pointer']"));
            sizeofoddrows = odd_rows.size();
            List<WebElement> even_rows = driver.findElements(By.xpath("//tr[@class='even']//a[@class='wishl-thumb pull-left pointer']"));
            sizeofevenrows = even_rows.size();
            ((JavascriptExecutor) driver).executeScript("scroll(250,0);");

            try { Thread.sleep(3000l); } catch (Exception e) { throw new RuntimeException(e); }
            //driver.findElement(By.xpath("//table[@id='tbl_search_results']/tbody/tr[1]/td[1]/div/a/img")).click();
            driver.findElement(By.cssSelector("img.propImage")).click();


            for(i = 0; i < (sizeofoddrows+sizeofevenrows); i++) {
                //Wait for the PDP to load
                WebDriverWait wait = new WebDriverWait(driver, 100);
                WebElement element = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#tab3 > a")));

                // Go to neighborhood page
                driver.findElement(By.cssSelector("#tab5 > a")).click();
                try {
                    Thread.sleep(5000l);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                property_address = driver.findElement(By.cssSelector("h3")).getText();
                System.out.print("checking property -");
                System.out.println(property_address);

                //switch to neighborhood frame
                driver = (ChromeDriver) driver.switchTo().frame("nbrhdInfo");

                if (driver.findElement(By.tagName("html")).getText().contains("No Neighborhood Found.")) {
                    System.out.print("Neighbourhood data not present for -");
                    System.out.println(property_address);
                }

                driver = (ChromeDriver) driver.switchTo().defaultContent();



                //check if we have finished testing all the properties in this page
                if(i == (sizeofoddrows+sizeofevenrows-1))
                {
                    // Yes we are done with this page. Go back to search results and navigate to next page
                    driver.findElement(By.xpath("//*[@id=\"back_to_search\"]")).click();
                    try { Thread.sleep(9000l); } catch (Exception e) { throw new RuntimeException(e); }
                }
                else
                {
                    //no, we are not done yet. Go to next property
                    driver.findElement(By.id("next_property")).click();
                }
            }

            //Below code is to check whether this is the last page
            //WebElement next_page_element = driver.findElement(By.xpath("//ul[contains(@class, 'pagination')]/li[contains(@class,'next')]"));
            //match_found = next_page_element.getAttribute("class");
            //if(match_found.matches("(.*)disabled"))
            if(sizeofoddrows+sizeofevenrows < 20)
                final_page = true; //yep, this is the last page
            else {
                for(int k=0; k<page;k++) {
                    driver.findElement(By.cssSelector("i.ico.ico-next")).click(); //too bad, still few more pages to check
                    //wait a sec for page to load
                    try {
                        Thread.sleep(6000l);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }

        }while (final_page == false);

        /*if(fail_status == true)
        {
            Assert.fail("Financial calculations test is failed ");
        }*/
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

}
