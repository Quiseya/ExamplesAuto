package ui.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import ui.util.network.NetworkEntity;
import ui.util.network.NetworkHelper;

@Data
@AllArgsConstructor
public class Logger {

    private NetworkHelper networkHelper;

    private NetworkEntity networkEntity;

}
