package ch.hsr.adv.lib.core.logic;


import ch.hsr.adv.lib.core.logic.domain.ModuleGroup;

/**
 * Builds a {@link ch.hsr.adv.lib.core.logic.domain.Session}
 * <p>
 * Abstraction Interface of the strategy pattern. Every Module supplies a
 * concrete strategy to be used.
 */
public interface Builder {

    /**
     * Builds a session with a snapshot of the module content
     *
     * @param module  containing the snapshot data
     * @return builded module group
     */
    ModuleGroup build(ADVModule module);

}
