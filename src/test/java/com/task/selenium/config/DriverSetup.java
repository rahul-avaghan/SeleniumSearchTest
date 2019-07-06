package com.task.selenium.config;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * The DriverSetup Interface has the declarations of methods required to set the capabilities
 * 
 * @author Sunanda Jakeral
 * @version 1.0
 * @since 06/07/2019
 */

public interface DriverSetup {
    RemoteWebDriver getWebDriverObject(DesiredCapabilities capabilities);
}