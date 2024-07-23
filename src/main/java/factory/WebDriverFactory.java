package factory;

import data.BrowserNameData;
import factory.impl.ChromeSettings;
import factory.impl.FirefoxSettings;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Locale;

public class WebDriverFactory {

    private String browserName = System.getProperty("browser.name", "chrome");

    public WebDriver create() {
        BrowserNameData browserNameData = BrowserNameData.valueOf(browserName.toUpperCase(Locale.ROOT));

        switch(browserNameData) {
            case CHROME: {
                WebDriverManager.chromedriver().setup(); //установка последней версии хромдрайвера
                return new ChromeDriver((ChromeOptions) new ChromeSettings().settings());
            }
            case FIREFOX: {
                return new FirefoxDriver((FirefoxOptions) new FirefoxSettings().settings());
            }
        }
        return null;
    }
}
