import java.io.IOException;
import java.util.StringTokenizer;

import java.text.NumberFormat;
import java.util.*;

// Class to represent node
// For simplicity the atributes are public
class Node {
    public String card;
    public int balance;
    public int height;
    public Node left;
    public Node right;

    // Constructors
    Node(String card, int balance) {
        this.card = card;
        this.balance = balance;
        left = null;
        right = null;
        this.height = 1;
    }

    Node(String card, int balance, Node l, Node r) {
        this.card = card;
        this.balance = balance;
        left = l;
        right = r;
        this.height = 1;
    }
    //Comparators. The unique criteria is the String card. 
    int compareTo(String card) {
        return this.card.compareTo(card);
    }

    int compareTo(Node otherNode) {
        return this.compareTo(otherNode.card);
    }

}


// Class to represent the binary tree.
class BST {

    // root of the tree. Null when the tree is empty
    protected Node root = null;

    //Constructors
    public BST() {
        root = null;
    }

    public BST(Node no) {
        root = no;
    }

    public BST(String card, int balance) {
        root = new Node(card, balance);
    }

    //find some card in the node. Null is not found.
    public Node get(Node no) {
        return this.get(no.card);
    }

    public Node get(String card) {
        Node no = root;
        while (no != null) {
            if (no.compareTo(card) == 0) {
                return no;
            }
            no = ((no.compareTo(card) > 0) ? no.left : no.right);
        }
        return null;
    }

    //adds a new node to the tree. If values already exist, adds it. Executes the balance.
    public void add(String card, int balance) {
        root = add(card, balance, root);
        return;
    }

    protected Node add(String card, int balance, Node node) {
        //if the card not exists, we creat a new node for it.
        if (node == null) {
            return new Node(card, balance);
        }

        //if card already exists, we add the value to the card.
        if (node.compareTo(card) == 0) {
            node.balance += balance;
            // not found the card yet. Get down a level.
        } else {
            if (node.compareTo(card) > 0) {
                node.left = add(card, balance, node.left);
            } else {
                node.right = add(card, balance, node.right);
            }
        }
        //update the height of the actual node
        node.height = nodeHeightCalc(node);
        
        //gets balance factor of node
        int balanceFactor = balanceFactorCalc(node);

        //Left Case (if the card I inserted is lower than the left son of the actual)
        if(balanceFactor ==2 && node.left.compareTo(card)>0)  {
            return RightRotation(node);
        }
        //Right Case (if the card I inserted is bigger than the right son of the actual)
        if (balanceFactor == -2 && node.right.compareTo(card)<0) {
            return LeftRotation(node);
        }
        //Left Right Case (if the card I inserted is bigger than the left son of the actual)
        if (balanceFactor == 2 && node.left.compareTo(card)< 0) {
            return LeftRightRotation(node);

        }      
        //Right Left Case (if the card I inserted is lower than the right son of the actual)
        if (balanceFactor == -2 && node.right.compareTo(card)>0) {
            return RightLeftRotation(node);
        }

        return node;

    }

    //Return height of node
    int heightOfNode(Node node) {
        if (node == null)
            return 0;

        return node.height;
    }

    //Return the balance factor of node
    public int balanceFactorCalc(Node node) {
        if (node == null)
            return 0;
        return heightOfNode(node.left) - heightOfNode(node.right);

    }

    //Returns the updated height of node 
    public int nodeHeightCalc(Node node) {
        return 1 + Math.max(heightOfNode(node.left), heightOfNode(node.right));

    }

    //Rotation in Left Case
    public Node RightRotation(Node node) {
        //rotation
        Node temp = node.left;
        node.left = temp.right; 
        temp.right = node;
        //update height
        node.height = nodeHeightCalc(node);
        temp.height = nodeHeightCalc(temp);
        return temp;

    }

    //Rotation in Right Case
    public Node LeftRotation(Node node) {
        //rotation
        Node temp = node.right;
        node.right = temp.left;
        temp.left = node;
        //update height
        node.height = nodeHeightCalc(node);
        temp.height = nodeHeightCalc(temp);
        return temp;
    }

    //Left Right Rotation
    public Node LeftRightRotation(Node node) {
        node.left = LeftRotation(node.left);
        return RightRotation(node);

    }

    // Right Left Rotation
    public Node RightLeftRotation(Node node) {
        node.right = RightRotation(node.right);
        return LeftRotation(node);
    }

    //Deletes card of tree
    public void remove(Node no) {
        remove(no.card);
    }

    public void remove(String card) {
        root = remove(card, root);
    }

    protected Node remove(String card, Node no) {
        if (no == null) { // tree empty or not found
            return null;
        }
        if (no.compareTo(card) == 0) { // delete this node
            if (no.left == null) { // one child, links and leaves
                return no.right;
            } else if (no.right == null) { // same
                return no.left;
            } else { // two childs...
                // swap card with rigthmost and then deletes the node...
                Node aux = getRightmost(no.left);
                no.card = aux.card;
                no.balance = aux.balance;
                aux.card = card; //balance of node doesnt need to change...
                no.left = remove(card, no.left);
            }
        } else { //keeps going down a level , left or right
            if (no.compareTo(card) > 0) {
                // left...
                no.left = remove(card, no.left);
            } else { // right...
                no.right = remove(card, no.right);
            }
        }
        //update height
        no.height = nodeHeightCalc(no);
        //check balance factor
        int balanceFactor = balanceFactorCalc(no);
        //Left Case
        if (balanceFactor == 2 && balanceFactorCalc(no.left) >= 0){
            return RightRotation(no);
        }
            
        //Left-Right Case
        if (balanceFactor == 2 && balanceFactorCalc(no.left) < 0){
            return LeftRightRotation(no);
        }
            
        //Right Case
        if (balanceFactor == -2 && balanceFactorCalc(no.right) <= 0){
            return LeftRotation(no);
        }
            
        //Right Left Case
        if (balanceFactor == -2 && balanceFactorCalc(no.right) > 0){
            return RightLeftRotation(no);
        }

        return no;
    }

    protected Node getRightmost(Node no) {
        return ((no.right == null) ? no : getRightmost(no.right));
    }

    protected Node getLeftmost(Node no) {
        return ((no.left == null) ? no : getLeftmost(no.left));

    }


    // prints in order.
    void printInOrder() {
        printInOrder(root);
    }

    void printInOrder(Node no) {
        if (no == null)
            return;
        printInOrder(no.left);
        System.out.println(no.card + " BALANCE " + no.balance);
        printInOrder(no.right);
    }
}

public class AvlTrees{

    public static void main(String[] arguments) {


        String input, comands;
        int balance;
        String card;
        StringTokenizer st;

        BST tree = new BST();

        do { 
            input = readLn(200);
            st = new StringTokenizer(input.trim());
            comands = st.nextToken();
            if (comands.equals("UPDATE")) {
                card = new String(st.nextToken());
                balance = Integer.parseInt(st.nextToken());
                tree.add(card, balance);
            } else if (comands.equals("BALANCE")) {
                card = new String(st.nextToken());
                Node no = tree.get(card);
                if (no == null)
                    System.out.println(card + " UNEXISTENT");
                else
                    System.out.println(card + " BALANCE " + no.balance);
            } else if (comands.equals("REMOVE")) {
                card = new String(st.nextToken());
                tree.remove(card);
            } else if (comands.equals("PRINT")) {
                tree.printInOrder();
            } else if (comands.equals("END"))
                return;
        } while (true);
    }

    //utility function to read from stdin
    static String readLn(int maxLg) { 
        byte lin[] = new byte[maxLg];
        int lg = 0, car = -1;
        String line = "";
        try {
            while (lg < maxLg) {
                car = System.in.read();
                if ((car < 0) || (car == '\n'))
                    break;
                lin[lg++] += car;
            }
        } catch (IOException e) {
            return (null);
        }
        if ((car < 0) && (lg == 0))
            return (null); // eof
        return (new String(lin, 0, lg));
    }

}
