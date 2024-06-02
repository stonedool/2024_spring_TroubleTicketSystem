package classes;
import interfaces.accountInterface;

import java.io.*;

public class account implements accountInterface {
    static String name = "";
    public void newAccount(String text) {
        String fileName = "idList.txt";
        // try-with-resources 문을 사용하여 파일 쓰기를 자동으로 닫음
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.newLine(); // 새 줄 추가
            writer.write(text);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean login(String id, String pw) throws FileNotFoundException {
        String idList = "idList.txt";
        String searchData = id + "#" +pw;

        try {
            FileReader fileReader = new FileReader(idList);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;

            while((line = bufferedReader.readLine()) != null){
                if (line.contains(searchData)){
                    System.out.println("Login success");
                    String[] chooseName = line.split("#");
                    name = chooseName[4];
                    return true;
                }
                else System.out.println("No such id/pw!!");
            }
        }
        catch (IOException e){
            System.out.println("There is no such id and pw!!");
        }
        return false;
    }
    public String totalUserList() {
        String fileName = "idList.txt";
        String retStr = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
               retStr = retStr +'/'+ userList(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return retStr;

    }
    public String userList(String data) {
        String result = null;
        String delimiter = "#";
        int n = 5; //5번째 델리미터가 가르키는게 유저네임.

        String[] parts = data.split(delimiter);
        result = parts[n - 1];

        return result; //유저네임 리턴
    }
}
