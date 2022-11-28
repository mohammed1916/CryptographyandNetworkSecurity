import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.SignedObject;

public class ObjectSigningExample {
    public static void main(String[] args) {
        try {
            // Generate a 1024-bit Digital Signature Algorithm (DSA) key pair
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA");
            keyPairGenerator.initialize(1024);
            KeyPair keyPair = keyPairGenerator.genKeyPair();
            PrivateKey privateKey = keyPair.getPrivate();
            PublicKey publicKey = keyPair.getPublic();

            // We can sign Serializable objects only
            String unsignedString = new String("A Test Object");

            SignedObject signedObject = new SignedObject(unsignedString, privateKey,
                    Signature.getInstance(privateKey.getAlgorithm()));

            // Verify the signed object
            boolean verified = signedObject.verify(publicKey, Signature.getInstance(publicKey.getAlgorithm()));
            System.out.println("Is signed Object verified ? " + verified);

            // Retrieve the object
            unsignedString = (String) signedObject.getObject();
            System.out.println("Unsigned Object : " + unsignedString);
        } catch (SignatureException e) {
        } catch (InvalidKeyException e) {
        } catch (NoSuchAlgorithmException e) {
        } catch (ClassNotFoundException e) {
        } catch (IOException e) {
        }
    }
}