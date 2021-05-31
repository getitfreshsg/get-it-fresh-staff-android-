package com.wetmarket.staff.Activity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import com.wetmarket.staff.R;
import com.wetmarket.staff.Util.MyPref;
import com.wetmarket.staff.Util.StringUtils;
import com.wetmarket.staff.base.BaseActivity;
import com.wetmarket.staff.databinding.ActivitySearchandFilterBinding;
import com.wetmarket.staff.retrofit.model.OrderStatusModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class SearchandFilterActivity extends BaseActivity implements View.OnClickListener {
    private final Calendar startDate = Calendar.getInstance();
    ActivitySearchandFilterBinding binding;
    OrderStatusModel orderStatusModel;
    private String date;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchandFilterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.setLable(lableModel);
        binding.ivBack.setOnClickListener(this);
        binding.tvSelectDate.setOnClickListener(this);
        binding.tvSelectStatus.setOnClickListener(this);
        binding.tvClear.setOnClickListener(this);
        binding.btnSave.setOnClickListener(this);
        setSpinner(binding.spStatus);
        binding.spStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0) {
                    orderStatusModel = (OrderStatusModel) binding.spStatus.getSelectedItem();
                    binding.tvSelectStatus.setText(orderStatusModel.getStatus_name());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        if (StringUtils.isNotEmpty(MainActivity.search_date)) {
            date = MainActivity.search_date;
            binding.tvSelectDate.setText(date);
        }
        binding.edtText.setText(MainActivity.search_text);
    }

    private void openDatePicker() {


        DatePickerDialog startpickerDialog = new DatePickerDialog(SearchandFilterActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        startDate.set(Calendar.YEAR, year);
                        startDate.set(Calendar.MONTH, monthOfYear);
                        startDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        date = simpleDateFormat.format(startDate.getTime());
                        binding.tvSelectDate.setText(date);
                        //binding.tvStartDate.setText(DateUtils.changeDateFormat(DateUtils.DATE_FORMATE_8, DateUtils.DATE_FORMATE_1, sDate, false));*/
                    }
                }, startDate.get(Calendar.YEAR), startDate.get(Calendar.MONTH), startDate.get(Calendar.DAY_OF_MONTH));

        startpickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
        startpickerDialog.show();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_select_date) {
            openDatePicker();
        }
        if (v.getId() == R.id.tv_select_status) {
            binding.spStatus.performClick();
        }
        if (v.getId() == R.id.ivBack) {
            onBackPressed();
        }
        if (v.getId() == R.id.tv_clear) {

            MainActivity.search_status = "";
            MainActivity.search_date = "";
            MainActivity.search_text = "";
            finish();
        }
        if (v.getId() == R.id.btnSave) {
            MainActivity.search_text = binding.edtText.getText().toString();
            MainActivity.search_date = date;
            if (orderStatusModel != null) {
                MainActivity.search_status = orderStatusModel.get_id();
            } else {
                MainActivity.search_status = "";
            }
            finish();
        }

    }

    private void setSpinner(AppCompatSpinner spinner) {

        List<OrderStatusModel> list = new MyPref(SearchandFilterActivity.this).getOrderStatus().getResult();
        OrderStatusModel orderStatusModel = new OrderStatusModel();
        orderStatusModel.set_id("0");
        orderStatusModel.setStatus_name("Select");
        list.add(0, orderStatusModel);
        ArrayAdapter<OrderStatusModel> arrayAdapter = new ArrayAdapter<OrderStatusModel>(SearchandFilterActivity.this, R.layout.spinner_row, list) {
            @Override
            public boolean isEnabled(int position) {
                return position != 0;
            }

            @Override
            public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
                View spinnerView = super.getDropDownView(position, convertView, parent);
                TextView spinnerTextV = (TextView) spinnerView;
                spinnerTextV.setTextColor(Color.parseColor("#b2000000"));
                if (position == 0) {
                    spinnerTextV.setTextColor(Color.parseColor("#a7a7a6"));
                } else {
                    spinnerTextV.setTextColor(Color.parseColor("#b2000000"));
                }
                return spinnerView;
            }
        };


        arrayAdapter.setDropDownViewResource(R.layout.spinner_drop_down);

        spinner.setAdapter(arrayAdapter);
        if (StringUtils.isNotEmpty(MainActivity.search_status)) {
            for (int position = 0; position < arrayAdapter.getCount(); position++) {
                if (arrayAdapter.getItem(position).get_id().equalsIgnoreCase(MainActivity.search_status)) {
                    spinner.setSelection(position);
                    return;
                }
            }
        }
    }

}