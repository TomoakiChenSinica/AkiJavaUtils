/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.fileexplorer.impl.filetree.entity;

import tw.dev.tomoaki.entity.TreeNode;
import java.io.File;

/**
 *
 * @author Tomoaki Chen
 */
public class FileNode /*extends File*/ extends FileTreeNode {

//    
//    protected FileNode()
//
//    
//    protected FileNode(String parent, String child) {
//        super(parent, child);
//    }
//    
//    public FileNode(String pathName) {
//    
//    }
    private DirectoryNode parent;

    @Override
    public Boolean isRoot() {
        return false;
    }

    @Override
    public Boolean isLeaf() {
        return true;
    }
}
