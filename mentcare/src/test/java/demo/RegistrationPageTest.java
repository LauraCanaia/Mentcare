package demo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPageTest extends PageDriver{

    @FindBy(xpath = "//input[@id='username']")
    private WebElement username;

    @FindBy(xpath = "//input[@id='lastname']")
    private WebElement lastname;

    @FindBy(xpath = "//input[@id='fiscalcode']")
    private WebElement fiscalCode;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement password;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement register;

    @FindBy(xpath = "//a[contains(text(),'Login here')]")
    private WebElement login;

    public RegistrationPageTest(WebDriver driver) {
        super(driver);
    }
}
