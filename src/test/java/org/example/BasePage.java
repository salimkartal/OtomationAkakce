package org.example;

import driver.Driver;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BasePage {

    public void initialize() throws IOException{

        WebDriver webDriver= Driver.webDriver;
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

    }
}
