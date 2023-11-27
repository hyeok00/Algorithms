import java.util.*;

class Node implements Comparable<Node>{
    int num;
    int x;
    int y;
    Node left;
    Node right;
    Node(int n, int x, int y){
        num = n;
        this.x = x;
        this.y = y;
        left = null;
        right = null;
    }
    
    public int compareTo(Node o){
        if(this.y == o.y)
            return this.x - o.x;
        return o.y - this.y;
    }
}

class BTree{
    Node head;
    
    BTree(){
        head = null;
    }
    
    void insert(Node inNode){
        if(head == null){
            head = inNode;
            return;
        }
        if(inNode.x < head.x){
            if(head.left == null)
                head.left = inNode;
            else
                insert(head.left, inNode);
        }else{
            if(head.right == null)
                head.right = inNode;
            else
                insert(head.right, inNode);
        }
    }
    
    void insert(Node base, Node inNode){
        if(inNode.x < base.x){
            if(base.left == null)
                base.left = inNode;
            else
                insert(base.left, inNode);
        }else{
            if(base.right == null)
                base.right = inNode;
            else
                insert(base.right, inNode);
        }
    }
    
    // VLR
    List<Integer> getPreorder(){
        List<Integer> list = new ArrayList();
        preorder(head, list);
        return list;
    }
    void preorder(Node n, List l){
        l.add(n.num);
        if(n.left != null)
            preorder(n.left, l);
        if(n.right != null)
            preorder(n.right, l);
    }
    
    // LRV
    List<Integer> getPostorder(){
        List<Integer> list = new ArrayList();
        postorder(head, list);
        return list;
    }
    void postorder(Node n, List l){
        if(n.left != null)
            postorder(n.left, l);
        if(n.right != null)
            postorder(n.right, l);
        l.add(n.num);
    }
}

class Solution {
    public int[][] solution(int[][] nodeinfo) {
        List<Node> list = new ArrayList();
        for(int i = 0 ; i < nodeinfo.length; ++i)
            list.add(new Node(i+1, nodeinfo[i][0], nodeinfo[i][1]));
        
        Collections.sort(list);
        
        BTree tree = new BTree();
        for(Node n : list){
            // System.out.println(n.num + " " + n.x + " " + n.y);
            tree.insert(n);
        }
        
        List<Integer> preOrderList = tree.getPreorder();
        List<Integer> postOrderList = tree.getPostorder();
        
        return new int[][] {preOrderList.stream().mapToInt(i->i).toArray(),
                           postOrderList.stream().mapToInt(i->i).toArray()};
    }
}