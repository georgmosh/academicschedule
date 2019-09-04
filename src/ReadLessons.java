import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.lang.RuntimeException;

/**
 * ReadLessons.java
 * @authors  G. Moschovis (p3150113@aueb.gr)
 */
public class ReadLessons {
	
	/**
	 * Static field used as data accessor between readFile & the class containing a creating
	 * instance of ReadLessons.java; being assigned a Lessons' Arraylist if reading was successful;
	 * otherwise NULL.
	 */
	protected static ArrayList<Lesson> gbl;

	/**
	 * A method reading a given text file of a specific format described in comments; contents of which  
	 * are assigned to a parameterized  Lessons' Arraylist if reading was successful; otherwise returns a
	 * NULL value.
	 * @param filename The name of the given text file.
	 */
	public ArrayList<Lesson> readFile(String filename) {

		BufferedReader br = null;
		
		try {
		
			br = new BufferedReader(new FileReader(filename));
			ArrayList<Lesson> init = new ArrayList<Lesson>();
			String  sCurrentLine, firstLine = br.readLine();
			firstLine = firstLine.trim().replaceAll(" +", " ");	
			int tuples_length, domain_length = firstLine.isEmpty() ? 0 : firstLine.split("\\s+").length;
			boolean check = true;	
			if (domain_length != 4) {	
				check = false;		
			}
			
			while (((sCurrentLine = br.readLine()) != null) && (check == true)) {
				sCurrentLine = sCurrentLine.trim().replaceAll(" +", " ");;
				String[] tuples_context = sCurrentLine.split(", "); // separator is: ", " = [comma][whitespace]
				tuples_length = tuples_context.length;
				if (tuples_length != 4) {
					check = false;
				} else {
							// any condition necessary to set check = false
					for(int i = 1; i < 4; i++) {
						int tuple_duration = Integer.parseInt(tuples_context[3]);
						Lesson l = new Lesson(tuples_context[0], tuples_context[1], tuples_context[2], tuples_context[2]+i, tuple_duration);
						init.add(l);
					}
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