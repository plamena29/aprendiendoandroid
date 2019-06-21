package com.canplimplam.gestormultas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.canplimplam.gestormultas.model.Multa;
import com.canplimplam.gestormultas.services.impl.MultaServicesImpl;

import java.text.SimpleDateFormat;
import java.util.List;

public class Adaptador extends BaseAdapter {

    private LayoutInflater inflater = null;
    private List<Multa> multas;
    private Context contexto;

    public Adaptador (Context contexto){
        this.contexto = contexto;
        inflater = (LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
        multas = MultaServicesImpl.getInstance().getAll();
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {

        final View vista = inflater.inflate(R.layout.multa_row, null);
        SimpleDateFormat sdfDia = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdfHora = new SimpleDateFormat("HH:mm");

        //Recoger todas las vistas de ese layout..
        TextView fechaDia = (TextView) vista.findViewById(R.id.idDiaLista);
        TextView fechaHora = (TextView) vista.findViewById(R.id.idHoraLista);
        TextView codigoMulta = (TextView) vista.findViewById(R.id.idCodigoMultaLista);
        TextView agente = (TextView) vista.findViewById(R.id.idAgenteLista);
        TextView motivo = (TextView) vista.findViewById(R.id.idMotivoLista);
        TextView observaciones = (TextView) vista.findViewById(R.id.idObservacionesLista);
        TextView importe = (TextView) vista.findViewById(R.id.idImporteLista);
        TextView aceptada = (TextView) vista.findViewById(R.id.idAceptadaLista);
        TextView tipo = (TextView) vista.findViewById(R.id.idTipoLista);

        Multa multa = multas.get(i);
        fechaDia.setText(sdfDia.format(multa.getFechaHora()));
        fechaHora.setText(sdfHora.format(multa.getFechaHora()));
        codigoMulta.setText(String.valueOf(multa.getCodigo()));
        agente.setText(String.valueOf(multa.getAgente().getNombre() + " " + multa.getAgente().getApellido1() + " " + multa.getAgente().getApellido2()));
        motivo.setText(String.valueOf(multa.getMotivo()));
        observaciones.setText(String.valueOf(multa.getObservaciones()));
        importe.setText(String.valueOf(multa.getImporte()));
        if(multa.isAceptada()){
            aceptada.setText("SI");
        } else{
            aceptada.setText("NO");
        }
        tipo.setText(String.valueOf(multa.getTipo()));
        return vista;
    }

    @Override
    public int getCount() {
        return multas.size();
    }

    @Override
    public Object getItem(int position) {
        return multas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return multas.get(position).getCodigo();
    }
}
