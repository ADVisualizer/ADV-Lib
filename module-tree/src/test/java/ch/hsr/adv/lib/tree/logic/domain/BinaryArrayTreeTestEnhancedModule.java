package ch.hsr.adv.lib.tree.logic.domain;

import ch.hsr.adv.lib.tree.logic.binaryarraytree.BinaryArrayTreeEnhancedModule;

import java.util.ArrayList;

public class BinaryArrayTreeTestEnhancedModule<T> extends BinaryArrayTreeEnhancedModule<T> {
    public BinaryArrayTreeTestEnhancedModule(T[] nodeArray, String sessionName) {
        super(nodeArray, sessionName);
    }

    public BinaryArrayTreeTestEnhancedModule(ArrayList<T> nodeList,
                                             String sessionName) {
        super(nodeList, sessionName);
    }

    @Override
    public T[] getModuleNodeArray() {
        return super.getModuleNodeArray();
    }
}
