package co.marcuss.graphs;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * @author Marcus Sanchez sanchez.marcus@gmail.com<br/>
 *         <a ref="https://github.com/marcuss">https://github.com/marcuss</a>
 */
public class NodeTest {

    /**
     * Node can connect with another node.
     */
    @Test
    public void connect() {
        final Node node = new Node(5);
        final Node node2 = new Node(3);
        MatcherAssert.assertThat(
            node.connect(node2).getAdjacents().contains(node2),
            Matchers.equalTo(true)
        );
    }

}
