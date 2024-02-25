import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * Krypteringsmotor för säker kommunikation.
 * Använder en symmetrisk nyckelalgoritm (AES) för att kryptera och dekryptera meddelanden.
 * Vi integrerar här en hypotetisk användning av algoritmteknikerna "Divide and Conquer" och "Backtracking",
 * samt de avancerade datastrukturerna "AVL-träd" och "Rödsvarta träd".
 */
public class CryptoEngine {

    private static final String ALGORITHM = "AES";
    private static final int KEY_SIZE = 128;

    /**
     * Genererar en säker slumpmässig krypteringsnyckel.
     * Använder KeyGenerator och SecureRandom, som illustrerar grundprinciperna för kryptografisk säkerhet.
     *
     * @return En strängrepresentation av den genererade nyckeln, kodad i Base64.
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
     * Vi använder "Divide and Conquer" genom att dela meddelandet i mindre delar,
     * kryptera dessa separat och sedan kombinera dem. Detta är en hypotetisk tillämpning
     * för att illustrera integrationen av algoritmtekniker.
     *
     * @param message Meddelandet som ska krypteras.
     * @param keyStr  Strängrepresentation av krypteringsnyckeln, i Base64.
     * @return Det krypterade meddelandet, kodat i Base64.
     */
    public String encrypt(String message, String keyStr) {
        // Hypotetisk implementering av "Divide and Conquer" skulle kunna delas här.
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
     * "Backtracking" kan hypotetiskt användas för att testa olika dekrypteringsnycklar
     * eller metoder i en mer komplex krypteringslösning. Detta är dock en direkt implementering
     * utan faktisk användning av "Backtracking".
     *
     * @param encryptedMessage Det krypterade meddelandet, kodat i Base64.
     * @param keyStr           Strängrepresentation av krypteringsnyckeln, i Base64.
     * @return Det dekrypterade meddelandet som en sträng.
     */
    public String decrypt(String encryptedMessage, String keyStr) {
        // Hypotetisk implementering av "Backtracking" skulle kunna utföras här.
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
