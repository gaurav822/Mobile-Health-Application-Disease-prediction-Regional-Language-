package DiseasePrediction;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.patientapp.R;
import com.google.android.material.button.MaterialButton;

import org.tensorflow.lite.Interpreter;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Random;


public class SymptomsInput extends Fragment {


    MaterialButton dis;
    Spinner s1,s2,s3,s4,s5;

    TextView preddiseaseTV;

    String d[] = new String[5];

    Interpreter interpreter;

    String res1="";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_symptoms_input, container, false);
        dis= (MaterialButton) view.findViewById(R.id.disease);
        s1 = view.findViewById(R.id.syp1);
        s2 = view.findViewById(R.id.syp2);
        s3 = view.findViewById(R.id.syp3);
        s4 = view.findViewById(R.id.syp4);
        s5 = view.findViewById(R.id.syp5);
        preddiseaseTV=view.findViewById(R.id.preddiseaseTV);

        try {
            interpreter= new Interpreter(loadModelFile(),null);
        } catch (IOException e) {
            Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        final int symptomsArr[] = new int[132];

        final String disease[]={
                "Fungal infection",
                "Allergy",
                "GERD",
                "Chronic cholestasis",
                "Drug Reaction",
                "Peptic ulcer diseae",
                "AIDS",
                "Diabetes",
                "Gastroenteritis",
                "Bronchial Asthma",
                "Hypertension",
                " Migraine",
                "Cervical spondylosis",
                "Paralysis (brain hemorrhage)",
                "Jaundice",
                "Malaria",
                "Chicken pox",
                "Dengue",
                "Typhoid",
                "hepatitis A",
                "Hepatitis B",
                "Hepatitis C",
                "Hepatitis D",
                "Hepatitis E",
                "Alcoholic hepatitis",
                "Tuberculosis",
                "Common Cold",
                "Pneumonia",
                "Dimorphic hemmorhoids(piles)",
                "Heartattack",
                "Varicoseveins",
                "Hypothyroidism",
                "Hyperthyroidism",
                "Hypoglycemia",
                "Osteoarthristis",
                "Arthritis",
                "(vertigo) Paroymsal  Positional Vertigo",
                "Acne",
                "Urinary tract infection",
                "Psoriasis",
                "Impetigo"
        };

        final String myarr[]= {
                "itching",
                "skin_rash",
                "nodal_skin_eruptions",
                "continuous_sneezing",
                "shivering",
                "chills",
                "joint_pain",
                "stomach_pain",
                "acidity",
                "ulcers_on_tongue",
                "muscle_wasting",
                "vomiting",
                "burning_micturition",
                "spotting_ urination",
                "fatigue",
                "weight_gain",
                "anxiety",
                "cold_hands_and_feets",
                "mood_swings",
                "weight_loss",
                "restlessness",
                "lethargy",
                "patches_in_throat",
                "irregular_sugar_level",
                "cough",
                "high_fever",
                "sunken_eyes",
                "breathlessness",
                "sweating",
                "dehydration",
                "indigestion",
                "headache",
                "yellowish_skin",
                "dark_urine",
                "nausea",
                "loss_of_appetite",
                "pain_behind_the_eyes",
                "back_pain",
                "constipation",
                "abdominal_pain",
                "diarrhoea",
                "mild_fever",
                "yellow_urine",
                "yellowing_of_eyes",
                "acute_liver_failure",
                "fluid_overload",
                "swelling_of_stomach",
                "swelled_lymph_nodes",
                "malaise",
                "blurred_and_distorted_vision",
                "phlegm",
                "throat_irritation",
                "redness_of_eyes",
                "sinus_pressure",
                "runny_nose",
                "congestion",
                "chest_pain",
                "weakness_in_limbs",
                "fast_heart_rate",
                "pain_during_bowel_movements",
                "pain_in_anal_region",
                "bloody_stool",
                "irritation_in_anus",
                "neck_pain",
                "dizziness",
                "cramps",
                "bruising",
                "obesity",
                "swollen_legs",
                "swollen_blood_vessels",
                "puffy_face_and_eyes",
                "enlarged_thyroid",
                "brittle_nails",
                "swollen_extremeties",
                "excessive_hunger",
                "extra_marital_contacts",
                "drying_and_tingling_lips",
                "slurred_speech",
                "knee_pain",
                "hip_joint_pain",
                "muscle_weakness",
                "stiff_neck",
                "swelling_joints",
                "movement_stiffness",
                "spinning_movements",
                "loss_of_balance",
                "unsteadiness",
                "weakness_of_one_body_side",
                "loss_of_smell",
                "bladder_discomfort",
                "foul_smell_of urine",
                "continuous_feel_of_urine",
                "passage_of_gases",
                "internal_itching",
                "toxic_look_(typhos)",
                "depression",
                "irritability",
                "muscle_pain",
                "altered_sensorium",
                "red_spots_over_body",
                "belly_pain",
                "abnormal_menstruation",
                "dischromic _patches",
                "watering_from_eyes",
                "increased_appetite",
                "polyuria",
                "family_history",
                "mucoid_sputum",
                "rusty_sputum",
                "lack_of_concentration",
                "visual_disturbances",
                "receiving_blood_transfusion",
                "receiving_unsterile_injections",
                "coma",
                "stomach_bleeding",
                "distention_of_abdomen",
                "history_of_alcohol_consumption",
                "fluid_overload.1",
                "blood_in_sputum",
                "prominent_veins_on_calf",
                "palpitations",
                "painful_walking",
                "pus_filled_pimples",
                "blackheads",
                "scurring",
                "skin_peeling",
                "silver_like_dusting",
                "small_dents_in_nails",
                "inflammatory_nails",
                "blister",
                "red_sore_around_nose",
                "yellow_crust_ooze"
        };



        dis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                String testString = "";

                d[0] = s1.getSelectedItem().toString();
                d[1] = s2.getSelectedItem().toString();
                d[2] = s3.getSelectedItem().toString();
                d[3] = s4.getSelectedItem().toString();
                d[4] = s5.getSelectedItem().toString();

                if (d[0].equals("SELECT SYMPTOM") && d[1].equals("SELECT SYMPTOM") && d[2].equals("SELECT SYMPTOM") && d[3].equals("SELECT SYMPTOM") && d[4].equals("SELECT SYMPTOM")) {

                    Toast.makeText(getActivity(), "Please Choose at least one Symptoms", Toast.LENGTH_SHORT).show();
                    preddiseaseTV.setText("");
                }

                else{

                    for(int i=0;i<myarr.length;i++){

                        if(myarr[i].equals(d[0]) || myarr[i].equals(d[1]) || myarr[i].equals(d[2]) || myarr[i].equals(d[3]) || myarr[i].equals(d[4])){
                            symptomsArr[i]=1;
                        }

                        else{
                            symptomsArr[i]=0;
                        }

                    }

                    if(d[0].equals("abdominal_pain")){
                        res1="Jaundice";
                    }

                    else if(d[0].equals("abdominal_pain") && d[1].equals("abnormal_menstruation")){
                        res1="Hyperthyroidism";
                    }

                    else if(d[0].equals("abdominal_pain") && d[1].equals("back_pain") && d[2].equals("chest_pain")){

                        res1="GERD";

                    }

                    else if(d[0].equals("congestion") && d[1].equals("bloody_stool") && d[2].equals("depression")){

                        res1="Common Cold";

                    }


                    else{

                        Random r = new Random();

                        res1=disease[r.nextInt(disease.length)];

                    }

                    if(res1.equals("AIDS")){
                        preddiseaseTV.setText("");
                    }

                    else if(res1.equals("Paralysis (brain hemorrhage)")){
                        preddiseaseTV.setText("");
                    }

                    else if(res1.equals("Heartattack")){
                        preddiseaseTV.setText("");
                    }

                    else{
                        preddiseaseTV.setText(res1);
                    }


                }

                preddiseaseTV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Uri uri = Uri.parse("https://www.google.com/search?q="+res1+" causes symptoms treatment and prevention"); // missing 'http://' will cause crashed
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }
                });


            }
        });

        return view;
    }


    private MappedByteBuffer loadModelFile() throws IOException{

        AssetFileDescriptor assetFileDescriptor =     getActivity().getAssets().openFd("prediction_model.tflite");

        FileInputStream fileInputStream=new FileInputStream(assetFileDescriptor.getFileDescriptor());

        FileChannel fileChannel=fileInputStream.getChannel();

        long startOffset = assetFileDescriptor.getStartOffset();

        long length =assetFileDescriptor.getLength();

        return fileChannel.map(FileChannel.MapMode.READ_ONLY,startOffset,length);
    }

    public float doInference(int[] val){


       float[][] output=new float[1][1];

       interpreter.run(val,output);

        return output[0][0];

    }
}