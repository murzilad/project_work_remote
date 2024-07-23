package factory.impl;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;

public class ChromeSettings implements IBrowserSettings {

    @Override
    public AbstractDriverOptions settings() {
        ChromeOptions chromeOptions = new ChromeOptions();
        if (isBrowserMode.equals("fullscreen")) {
            chromeOptions.addArguments("--start-maximized");
        } else if (isBrowserMode.equals("kiosk")) {
            chromeOptions.addArguments("--kiosk");
        }
        return chromeOptions;
    }
}
