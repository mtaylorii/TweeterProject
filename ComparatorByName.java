import java.util.Comparator;
/**
 * The class below implements Java's comparator utility and defines how Account
 * objects will be sorted in a list.
 */
public class ComparatorByName implements Comparator<Account> {
    public int compare(Account a1, Account a2) {
        return a1.getUserName().compareTo(a2.getUserName());
    }
}
