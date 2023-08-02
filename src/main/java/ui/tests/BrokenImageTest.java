package ui.tests;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ui.util.ElementUtil;
import ui.util.MainUtil;

import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

@DisplayName("BrokenImage")
public class BrokenImageTest extends MainUtil {
    @DisplayName("Наличие повреждённых изображений на странице")
    @Test
    protected void test() {
        String URL = "http://the-internet.herokuapp.com/broken_images";
        String status = "passed";
        Integer iBrokenImageCount = 0;

        ElementUtil.goToUrl(URL);
        try {
            iBrokenImageCount = 0;
            List<WebElement> image_list = getDriver().findElements(By.tagName("img"));
            System.out.println("На странице есть " + image_list.size() + " изображений");
            for (WebElement img : image_list) {
                if (img != null) {
                    CloseableHttpClient client = HttpClientBuilder.create().build();
                    HttpGet request = new HttpGet(img.getAttribute("src"));
                    CloseableHttpResponse response = client.execute(request);
                    if (response.getCode() != 200) {
                        System.out.println(img.getAttribute("outerHTML") + " повреждено. Статус: "+ response.getCode());
                        iBrokenImageCount++;
                    }
                }
            }
        } catch (Exception e)
        {
            e.printStackTrace();
            fail(e.getMessage());
            System.out.println(e.getMessage());
        }
        System.out.println("На странице " + URL + " есть " + iBrokenImageCount + " повреждённых изображений");
    }



}