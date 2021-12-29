package com.example.gestion_des_notes_;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listeView;
    private EditText Note;
    static double somme;
    static ArrayList<String> ModuleExiste;
    private Button button;
    private TextView moyenne;
    private ArrayList<String> itemsN;
    ArrayAdapter<String> adap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Spinner dropdown = findViewById(R.id.spinner1);
        String[] items = new String[]{"Big Data", "Statistique", "Cloud Computing","Machine Learning"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        //initialisation des variables
        listeView=findViewById(R.id.litViewItems);
        Note=findViewById(R.id.Note);
        button=findViewById(R.id.Ajouter);
        moyenne=findViewById(R.id.moyenne);
        ModuleExiste = new ArrayList<>();
        itemsN = new ArrayList<>();
        adap=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1,itemsN);
        listeView.setAdapter(adap);

        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                String n =Note.getText().toString();
                String module=dropdown.getSelectedItem().toString();
                //On teste si la case de note est vide
                if(n==null || n.length()==0){
                    makeToast("il faut remplire toutes la case de note");
                }else{
                    //On test si le module a ete deja existe deja dans listView
                    if (existe(ModuleExiste,module)){
                        makeToast("le module "+module+" existe déjà");
                    }else{
                        //Ajouter le module et la note a la litView
                        addItem(n,module);
                        //converter n en double
                        double no = Double.parseDouble(n);
                        //calcul de la somme des notes
                        somme= somme + no;
                        //clacul de la moyenne general
                        double moy=somme/(itemsN.size());
                        moyenne.setText("Le moyenne : "+moy);
                        Note.setText("");
                    }


                }

            }
        });
    }

    Toast t;
    public void makeToast(String s){
        if(t!=null) t.cancel();
        t=Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT);
        t.show();
    }

    public void addItem( String item,String module){
        int n=itemsN.size()+1;
        ModuleExiste.add(module);
        itemsN.add(n+"          "+item+"         "+module);
        listeView.setAdapter(adap);
    }

    public boolean existe(ArrayList<String> ModuleExiste, String val){
        boolean ex=false;
        for(int i = 0 ; i<ModuleExiste.size();i++){

            if(val.equals(ModuleExiste.get(i))){
                ex=true;
            }
        }
        return ex;
    }
}