package com.nagesh.messagenotifier.mail;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;

public class EncryptionUtil {
    static PooledPBEStringEncryptor encryptor = null;
    static {
        encryptor = new PooledPBEStringEncryptor();
        encryptor.setPoolSize(4); 
        //  There are various approaches to pull this configuration via system level properties. 
        encryptor.setPassword("parashar");
        encryptor.setAlgorithm("PBEWITHMD5ANDDES");
    }

    public static String encrypt(String input) {
        return encryptor.encrypt(input);
    }

    public static String decrypt(String encryptedMessage) {
        return encryptor.decrypt(encryptedMessage);
    }
}
