package co.marcuss.graphs;

import java.util.Stack;

/**
 * @author Marcus Sanchez sanchez.marcus@gmail.com<br/>
 *         <a ref="https://github.com/marcuss">https://github.com/marcuss</a>
 */
public final class NetworkLinear {

    private final boolean[][] connections;

    public NetworkLinear(final int size)
        throws IllegalArgumentException {
        if (size < 1) {
            throw new IllegalArgumentException(
                "Network size must be a positive integer."
            );
        }
        this.connections = new boolean[size + 1][size + 1];
    }

    public NetworkLinear connect(final int start, final int end)
        throws IllegalArgumentException {
        // for the problem in hand, the connection direction doesn't matter
        this.checkValidNodes(start, end);
        this.connections[start][end] = true;
        this.connections[end][start] = true;
        return this;
    }

    private void checkValidNodes(final int start, final int end)
        throws IllegalArgumentException {
        if (start > this.connections.length - 1) {
            throw new IllegalArgumentException(
                "Start network node: " + start + ", doesn't exist"
            );
        }
        if (end > this.connections.length - 1) {
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

    public boolean query(int start, final int end) throws IllegalArgumentException {
        this.checkValidNodes(start, end);
        final Stack<Integer> visited = new Stack<>();
        final Stack<Integer> path = new Stack<>();
        visited.push(start);
        path.push(start);
        int adjacent = 1;
        while (!path.isEmpty()) {
            start = path.peek();
            if (this.connections[start][end]) {
                return true;
            }
            if (!visited.contains(adjacent)
                && this.connections[start][adjacent] && start != adjacent) {
                visited.push(adjacent);
                path.push(adjacent);
                adjacent = 1;
            } else {
                adjacent++;
            }
            if (adjacent >= this.connections.length) {
                path.pop();
                adjacent = 1;
            }
        }
        return false;
    }

}
