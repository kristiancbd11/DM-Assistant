package fbhandler;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import java.io.FileInputStream;
import java.io.IOException;

public class FirebaseInitializer {

    public static void initialize() {
        try {
            FileInputStream serviceAccount = new FileInputStream("ruta/a/tu/archivo.json");

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://dm-assistant-c8050.firebaseio.com") // Usa tu URL real de la base de datos si usas Realtime DB
                    .build();

            FirebaseApp.initializeApp(options);

            System.out.println("Firebase ha sido inicializado correctamente.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

