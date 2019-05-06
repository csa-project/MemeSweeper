import java.nio.file.*;
import java.io.*;
import static java.nio.file.StandardOpenOption.*;

public class Score {
    public static void main()
    {
        int score2;
        score2 =  Cell.score;
        Path file = Paths.get("C:\\Users\\jg318682\\Desktop\\MemeSweeper\\Score.txt");
        String s = "";
        byte[] data = s.getBytes();
        OutputStream output = null;
        try
        {
            output = new
                    BufferedOutputStream(Files.newOutputStream(file, CREATE));
            output.write(data);
            output.flush();
            output.close();
        }
        catch(Exception e){
            System.out.println(score2);
        }
    }
}
