//Automatic Caesar Cipher Decryptor Made By Hyekang Joo

//30k+ Word List Credits to Grady Ward (https://github.com/dwyl/english-words)



import java.io.IOException;
import java.util.*;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.stream.Collectors;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

                                     //  REPLACE THE FOLLOWING WITH YOUR OWN PATH TO words_alpha.txt FIRST!!! Then you are good to go!
                                     //      |        |     |      |     |    |      |   |
                                     //      V        V     V      V     V    V      V   V
        Path path = Paths.get("/Users/hyekangjoo/IdeaProjects/Caesar_Cipher/words_alpha.txt").toAbsolutePath();
        List<String> words = Files.lines(path).collect(Collectors.toList());
        String code = getInput();
        cipher(code, words);
    }

    public static String getInput(){
        Scanner kb = new Scanner(System.in);
        System.out.print("Enter the code: ");
        String code = kb.nextLine();
        kb.close();
        return code;
    }

    public static boolean isWord(String search, List<String> words) throws IOException{
        return words.stream().anyMatch(p->p.equalsIgnoreCase(search));
    }

    public static void cipher(String code, List<String> database) throws IOException{
        Queue<Integer> indexHolder;
        String index = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char[] indexArray = index.toCharArray();
        int keyChange = 0, mostCommonCounter = 0, keyRemembered = -1;
        String answer = code.toUpperCase();

        while(keyChange != 26) {

            indexHolder = new LinkedList<Integer>();
            String holder = "";
            int preInd = 0, commonCounter = 0;

            for (int i = 0; i < code.length(); i++) {
                char inQuestion = answer.charAt(i);

                for (int j = 0; j < 26; j++) {
                    if(inQuestion ==  ' '){
                        holder += ' ';
                        indexHolder.add(i);
                        break;
                    }
                    else if (inQuestion == indexArray[j]) {
                        holder += indexArray[(j + keyChange) % 26];
                        break;
                    }
                }
            }

            indexHolder.add(holder.length());

            for (int i = 0; i < indexHolder.size() + 1; i++) {
                if( isWord( holder.substring(preInd, indexHolder.peek()) , database) ){
                    commonCounter++;
                }
                preInd = indexHolder.remove() + 1;
            }

            if(mostCommonCounter < commonCounter){
                mostCommonCounter = commonCounter;
                keyRemembered = keyChange;
            }

            keyChange++;
        }

        if(keyRemembered == -1){
            System.out.println("Caesar Cipher Did Not Work.");
        }
        else
            cipher(answer,keyRemembered);
    }

    public static void cipher(String code, int ind){
        String index = "abcdefghijklmnopqrstuvwxyz";
        index = index.toUpperCase();
        String holder = "";
        char[] indexArray = index.toCharArray();
        String answer = code.toUpperCase();

        for(int i = 0; i < code.length(); i++){
            char inQuestion = answer.charAt(i);

            for(int j = 0; j < 26; j++){
                if(inQuestion ==  ' '){
                    holder += ' ';
                    break;
                }
                if(inQuestion == indexArray[j]){
                    holder += indexArray[(j+ind)%26];
                    break;
                }
            }
        }
        System.out.println(holder);
    }

}


