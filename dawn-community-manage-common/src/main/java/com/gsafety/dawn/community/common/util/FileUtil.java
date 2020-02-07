package com.gsafety.dawn.community.common.util;

import org.apache.commons.codec.binary.Base64;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * The type File util.
 */
public class FileUtil {
    private FileUtil(){
        //无参构造
    }

    /**
     * 把上传的图片转换成转换成byte
     *
     * @param file the file
     * @return the byte [ ]
     * @throws IOException the io exception
     */
    public static byte[] fileToByte(MultipartFile file) throws IOException {
        InputStream is = file.getInputStream();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int ch;
        while ((ch = is.read()) != -1) {
            byteArrayOutputStream.write(ch);
        }
        byte[] data = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();
        return data;
    }

    /**
     * Replacer string.
     *
     * @param string the string
     * @return the string
     */
//Decode
    public static String replacer(String string) {
        String strMsg = string ;
        try {
            strMsg = strMsg.replaceAll("%(?![0-9a-fA-F]{2})", "%25");
            strMsg = strMsg.replaceAll("\\+", "%2B");
            strMsg = URLDecoder.decode(strMsg, "utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e); //NOSONAR

        }
        return strMsg;
    }

    /**
     * byte[]转成base64;
     *
     * @param bytes the bytes
     * @return the string
     */
    public static String byteToBase64(byte[] bytes){
        return Base64.encodeBase64String(bytes);
    }

    /**
     * base64字符串byte[];
     *
     * @param base64Str the base 64 str
     * @return the byte [ ]
     */
    public static byte[] base64Tobyte(String base64Str){
        return Base64.decodeBase64(base64Str);
    }

}
