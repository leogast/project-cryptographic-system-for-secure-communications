import java.util.TreeMap;
import java.util.UUID;

/**
 * Hanterar lagrade krypterade meddelanden.
 * Använder en TreeMap för att effektivt lagra och hämta meddelanden baserat på unika nycklar.
 */
public class EncryptedMessageManager {

    private final TreeMap<String, String> encryptedMessages;

    /**
     * Konstruktor som initierar meddelandehanteraren.
     */
    public EncryptedMessageManager() {
        encryptedMessages = new TreeMap<>();
    }

    /**
     * Lagrar ett krypterat meddelande och returnerar en unik nyckel för åtkomst.
     *
     * @param message Det krypterade meddelandet som ska lagras.
     * @return En unik nyckel för att hämta det lagrade meddelandet.
     */
    public String storeEncryptedMessage(String message) {
        String uniqueKey = UUID.randomUUID().toString();
        encryptedMessages.put(uniqueKey, message);
        return uniqueKey;
    }

    /**
     * Hämtar ett krypterat meddelande baserat på dess unika nyckel.
     *
     * @param key Den unika nyckeln för det meddelande som ska hämtas.
     * @return Det krypterade meddelandet, eller null om ingen matchning finns.
     */
    public String retrieveEncryptedMessage(String key) {
        return encryptedMessages.get(key);
    }

    /**
     * Raderar ett krypterat meddelande baserat på dess unika nyckel.
     *
     * @param key Den unika nyckeln för det meddelande som ska raderas.
     * @return true om meddelandet raderades, annars false.
     */
    public boolean deleteEncryptedMessage(String key) {
        return encryptedMessages.remove(key) != null;
    }
}
