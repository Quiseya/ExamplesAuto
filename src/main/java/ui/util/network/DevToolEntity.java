package ui.util.network;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.devtools.v108.network.model.Headers;

import java.util.Map;

@Getter
@Setter
@EqualsAndHashCode
public class DevToolEntity {

    private String url;

    private String statusCode;

    private Map<String, Object> headerResponse;

    private String statusText;

    private Headers requestHeaders;


    @Override
    public String toString() {
        return  "\n" +
                "{" + "\n" +
                "url='" + url + '\'' + "\n" +
                "statusCode='" + statusCode + '\'' + "\n" +
                "headerResponse=" + headerResponse + "\n" +
                "statusText='" + statusText + '\'' + "\n" +
                "requestHeaders=" + requestHeaders + "\n" +
                '}' + "\n" +
                "___________________________________________" + "\n";
    }
}
