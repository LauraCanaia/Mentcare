package demo.pageObjectsSetup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MentcarePageTest extends PageDriver {


    @FindBy(xpath = "//input[@type='text']")
    private WebElement username;

    @FindBy(xpath = "//input[@type='password']")
    private WebElement password;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement login;

    @FindBy(xpath = "//a[contains(text(),\"Don't have an account? Register here\")]")
    private WebElement register;

    @FindBy(xpath = "")
    private WebElement loginError;

    public MentcarePageTest(WebDriver driver) {
        super(driver);
    }

    public WelcomePageTest SubmitCredentials(String username, String password, WebDriver driver)
    {
        this.username.clear();
        this.password.clear();
        this.username.sendKeys(username);
        this.password.sendKeys(password);
        login.click();
        if(driver.getTitle().equals("Mentcare - Login"))
            return null;
        return new WelcomePageTest(driver);
    }

    public RegistrationPageTest clickRegistration(WebDriver driver)
    {
        register.click();
        return new RegistrationPageTest(driver);
    }


}
