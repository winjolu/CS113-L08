package edu.miracosta.cs113;

import java.util.*;

/**
 * HuffmanTree.java : Implementation of Huffman Tree encoding and decoding.
 *
 * @author winjolu
 * @version 1.0
 */
public class HuffmanTree implements HuffmanInterface {

    private BinaryTree<HuffData> huffTree;
    private Map<Character, String> encodingMap;

    /**
     * Constructs a HuffmanTree from the given message.
     *
     * @param message The message to build the HuffmanTree from.
     */
    public HuffmanTree(String message) {
        buildTree(message);
    }

    @Override
    public String decode(String codedMessage) {
        StringBuilder decodedMessage = new StringBuilder();
        BinaryTree<HuffData> currentTree = huffTree;
        int i = 0;
        while (i < codedMessage.length()) {
            while (!currentTree.isLeaf()) {
                char bit = codedMessage.charAt(i);
                if (bit == '0') {
                    currentTree = currentTree.getLeftSubtree();
                } else {
                    currentTree = currentTree.getRightSubtree();
                }
                i++;
            }
            HuffData huffData = currentTree.getData();
            decodedMessage.append(huffData.symbol);
            currentTree = huffTree;
        }
        return decodedMessage.toString();
    }

    @Override
    public String encode(String message) {
        StringBuilder encodedMessage = new StringBuilder();
        for (char ch : message.toCharArray()) {
            encodedMessage.append(encodingMap.get(ch));
        }
        return encodedMessage.toString();
    }

    /**
     * Builds the HuffmanTree from the given message.
     *
     * @param message The message to build the tree from.
     */
    private void buildTree(String message) {
        Map<Character, Integer> frequencyMap = buildFrequencyMap(message);
        PriorityQueue<BinaryTree<HuffData>> pq = new PriorityQueue<>(new CompareHuffmanTrees());

        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            pq.offer(new BinaryTree<>(new HuffData(entry.getValue(), entry.getKey()), null, null));
        }

        while (pq.size() > 1) {
            BinaryTree<HuffData> left = pq.poll();
            BinaryTree<HuffData> right = pq.poll();
            int newWeight = left.getData().weight + right.getData().weight;
            pq.offer(new BinaryTree<>(new HuffData(newWeight, '\0'), left, right));
        }

        huffTree = pq.poll();
        buildEncodingMap(huffTree, new StringBuilder());
    }

    /**
     * Builds a frequency map from the given message.
     *
     * @param message The message to build the frequency map from.
     * @return A map of characters to their frequencies.
     */
    private Map<Character, Integer> buildFrequencyMap(String message) {
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char ch : message.toCharArray()) {
            frequencyMap.put(ch, frequencyMap.getOrDefault(ch, 0) + 1);
        }
        return frequencyMap;
    }

    /**
     * Builds the encoding map for the HuffmanTree.
     *
     * @param tree The HuffmanTree.
     * @param prefix The prefix code.
     */
    private void buildEncodingMap(BinaryTree<HuffData> tree, StringBuilder prefix) {
        if (tree.isLeaf()) {
            HuffData huffData = tree.getData();
            encodingMap.put(huffData.symbol, prefix.toString());
        } else {
            prefix.append('0');
            buildEncodingMap(tree.getLeftSubtree(), prefix);
            prefix.deleteCharAt(prefix.length() - 1);

            prefix.append('1');
            buildEncodingMap(tree.getRightSubtree(), prefix);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }

    /**
     * HuffData class to store the symbol and its weight.
     */
    private static class HuffData {
        private int weight;
        private char symbol;

        public HuffData(int weight, char symbol) {
            this.weight = weight;
            this.symbol = symbol;
        }
    }

    /**
     * Comparator for comparing HuffmanTrees based on their weights.
     */
    private static class CompareHuffmanTrees implements Comparator<BinaryTree<HuffData>> {
        @Override
        public int compare(BinaryTree<HuffData> tree1, BinaryTree<HuffData> tree2) {
            return tree1.getData().weight - tree2.getData().weight;
        }
    }
}