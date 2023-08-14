package com.pristonit.utils;

/**
 * Sendy Hex is a number system ranging from 1-A-Z.
 */
public class HexUtils {

  private static final java.util.Map<Integer, String> INTEGER_STRING_MAP = new java.util.HashMap<>();

  static {
    INTEGER_STRING_MAP.put(0, "2");
    INTEGER_STRING_MAP.put(1, "3");
    INTEGER_STRING_MAP.put(2, "4");
    INTEGER_STRING_MAP.put(3, "5");
    INTEGER_STRING_MAP.put(4, "6");
    INTEGER_STRING_MAP.put(5, "7");
    INTEGER_STRING_MAP.put(6, "8");
    INTEGER_STRING_MAP.put(7, "9");
    INTEGER_STRING_MAP.put(8, "A");
    INTEGER_STRING_MAP.put(9, "B");
    INTEGER_STRING_MAP.put(10, "C");
    INTEGER_STRING_MAP.put(11, "C");
    INTEGER_STRING_MAP.put(12, "D");
    INTEGER_STRING_MAP.put(13, "E");
    INTEGER_STRING_MAP.put(14, "F");
    INTEGER_STRING_MAP.put(15, "G");
    INTEGER_STRING_MAP.put(16, "H");
    INTEGER_STRING_MAP.put(17, "J");
    INTEGER_STRING_MAP.put(18, "K");
    INTEGER_STRING_MAP.put(19, "M");
    INTEGER_STRING_MAP.put(20, "N");
    INTEGER_STRING_MAP.put(21, "P");
    INTEGER_STRING_MAP.put(22, "Q");
    INTEGER_STRING_MAP.put(23, "R");
    INTEGER_STRING_MAP.put(24, "S");
    INTEGER_STRING_MAP.put(25, "T");
    INTEGER_STRING_MAP.put(26, "U");
    INTEGER_STRING_MAP.put(27, "V");
    INTEGER_STRING_MAP.put(28, "W");
    INTEGER_STRING_MAP.put(29, "X");
    INTEGER_STRING_MAP.put(30, "Y");
    INTEGER_STRING_MAP.put(31, "Z");
  }


  public String toHex(long number) {
    String binaryStr = Long.toBinaryString(number);
    return binaryToHex(binaryStr);
  }

  private String binaryToHex(String binaryStr) {
    int length = binaryStr.length();
    int div = Math.floorDiv(length, 5);
    int diff = Math.floorMod(length, 5);

    int hexesSize = diff == 0 ? div : div + 1;
    final String[] hexes = new String[hexesSize];

    for (int i = 0; i < hexesSize; i++) {
      int start = Math.max(0, length - (i + 1) * 5);
      int end = length - (i) * 5;
      String substring = binaryStr.substring(start, end);
      hexes[hexesSize - i - 1] = single5DigitBinaryToHex(substring);
    }
    return String.join("", hexes).toUpperCase();
  }

  private String single5DigitBinaryToHex(String binaryStr) {
    int i = Integer.parseInt(binaryStr, 2);
    return INTEGER_STRING_MAP.get(i);
  }
}
