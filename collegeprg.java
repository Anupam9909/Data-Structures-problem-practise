import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Comparator;


class HuffmanNode {
  char c;
  int item;
  HuffmanNode left;
  HuffmanNode right;
}

class ImplementComparator implements Comparator<HuffmanNode> {
  public int compare(HuffmanNode x, HuffmanNode y) {
    return x.item - y.item;
  }
}

public class Huffman {
    
  public static void printCode(HuffmanNode root, String s) {
    if (root.left == null && root.right == null && Character.isLetter(root.c)) {
      System.out.println(root.c + "   |  " + s);
      return;
    }
    printCode(root.left, s + "0");
    printCode(root.right, s + "1");
  }


  public static void main(String[] args) {

    int n = 5;
    char[] charArray = { 'A', 'B', 'C', 'D', 'E' };
    int[] charfreq = { 4, 7, 3, 2, 4 };

    PriorityQueue<HuffmanNode> pq = new PriorityQueue<HuffmanNode>(n, new ImplementComparator());

    for (int i = 0; i < n; i++) {
      HuffmanNode hn = new HuffmanNode();

      hn.c = charArray[i];
      hn.item = charfreq[i];

      hn.left = null;
      hn.right = null;

      pq.add(hn);
    }

    HuffmanNode root = null;

    while (pq.size() > 1) {

      HuffmanNode x = pq.peek();
      pq.poll();

      HuffmanNode y = pq.peek();
      pq.poll();

      HuffmanNode f = new HuffmanNode();

      f.item = x.item + y.item;
      f.c = '-';
      f.left = x;
      f.right = y;
      root = f;

      pq.add(f);
    }


    System.out.println(" Char | Huffman code ");
    System.out.println("--------------------");
    printCode(root, "");
  }
}