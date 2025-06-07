package fbhandler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;

public class TokenVerifier {
    public static FirebaseToken verifyIdToken(String idToken) throws Exception {
        return FirebaseAuth.getInstance().verifyIdToken(idToken);
    }
}

//project-77731737327