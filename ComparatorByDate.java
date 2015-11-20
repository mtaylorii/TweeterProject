import java.util.Comparator;
/**
 * The class below implements Java's comparator utility and defines how Twit
 * objects will be sorted in a list; they will be sorted with the most recent
 * Twit objects being placed before others.
 */
public class ComparatorByDate implements Comparator<Twit> {
    public int compare(Twit t1, Twit t2) {
        return t1.getTwitDate().compareTo(t2.getTwitDate());
    }
}
