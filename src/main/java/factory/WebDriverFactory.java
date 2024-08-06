package factory;

import data.BrowserNameData;
import factory.impl.ChromeSettings;
import factory.impl.FirefoxSettings;
import factory.impl.RemoteSettings;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

public class WebDriverFactory {

    private String browserName = System.getProperty("browser.name", "chrome");
    private String remoteGridUrl = System.getProperty("remote.url", "http://193.104.57.173/wd/hub");

    public WebDriver create() throws MalformedURLException {
        BrowserNameData browserNameData = BrowserNameData.valueOf(browserName.toUpperCase(Locale.ROOT));

        switch(browserNameData) {
            case CHROME: {
                WebDriverManager.chromedriver().setup(); //установка последней версии хромдрайвера
                return new ChromeDriver((ChromeOptions) new ChromeSettings().settings());
            }
            case FIREFOX: {
                return new FirefoxDriver((FirefoxOptions) new FirefoxSettings().settings());
            }
            case REMOTE: {
                RemoteSettings chromeOptions = new RemoteSettings();
                return new RemoteWebDriver(new URL(remoteGridUrl), chromeOptions.settings());
            }
        }
        return null;
    }
}
