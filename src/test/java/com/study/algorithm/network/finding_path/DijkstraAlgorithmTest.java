package com.study.algorithm.network.finding_path;

import com.study.algorithm.network.finding_path.DijkstraAlgorithm.Node;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(Parameterized.class)
public class DijkstraAlgorithmTest {

    private List<Node<Integer>> nodeList;
    private int startNodeId;
    private int targetNodeId;
    private int[] way;

    public DijkstraAlgorithmTest(int startNodeId, int targetNodeId, int... way) {
        this.startNodeId = startNodeId;
        this.targetNodeId = targetNodeId;
        this.way = way;
    }

    @Parameterized.Parameters
    public static Collection primeNumbers() {
        return asList(new Object[][]{
                {0, 1, new int[]{0, 1}},
                {0, 4, new int[]{0, 2, 5, 4}},
                {1, 5, new int[]{1, 2, 5}}
        });
    }

    @Before
    public void prepareNetwork() {
        Node<Integer> node0 = new Node<>(0);
        Node<Integer> node1 = new Node<>(1);
        Node<Integer> node2 = new Node<>(2);
        Node<Integer> node3 = new Node<>(3);
        Node<Integer> node4 = new Node<>(4);
        Node<Integer> node5 = new Node<>(5);

        node0.connections.put(node1, 7);
        node0.connections.put(node2, 9);
        node0.connections.put(node5, 14);

        node1.connections.put(node0, 7);
        node1.connections.put(node2, 10);
        node1.connections.put(node3, 15);

        node2.connections.put(node0, 9);
        node2.connections.put(node1, 10);
        node2.connections.put(node3, 11);
        node2.connections.put(node5, 2);

        node3.connections.put(node1, 15);
        node3.connections.put(node2, 11);
        node3.connections.put(node4, 6);

        node4.connections.put(node3, 6);
        node4.connections.put(node5, 9);

        node5.connections.put(node0, 14);
        node5.connections.put(node2, 2);
        node5.connections.put(node4, 9);

        nodeList = Arrays.asList(node0, node1, node2, node3, node4, node5);
    }

    @Test
    public void test() {
        List<Node<Integer>> closestWay = DijkstraAlgorithm.findLowestCostWay(nodeList.get(startNodeId), nodeList.get(targetNodeId));
        assertEquals(closestWay.size(), way.length);
        List<Integer> idWay = closestWay.stream().map(Node::getValue).collect(Collectors.toList());
        for (int i = 0; i < way.length; i++) {
            assertEquals((Integer) way[i], idWay.get(i));
        }
    }

}
