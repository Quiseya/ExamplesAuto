package ui.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import ui.util.ElementUtil;
import ui.util.MainUtil;

import java.util.HashMap;
import java.util.Map;

@DisplayName("Dropdown List")
public class DropdownListTest extends MainUtil {
    private static final String URL = "http://the-internet.herokuapp.com/dropdown";

    private static final Map<Integer, String> expectedValue = new HashMap<>();

    private static void options() {
        expectedValue.put(1, "Option 1");
        expectedValue.put(2, "Option 2");
    }

    @DisplayName("Последовательность значений в выпадающем списке")
    @Test
    protected void test() {
        options();
        ElementUtil.goToUrl(URL);
        for (int i = 1; i <= 2; i++) {
            ElementUtil.click(By.xpath("//select[@id=\"dropdown\"]"));
            ElementUtil.contains("//option[@value=\"" + i + "\"]", expectedValue.get(i));
        }

    }

}
