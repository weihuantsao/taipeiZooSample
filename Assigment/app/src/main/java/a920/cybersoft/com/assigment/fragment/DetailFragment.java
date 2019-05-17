package a920.cybersoft.com.assigment.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import a920.cybersoft.com.assigment.R;
import a920.cybersoft.com.assigment.bean.ZooArea;
import butterknife.BindView;
import butterknife.ButterKnife;


public class DetailFragment extends Fragment {

    @BindView(R.id.item_img)
    ImageView item_img;
    @BindView(R.id.item_msg)
    TextView item_msg;
    @BindView(R.id.item_memo)
    TextView item_memo;
    @BindView(R.id.item_category)
    TextView item_category;

    private static ZooArea zooArea;

    public static DetailFragment newInstance(ZooArea item) {
        DetailFragment fragment = new DetailFragment();
        zooArea = item;

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, view);

        item_msg.setText(zooArea.getInfo());
        item_memo.setText(zooArea.getMemo().equals("") ? "無休館資訊" : zooArea.getMemo());
        item_category.setText(zooArea.getCategory());

        Glide.with(getActivity())
                .load(zooArea.getImgUrl())
                .into(item_img);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }
}
