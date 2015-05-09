package co.marcuss.graphs;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * @author Marcus Sanchez sanchez.marcus@gmail.com<br/>
 *         <a ref="https://github.com/marcuss">https://github.com/marcuss</a>
 */
public class NetworkTest {

    /**
     * Network returns an exception on invalid size.
     */
    @Test(expected = IllegalArgumentException.class)
    public void invalidSize() throws IllegalArgumentException {
        new Network(-1);
    }

    /**
     * Network return an exception on invalid connect.
     */
    @Test(expected = IllegalArgumentException.class)
    public void invalidConnect() {
        new Network(2).connect(3, 1);
    }

    /**
     * Network can connect two nodes.
     */
    public void connect() {
        MatcherAssert.assertThat(
            new Network(2).connect(3, 1),
            Matchers.any(Network.class)
        );
    }

    /**
     * Network returns an exception on invalid query.
     */
    @Test(expected = IllegalArgumentException.class)
    public void invalidQuery() {
        new Network(2).connect(2, 1).query(1, 5);
    }

    /**
     * Network returns an exception on same node connect.
     */
    @Test(expected = IllegalArgumentException.class)
    public void invalidConnectSameNode() {
        new Network(4).connect(2, 1).connect(2, 2);
    }

    /**
     * Network returns an exception on same node query.
     */
    @Test(expected = IllegalArgumentException.class)
    public void invalidQuerySameNode() {
        new Network(4).connect(2, 1).query(2, 2);
    }

    /**
     * Network query returns false on not found connection.
     */
    @Test
    public void queryNoConnectionFound() {
        MatcherAssert.assertThat(
            new Network(10).connect(1, 3).connect(3, 5).connect(5, 7)
                .connect(7, 9).connect(2, 4).connect(4, 6)
                .connect(6, 8).connect(8, 10).query(1, 10),
            Matchers.equalTo(false)
        );
    }

    /**
     * Network query returns true on found connection.
     */
    @Test
    public void queryConnectionFound() {
        MatcherAssert.assertThat(
            new Network(8).connect(1, 2).connect(1, 6).connect(2, 6)
                .connect(2, 4).connect(5, 8).query(6, 4),
            Matchers.equalTo(true)
        );
    }

    /**
     * Network can query on mid size data scenarios.
     */
    @Test
    public void queryOnMidSizeData() throws InterruptedException {
        final int size = 2000;
        final Network network = new Network(size);
        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                if (i != j) {
                    network.connect(i, j);
                }
            }
        }
        MatcherAssert.assertThat(
            network.query(1, size), Matchers.equalTo(true)
        );
    }
}
