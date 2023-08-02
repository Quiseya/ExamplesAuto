package web.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import web.constant.Constant;
import web.pages.MainPage;
import web.util.ElementUtil;
import web.util.MainUtil;


public class AbTest extends MainUtil implements MainPage {

    @DisplayName("A/B Testing")
    @Test()
    public void abTest() {
        ElementUtil.goToUrl(Constant.MAIN_URL);
        ElementUtil.click(AB_TEST_LINK);
        ElementUtil.checkUrls("http://the-internet.herokuapp.com/abtest");
    }

}
