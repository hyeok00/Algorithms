import java.util.*;

class TrieNode{
    public Map<Character, TrieNode> childNodes = new HashMap();
    public int count;
    public boolean isLast;
}
class Trie{
    TrieNode root;
    
    Trie(){
        root = new TrieNode();
    }
    
    void insert(String word){
        TrieNode node = this.root;
        
        for (int i = 0; i < word.length(); i++){
            node.count++;
            node = node.childNodes.computeIfAbsent(word.charAt(i), c -> new TrieNode());
        }
        node.count++;
        node.isLast = true;
    }
    
    int getMinTypingCount(String word){
        TrieNode node = this.root;
        
        int length = word.length();
        int last = length;
        
        for (int i = 0; i < length; i++){
            if(node.count == 1){
                last = i;
                break;
            }
            node = node.childNodes.get(word.charAt(i));
		}
        return last;
    }

}

class Solution {
    public int solution(String[] words) {
        Trie myTrie = new Trie();
        for(String str : words)
            myTrie.insert(str);
        
        int answer = 0;
        for(String str : words)
            answer += myTrie.getMinTypingCount(str);
        
        return answer;
    }
}