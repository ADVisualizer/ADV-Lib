package ch.adv.lib.core.access;

import com.google.gson.annotations.SerializedName;

/**
 * Available commands for the ADVCore protocol.
 */
enum ProtocolCommand {

    @SerializedName("TRANSMIT")
    TRANSMIT("TRANSMIT"),

    @SerializedName("ACK")
    ACKNOWLEDGE("ACK"),

    @SerializedName("EXCEPTION")
    EXCEPTION("EXCEPTION"),

    @SerializedName("END")
    END("END");

    private final String command;

    ProtocolCommand(String command) {
        this.command = command;
    }
}