package demo;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WelcomePageTest extends PageDriver {

    @FindBy(xpath = "//button[contains(text(),'Modifica')]")
    private WebElement modify;

    @FindBy(xpath = "//button[contains(text(),'Elimina')]")
    private WebElement delete;

    @FindBy(xpath = "//button[contains(text(),'Logout')]")
    private WebElement logout;

    @FindBy(xpath = "\t//button[contains(text(),'Aggiungi Visita')]")
    private WebElement addVisit;

    public WelcomePageTest(WebDriver driver) {
        super(driver);
    }
}
