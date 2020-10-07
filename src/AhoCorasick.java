import java.net.Inet4Address;
import java.util.*;

class AhoCorasick {

    static class TrieNode {
        private final Character representative;

        private final HashMap<Character,TrieNode> children;

        private TrieNode suffixLink;
        private TrieNode outputLink;

        private final Set<Integer> outputStringIndices;

        private boolean isWord;

        TrieNode(Character data){
            this.representative = data;
            this.children = new HashMap<>();
            this.outputStringIndices = new HashSet<>();
        }

        public TrieNode getChild(Character child){
            if(children.containsKey(child)){
                return children.get(child);
            }
            return null;
        }

        public boolean addChild(Character data, TrieNode child){
            if(isChildExists(data)){
                return false;
            }

            children.put(data,child);
            return true;
        }

        public boolean isChildExists(Character child){
            return children.containsKey(child);
        }

        public void setSuffixLink(TrieNode suffixLink){
            this.suffixLink = suffixLink;
        }

        public TrieNode getSuffixLink() {
            return suffixLink;
        }

        public void setOutputLink(TrieNode outputLink){
            this.outputLink = outputLink;
        }

        public TrieNode getOutputLink() {
            return outputLink;
        }

        public Character getData(){
            return this.representative;
        }

        public Set<Integer> getOutputStringIndices(){
            return this.outputStringIndices;
        }

        public void addOutputStringIndex(int i){
            this.outputStringIndices.add(i);
        }

        public boolean getIsWord(){
            return this.isWord;
        }

        public void setIsWord(boolean isWord){
            this.isWord = isWord;
        }

    }

    private final TrieNode root;
    private final int defaultIndex = -1;

    private void insert(String pattern){
        insert(pattern, defaultIndex);
    }

    private void insert(String word, int patternIndex){

        if(word != null){
            TrieNode currentNode = root;

            for(char c : word.toCharArray()){

                if(currentNode.isChildExists(c)){
                    currentNode = currentNode.getChild(c);
                }else {
                    currentNode = new TrieNode(c);
                }

            }

            if(patternIndex != this.defaultIndex){
                currentNode.addOutputStringIndex(patternIndex);
            }

            currentNode.setIsWord(true);

        }
    }

    /*
    builds trie in O(n); where n = n1 + n2 + n3 +... + ni
     */
    private void insertPatterns(List<String> patterns){

        if(patterns == null){
            return;
        }

        for(int i=0; i < patterns.size(); i++){
            this.insert(patterns.get(i),i);
        }
    }

    private TrieNode getPrefixNode(String key){
        TrieNode currentNode = this.root;

        for(char c: key.toCharArray()){
            if (!currentNode.isChildExists(c)){
                return null;
            }

            currentNode = currentNode.getChild(c);
        }

        return currentNode;
    }

    public boolean containsPrefix(String key){
        TrieNode currentNode = getPrefixNode(key);
        return currentNode != null;
    }

    public boolean containsWord(String key){
        TrieNode currentNode = getPrefixNode(key);

        if(currentNode == null) return false;

        return currentNode.getIsWord();
    }


    /*
    create suffix link for each node; works in O(n) time
    picks up parent suffix link to succeed the automaton search
    Same logic as KMP LPS array but in trie automaton
    Since it a tree and we need to process nodes in level order hence use bfs
     */
    private void buildSuffixLinks() {

        Queue<TrieNode> queue = new LinkedList<>();

        for(TrieNode firstLevelChild : this.root.children.values()){
            firstLevelChild.setSuffixLink(this.root);
            queue.add(firstLevelChild);
        }

        while (!queue.isEmpty()){

            TrieNode parent = queue.poll();

            for(TrieNode child : parent.children.values()){


                TrieNode potentialSuffixLink = parent.getSuffixLink();

                //at max this this while loop work till O(h); h is the longest size of pattern
                while (potentialSuffixLink != this.root){

                    if(potentialSuffixLink.isChildExists(child.getData())){
                        break;
                    }

                    potentialSuffixLink = potentialSuffixLink.getSuffixLink();

                }

                child.setSuffixLink(potentialSuffixLink);

            }

        }

    }

    /*
    creates output links in O(n) time
    for a node if suffix link node is a word then point it else point suffix link's output link
    Since it a tree and we need to process nodes in level order hence use bfs
     */
    private void buildOutputLinks() {

        Queue<TrieNode> queue = new LinkedList<>(this.root.children.values());

        while (!queue.isEmpty()){

            TrieNode node = queue.poll();

            TrieNode suffixLink  = node.getSuffixLink();

            if(suffixLink != null && suffixLink.getIsWord()){
                node.setOutputLink(suffixLink);
            }else{
                TrieNode outputLink = suffixLink == null ? null : suffixLink.outputLink;
                node.setOutputLink(outputLink);
            }
        }

    }


    /*
    matches all the patterns from automaton in the text. works in O(m+z); m = length of text; z = maximum number of matches
     */
    public HashMap<Integer, List<Integer>> matchText(String inputText){

        HashMap<Integer, List<Integer>> patternIndexToTextIndexMatches = new HashMap<>();

        if(inputText == null || inputText.length() == 0){
            return patternIndexToTextIndexMatches;
        }


        char[] text = inputText.toCharArray();

        TrieNode node = this.root;

        for(int i = 0; i < text.length; ){

            char childData = text[i];

            if(node.isChildExists(childData)){

                node = node.getChild(childData);

                TrieNode outputNode  = node;
                while (outputNode.getSuffixLink() != null){

                    for(Integer patternIndex : outputNode.getSuffixLink().getOutputStringIndices()){

                        if(!patternIndexToTextIndexMatches.containsKey(patternIndex)){
                            patternIndexToTextIndexMatches.put(patternIndex, new ArrayList<>());
                        }

                        patternIndexToTextIndexMatches.get(patternIndex).add(i);

                    }

                    outputNode = outputNode.getOutputLink();

                }

                i++;

            }else if (node == this.root){
                i++;
            }else {
                node = node.getSuffixLink();
            }

        }


        return patternIndexToTextIndexMatches;
    }


    /*
    Initializes trie automaton with input patterns
    works in O(n) + O(n) + O(n) = O(n) time

    Better suitable when text changes and patterns don't

    (if patterns changes and text don't then build a suffix trie out of text for all text suffix.
    if any pattern is prefix of a suffix chain in trie then pattern has been matched)
     */
    AhoCorasick(List<String> patterns){
        this.root = new TrieNode(' ');

        this.root.setSuffixLink(this.root);

        this.insertPatterns(patterns);

        this.buildSuffixLinks();

        this.buildOutputLinks();
    }
}
