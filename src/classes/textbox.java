package classes;
import interfaces.textboxInterface;
import java.io.*;
import java.nio.file.Files;
import java.util.Vector;
import java.io.File.*;
import java.nio.file.*;
import java.util.List.*;

public class textbox implements textboxInterface{

    String issue_txt = "issues.txt";
    String ticketDirPath = "Tickets/";
    File directory = new File(ticketDirPath);
    //데이터가 저장되는 위치.
    public void txtToVector(Vector<Vector<String>> paramStr, int mode, String keyword){
        //텍스트 파일에 있는 데이터를 벡터로 가져온다. 코멘트는 안가져옴.
        String ticketPath = "Tickets";
        if (mode == 1) {//read-all mode
            try {
                Files.list(Paths.get(ticketPath))
                        .forEach(path -> {
                            try {
                                String Line = readTxt(path);
                                String[] strToVector = Line.split("@");

                                Vector<String> temp = new Vector<>();
                                for (String element : strToVector) {
                                    String[] only_use_data = element.split(":");
                                    temp.add(only_use_data[1]);
                                }
                                paramStr.add(temp);
                            } catch (IOException e) {
                                System.err.println("Error reading file: " + path.getFileName());
                                e.printStackTrace();
                            }
                        });
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (mode == 2) { //read partial mode
            try {
                Files.list(Paths.get(ticketPath))
                        .forEach(path -> {
                            try {
                                String Line = readTxt(path);
                                // 특정 키워드가 포함된 파일만 처리
                                if (Line.contains(keyword)) {
                                    String[] strToVector = Line.split("@");

                                    Vector<String> temp = new Vector<>();
                                    for (String element : strToVector){
                                        String[] only_use_data = element.split(":");
                                        temp.add(only_use_data[1]);
                                    }
                                    paramStr.add(temp);
                                }
                            } catch (IOException e) {
                                System.err.println("Error reading file: " + path.getFileName());
                                e.printStackTrace();
                            }
                        });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private static String readTxt(Path filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath.toFile()))) {
            String retStr =  reader.readLine() +'@'+ reader.readLine() +'@'+ reader.readLine()
                    +'@'+ reader.readLine() +'@'+ reader.readLine() +'@'+ reader.readLine()
                    +'@'+ reader.readLine()+'@';
            return retStr;
        }
    }
    public void newIssue(String data) {
        if (!directory.exists()) directory.mkdir();
        //디렉토리가 없으면 생성.
        String[] ticketNum = data.split("@");

        System.out.println(data);
        File createTicket_txt = new File(ticketDirPath + ticketNum[0]);
        try {
            if (createTicket_txt.createNewFile()) {
                FileWriter writer = new FileWriter(createTicket_txt);
                if (ticketNum.length <= 7){
                    writer.write("Ticket Number :" + ticketNum[0] + '\n' + "Reported date :" + ticketNum[1] + '\n'+
                            "Reporter :" + ticketNum[2] + '\n' + "Fixer time :" + ticketNum[3] + '\n' +
                            "Priority :" + ticketNum[4] + '\n' + "Assignee :" + ticketNum[5] + '\n' +
                            "Status :" + ticketNum[6] + '\n' + "Comments :None ");
                    writer.close();
                }
                else {
                    writer.write("Ticket Number :" + ticketNum[0] + '\n' + "Reported date :" + ticketNum[1] + '\n'+
                        "Reporter :" + ticketNum[2] + '\n' + "Fixer time :" + ticketNum[3] + '\n' +
                        "Priority :" + ticketNum[4] + '\n' + "Assignee :" + ticketNum[5] + '\n' +
                        "Status :" + ticketNum[6] + '\n' + "Comments :"+ticketNum[7]);
                    writer.close();
                }
            } else {
                System.out.println("File Exsist " + createTicket_txt.getAbsolutePath());
            }
        } catch (IOException e) {
            System.out.println("ERROR " + e.getMessage());
        }
    }
    public String issueEditing(String data) throws IOException {
        //이슈를 읽는 메서드.
        String retStr ="";
        File file = new File(ticketDirPath + data);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line ="";

        String ReadBuffer ="";
        System.out.println("\nRead Line Start");
        try{
            while ((line = bufferedReader.readLine()) != null){
                ReadBuffer = line;
                //Buffer for Catch clause
                // -because it passes away when exception
                String[] readLine = ReadBuffer.split(":");
                retStr = retStr + '@' +readLine[1];
            }
            System.out.println(retStr);
            return retStr.substring(1);

        }
        catch(ArrayIndexOutOfBoundsException e){
            //execption 이 일어나면서 바로 다음문장을 씹었음.
            //

            retStr = retStr + '\n' + ReadBuffer + '\n'; //문장이 씹혔으니까 개행문자를 꼭 넣어줘야함!!
            while ((line = bufferedReader.readLine()) != null) {
                ReadBuffer = line;
                if (ReadBuffer.equals("")){
                    retStr = retStr + '\n';
                }
                else retStr = retStr + ReadBuffer + '\n';
            }
            System.out.println("ret : " + retStr);
            return retStr.substring(1);
        }
        finally {
            bufferedReader.close();
            fileReader.close();
            System.out.println("Read Line End\n");
        }
    }
    public void rewriteIssue(String data){
        String[] splitedData = data.split("@");
        File del_file = new File("Tickets/" + splitedData[0]);

        if (del_file.exists()) {
            del_file.delete();
            System.out.println("Deleted");
        }
        else System.out.println("No such file ");
        System.out.println(splitedData[0]);

        newIssue(data);
    }
}
