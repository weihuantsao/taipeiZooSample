package a920.cybersoft.com.assigment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import a920.cybersoft.com.assigment.R;
import a920.cybersoft.com.assigment.bean.ZooArea;

public class ZooAreaListAdapter extends RecyclerView.Adapter<ZooAreaListAdapter.MyViewHolder> {
    private Context context;
    private List<ZooArea> areatList;
    private OnItemClicked onClick;


    public interface OnItemClicked {
        void onItemClick(ZooArea zooArea);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public View mView;
        public TextView title, description, memo;
        public ImageView img;

        public MyViewHolder(View view) {
            super(view);
            mView = view;
            title = view.findViewById(R.id.item_title);
            description = view.findViewById(R.id.item_msg);
            img = view.findViewById(R.id.item_img);
            memo = view.findViewById(R.id.item_memo);
        }
    }

    public ZooAreaListAdapter(Context context, List<ZooArea> zooAreasList, OnItemClicked onClick) {
        this.context = context;
        this.onClick = onClick;
        this.areatList = zooAreasList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final ZooArea zooArea = areatList.get(position);
        holder.title.setText(zooArea.getName());
        holder.description.setText(zooArea.getInfo());
        holder.memo.setText(zooArea.getMemo());

        Glide.with(context)
                .load(zooArea.getImgUrl())
                .into(holder.img);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != onClick) {
                    onClick.onItemClick(zooArea);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return areatList.size();
    }
}
