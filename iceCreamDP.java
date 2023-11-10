import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import javafx.util.Pair;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'icecreamParlor' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER m
     *  2. INTEGER_ARRAY arr
     */
    
    public static Integer helperDP (int m, List<Integer> arr, int iceNeeded){
    int size = arr.size();
    List<Integer>[][][] arrayDP = new List[size+1][m+1][iceNeeded+1];
    for (int i = 0; i < arrayDP.length; i++) {
        for (int j = 0; j < arrayDP[0].length; j++) {
            arrayDP[i][j][0] = new ArrayList<>();
        }
    }
    for (int i = arrayDP.length - 2; i > -1; i--) {
        for (int money = 0; money < arrayDP[0].length; money++) {
            for (int iceCream = 1; iceCream < arrayDP[0][0].length; iceCream++) {
                int price = arr.get(i);
                List <Integer> add1 = null;
                List <Integer> dontadd = null;
                if (price <= money) {
                    List<Integer> add2 = arrayDP[i+1][money - price][iceCream - 1];
                    if (add2 != null) {
                        add1 = new ArrayList<>();
                        add1.add(i);
                        for (int k = 0; k < add2.size(); k++) {
                            add1.add(add2.get(k));
                        }
                    }
                }
                dontadd = arrayDP[i+1][money][iceCream];
                if (dontadd != null && add1 != null) {
                    int skip = 0;
                    int include = 0;
                    for (int h = 0; h < add1.size(); h++) {
                        include = include + arr.get(add1.get(h));
                    }
                    for (int j = 0; j < dontadd.size(); j++) {
                        skip = skip + arr.get(dontadd.get(j));
                    }
                    if (include == m) {
                        arrayDP[i][money][iceCream] = add1;
                    }
                    else if (skip == m) {
                        arrayDP[i][money][iceCream] = dontadd;
                    }
            
                }
                else if (dontadd != null) {
                    arrayDP[i][money][iceCream] = dontadd;
                }
                else if(add1 != null) {
                    arrayDP[i][money][iceCream] = add1;
                }
                else {
                    arrayDP[i][money][iceCream] = null;
                }
            }
        }
    }
    List<Integer> result = arrayDP[0][m][iceNeeded];
    List<Integer> result2 = new ArrayList<>();
    if (arrayDP[1][m-1][iceNeeded-1] != null) {
        result2 = arrayDP[1][m-1][iceNeeded-1];
    }
    List<Integer> combinations = new ArrayList <>();
    return (result.size() + result2.size())/2;
    }
    
    public static Integer icecreamParlor(int m, List<Integer> arr) {
    // Write your code here
    return helperDP(m, arr, 2);
    }
}



public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                int m = Integer.parseInt(bufferedReader.readLine().trim());

                int n = Integer.parseInt(bufferedReader.readLine().trim());

                List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                    .map(Integer::parseInt)
                    .collect(toList());

                Integer result = Result.icecreamParlor(m, arr);
                bufferedWriter.write(result.toString() + "\n");
                // bufferedWriter.write(
                //     result.stream()
                //         .map(Object::toString)
                //         .collect(joining(" "))
                //     + "\n"
                // );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}