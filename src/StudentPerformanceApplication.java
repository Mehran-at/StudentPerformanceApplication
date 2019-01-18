import java.util.List;
import java.util.stream.Collectors;
//Let’s have a look at student performances. We received an american survey that shows the following relevant topics regarding their students:
//          * Their gender
//          * Their parental level of education
//          * Their math score
//          * Their reading score
//          * Their writing score
//Develop a code that displays the answer to the following questions:
//          o How many boys and girls are there?
//          o Which are the distinct parental levels of education sorted alphabetically?
//          o How many students scored higher than a 90 on every topic?
//How many students scored exactly 100 on every topic? Which ones are their genders?
//      Hints:
//          > You can answer these questions without creating any special class to hold the student performance information.
//          > You can use the “count” terminal operation to simplify answering questions that just ask for a number.
//--------------------------------------------------------------------------------------------------------
//Total number of student performance entries: 1000
//Number of female students: 518
//Number of male students: 482
//Parental education levels sorted alphabetically:
//[associate's degree, bachelor's degree, high school, master's degree, some college, some high school]
//Number of students with scores higher than 90: 23
//Number of students with scores equal to 100: 3
//Genders of these students are: [female, male, female]
//----------------------------------------------------------------------------------------------------------
public class StudentPerformanceApplication {
    public static void main(String[] args) {
        FileReader fileReader = new FileReader();
        List<String> students = fileReader.asList("studentsPerformance.csv");
        long list = students.stream()
                .skip(1)
                .count();
        System.out.println("Total number of student performance entries: " + list);
        long female = students.stream()
                .filter(e -> e.startsWith("female"))
                .count();
        System.out.print("Number of female students: " + female);
        long male = students.stream()
                .filter(e -> e.startsWith( "male"))
                .count();
        System.out.println("\nNumber of male students: " + male);
        List<String> distinction = students.stream()
                .map(e -> e.split(";"))
                .map(e -> e[1])
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        System.out.println("Parental education levels sorted alphabetically: " + "\n" + distinction);
        int topScore = 90;
        long count = students.stream()
                .skip(1)
                .map(e -> e.split(";"))
                .filter(e -> Integer.valueOf(e[2]) > topScore)
                .filter(e -> Integer.valueOf(e[3]) > topScore)
                .filter(e -> Integer.valueOf(e[4]) > topScore)
                .count();
        System.out.println("Number of students with scores higher than 90: " + count);
        int highestScore = 100;
        List<String> topStudentss = students.stream()
                .skip(1)
                .map(e -> e.split(";"))
                .filter(e -> Integer.valueOf(e[2]) == highestScore && Integer.valueOf(e[3]) == highestScore && Integer.valueOf(e[4]) == highestScore)
                .map(e -> e[0])
                .collect(Collectors.toList());
        System.out.println("Number of students with scores equal to 100: " + topStudentss.size());
        System.out.println("Genders of these students are: " + topStudentss);




    }
}