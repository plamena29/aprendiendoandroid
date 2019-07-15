package com.canplimplam.restpolloloco.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.canplimplam.restpolloloco.modelo.LineaPedido;
import com.canplimplam.restpolloloco.modelo.Pedido;

import java.util.List;

public class LineaPedidoListAdapter extends BaseAdapter {

    private LayoutInflater inflater = null;
    private List<LineaPedido> lineasPedido;
    private Context contexto;

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
