package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class InfoCardCoursesPage extends AbsBasePage {

    public InfoCardCoursesPage(WebDriver driver) {
        super(driver);
    }

    private By courseCardsLocator = By.xpath("//div[text()='Каталог']/ancestor::section/div[2]/div/a[1]");
    private By searchIconLocator = By.xpath("//label[text() = 'Поиск']/following-sibling::div");
    private By leaveRequestButtonLocator = By.xpath("//div[./h1]/child::div[./button[./span[text() = 'Оставить заявку']]]");

    private By titleSelector = By.cssSelector("div > h1");
    private By descriptionLocator = By.xpath("//div[./h1]/child::div/p[./br]");
    private By courseDurationLocator = By.xpath("//div[contains(@class, 'galmep')]/descendant::p[3]");
    private By formatLocator = By.xpath("//div[contains(@class, 'galmep')]/descendant::p[4]");


    public InfoCardCoursesPage checkCardInfo() {

        $(titleSelector).click(); //пустой клик по странице, тк сайт отус не прогружается, пока не кликнешь на свободное пространство страницы
        waitElementVisible(searchIconLocator);
        $(courseCardsLocator).click();
        waitElementVisible(leaveRequestButtonLocator);


        assertThat($(titleSelector).getText())
            .as("Error: title is empty")
            .isNotEmpty();

        assertThat($(descriptionLocator).getText())
             .as("Error: description is empty")
            .isNotEmpty();

        assertThat($(courseDurationLocator).getText())
            .as("Error: course duration isn't specified")
            .isNotEmpty();

        assertThat($(formatLocator).getText())
            .as("Error: course format isn't specified")
            .isNotEmpty();

        return this;
    }

}


