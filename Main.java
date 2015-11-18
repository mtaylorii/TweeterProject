import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		AccountRepository repo = new AccountRepository();
		
		Account Chairman_Mao = new Account("Chairman_mao", "china");
		Account Vladimir_Putin = new Account("Vladimir_Putin", "russia");
		Account Bashful = new Account("Bashful", "dwarf");
		
		Chairman_Mao.setProfileText("blahblahblah");
		Chairman_Mao.setProfilePhotoPath("images/suffering.png");
		Chairman_Mao.printAccount();
		Twit maoTwit = new Twit("Labor camps rule");
		Chairman_Mao.postTwit(maoTwit);
		Chairman_Mao.subscribe(Vladimir_Putin);
		Chairman_Mao.subscribe(Bashful);
		Vladimir_Putin.subscribe(Chairman_Mao);
		Chairman_Mao.printAccount();
		Chairman_Mao.unSubscribe(Bashful);
		Chairman_Mao.printAccount();
		
		String loadMao = Chairman_Mao.toString();
		repo.loadAccount(loadMao);
		repo.printAccounts();
		repo.saveDatabase();
		
	}
}