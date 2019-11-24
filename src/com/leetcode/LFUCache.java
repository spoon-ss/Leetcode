package com.leetcode;

import java.util.HashMap;
import java.util.Map;

class LFUCache {
    private class Node{
        public int key;
        public int value;
        public int frequence;
        public Node next;
        public Node previous;

        public Node(int key, int value, int frequence){
            this.key = key;
            this.value = value;
            this.frequence = frequence;
            this.next = null;
            this.previous = null;
        }
    }
    private class DoubleLinkedList{
        private Node dummyHead;
        private Node tail;
        public int size;

        public DoubleLinkedList(){
            this.dummyHead = new Node(0, 0, 0);
            this.tail = dummyHead;
            this.size = 0;
        }

        public void addAtHead(Node node){
            if(size == 0){
                dummyHead.next = node;
                node.next = null;
                node.previous = dummyHead;
                tail = node;
            }else{
                Node next = dummyHead.next;
                dummyHead.next = node;
                node.next = next;
                next.previous = node;
                node.previous = dummyHead;
            }
            this.size += 1;

        }
        public Node removeAtTail(){
            if(size == 0){
                return null;
            }
            Node deleteNode = this.tail;
            Node previous = tail.previous;
            previous.next = null;
            tail = previous;
            size -= 1;
            return deleteNode;
        }
        public void removeNode(Node node){
            if(node == tail){
                removeAtTail();
            }else{
                Node next = node.next;
                Node previous = node.previous;
                node.next = null;
                node.previous = null;

                previous.next = next;
                next.previous = previous;
                this.size -= 1;
            }

        }


    }

    private Map<Integer, Node> keyToNodeMap;
    private Map<Integer, DoubleLinkedList> frequenceToListMap;
    private int minFrequence;
    private int capacity;
    private int size;

    public LFUCache(int capacity){
        this.capacity = capacity;
        keyToNodeMap = new HashMap<>();
        frequenceToListMap = new HashMap<>();
        minFrequence = -1;
        this.capacity = capacity;
        this.size = 0;
    }


    public int get(int key){
        if(capacity == 0){
            return -1;
        }
        if(!keyToNodeMap.containsKey(key)){
            return -1;
        }
        Node node = keyToNodeMap.get(key);
        DoubleLinkedList list = frequenceToListMap.get(node.frequence);
        list.removeNode(node);
        node.frequence += 1;
        if(!frequenceToListMap.containsKey(node.frequence)){
            frequenceToListMap.put(node.frequence, new DoubleLinkedList());
        }
        frequenceToListMap.get(node.frequence).addAtHead(node);

        if(list.size == 0){
            frequenceToListMap.remove(node.frequence - 1);
            if(minFrequence == node.frequence - 1){
                minFrequence += 1;
            }
        }
        return node.value;


    }
    public void put(int key, int value){
        if(capacity == 0){
            return;
        }
        if(keyToNodeMap.containsKey(key)){
            keyToNodeMap.get(key).value = value;
            get(key);
            return;
        }
        if(size == capacity){
            DoubleLinkedList list = frequenceToListMap.get(minFrequence);
            Node deleteNode = list.removeAtTail();
            keyToNodeMap.remove(deleteNode.key);

            if(list.size == 0){
                frequenceToListMap.remove(minFrequence);
            }
            Node newNode = new Node(key, value, 1);
            if(frequenceToListMap.containsKey(1)){
                frequenceToListMap.get(1).addAtHead(newNode);
            }else{
                DoubleLinkedList newList = new DoubleLinkedList();
                newList.addAtHead(newNode);
                frequenceToListMap.put(1, newList);
            }
            keyToNodeMap.put(key, newNode);
            minFrequence = 1;
        }else{
            Node newNode = new Node(key, value, 1);
            if(frequenceToListMap.containsKey(1)){
                frequenceToListMap.get(1).addAtHead(newNode);
            }else{
                DoubleLinkedList newList = new DoubleLinkedList();
                newList.addAtHead(newNode);
                frequenceToListMap.put(1, newList);
            }
            keyToNodeMap.put(key, newNode);
            minFrequence = 1;
            this.size += 1;
        }

    }
}
