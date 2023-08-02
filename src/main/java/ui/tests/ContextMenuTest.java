package ui.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import ui.steps.ContextMenuSteps;
import ui.util.ElementUtil;


@DisplayName("Context Menu")

public class ContextMenuTest extends ContextMenuSteps {
    private final ContextMenuSteps contextMenuSteps = new ContextMenuSteps();

    private static final String URL = "http://the-internet.herokuapp.com/context_menu";

    @DisplayName("Отображение контекстного меню")
    @Test
    protected void test() {
        ElementUtil.goToUrl(URL);
        contextMenuSteps.click(By.xpath("//div[@id=\"hot-spot\"]"));
        getDriver().switchTo().alert().accept();
    }

}
