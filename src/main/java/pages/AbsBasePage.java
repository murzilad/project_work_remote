package pages;

import common.AbsCommon;
import org.openqa.selenium.WebDriver;

public abstract class AbsBasePage extends AbsCommon {

    private final String BASE_URL = System.getProperty("base.url").endsWith("/") ?
            System.getProperty("base.url").replaceAll("/$", ""): //обрезает слеш в конце, если он есть
            System.getProperty("base.url");

    private String path = "";

    public AbsBasePage(WebDriver driver, String path) {
        super(driver);
        this.path = path.startsWith("/") ? path: "/" + path;
    }

    public AbsBasePage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(BASE_URL + path);
    }
}
