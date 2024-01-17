package demo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditVisitPageTest extends PageDriver{

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
}
