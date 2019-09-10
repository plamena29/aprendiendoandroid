package com.canplimplam.myviewselectornumero;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

public class EmptyView extends View {
    public EmptyView(Context context) {
        super(context);
        Log.d("**", "constructior");
      //  LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
       // inflater.inflate(R.layout.empty_view, null, true);
    }
}
