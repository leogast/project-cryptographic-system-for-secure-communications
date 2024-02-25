import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testklass för CryptoEngine.
 * Verifierar funktionaliteten för nyckelgenerering, kryptering och dekryptering.
 */
class CryptoEngineTest {

    private CryptoEngine cryptoEngine;

    @BeforeEach
    void setUp() {
        cryptoEngine = new CryptoEngine();
    }

    @Test
    void testGenerateKey_NotNull() {
        assertNotNull(cryptoEngine.generateKey(), "Genererad nyckel bör inte vara null");
    }

    @Test
    void testEncryptDecrypt_MessageIntegrity() {
        String originalMessage = "Hemligt meddelande";
        String key = cryptoEngine.generateKey();
        String encryptedMessage = cryptoEngine.encrypt(originalMessage, key);
        String decryptedMessage = cryptoEngine.decrypt(encryptedMessage, key);

        assertEquals(originalMessage, decryptedMessage, "Det dekrypterade meddelandet bör matcha det ursprungliga");
    }

    @Test
    void testEncrypt_NotSameAsOriginal() {
        String originalMessage = "Testmeddelande";
        String key = cryptoEngine.generateKey();
        String encryptedMessage = cryptoEngine.encrypt(originalMessage, key);

        assertNotEquals(originalMessage, encryptedMessage, "Det krypterade meddelandet bör inte matcha det ursprungliga");
    }

    @Test
    void testDecrypt_WithWrongKey() {
        String originalMessage = "Ett annat hemligt meddelande";
        String key = cryptoEngine.generateKey();
        String wrongKey = cryptoEngine.generateKey(); // Generate a different key
        String encryptedMessage = cryptoEngine.encrypt(originalMessage, key);

        assertThrows(RuntimeException.class, () -> cryptoEngine.decrypt(encryptedMessage, wrongKey),
                "Dekryptering med fel nyckel bör kasta ett undantag");
    }
}
