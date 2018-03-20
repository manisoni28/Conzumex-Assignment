package com.mani.conzumex.assignment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.ProviderQueryResult;
import com.google.firebase.database.DataSnapshot;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;

import rx.observers.TestSubscriber;


import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@RunWith(MockitoJUnitRunner.class)
public class FirebaseAuthTests {

    @Mock
    private FirebaseAuth mockAuth;

    @Mock
    private Task<AuthResult> mockAuthTask;

    @Mock
    private Task<ProviderQueryResult> mockProviderQueryResultTask;

    @Mock
    private Task<Void> mockVoidTask;

    @Mock
    private AuthResult mockAuthResult;

    @Mock
    private ProviderQueryResult mockProviderQueryResult;

    @Mock
    private DataSnapshot mockDatabaseDataSnapshot;

    @Mock
    private AuthCredential mockCredentials;

    @Mock
    private FirebaseUser mockUser;

    @Captor
    private ArgumentCaptor<OnCompleteListener> testOnCompleteListener;

    @Captor
    private ArgumentCaptor<OnSuccessListener> testOnSuccessListener;

    @Captor
    private ArgumentCaptor<OnFailureListener> testOnFailureListener;

    private Void mockRes = null;

    @Before
    public void setup() {
        setupTask(mockAuthTask);
        setupTask(mockProviderQueryResultTask);
        setupTask(mockVoidTask);

        when(mockAuth.signInAnonymously()).thenReturn(mockAuthTask);
        when(mockAuth.signInWithEmailAndPassword("email", "password")).thenReturn(mockAuthTask);
        when(mockAuth.signInWithCredential(mockCredentials)).thenReturn(mockAuthTask);
        when(mockAuth.signInWithCustomToken("token")).thenReturn(mockAuthTask);
        when(mockAuth.createUserWithEmailAndPassword("email", "password")).thenReturn(mockAuthTask);
        when(mockAuth.fetchProvidersForEmail("email")).thenReturn(mockProviderQueryResultTask);
        when(mockAuth.sendPasswordResetEmail("email")).thenReturn(mockVoidTask);

        when(mockAuth.getCurrentUser()).thenReturn(mockUser);
    }

    private <T> void setupTask(Task<T> task) {
        when(task.addOnCompleteListener(testOnCompleteListener.capture())).thenReturn(task);
        when(task.addOnSuccessListener(testOnSuccessListener.capture())).thenReturn(task);
        when(task.addOnFailureListener(testOnFailureListener.capture())).thenReturn(task);
    }

    @Test
    public void signInAnonymously() throws InterruptedException {

        TestSubscriber<AuthResult> testSubscriber = new TestSubscriber<>();

        testOnSuccessListener.getValue().onSuccess(mockAuthResult);
        testOnCompleteListener.getValue().onComplete(mockAuthTask);

        verify(mockAuth).signInAnonymously();

        testSubscriber.assertNoErrors();
        testSubscriber.assertValueCount(1);
        testSubscriber.assertReceivedOnNext(Collections.singletonList(mockAuthResult));
        testSubscriber.assertCompleted();
        testSubscriber.unsubscribe();
    }

    @Test
    public void signInAnonymously_Failed() throws InterruptedException {

        TestSubscriber<AuthResult> testSubscriber = new TestSubscriber<>();


        Exception e = new Exception("something bad happened");
        testOnFailureListener.getValue().onFailure(e);

        verify(mockAuth).signInAnonymously();

        testSubscriber.assertError(e);
        testSubscriber.assertNotCompleted();
        testSubscriber.unsubscribe();
    }

    @Test
    public void signInWithEmailAndPassword() throws InterruptedException {

        TestSubscriber<AuthResult> testSubscriber = new TestSubscriber<>();


        testOnSuccessListener.getValue().onSuccess(mockAuthResult);
        testOnCompleteListener.getValue().onComplete(mockAuthTask);

        verify(mockAuth).signInWithEmailAndPassword(eq("email"), eq("password"));

        testSubscriber.assertNoErrors();
        testSubscriber.assertValueCount(1);
        testSubscriber.assertReceivedOnNext(Collections.singletonList(mockAuthResult));
        testSubscriber.assertCompleted();
        testSubscriber.unsubscribe();
    }

    @Test
    public void signInWithEmailAndPassword_AuthError() throws InterruptedException {

        TestSubscriber<AuthResult> testSubscriber = new TestSubscriber<>();


        Exception e = new Exception("something bad happened");
        testOnFailureListener.getValue().onFailure(e);

        verify(mockAuth).signInWithEmailAndPassword(eq("email"), eq("password"));

        testSubscriber.assertError(e);
        testSubscriber.assertNotCompleted();
        testSubscriber.unsubscribe();
    }

    @Test
    public void signInWithCredential() throws InterruptedException {

        TestSubscriber<AuthResult> testSubscriber = new TestSubscriber<>();


        testOnSuccessListener.getValue().onSuccess(mockAuthResult);
        testOnCompleteListener.getValue().onComplete(mockAuthTask);

        verify(mockAuth).signInWithCredential(mockCredentials);

        testSubscriber.assertNoErrors();
        testSubscriber.assertValueCount(1);
        testSubscriber.assertReceivedOnNext(Collections.singletonList(mockAuthResult));
        testSubscriber.assertCompleted();
        testSubscriber.unsubscribe();
    }

    @Test
    public void signInWithCustomToken() throws InterruptedException {

        TestSubscriber<AuthResult> testSubscriber = new TestSubscriber<>();


        testOnSuccessListener.getValue().onSuccess(mockAuthResult);
        testOnCompleteListener.getValue().onComplete(mockAuthTask);

        verify(mockAuth).signInWithCustomToken(eq("token"));

        testSubscriber.assertNoErrors();
        testSubscriber.assertValueCount(1);
        testSubscriber.assertReceivedOnNext(Collections.singletonList(mockAuthResult));
        testSubscriber.assertCompleted();
        testSubscriber.unsubscribe();
    }

    @Test
    public void createUserWithEmailAndPassword() throws InterruptedException {

        TestSubscriber<AuthResult> testSubscriber = new TestSubscriber<>();


        testOnSuccessListener.getValue().onSuccess(mockAuthResult);
        testOnCompleteListener.getValue().onComplete(mockAuthTask);

        verify(mockAuth).createUserWithEmailAndPassword(eq("email"), eq("password"));

        testSubscriber.assertNoErrors();
        testSubscriber.assertValueCount(1);
        testSubscriber.assertReceivedOnNext(Collections.singletonList(mockAuthResult));
        testSubscriber.assertCompleted();
        testSubscriber.unsubscribe();
    }

    @Test
    public void fetchProvidersForEmail() throws InterruptedException {

        TestSubscriber<ProviderQueryResult> testSubscriber = new TestSubscriber<>();


        testOnSuccessListener.getValue().onSuccess(mockProviderQueryResult);
        testOnCompleteListener.getValue().onComplete(mockProviderQueryResultTask);

        verify(mockAuth).fetchProvidersForEmail(eq("email"));

        testSubscriber.assertNoErrors();
        testSubscriber.assertValueCount(1);
        testSubscriber.assertReceivedOnNext(Collections.singletonList(mockProviderQueryResult));
        testSubscriber.assertCompleted();
        testSubscriber.unsubscribe();
    }


    @Test
    public void testObserveAuthState() throws InterruptedException {

        TestSubscriber<FirebaseUser> testSubscriber = new TestSubscriber<>();


        ArgumentCaptor<FirebaseAuth.AuthStateListener> argument = ArgumentCaptor.forClass(FirebaseAuth.AuthStateListener.class);
        verify(mockAuth).addAuthStateListener(argument.capture());
        argument.getValue().onAuthStateChanged(mockAuth);

        testSubscriber.assertNoErrors();
        testSubscriber.assertValueCount(1);
        testSubscriber.assertReceivedOnNext(Collections.singletonList(mockUser));
        testSubscriber.assertNotCompleted();
        testSubscriber.unsubscribe();
    }
}