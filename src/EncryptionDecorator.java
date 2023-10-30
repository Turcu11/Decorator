import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

public class EncryptionDecorator implements DataSource {
    private DataSource dataSource;

    public EncryptionDecorator(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public String read(String filePath) {
        String encryptedContent = dataSource.read(filePath); // Read the content
        return encryptedContent;
    }

    @Override
    public void write(String filePath, String content) {
        String encryptedContent = encrypt(content, "");
        dataSource.write(filePath, encryptedContent); // Write the encrypted content
    }

    public String encrypt(String content, String original) {
        try {
            // Generate a secret key
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128); // You can choose 128, 192, or 256 bits
            SecretKey secretKey = keyGenerator.generateKey();

            // Encrypt a string
            String originalString = original;
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedBytes = cipher.doFinal(originalString.getBytes());

            // Decrypt the encrypted string
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            String decryptedString = new String(decryptedBytes);

            System.out.println("Original: " + originalString);
            System.out.println("Encrypted: " + new String(encryptedBytes));
            System.out.println("Decrypted: " + decryptedString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Encrypted: " + content;
    }

    private String decrypt(String content) {
        return content.replace("Encrypted: ", "");
    }
}
