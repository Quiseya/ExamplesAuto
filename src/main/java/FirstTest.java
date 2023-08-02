import web.util.ElementUtil;
import web.util.MainUtil;

public class FirstTest extends MainUtil {
    @org.junit.jupiter.api.Test
    public void testUrl() {
        ElementUtil.goToUrl("http://the-internet.herokuapp.com/");
    }
}
