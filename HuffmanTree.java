package edu.miracosta.cs113;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * HuffmanTree.java :
 *
 * @author
 * @version 1.0
 */
//public class HuffmanTree implements HuffmanInterface {

    // Completed huffman tree.




    // Array of accepted characters from message.



    /**
     * String constructor creates a custom huffman tree for the String passed
     * as a parameter. Also creates a dataTable that consists of the symbols
     * and their corresponding huffman code for quick and easy decoding.
     *
     * @param message String from which the custom huffman tree is built.
     */




    /**
     * Decodes a message using the generated Huffman tree, where each character in the given message ('1's and '0's)
     * corresponds to traversals through said tree.
     *
     * @param codedMessage The compressed message based on this Huffman tree's encoding
     * @return The given message in its decoded form
     */




    /**
     * Outputs the message encoded from the generated Huffman tree.
     * pre: the Huffman tree has been built using characters by which the message is only comprised.
     *
     * @param message The message to be decoded
     * @return The given message in its specific Huffman encoding of '1's and '0's
     */





    /**
     * A helper method that build the custom huffman tree.
     *
     * @param symbols array of Huffman objects
     */





    /**
     * Helper method that counts the frequency of the accepted characters in a string and returns
     * an array of nodes containing the symbol and weight.
     *
     * @param message sting being analyzed.
     * @return HuffmanData array of nodes with their symbols and frequencies.
     */




    /**
     * Wrapper method for the recursive buildFrequencyTable method.
     *
     * pre - HuffmanTree is already built.
     * @param root root node of the huffman tree.
     */




    /**
     * Traverses through the huffman tree and obtains the huffman codes for each symbol
     * and stores the values in a table.
     * @param node
     * @param s
     */





    /**
     * Class representing the node in a huffman tree. Used to build the tree.
     */




    /**
     * Nested class for comparing two HuffmanTree's.
     */
//    private static class CompareHuffmanTrees implements Comparator<BinaryTree<HuffData>> {

        /**
         * Compares the weights in both children of this root.
         *
         * @param treeLeft left child
         * @param treeRight right child
         * @return -1 if left is less than right, 0 if
         *         left equals right and +1 if left is
         *         greater than right.
         */

//    }


    /**
     * Stores the symbol of each character and its corresponding huffman code for easy
     * decoding lookup.
     */
//    public class FrequencyTable {
//
//    }
//}
