import java.util.Scanner;

/**
 * Användargränssnitt för det avancerade kryptografisystemet.
 * Tillåter användare att kryptera, dekryptera meddelanden och hantera dem.
 */
public class UserInterface {

    private final CryptoEngine cryptoEngine; // Kärnkomponent för krypteringsoperationer.
    private final EncryptedMessageManager messageManager; // Hanterar lagring och återhämtning av krypterade meddelanden.
    private final Scanner scanner; // Scanner används för att läsa användarinmatning från konsolen.

    /**
     * Konstruktor som initierar användargränssnittet med en CryptoEngine och EncryptedMessageManager.
     */
    public UserInterface() {
        this.cryptoEngine = new CryptoEngine(); // Skapar en instans av CryptoEngine.
        this.messageManager = new EncryptedMessageManager(); // Skapar en instans av EncryptedMessageManager.
        this.scanner = new Scanner(System.in); // Initierar Scanner för att läsa från standardinmatningen.
    }

    /**
     * Startar användargränssnittet.
     * Användaren får en meny med valmöjligheter för att utföra olika krypteringsrelaterade operationer.
     */
    public void start() {
        boolean running = true; // Kontrollvariabel för att hålla igång huvudloopen.
        String key = ""; // Variabel för att lagra den nuvarande krypteringsnyckeln.

        System.out.println("Välkommen till det avancerade kryptografisystemet!");

        while (running) { // Huvudloop för användargränssnittet.
            System.out.println("\nVälj ett alternativ:");
            System.out.println("1. Generera nyckel");
            System.out.println("2. Kryptera meddelande");
            System.out.println("3. Dekryptera meddelande");
            System.out.println("4. Avsluta");

            String choice = scanner.nextLine(); // Läser användarens val.

            switch (choice) { // Hanterar användarens val.
                case "1": // Generera nyckel.
                    key = cryptoEngine.generateKey();
                    System.out.println("Nyckel genererad: " + key);
                    break;
                case "2": // Kryptera meddelande.
                    System.out.println("Ange meddelande att kryptera:");
                    String message = scanner.nextLine();
                    String encryptedMessage = cryptoEngine.encrypt(message, key);
                    String messageKey = messageManager.storeEncryptedMessage(encryptedMessage);
                    System.out.println("Meddelande krypterat och lagrat med nyckel: " + messageKey);
                    break;
                case "3": // Dekryptera meddelande.
                    System.out.println("Ange nyckel för att hämta och dekryptera meddelande:");
                    String retrievalKey = scanner.nextLine();
                    String toDecrypt = messageManager.retrieveEncryptedMessage(retrievalKey);
                    if (toDecrypt != null) {
                        String decryptedMessage = cryptoEngine.decrypt(toDecrypt, key);
                        System.out.println("Dekrypterat meddelande: " + decryptedMessage);
                    } else {
                        System.out.println("Inget meddelande hittades för angiven nyckel.");
                    }
                    break;
                case "4": // Avsluta programmet.
                    running = false;
                    System.out.println("Avslutar...");
                    break;
                default: // Felaktigt val hanteras här.
                    System.out.println("Ogiltigt val, försök igen.");
            }
        }
    }

    /**
     * Huvudmetod för att köra programmet.
     * Skapar en instans av UserInterface och startar användargränssnittet.
     *
     * @param args Argument för kommandoraden (används inte).
     */
    public static void main(String[] args) {
        UserInterface ui = new UserInterface(); // Skapar användargränssnittet.
        ui.start(); // Startar användargränssnittet.
    }
}
