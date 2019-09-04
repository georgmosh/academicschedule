import java.io.PrintStream;
import java.util.ArrayList;

public class testecho {
	public static void main(String[] args) {
		ReadLessons reader = new ReadLessons();
		ReadTeachers reader2 = new ReadTeachers();
		ArrayList<Lesson> less = reader.readFile("lessons.txt");
		ArrayList<Teacher> teac =reader2.readFile("teachers.txt");
		State matrix = new State(less, teac);
		AStar impl = new AStar(matrix, less, teac);
	}
}