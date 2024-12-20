package stepDefinitions;

import applicationName.factory.appPageFactory;
import applicationName.pages.landingPage;
import com.esotericsoftware.yamlbeans.YamlException;
import commonLibrary.basePage;
import commonLibrary.browser;
import commonLibrary.common;
import commonLibrary.utility;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.time.Duration;

public class commonSteps {
    public static String applicationName = null;
    browser brw = new browser();
    appPageFactory pf = new appPageFactory();
    landingPage cpLp;

    WebDriverWait eWait;

    WebDriver driver;

    public static basePage page;
    utility ut = new utility();
    common cm;

    @When("^I launch the (.*) browser$")
    public void browserSetup(String brwName){
        cm = new common();
        browser.logger.info("***** Executing browserSetup method *****");
        brw.launchBrowser(brwName);
        browser.logger.info("Browser launched");
        eWait = new WebDriverWait(browser.driver, Duration.ofSeconds(45));
    }

    @And("^I switch the browser tab to child$")
    public void switchTab(){
        String originalWindow = browser.driver.getWindowHandle();
        String childWindowHandle = null;
        for (String wndHandle: browser.driver.getWindowHandles()){
            if (!wndHandle.equals(originalWindow)){
                browser.logger.info("Child Window Handle: " + wndHandle);
                childWindowHandle = wndHandle;
                break;
            }
        }
        browser.driver.switchTo().window(childWindowHandle);

    }

    @And("^I click (.*) element if exists$")
    public void clickElementStepIfExist(String eleName) throws InterruptedException, NoSuchFieldException, IllegalAccessException {
        WebElement ele = getElement(eleName);
        try{
            if (cm.waitForElement(ele).isDisplayed()){
                cm.waitForElement(ele).click();
            }
        }catch(Exception e){
            browser.logger.info("Error Message: "+ e.getMessage());
        }

    }

    @And("^I click the (.*) element$")
    public void clickElementStep(String eleName) throws InterruptedException, NoSuchFieldException, IllegalAccessException {
        WebElement ele = getElement(eleName);
        cm.waitForElement(ele);
        cm.checkElementExists(ele, "click").click();
    }

    @And("^I enter (.*) value in the (.*) field$")
    public void enterTextBox(String value, String elementName) throws NoSuchFieldException, IllegalAccessException {
        WebElement ele = getElement(elementName);
        ele.sendKeys(value);
    }

    @Then("^I open the application in (.*) environment$")
    public void openApplication(String envName) throws IOException {
        String url = ut.getAppUrl(envName); //ut.getValueFromYml(envName, appName, "URL");
        brw.openApp(url);
        browser.logger.info(url + " opened!");
    }

    @Given("^I am on the (.*) page$")
    public void setPage(String pageName){
        cm = new common();
        page = pf.getPage(pageName);
        browser.logger.info(pageName + " loaded!");
    }

    @And("^I verify (.*) element is displayed$")
    public void verifyEleDisplayed(String eleName) throws InterruptedException, NoSuchFieldException, IllegalAccessException {
        WebElement ele = getElement(eleName);
        cm.checkElementExists(ele, "sendkeys");
    }

    @And("^I mouse hover the (.*) element$")
    public void mouseHoverElement(String eleName) throws InterruptedException, NoSuchFieldException, IllegalAccessException {
        Thread.sleep(2500);
        WebElement element = getElement(eleName);

        Actions action = new Actions(browser.driver);
        action.moveToElement(element).build().perform();

        Thread.sleep(2500);

    }

    @And("^I wait for (.*) seconds$")
    public void hardCodeWait(int val) {
        try {
            Thread.sleep(val * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Then("^I close the browser$")
    public void closeBrowserTearDown(){
        brw.closeBrowser();
    }

    public WebElement getElement(String elementName) throws NoSuchFieldException, IllegalAccessException {
        Field field = page.getClass().getDeclaredField(elementName);
        field.setAccessible(true);
        return (WebElement) field.get(page);
    }

}
