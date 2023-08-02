package ui.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import ui.util.ElementUtil;
import ui.util.MainUtil;

@DisplayName("CheckboxesTest")
public class CheckboxesTest extends MainUtil {
    private static final String URL = "http://the-internet.herokuapp.com/checkboxes";

    @DisplayName("Нажатие всех чек-боксов")
    @Test
    protected void test() {
        ElementUtil.goToUrl(URL);
        int count = getDriver().findElements(By.tagName("input")).size();
        for (int i = 0; i < count; i++) {
            ElementUtil.click(By.tagName("input"));
        }
    }
}
