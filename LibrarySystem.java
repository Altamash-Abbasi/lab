import java.util.logging.*;
class BookNotAvailableException extends Exception{
    @Override
    public String toString(){
        return "Book is not available";
    }
} 
class Book{
    Book(int id,String name,int quant){
        assert quant>0:"Quantity of book should be greater than 0";
        BId=id;
        BookName=name;
        BookQuantity=quant;
    }
    int BId;
    String BookName;
    int BookQuantity;
}
class Student{
    Student(int id,String name,int age){
        assert age>0:"Age should be greater than 0";
        sId=id;
        stName=name;
        stAge=age;
    }
    int sId;
    String stName;
    int stAge;
}
class LibraryOperation{
    static int[][] Record=new int[10][10];
    static Logger logger=Logger.getLogger("Lib");
   static{
     try{
        FileHandler fh=new FileHandler("lfile.log",true);
        fh.setFormatter(new SimpleFormatter());
        logger.addHandler(fh);
        logger.setLevel(Level.ALL);
    }
    catch(Exception e){
        System.out.println("File logger not created");
    }
}
    static void issueBook(Book b,Student s){
        try{
            if(s==null){
                System.out.println("Throwing NullPointerException because student is null");
                throw new NullPointerException();
            } 
            if(b.BookQuantity<=0){ 
                logger.severe("BookNotAvailableException occured");
                throw new BookNotAvailableException();
            }
             else{
                b.BookQuantity--;
                Record[b.BId][s.sId]=1;
                logger.info("Book "+b.BookName+" is issued to "+s.stName);
            }
            System.out.println("Book "+b.BookName+" is issued to "+s.stName+" successfully");
        }
        catch(NullPointerException e){
            System.out.println("NUllPointerException occured");
            logger.severe("NullPointerException occured");
        }
        catch(BookNotAvailableException e){
            System.out.println(e);
        }
    }
}
public class LibrarySystem {
    public static void main(String[] args){
        Book b=new Book(1,"DSA",1);
        Student s=new Student(1,"Fardeen",22);
        LibraryOperation.issueBook(b,s);
        Student s2=new Student(2,"Shahzan",21);
        LibraryOperation.issueBook(b, s2);
    }
}
