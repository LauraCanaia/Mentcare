package demo.pageObjectsSetup;

import demo.pageObjectsSetup.MentcarePageTest;
import demo.pageObjectsSetup.PageDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPageTest extends PageDriver {

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

    public MentcarePageTest userSignUp(String name, String lastName, String fiscalCode, String password, WebDriver driver)
    {
        this.username.click();
        this.username.sendKeys(name);
        this.lastname.click();
        this.lastname.sendKeys(lastName);
        this.fiscalCode.click();
        this.fiscalCode.sendKeys(fiscalCode);
        this.password.click();
        this.password.sendKeys(password);
        this.register.click();
        return new MentcarePageTest(driver);
    }

    public MentcarePageTest returnToLogin(WebDriver driver)
    {
        this.login.click();
        return new MentcarePageTest(driver);
    }
}
