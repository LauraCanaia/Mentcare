package demo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddVisitPageTest extends PageDriver{

    @FindBy(xpath = "//input[@name='date']")
    private WebElement date;

    @FindBy(xpath = "//input[@name='time']")
    private WebElement time;

    @FindBy(xpath="//input[@name='motivation']")
    private WebElement motivation;

    @FindBy(xpath="//button[@type='submit']")
    private WebElement submit;

    public AddVisitPageTest(WebDriver driver) {
        super(driver);
    }
}
