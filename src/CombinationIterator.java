import java.util.*;
class CombinationIterator {

    private Iterator<String> iterator;
    private List<String> combinations = new ArrayList<>();

    public CombinationIterator(String characters, int combinationLength) {
        findStrings(characters, 0, combinationLength, new StringBuilder());
        iterator = combinations.iterator();
    }

    private void findStrings(String characters, int index, int maxLength, StringBuilder sb){

        if(sb.length() == maxLength){
            combinations.add(sb.toString());
            return;
        }

        if(index >= characters.length()){
            return;
        }

        //include
        sb.append(characters.charAt(index));
        findStrings(characters, index+1, maxLength, sb);
        sb.deleteCharAt(sb.length()-1);

        //don't include 
        findStrings(characters, index+1, maxLength, sb);
    }

    public String next() {
        return iterator.next();
    }

    public boolean hasNext() {
        return iterator.hasNext();
    }

    /*
            1. Scrape the www (figure out words)
            2. Build the dictionary (with existing dict figure out whether it is new or not)
            3. Find probably meaning of the words in dictionary
            4. tend towards to most acc meaning

            1. func/ non func require
            2. data size estimation (in,outgress)
            3. Api design(understanding part)
            4. Data model design
            5. High level design
            6. Deep dive in component design (1,2)
            7. bottlenecks or single point of failure

            1. func/ non func require
            1.1
            1.2 consistent

            3. Api design(understanding part)
            3.1 Crawling
            3.2 DictionaryService

            4. Data model design
            4.1 Crawling
                BFS
                visitedSet = [] -> DB
                toVisitSet = [] -> message broker

            4.2 DictionaryService

            5. High level design

            Crawling  --text--> DictionaryService
            |
            |
            |
            CrawlService(seedUrl) ---> toVisitSetService (message broker)
            |
            |
            VisitedSet (DB)


            DictionaryService ( text )
            |
            |
            WordPreProcessor(text) (have minSpellDistanceThreshold discard check) --> trie --> dump seperate table
            |
            |
            |
            WordService -- ----------------------> Accurate word meaning DB
            |
            |
            word, lineUsedText  (table)
            |
            |
            |


            1. Take input of list of words
            2. Check if word is present in our Accurate word meaning DB
            3. if it is not present then insert in wordsTobeProcessed table


            class WordsService {


               public Pair<String, String> processWords(Pair<String,String> inputWord){

                      if(inputWord == null) {
                        return null;
                      }


                      if(isNotPresentInAccurateDB(inputWord)) {

                        return addToWordsTobeProcessed(inputWord);
                      }

                      return null;
               }

               public boolean isNotPresentInAccurateDB(Pair<String,String> inputWord) {

               //sql query with index on word


               }
            }
         */
}

/**
 * Your CombinationIterator object will be instantiated and called as such:
 * CombinationIterator obj = new CombinationIterator(characters, combinationLength);
 * String param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */