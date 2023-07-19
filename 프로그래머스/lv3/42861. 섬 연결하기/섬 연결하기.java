import java.util.*;

class Solution {
    static int parent[];
    static class Node implements Comparable<Node> {
        int from;
        int to;
        int weight;
        
        Node(int a, int b, int c){
            from = a;
            to = b;
            weight = c;
        }
        public int compareTo(Node o){
            return this.weight - o.weight;
        }
    }
    
    static void union(int a, int b){
        a = find(a);
        b = find(b);
        if(a<b)
            parent[b] = a;
        else
            parent[a] = b;
    }
    
    static int find(int n){
        if(n == parent[n])
            return n;
        else
            return parent[n] = find(parent[n]);
    }
    
    public int solution(int n, int[][] costs) {
        int answer = 0;
        
        parent = new int[n];
        for(int i = 0; i < n; ++i)
            parent[i] = i;
        
        PriorityQueue<Node> pq = new PriorityQueue();
        for(int i = 0; i < costs.length; ++i) {
            pq.add(new Node(costs[i][0], costs[i][1], costs[i][2]));
        }
        
        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            if(find(cur.from) != find(cur.to)) {
                union(cur.from, cur.to);
                answer += cur.weight;
            }
        }
        
        return answer;
    }
}