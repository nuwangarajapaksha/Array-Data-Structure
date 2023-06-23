/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Map;

/**
 *
 * @author NUWAA
 */
public class HuffmanDecoding {

    static Map<Character, String> huffmanCodes = HuffmanEncoding.huffmanCodes;
    static String decodedMessage = "";

    public static void decodeMessage(String encodedMessage) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < encodedMessage.length(); i++) {
            stringBuilder.append(encodedMessage.charAt(i));
            for (Map.Entry<Character, String> entry : huffmanCodes.entrySet()) {
                if (entry.getValue().equals(stringBuilder.toString())) {
                    decodedMessage += entry.getKey();
                    stringBuilder = new StringBuilder();
                }
            }
        }
    }
}
