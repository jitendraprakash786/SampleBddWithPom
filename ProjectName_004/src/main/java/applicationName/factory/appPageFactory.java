package applicationName.factory;

import applicationName.pages.homePage;
import applicationName.pages.landingPage;
import commonLibrary.basePage;
import commonLibrary.browser;

public class appPageFactory extends browser {

    public basePage getPage(String pageName) {
        basePage page = null;
        switch (pageName) {

            case "Landing":
                page = new landingPage(browser.driver);
                break;

            case "Home":
                page = new homePage(browser.driver);
                break;
        }

        return page;
    }
}
