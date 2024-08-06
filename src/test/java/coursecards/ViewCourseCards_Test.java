package coursecards;

import components.CoursesFilterComponent;
import data.menu.CoursesMenuData;
import factory.WebDriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.InfoCardCoursesPage;
import pages.MainPage;

import java.net.MalformedURLException;

public class ViewCourseCards_Test {

    private final static Logger logger = LogManager.getLogger(ViewCourseCards_Test.class);
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
    public void viewCourseCards() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();

        mainPage
                .clickCoursesCategoriesMenu(CoursesMenuData.TESTING)
                .pageHeaderShouldBeSameAs("Каталог");

        new CoursesFilterComponent(driver)
                .checkCheckboxFilterStateShouldBeSameAs(CoursesMenuData.TESTING, true);

        new InfoCardCoursesPage(driver)
                .checkCardInfo();
    }
}
