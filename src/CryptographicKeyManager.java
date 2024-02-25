import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Hanterar kryptografiska nycklar med hjälp av en slumpmässig algoritm för att generera nycklar.
 * Använder en förenklad representation av ett rödsvart träd (HashMap) för att effektivt lagra och hämta nycklar.
 */
public class CryptographicKeyManager {
    // En enkel representation för att lagra nycklar. HashMap används här som en förenklad version av ett rödsvart träd,
    // eftersom det erbjuder effektiv insättning, sökning och borttagning av element.
    private Map<String, String> keyStore;

    public CryptographicKeyManager() {
        this.keyStore = new HashMap<>();
    }

    /**
     * Genererar en nyckel med hjälp av en slumpmässig algoritm och lagrar den med ett unikt id.
     * Denna metod illustrerar användningen av en slumpmässig algoritm för att säkerställa att varje genererad nyckel är unik.
     *
     * @param keyId Identifierare för nyckeln.
     */
    public void generateAndStoreKey(String keyId) {
        String generatedKey = generateRandomKey(); // Anropar en intern metod för att generera en slumpmässig nyckel.
        keyStore.put(keyId, generatedKey); // Lagrar den genererade nyckeln i keyStore med det unika id:t som nyckel.
    }

    /**
     * Hämtar en lagrad nyckel baserat på dess id.
     * Metoden demonstrerar effektiv sökning i en HashMap, vilket är en operation som vanligtvis är snabb i ett rödsvart träd.
     *
     * @param keyId Identifierare för nyckeln som ska hämtas.
     * @return Den hämtade nyckeln om den finns, annars null.
     */
    public String getKey(String keyId) {
        return keyStore.get(keyId); // Hämtar den lagrade nyckeln baserat på id. Returnerar null om nyckeln inte finns.
    }

    /**
     * Genererar en slumpmässig nyckel. Denna metod förenklar processen och genererar en baserad på slumpmässiga tal.
     * Användningen av klassen Random illustrerar en grundläggande implementering av en slumpmässig algoritm.
     * I en verklig implementation bör detta göras med säkrare metoder för att generera kryptografiskt säkra nycklar.
     *
     * @return En slumpmässigt genererad nyckel som en sträng.
     */
    private String generateRandomKey() {
        Random random = new Random(); // Skapar en instans av Random för att generera slumpmässiga tal.
        return Integer.toString(random.nextInt()); // Genererar ett slumpmässigt heltal och konverterar det till en sträng.
    }

    public static void main(String[] args) {
        CryptographicKeyManager manager = new CryptographicKeyManager();
        manager.generateAndStoreKey("user1"); // Genererar och lagrar en nyckel för 'user1'.
        System.out.println("Generated Key for user1: " + manager.getKey("user1")); // Skriver ut den genererade nyckeln för 'user1'.
    }
}
