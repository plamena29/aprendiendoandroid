package com.canplimplam.paisesbanderas.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.canplimplam.paisesbanderas.R;
import com.canplimplam.paisesbanderas.modelo.Country;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CountriesAdapter extends BaseAdapter {

    private List<Country> paises;
    private LayoutInflater inflater;

    public CountriesAdapter(Context context, List<Country> paises) {
        this.paises = paises;
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = inflater.inflate(R.layout.row_country, null);
        TextView pais = (TextView) view.findViewById(R.id.idNameRow);
        TextView alpha2Code = (TextView) view.findViewById(R.id.idAlpha2CodeRow);
        TextView capital = (TextView) view.findViewById(R.id.idCapitalRow);
        ImageView bandera = (ImageView) view.findViewById(R.id.idBanderaRow);

        Country country = paises.get(position);
        pais.setText(country.getName());
        alpha2Code.setText(country.getAlpha2Code());
        capital.setText(country.getCapital());

        String imageURL = "https://www.countryflags.io/" + country.getAlpha2Code() + "/flat/64.png";

        Picasso.get().load(imageURL).placeholder(R.drawable.banderablanca).into(bandera);

        return view;
    }

    @Override
    public int getCount() {
        return paises.size();
    }

    @Override
    public Object getItem(int position) {
        return paises.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}
