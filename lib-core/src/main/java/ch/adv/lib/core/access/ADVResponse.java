package ch.adv.lib.core.access;

/**
 * Encapsulates a response from the ADVCore UI.
 */
class ADVResponse {

    private final ProtocolCommand command;
    private final String exceptionMessage;

    ADVResponse(ProtocolCommand command, String exceptionMessage) {
        this.command = command;
        this.exceptionMessage = exceptionMessage;
    }

    ProtocolCommand getCommand() {
        return command;
    }

    String getExceptionMessage() {
        return exceptionMessage;
    }
}
