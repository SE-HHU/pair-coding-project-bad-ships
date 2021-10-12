import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CheckWeight {
    List<String> exp =new ArrayList<String>();
    List<String> ans =new ArrayList<String>();

    public List checkAnswer(){                             //第一步比较答案，答案相同再进行下一步判断，提高效率
        File file1 = new File("Exercises.txt");
        File file2 = new File("Answers.txt");

        List<String> find=new ArrayList<String>();         //用于存取重复表达式的信息

        try{                                               //将题目和答案扫描存入list表中
            BufferedReader br1 = new BufferedReader(new FileReader(file1));
            String s=null;
            while((s = br1.readLine())!=null){
                s=s.substring(s.indexOf(":")+1,s.length());
                exp.add(s);
            }

            BufferedReader br2 = new BufferedReader(new FileReader(file2));
            while((s = br2.readLine())!=null){
                s=s.substring(s.indexOf(":")+1,s.length());
                ans.add(s);
            }

        }catch (IOException e) {
            e.printStackTrace();
        }

        for(int i=0;i<ans.size()-1;i++){                    //冒泡排序查找相同答案，再进行下一步判断
            String s="";
            for(int j=i+1;j<ans.size();j++){
                if(ans.get(i).equals(ans.get(j))){
                    if(checkExp(exp.get(i),exp.get(j)))
                        s+=(i+1)+","+exp.get(i)+" Repeat "+(j+1)+","+exp.get(j)+"  ";
                }
            }
            if(s.length()>0)
                find.add(s);
        }
        return find;
    }

    public boolean checkExp(String exp1,String exp2){       //中缀转后缀，通过比较两表达式字符判断
        infixToffix its=new infixToffix();
        exp1=its.infixToSuffix(exp1);
        exp2=its.infixToSuffix(exp2);

        String[] strings = exp1.split(" ");

        for(int i=0;i<strings.length;i++){
            if(exp2.indexOf(strings[i])==-1)
                return false;
        }
        return true;
    }

}

