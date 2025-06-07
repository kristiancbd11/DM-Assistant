package fbhandler;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class AuthService {

    private static final String API_KEY = "TU_API_KEY"; // Encuéntrala en Firebase Console

    public static String loginWithEmail(String email, String password) throws Exception {
        URL url = new URL("https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=" + API_KEY);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "application/json");

        String requestBody = String.format("{\"email\":\"%s\",\"password\":\"%s\",\"returnSecureToken\":true}", email, password);

        try (OutputStream os = conn.getOutputStream()) {
            os.write(requestBody.getBytes());
        }

        Scanner scanner = new Scanner(conn.getInputStream());
        String response = scanner.useDelimiter("\\A").next();
        scanner.close();

        return response; // Contiene idToken, refreshToken, etc.
    }
}

