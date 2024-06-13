package zfr.mobile.persuhuanduniawii;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterCuaca extends RecyclerView.Adapter<AdapterCuaca.ItemVH> {
    private Context context;
    private ArrayList<Cuaca> cuacaData;

    public AdapterCuaca(Context context, ArrayList<Cuaca> cuacaData){
        this.context = context;
        this.cuacaData = cuacaData;
    }

    @NonNull
    @Override
    public AdapterCuaca.ItemVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View card = LayoutInflater.from(context)
                .inflate(R.layout.item_cuaca, parent, false);
        ItemVH viewholder = new ItemVH(card);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCuaca.ItemVH holder, int position) {
        Cuaca c = cuacaData.get(position);
        holder.tvDate.setText(c.date);
        holder.ivIcon.setImageResource(c.icon);
        holder.tvCondition.setText(c.condition);
    }

    @Override
    public int getItemCount() {
        return cuacaData.size();
    }

    class ItemVH extends RecyclerView.ViewHolder {

        private final ImageView ivIcon;
        private final TextView tvCondition;
        private final TextView tvDate;
        private int weatherCode;

        public ItemVH(@NonNull View itemView) {
            super(itemView);
            this.tvDate = itemView.findViewById(R.id.tvDateModal);
            this.ivIcon = itemView.findViewById(R.id.ivIconModal);
            this.tvCondition = itemView.findViewById(R.id.tvConditionModal);
        }
    }
}
