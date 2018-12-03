package im.joshua.jutils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @description: 编码器
 * @author: joshua
 * @E-mail: joshua.yin@ucloud.cn
 * @date: 2018/11/7 17:29
 */
public class Encoder {
    public final static String TYPE_MD5 = "MD5";

    /**
     * MD5 编码
     *
     * @param data 编码内容
     * @return 编码结果
     * @throws NoSuchAlgorithmException
     */
    public static byte[] md5(byte[] data) throws NoSuchAlgorithmException {
        if (data == null || data.length == 0)
            return null;

        MessageDigest md5 = MessageDigest.getInstance(TYPE_MD5);
        return md5.digest(data);
    }

    public static byte[] md5(File file) throws NoSuchAlgorithmException, IOException {
        if (!file.exists() || !file.isFile())
            return null;

        return md5(new FileInputStream(file));
    }

    public static byte[] md5(InputStream inputStream) throws NoSuchAlgorithmException, IOException {
        if (inputStream == null)
            return null;

        MessageDigest md5 = MessageDigest.getInstance(TYPE_MD5);
        try {
            byte[] buff = new byte[64 * 1024];
            int len = 0;
            while ((len = inputStream.read(buff)) > 0)
                md5.update(buff, 0, len);
        } finally {
            FileUtil.close(inputStream);
        }

        return md5.digest();
    }
}
