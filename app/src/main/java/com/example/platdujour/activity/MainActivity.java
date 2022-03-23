package com.example.platdujour.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.platdujour.Model.Usuario;
import com.example.platdujour.R;
import com.example.platdujour.config.ConfiguracaoFirebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private TextInputEditText campoEmail, campoSenha;
    private FirebaseAuth autenticacao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        campoEmail = findViewById(R.id.editLoginEmail);
        campoSenha = findViewById(R.id.editLoginSenha);
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_NETWORK_STATE}, PackageManager.PERMISSION_GRANTED);
    }
    public void logarUsuario(Usuario usuario) {
        autenticacao.signInWithEmailAndPassword(
                usuario.getEmail(), usuario.getSenha()
        ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    abrirTelaPrincipal();
                } else {
                    Toast.makeText(MainActivity.this, "Erro ao autenticar Usuario!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
            public void validarAutenticacaoUsuario(View view) {
                String textoEmail = campoEmail.getText().toString();
                String textoSenha = campoSenha.getText().toString();

                if (!textoSenha.isEmpty()) {
                    if (!textoEmail.isEmpty()) {
                        Usuario usuario = new Usuario();
                        usuario.setEmail(textoEmail);
                        usuario.setSenha(textoSenha);
                        logarUsuario(usuario);
                    } else {
                        Toast.makeText(MainActivity.this, "Preencha o email!", Toast.LENGTH_SHORT).show();

                    }
                } else {

                    Toast.makeText(MainActivity.this, "Preencha a senha!", Toast.LENGTH_SHORT).show();

                }

            }

            public void cad(View view) {
                Intent intent = new Intent(MainActivity.this, CadastroActivity.class);
                startActivity(intent);
            }
        public void abrirTelaPrincipal() {
            Intent intent = new Intent(MainActivity.this, telaPrincipal.class);
            startActivity(intent);
    }
}
