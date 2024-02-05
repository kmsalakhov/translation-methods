package Parser;

import java.util.Arrays;
import java.util.List;

public class Tree {
    private final String node;
    private final List<Tree> children;

    public Tree(String node, Tree... children) {
        this.node = node;
        this.children = Arrays.asList(children);
    }

    public Tree(String node, List<Tree> children) {
        this.node = node;
        this.children = children;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(node);
        for (Tree tree : children) {
            sb.append(' ');
            sb.append(tree.toString());
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Tree anotherTree) {
            if (anotherTree.node.equals(node) && anotherTree.children.size() == children.size()) {
                for (int i = 0; i < children.size(); i++) {
                    if (children.get(i).equals(anotherTree.children.get(i))) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    public String getNode() {
        return node;
    }

    public List<Tree> getChildren() {
        return children;
    }
}
