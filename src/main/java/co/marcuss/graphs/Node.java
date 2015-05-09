package co.marcuss.graphs;

/**
 * @author Marcus Sanchez sanchez.marcus@gmail.com<br/>
 *         <a ref="https://github.com/marcuss">https://github.com/marcuss</a>
 */

import java.util.ArrayList;
import java.util.List;

public final class Node {
    private final List<Node> adjacents;
    private final Integer value;

    public Node(final Integer value) {
        this.adjacents = new ArrayList<>();
        this.value = value;
    }

    public Integer getValue() {
        return this.value;
    }

    public Node connect(final Node adjacent) {
        this.adjacents.add(adjacent);
        adjacent.adjacents.add(this);
        return this;
    }

    List<Node> getAdjacents() {
        return this.adjacents;
    }

    @Override
    public String toString() {
        return "Value: " + this.value;
    }
}
