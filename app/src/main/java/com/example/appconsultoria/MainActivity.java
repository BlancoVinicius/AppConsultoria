package com.example.appconsultoria;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appconsultoria.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar); //Adiciona um actionBar
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_principal, R.id.nav_clientes, R.id.nav_contato,
                R.id.nav_servicos, R.id.nav_sobre)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    //Meus Metodos personalizados
    public void sendEmail(){
        //para chamar app de ligação
        //Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:1195555826"));

        //para abri um site
//        String urlImagem = "https://img.freepik.com/fotos-gratis/praia-tropical_74190-188.jpg?w=1800&t=st=1706377331~exp=1706377931~hmac=4292983e0c4a666011fd55cd79d2c7b92d182a4b83336f462bc4de1a8445322a";
//        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlImagem));

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] {"Email1@hotmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Enviar Email");
        intent.putExtra(Intent.EXTRA_TEXT, "MEnsagem automatica");

        //para definir outros tipos use mini-types-complete-list
        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Compartilhar"));
    }

}