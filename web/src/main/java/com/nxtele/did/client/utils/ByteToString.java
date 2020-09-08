package com.nxtele.did.client.utils;

import org.nustaq.serialization.FSTObjectInput;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class ByteToString {

    /**
    *                         将 redis查出来的 value 转成 obj
    * @author gc
    * @date   2020/6/10 19:44
    * @param
    * @return
    */
    public static Object valueFromBytes(byte[] bytes) {
        if(bytes == null || bytes.length == 0)
            return null;

        FSTObjectInput fstInput = null;
        try {
            fstInput = new FSTObjectInput(new ByteArrayInputStream(bytes));
            return fstInput.readObject();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            if(fstInput != null)
                try {fstInput.close();} catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }


}
