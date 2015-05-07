package HomeUnion;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class Propertysearch1 {
    FirefoxDriver wd;
    
    @Before
    public void setUp() throws Exception {
        wd = new FirefoxDriver();
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }
    
    @Test
    public void PropertySearch1() {
        wd.get("https://investor.homeunion.com/");
        wd.findElement(By.cssSelector("div.wrap-height")).click();
        wd.findElement(By.id("open_login_modal")).click();
        wd.findElement(By.id("login-username-input")).click();
        wd.findElement(By.id("login-username-input")).clear();
        wd.findElement(By.id("login-username-input")).sendKeys("solutionspecialist@outlook.com");
        wd.findElement(By.id("login-password-input")).click();
        wd.findElement(By.id("login-password-input")).clear();
        wd.findElement(By.id("login-password-input")).sendKeys("123456");
        wd.findElement(By.id("login-btn")).click();
        wd.findElement(By.linkText("Property Search")).click();
        wd.findElement(By.xpath("//div[@id='hil_div']//label[.='Austin']")).click();
        if (!wd.findElement(By.id("hil_checkbox2")).isSelected()) {
            wd.findElement(By.id("hil_checkbox2")).click();
        }
        wd.findElement(By.id("prop_srch_btn")).click();
        wd.findElement(By.cssSelector("span.irs.js-irs-0 > span.irs")).click();
        wd.findElement(By.id("prop_srch_btn")).click();
        wd.findElement(By.id("prop_srch_btn")).click();
        wd.findElement(By.xpath("//form[@id='asset_prop_srch_form']/div[1]/div/div[3]/div/div[2]/div[2]/div/label[2]")).click();
        wd.findElement(By.id("prop_srch_btn")).click();
        wd.findElement(By.xpath("//table[@id='tbl_search_results']/tbody/tr[1]/td[1]/div/a/img")).click();
        wd.findElement(By.linkText("Street View")).click();
        wd.findElement(By.linkText("Financial Details")).click();
        wd.findElement(By.cssSelector("div.col-md-24.bb2")).click();
        wd.findElement(By.id("all")).click();
        wd.findElement(By.linkText("Comps")).click();
        wd.findElement(By.linkText("Neighborhood")).click();
        wd.findElement(By.linkText("Local Area")).click();
        wd.findElement(By.linkText("City Info")).click();
        wd.findElement(By.id("back_to_search")).click();
        wd.findElement(By.xpath("//div[@id='hil_div']//label[.='Chicago']")).click();
        if (!wd.findElement(By.id("hil_checkbox4")).isSelected()) {
            wd.findElement(By.id("hil_checkbox4")).click();
        }
        wd.findElement(By.xpath("//div[@id='hil_div']//label[.='Austin']")).click();
        if (wd.findElement(By.id("hil_checkbox2")).isSelected()) {
            wd.findElement(By.id("hil_checkbox2")).click();
        }
        wd.findElement(By.id("prop_srch_btn")).click();
        wd.findElement(By.xpath("//form[@id='asset_prop_srch_form']/div[1]/div/div[3]/div/div[2]/div[2]/div/label[3]")).click();
        wd.findElement(By.id("prop_srch_btn")).click();
        wd.findElement(By.xpath("//tr[@class='odd']/td[1]/div/a/img")).click();
        wd.findElement(By.linkText("Financial Details")).click();
        wd.findElement(By.linkText("Street View")).click();
        wd.findElement(By.linkText("Comps")).click();
        wd.findElement(By.linkText("Neighborhood")).click();
        wd.findElement(By.linkText("City Info")).click();
        wd.findElement(By.id("back_to_search")).click();
        wd.findElement(By.xpath("//div[@id='hil_div']//label[.='Dallas']")).click();
        if (!wd.findElement(By.id("hil_checkbox6")).isSelected()) {
            wd.findElement(By.id("hil_checkbox6")).click();
        }
        wd.findElement(By.xpath("//div[@id='hil_div']//label[.='Cleveland']")).click();
        if (!wd.findElement(By.id("hil_checkbox5")).isSelected()) {
            wd.findElement(By.id("hil_checkbox5")).click();
        }
        wd.findElement(By.xpath("//div[@id='hil_div']//label[.='Dallas']")).click();
        if (wd.findElement(By.id("hil_checkbox6")).isSelected()) {
            wd.findElement(By.id("hil_checkbox6")).click();
        }
        wd.findElement(By.xpath("//div[@id='hil_div']//label[.='Chicago']")).click();
        if (wd.findElement(By.id("hil_checkbox4")).isSelected()) {
            wd.findElement(By.id("hil_checkbox4")).click();
        }
        wd.findElement(By.id("prop_srch_btn")).click();
        wd.findElement(By.linkText("Cleveland-Growth")).click();
        wd.findElement(By.id("property-tab")).click();
        wd.findElement(By.xpath("//table[@id='assetPropListTable']/tbody/tr[2]/td[1]/a/img")).click();
        wd.findElement(By.xpath("//div[@class='header-inner']/ul/li[3]/a/span[1]")).click();
        wd.findElement(By.id("logout-link")).click();
    }
    
    @After
    public void tearDown() {
        wd.quit();
    }
    
    public static boolean isAlertPresent(FirefoxDriver wd) {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
}
