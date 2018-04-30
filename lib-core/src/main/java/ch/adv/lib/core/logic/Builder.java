package ch.adv.lib.core.logic;


import ch.adv.lib.core.logic.domain.Session;

/**
 * Builds a {@link Session}
 * <p>
 * Abstraction Interface of the strategy pattern. Every Module supplies a
 * concrete strategy to be used.
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

}
