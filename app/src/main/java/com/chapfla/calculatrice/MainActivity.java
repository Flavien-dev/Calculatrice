package com.chapfla.calculatrice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Crée tous les objets de l'application
 */
public class MainActivity extends AppCompatActivity {

    private TextView TV_result;
    private TextView TV_calcul;
    private Button BT_num_1;
    private Button BT_num_2;
    private Button BT_num_3;
    private Button BT_num_4;
    private Button BT_num_5;
    private Button BT_num_6;
    private Button BT_num_7;
    private Button BT_num_8;
    private Button BT_num_9;
    private Button BT_num_0;
    private Button BT_opera_plus;
    private Button BT_opera_moins;
    private Button BT_opera_fois;
    private Button BT_opera_divise;
    private Button BT_opera_modulo;
    private Button BT_reset;
    private Button BT_comma;
    private Button BT_calculer;

    // initialise des variables globales à ""
    Double buffer = 0.0;
    Double résultatFinal = 0.0;
    String opérateur = "";
    boolean virguleOK = false;

    /**
     * Initialise tous les objets de l'application
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TV_result = findViewById(R.id.result);
        TV_calcul = findViewById(R.id.calcul);
        BT_num_0 = findViewById(R.id.numero0);
        BT_num_1 = findViewById(R.id.numero1);
        BT_num_2 = findViewById(R.id.numero2);
        BT_num_3 = findViewById(R.id.numero3);
        BT_num_4 = findViewById(R.id.numero4);
        BT_num_5 = findViewById(R.id.numero5);
        BT_num_6 = findViewById(R.id.numero6);
        BT_num_7 = findViewById(R.id.numero7);
        BT_num_8 = findViewById(R.id.numero8);
        BT_num_9 = findViewById(R.id.numero9);
        BT_opera_plus = findViewById(R.id.operation_plus);
        BT_opera_moins = findViewById(R.id.operation_moins);
        BT_opera_fois = findViewById(R.id.operation_fois);
        BT_opera_divise = findViewById(R.id.operation_divise);
        BT_opera_modulo = findViewById(R.id.operation_modulo);
        BT_reset = findViewById(R.id.reset);
        BT_comma = findViewById(R.id.comma);
        BT_calculer = findViewById(R.id.calculer);

        BT_calculer.setEnabled(true);
    }

    /**
     * Exécute le programme au lancement de l'application
     */
    @Override
    protected void onStart() {
        super.onStart();

        // initialise la valeur qu'ajouteront chaques boutons dans la TextView
        BT_num_0.setOnClickListener(new View.OnClickListener() { public void onClick(View v) { TV_calcul.append("0"); virguleOK = false; } });
        BT_num_1.setOnClickListener(new View.OnClickListener() { public void onClick(View v) { TV_calcul.append("1"); virguleOK = false; } });
        BT_num_2.setOnClickListener(new View.OnClickListener() { public void onClick(View v) { TV_calcul.append("2"); virguleOK = false; } });
        BT_num_3.setOnClickListener(new View.OnClickListener() { public void onClick(View v) { TV_calcul.append("3"); virguleOK = false; } });
        BT_num_4.setOnClickListener(new View.OnClickListener() { public void onClick(View v) { TV_calcul.append("4"); virguleOK = false; } });
        BT_num_5.setOnClickListener(new View.OnClickListener() { public void onClick(View v) { TV_calcul.append("5"); virguleOK = false; } });
        BT_num_6.setOnClickListener(new View.OnClickListener() { public void onClick(View v) { TV_calcul.append("6"); virguleOK = false; } });
        BT_num_7.setOnClickListener(new View.OnClickListener() { public void onClick(View v) { TV_calcul.append("7"); virguleOK = false; } });
        BT_num_8.setOnClickListener(new View.OnClickListener() { public void onClick(View v) { TV_calcul.append("8"); virguleOK = false; } });
        BT_num_9.setOnClickListener(new View.OnClickListener() { public void onClick(View v) { TV_calcul.append("9"); virguleOK = false; } });

        /**
         * Exécute le code du bouton +
         */
        BT_opera_plus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (résultatFinal != 0.0) {
                    opérateur = "+";
                }

                try {
                    // initialise le buffer
                    buffer = Double.parseDouble(TV_calcul.getText().toString());

                    Log.wtf("buffer ", Double.toString(buffer));
                    Log.wtf("résultat ", Double.toString(résultatFinal));

                    if (résultatFinal == 0.0) {
                        TV_result.setText("");
                        résultatFinal = buffer;
                    } else {
                        // met l'opérateur - dans la variable opérateur
                        opérateur = "+";
                        résultatFinal = calculer(résultatFinal, buffer, opérateur);
                    }

                    // affiche la valeur du buffer dans la TextView TV_result
                    TV_result.append(Double.toString(buffer));

                    // affiche l'opérateur dans la TextView TV_result
                    TV_result.append("+");

                    // vide la TextView TV_calcul
                    TV_calcul.setText("");

                    // dégrise le bouton =
                    BT_calculer.setEnabled(true);
                } catch (Exception e) {
                    // indique si il y a eu un problème
                    Log.i("ça ne fonctionne pas?","non");
                }
                // dégrise les boutons
                BT_comma.setEnabled(true);
                BT_num_0.setEnabled(true);
            }
        });

        /**
         * Exécute le code du bouton -
         */
        BT_opera_moins.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (résultatFinal != 0.0) {
                    opérateur = "-";
                }

                try {
                    // initialise le buffer
                    buffer = Double.parseDouble(TV_calcul.getText().toString());

                    Log.wtf("buffer - " , Double.toString(buffer));

                    if (résultatFinal == 0.0) {
                        TV_result.setText("");
                        résultatFinal = buffer;
                    } else {
                        résultatFinal = calculer(résultatFinal, buffer, opérateur);
                        opérateur = "-";
                    }

                    TV_result.append(Double.toString(buffer));

                    // affiche l'opérateur dans la TextView TV_result
                    TV_result.append("-");

                    // vide la TextView TV_calcul
                    TV_calcul.setText("");

                    // dégrise le bouton
                    BT_calculer.setEnabled(true);
                } catch (Exception e) {
                    // indique si il y a eu un problème
                    Log.i("le moins ne fonctionne pas?","non");
                }
                // dégrise les boutons
                BT_comma.setEnabled(true);
                BT_num_0.setEnabled(true);
            }
        });

        /**
         * Exécute le code du bouton *
         */
        BT_opera_fois.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                try {

                    // initialise le buffer
                    buffer = Double.parseDouble(TV_calcul.getText().toString());

                    // vérifie si il n'y a pas de résultat
                    if (résultatFinal == 0.0) {
                        // initialise la variable résultatFinal
                        résultatFinal = buffer;
                    } else {
                        // effectue le calcul des 2 variables selon l'opérateur
                        résultatFinal = calculer(résultatFinal,buffer,opérateur);
                    }

                    // met l'opérateur * dans la variable opérateur
                    opérateur = "*";

                    // affiche la valeur du buffer dans la TextView TV_result
                    TV_result.append(Double.toString(buffer));

                    // affiche l'opérateur dans la TextView TV_result
                    TV_result.append("*");

                    // vide la TextView TV_calcul
                    TV_calcul.setText("");

                    // dégrise le bouton
                    BT_calculer.setEnabled(true);
                } catch (Exception e) {
                    // indique si il y a eu un problème
                    Log.i("ça ne fonctionne pas?","non");
                }
                // dégrise les boutons
                BT_comma.setEnabled(true);
                BT_num_0.setEnabled(true);
            }
        });

        /**
         * Exécute le code du bouton /
         */
        BT_opera_divise.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                try {
                    // initialise le buffer
                    buffer = Double.parseDouble(TV_calcul.getText().toString());

                    // vérifie si il n'y a pas de résultat
                    if (résultatFinal == 0.0) {
                        // initialise la variable résultatFinal
                        résultatFinal = buffer;
                    } else {
                        // effectue le calcul des 2 variables selon l'opérateur
                        résultatFinal = calculer(résultatFinal,buffer,opérateur);
                    }

                    // met l'opérateur / dans la variable opérateur
                    opérateur = "/";

                    // affiche la valeur du buffer dans la TextView TV_result
                    TV_result.append(Double.toString(buffer));

                    // affiche l'opérateur dans la TextView TV_result
                    TV_result.append("/");

                    // vide la TextView TV_calcul
                    TV_calcul.setText("");

                    // dégrise le bouton
                    BT_calculer.setEnabled(true);
                } catch (Exception e) {
                    // vérifie si il y a une erreur
                    Log.i("ça ne fonctionne pas?","non");
                }
                // dégrise les boutons
                BT_comma.setEnabled(true);
                BT_num_0.setEnabled(false);
            }
        });

        /**
         * Exécute le code du bouton %
         */
        BT_opera_modulo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                try {
                    // initialise le buffer
                    buffer = Double.parseDouble(TV_calcul.getText().toString());

                    // vérifie si il n'y a pas de résultat
                    if (résultatFinal == 0.0) {
                        // initialise la variable résultatFinal
                        résultatFinal = buffer;
                    } else {
                        // effectue le calcul des 2 variables selon l'opérateur
                        résultatFinal = calculer(résultatFinal,buffer,opérateur);
                    }

                    // met l'opérateur % dans la variable opérateur
                    opérateur = "%";

                    // affiche la valeur du buffer dans la TextView TV_result
                    TV_result.append(Double.toString(buffer));

                    // affiche l'opérateur dans la TextView TV_result
                    TV_result.append("%");

                    // vide la TextView TV_calcul
                    TV_calcul.setText("");

                    // dégrise le bouton
                    BT_calculer.setEnabled(true);
                } catch (Exception e) {
                    Log.i("ça ne fonctionne pas?","non");
                }
                // dégrise les boutons
                BT_comma.setEnabled(true);
                BT_num_0.setEnabled(true);
            }
        });

        BT_comma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (virguleOK == false) {
                    TV_calcul.append(".");
                    BT_comma.setEnabled(false);
                } else {
                    Log.wtf("virgule présente", String.valueOf(virguleOK));
                }
            }
        });

        /**
         * Vide tous les champs de la fenêtre
         */
        BT_reset.setOnClickListener(new View.OnClickListener() {
            /**
             * vide tous les champs
             * @param v vue de la fonction
             */
            @Override
            public void onClick(View v) { resetFields(); } });

        /**
         * Exécute le code du bouton Calculer
         */
        BT_calculer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // initialise le buffer
                    buffer = Math.abs(Double.parseDouble(TV_calcul.getText().toString()));

                    Log.wtf("buffer = ", Double.toString(buffer));
                    Log.wtf("operateur = ", opérateur);
                    Log.wtf("résultat = ", Double.toString(résultatFinal));

                    if (résultatFinal == 0.0) {
                        résultatFinal = buffer;
                    } else {
                        // effectue le calcul des 2 variables selon l'opérateur
                        résultatFinal = calculer(résultatFinal,buffer,opérateur);
                    }

                    // change le contenu de la TextView TV_result
                    TV_result.setText(Double.toString(résultatFinal));

                    Log.wtf("résultat = ", Double.toString(résultatFinal));

                    // sauvegarde le résultat final
                    buffer = résultatFinal;

                    // vide la TextView TV_calcul
                    TV_calcul.setText("");

                    Log.wtf("buffer du résultat ", Double.toString(buffer));

                    // dégriser les boutons
                    BT_calculer.setEnabled(true);
                    BT_num_0.setEnabled(true);

                } catch (Exception e) {
                    // vérifie si il y a un problème
                    Log.wtf("Est-ce que ça fonctionne?","non");
                }

            }
        });
    }

    private Double calculer(Double nombre1,Double nombre2,String operateur) {
        Double calcul = 0.0;

        // éffectue le calcul selon l'opération
        switch (operateur) {
            case "+": calcul = nombre1 + nombre2;break;
            case "-": calcul = nombre1 - nombre2;break;
            case "*": calcul = nombre1 * nombre2;break;
            case "/": calcul = nombre1 / nombre2;break;
            case "%": calcul = nombre1 % nombre2;break;
            default: calcul = nombre1;break;
        }
        return calcul;
    }

    /**
     * Remet à neuf l'application
     */
    private void resetFields(){
        // supprime les TextView
        TV_calcul.setText("");
        TV_result.setText("");

        // remet le focus sur la TextView du calcul
        TV_calcul.requestFocus();

        // grise le bouton Calculer
        BT_calculer.setEnabled(false);

        // vide les variables du calcul
        buffer = 0.0;
        résultatFinal = 0.0;
        opérateur = "";
    }
}