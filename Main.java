import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        matchPattern("caats","ca+t");
        if (args.length != 2 || !args[0].equals("-E")) {
            System.out.println("Usage: ./your_program.sh -E <pattern>");
            System.exit(1);
        }

        String pattern = args[1];
        Scanner scanner = new Scanner(System.in);
        String inputLine = scanner.nextLine();

        // You can use print statements as follows for debugging, they'll be visible when running tests.
        System.err.println("Logs from your program will appear here!");

        if (matchPattern(inputLine, pattern)) {
            System.exit(0);
        } else {
            System.exit(1);
        }
    }

    public static boolean matchCapitalLetters(String inputLine){
        for(int i=0;i<inputLine.length();i++){
            if(inputLine.charAt(i)>='A'&&inputLine.charAt(i)<='Z'){
                return true;
            }
        }
        return false;
    }
    public static boolean matchSmallerLetters(String inputLine){
        for(int i=0;i<inputLine.length();i++){
            if(inputLine.charAt(i)>='a'&&inputLine.charAt(i)<='z'){
                return true ;
            }
        }
        return false;
    }
    static boolean positiveCharacterGroups(String s,String t){
        char []patten =  t.toCharArray();
        for(int i=0;i<patten.length;i++) {
            if (s.contains(patten[i]+"")){
                return true;
            }
        }
        return false;
    }
    //w d,[],[^]
    public static boolean matchPatternV2(String s,String t){
        int i = 0 ;
        try {
            for (int j = 0; j < t.length(); j++) {
                if (t.charAt(j) == '\\') {
                    j++;
                    if (t.charAt(j)=='d'){
                        if(s.charAt(i)>='0'&&s.charAt(i)<='9'){
                            i++;
                            continue;
                        }
                        else{
                            return false;
                        }
                    }
                    else if(t.charAt(j)=='w'){
                        String temp = s.substring(i,i+1);
                        boolean x =matchAnyNumber(temp) || matchCapitalLetters(temp) || temp.contains("_") || matchSmallerLetters(temp);
                        i++;
                        if(x){
                            continue;
                        }
                        return false;
                    }
                }
                if(s.charAt(i)==t.charAt(j)){
                    i++;
                }
                else {
                    return false;
                }
            }
            
        }
        catch (Exception e){
            // System.out.println("Invalid Expression");
            return false;
        }
        return true; 
    }
    static boolean computeOneOrMore(String s, String t){
        int i=0;
        char pre = ' ';
        while(i<s.length()&&i<t.length()&&s.charAt(i)==t.charAt(i)){
            pre = s.charAt(i);
            i++;
        }
        if(t.charAt(i)!='+') return false;
        if(i+1==t.length()) return true;
        for(int j=i;j<s.length();j++){
            if(pre!=s.charAt(j-1)){
                return false;
            }
            if(s.substring(j,j+t.substring(i+1).length()).equals(t.substring(i+1))){
                return true;
            }
        }
        return false;
    }
    static boolean computeZeroOrOne(String s,String t){
        int indexof = t.indexOf('?');
        return s.contains(t.substring(0,indexof-1)+t.substring(indexof+1))||
                s.contains(t.substring(0,indexof)+t.substring(indexof+1));
    }
    public static boolean computeDot(String s,String t){
        // int i=0;
        // if(t.contains(".?")){
        //     return computeDot
        // }
        // while(i<s.length()){
        //     if(s.charAt(i)!=t.charAt(i)){
        //         if(t.charAt(i)=='.'){
        //             continue;
        //         }
        //         return false;
        //     }
        //     i++;
        // }
        // return i==Math.max(s.length(),t.length());
        return true;
    }
    public static boolean matchPattern(String inputLine, String pattern) {
        //checks if there are any digits in the string
        if(pattern.contains(".")){
            for(int i=0;i<inputLine.length();i++){
                if(computeDot(inputLine.substring(i),pattern)){
                    return true;
                }
            }
            return false;
        }
        if(pattern.contains("?")){
            for(int i=0;i<inputLine.length();i++){
                if(computeZeroOrOne(inputLine.substring(i), pattern))
                    return true;
            }
            return false;
        }
        if(pattern.contains("+")){
            for(int i=0;i<inputLine.length();i++){
                if(computeOneOrMore(inputLine.substring(i),pattern)){
                    return true;
                }
            }
            return false;
        }
        if(pattern.charAt(pattern.length()-1)=='$'){
            if(inputLine.length()<pattern.length()-1) return false;
            return inputLine.substring(inputLine.length()-pattern.length()+1).equals(pattern.substring(0,pattern.length()-1));
        }
        if(pattern.charAt(0)=='^'){
            int i=0;
            while(i<inputLine.length()&&i+1<pattern.length()&&inputLine.charAt(i)==pattern.charAt(i+1)){
                i++;
            }
            return i+1==pattern.length();
        }else if(pattern.charAt(0)=='['&&pattern.charAt(pattern.length()-1)==']'){
            if(pattern.charAt(1)=='^')
                return !positiveCharacterGroups(inputLine,pattern.substring(2,pattern.length()-1 ));
            return positiveCharacterGroups(inputLine,pattern.substring(1,pattern.length()-1 ));
        } else if(pattern.equals("\\w")){
            return matchAnyNumber(inputLine) || matchCapitalLetters(inputLine) || inputLine.contains("_") || matchSmallerLetters(inputLine);
        } else if(pattern.equals("\\d")){
            return matchAnyNumber(inputLine);
        }else if (pattern.length() == 1) {
            return inputLine.contains(pattern);
        }else if(pattern.contains("\\w")||pattern.contains("\\d")){
            for(int i=0;i<inputLine.length();i++){
                boolean result = matchPatternV2(inputLine.substring(i),pattern);
                if(result){
                    return true;
                }
            }
            return false;
        }

        else {
            throw new RuntimeException("Unhandled pattern: " + pattern);
        }

    }

    public static boolean matchAnyNumber(String inputLine) {
        for(int i=0;i<inputLine.length();i++){
            if(inputLine.charAt(i)>='0'&&inputLine.charAt(i)<='9'){
                return true;
            }
        }
        return false;
    }
}
