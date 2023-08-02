package ui.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ui.pages.DragAndDropTestPage;
import ui.steps.ContextMenuSteps;
import ui.util.ElementUtil;
import ui.util.MainUtil;
import ui.util.WaitHelper;

@DisplayName("DragAndDropTest")
public class DragAndDropTest extends MainUtil implements DragAndDropTestPage {
    private static final String URL = "http://the-internet.herokuapp.com/drag_and_drop";

    private static final ContextMenuSteps contextMenuSteps = new ContextMenuSteps();

    @DisplayName("Перетаскивание элементов")
    @Test
    protected void test() throws InterruptedException {
        ElementUtil.goToUrl(URL);
        contextMenuSteps.move(A_LETTER, B_LETTER);
    }
}
