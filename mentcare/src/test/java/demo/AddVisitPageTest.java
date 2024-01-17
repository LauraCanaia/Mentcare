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

    @FindBy(xpath = "//a[@class='back-button']")
    private WebElement goBack;

    public AddVisitPageTest(WebDriver driver) {
        super(driver);
    }

    public WelcomePageTest compileVisitForm(WebDriver driver, String date, String time, String motivation)
    {
        this.date.click();
        this.date.sendKeys(date);
        this.time.click();
        this.time.sendKeys(time);
        this.motivation.click();
        this.motivation.sendKeys(motivation);
        this.submit.click();
        return new WelcomePageTest(driver);
    }

    public WelcomePageTest goBackToWelcome(WebDriver driver)
    {
        this.goBack.click();
        return new WelcomePageTest(driver);
    }
}
