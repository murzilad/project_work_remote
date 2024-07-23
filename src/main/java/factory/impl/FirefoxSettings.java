package factory.impl;

import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;

public class FirefoxSettings implements IBrowserSettings {

    @Override
    public AbstractDriverOptions settings() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        if (isBrowserMode.equals("fullscreen")) {
            firefoxOptions.addArguments("--start-maximized");
        } else if (isBrowserMode.equals("kiosk")) {
            firefoxOptions.addArguments("--kiosk");
        }
        return firefoxOptions;
    }
}
