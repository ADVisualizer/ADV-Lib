package ch.adv.lib.core.access;

/**
 * Encapsulates a request to the ADV UI.
 */
class ADVRequest {

    private final ProtocolCommand command;
    private final String json;

    ADVRequest(ProtocolCommand command) {
        this(command, null);
    }

    ADVRequest(ProtocolCommand command, String json) {
        this.command = command;
        this.json = json;
    }

    ProtocolCommand getCommand() {
        return command;
    }

    String getJson() {
        return json;
    }
}
