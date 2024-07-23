package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CatalogPage extends AbsBasePage {

    public CatalogPage(WebDriver driver) {
        super(driver);
    }

    private By courseCardLocator = By.xpath("//div[text()='Каталог']/ancestor::section/div[2]/div/a");

    public CatalogPage pageHeaderShouldBeSameAs(String expectedHeader) {

        By headerSelector = By.cssSelector("h1 > div");
        waiters.waitForCondition(ExpectedConditions.presenceOfElementLocated(headerSelector));

        assertThat($(headerSelector).getText()) //actual result
                .as("Header of page should be same as {}", expectedHeader)
                .isEqualTo(expectedHeader);
        return this;
    }

    public CatalogPage checkCountCard() {
        List<WebElement> countCard = $$(courseCardLocator);
        assertThat(countCard.size())
                .as("Error: the number of cards isn't equal to 10")
                .isEqualTo(10);
        return this;
    }
}
