package ui.steps;

import io.qameta.allure.Step;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.By;
import ui.pages.AddRemoveElementsPage;
import ui.util.ElementUtil;
import ui.util.MainUtil;

import static org.junit.jupiter.api.Assertions.fail;

public class AddRemoveElementsSteps extends MainUtil implements AddRemoveElementsPage {

    @Step("На странице отображается только одна кнопка addElement")
    public void checkButton() {
        ElementUtil.searchCount(ADD_BTN, 1);
    }

    @Step("При нажатии кнопки addElement {0} раз, кнопка Delete добавляется столько же ({0}) раз")
    public void clickAdd(Integer count) {
        for (int i = 0; i < count; i++) {
            ElementUtil.click(ADD_BTN);
        }
        ElementUtil.searchCount(DELETE_BTN, count);

    }

    @Step("При нажатии кнопки Delete, элемент удаляется")
    public void checkDelete(Integer count) {
        for (int i = 0; i < count; i++) {
            ElementUtil.click(ADD_BTN);
        }
        for (int i = 0; i < count - 1; i++) {
            ElementUtil.click(DELETE_BTN);
        }
        int countDeleteBtn = getDriver().findElements(DELETE_BTN).size();
        if (countDeleteBtn != 1) {
            fail("Фактическое количество элементов: " + countDeleteBtn + " не равно ожиждаемому: " + (count - 1));
        }
    }
}
