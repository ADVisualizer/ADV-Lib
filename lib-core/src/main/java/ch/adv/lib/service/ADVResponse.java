package ch.adv.lib.service;

public class ADVResponse {

    private final ProtocolCommand command;
    private final String exceptionMessage;

    public ADVResponse(ProtocolCommand command, String exceptionMessage) {
        this.command = command;
        this.exceptionMessage = exceptionMessage;
    }

    public ProtocolCommand getCommand() {
        return command;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }
}
