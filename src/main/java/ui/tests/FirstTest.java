package ui.tests;

import ui.util.ElementUtil;
import ui.util.MainUtil;

import java.lang.reflect.Array;

public class FirstTest extends MainUtil {
    @org.junit.jupiter.api.Test
    public void testUrl() {
        ElementUtil.goToUrl("http://the-internet.herokuapp.com/");
    }

}
