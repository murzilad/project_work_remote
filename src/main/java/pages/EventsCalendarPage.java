package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class EventsCalendarPage extends AbsBasePage {

    public EventsCalendarPage(WebDriver driver) {
        super(driver);
    }

    private By eventDateLocator = By.xpath("//span[contains(@class, 'event__calendar')]/following-sibling::span[contains(@class, 'date-text')]");

    private By eventTypeButtonLocator = By.xpath("//div[contains(@class, 'events__header-left')]/div");
    private By openWebinarSelectValueLocator = By.xpath("//div[contains(@class, 'events-dropdown_opened')]//a[@title = 'Открытый вебинар']");
    private By eventTypeInputSelectedLocator = By.xpath("//div[contains(@class, 'events__header-left')]/descendant::span[contains(@class, 'input-selected')]");
    private By eventTypeInCardLocator = By.xpath("//div[contains(@class, 'type__text')]");


    public EventsCalendarPage pageHeaderShouldBeSameAs(String expectedHeader) {
        By headerSelector = By.xpath("//div[contains(text(), 'Календарь мероприятий')]");
        waiters.waitForCondition(ExpectedConditions.presenceOfElementLocated(headerSelector));
        assertThat($(headerSelector).getText())
                .as("Header of page should be same as {}", expectedHeader)
                .isEqualTo(expectedHeader);

        return this;
    }

    public boolean compareCurrentDateWithEventDate() {

        //текущая дата
        LocalDate currentDate = LocalDate.now();

        int currentYear = currentDate.getYear();

        List<WebElement> eventDate = driver.findElements(eventDateLocator);
        for (WebElement dates : eventDate) {
            String eventDataPage = dates.getText() + " " + currentYear;

            //создание объекта DateTimeFormatter, с указанием шаблона соответствующего формату даты eventDataPage
            DateTimeFormatter parser = DateTimeFormatter.ofPattern("dd MMMM yyyy");
            //преобразование строки в объект LocalDate
            LocalDate eventDateLocal = LocalDate.parse(eventDataPage, parser);

            //сравнение даты мероприятия с текущей датой
            assertThat((eventDateLocal.isAfter(currentDate) || eventDateLocal.isEqual(currentDate)))
                    .as("Event date is less than current date")
                    .isTrue();
        }
        return true;
    }

    public void clickToButton(By locator) {
        waitElementVisible(locator);
        $(locator).click();
    }

    public EventsCalendarPage selectTypeOfEvent() {
        waitAttributeNotOpened(eventTypeButtonLocator);
        clickToButton(eventTypeButtonLocator);
        waitAttributeOpened(eventTypeButtonLocator);
        clickToButton(openWebinarSelectValueLocator);
        waitAttributeNotOpened(eventTypeButtonLocator);
        checkSelectedEventType();

        return this;
    }

    public void checkSelectedEventType() {
        assertThat($(eventTypeInputSelectedLocator).getText())
                .as("Error: incorrect event type selected")
                .isEqualTo("Открытые вебинары");
    }

    public void checkAllCardsForEventType() {
        String expectedType = "Открытый вебинар";

        List<WebElement> eventType = driver.findElements(eventTypeInCardLocator);
        for (WebElement types : eventType) {
            String eventDataPage = types.getText();

            assertThat(eventDataPage)
                    .as("Error: type events of card should be same as {}", expectedType)
                    .isEqualTo(expectedType);
        }
    }

    public void waitAttributeOpened(By locator) {
        assertThat(waitElementVisible(locator).getAttribute("class").contains("dod_new-events-dropdown_opened"))
                .as("Error: element has no attribute")
                .isTrue();
    }

    public void waitAttributeNotOpened(By locator) {
        assertThat(waitElementVisible(locator).getAttribute("class").contains("dod_new-events-dropdown_opened"))
                .as("Error: element has no attribute")
                .isFalse();
    }
}
