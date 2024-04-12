public class Solution {
    /**
     * @param words: a set of words without duplicates
     * @return: all word squares
     *          we will sort your return value in output
     */
    class TrieNode{
        TrieNode [] children;
        List<String> startsWith;
        public TrieNode(){
            this.startsWith = new ArrayList<>();
            this.children = new TrieNode[26];
        }
    }
    private void insert( TrieNode root, String word){
        TrieNode curr = root;
        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if(curr.children[c - 'a'] == null){
                curr.children[c - 'a'] = new TrieNode();
            }
            curr = curr.children[c - 'a'];
            curr.startsWith.add(word);
        }
    }
    private List<String> search(TrieNode root, String prefix){
        TrieNode curr = root;
        for(int i = 0; i < prefix.length(); i++){
            char c = prefix.charAt(i);
            if(curr.children[c - 'a'] == null){
                return new ArrayList<>();
            }
            curr = curr.children[c- 'a']; 
        }
        return curr.startsWith;
    }
    List<List<String>> result;
    public List<List<String>> wordSquares(String[] words) {
        // write your code here
        this.result = new ArrayList<>();
        TrieNode root = new TrieNode();
        for(String word : words){
            insert(root,word);
        }
        List<String> li = new ArrayList<>();
        for(int i = 0; i < words.length; i++){
            li.add(words[i]);
            //recurse
            backtrack(root,li);
            //backtrack
            li.remove(li.size() - 1);
        }
       return result;
    }

    private void backtrack(TrieNode root, List<String> li){
        //base
        if(li.size() == li.get(0).length()){
            result.add(new ArrayList<>());
            return;
        }
        //logic
        //get curr prefix 
        String prefix = "";
        int idx = li.size();
         for(String word : li){
             prefix = prefix + word.charAt(idx);
         }
        //get all the words from Trie which are startwith this prefix
        List<String> startsWith  = search(root, prefix);
        for(String word: startsWith){
            li.add(word);
            backtrack(root, li);
            li.remove(li.size() - 1);
        }
    }










}
