package com.study.algorithm.dijkstra;


import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class Node {
    private final int id;
    int lowestCost;
    boolean isVisited = false;
    Node parent;
    Map<Node, Integer> neighborMap = new HashMap<>();

    Node(int id) {
        this.id = id;
    }

}