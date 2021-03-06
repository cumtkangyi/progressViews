package com.example.demo;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.progressdemo.R;

import person.zhou.views.PieChartView;
import person.zhou.views.ProgressView;

public class ProgressViewFragment extends Fragment  implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    ProgressView progress1, progress2,progress3;
    SeekBar seekBar1, seekBar2;
    PieChartView pieChart;
    TextView txtCmd;

    public ProgressViewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_progress_view, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        View button1 = findViewById(R.id.button1);
        progress1 = (ProgressView) findViewById(R.id.progress1);
        progress2 = (ProgressView) findViewById(R.id.progress2);
        progress3 = (ProgressView) findViewById(R.id.progress3);
        seekBar1 = (SeekBar) findViewById(R.id.seekBar1);
        seekBar2 = (SeekBar) findViewById(R.id.seekBar2);
        pieChart = (PieChartView) findViewById(R.id.pie_chart);
        txtCmd = (TextView) findViewById(R.id.txt_cmd);
        button1.setOnClickListener(this);
        seekBar1.setOnSeekBarChangeListener(this);
        seekBar1.setTag(progress1);
        seekBar2.setOnSeekBarChangeListener(this);
        seekBar2.setTag(progress2);
    }

    private View findViewById(int viewId) {
        return getView().findViewById(viewId);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button1) {
            progress1.setProgress(seekBar1.getProgress(), true);
            progress2.setProgress(seekBar2.getProgress(), true);
            handler.sendEmptyMessage(0);
        }
    }

    public Handler.Callback callback = new Handler.Callback() {

        int[] progress = {25,33,40,80,50};

        @Override
        public boolean handleMessage(Message message) {
            switch (message.what){
                case 0:
                    txtCmd.setText("");
                case 1:
                case 2:
                case 3:
                    handler.sendEmptyMessageDelayed(message.what + 1, 800);
                    break;
                case 4:
                    break;
            }
            progress3.setProgress(progress[message.what], true);
            String text = txtCmd.getText()+"\np.setProgress("+progress[message.what]+", true);";
            txtCmd.setText(text);
            return false;
        }
    };

    public Handler handler = new Handler(callback);

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        ProgressView pView= (ProgressView) seekBar.getTag();
        pView.setProgress(seekBar.getProgress(),true);
    }
}
