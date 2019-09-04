import java.util.Comparator;
import java.io.PrintStream;
import java.util.ArrayList;

/**
 * State.java
 * Represents a full secondary school weekly program instance; using a 3D array. Data is
 * inserted to the program using ArrayLists, for lesson and teacher instances to substitute it
 * respectively, while there are three formats of getters available.
 * @authors  G. Moschovis (p3150113@aueb.gr)
 */
public class State implements Comparator, Comparable<State> {
	/**
	 * Represents a 3D(i x j x k)= (9 x 5 x 7)Array of Vec2<Lesson, Teacher> values of 
	 * Lesson-Teacher combinations. 
	 * i = the specific classroom that dimension references
	 * j = numeric representation for the weekdays (1 = Monday, 2 = Tuesday etc.)
	 * k = numeric representation for the school hours
	 */
	protected Vec2<Lesson, Teacher>[][][] state;
	
	/**
     * A priority field; that indicates the state's priority; assumed by an euretic.
     */
	  protected double priority;
	
	/**
	 * Overloaded constructor creating an instance of State Class with its matrix assigned default
	 * lesson and associated values; as read from the relevant text files through ReadLessons.java
	 * class. Lesson instances are being imported from a Lessons' Arraylist; whereas Teacher
	 * instances are being imported from a Teachers' Arraylist and associated to lessons they are 
	 * knowledgeable to teach in class.
	 * @param L The Lesson's Arraylist Lesson instances are being imported from relevant text file
	 * @param T The Teacher's Arraylist Teacher instances are being imported from relevant text file
	 */
	public State(ArrayList<Lesson> L, ArrayList<Teacher> T) {
		state = (Vec2<Lesson, Teacher>[][][])new Vec2[9][5][7];
		for(int n = 0; n < T.size(); n++) {
			Teacher t = T.get(n);
			t.setLesson(findLessons(L, t.getLessonID()));
			for(int m1 = 0; m1 < t.getLesson().length && t.getminW() > 0; m1++) {
				Lesson l = t.getLesson()[m1];
				for(int d = l.getDuration(); d > 0; d-=1) {
					boolean flag = true;
					for(int m2 = 0; m2 < t.getminD().length && flag; m2++) {
						if(t.getminD()[m2] > 0) {
							int i, j;
							if((l.getClassroom()).equals("A1")) i = 0;
								else if ((l.getClassroom()).equals("A2")) i = 1;
								else if ((l.getClassroom()).equals("A3")) i = 2;
								else if ((l.getClassroom()).equals("B1")) i = 3;
								else if ((l.getClassroom()).equals("B2")) i = 4;
								else if ((l.getClassroom()).equals("B3")) i = 5;
								else if ((l.getClassroom()).equals("C1")) i = 6;
								else if ((l.getClassroom()).equals("C2")) i = 7;
								else i = 8;
							for(j = 0; j < 7 && flag; j++) {
								if(Teacher_Search(t, i, m2, j) && (state[i][m2][j] == null)) {
									state[i][m2][j] = new Vec2<Lesson, Teacher>(l, t);
									l.setDuration(l.getDuration()-1);
									t.minpDay[m2] =(t.getminD()[m2] - 1);
									t.minpWeek -=1;
									flag = false;
								}
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * Overloaded copy constructor creating an instance of State Class with its matrix assigned copies
	 * of the associated values in the original state; as read from the relevant parameter. Teachers have
	 * already been associated to lessons they are knowledgeable to teach in class.
	 * @param s The state to copy its values.
	 */
	public State(State s) {
		state = (Vec2<Lesson, Teacher>[][][])new Vec2[9][5][7];
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 5; j++) {
				for(int k = 0; k < 7; k++) {
					this.state[i][j][k] = s.state[i][j][k];
				}
			}
		}
	}
	
	/**
	 * Method examining if a teacher has been assigned to a Class, except for the one coordinate
	 * i refers to, in day and hours coordinates j and k reference respectively. The result of that search
	 * is assigned to a boolean value to be returned.
	 * @param t The teacher to check his assignments.
	 * @param i The i coordinate of the instance's state array; that refers to the Classroom.
	 * @param j The j coordinate of the instance's state array; that refers to the day.
	 * @param k The k coordinate of the instance's state array; that refers to the hour.
	 * @return True if the search was successful; otherwise false.
	*/
	public boolean Teacher_Search(Teacher t,int i, int j, int k){
		for(int i0 = 0; i0 < 9;i0++){
			if(i0!=i){
				if(state[i0][j][k] != null)
					if((state[i0][j][k]).getYValue() == t)
						return false;
			}
		}
		return true;
	}
	
	/**
	 * Method assigning by reference the lessons, which ID are mentioned in a String Array, into a
	 * relevant Lesson Array of the triple the size (for each of the 3 Classrooms per grade. Lesson
	 * instances, featuring an ID for a unique key, are being imported from a relevant Arraylist.
	 * @param L The Arraylist Lesson instances are being imported from.
	 * @param L1 The String array; including the IDs of the Lessons to be imported.
	 * @return A Lesson array; including references of the Lessons the IDs of which are mentioned in L1.
	 */
	public Lesson[] findLessons(ArrayList<Lesson> L, String[] L1){
		Lesson[] lArr = new Lesson[L1.length*3];
		for(int i=0;i<L1.length;i++){
			boolean flag = true;
			for(int j=0;j<L.size()&& flag;j++){
				if((L.get(j)).getID().equals(L1[i])) {
					if(3*i < lArr.length)lArr[3*i] = L.get(j);															// Saving references of the lessons
					if(j+1 < L.size() && 3*i+1 < lArr.length)lArr[3*i+1] = L.get(j+1);
					if(j+2 < L.size() && 3*i+2 < lArr.length)lArr[3*i+2] = L.get(j+2);
					flag = false;
				}  
			}
		}
		return lArr;
	}
	
	/**
	 * Setter for priority: assumption for the pair's priority
	 * @param priority Value of priority
	 */
	public void setPriority(double priority){
		this.priority = priority;
	}
	
	/**
	 * Getter for priority: assumption for the pair's priority
	 * @return priority Value of priority
	 */
	public double getPriority(){
		return priority;
	}
	
	/**
	 * Implementation of overridden method compare.
	 */
	public int compare(Object a, Object b) {
		a = (State)a;
		return((Comparable)a).compareTo((State)b);
	}
	
	/**
	 * Implementation of overridden method compareTo.
	 */
	public int compareTo(State a){
		return((Comparable)((State)this).priority).compareTo(((State)a).priority);
	}
	
	/**
	 * Getter for state: modelized illustration of a sample program.
	 * Redirects the state matrix's contents; in a distinctive but rather short manner as a String.
	 * @return The String to redirect the state matrix's contents.
	*/
	public String print() {
		String s = "";
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 5; j++) {
				for(int k = 0; k < 7; k++) {
					String f, d;
					switch (i) {
					case 0:  f = "A1";
							break;
		            case 1:  f = "A2";
		                     break;
		            case 2:  f = "A3";
		                     break;
		            case 3:  f = "B1";
		                     break;
		            case 4:  f = "B2";
		                     break;
		            case 5:  f = "B3";
		                     break;
		            case 6:  f = "C1";
		                     break;
		            case 7:  f = "C2";
		                     break;
		            case 8:  f = "C3";
		                     break;
		            default: f = "-";
		                     break;
					}
					switch (j) {
					case 0:  d = "MON";
							break;
		            case 1:  d = "TUE";
		                     break;
		            case 2:  d = "WED";
		                     break;
		            case 3:  d = "THU";
		                     break;
		            case 4:  d = "FRI";
		                     break;
		            default: d = "-";
		                     break;
		        }
					if(((state[i][j][k])!= null)&& ((state[i][j][k]).getTValue() != null) && ((state[i][j][k]).getYValue()!= null) )
						s+=("Class: "  + f + ", " + d + " " + (k+1) + "hour » Lesson: " + ((state[i][j][k]).getTValue()).getID() + ", " + ((state[i][j][k]).getTValue()).getTitle() + ", Teacher: " + ((state[i][j][k]).getYValue().getID())+ ", " + ((state[i][j][k]).getYValue().getName()) +"\n");
					else
						s+=("Class: "  + f + ", " + d + " " + (k+1) + "hour » No Lesson!" + "\n");
				}
			}
		}
		return s;
	}
	
	/**
	 * Getter for state: modelized illustration of a sample program.
	 * Redirects the state matrix's contents; in a distinctive but rather short manner to given stream.
	 * @param stream The stream to redirect the state matrix's contents.
	*/
	public void print(PrintStream stream) {
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 5; j++) {
				for(int k = 0; k < 7; k++) {
					String f, d;
					switch (i) {
					case 0:  f = "A1";
							break;
		            case 1:  f = "A2";
		                     break;
		            case 2:  f = "A3";
		                     break;
		            case 3:  f = "B1";
		                     break;
		            case 4:  f = "B2";
		                     break;
		            case 5:  f = "B3";
		                     break;
		            case 6:  f = "C1";
		                     break;
		            case 7:  f = "C2";
		                     break;
		            case 8:  f = "C3";
		                     break;
		            default: f = "-";
		                     break;
					}
					switch (j) {
					case 0:  d = "MON";
							break;
		            case 1:  d = "TUE";
		                     break;
		            case 2:  d = "WED";
		                     break;
		            case 3:  d = "THU";
		                     break;
		            case 4:  d = "FRI";
		                     break;
		            default: d = "-";
		                     break;
		        }
					if(((state[i][j][k])!= null)&& ((state[i][j][k]).getTValue() != null) && ((state[i][j][k]).getYValue()!= null) )
						stream.printf("Class: "  + f + ", " + d + " " + (k+1) + "hour » Lesson: " + ((state[i][j][k]).getTValue()).getID() + ", " + ((state[i][j][k]).getTValue()).getTitle() + ", Teacher: " + ((state[i][j][k]).getYValue().getID())+ ", " + ((state[i][j][k]).getYValue().getName()) +"\n");
					else
						stream.printf("Class: "  + f + ", " + d + " " + (k+1) + "hour » No Lesson!" + "\n");
				}
			}
		}
	}
	
	/**
	 * Getter for state: the 3D Array of Vec2<Lesson, Teacher>.
	 * Redirects the state matrix's contents; in full detail about each of its registrations to System.out.
	*/
	public void tester() {
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 5; j++) {
				for(int k = 0; k < 7; k++) {
					System.out.println("POSITION = [" + i + "] [" + j + "] [" + k + "], » "  + state[i][j][k] + "\n");
				}
			}
		}
	}
	
}