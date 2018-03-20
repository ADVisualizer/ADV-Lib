package ch.adv.lib.model;


public interface Builder {
    Session build(ADVModule module, String snapshotDescription);
    Session build(ADVModule module);
}
