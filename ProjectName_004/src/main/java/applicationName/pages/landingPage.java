package applicationName.pages;

import commonLibrary.basePage;
import commonLibrary.browser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class landingPage extends basePage {
    public landingPage(WebDriver driver){
        PageFactory.initElements(browser.driver, this);
    }

    @FindBy(xpath = "//textarea[@name='q']")
    private WebElement TextBox_Search;

}