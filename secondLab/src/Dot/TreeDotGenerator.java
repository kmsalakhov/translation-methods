package Dot;

import Parser.Tree;

import java.io.IOException;
import java.io.Writer;

public class TreeDotGenerator implements DotGenerator<Tree> {
    private final Writer writer;

    public TreeDotGenerator(Writer writer) {
        this.writer = writer;
    }

    @Override
    public void generateDot(Tree root) throws IOException {
        writer.write("digraph treeGraph {%n".formatted());
        dot(root, 0);
        writer.write("}%n".formatted());
    }

    private int dot(Tree node, int id) throws IOException {
        writer.write("    %d [label=\"%s\"]%n".formatted(id, node.getNode()));

        int childId = id + 1;
        for (Tree child : node.getChildren()) {
            writer.write("    %d -> %d%n".formatted(id, childId));
            childId = dot(child, childId);
        }

        return childId;
    }
}
