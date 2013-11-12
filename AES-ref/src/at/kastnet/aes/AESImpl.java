package at.kastnet.aes;

import iaik.security.cipher.SecretKey;
import iaik.security.provider.IAIK;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class AESImpl {

  static {
    IAIK.addAsProvider();
  }

  /**
   * @param args
   * @throws NoSuchPaddingException
   * @throws NoSuchAlgorithmException
   * @throws NoSuchProviderException
   * @throws InvalidKeyException
   * @throws BadPaddingException
   * @throws IllegalBlockSizeException
   */
  public static void main(String[] args) throws NoSuchAlgorithmException,
      NoSuchPaddingException, NoSuchProviderException, InvalidKeyException,
      IllegalBlockSizeException, BadPaddingException {

    Cipher aes = Cipher.getInstance("AES/CTR/NoPadding", "IAIK");

    byte[] keyBytes = { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
        0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 };

    byte[] ctr = { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
        0x00, 0x00, 0x00, 0x00, 0x00, 0x00 };

    SecretKey key = new SecretKey(keyBytes, "AES");
    
    SecureRandom random = new SecureRandom(ctr);

    aes.init(Cipher.ENCRYPT_MODE, key, random);

    byte[] ciphertext = aes.doFinal("!my_evil_bytes!".getBytes());

    System.out.println("AES CTR: " + iaik.utils.Util.toString(ciphertext, ":"));

  }

}
