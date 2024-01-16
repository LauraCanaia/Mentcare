package demo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PageDriver {

    private WebDriver driver;

    public PageDriver(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}