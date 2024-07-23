package pages;

import data.menu.CoursesMenuData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends AbsBasePage {

    public MainPage(WebDriver driver) {
        super(driver, "/");
    }

    public CatalogPage clickCoursesCategoriesMenu(CoursesMenuData coursesMenuData) {
        String categoriesMenuLocatorTemplate = "//div[./h2[text()='Авторские онлайн‑курсы для профессионалов']]/following-sibling::section//*[text()='%s']";

        String locator = String.format(categoriesMenuLocatorTemplate, coursesMenuData.getName());
        $(By.xpath(locator)).click();

        return new CatalogPage(driver);
    }

}
