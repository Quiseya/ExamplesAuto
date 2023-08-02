package ui.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ui.constant.Constant;
import ui.pages.MainPage;
import ui.util.ElementUtil;
import ui.util.MainUtil;
//http://the-internet.herokuapp.com/

public class AbTest extends MainUtil implements MainPage {

    @DisplayName("A/B Testing")
    @Test()
    public void abTest() {
        ElementUtil.goToUrl(Constant.MAIN_URL);
        ElementUtil.click(AB_TEST_LINK);
        ElementUtil.checkUrls("http://the-internet.herokuapp.com/abtest");
    }

}
