// login firebase with play services
//1-- add to gradle
implementation 'com.google.firebase:firebase-auth:21.0.1' // Replace with latest version
implementation 'com.google.android.gms:play-services-auth:19.0.0' // Replace with latest version


    //1- extend 
       GoogleApiClient.OnConnectionFailedListener
       //2 - define in public
 

    private static final String TAG = "GoogleActivity";
    private static final int RC_SIGN_IN = 9001;

    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    // [START declare_auth_listener]
    private FirebaseAuth.AuthStateListener mAuthListener;
    // [END declare_auth_listener]

    private GoogleApiClient mGoogleApiClient;
 

 //3- onCreate add

    
        // [ST// Updated Google Sign-In Setup with GoogleSignInClient
private static final String TAG = "GoogleActivity";
private static final int RC_SIGN_IN = 9001;

private FirebaseAuth mAuth;
private FirebaseAuth.AuthStateListener mAuthListener;

private GoogleSignInClient mGoogleSignInClient;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // Configure Google Sign In
    GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build();

    mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

    // Initialize Firebase Auth
    mAuth = FirebaseAuth.getInstance();

    // Auth state listener
    mAuthListener = firebaseAuth -> {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
            Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
        } else {
            Log.d(TAG, "onAuthStateChanged:signed_out");
        }
        updateUI(user);
    };
}

// Sign in with Google
private void signIn() {
    Intent signInIntent = mGoogleSignInClient.getSignInIntent();
    startActivityForResult(signInIntent, RC_SIGN_IN);
}

@Override
public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    // Handle Google Sign-In
    if (requestCode == RC_SIGN_IN) {
        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);
            firebaseAuthWithGoogle(account);
        } catch (ApiException e) {
            Log.w(TAG, "Google sign-in failed", e);
            updateUI(null);
        }
    }
}

private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
    Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

    showProgressDialog();

    AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
    mAuth.signInWithCredential(credential)
            .addOnCompleteListener(this, task -> {
                if (task.isSuccessful()) {
                    Log.d(TAG, "signInWithCredential:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    updateUI(user);
                } else {
                    Log.w(TAG, "signInWithCredential:failure", task.getException());
                    Toast.makeText(MainActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                    updateUI(null);
                }
                hideProgressDialog();
            });
}

private void signOut() {
    // Firebase sign out
    mAuth.signOut();

    // Google sign out
    mGoogleSignInClient.signOut().addOnCompleteListener(this, task -> updateUI(null));
}

private void updateUI(FirebaseUser user) {
    hideProgressDialog();
    if (user != null) {
        String email = user.getEmail();
        String uid = user.getUid();
        Log.d(TAG, "User Email: " + email + " UID: " + uid);
    }
}

@Override
public void onStart() {
    super.onStart();
    mAuth.addAuthStateListener(mAuthListener);
}

@Override
public void onStop() {
    super.onStop();
    if (mAuthListener != null) {
        mAuth.removeAuthStateListener(mAuthListener);
    }
    hideProgressDialog();
}

// Progress dialog methods
private ProgressDialog mProgressDialog;

public void showProgressDialog() {
    if (mProgressDialog == null) {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.setIndeterminate(true);
    }
    mProgressDialog.show();
}

public void hideProgressDialog() {
    if (mProgressDialog != null && mProgressDialog.isShowing()) {
        mProgressDialog.dismiss();
    }
}
