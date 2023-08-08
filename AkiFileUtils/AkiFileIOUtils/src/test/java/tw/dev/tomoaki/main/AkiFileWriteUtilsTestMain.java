package tw.dev.tomoaki.main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import tw.dev.tomoaki.fileioutils.AkiFileWriteUtils;

/**
 *
 * @author tomoaki
 */
public class AkiFileWriteUtilsTestMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        test3();
    }

    protected static void test1() throws IOException {
        //String sourceFilePath = "C:\\AkiRoot\\ProgramPlayGround\\Java\\System-ResearchPaper\\OldSystem\\papers\\dtlee\\25888-F.pdf";
        String sourceFilePath = "C:\\AkiRoot\\ProgramPlayGround\\Java\\System-ResearchPaper\\OldSystem\\papers\\kinkin\\7492-F.pdf";
        String targetDirPath = "C:\\AkiRoot\\ProgramPlayGround\\Java\\System-ResearchPaper\\Target\\Test.pdf";

        File resultFile = AkiFileWriteUtils.write(sourceFilePath, targetDirPath);
        System.out.println("resultFile= " + resultFile + ", exists? " + resultFile.exists());
    }

    protected static void test2() throws IOException {
        String sourceFilePath = "C:\\AkiRoot\\ProgramPlayGround\\Java\\System-ResearchPaper\\OldSystem\\papers\\jascha\\2203-F.pdf";    //2007-10-01  
//        String sourceFilePath = "C:\\AkiRoot\\ProgramPlayGround\\Java\\System-ResearchPaper\\OldSystem\\papers\\kinkin\\7492-F.pdf";  //2008-11-27  
//        String sourceFilePath = "C:\\AkiRoot\\ProgramPlayGround\\Java\\System-ResearchPaper\\OldSystem\\papers\\dtlee\\25888-F.pdf"; //2019-07-19
        String targetDirPath = "C:\\AkiRoot\\ProgramPlayGround\\Java\\System-ResearchPaper\\Target\\Test.pdf";

        File resultFile = AkiFileWriteUtils.copyNecessarily(sourceFilePath, targetDirPath);
        String msgFmt = "resultFile= %s, exists? %s, lastModifiedDateTime= %s";
        System.out.println(String.format(msgFmt, resultFile, resultFile.exists(), Files.getLastModifiedTime(resultFile.toPath())));
        
    }
    
    protected static void test3() throws IOException {
        String sourceFilePath = "C:\\AkiRoot\\ProgramPlayGround\\Java\\System-ResearchPaper\\OldSystem\\papers\\jascha\\2203-F.pdf";    //2007-10-01  
        Path sourcePath = Paths.get(sourceFilePath);
        
        System.out.println(sourcePath.getFileName().toString());
    }    

}
