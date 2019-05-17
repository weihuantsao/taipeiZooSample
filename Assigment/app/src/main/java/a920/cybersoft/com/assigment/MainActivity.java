package a920.cybersoft.com.assigment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import a920.cybersoft.com.assigment.bean.ZooArea;
import a920.cybersoft.com.assigment.fragment.DetailFragment;
import a920.cybersoft.com.assigment.fragment.ItemFragment;
import a920.cybersoft.com.assigment.fragment.ItemFragment.OnListFragmentInteractionListener;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements OnListFragmentInteractionListener {

    private final String TAG = getClass().getSimpleName();
    private Fragment[] mFragments = new Fragment[2];
    public final int ITEM = 0;
    public final int DETAIL = 1;

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.btnLeft)
    ImageView btnLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initFragment();
        tvTitle.setText("台北市立動物園");
        setFragment(ITEM);
    }

    private void initFragment() {
        mFragments[ITEM] = ItemFragment.newInstance();
        mFragments[DETAIL] = DetailFragment.newInstance(null);
    }

    private void setFragment(int fragmentIndex) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.flContent, mFragments[fragmentIndex]);
        ft.addToBackStack("");
        ft.commitAllowingStateLoss();
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.flContent, fragment);
        ft.addToBackStack("");
        ft.commitAllowingStateLoss();
    }

    @Override
    public void onListFragmentInteraction(ZooArea item) {
        tvTitle.setText(item.getName());
        btnLeft.setImageResource(R.drawable.ic_arrow_back_black_24dp);
        setFragment(DetailFragment.newInstance(item));

    }

    @OnClick(R.id.btnLeft)
    void onClickBack() {
        int fragments = getSupportFragmentManager().getBackStackEntryCount();
        if (fragments == 1) {
            finish();
        } else if (getFragmentManager().getBackStackEntryCount() > 1) {
            getFragmentManager().popBackStack();
        } else {
            tvTitle.setText("台北市立動物園");
            btnLeft.setImageResource(R.drawable.ic_dehaze_black_24dp);
            super.onBackPressed();
        }
    }

    @Override
    public void onBackPressed() {
        int fragments = getSupportFragmentManager().getBackStackEntryCount();
        if (fragments == 1) {
            finish();
        } else if (getFragmentManager().getBackStackEntryCount() > 1) {
            getFragmentManager().popBackStack();
        } else {
            tvTitle.setText("台北市立動物園");
            btnLeft.setImageResource(R.drawable.ic_dehaze_black_24dp);
            super.onBackPressed();
        }
    }

}
