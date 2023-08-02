package ui.steps;

import com.beust.ah.A;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import ui.util.ElementUtil;
import ui.util.MainUtil;

public class ContextMenuSteps extends MainUtil {

    Actions actions = new Actions(getDriver());

    @Step("Нажать правой кнопкой мыши на xpath {0}")
    public void click(By xpath) {
        WebElement elementLocator = getDriver().findElement(xpath);
        actions.contextClick(elementLocator).perform();
    }

    @Step("Перетащить элемент {0} на место {1}")
    public void move(By xpath1,By xpath2) {
        WebElement fromElement = getDriver().findElement(xpath1);
        WebElement toElement = getDriver().findElement(xpath2);
        actions.clickAndHold(fromElement).
                moveToElement(toElement).
                release(toElement).build().perform();

    }
}
