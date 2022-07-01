import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static java.util.Comparator.comparing;

class Student {
    public int id;
    public String name;
    Student(int id,String name){
        this.id = id;
        this.name = name;
    }
    public static ArrayList<Student> readData(String filepath){
        String line;
        ArrayList<Student> studentInfo = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filepath));
            while ((line = br.readLine()) != null)
            {
                String[] student = line.split(",");
                studentInfo.add(new Student(Integer.parseInt(student[0]),student[1]));
            }
            br.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return studentInfo;
    }
}

class StudentResult {
    public int id;
    public double gpa;
    public String subject;
    StudentResult(int id,double gpa,String subject){
        this.id = id;
        this.gpa = gpa;
        this.subject = subject;
    }
    public static ArrayList<StudentResult> readData(String filepath){
        String line;
        ArrayList<StudentResult> studentResults = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filepath));
            while ((line = br.readLine()) != null)
            {
                String[] studentResult = line.split(",");
                studentResults.add(new StudentResult(Integer.parseInt(studentResult[0]),Double.parseDouble(studentResult[1]),studentResult[2]));
            }
            br.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return studentResults;
    }
}

class StudentCGPA{
    public int id;
    public String name;
    public double cgpa;
    StudentCGPA(int id, String name, double cgpa){
        this.id = id;
        this.name = name;
        this.cgpa = cgpa;
    }
}

public class StudentData {
    public static void main(String[] args) {
        ArrayList<Student> studentInfo = Student.readData("dummy_data_id.csv");
        ArrayList<StudentResult> studentResults = StudentResult.readData("dummy_data.csv");

        for(Student student: studentInfo){
            System.out.println(student.id+" "+student.name);
        }

        for(StudentResult studentResult: studentResults){
            System.out.println(studentResult.id +" " + studentResult.subject +" " + studentResult.gpa);
        }

        ArrayList<StudentCGPA> cgpaList = new ArrayList<>();
        for(Student student: studentInfo){
            int subjectCount = 0;
            double cgpa = 0;
            for(StudentResult studentResult: studentResults){
                if(studentResult.id==student.id){
                    cgpa+=studentResult.gpa;
                    subjectCount++;
                }
            }
            cgpa/=subjectCount;
            cgpaList.add(new StudentCGPA(student.id,student.name,cgpa));
        }

        for(StudentCGPA infoCGPA: cgpaList){
            System.out.println(infoCGPA.cgpa+" "+infoCGPA.id+" "+infoCGPA.name);
        }

        Collections.sort(cgpaList, new Comparator<StudentCGPA>() {
            @Override
            public int compare(StudentCGPA lhs, StudentCGPA rhs) {
                if(lhs.cgpa!=rhs.cgpa){
                    return lhs.cgpa<rhs.cgpa?1:-1;
                }
                else if(lhs.id!=rhs.id){
                    return lhs.id<rhs.id?1:-1;
                }
                return lhs.name.compareTo(rhs.name);
            }
        });

        for(StudentCGPA infoCGPA: cgpaList){
            System.out.println(infoCGPA.cgpa+" "+infoCGPA.id+" "+ infoCGPA.name);
        }

        try {
            FileWriter writer = new FileWriter("cgpaSorted.csv");
            BufferedWriter bwr = new BufferedWriter(writer);
            for(StudentCGPA infoCGPA: cgpaList){
                System.out.println(infoCGPA.cgpa+" "+infoCGPA.id+" "+ infoCGPA.name);
                bwr.write(infoCGPA.cgpa+","+infoCGPA.id+","+ infoCGPA.name);
                bwr.write("\n");
            }
            bwr.close();
            System.out.println("Successfully written to a file");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
