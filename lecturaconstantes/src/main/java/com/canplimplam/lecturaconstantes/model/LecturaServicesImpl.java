package com.canplimplam.lecturaconstantes.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class LecturaServicesImpl  implements LecturaServices{

    private static final Map<Integer, Lectura> LECTURAS;

    private static final LecturaServicesImpl INSTANCE = new LecturaServicesImpl();

    static{
        LECTURAS = new TreeMap<>();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        Date fecha0 = null;
        Date fecha1 = null;
        Date fecha2 = null;
        Date fecha3 = null;
        Date fecha4 = null;
        Date fecha5 = null;
        Date fecha6 = null;
        Date fecha7 = null;
        Date fecha8 = null;
        Date fecha9 = null;

        try {
            fecha0 = sdf.parse("01/01/2019 01:20:10");
            fecha1 = sdf.parse("02/01/2019 10:23:20");
            fecha2 = sdf.parse("03/01/2019 09:45:40");
            fecha3 = sdf.parse("04/01/2019 08:34:50");
            fecha4 = sdf.parse("05/01/2019 10:17:20");
            fecha5 = sdf.parse("06/01/2019 09:19:10");
            fecha6 = sdf.parse("07/01/2019 07:34:40");
            fecha7 = sdf.parse("08/01/2019 10:26:50");
            fecha8 = sdf.parse("09/01/2019 11:35:40");
            fecha9 = sdf.parse("10/01/2019 08:28:30");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Lectura l0 = new Lectura(fecha0, 54.0, 80.0, 115.0, 1.1333, 41.0667);
        Lectura l1 = new Lectura(fecha1, 53.5, 85.0, 110.0, 1.1333, 41.0667);
        Lectura l2 = new Lectura(fecha2, 53.0, 85.0, 120.0, 2.0833300, 41.4666700);
        Lectura l3 = new Lectura(fecha3, 53.2, 80.0, 120.0, 2.0833300, 41.4666700);
        Lectura l4 = new Lectura(fecha4, 53.2, 85.0, 115.0, 2.0833300, 41.4666700);
        Lectura l5 = new Lectura(fecha5, 53.0, 90.0, 110.0, 2.0833300, 41.4666700);
        Lectura l6 = new Lectura(fecha6, 52.7, 85.0, 110.0, 2.1564100, 41.4023700);
        Lectura l7 = new Lectura(fecha7, 52.5, 80.0, 105.0, 1.1333, 41.0667);
        Lectura l8 = new Lectura(fecha8, 52.6, 80.0, 115.0, 1.1333, 41.0667);
        Lectura l9 = new Lectura(fecha9, 52.3, 85.0, 120.0, 2.0833300, 41.4666700);

        l0.setCodigo(100);
        l1.setCodigo(101);
        l2.setCodigo(102);
        l3.setCodigo(103);
        l4.setCodigo(104);
        l5.setCodigo(105);
        l6.setCodigo(106);
        l7.setCodigo(107);
        l8.setCodigo(108);
        l9.setCodigo(109);

        LECTURAS.put(l0.getCodigo(), l0);
        LECTURAS.put(l1.getCodigo(), l1);
        LECTURAS.put(l2.getCodigo(), l2);
        LECTURAS.put(l3.getCodigo(), l3);
        LECTURAS.put(l4.getCodigo(), l4);
        LECTURAS.put(l5.getCodigo(), l5);
        LECTURAS.put(l6.getCodigo(), l6);
        LECTURAS.put(l7.getCodigo(), l7);
        LECTURAS.put(l8.getCodigo(), l8);
        LECTURAS.put(l9.getCodigo(), l9);
    }

    private LecturaServicesImpl(){

    }

    public static LecturaServicesImpl getInstance(){
        return INSTANCE;
    }

    @Override
    public Lectura create(Lectura lectura) {
        //hemos de calcular el nuevo código
        Integer maxCode = ((TreeMap<Integer, Lectura>) LECTURAS).lastKey();
        Integer newCode = ++maxCode;
        lectura.setCodigo(newCode);
        return LECTURAS.put(lectura.getCodigo(), lectura);
    }

    @Override
    public Lectura read(Integer codigo) {
        return LECTURAS.get(codigo);
    }

    @Override
    public Lectura update(Lectura lectura) {

        boolean lecturaExiste = LECTURAS.containsKey(lectura.getCodigo());

        if(lectura.getCodigo() == null || !lecturaExiste){
            throw new IllegalArgumentException("Sólo se pueden actualizar lecturas que están en el sistema");
        }
        return LECTURAS.put(lectura.getCodigo(), lectura);
    }

    @Override
    public boolean delete(Integer codigo) {
        Lectura lectura = LECTURAS.remove(codigo);
        return lectura == null ? false: true;
    }

    @Override
    public List<Lectura> getAll() {
        List<Lectura> lecturas = new ArrayList<Lectura>(LECTURAS.values());
        Collections.sort(lecturas, new Comparator<Lectura>(){

            @Override
            public int compare(Lectura lectura1, Lectura lectura2) {
                return lectura2.getCodigo()-lectura1.getCodigo();
            }
        });
        return lecturas;
    }

    @Override
    public List<Lectura> getBetweenDates(Date fecha1, Date fecha2) {
        List<Lectura> lecturas = new ArrayList<>();

        for (Lectura lectura: getAll()){
            Date fechaHora = lectura.getFechaHora();
            if(fechaHora.after(fecha1) && fechaHora.before(fecha2)){
                lecturas.add(lectura);
            }
        }
        return lecturas;
    }
}
