package ui.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ui.steps.AddRemoveElementsSteps;
import ui.util.ElementUtil;

@DisplayName("Add/Remove Elements")
//http://the-internet.herokuapp.com/add_remove_elements/
public class AddRemoveElementsTest extends AddRemoveElementsSteps {
    private static final String URL = "http://the-internet.herokuapp.com/add_remove_elements/";
    private static final AddRemoveElementsSteps addRemoveElementsSteps = new AddRemoveElementsSteps();

    @DisplayName("Проверка работы кнопки addElement")
    @Test
    public void addTest() {
        ElementUtil.goToUrl(URL);
        addRemoveElementsSteps.checkButton();
        addRemoveElementsSteps.clickAdd(5);
    }
    @DisplayName("Проверка работы кнопки Delete")
    @Test
    public void deleteTest(){
        ElementUtil.goToUrl(URL);
        addRemoveElementsSteps.checkDelete(10);
    }

}
