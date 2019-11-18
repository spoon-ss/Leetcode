package com.leetcode;

import java.util.HashMap;
import java.util.Map;

class LFUCache {
    class Node{
        int value;
        int count;
        Node next;
        Node previous;

        public Node(int value, int count){
            this.value = value;
            this.count = count;
            this.next = null;
            this.previous = null;
        }
    }

    private Map<Integer, Node> freToNodeMap;
    private Map<Integer, Node> keyToNodeMap;
    private Node dummyHead;
    private Node dummyTail;
    private int size;
    private int capacity;

    public LFUCache(int capacity) {
        this.freToNodeMap = new HashMap<>();
        this.keyToNodeMap = new HashMap<>();

        this.dummyHead = new Node(0, 0);
        this.dummyTail = new Node(0, 0);
        this.dummyHead.next = dummyTail;
        this.dummyTail.previous = dummyHead;

        this.size = 0;
        this.capacity = capacity;
    }

    public int get(int key) {
        if(!keyToNodeMap.containsKey(key)){
            return -1;
        }
        Node current = keyToNodeMap.get(key);
        adjustNode(current);
        return current.value;
    }

    public void put(int key, int value) {
        if(!keyToNodeMap.containsKey(key)){
            Node node = new Node(value, 1);
            if(this.size == this.capacity){
                deleteNode(this.dummyTail.previous);
            }
            Node head;
            if(freToNodeMap.containsKey(1)){
                head = freToNodeMap.get(1);
            }else{
                head = this.dummyTail;
            }
            freToNodeMap.put(1, node);
            insertBefore(node, head);
            keyToNodeMap.put(key, node);
            this.size += 1;
        }else{
            Node node = keyToNodeMap.get(key);
            node.value = value;
            adjustNode(node);
        }

    }

    private void adjustNode(Node node){

        int oldCount = node.count;
        int newCount = node.count + 1;
        node.count += 1;

        if(freToNodeMap.get(oldCount) != node){
            Node head;
            if(!freToNodeMap.containsKey(newCount)){
                head = freToNodeMap.get(oldCount);
            }else{
                head = freToNodeMap.get(newCount);
            }
            insertBefore(node, head);
            freToNodeMap.put(newCount, node);
        }else{
            if(node.next == this.dummyTail || node.next.count != node.count){
                freToNodeMap.remove(oldCount);
            }else if(node.next.count == oldCount){
                freToNodeMap.put(oldCount, node.next);
            }

            if(!freToNodeMap.containsKey(newCount)){
                freToNodeMap.put(newCount, node);
            }else{
                insertBefore(node, freToNodeMap.get(node.count));
                freToNodeMap.put(newCount, node);
            }

        }
    }

    private void insertBefore(Node insertingNode, Node insertedNode){
        Node previousNode = insertingNode.previous;
        Node afterNode = insertingNode.next;
        previousNode.next = afterNode;
        afterNode.previous = previousNode;

        insertingNode.next = null;
        insertingNode.previous = null;

        previousNode = insertedNode.previous;
        previousNode.next = insertingNode;
        insertingNode.next = insertedNode;
        insertingNode.previous = previousNode;
        insertedNode.previous = insertingNode;
    }

    private void deleteNode(Node node){
        if(freToNodeMap.get(node.count) == node){
            if(node.next == this.dummyTail || node.next.count != node.count){
                freToNodeMap.remove(node.count);
            }else if(node.next.count == node.count){
                freToNodeMap.put(node.count, node.next);
            }
        }
        Node next = node.next;
        Node previous = node.previous;
        previous.next = next;
        next.previous = previous;
        this.size -= 1;
    }
}
