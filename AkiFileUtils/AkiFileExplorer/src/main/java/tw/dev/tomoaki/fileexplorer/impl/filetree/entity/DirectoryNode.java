/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.fileexplorer.impl.filetree.entity;

import tw.dev.tomoaki.entity.TreeNode;
import java.util.List;

/**
 *
 * @author Tomoaki Chen
 */
public class DirectoryNode /*extends File*/ extends FileTreeNode {

    private DirectoryNode parent;
    private List<TreeNode> treeNode; 

    @Override
    public Boolean isRoot() {
        return false;
    }

    @Override
    public Boolean isLeaf() {
        return false;
    }

}
