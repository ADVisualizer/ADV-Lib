package ch.adv.lib.core.service;

/**
 * Encapsulates a request to the ADV UI.
 */
public class ADVRequest {

    private final ProtocolCommand command;
    private final String json;

    private final transient GsonProvider gsonProvider = new GsonProvider();

    public ADVRequest(ProtocolCommand command) {
        this(command, null);
    }

    public ADVRequest(ProtocolCommand command, String json) {
        this.command = command;
        this.json = json;
    }

    /**
     * @return the serialized string representation of this class
     */
    public String toJson() {
        return gsonProvider.getMinifier().toJson(this);
    }

    public ProtocolCommand getCommand() {
        return command;
    }

    public String getJson() {
        return json;
    }
}
