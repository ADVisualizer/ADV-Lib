package ch.adv.lib.service;

public class ADVResponse {

    private final ProtocolCommand command;
    private final String exceptionMessage;
    private final String json;

    public ADVResponse(ProtocolCommand command, String json, String exceptionMessage) {
        this.command = command;
        this.json = json;
        this.exceptionMessage = exceptionMessage;
    }

    public ProtocolCommand getCommand() {
        return command;
    }

    public String getJson() {
        return json;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }
}
