package com.canplimplam.restpolloloco.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.canplimplam.restpolloloco.R;
import com.canplimplam.restpolloloco.modelo.Pedido;
import com.canplimplam.restpolloloco.modelo.Producto;

import java.text.SimpleDateFormat;
import java.util.List;

public class PedidoListAdapter extends BaseAdapter {

    private LayoutInflater inflater = null;
    private List<Pedido> pedidos;
    private Context contexto;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public PedidoListAdapter (Context contexto, List<Pedido> pedidos) {
        this.contexto = contexto;
        inflater = (LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
        this.pedidos = pedidos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View vista = inflater.inflate(R.layout.row_model_pedido, null);

        //Recoger todas las vistas de ese layout..

        TextView idPedido = (TextView) vista.findViewById(R.id.idIdPedidoRowAdaptador);
        TextView fechaPedido = (TextView) vista.findViewById(R.id.idFechaPedidoRowAdaptador);
        TextView mesaPedido = (TextView) vista.findViewById(R.id.idMesaPedidoRowAdaptador);
        TextView camareroPedido = (TextView) vista.findViewById(R.id.idCamareroPedidoRowAdaptador);

        Pedido pedido = pedidos.get(position);
        idPedido.setText(String.valueOf(pedido.getId()));
        fechaPedido.setText(sdf.format(pedido.getFecha()));
        mesaPedido.setText(String.valueOf(pedido.getMesa()));
        camareroPedido.setText(pedido.getCamarero().getNombre());

        return vista;
    }

    @Override
    public int getCount() {
        return pedidos.size();
    }

    @Override
    public Object getItem(int position) {
        return pedidos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return pedidos.get(position).getId();
    }
}
