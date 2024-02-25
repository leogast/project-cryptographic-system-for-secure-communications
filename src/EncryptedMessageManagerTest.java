import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testklass för EncryptedMessageManager.
 * Verifierar lagring, hämtning och radering av krypterade meddelanden.
 */
class EncryptedMessageManagerTest {

    private EncryptedMessageManager messageManager;

    @BeforeEach
    void setUp() {
        messageManager = new EncryptedMessageManager();
    }

    @Test
    void testStoreEncryptedMessage_ReturnsUniqueKey() {
        String encryptedMessage = "krypteratMeddelande";
        String key = messageManager.storeEncryptedMessage(encryptedMessage);
        assertNotNull(key, "Nyckeln bör inte vara null");
        assertFalse(key.isEmpty(), "Nyckeln bör inte vara tom");
    }

    @Test
    void testRetrieveEncryptedMessage_ValidKey() {
        String originalMessage = "ettKrypteratMeddelande";
        String key = messageManager.storeEncryptedMessage(originalMessage);
        String retrievedMessage = messageManager.retrieveEncryptedMessage(key);

        assertEquals(originalMessage, retrievedMessage, "Hämtat meddelande bör matcha det ursprungligen lagrade");
    }

    @Test
    void testRetrieveEncryptedMessage_InvalidKey() {
        String key = "ickeExisterandeNyckel";
        assertNull(messageManager.retrieveEncryptedMessage(key), "Hämtning med ogiltig nyckel bör returnera null");
    }

    @Test
    void testDeleteEncryptedMessage_ValidKey() {
        String message = "meddelandeAttRadera";
        String key = messageManager.storeEncryptedMessage(message);
        assertTrue(messageManager.deleteEncryptedMessage(key), "Radering med giltig nyckel bör returnera true");
        assertNull(messageManager.retrieveEncryptedMessage(key), "Meddelandet bör vara raderat");
    }

    @Test
    void testDeleteEncryptedMessage_InvalidKey() {
        String key = "ickeExisterandeNyckelFörRadering";
        assertFalse(messageManager.deleteEncryptedMessage(key), "Radering med ogiltig nyckel bör returnera false");
    }
}
