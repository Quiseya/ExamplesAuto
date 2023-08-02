package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

public interface AddRemoveElementsPage {
    @FindBy(name = "Add Element")
    By ADD_BTN = By.xpath("//button[@onclick=\"addElement()\"]");
    @FindBy(name = "")
    By DELETE_BTN = By.xpath("//button[@onclick=\"deleteElement()\"]");
}
