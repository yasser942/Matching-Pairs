import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Homework2 {
    public static void readTxt(Stack s1,String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            s1.push(scanner.nextLine());
        }
        scanner.close();
    }
    public static void show(Stack s, int capacity){
        Stack tempStack= new Stack(capacity);
        while (!s.isEmpty()){
            tempStack.push(s.pop());
        } while (!tempStack.isEmpty()){
            Object element =tempStack.pop();
            System.out.print(element+" ");
            s.push(element);
        }
    }
    public static int randomNumber ( int b){
        int random= (int)(1+(Math.random()*b));
        return random;
    }
    public static void randomizeStack(Stack s, int capacity){
        Stack temp1= new Stack(capacity);
        Stack temp2= new Stack(capacity);
        while (!s.isEmpty()){
            if(randomNumber(2)==1){
                temp1.push(s.pop());

            }else {
                temp2.push(s.pop());
            }
        }
        while (!temp1.isEmpty()){
            s.push(temp1.pop());
        }
        while (!temp2.isEmpty()){
            s.push(temp2.pop());
        }

    }
    public  static  void copyElements(Stack s1, Stack s2, int capacity){
        randomizeStack(s1,capacity);
        Stack temp= new Stack(capacity);
        while (!s1.isEmpty()){
            Object tempElement=s1.pop();
            s2.push(tempElement);
            temp.push(tempElement);

        }
        while (!temp.isEmpty()){
            s1.push(temp.pop());
        }
    }
    public static Object checkPairs(Stack s1 ,Stack s2 ,int capacity, int a, int b){
        Stack temp1= new Stack(capacity);
        Stack temp2= new Stack(capacity);
        Object element1=null;
        Object element2=null;



       while (!s1.isEmpty()){
           if (s1.size()==a){
               element1=s1.peek();
           }
           temp1.push(s1.pop());
       }
        while (!temp1.isEmpty()){
            s1.push(temp1.pop());
        }


        while (!s2.isEmpty()){
            if (s2.size()==b){
                element2=s2.peek();
            }
            temp2.push(s2.pop());
        }
        while (!temp2.isEmpty()){
            s2.push(temp2.pop());
        }


        if (element1==element2){
            return element1;
        }else {
            return null;

        }



    }
    public static void remove(Stack s1,Stack s2,int capacity,Object element){
        Stack temp1= new Stack(capacity);
        Stack temp2= new Stack(capacity);
        while (!s1.isEmpty()){
            Object t1=s1.pop();
            if (t1!=element){
                temp1.push(t1);
            }
        }
        while (!s2.isEmpty()){
            Object t2=s2.pop();
            if (t2!=element){
                temp2.push(t2);
            }
        }
        while (!temp1.isEmpty()){
            s1.push(temp1.pop());
        }
        while (!temp2.isEmpty()){
            s2.push(temp2.pop());
        }
    }
    public static void sort(Stack s1,Stack s2 ,int capacity , int score) throws FileNotFoundException {
        Stack temp1= new Stack(capacity);
        Stack temp2= new Stack(capacity);
        while ((int)s1.peek()<score){
            temp1.push(s1.pop());
            temp2.push(s2.pop());
        }
         temp1.push(score);
         temp2.push("YASSER");
       while (!s1.isEmpty()){
           temp1.push(s1.pop());
           temp2.push(s2.pop());
       }
        File outFile = new File("D:\\highscoretable.txt");

        PrintWriter out = new PrintWriter(outFile);
       for (int i=0; i<10;i++){
           out.println(temp2.pop());
           out.println(temp1.pop());
       }
        out.close();
    }
    public static void printHighScore(Stack sortedHighScore) throws FileNotFoundException {
        int counter=1;
        Stack temp= new Stack(20);
        readTxt(sortedHighScore,"C:\\Users\\HP\\Desktop\\yasser1.txt");
        while (!sortedHighScore.isEmpty()){
            temp.push(sortedHighScore.pop());
        }
        while (!temp.isEmpty()){

            System.out.printf("%-10s",String.valueOf(temp.pop()));
            if(counter%2==0){
                System.out.println();

            }
            counter++;
        }
    }
    public static void main(String[] args) throws FileNotFoundException {
        int stackCapacity=5;
        int a;
        int b;
        int score=0;
        int step=0;
        int maxRnd=stackCapacity;
        Stack s =new Stack(15);
        Stack s1= new Stack(stackCapacity);
        Stack s2= new Stack(stackCapacity);
        Stack names= new Stack(10);
        Stack scores= new Stack(10);
        Stack sortedHighScore = new Stack(20);
        readTxt(s,"D:\\fruits.txt");
        System.out.print("Fruits Stack :");
        show(s,15);
        System.out.println();
        randomizeStack(s,15);
        while (!s1.isFull()){
            s1.push(s.pop());
        }
        System.out.println();
        copyElements(s1,s2,stackCapacity);
        System.out.print("Stack 1 :");
        show(s1,stackCapacity);
        System.out.println();
        System.out.print("Stack 2 :");
        show(s2,stackCapacity);
        System.out.print("            Score = "+score);
        System.out.println();
        while (!s1.isEmpty()){
            step++;
            a=randomNumber(maxRnd);
            b=randomNumber(maxRnd);
            System.out.println();
            Object elementToDelete=checkPairs(s1,s2,stackCapacity,a,b);
            if (elementToDelete!=null){
                remove(s1,s2,stackCapacity,elementToDelete);
                maxRnd--;
                score+=20;
            }else {
                score-=1;
            }
            System.out.println("**Randomly generated numbers: "+a+"          "+b+"                  "+"Step= "+step+"\n"
                   + "                                                            Score= "+score);
            System.out.print("Stack 1 :");
            show(s1,stackCapacity);
            System.out.println();
            System.out.print("Stack 2 :");
            show(s2,stackCapacity);
            System.out.println();
        }
        System.out.println("The game is over !!.\n");
        System.out.println("Your score  is : "+score);
        File file2 = new File("D:\\highscoretable.txt");
        Scanner scanner2 = new Scanner(file2);
        while (scanner2.hasNextLine()) {
           names.push(scanner2.nextLine());
           scores.push(Integer.parseInt(scanner2.nextLine()));
        }
        sort(scores,names ,11,score);
        System.out.println();
        System.out.println("High Score table : ");
        System.out.println("----------------------");
        printHighScore(sortedHighScore);
    }
}
