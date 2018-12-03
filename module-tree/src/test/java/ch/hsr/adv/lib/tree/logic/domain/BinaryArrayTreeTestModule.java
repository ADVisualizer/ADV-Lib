package ch.hsr.adv.lib.tree.logic.domain;

import ch.hsr.adv.lib.tree.logic.binaryarraytree.BinaryArrayTreeModule;

import java.util.ArrayList;

public class BinaryArrayTreeTestModule<T> extends BinaryArrayTreeModule<T> {
    public BinaryArrayTreeTestModule(T[] nodeArray, String sessionName) {
        super(nodeArray, sessionName);
    }

    public BinaryArrayTreeTestModule(ArrayList<T> nodeList,
                                     String sessionName) {
        super(nodeList, sessionName);
    }

    @Override
    public T[] getModuleNodeArray() {
        return super.getModuleNodeArray();
    }
}
