package co.marcuss.graphs;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author Marcus Sanchez sanchez.marcus@gmail.com<br/>
 *         <a ref="https://github.com/marcuss">https://github.com/marcuss</a>
 */
public final class Network {

    private final Map<Integer, Node> nodes = new HashMap<>();

    public Network(final int size) throws IllegalArgumentException {
        if (size < 1) {
            throw new IllegalArgumentException(
                "Network size must be a positive integer."
            );
        }
        for (int value = 1; value <= size; value++) {
            this.nodes.put(value, new Node(value));
        }
    }

    public Network connect(final int start, final int end)
                            throws IllegalArgumentException {
        this.checkValidNodes(start, end);
        this.getNode(start).connect(this.getNode(end));
        return this;
    }

    private void checkValidNodes(final int start, final int end) {
        if (!this.nodes.containsKey(start)) {
            throw new IllegalArgumentException(
                "Source network node: " + start + ", doesn't exist"
            );
        }
        if (!this.nodes.containsKey(end)) {
            throw new IllegalArgumentException(
                "Destination network node: " + end + ", doesn't exist"
            );
        }
        if (start == end) {
            throw new IllegalArgumentException(
                "Source network node can not be the same as destination"
            );
        }
    }

    private Node getNode(final Integer value) {
        return this.nodes.get(value);
    }

    public boolean query(final int start, final int end)
        throws IllegalArgumentException {
        this.checkValidNodes(start, end);
        final Stack<Integer> visited = new Stack<>();
        return this.query(start, end, visited);
    }

    private boolean query(final int start, final int end,
                            final Stack<Integer> visited)
        throws IllegalArgumentException {
        visited.push(this.nodes.get(start).getValue());
        for (final Node adjacent : this.getNode(start).getAdjacents()) {
            if (adjacent.getValue().equals(end)) {
                return true;
            }
            if (!visited.contains(adjacent.getValue())) {
                return this.query(
                    adjacent.getValue(),
                    end, visited
                );
            }
        }
        visited.pop();
        return false;
    }
}
