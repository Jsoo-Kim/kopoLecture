import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class ClassRoom {
	String name;
	Student[] students;
	
	ClassRoom(String name) {
		this.name = name;
	}
	
	public void setStudents(int numberOfStudents) {
		Random random = new Random();
		this.students = new Student[numberOfStudents];
		
		for (int i = 0; i < students.length; i++) {
			String name = "" + i;
			int middleScore = random.nextInt(1001);
			int finalScore = random.nextInt(1001);
			students[i] = new Student(name, middleScore, finalScore);
		}
		
		Arrays.sort(students, new Comparator<Student>() {

			@Override
			public int compare(Student o1, Student o2) {
				return o2.totalScore - o1.totalScore;
			}
			
		});
		
		// 정렬을 이렇게 해도 됨
//		for (int i = 0; i < students.length; i++) {
//			for (int j = 0; j < students.length - 1; j++) {
//				if (students[i].totalScore < students[j].totalScore) {
//					Student tmp = students[i];
//					students[i] = students[j];
//					students[j] = tmp;
//				}
//			}
//		} 
		
	}
}
