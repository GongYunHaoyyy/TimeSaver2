package com.gongyunhaoyyy.timesaver;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class timeReportActivity extends AppCompatActivity {

    private LineChart mChart;
    static int prePosition = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_report);
        final Button monthButton1 = (Button) findViewById(R.id.month_button1);
        monthButton1.setTextColor(getResources().getColor(R.color.light_purple));
        monthButton1.setBackgroundResource(R.drawable.shape_monthbutton_selected);
        final Button monthButton2 = (Button) findViewById(R.id.month_button2);
        final Button monthButton3 = (Button) findViewById(R.id.month_button3);
        final Button monthButton4 = (Button) findViewById(R.id.month_button4);
        final Button monthButton5 = (Button) findViewById(R.id.month_button5);
        final Button monthButton6 = (Button) findViewById(R.id.month_button6);
        final Button monthButton7 = (Button) findViewById(R.id.month_button7);
        final Button monthButton8 = (Button) findViewById(R.id.month_button8);
        final Button monthButton9 = (Button) findViewById(R.id.month_button9);
        final Button monthButton10 = (Button) findViewById(R.id.month_button10);
        final Button monthButton11 = (Button) findViewById(R.id.month_button11);
        final Button monthButton12 = (Button) findViewById(R.id.month_button12);
        final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.month_button_list);
        monthButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View view1 = linearLayout.getChildAt(prePosition);
                Button button = (Button) view1;
                button.setTextColor(getResources().getColor(R.color.accent_white));
                button.setBackgroundResource(R.drawable.shape_monthbutton_normal);
                monthButton1.setTextColor(getResources().getColor(R.color.light_purple));
                monthButton1.setBackgroundResource(R.drawable.shape_monthbutton_selected);
                prePosition = 0;
            }
        });
        monthButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View view1 = linearLayout.getChildAt(prePosition);
                Button button = (Button) view1;
                button.setTextColor(getResources().getColor(R.color.accent_white));
                button.setBackgroundResource(R.drawable.shape_monthbutton_normal);
                monthButton2.setTextColor(getResources().getColor(R.color.light_purple));
                monthButton2.setBackgroundResource(R.drawable.shape_monthbutton_selected);
                prePosition = 1;
            }
        });
        monthButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View view1 = linearLayout.getChildAt(prePosition);
                Button button = (Button) view1;
                button.setTextColor(getResources().getColor(R.color.accent_white));
                button.setBackgroundResource(R.drawable.shape_monthbutton_normal);
                monthButton3.setTextColor(getResources().getColor(R.color.light_purple));
                monthButton3.setBackgroundResource(R.drawable.shape_monthbutton_selected);
                prePosition = 2;
            }
        });
        monthButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View view1 = linearLayout.getChildAt(prePosition);
                Button button = (Button) view1;
                button.setTextColor(getResources().getColor(R.color.accent_white));
                button.setBackgroundResource(R.drawable.shape_monthbutton_normal);
                monthButton4.setTextColor(getResources().getColor(R.color.light_purple));
                monthButton4.setBackgroundResource(R.drawable.shape_monthbutton_selected);
                prePosition = 3;
            }
        });
        monthButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View view1 = linearLayout.getChildAt(prePosition);
                Button button = (Button) view1;
                button.setTextColor(getResources().getColor(R.color.accent_white));
                button.setBackgroundResource(R.drawable.shape_monthbutton_normal);
                monthButton5.setTextColor(getResources().getColor(R.color.light_purple));
                monthButton5.setBackgroundResource(R.drawable.shape_monthbutton_selected);
                prePosition = 4;
            }
        });
        monthButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View view1 = linearLayout.getChildAt(prePosition);
                Button button = (Button) view1;
                button.setTextColor(getResources().getColor(R.color.accent_white));
                button.setBackgroundResource(R.drawable.shape_monthbutton_normal);
                monthButton6.setTextColor(getResources().getColor(R.color.light_purple));
                monthButton6.setBackgroundResource(R.drawable.shape_monthbutton_selected);
                prePosition = 5;
            }
        });
        monthButton7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View view1 = linearLayout.getChildAt(prePosition);
                Button button = (Button) view1;
                button.setTextColor(getResources().getColor(R.color.accent_white));
                button.setBackgroundResource(R.drawable.shape_monthbutton_normal);
                monthButton7.setTextColor(getResources().getColor(R.color.light_purple));
                monthButton7.setBackgroundResource(R.drawable.shape_monthbutton_selected);
                prePosition = 6;
            }
        });
        monthButton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View view1 = linearLayout.getChildAt(prePosition);
                Button button = (Button) view1;
                button.setTextColor(getResources().getColor(R.color.accent_white));
                button.setBackgroundResource(R.drawable.shape_monthbutton_normal);
                monthButton8.setTextColor(getResources().getColor(R.color.light_purple));
                monthButton8.setBackgroundResource(R.drawable.shape_monthbutton_selected);
                prePosition = 7;
            }
        });
        monthButton9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View view1 = linearLayout.getChildAt(prePosition);
                Button button = (Button) view1;
                button.setTextColor(getResources().getColor(R.color.accent_white));
                button.setBackgroundResource(R.drawable.shape_monthbutton_normal);
                monthButton9.setTextColor(getResources().getColor(R.color.light_purple));
                monthButton9.setBackgroundResource(R.drawable.shape_monthbutton_selected);
                prePosition = 8;
            }
        });
        monthButton10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View view1 = linearLayout.getChildAt(prePosition);
                Button button = (Button) view1;
                button.setTextColor(getResources().getColor(R.color.accent_white));
                button.setBackgroundResource(R.drawable.shape_monthbutton_normal);
                monthButton10.setTextColor(getResources().getColor(R.color.light_purple));
                monthButton10.setBackgroundResource(R.drawable.shape_monthbutton_selected);
                prePosition = 9;
            }
        });
        monthButton11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View view1 = linearLayout.getChildAt(prePosition);
                Button button = (Button) view1;
                button.setTextColor(getResources().getColor(R.color.accent_white));
                button.setBackgroundResource(R.drawable.shape_monthbutton_normal);
                monthButton11.setTextColor(getResources().getColor(R.color.light_purple));
                monthButton11.setBackgroundResource(R.drawable.shape_monthbutton_selected);
                prePosition = 10;
            }
        });
        monthButton12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View view1 = linearLayout.getChildAt(prePosition);
                Button button = (Button) view1;
                button.setTextColor(getResources().getColor(R.color.accent_white));
                button.setBackgroundResource(R.drawable.shape_monthbutton_normal);
                monthButton12.setTextColor(getResources().getColor(R.color.light_purple));
                monthButton12.setBackgroundResource(R.drawable.shape_monthbutton_selected);
                prePosition = 11;
            }
        });

        mChart = (LineChart) findViewById(R.id.chart1);
        // no description text
        mChart.getDescription().setEnabled(false);
        // enable touch gestures
        mChart.setTouchEnabled(false);
        mChart.setDragDecelerationFrictionCoef(0.9f);
        // enable scaling and dragging
        mChart.setDragEnabled(false);
        mChart.setScaleEnabled(false);
        mChart.setDrawGridBackground(false);
        mChart.setHighlightPerDragEnabled(false);
        // if disabled, scaling can be done on x- and y-axis separately
        mChart.setPinchZoom(true);
        // set an alternative background color
        mChart.setBackgroundColor(Color.WHITE);
        // add data
        setData(30, 70);
        List<ILineDataSet> sets = mChart.getData()
                .getDataSets();

        for (ILineDataSet iSet : sets) {

            LineDataSet set = (LineDataSet) iSet;
            set.setDrawValues(!set.isDrawValuesEnabled());
        }

        for (ILineDataSet iSet : sets) {

            LineDataSet set = (LineDataSet) iSet;
            if (set.isDrawCirclesEnabled())
                set.setDrawCircles(false);
            else
                set.setDrawCircles(true);
        }

        for (ILineDataSet iSet : sets) {

            LineDataSet set = (LineDataSet) iSet;
            set.setMode(set.getMode() == LineDataSet.Mode.CUBIC_BEZIER
                    ? LineDataSet.Mode.LINEAR
                    : LineDataSet.Mode.CUBIC_BEZIER);
        }

        for (ILineDataSet iSet : sets) {

            LineDataSet set = (LineDataSet) iSet;
            if (set.isDrawFilledEnabled())
                set.setDrawFilled(false);
            else
                set.setDrawFilled(true);
        }

        mChart.invalidate();
        mChart.setViewPortOffsets(0, 0, 0, 0);
        mChart.animateY(1000);
        // get the legend (only possible after setting data)
        Legend l = mChart.getLegend();
        // modify the legend ...
        l.setForm(Legend.LegendForm.LINE);
        l.setEnabled(false);
//        l.setTypeface(mTfLight);
//        l.setTextSize(11f);
        l.setTextColor(Color.WHITE);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
//        l.setYOffset(11f);

        XAxis xAxis = mChart.getXAxis();

        xAxis.setTextSize(11f);
        xAxis.setTextColor(Color.GRAY);
        xAxis.setDrawGridLines(true);
        xAxis.setDrawAxisLine(false);

        YAxis leftAxis = mChart.getAxisLeft();

        leftAxis.setEnabled(false);
        leftAxis.setTextColor(ColorTemplate.getHoloBlue());
        leftAxis.setAxisMaximum(200f);
        leftAxis.setAxisMinimum(0f);
        leftAxis.setDrawGridLines(false);
        leftAxis.setDrawAxisLine(false);
        leftAxis.setGranularityEnabled(true);

        YAxis rightAxis = mChart.getAxisRight();

        rightAxis.setTextColor(Color.RED);
        rightAxis.setEnabled(false);
        rightAxis.setAxisMaximum(900);
        rightAxis.setAxisMinimum(0);
        rightAxis.setDrawGridLines(false);
        rightAxis.setDrawAxisLine(false);
        rightAxis.setDrawZeroLine(false);
        rightAxis.setGranularityEnabled(false);

    }

    private void setData(int count, float range) {

        ArrayList<Entry> yVals1 = new ArrayList<Entry>();

        for (int i = 0; i < count + 1; i++) {
            float mult = range / 2f;
            float val = (float) (Math.random() * mult) + 50;
            yVals1.add(new Entry(i, val));
        }

        ArrayList<Entry> yVals2 = new ArrayList<Entry>();

        for (int i = 0; i < count + 1; i++) {
            float mult = range;
            float val = (float) (Math.random() * mult) + 450;
            yVals2.add(new Entry(i, val));
//            if(i == 10) {
//                yVals2.add(new Entry(i, val + 50));
//            }
        }

        ArrayList<Entry> yVals3 = new ArrayList<Entry>();

        for (int i = 0; i < count + 1; i++) {
            float mult = range;
            float val = (float) (Math.random() * mult) + 500;
            yVals3.add(new Entry(i, val));
        }

        LineDataSet set1, set2, set3;

        if (mChart.getData() != null &&
                mChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) mChart.getData().getDataSetByIndex(0);
            set2 = (LineDataSet) mChart.getData().getDataSetByIndex(1);
            set3 = (LineDataSet) mChart.getData().getDataSetByIndex(2);
            set1.setValues(yVals1);
            set2.setValues(yVals2);
            set3.setValues(yVals3);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(yVals1, "DataSet 1");
            set1.setAxisDependency(YAxis.AxisDependency.RIGHT);
            set1.setColor(ColorTemplate.getHoloBlue());
            set1.setLineWidth(2f);
            set1.setCircleRadius(3f);
            set1.setFillAlpha(75);
            set1.setFillColor(ColorTemplate.getHoloBlue());
            set1.setDrawCircleHole(false);

            set2 = new LineDataSet(yVals2, "DataSet 2");
            set2.setAxisDependency(YAxis.AxisDependency.RIGHT);
            set2.setColor(Color.RED);
            set2.setLineWidth(2f);
            set2.setCircleRadius(3f);
            set2.setFillAlpha(75);
            set2.setFillColor(Color.RED);
            set2.setDrawCircleHole(false);

            set3 = new LineDataSet(yVals3, "DataSet 3");
            set3.setAxisDependency(YAxis.AxisDependency.RIGHT);
            set3.setColor(Color.YELLOW);
            set3.setCircleColor(Color.WHITE);
            set3.setLineWidth(2f);
            set3.setCircleRadius(3f);
            set3.setFillAlpha(75);
            set3.setFillColor(Color.YELLOW);
            set3.setDrawCircleHole(false);
            set3.setHighLightColor(Color.rgb(244, 117, 117));

            // create a data object with the datasets
            LineData data = new LineData(set1, set2, set3);
            data.setValueTextColor(Color.WHITE);
            data.setValueTextSize(9f);

            // set data
            mChart.setData(data);
        }
    }
}

