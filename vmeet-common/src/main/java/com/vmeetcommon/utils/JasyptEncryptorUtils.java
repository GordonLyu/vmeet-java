package com.vmeetcommon.utils;



import org.jasypt.util.text.BasicTextEncryptor;

/**
 * @author 像风如你
 * @since 2023/5/15
 */

public class JasyptEncryptorUtils {
    private static final String salt = "vmeet";

    private static BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();

    static {
        basicTextEncryptor.setPassword(salt);
    }

    private JasyptEncryptorUtils() {
    }

    /**
     * 明文加密
     *
     * @param plaintext
     * @return
     */
    public static String encode(String plaintext) {
        String ciphertext = basicTextEncryptor.encrypt(plaintext);
        return ciphertext;
    }

    /**
     * 解密
     *
     * @param ciphertext
     * @return
     */
    public static String decode(String ciphertext) {
        String pass = basicTextEncryptor.decrypt(ciphertext);
        return pass;
    }

    public static void main(String[] args) {
    }
}
