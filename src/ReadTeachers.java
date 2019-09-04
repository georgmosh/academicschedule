import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.lang.RuntimeException;

/**
 * ReadTeachers.java
 * @authors  G. Moschovis (p3150113@aueb.gr)
 */
public class ReadTeachers {

	/**
	 * Static field used as data accessor between readFile & the class containing a creating
	 * instance of ReadTeachers.java; being assigned a Teachers' Arraylist if reading was successful;
	 * otherwise NULL.
	 */
	protected static ArrayList<Teacher> gbl;

	/**
	 * A method reading a given text file of a specific format described in comments; contents of which  
	 * are assigned to a parameterized  Teachers' Arraylist if reading was successful; otherwise returns a
	 * NULL value.
	 * @param filename The name of the given text file.
	 */
	public ArrayList<Teacher> readFile(String filename) {

		BufferedReader br = null;

		try {
		
			br = new BufferedReader(new FileReader(filename));
			
			ArrayList<Teacher> init = new ArrayList<Teacher>();
			String  sCurrentLine, firstLine = br.readLine();
			firstLine = firstLine.trim().replaceAll(" +", " ");	
			int tuples_length, domain_length = firstLine.isEmpty() ? 0 : firstLine.split("\\s+").length;
			boolean check = true;	
			if (domain_length != 5) {	
				check = false;				
			}
			
			while (((sCurrentLine = br.readLine()) != null) && (check == true)) {
				sCurrentLine = sCurrentLine.trim().replaceAll(" +", " ");;
				String[] tuples_lessons, tuples_context = sCurrentLine.split(", "); // separator is: ", " = [comma][whitespace]
				Lesson[] tuples_lessons2;
				tuples_length = tuples_context.length;
				if (tuples_length < 5) {
					check = false;
				} else {
					tuples_lessons = new String[tuples_length - 4];
					tuples_lessons2 = new Lesson[tuples_length - 4];
					for(int u = 0; u < tuples_length - 4; u++) {
						if(u+4<tuples_context.length)
							tuples_lessons[u] = tuples_context[u+4];
						}
							// any condition necessary to set check = false
					Teacher t = new Teacher(tuples_context[0], tuples_context[1], Integer.parseInt(tuples_context[2]), Integer.parseInt(tuples_context[3]), tuples_lessons, tuples_lessons2);
					init.add(t);
				}
			}
			
			if (check == false) throw new RuntimeException();

			gbl = (!init.isEmpty()) ? init : null;
		
		} catch (IOException e) {
			System.err.println("The system could not find the file specified");
			e.printStackTrace();
		
		} catch (RuntimeException incompatibleInputs) {
			System.err.println("Incompatible inputs. An unexpected error occured.");
			incompatibleInputs.printStackTrace();
		
		} finally { // Stream closure should be executed at any case
			
			try {
				if (br != null) br.close();
			} catch (IOException exception) {
				exception.printStackTrace();
			}
			
		}
		
		return gbl;

	}
	
}