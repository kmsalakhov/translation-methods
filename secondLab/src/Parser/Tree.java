package Parser;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Tree {
    private final String node;
    private final List<Tree> children;

    public Tree(String node, Tree ... children) {
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
        return Objects.equals(toString(), obj.toString());
    }
}
