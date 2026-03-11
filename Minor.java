import java.util.*;
public class Minor {
    public static void main(String[] args) {
        String[] s=new String[5];
        Scanner sc=new Scanner(System.in);
        System.out.println("enter list of string to sort");
        for(int  i=0;i<5;i++){
            s[i]=sc.nextLine();
        }
        LinkedList<String>[] bucket=new LinkedList[27];
        for(int i=0;i<27;i++){
            bucket[i]=new LinkedList<String>();
        }
        int max=0;
        for(String s1:s){
            if(s1.length()>max) max=s1.length();
        }   
        int pos=1;
        for(int i=0;i<max;i++){
            for(int j=0;j<s.length;j++){
                char ch;
                if(pos>s[j].length()) ch='\0';
                else
                    ch=s[j].charAt(s[j].length()-pos);
                bucket[index(ch)].add(s[j]);
            }
            int l=0;
            for(int k=0;k<27;k++){
                while(!bucket[k].isEmpty()){
                    s[l]=bucket[k].remove();
                    l++;
                }
            }
            pos++;
        }
        System.out.println("The sorted list is: ");
        for(int i=0;i<s.length;i++){
            System.out.println(s[i]);
        }
    }
    static int index(char ch){
        if(ch=='\0')    return 0;
        else return ch-'a'+1;
    }
}