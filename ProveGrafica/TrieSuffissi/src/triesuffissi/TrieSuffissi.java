package triesuffissi;

/**
 *
 * @author Florian
 */
import java.util.Map;
import java.util.HashMap;

public class TrieSuffissi {
    private Node createSuffixTree(String source, Node root) {
        for (int i = 0; i < source.length(); i++) {
            Node parent = new Node(source.charAt(i));
            if (root.hasChild(parent)) {
                parent = root.getChild(parent);
            }
            Node current = parent;
            for (int j = i + 1; j < source.length(); j++) {
                Node temp = new Node(source.charAt(j));
                if (current.hasChild(temp)) {
                    temp = current.getChild(temp);
                } else {
                    current.addChild(temp);
                }
                current = temp;
            }
            root.addChild(parent);
        }
        return root;
    }

    public TrieSuffissi(String source) {
        this.root = createSuffixTree(source, new Node());
    }

    void printMap(Map<Character, Node> map) {
        for (char c : map.keySet()) {
            System.out.println("Current node has child" + c);
        }
    }

    boolean search(String target) {
        Map<Character, Node> rootChildren = this.root.children;
        for (char c : target.toCharArray()) {
            if (rootChildren.containsKey(c)) {
                rootChildren = rootChildren.get(c).children;
            } else {
                return false;
            }
        }
        return true;
    }

    class Node {

        private final char currentValue;
        private Map<Character, Node> children;

        Node() {
            this.currentValue = '*';
            this.children = new HashMap<>();
        }

        Node(char currentValue) {
            this.currentValue = currentValue;
            this.children = new HashMap<>();
        }

        char getValue() {
            return this.currentValue;
        }

        void addChild(Node c) {
            this.children.put(c.getValue(), c);
        }

        boolean hasChild(Node c) {
            return this.children.containsKey(c.getValue());
        }

        Node getChild(Node c) {
            return this.children.get(c.getValue());
        }

        public String toString() {
            char currentValue = this.getValue();
            StringBuffer keys = new StringBuffer();
            for (char key : this.children.keySet()) {
                keys.append("Current key:" + key + "\n");
            }
            return "Current value:" + currentValue + "\n"
                    + "Keys:" + keys.toString();
        }
    }

    private Node root;

    private void log(Object l) {
        System.out.println(l);
    }
    
    public static void main(String[] args) {
        TrieSuffissi sTree = new TrieSuffissi("bananas");

        sTree.search("meme");

    }
}
