package ch.hsr.adv.lib.tree.logic.domain;

import ch.hsr.adv.commons.core.logic.domain.styles.ADVStyle;
import ch.hsr.adv.commons.tree.logic.domain.ADVGeneralTreeNode;

import java.util.ArrayList;
import java.util.List;

public class GeneralTreeTestNode implements ADVGeneralTreeNode<String> {

    private List<ADVGeneralTreeNode<String>> children;
    private String content;
    private ADVStyle style;

    public GeneralTreeTestNode() {
        this(null);
    }

    public GeneralTreeTestNode(String content) {
        this(content, null);
    }

    public GeneralTreeTestNode(String content, ADVStyle style) {
        this.style = style;
        this.content = content;
        children = new ArrayList<>();
    }

    public void addChild(GeneralTreeTestNode child) {
        children.add(child);
    }

    @Override
    public List<ADVGeneralTreeNode<String>> getChildren() {
        return children;
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
