package ch.adv.lib.model;


public interface Builder {
    /**
     * Builds a session with a snapshot of the module content
     * @param module containing the snapshot data
     * @param snapshotDescription a helpful explanation for the snapshot
     * @return a session containing the snapshot data
     */
    Session build(ADVModule module, String snapshotDescription);

    /**
     * Builds a session with a snapshot of the module content
     * @param module containing the snapshot data
     * @return a session containing the snapshot data
     */
    Session build(ADVModule module);
}
