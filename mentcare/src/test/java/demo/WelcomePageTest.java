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

    @FindBy(xpath = "//div[@class='welcome-container']//table//tbody//tr[1]//td[1]")
    private WebElement firstVisitDate;

    @FindBy(xpath = "//div[@class='welcome-container']//table//tbody//tr[1]//td[2]")
    private WebElement firstVisitTime;
    @FindBy(xpath = "//div[@class='welcome-container']//table//tbody//tr[1]//td[3]")
    private WebElement firstVisitMotivation;

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

    public MentcarePageTest logoutAction(WebDriver driver)
    {
        this.logout.click();
        return new MentcarePageTest(driver);
    }

    public void deleteVisit()
    {
        this.delete.click();
    }

    public EditVisitPageTest editButton(WebDriver driver)
    {
        this.modify.click();
        return new EditVisitPageTest(driver);
    }

    public String getFirstVisitDateToString()
    {
        return this.firstVisitDate.getText();
    }

    public String getFirstVisitTimeToString()
    {
        return this.firstVisitTime.getText();
    }

    public String getFirstVisitMotivationToString()
    {
        return this.firstVisitMotivation.getText();
    }
}
