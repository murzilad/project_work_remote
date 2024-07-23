package components;

import common.AbsCommon;
import data.menu.CoursesMenuData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.assertj.core.api.Assertions.assertThat;


public class CoursesFilterComponent extends AbsCommon {

    public CoursesFilterComponent(WebDriver driver) {
        super(driver);
    }

    private String filterMenuItemLocatorTemplate = "//*[@aria-hidden='false']//div[./label='%s']";

    public CoursesFilterComponent checkCheckboxFilterStateShouldBeSameAs(CoursesMenuData coursesMenuData, boolean isExpectedState) {
        String locator = String.format(filterMenuItemLocatorTemplate, coursesMenuData.getName());

        assertThat(waiters.waitForCondition(ExpectedConditions.attributeToBe(
                $(By.xpath(locator)),
                "value",
                String.valueOf(isExpectedState)
        )))
                .as("Error: other filter state")
                .isTrue();

        return this;

    }
}
