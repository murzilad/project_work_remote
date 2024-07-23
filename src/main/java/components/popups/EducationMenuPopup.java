package components.popups;

import common.AbsCommon;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.assertj.core.api.Assertions.assertThat;

public class EducationMenuPopup extends AbsCommon implements IPopup {

    public EducationMenuPopup(WebDriver driver) {
        super(driver);
    }

    private By educationMenuPopupLocator = By.xpath("//div[contains(@class, 'fzHlJa')]/div");
    private By navEducationMenuLocator = By.xpath("//span[@title = 'Обучение']/following-sibling::div/div");
    private By eventsCalendarButtonLocator = By.xpath("//a[contains(text(), 'Календарь мероприятий')]");

    Actions actions = new Actions(driver);

    @Override
    public void popupShouldNotBeVisible() {
        assertThat(waiters.waitForCondition(ExpectedConditions.invisibilityOfElementLocated(educationMenuPopupLocator)))
                .as("Error: element is present in the dom")
                .isTrue();
    }

    @Override
    public void popupShouldBeVisible() {
        assertThat(waiters.waitForCondition(ExpectedConditions.visibilityOfElementLocated(educationMenuPopupLocator)))
                .as("Error: element is missing in the dom")
                .isTrue();
    }

    public EducationMenuPopup moveToMenu() {
        waitElementVisible(navEducationMenuLocator);
        actions.moveToElement($(navEducationMenuLocator)).perform();
        return this;
    }

    public EducationMenuPopup clickMyProfileButton() {
        waitElementVisible(eventsCalendarButtonLocator);
        $(eventsCalendarButtonLocator).click();
        return this;
    }
}
