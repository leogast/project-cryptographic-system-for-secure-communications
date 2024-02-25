import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * Krypteringsmotor för säker kommunikation.
 * Använder en symmetrisk nyckelalgoritm för att kryptera och dekryptera meddelanden.
 */
public class CryptoEngine {

    private static final String ALGORITHM = "AES";
    private static final int KEY_SIZE = 128; // Storlek på krypteringsnyckeln i bitar

    /**
     * Genererar en säker slumpmässig krypteringsnyckel.
     *
     * @return En strängrepresentation av den genererade nyckeln.
     */
    public String generateKey() {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITHM);
            keyGen.init(KEY_SIZE, new SecureRandom());
            SecretKey secretKey = keyGen.generateKey();
            return Base64.getEncoder().encodeToString(secretKey.getEncoded());
        } catch (Exception e) {
            throw new RuntimeException("Fel vid generering av nyckel", e);
        }
    }

    /**
     * Krypterar ett meddelande med angiven nyckel.
     *
     * @param message Meddelandet som ska krypteras.
     * @param keyStr  Strängrepresentation av krypteringsnyckeln.
     * @return Det krypterade meddelandet, kodat i Base64.
     */
    public String encrypt(String message, String keyStr) {
        try {
            SecretKey key = new SecretKeySpec(Base64.getDecoder().decode(keyStr), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptedBytes = cipher.doFinal(message.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("Fel vid kryptering", e);
        }
    }

    /**
     * Dekrypterar ett meddelande med angiven nyckel.
     *
     * @param encryptedMessage Det krypterade meddelandet, kodat i Base64.
     * @param keyStr           Strängrepresentation av krypteringsnyckeln.
     * @return Det dekrypterade meddelandet.
     */
    public String decrypt(String encryptedMessage, String keyStr) {
        try {
            SecretKey key = new SecretKeySpec(Base64.getDecoder().decode(keyStr), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedMessage));
            return new String(decryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("Fel vid dekryptering", e);
        }
    }
}
