import java.util.LinkedList;

public class SimplifyRelativePath {

    public String simplifyPath(String path) {

        StringBuilder sb = new StringBuilder();

        if(path == null || path.length() == 0){
            return sb.toString();
        }


        LinkedList<String> stack = new LinkedList<>();

        char[] s = path.toCharArray();

        /*
        / a / / b / / / / c / d / / . / . / / . .
        0 1 2 3 4 5 6 7 8 9 10
                              1 2 3 4 5 6 7 8 9 10

        i = 20
        st => a-> b -> c -> d ->
        */

        for(int i=0; i < s.length; i++){


            StringBuilder word = new StringBuilder();
            while(i < s.length && s[i] != '/'){
                word.append(s[i]);
                i++;
            }

            String res = word.toString();

            if(res.equals("..")){
                if(stack.size() > 0) {
                    stack.removeLast();
                }
            } else if (res.equals("/") || res.equals(".") || res.isEmpty() || res.isBlank()){
                continue;
            }else {
                stack.addLast(res);
            }
        }



        while(stack.size() > 0){
            sb.append('/');
            sb.append(stack.removeFirst());
        }


        if(sb.length() == 0){
            sb.append('/');
        }

        return sb.toString();
    }

    //https://leetcode.com/problems/simplify-path/submissions/
    static public void main( String args[] ) {

        System.out.println(new SimplifyRelativePath().simplifyPath("/a/./b/../../c/"));
    }
}
