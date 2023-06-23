/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 *
 * @author NUWAA
 */
public class HuffmanEncoding {

    static class Node implements Comparable<Node> {

        char character;
        int frequency;
        Node leftNode, rightNode;

        Node(char character, int frequency) {
            this.character = character;
            this.frequency = frequency;
        }

        Node(Node leftNode, Node rightNode) {
            this.leftNode = leftNode;
            this.rightNode = rightNode;
            this.frequency = leftNode.frequency + rightNode.frequency;
        }

        public int compareTo(Node node) {
            return this.frequency - node.frequency;
        }
    }

    static Map<Character, String> huffmanCodes = new HashMap<>();
    static String encodedMessage = "";

    public static void buildHuffmanTree(String message) {
        Map<Character, Integer> frequency = new HashMap<>();

        for (int i = 0; i < message.length(); i++) {
            if (!frequency.containsKey(message.charAt(i))) {
                frequency.put(message.charAt(i), 0);
            }
            frequency.put(message.charAt(i), frequency.get(message.charAt(i)) + 1);
        }

        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();

        for (Map.Entry<Character, Integer> entry : frequency.entrySet()) {
            priorityQueue.add(new Node(entry.getKey(), entry.getValue()));
        }

        while (priorityQueue.size() > 1) {
            Node leftNode = priorityQueue.poll();
            Node rightNode = priorityQueue.poll();
            priorityQueue.add(new Node(leftNode, rightNode));
        }

        Node rootNode = priorityQueue.poll();

        buildHuffmanCodes(rootNode, "");
    }

    public static void buildHuffmanCodes(Node rootNode, String message) {
        if (rootNode.leftNode == null && rootNode.rightNode == null) {
            huffmanCodes.put(rootNode.character, message);
            return;
        }
        buildHuffmanCodes(rootNode.leftNode, message + "0");
        buildHuffmanCodes(rootNode.rightNode, message + "1");
    }

    public static String encodeMessage(String message) {
        for (int i = 0; i < message.length(); i++) {
            encodedMessage += huffmanCodes.get(message.charAt(i));
        }
        return encodedMessage;
    }

    public static void main(String[] args) {
        String message = "WE WANT INFO";
        buildHuffmanTree(message);
        System.out.println("Original Message: " + message);
        System.out.println("Huffman Encoded Message: " + encodeMessage(message));
        HuffmanDecoding.decodeMessage(encodedMessage);
        System.out.println("Huffman Decoded Message: " + HuffmanDecoding.decodedMessage);
    }
}
