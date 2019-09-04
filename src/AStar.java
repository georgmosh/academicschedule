import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.Math;
import java.util.ArrayList;

/**
 * AStar.java
 * @authors  G. Moschovis (p3150113@aueb.gr)
 */

public class AStar{
	
	/**
	 * Static field used as data accessor between the Euretic function & the constructor for a creating 
	 * of AStar.java, that also embodies a sample implementation of the AI Algorithm; formed in a instance
	 * bottom-up manner by our team.
	 */
	static public ArrayList<Vec2<Lesson, Teacher>> content;
	
	/**
	 * Static field used as a State Storage; what in Artificial Intelligence is known as Search Frontier and a
	 * data accessor among AStar.java, that also embodies a sample implementation of the AI Algorithm; formed
	 *  in a instance bottom-up manner by our team.
	 */
	static public ArrayList<State> states;
	
	/**
	 * Bottom-up implementation of A* Algorithm. Holds a Search Frontier; which is being updated in each of
	 * the algorithm repetitions, by the removal of the worst state and the addition of one or two new states,
	 * having been produced by the best of the states saved in the Search Frontier. Sorting uses the priority
	 * field of State.java & Vec2.java for each Lesson-Teacher combination. The resulting state is finally
	 * written in a text file named "schedule.txt".
	 * @param init The initial state; for the Algorithm to start producing better states.
	 * @param L The Lesson's Arraylist Lesson instances are being imported from relevant text file
	 * @param T The Teacher's Arraylist Teacher instances are being imported from relevant text file
	 */
	public AStar(State init, ArrayList<Lesson> L, ArrayList<Teacher> T) {
		states = new ArrayList<State>();
		State temp = init;
		double factor_g = 0;
		init.setPriority(eureticFunc(init, L, T));
		states.add(init);
		while((temp.getPriority()> 0)&&(factor_g < Integer.MAX_VALUE)) {
			State para1, para2;
			para1 = para2 = new State(temp);
			double factor_f = eureticFunc(para1, L, T), factor_w = factor_g + factor_f;
			factor_g++;
			para1.setPriority(factor_w);
			para2.setPriority(factor_w);
			content.sort(new DefaultComparator());
					Vec2<Lesson, Teacher> rep1 = content.get(content.size()-1);
						String pos1 = rep1.getPosition();
						int i1 = Integer.parseInt(pos1.substring(0, 1));
						int j1 = Integer.parseInt(pos1.substring(3, 4));
						int k1 = Integer.parseInt(pos1.substring(6, 7));
					Vec2<Lesson, Teacher> rep2 = content.get(content.size()/2);
						String pos2 = rep2.getPosition();
						int i2 = Integer.parseInt(pos2.substring(0, 1));
						int j2 = Integer.parseInt(pos2.substring(3, 4));
						int k2 = Integer.parseInt(pos2.substring(6, 7));
			para1.state[i1][j1][k1] = rep2;
			para1.state[i2][j2][k2] = rep1;
			states.add(para1);
			boolean ins_para2 = false;
			for(int i = 0; i < 9; i++) {
				for(int j = 0; j < 5; j++) {
					for(int k = 0; k < 7; k++) {
						if((para2.state[i][j][k] == null)&& !ins_para2) {
							para2.state[i][j][k] = rep1;
							para2.state[i1][j1][k1] = null;
							ins_para2 = true;
						}
					}
				}
			}
			if(ins_para2) states.add(para2);
			states.sort(new DefaultComparator());
			if(factor_g > 5)states.remove(states.size()-1);
			temp = states.get(0);
		}
		String prog = temp.print();
		BufferedReader br = null;
		BufferedWriter bw = null;
		FileWriter fw = null;
		writeFile(bw, fw, prog);
	}
	
	/**
	 * The euretic function to calculate the product of weight and the amount of the state's biased
	 * constraints. The weight of each constraint is given a parameter to the function calculating each
	 * of the constraints and the product is computed by the euretic function, after reseting for the
	 * states to be independent, as well as their calculations.
	 * @param s The state; for the Algorithm to calculate.
	 * @param L The Lesson's Arraylist Lesson instances are being imported from relevant text file
	 * @param T The Teacher's Arraylist Teacher instances are being imported from relevant text file
	 */
	public int eureticFunc(State s,  ArrayList<Lesson> L, ArrayList<Teacher> T) {
		zeroConstraint(s);
		calcConstraint1(s, 0.6);
		calcConstraint2(s, 0.4);
		calcConstraint3(s, 0.5);
		calcConstraint4(s, L, 0.1);
		calcConstraint5(s, T, 0.3);
		content = new ArrayList<Vec2<Lesson, Teacher>>();
		int n = 0;
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 5; j++) {
				for(int k = 0; k < 7; k++) {
					if(s.state[i][j][k] != null) {
						n+=s.state[i][j][k].getPriority();
						s.state[i][j][k].setPosition(i + ", " + j + ", " + k + ".");
						content.add(s.state[i][j][k]);
					}
				}
			}
		}
		return n;
	}
	
	/**
	 * The function to reset the priorities of State & its containing Vec2 instances.
	 * @param s The state to reset priority of it & its containing Vec2 instances.
	 */
	public void zeroConstraint(State s) {
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 5; j++) {
				for(int k = 0; k < 7; k++) {
					if(s.state[i][j][k] != null)s.state[i][j][k].setPriority(0);
				}
			}
		}
	}
	
	/**
	 * The function to calculate the sum of the weights for the 1st constraint in a State.
	 * @param s The state to calculate the sum of the weights for the 1st constraint.
	 * @param w The weight of the constraint.
	 */
	public void calcConstraint1(State s, double w) {
		Vec2<Lesson, Teacher> prev = null, curr;
		int n = 0;
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 5; j++) {
				for(int k = 0; k < 7; k++) {
					curr = s.state[i][j][k];
					if(k != 0) {
						if((prev == null) && (curr != null)) curr.setPriority(curr.getPriority()+ w);
					}
				}
			}
		}
	}
	
	/**
	 * The function to calculate the sum of the weights for the 2nd constraint in a State.
	 * @param s The state to calculate the sum of the weights for the 2nd constraint.
	 * @param w The weight of the constraint.
	 */
	public void calcConstraint2(State s, double w) {
		Vec2<Lesson, Teacher> prev = null, curr, next;
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 5; j++) {
				for(int k = 1; k < 6; k++) {
					curr = s.state[i][j][k];
					next = s.state[i][j][k+1];
					if((next != null)&& (prev != null)&& (curr != null)) {
						if((prev.getYValue().getID().equals(curr.getYValue().getID()))&& (curr.getYValue().getID().equals(next.getYValue().getID())))  {
							next.setPriority(next.getPriority()+ w);
						}	
					}
					prev = curr;
				}
			}
		}
	}
	
	/**
	 * The function to calculate the sum of the weights for the 3rd constraint in a State.
	 * @param s The state to calculate the sum of the weights for the 3rd constraint.
	 * @param w The weight of the constraint.
	 */
	public void calcConstraint3(State s, double w) {
		for(int i = 0; i < 9; i++) {
			int n = 0;
			int[] hours = new int[5];
			for(int j = 0; j < 5; j++) {
				for(int k = 0; k < 7; k++) {
					if(s.state[i][j][k] != null)hours[j]++;
				}
			}
			for(int m1 = 0; m1 < hours.length-2; m1++) {
				for(int m2 = m1+1; m2 < hours.length-1; m2++) {
					if(hours[m2] != hours[m1] ){
						n+=Math.abs(hours[m2] - hours[m1]);
						int m3 = (hours[m2] > hours[m1]) ? m2 : m1;
						for(int m4 = 6; m4 > -1 && n > 0; m4--) {
							if(s.state[i][m3][m4] != null) {
								s.state[i][m3][m4].setPriority(s.state[i][m3][m4].getPriority() + w);
								n--;
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * The function to calculate the sum of the weights for the 4th constraint in a State.
	 * @param s The state to calculate the sum of the weights for the 4th constraint.
	 * @param w The weight of the constraint.
	 */
	public void calcConstraint4(State s, ArrayList<Lesson> L, double w) {
		for(int m = 0; m < L.size(); m++) {
			int i, n = 0;
			int[] show = new int[5];
			Lesson l = L.get(m);
			if((l.getClassroom()).equals("A1")) i = 0;
				else if ((l.getClassroom()).equals("A2")) i = 1;
				else if ((l.getClassroom()).equals("A3")) i = 2;
				else if ((l.getClassroom()).equals("B1")) i = 3;
				else if ((l.getClassroom()).equals("B2")) i = 4;
				else if ((l.getClassroom()).equals("B3")) i = 5;
				else if ((l.getClassroom()).equals("C1")) i = 6;
				else if ((l.getClassroom()).equals("C2")) i = 7;
				else i = 8;
			for(int j = 0; j < 5; j++) {
				for(int k = 0; k < 7; k++) {
					if((s.state[i][j][k] != null)&& (s.state[i][j][k].getTValue()!= null)) {
						if(s.state[i][j][k].getTValue()== l)show[j]++;
					}
				}
			}	
			for(int m1 = 0; m1 < show.length-2; m1++) {
				for(int m2 = m1+1; m2 < show.length-1; m2++) {
					if(show[m2] != show[m1]) {
						n+=Math.abs(show[m2] - show[m1]);
						int m3 = (show[m2] > show[m1]) ? m2 : m1;
						for(int m4 = 6; m4 > -1 && n > 0; m4--) {
							if((s.state[i][m3][m4] != null)&& (s.state[i][m3][m4].getTValue()== l)) {
								s.state[i][m3][m4].setPriority(s.state[i][m3][m4].getPriority() + w);
								n--;
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * The function to calculate the sum of the weights for the 5th constraint in a State.
	 * @param s The state to calculate the sum of the weights for the 5th constraint.
	 * @param w The weight of the constraint.
	 */
	public void calcConstraint5(State s, ArrayList<Teacher> T, double w) {
		for(int m = 0; m < T.size(); m++) {
			Teacher t = T.get(m);
			for(int i = 0; i < 9; i++) {
				int n = 0;
				int[] show = new int[5];
				for(int j = 0; j < 5; j++) {
					for(int k = 0; k < 7; k++) {
						if((s.state[i][j][k] != null)&& (s.state[i][j][k].getYValue()!= null)) {
							if(s.state[i][j][k].getYValue()== t)show[m]++;
						}
					}
				}
				for(int m1 = 0; m1 < show.length-2; m1++) {
					for(int m2 = m1+1; m2 < show.length-1; m2++) {
						if(show[m2] != show[m1]) {
							n+=Math.abs(show[m2] - show[m1]);
							int m3 = (show[m2] > show[m1]) ? m2 : m1;
							for(int m4 = 6; m4 > -1 && n > 0; m4--) {
								if((s.state[i][m3][m4] != null)&& (s.state[i][m3][m4].getYValue()== t)) {
									s.state[i][m3][m4].setPriority(s.state[i][m3][m4].getPriority() + w);
									n--;
								}
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * Static method exporting data to the .txt file named "schedule.txt"; including final format of the state A* produced.
	 * @param bw A Buffered Writer used to export data to the .txt file.
	 * @param fw A File Writer used to export data to the .txt file.
	 * @param outputReport The String containing the data to be exported to the .txt file; including final format of the state A* produced.
	 */
	public static void writeFile(BufferedWriter bw, FileWriter fw, String outputReport) {
		try {
			fw = new FileWriter("schedule.txt");
			bw = new BufferedWriter(fw);
			bw.write(outputReport);
		} catch (IOException exception) {
			exception.printStackTrace();
		} finally { // Stream closure should be executed at any case
			try {
				if (bw != null)bw.close();
				if (fw != null)fw.close();
			} catch (IOException exception) {
				exception.printStackTrace();
			}
		}
	}
}