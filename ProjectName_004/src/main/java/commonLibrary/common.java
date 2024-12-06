package commonLibrary;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.Field;
import java.time.Duration;



public class common extends browser{

    public WebElement checkElementExists(WebElement ele, String action) throws InterruptedException {
        WebDriverWait eWait = new WebDriverWait(driver, Duration.ofSeconds(60));
        String message = "";
        WebElement eleToSearch = null;
        for (int i=0; i < 2; i++){
            try{
                switch (action.toLowerCase()){
                    case "click":
                        eleToSearch = eWait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(ele)));
                        break;
                    case "sendkeys":
                        eleToSearch = eWait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(ele)));
                        break;
                }

            }catch(NoSuchElementException | StaleElementReferenceException st){
                //System.out.println("Error Message: " + st.getMessage());
                logger.info("Error Message: " + st.getMessage());
                message = st.getMessage();
            }

            if (message.length() > 0){
                Thread.sleep(2500);
            }else{
                break;
            }
        }

        return eleToSearch;
    }

    public WebElement waitForElement(WebElement element){
        Wait<WebDriver> wait = new FluentWait<WebDriver>(browser.driver)
                .withTimeout(Duration.ofSeconds(60L))
                .pollingEvery(Duration.ofSeconds(5L))
                .ignoring(NoSuchElementException.class);

        wait.until(ExpectedConditions.visibilityOf(element));

        return element;
    }
}
