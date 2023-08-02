package ui.util;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;

@Config.Sources({"classpath:${env}.properties"})
public interface WebConfig extends Config {

    WebConfig BASE_CONFIG = ConfigFactory.create(WebConfig.class, System.getenv(), System.getProperties());

    @Key("test.url")
    @DefaultValue(" http://the-internet.herokuapp.com/")
    String testUrl();



    @Key("http.connection.timeout")
    @DefaultValue("3000")
    String httpConnectionTimeout();

    @Key("enabled.test")
    @DefaultValue("false")
    boolean enableTest();

    @Key("log.network")
    @DefaultValue("false")
    boolean logNetwork();

}