package demo.pageObjectsSetup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditVisitPageTest extends PageDriver {

    @FindBy(xpath = "//input[@name='date']")
    private WebElement date;

    @FindBy(xpath = "//input[@name='time']")
    private WebElement time;

    @FindBy(xpath="//input[@name='motivation']")
    private WebElement motivation;

    @FindBy(xpath="//button[@type='submit']")
    private WebElement submit;

    @FindBy(xpath = "//button[@type='button']")
    private WebElement goBack;

    public EditVisitPageTest(WebDriver driver) {
        super(driver);
    }

    public void editDate(String date)
    {
        this.date.click();
        this.date.clear();
        this.date.sendKeys(date);
    }

    public void editTime(String time)
    {
        this.time.click();
        this.time.clear();
        this.time.sendKeys(time);
    }

    public void editMotivation(String motivation)
    {
        this.motivation.click();
        this.motivation.clear();
        this.motivation.sendKeys(motivation);
    }

    public WelcomePageTest submitEdited(WebDriver driver)
    {
        this.submit.click();
        return new WelcomePageTest(driver);
    }

    public WelcomePageTest goBackButton(WebDriver driver)
    {
        this.goBack.click();
        return new WelcomePageTest(driver);
    }
}
