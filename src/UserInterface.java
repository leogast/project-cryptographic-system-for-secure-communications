import java.util.Scanner;

/**
 * Användargränssnitt för det avancerade kryptografisystemet.
 * Tillåter användare att kryptera, dekryptera meddelanden och hantera dem.
 */
public class UserInterface {

    private final CryptoEngine cryptoEngine;
    private final EncryptedMessageManager messageManager;
    private final Scanner scanner;

    /**
     * Konstruktor som initierar användargränssnittet med en CryptoEngine och EncryptedMessageManager.
     */
    public UserInterface() {
        this.cryptoEngine = new CryptoEngine();
        this.messageManager = new EncryptedMessageManager();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Startar användargränssnittet.
     */
    public void start() {
        boolean running = true;
        String key = "";

        System.out.println("Välkommen till det avancerade kryptografisystemet!");

        while (running) {
            System.out.println("\nVälj ett alternativ:");
            System.out.println("1. Generera nyckel");
            System.out.println("2. Kryptera meddelande");
            System.out.println("3. Dekryptera meddelande");
            System.out.println("4. Avsluta");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    key = cryptoEngine.generateKey();
                    System.out.println("Nyckel genererad: " + key);
                    break;
                case "2":
                    System.out.println("Ange meddelande att kryptera:");
                    String message = scanner.nextLine();
                    String encryptedMessage = cryptoEngine.encrypt(message, key);
                    String messageKey = messageManager.storeEncryptedMessage(encryptedMessage);
                    System.out.println("Meddelande krypterat och lagrat med nyckel: " + messageKey);
                    break;
                case "3":
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
                case "4":
                    running = false;
                    System.out.println("Avslutar...");
                    break;
                default:
                    System.out.println("Ogiltigt val, försök igen.");
            }
        }
    }

    /**
     * Huvudmetod för att köra programmet.
     *
     * @param args Argument för kommandoraden (används inte).
     */
    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        ui.start();
    }
}
