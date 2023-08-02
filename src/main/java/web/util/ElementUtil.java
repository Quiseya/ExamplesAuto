package web.util;

import io.netty.channel.ConnectTimeoutException;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.fail;
import static web.util.WebConfig.BASE_CONFIG;

public class ElementUtil extends MainUtil {

    private static final Object clock = new Object();

    private static final String httpConnectionTimeout = BASE_CONFIG.httpConnectionTimeout();


    @Step("Переход по ссылке")
    public static void goToUrl(String url) {
        try {
            connect(url);
        } catch (Exception e) {
            if (e instanceof ConnectTimeoutException) {
                try {
                    connect(url);
                } catch (Exception t) {
                    if (t instanceof ConnectTimeoutException) {
                        saveScreenshot();
                        fail("Ошибка соединения с сервером по адресу = " + url);
                    } else {
                        fail("Ошибка, что-то пошло не так... Результат ошибки = " + e.getMessage());
                    }
                }
            } else {
                try {
                    connect(url);
                } catch (Exception t) {
                    saveScreenshot();
                    fail("Ошибка, что-то пошло не так... Результат ошибки = " + e.getMessage());
                }

            }
        }
    }

    private static void connect(String url) {
        var config = RestAssured.config = RestAssuredConfig.config().httpClient(HttpClientConfig.httpClientConfig().
                setParam("http.connection.timeout", Integer.parseInt(httpConnectionTimeout)).
                setParam("http.socket.timeout", Integer.parseInt(httpConnectionTimeout)));
        given().config(config).get(url);
        getDriver().get(url);
        System.out.println("Стенд = " + getDriver().getCurrentUrl());
        getDriver().getPageSource();
    }

    @Step("Скриншот")
    @Attachment(value = "Большая картинка, мяу")
    public static byte[] saveScreenshot() {
        var scrFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
        return scrFile;
    }

    public static void generateAllureEnvironment() {
        File source = new File("src/main/resources/environment.properties");
        File dest = new File("target/allure-results");

        var list = List.of("STAND=" + BASE_CONFIG.testUrl());
        try {
            synchronized (clock) {
                var patch = dest.getAbsoluteFile().getCanonicalPath();
                FileUtils.copyToDirectory(source, dest);
                FileUtils.writeLines(new File(patch + "environment.properties"), list);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}








