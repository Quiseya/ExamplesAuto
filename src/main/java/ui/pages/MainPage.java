package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
//http://the-internet.herokuapp.com/
public interface MainPage {
    @FindBy(name = "A/B Testing")
    By AB_TEST_LINK = By.xpath("//a[@href=\"/abtest\"]");
}
