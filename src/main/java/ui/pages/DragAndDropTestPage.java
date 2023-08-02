package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

public interface DragAndDropTestPage {
    @FindBy(name = "column-a")
    By A_LETTER = By.xpath("//div/header[contains(text(),\"A\")]/..");
    @FindBy(name = "column-b")
    By B_LETTER = By.xpath("//div/header[contains(text(),\"B\")]/..");
}
