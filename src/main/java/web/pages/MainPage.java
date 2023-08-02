package web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

public interface MainPage {
    @FindBy(name = "A/B Testing")
    By AB_TEST_LINK = By.xpath("//a[@href=\"/abtest\"]");
}
