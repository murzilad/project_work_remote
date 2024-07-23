package common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import waiters.Waiters;

import java.time.Duration;
import java.util.List;

public abstract class AbsCommon {

    protected WebDriver driver = null;
    protected Waiters waiters = null;

    public AbsCommon(WebDriver driver) {
        this.driver = driver;
        this.waiters = new Waiters(driver);

        PageFactory.initElements(driver, this);
    }

    public WebElement $(By locator) {
        return driver.findElement(locator);
    }

    public List<WebElement> $$(By locator) {
        return driver.findElements(locator);
    }

    public WebElement waitElementVisible(By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public boolean waitElementNotVisible(By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

}
