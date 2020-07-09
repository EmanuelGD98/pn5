package com.example.interfaz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieDataSet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    //pre registros de alumnos
    String[] alumnos = {"Seleccione","Pepito","Juanito","Pedrito", "Jose"};

    EditText etBirthday,alumno_registrado;
    TextView textViewT4_5, textViewT4_7, Nombre_Grupo_PestanaAnalisis, id_Grupo_PestanaAnalisis;
    Calendar calendario = Calendar.getInstance();
    Button pase_lista, administrar_grupos, hoy, salida, crear_grupo_vacio, crear_grupo_arch, agregar_alumno;
    Spinner spinner1, spinner2, spinner3;
    ListView list_view;
    BarChart LineChart ;
    BarDataSet LineDataSet;
    BarChart barChart;
    BarDataSet barDataSet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button pase_lista = (Button) findViewById(R.id.pase_lista);
        Button administrar_grupos = (Button) findViewById(R.id.buttonAdministrarGrupo);
        Button hoy = (Button) findViewById(R.id.button_hoy);
        Button salida = (Button) findViewById(R.id.button_salir_aplicacion);
        Button crear_grupo_vacio = (Button) findViewById(R.id.grupo_vacio);
        Button crear_grupo_arch = (Button) findViewById(R.id.grupo_arch);
        Button agregar_alumno = (Button) findViewById(R.id.agregar_alumno);
        //EditText alumno_registrado = (EditText) findViewById(R.id.alumno_registrado);
        Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        Spinner spinner3 = (Spinner) findViewById(R.id.spinner3);
        final TextView textViewT4_5 = (TextView) findViewById(R.id.textViewT4_5);
        final TextView textViewT4_7 = (TextView) findViewById(R.id.textViewT4_7);
        final TextView id_Grupo_PestanaAnalisis = (TextView) findViewById(R.id.id_Grupo_PestanaAnalisis);
        final TextView Nombre_Grupo_PestanaAnalisis = (TextView) findViewById(R.id.Nombre_Grupo_PestanaAnalisis);
        final BarChart lineChart = findViewById(R.id.lineChart);
        final BarChart barChart = findViewById(R.id.pastelChart);

        // Importante: Esto va antes de instanciar controles dentro de cada pestaña

        // Agregar las pestañas---
        Resources res = getResources();
        TabHost tabHost = (TabHost) findViewById(R.id.tabhost);
        tabHost.setup();

        TabHost.TabSpec spec1 = tabHost.newTabSpec("");
        spec1.setContent(R.id.tab1);
        spec1.setIndicator("PASES LISTA");

        TabHost.TabSpec spec2 = tabHost.newTabSpec("");
        spec2.setContent(R.id.tab2);
        spec2.setIndicator("Grupos");

        TabHost.TabSpec spec3 = tabHost.newTabSpec("");
        spec3.setContent(R.id.tab3);
        spec3.setIndicator("Alumnos");

        TabHost.TabSpec spec4 = tabHost.newTabSpec("");
        spec4.setContent(R.id.tab4);
        spec4.setIndicator("Estadisticas");
//acomoda las pestañas
        tabHost.addTab(spec1);
        tabHost.addTab(spec2);
        tabHost.addTab(spec3);
        tabHost.addTab(spec4);

        // Otros Recursos (TextView, Buttons, ListView, EditText, etx)

        //calendario
        etBirthday = findViewById(R.id.etBirthday);
        etBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(MainActivity.this, date, calendario
                        .get(Calendar.YEAR), calendario.get(Calendar.MONTH),
                        calendario.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        //spinner2.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, alumnos));


        //se le agregan datos default
        final ArrayList<Contact> contacts = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            contacts.add(new Contact("Name_" + i, "Id_" + i, 5, "1"));
        }

        ArrayAdapter<Contact> adapter =
                new ArrayAdapter<Contact>(getApplicationContext(),  android.R.layout.simple_spinner_dropdown_item, contacts);
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);

        spinner2.setAdapter(adapter);

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Object item = parent.getItemAtPosition(pos);
                System.out.println("se selecciono el : "+ item+ " con id "+ id);

                textViewT4_5.setText(""+id);
                textViewT4_7.setText(""+item);
                Contact c = new Contact();

                // Creamos un set de datos

                System.out.println("nueva funcion es = "+findUsingEnhancedForLoop(""+item, contacts));

                ArrayList<BarEntry> lineEntries = new ArrayList<BarEntry>();
                    //contacts.get(c.());
                    float y = findUsingEnhancedForLoop(""+item, contacts);
                    float x = id;
                    lineEntries.add(new BarEntry((float) x,(float)y));
                System.out.println(" y = "+y+" x = "+x);


                // Unimos los datos al data set
                BarDataSet lineDataSet = new BarDataSet(lineEntries, "Asistencia "+ item);

                // Asociamos al gráfico
                BarData lineData = new BarData();
                lineData.addDataSet(lineDataSet);
                lineChart.setData(lineData);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //se le agregan datos default
        final ArrayList<Grupos> groups2 = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            groups2.add(new Grupos("Grupo_" + i, "Id_" + i));
        }
        ArrayAdapter<Grupos> adapter2 =
                new ArrayAdapter<Grupos>(getApplicationContext(),  android.R.layout.simple_spinner_dropdown_item, groups2);

        adapter2.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);

        spinner3.setAdapter(adapter2);


        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Object item = parent.getItemAtPosition(pos);
                System.out.println("se selecciono el : "+ item+ " con id "+ id);

                id_Grupo_PestanaAnalisis.setText(""+id);
                Nombre_Grupo_PestanaAnalisis.setText(""+item);

                // Creamos un set de datos

                System.out.println("nueva funcion es = "+findUsingEnhancedForLoop2(""+item, contacts));

                ArrayList<BarEntry> lineEntries2 = new ArrayList<BarEntry>();
                //contacts.get(c.());
                float y = findUsingEnhancedForLoop2(""+id, contacts);
                float x = id;
                lineEntries2.add(new BarEntry((float) x,(float)y));
                System.out.println(" y = "+y+" x = "+x);


                // Unimos los datos al data set
                BarDataSet lineDataSet = new BarDataSet(lineEntries2, "Total asistencia por grupo"+ item);

                // Asociamos al gráfico
                BarData lineData2 = new BarData();
                lineData2.addDataSet(lineDataSet);
                barChart.setData(lineData2);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    public int findUsingEnhancedForLoop(
            String name, ArrayList<Contact> customers) {

        for (Contact customer : customers) {
            if (customer.getContact_name().equals(name)) {
                int  a = customer.getAsistencia();
                System.out.println("sss = "+a);
                return a;
            }
        }
        return 0;
    }

    public int findUsingEnhancedForLoop2(
            String id, ArrayList<Contact> customers) {
        int a = 0;
        for (Contact customer : customers) {
            if (customer.getGrupo().equals(id)) {
                a++;
                System.out.println("sss = "+a);
                return a;
            }
        }
        return 0;
    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            calendario.set(Calendar.YEAR, year);
            calendario.set(Calendar.MONTH, monthOfYear);
            calendario.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            actualizarInput();
        }

    };


    private void actualizarInput() {
        String formatoDeFecha = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(formatoDeFecha, Locale.US);

        etBirthday.setText(sdf.format(calendario.getTime()));
    }


    private class Contact {
        private String contact_name;
        private String contact_id;
        private int asistencias;
        private String id_grupo;
        public Contact() {
        }

        public Contact(String contact_name, String contact_id, int asistencias, String id_grupo) {
            this.contact_name = contact_name;
            this.contact_id = contact_id;
            this.asistencias = asistencias;
            this.id_grupo = id_grupo;
        }

        public String getContact_name() {
            return contact_name;
        }

        public void setContact_name(String contact_name) {
            this.contact_name = contact_name;
        }

        public String getContact_id() {
            return contact_id;
        }

        public void setContact_id(String contact_id) {
            this.contact_id = contact_id;
        }

        public int getAsistencia() {
            return asistencias;
        }

        public void setAsistencias(int asistencias) {
            this.asistencias = asistencias;
        }

        public String getGrupo() {
            return id_grupo;
        }

        public void setGrupo(String id_grupo) {
            this.id_grupo = id_grupo;
        }




        /**
         * Pay attention here, you have to override the toString method as the
         * ArrayAdapter will reads the toString of the given object for the name
         *
         * @return contact_name
         */
        @Override
        public String toString() {
            return contact_name;
        }
    }


    private class Grupos {
        private String grupo_name;
        private String grupo_id;
        public Grupos() {
        }

        public Grupos(String grupo_name, String grupo_id) {
            this.grupo_name = grupo_name;
            this.grupo_id = grupo_id;
        }

        public String getNombreGrupo() {
            return grupo_name;
        }

        public void setNombreGrupo(String grupo_name) {
            this.grupo_name = grupo_name;
        }

        public String getGrupo_id() {
            return grupo_id;
        }

        public void setGrupo_id(String grupo_id) {
            this.grupo_id = grupo_id;
        }

        /**
         * Pay attention here, you have to override the toString method as the
         * ArrayAdapter will reads the toString of the given object for the name
         *
         * @return contact_name
         */
        @Override
        public String toString() {
            return grupo_name;
        }
    }
}

