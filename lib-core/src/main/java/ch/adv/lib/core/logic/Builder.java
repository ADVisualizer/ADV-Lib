package ch.adv.lib.core.logic;


import ch.adv.lib.core.app.ADVModule;
import ch.adv.lib.core.domain.Session;

/**
 * Builds a {@link Session}
 */
public interface Builder {

    /**
     * Builds a session with a snapshot of the module content
     *
     * @param module              containing the snapshot data
     * @param snapshotDescription a helpful explanation for the snapshot
     * @return a session containing the snapshot data
     */
    Session build(ADVModule module, String snapshotDescription);

    /**
     * Builds a session with a snapshot of the module content
     *
     * @param module containing the snapshot data
     * @return a session containing the snapshot data
     */
    Session build(ADVModule module);
}
