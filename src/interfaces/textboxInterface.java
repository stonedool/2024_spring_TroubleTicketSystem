package interfaces;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface textboxInterface {
    public void newIssue(String data);
    public String issueEditing(String data) throws IOException;

    public void rewriteIssue(String data);
}
