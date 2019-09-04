import java.util.Comparator;

/**
 * DefaultComparator.java
 * Final Class, implementing the Comparator Interface, for it to be applicable for all types of
 * Comparisons among our classes' instances; referencing of course exclusively instances of
 * the same class to be compared.
 * @authors  G. Moschovis (p3150113@aueb.gr)
 */
public final class DefaultComparator implements Comparator {
	public int compare(Object a, Object b) {
		return ((Comparable)a).compareTo(b);
	}
}
