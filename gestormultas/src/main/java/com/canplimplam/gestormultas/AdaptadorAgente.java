package com.canplimplam.gestormultas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.canplimplam.gestormultas.model.Agente;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorAgente extends ArrayAdapter<Agente> {

    public AdaptadorAgente(Context contexto, List<Agente> listaAgentes){
        super(contexto, 0, listaAgentes);
    }

    //@androidx.annotation.NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position,View convertView,ViewGroup parent){
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.spinner_agente_row, parent, false);
        }

        TextView codigoAgente = convertView.findViewById(R.id.idCodigoAgenteSpinnerRow);
        TextView nombreAgente = convertView.findViewById(R.id.idNombreAgenteSpinnerRow);

        Agente currentAgente = getItem(position);

        if(currentAgente != null) {
            codigoAgente.setText(currentAgente.getCodigo().toString());
            nombreAgente.setText(currentAgente.getNombre() + " " + currentAgente.getApellido1() + " " + currentAgente.getApellido2());
        }

        return convertView;
    }
}
