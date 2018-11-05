package ch.hsr.adv.lib.tree.logic.binaryarraytree.domain;

import ch.hsr.adv.commons.core.logic.domain.styles.ADVStyle;
import ch.hsr.adv.commons.core.logic.domain.styles.presets.ADVDefaultElementStyle;
import ch.hsr.adv.commons.tree.logic.domain.ADVTreeNode;

/**
 * Class to symbol an array entry with the information for the ui
 */
public class ArrayTreeNode implements ADVTreeNode<String> {

    private ADVStyle style;
    private String content;

    public ArrayTreeNode(Object arrayElement) {
        this.content = arrayElement.toString();
        style = new ADVDefaultElementStyle();
    }

    @Override
    public ADVStyle getStyle() {
        return style;
    }

    @Override
    public String getContent() {
        return content;
    }
}
