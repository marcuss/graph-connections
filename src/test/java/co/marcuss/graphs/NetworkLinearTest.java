package co.marcuss.graphs;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * @author Marcus Sanchez sanchez.marcus@gmail.com<br/>
 *         <a ref="https://github.com/marcuss">https://github.com/marcuss</a>
 */
public class NetworkLinearTest {

    /**
     * NetworkLinear returns an exception on invalid size.
     */
    @Test(expected = IllegalArgumentException.class)
    public void invalidSize() throws IllegalArgumentException {
        new NetworkLinear(-1);
    }

    /**
     * NetworkLinear return an exception on invalid connect.
     */
    @Test(expected = IllegalArgumentException.class)
    public void invalidConnect() {
        new NetworkLinear(2).connect(3, 1);
    }

    /**
     * NetworkLinear can connect two nodes.
     */
    public void connect() {
        MatcherAssert.assertThat(
            new NetworkLinear(2).connect(3, 1),
            Matchers.any(NetworkLinear.class)
        );
    }

    /**
     * NetworkLinear returns an exception on invalid query.
     */
    @Test(expected = IllegalArgumentException.class)
    public void invalidQuery() {
        new NetworkLinear(2).connect(2, 1).query(1, 5);
    }

    /**
     * NetworkLinear returns an exception on same node connect
     */
    @Test(expected = IllegalArgumentException.class)
    public void invalidConnectSameNode() {
        new NetworkLinear(4).connect(2, 1).connect(2, 2);
    }

    /**
     * NetworkLinear returns an exception on same node query.
     */
    @Test(expected = IllegalArgumentException.class)
    public void invalidQuerySameNode() {
        new NetworkLinear(4).connect(2, 1).query(2, 2);
    }

    /**
     * NetworkLinear returns false on no connection found.
     */
    @Test
    public void queryNoConnectionFound() {
        MatcherAssert.assertThat(
            new NetworkLinear(10).connect(1, 3).connect(3, 5)
                .connect(5, 7).connect(7, 9).connect(2, 4)
                .connect(4, 6).connect(6, 8).connect(8, 10)
                .query(1, 10),
            Matchers.equalTo(false)
        );
    }

    /**
     * NetworkLinear returns true on found connection.
     */
    @Test
    public void queryConnectionFound() {
        MatcherAssert.assertThat(
            new NetworkLinear(8).connect(1, 2).connect(1, 6).connect(2, 6)
                .connect(2, 4).connect(5, 8).query(6, 4),
            Matchers.equalTo(true)
        );
    }

    /**
     * NetworkLinear can query on large data scenarios.
     */
    @Test
    public void queryOnLargeData() throws InterruptedException {
        final int size = 10000;
        final NetworkLinear network = new NetworkLinear(size);
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
