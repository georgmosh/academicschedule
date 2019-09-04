import java.util.Comparator;

/**
 * Teacher.java
  * A class implementing teachers of a secondary school. Each teacher includes a serial number,
  * a name, surname and is associated with one lesson (using its ID )per instance( i.e. 36F, 24R, ...); 
  * additionally teaches for a minimum time amount per day, week.
  * @authors  G. Moschovis (p3150113@aueb.gr)
  */
public class Teacher {
	/**
     * Teacher ID
     */
	protected String id;
	
	/**
     * Teacher name
     */
	protected String name;
	
	/**
     * Lesson ID the teacher instance is associated with.
     */
	protected String[] lessonID;
	
	/**
     * Lesson ID the teacher instance is associated with.
     */
	protected Lesson[] lesson;
	
	/**
     * Minimum teaching time (in school hours) per day; i.e. 3h.
     */
	protected int[] minpDay;
	
	/**
     * Minimum teaching time (in school hours) per week; i.e. 11h.
     * BEWARE that this field cannot be greater than minpDay x 5; where 5 indicates
     * the amount of weekdays.
     */
	protected int minpWeek;
	
	/**
	 * Default constructor
	*/
	public Teacher()
	{
		this(null, null, 0, 0, null, null);
	}
	
	/**
	 * Overloaded constructor creating an instance of Teacher Class with parameterized its serial
	 * number, name,  that one lesson (using its ID )the instance( i.e. 36F, 24R, ...)is associated
	 * the creating instance is associated with, and the minimum amount of hours the teacher is
	 * intended to teach per day, week.
	 * @param ID The teacher's serial number
	 * @param name The teacher's name
	 * @param lessonID The lesson's ID that teacher instance is associated with.
	 * @param minpDay The minimum teaching time (in school hours) per day; i.e. 3h. Array is being instanciated through deep copy.
	 * @param minpWeek The minimum teaching time (in school hours) per week; i.e. 11h.
	*/
	public Teacher(String id, String name, int minpDay, int minpWeek, String[] lessonID, Lesson[] lesson){
		this.id = id;
		this.name = name;
		this.lessonID = lessonID;
		this.lesson = lesson;
		this.minpWeek = minpWeek;
		this.minpDay = new int[5];
		for(int i = 0;i<5;i++) {
			this.minpDay[i] = minpDay;
		}
	}
	
	/**
	 * Getter for ID: the teacher's serial number
	 * @return value of ID
	 */
	public String getID(){
		return id;
	}
	
	/**
	 * Getter for name: the teacher's name
	 * @return value of name
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Getter for lessonID: the associated lesson's serial number
	 * @return value of LessonID
	 */
	public String[] getLessonID(){
		return lessonID;
	}
	
	/**
	 * Getter for lesson: the associated lesson's instance
	 * @return value of Lesson
	 */
	public Lesson[] getLesson(){
		return lesson;
	}
	
	/**
	 * Getter for minpDay: the minimum teaching time per day
	 * @return value of minpDay
	 */
	public int[] getminD(){
		return minpDay;
	}
	
	/**
	 * Getter for minpWeek: the minimum teaching time per week
	 * @return value of minpWeek
	 */
	public int getminW(){
		return minpWeek;
	}
	
	/**
	 * Setter for ID: the teacher's serial number
	 * @param value of ID
	 */
	public void setID(String id){
		this.id = id;
	}
	
	/**
	 * Setter for name: the teacher's name
	 * @param value of name
	 */
	public void setName(String name){
		this.name = name;
	}
	
	/**
	 * Setter for lessonID: the associated lesson's serial number
	 * @param value of LessonID
	 */
	public void setLessonID(String[] lessonID){
		this.lessonID = lessonID;
	}
	
	/**
	 * Setter for lesson: the associated lesson's instance
	 * @param value of Lesson
	 */
	public void setLesson(Lesson[] lesson){
		this.lesson = lesson;
	}
	
	/**
	 * Setter for minpWeek: the minimum teaching time per week
	 * @param value of minpWeek
	 */
	public void setMinpWeek(int minpWeek){
		this.minpWeek = minpWeek;
	}
	
	/**
	 * Setter for minpDay: the minimum teaching time per day
	 * @param value of minpDay
	 */
	public void setMinpDay(int minpDay[]){
		this.minpDay = minpDay;
	}
	
	/**
	 * Overloaded method toString
	 */
	public String toString() {
		return "Teacher: "+getID()+", Prof. "+getName()+", teaching "+getLessonID()+" for a maximum of "+getminD()+"h. per day and "+getminW()+"h. per week.";
	}
}