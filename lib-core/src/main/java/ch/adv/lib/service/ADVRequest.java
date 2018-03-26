package ch.adv.lib.service;

import javafx.scene.control.ProgressBar;

public class ADVRequest {

    private final ProtocolCommand command;
    private final String json;

    private transient final GsonProvider gsonProvider = new GsonProvider();

    public ADVRequest(ProtocolCommand command) {
        this(command, null);
    }

    public ADVRequest(ProtocolCommand command, String json) {
        this.command = command;
        this.json = json;
    }

    public String toJson() {
        return gsonProvider.getMinifier().toJson
                (this);
    }
}
