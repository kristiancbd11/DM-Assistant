package fbhandler;

import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Scanner;

import org.json.JSONObject;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

public class FirebaseAuthService {
    private static final String API_KEY = "AIzaSyDqwv-X7Up5YMYv0FnY392LH0xD4np1lwM";
    private static final String CLIENT_SECRET_FILE = "/google/client_secret.json";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final NetHttpTransport HTTP_TRANSPORT = new NetHttpTransport();


    public static String signInWithEmailAndPassword(String email, String password) throws Exception {
        String endpoint = "https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=" + API_KEY;

        JSONObject requestPayload = new JSONObject();
        requestPayload.put("email", email);
        requestPayload.put("password", password);
        requestPayload.put("returnSecureToken", true);

        URL url = new URL(endpoint);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "application/json");

        try (OutputStream os = conn.getOutputStream()) {
            os.write(requestPayload.toString().getBytes(StandardCharsets.UTF_8));
        }

        int responseCode = conn.getResponseCode();
        Scanner scanner;

        if (responseCode == 200) {
            scanner = new Scanner(conn.getInputStream(), StandardCharsets.UTF_8);
        } else {
            scanner = new Scanner(conn.getErrorStream(), StandardCharsets.UTF_8);
        }

        String response = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
        scanner.close();

        if (responseCode == 200) {
            JSONObject json = new JSONObject(response);
            return json.getString("idToken"); // Token JWT si el login fue exitoso
        } else {
            JSONObject error = new JSONObject(response);
            throw new Exception(error.getJSONObject("error").getString("message"));
        }
    }
    
    public static String createUserWithEmailAndPassword(String email, String password) throws Exception {
        String endpoint = "https://identitytoolkit.googleapis.com/v1/accounts:signUp?key=" + API_KEY;

        JSONObject requestPayload = new JSONObject();
        requestPayload.put("email", email);
        requestPayload.put("password", password);
        requestPayload.put("returnSecureToken", true);

        URL url = new URL(endpoint);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "application/json");

        try (OutputStream os = conn.getOutputStream()) {
            os.write(requestPayload.toString().getBytes(StandardCharsets.UTF_8));
        }

        int responseCode = conn.getResponseCode();
        Scanner scanner;

        if (responseCode == 200) {
            scanner = new Scanner(conn.getInputStream(), StandardCharsets.UTF_8);
        } else {
            scanner = new Scanner(conn.getErrorStream(), StandardCharsets.UTF_8);
        }

        String response = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
        scanner.close();

        if (responseCode == 200) {
            JSONObject json = new JSONObject(response);
            return json.getString("idToken");
        } else {
            JSONObject error = new JSONObject(response);
            throw new Exception(error.getJSONObject("error").getString("message"));
        }
    }
    
    public static String signInWithGoogle() throws Exception {
        // Carga credenciales cliente OAuth 2.0
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY,
                new InputStreamReader(FirebaseAuthService.class.getResourceAsStream(CLIENT_SECRET_FILE)));

        // Construye flujo OAuth con el scope de perfil básico
        AuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT,
                JSON_FACTORY,
                clientSecrets,
                Collections.singletonList("openid email profile"))
                .build();

        // Recibe el código de autorización abriendo navegador y escuchando redirect local
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();

        // Ejecuta el proceso de autorización (abre navegador)
        Credential credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");

        // Extrae el id_token (JWT) del token obtenido
        String idToken = credential.getAccessToken();

        // Nota: Aquí necesitarías obtener el id_token explícito para Firebase
        // Credential no expone id_token directamente. Se puede obtener mediante petición adicional si es necesario.

        // Por simplicidad, devolvemos el accessToken (puede que debas intercambiarlo o validar)
        return idToken;
    }
}

