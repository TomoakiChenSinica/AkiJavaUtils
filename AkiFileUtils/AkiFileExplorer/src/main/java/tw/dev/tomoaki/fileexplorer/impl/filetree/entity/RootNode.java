/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.fileexplorer.impl.filetree.entity;

/**
 *
 * @author Tomoaki Chen
 */
public class RootNode extends DirectoryNode {
    
    @Override
    public Boolean isRoot() {
        return true;
    }

}
