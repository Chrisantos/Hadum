package com.example.chrisantuseze.hadum.Academia;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chrisantuseze.hadum.R;

import java.util.List;

/**
 * Created by CHRISANTUS EZE on 08/11/2017.
 */

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookHolder> {
    private Context context;
    private List<Product> productList;

    public BookAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @Override
    public BookAdapter.BookHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_recyclerlayout, null);
        return new BookHolder(view);
    }

    @Override
    public void onBindViewHolder(BookAdapter.BookHolder holder, int position) {
        Product product = productList.get(position);
        holder.tvPdf.setText(product.getPdf());
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class BookHolder extends RecyclerView.ViewHolder {
        private TextView tvPdf;
        public BookHolder(View itemView) {
            super(itemView);
            tvPdf = (TextView)itemView.findViewById(R.id.tvpdf);
        }
    }
}
