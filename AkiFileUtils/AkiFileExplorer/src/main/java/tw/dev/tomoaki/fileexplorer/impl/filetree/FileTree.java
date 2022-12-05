package tw.dev.tomoaki.fileexplorer.impl.filetree;

import java.io.File;
import tw.dev.tomoaki.fileexplorer.core.AbsFileAnalyzer;
import tw.dev.tomoaki.fileexplorer.impl.filetree.entity.RootNode;
import tw.dev.tomoaki.fileexplorer.impl.filetree.entity.FileTreeNode;

public class FileTree extends AbsFileAnalyzer<FileTreeNode> {

    private RootNode rootNode;

    protected FileTree(Class resultClazz) {
        super(resultClazz);
    }    
    
    @Override
    public FileTreeNode doAnalyze4Dir(String parentDirPath, File desigDir, FileTreeNode result) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public FileTreeNode doAnalyze4File(String parentDirPath, File desigFile, FileTreeNode result) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
