/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.util.entity;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 *
 * @author tomoaki
 */
public final class BytesData {

    private final String name;
    private final byte[] container;

    protected BytesData(String name, byte[] bytes) {
        this.name = name;
        this.container = bytes;
    }

    public static BytesData of(String name, byte[] bytes) {
        return new BytesData(name, bytes);
    }
    
    public String getName() {
        return this.name;
    }

    public byte[] getBytes() {
        return this.container;
    }

    public InputStream toInputStream() {
        return new ByteArrayInputStream(container);
    }
}
