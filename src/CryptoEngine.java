import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * Krypteringsmotor för säker kommunikation.
 * Använder en symmetrisk nyckelalgoritm (AES) för att kryptera och dekryptera meddelanden.
 * Denna klass exemplifierar inte direkt användningen av de specifika algoritmteknikerna eller de avancerade datastrukturerna
 * men den implementerar grundläggande principer för kryptografisk säkerhet.
 */
public class CryptoEngine {

    private static final String ALGORITHM = "AES"; // AES är en vanlig symmetrisk krypteringsalgoritm.
    private static final int KEY_SIZE = 128; // Anger storleken på krypteringsnyckeln i bitar, standard för AES.

    /**
     * Genererar en säker slumpmässig krypteringsnyckel.
     * Använder KeyGenerator och SecureRandom för att garantera en hög entropi i nyckelgenereringen,
     * vilket är avgörande för kryptografisk säkerhet.
     *
     * @return En strängrepresentation av den genererade nyckeln, kodad i Base64.
     */
    public String generateKey() {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITHM); // Skapar en nyckelgenerator för AES.
            keyGen.init(KEY_SIZE, new SecureRandom()); // Initialiserar generatorn med nyckelstorlek och säker slumpgenerator.
            SecretKey secretKey = keyGen.generateKey(); // Genererar den faktiska nyckeln.
            return Base64.getEncoder().encodeToString(secretKey.getEncoded()); // Kodar nyckeln till Base64-sträng.
        } catch (Exception e) {
            throw new RuntimeException("Fel vid generering av nyckel", e);
        }
    }

    /**
     * Krypterar ett meddelande med angiven nyckel.
     * Använder Cipher-klassen för att utföra krypteringen, vilket illustrerar en grundläggande implementation
     * av kryptografiska operationer i Java.
     *
     * @param message Meddelandet som ska krypteras.
     * @param keyStr  Strängrepresentation av krypteringsnyckeln, i Base64.
     * @return Det krypterade meddelandet, kodat i Base64, för att säkerställa att resultatet är hanterbart som text.
     */
    public String encrypt(String message, String keyStr) {
        try {
            SecretKey key = new SecretKeySpec(Base64.getDecoder().decode(keyStr), ALGORITHM); // Omvandlar strängnyckeln tillbaka till SecretKey.
            Cipher cipher = Cipher.getInstance(ALGORITHM); // Skapar en Cipher-instans för AES.
            cipher.init(Cipher.ENCRYPT_MODE, key); // Initialiserar Cipher i krypteringsläge med den angivna nyckeln.
            byte[] encryptedBytes = cipher.doFinal(message.getBytes()); // Utför själva krypteringen.
            return Base64.getEncoder().encodeToString(encryptedBytes); // Kodar det krypterade meddelandet till Base64.
        } catch (Exception e) {
            throw new RuntimeException("Fel vid kryptering", e);
        }
    }

    /**
     * Dekrypterar ett meddelande med angiven nyckel.
     * Liknar krypteringsmetoden men använder Cipher i dekrypteringsläge.
     *
     * @param encryptedMessage Det krypterade meddelandet, kodat i Base64.
     * @param keyStr           Strängrepresentation av krypteringsnyckeln, i Base64.
     * @return Det dekrypterade meddelandet som en sträng.
     */
    public String decrypt(String encryptedMessage, String keyStr) {
        try {
            SecretKey key = new SecretKeySpec(Base64.getDecoder().decode(keyStr), ALGORITHM); // Återställer SecretKey från sträng.
            Cipher cipher = Cipher.getInstance(ALGORITHM); // Skapar Cipher för AES.
            cipher.init(Cipher.DECRYPT_MODE, key); // Initialiserar i dekrypteringsläge.
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedMessage)); // Dekrypterar meddelandet.
            return new String(decryptedBytes); // Omvandlar de dekrypterade byten tillbaka till en sträng.
        } catch (Exception e) {
            throw new RuntimeException("Fel vid dekryptering", e);
        }
    }
}
