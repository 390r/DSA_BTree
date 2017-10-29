

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException{
        boolean isChar = false;

        Scanner sc = new Scanner(new File("input.txt"));
        PrintWriter wr = new PrintWriter("output.txt");

        String inputStr = "";
        inputStr = sc.nextLine();

        String[] strArr;
        strArr = inputStr.split(" ");

        // READING

        int[] arr = new int[strArr.length];

        if (Character.isLetter(inputStr.charAt(0)))
            isChar = true;

        for(int i = 0; i < strArr.length; i++){
            if (isChar)
                arr[i] = (int)strArr[i].charAt(0);
            else
                arr[i] = Integer.parseInt(strArr[i]);
        }


        MyBTree tree = new MyBTree();


        // INSERTION

        for (int i = 0; i < arr.length; i++){
            tree.insert(arr[i]);
        }


        // OUTPUT

        if (isChar) {
            wr.print(toChars(tree.inOrderTrav(tree.root).split(" ")));
        }
        else
            wr.print(tree.inOrderTrav(tree.root));


        wr.close();
    }


    // just converts int to chars, if needed.
    public static String toChars(String[] strArr){
        String str = "";
        for (int i = 0; i < strArr.length; i++){
            str += (char)(Integer.parseInt(strArr[i])) + " ";
        }
        return str;
    }

}
