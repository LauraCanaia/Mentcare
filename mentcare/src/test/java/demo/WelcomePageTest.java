package demo;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class WelcomePageTest extends PageDriver {

    @FindBy(xpath = "//button[contains(text(),'Modifica')]")
    private WebElement modify;

    @FindBy(xpath = "//button[contains(text(),'Elimina')]")
    private WebElement delete;

    @FindBy(xpath = "//button[contains(text(),'Logout')]")
    private WebElement logout;

    @FindBy(xpath = "//button[contains(text(),'Aggiungi Visita')]")
    private WebElement addVisit;

    public WelcomePageTest(WebDriver driver) {
        super(driver);
    }

    public AddVisitPageTest addVisit(WebDriver driver)
    {
        this.addVisit.click();
        return new AddVisitPageTest(driver);
    }

    public Integer findTableDimension(WebDriver driver)
    {
        List<WebElement> rows = driver.findElements(By.xpath("//div[@class='welcome-container']//table//tbody//tr"));
        return rows.size();
    }
}
