package pl.pjatk.bsi_algorytmy.tDes;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import pl.pjatk.bsi_algorytmy.Algorithm;

import java.util.Scanner;

/**
 * Encrypt/Decrypt text by using Triple-DES
 *
 * source: https://gist.github.com/riversun
 *
 * @author Filip Werra
 */
public class TripleDes implements Algorithm{

    Scanner scanner = new Scanner(System.in);

    private static final String CRYPT_ALGORITHM = "DESede";
    private static final String PADDING = "DESede/CBC/NoPadding";
    private static final String CHAR_ENCODING = "UTF-8";

    private static final byte[] MY_KEY = "5oquil2oo2vb63e8ionujny6".getBytes();//24-byte
    private static final byte[] MY_IV = "3oco1v52".getBytes();//8-byte

    /**
     *
     */
    public void use(){

        System.out.println("Input your text to code");
        String srcMyText = scanner.nextLine();

        // create crypter
        final TripleDes crypter = new TripleDes();

        // do encrypt
        String encryptedText = crypter.encrypt(srcMyText);

        // show result
        System.out.println("sourceText=" + srcMyText + " -> encryptedText=" + encryptedText + "\n");

        System.out.println("encrypted-text=" + encryptedText + " -> decrypted-text(source text)="
                + crypter.decrypt(encryptedText));
    }

    /**
     * Encrypt text to encrypted-text
     */
    public String encrypt(String text) {

        if (text == null) {
            return null;
        }

        String retVal = null;

        try {

            final SecretKeySpec secretKeySpec = new SecretKeySpec(MY_KEY, CRYPT_ALGORITHM);

            final IvParameterSpec iv = new IvParameterSpec(MY_IV);

            final Cipher cipher = Cipher.getInstance(PADDING);

            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, iv);

            final byte[] encrypted = cipher.doFinal(text.getBytes(CHAR_ENCODING));

            retVal = new String(encodeHex(encrypted));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return retVal;
    }

    /**
     * Decrypt encrypted-text
     */
    public String decrypt(String text) {

        if (text == null) {
            return null;
        }

        String retVal = null;

        try {

            final SecretKeySpec secretKeySpec = new SecretKeySpec(MY_KEY, CRYPT_ALGORITHM);
            final IvParameterSpec iv = new IvParameterSpec(MY_IV);

            final Cipher cipher = Cipher.getInstance(PADDING);

            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, iv);

            final byte[] decrypted = cipher.doFinal(decodeHex(text.toCharArray()));

            retVal = new String(decrypted, CHAR_ENCODING);

        } catch (Exception e) {

            e.printStackTrace();
        }

        return retVal;
    }

    /**
     *
     * Converts an array of characters representing hexadecimal values into an array
     * of bytes of those same values. The returned array will be half the length of
     * the passed array, as it takes two characters to represent any given byte. An
     * exception is thrown if the passed char array has an odd number of elements.
     * <br>
     * Portion of Apache Software Foundation
     *
     * @param data
     *            An array of characters containing hexadecimal digits
     * @return A byte array containing binary data decoded from the supplied char
     *         array.
     * @throws Exception
     *             Thrown if an odd number or illegal of characters is supplied
     *
     *
     */
    private byte[] decodeHex(char[] data) throws Exception {

        int len = data.length;

        if ((len & 0x01) != 0) {
            throw new Exception("Odd number of characters.");
        }

        byte[] out = new byte[len >> 1];

        // two characters form the hex value.
        for (int i = 0, j = 0; j < len; i++) {

            int f = toDigit(data[j], j) << 4;
            j++;
            f = f | toDigit(data[j], j);
            j++;
            out[i] = (byte) (f & 0xFF);
        }

        return out;
    }

    /**
     * Converts a hexadecimal character to an integer. <br>
     * Portion of Apache Software Foundation
     *
     * @param ch
     *            A character to convert to an integer digit
     * @param index
     *            The index of the character in the source
     * @return An integer
     * @throws Exception
     *             Thrown if ch is an illegal hex character
     */
    private int toDigit(char ch, int index) throws Exception {
        int digit = Character.digit(ch, 16);
        if (digit == -1) {
            throw new Exception("Illegal hexadecimal character " + ch + " at index " + index);
        }
        return digit;
    }

    /**
     * Converts an array of bytes into an array of characters representing the
     * hexadecimal values of each byte in order. The returned array will be double
     * the length of the passed array, as it takes two characters to represent any
     * given byte. <br>
     * Portion of Apache Software Foundation
     */
    private char[] encodeHex(byte[] data) {

        final char[] DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

        int l = data.length;
        char[] out = new char[l << 1];
        // two characters form the hex value.
        for (int i = 0, j = 0; i < l; i++) {
            out[j++] = DIGITS[(0xF0 & data[i]) >>> 4];
            out[j++] = DIGITS[0x0F & data[i]];
        }
        return out;
    }
}