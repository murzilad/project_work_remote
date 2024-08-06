package events;

import components.popups.EducationMenuPopup;
import factory.WebDriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.EventsCalendarPage;
import pages.MainPage;

import java.net.MalformedURLException;

public class FilterEventsByType_Test {

    private final static Logger logger = LogManager.getLogger(FilterEventsByType_Test.class);
    private WebDriver driver = null;

    @BeforeEach
    public void init() throws MalformedURLException {
        logger.trace("Открытие браузера начато");
        driver = new WebDriverFactory().create();
        logger.trace("Открытие браузера завершено");
    }

    @AfterEach
    public void close() {
        logger.trace("Закрытие браузера начато");
        if (driver != null) {
            driver.quit();
        }
        logger.trace("Закрытие браузера завершено");
    }

    @Test
    public void filterEventsByType() {
        new MainPage(driver).open();

        EducationMenuPopup educationMenuPopup = new EducationMenuPopup(driver);

        educationMenuPopup
                .popupShouldNotBeVisible();

        educationMenuPopup.moveToMenu()
                .popupShouldBeVisible();

        educationMenuPopup.moveToMenu()
                .clickMyProfileButton();

        new EventsCalendarPage(driver)
                .pageHeaderShouldBeSameAs("Календарь мероприятий")
                .selectTypeOfEvent()
                .checkAllCardsForEventType();

    }
}
