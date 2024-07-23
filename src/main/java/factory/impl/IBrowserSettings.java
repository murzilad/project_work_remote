package factory.impl;

import org.openqa.selenium.remote.AbstractDriverOptions;

public interface IBrowserSettings {

    String isBrowserMode = System.getProperty("mode", "fullscreen");

    AbstractDriverOptions settings();
}
