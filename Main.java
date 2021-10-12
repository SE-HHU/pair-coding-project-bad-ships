import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Expression ex =new Expression();               //实例化表达式类
        infixToffix its=new infixToffix();         //实例化表达式计算类
        FileOperation fo=new FileOperation();          //实例化文件操作类

        File file1 = new File("Exercises.txt");
        File file2 = new File("Answers.txt");
        File file3 = new File("MyAnswers.txt");
        File file4 = new File("Grade.txt");

        int n=0,m=0,x=0,judge=0;                                   //参数默认值

        System.out.println("---------------四则运算程序---------------");
        //System.out.println("-r：参数数值范围");
        System.out.println("-n：输入题目参数");
        System.out.println("-g：查看测试结果");
        System.out.println("Do：执行程序，生成题目与答案");
        System.out.println("请输入指令：");

        Scanner in =new Scanner(System.in);
     a:   while(in.hasNext()){
            switch(in.next()){
                case "-n" :
                    System.out.println("请输入运算数的数值范围：");
                    m=in.nextInt();
                    System.out.println("请输入要生成的题目个数：");
                    n=in.nextInt();
                    System.out.println("若题目中有括号则输入1，否则输入0");
                    x=in.nextInt();
                    break;
                case "-g":
                    if (judge == 0) {
                        System.out.println("请先选择指令Do生成题目且将自己的答案写入MyAswers后再进行结果测试");
                        break ;

                    }
                    System.out.println("结束程序则输入0，继续程序则输入1");
                    int flag1 = in.nextInt();
                    if(flag1 ==0)
                        break  a;
                    fo.FileC(file2, file3, file4);        //答案和做题文档对比，结果写入Grade文档
                    break;
                case "Do":
                    if (n == 0||m == 0) {
                        System.out.println("请先选择指令-n输入要生成的题目的个数与参数范围");
                        break ;

                    }
                    judge = 1;
                    for(int i=0;i<n;i++){
                        String s=ex.CreatExp(n,m,x),fstr;   //生成随机表达式并求解
                        String rus=its.suffixToArithmetic(its.infixToSuffix(s));

                        fstr=i+1+":"+s+"\r\n";
                        fo.FileW(file1, fstr);            //表达式写入文档

                        fstr=i+1+":"+rus+"\r\n";
                        fo.FileW(file2, fstr);			  //答案写入文档
                    }
                    n=0;
                    m=0;
                    x=0;

                    System.out.println("请将自己计算的答案输入到MyAnswer文件中");
                    System.out.println("结束程序则输入0，继续程序则输入1");
                    int flag = in.nextInt();
                    if(flag ==0)
                        break  a;
                    break;
                default:
                    System.out.println("无效指令！");
                    break;
            }
            System.out.println("请输入指令：");
        }
        System.out.println("感谢使用");
    }
}