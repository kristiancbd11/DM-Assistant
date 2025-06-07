package fbhandler;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;

import clases_personaje.Personaje;

import java.util.concurrent.ExecutionException;

public class FirestoreService {

    public void guardarPersonaje(String userId, Personaje personaje) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        db.collection("usuarios")
                .document(userId)
                .collection("personajes")
                .add(personaje)
                .get(); // Espera a que se complete
        System.out.println("Personaje guardado correctamente");
    }
}
