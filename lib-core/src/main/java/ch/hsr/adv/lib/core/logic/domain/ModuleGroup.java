package ch.hsr.adv.lib.core.logic.domain;

import ch.hsr.adv.commons.core.logic.domain.ADVElement;
import ch.hsr.adv.commons.core.logic.domain.ADVRelation;

import java.util.ArrayList;
import java.util.List;

/**
 * A module group wraps all module specific elements and allows us to address
 * multiple modules in a snapshot.
 */
public class ModuleGroup {

    private final List<ADVElement> elements = new ArrayList<>();
    private final List<ADVRelation> relations = new ArrayList<>();
    private final List<String> flags = new ArrayList<>();
    private final String moduleName;

    public ModuleGroup(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getModuleName() {
        return moduleName;
    }

    /**
     * Adds a element to the snapshot
     *
     * @param element element to add
     */
    public void addElement(ADVElement element) {
        elements.add(element);
    }

    /**
     * Adds a relation to the snapshot
     *
     * @param relation relation to add
     */
    public void addRelation(ADVRelation relation) {
        relations.add(relation);
    }

    public List<ADVElement> getElements() {
        return elements;
    }

    public List<ADVRelation> getRelations() {
        return relations;
    }

    public List<String> getFlags() {
        return flags;
    }

}
