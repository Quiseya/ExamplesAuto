package web.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import web.util.network.NetworkEntity;
import web.util.network.NetworkHelper;

@Data
@AllArgsConstructor
public class Logger {

    private NetworkHelper networkHelper;

    private NetworkEntity networkEntity;

}
