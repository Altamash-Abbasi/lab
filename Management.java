import java.lang.reflect.*;
class Student {
    private String name;
    private Integer rollNo;
    private Double cgpa;
    private Boolean isScholarship;

   
    public Boolean compare(Student s1, Student s2) {
        double m1 = s1.cgpa; 
        double m2 = s2.cgpa; 
        return m1 == m2;
    }

   
    public static void behaviorWrapper(Integer r1, Integer r2) {
        if (r1 == r2)
            System.out.println("Wrapper objects are equal (==)");
        else
            System.out.println("Wrapper objects are NOT equal (==)");
    }

    
    public static void behaviorPrimitive(int r1, int r2) {
        if (r1 == r2)
            System.out.println("Primitive values are equal (==)");
        else
            System.out.println("Primitive values are NOT equal (==)");
    }

   
    public String getName() { return name; }
    public Integer getRollNo() { return rollNo; }
    public Double getCgpa() { return cgpa; }
    public Boolean getIsScholarship() { return isScholarship; }

    public void setName(String name) { this.name = name; }
    public void setRollNo(Integer rollNo) { this.rollNo = rollNo; }
    public void setCgpa(Double cgpa) { this.cgpa = cgpa; }
    public void setIsScholarship(Boolean isScholarship) { this.isScholarship = isScholarship; }

    
    public void addMultipleStudents(Student... students) {
        System.out.println("\nRegistering multiple students:");
        for (Student s : students) {
            System.out.println("Student Registered: " + s.getName());
        }
    }

   
    public void calculateAggregateMarks(String studentName, Double... subjectMarks) {
        double total = 0;
        for (Double mark : subjectMarks) {
            total += mark; 
        }
        double avg = subjectMarks.length > 0 ? total / subjectMarks.length : 0;
        System.out.println("\n" + studentName + "'s Aggregate Marks: " + avg);
    }

   
    enum Department {
        CSE("CS101", "Dr. Sharma"),
        IT("CS102", "Prof. Mehta"),
        ECE("EC101", "Dr. Khanna"),
        MECHANICAL("ME101", "Dr. Singh");

        private final String code;
        private final String hod;

        Department(String code, String hod) {
            this.code = code;
            this.hod = hod;
        }

        public String getCode() { return code; }
        public String getHod() { return hod; }
    }

    enum Grade {
        O(10),
        A_PLUS(9),
        A(8),
        B_PLUS(7),
        B(6),
        C(5),
        F(0);

        private final int gradePoint;

        Grade(int point) {
            this.gradePoint = point;
        }

        public int getGradePoint() { return gradePoint; }

        
        public static Grade marksToGrade(int marks) {
            if (marks >= 91) return O;
            else if (marks >= 81) return A_PLUS;
            else if (marks >= 71) return A;
            else if (marks >= 61) return B_PLUS;
            else if (marks >= 51) return B;
            else if (marks >= 41) return C;
            else return F;
        }
    }
	static public void re(Object o){
		Class c=o.getClass();
		System.out.println("Name of the class is "+c.getName());
		for(Field f:c.getDeclaredFields())
			System.out.println("Fields in class: "+f);
		for(Constructor c1:c.getDeclaredConstructors())
			System.out.println("Constructor in class: "+c1);
		for(Method m:c.getDeclaredMethods())
			System.out.println("Methods in class: "+m);
		for(Field f:c.getDeclaredFields()){
			Class g=f.getType();
			if(g.equals(Integer.class) || g.equals(Double.class) || g.equals(Boolean.class)) System.out.println("it is a wrapper class "+g.getName());
		}
        for(Method m:c.getDeclaredMethods()){
            if(m.getName().startsWith("get") && m.getParameterCount()==0){
                System.out.println("List of getters: "+m.getName());
            }
            if(m.getName().startsWith("set"))
            System.out.println("List of setters: "+m.getName());
        }
			
	}
}


public class Management extends Student {
    public static void main(String[] args) {
        
        Student s1 = new Student();
        Student s2 = new Student();

        s1.setRollNo(636);
        s2.setRollNo(636);

        behaviorPrimitive(63, 63);
        behaviorWrapper(s1.getRollNo(), s2.getRollNo());

        
        s1.setName("Altamash");
        s2.setName("Ayaan");
        s1.calculateAggregateMarks("Altamash", 88.5, 92.0, 79.0, 85.0);
        s1.addMultipleStudents(s1, s2);

       
        Department dept = Department.CSE;
        System.out.println("\nDepartment: " + dept + " | Code: " + dept.getCode() + " | HOD: " + dept.getHod());

       
        int marks = 87;
        Grade g = Grade.marksToGrade(marks);

        System.out.println("\nMarks: " + marks + " | Grade: " + g + " | Grade Points: " + g.getGradePoint());

        
        switch (g) {
            case O:
					System.out.println("Excellent! Marks between 91–100");
					break;
            case A_PLUS: 
					System.out.println("Very Good! Marks between 81–90");
					break;
            case A :
					System.out.println("Good! Marks between 71–80");
					break;
            case B_PLUS :
					System.out.println("Above Average! Marks between 61–70");
					break;
            case B :
					System.out.println("Average! Marks between 51–60");
					break;
            case C :
					System.out.println("Below Average! Marks between 41–50");
					break;
            case F :
				System.out.println("Fail! Marks below 40");
        }
		re(s1);
    }
}