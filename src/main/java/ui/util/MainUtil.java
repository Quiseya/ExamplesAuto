package ui.util;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevToolsException;
import org.openqa.selenium.remote.http.ConnectionFailedException;
import ui.constant.Constant;
import ui.util.network.NetworkHelper;

import java.nio.file.Paths;
import java.time.Duration;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.aspectj.bridge.MessageUtil.fail;
import static ui.util.WebConfig.BASE_CONFIG;

public class MainUtil {

    private static WebDriver driver;

    private static ui.util.Logger logger;

    public MainUtil() {
        starter();
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void setDriver(ChromeDriver driver) {
        MainUtil.driver = driver;
    }

    @BeforeEach
    void init() {
        starter();
    }


    @AfterEach
    void loggerAfter() {
        if (BASE_CONFIG.logNetwork()) {
            logger.getNetworkHelper().checkStatusCodeInNetworkConsole(logger.getNetworkEntity(), Constant.CONSOLE_ERRORS_500);
        }
    }


    @AfterEach
    void after() {
        if (driver != null) {
            getDriver().quit();
            setDriver(null);
        }
        ElementUtil.generateAllureEnvironment();
    }

    private void starter() {
        if (driver == null) {
            var downloadDir = Paths.get("target").toAbsolutePath().toString();
            ChromeOptions options = new ChromeOptions();
            HashMap<String, Object> chromePrefs = new HashMap<>();
            chromePrefs.put("profile.default_content_settings.popups", 0);
            chromePrefs.put("download.default_directory", downloadDir);
            options.setExperimentalOption("prefs", chromePrefs);
            var os = System.getProperty("os.name").toLowerCase();
            if (os.contains("mac os") || os.contains("windows")) {

                WebDriverManager.chromedriver().setup();
                options.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(options);
                if (os.contains("windows")) {
                    options.addArguments("--window-size=2440, 1900");
                } else {
                    driver.manage().window().maximize();
                }
                Logger.getLogger("org.openqa.selenium").setLevel(Level.SEVERE);
            } else {
                options.addArguments("--remote-allow-origins=*");
                options.addArguments("--headless");
                options.addArguments("--window-size=2440, 1900");
                options.addArguments("--session-override true");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("user-agent=\"Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)\"");
                options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(options);
                Logger.getLogger("org.openqa.selenium").setLevel(Level.SEVERE);
            }
            if (BASE_CONFIG.logNetwork()) {
                try {
                    logger = starterLog();
                } catch (DevToolsException | ConnectionFailedException e) {
                    ElementUtil.saveScreenshot();
                    after();
                    fail("Ошибка DevTools = " + e.getMessage());
                }
            }
            driver.manage().deleteAllCookies();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        }
    }

    private static ui.util.Logger starterLog() {
        var network = new NetworkHelper();
        var entity = network.starter();
        return new ui.util.Logger(network, entity);
    }

}
