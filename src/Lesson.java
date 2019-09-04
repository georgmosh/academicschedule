import java.util.Comparator;

/**
 * Lesson.java
 * A class implementing lessons of a secondary school. Each lesson includes a serial number,
 * a title and is associated with one grade and specific classrooms( i.e. A1, A2, ...); where it is
 * taught for a specific amount of time, being also indicated -in minutes.
 * @authors  G. Moschovis (p3150113@aueb.gr)
 */
public class Lesson {
	/**
     * Lesson ID
     */
	protected String ID;
    
	/**
     * Lesson name
     */
	protected String title;
    
	/**
     * School grade the lesson is taught; i.e. A, B, C.
     */
	protected String grade;
	
	/**
     * Time (in school hours) the lesson is taught; i.e. 1h.
     */
    protected int duration;
	
	/**
     * Specific classroom the lesson is taught; i.e. A3, C5.
     */
    protected String classroom;
	
	/**
	 * Default constructor
	*/
	public Lesson()
	{
		this(null, null, null, null, 0);
	}
	
	/**
	 * Overloaded constructor creating an instance of Lesson Class with parameterized its serial
	 * number, title, the grade, the specific grade (A, B, or C) and the classroom ( i.e. A1, A2, ...)
	 * the creating instance is associated with and the hours intending to be taught.
	 * @param ID The lesson's serial number
	 * @param title The lesson's title
	 * @param grade The grade the lesson's instance belongs to
	 * @param classroom The specific classroom the lesson's instance is associated to
	 * @param hours How many hours (in minutes) the lesson is intended to be taught
	*/
	public Lesson(String ID, String title, String grade, String classroom, int duration)
	{
		this.ID = ID;
		this.title = title;
		this.grade = grade;
		this.classroom = classroom;
		this.duration = duration;
	}
	
	/**
	 * Setter for ID: the lesson's serial number
	 * @param value of ID
	 */
	public void setID(String a) {ID = a;}
	
	/**
	 * Setter for title: the lesson's title 
	 *  @param value of title
	 */
	public void setTitle(String a) {title=a;}
	
	/**
	 * Setter for grade: the lesson's grade
	 *  @param value of grade
	 */
	public void setGrade(String a) {grade=a;}
	
	/**
	 * Setter for classroom: the specific classroom the lesson's instance is associated to
	 *  @param value of classroom
	 */
	public void setClassroom(String a) {classroom=a;}
	
	/**
	 * Setter for hours: how many hours (in minutes) the lesson is intended to be taught
	 *  @param value of duration
	 */
	public void setDuration(int a) {duration=a;}
	
	/**
	 * Getter for ID: the lesson's serial number
	 * @return value of ID
	 */ 
	public String getID(){
		return ID;
	} 
	
	/**
	 * Getter for title: the lesson's title  
	 *  @return value of title
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Getter for grade: the grade the lesson's instance belongs to
	 *  @return value of grade
	 */
	public String getGrade() {
		return grade;
	}
	
	/**
	 * Getter for classroom: the specific classroom the lesson's instance is associated to 
	 *  @return value of classroom
	 */
	public String getClassroom() {
		return classroom;
	}
	
	/**
	 * Getter for duration: how many hours (in minutes) the lesson is intended to be taught 
	 *  @return value of duration
	 */
	public int getDuration() {
		return duration;
	}
	
	/**
	 * Overloaded method toString
	 */
	public String toString(){
		return "Lesson: " + getID() + ", " + getTitle() + " in class " + getGrade()+ ", group " + getClassroom() + " for " + getDuration() + " hr(s)." ;
	}
}
