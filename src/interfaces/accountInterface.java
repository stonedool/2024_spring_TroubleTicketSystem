package interfaces;

import java.io.FileNotFoundException;

public interface accountInterface {
    public void newAccount(String param);
    public boolean login(String id, String pw) throws FileNotFoundException;
}
