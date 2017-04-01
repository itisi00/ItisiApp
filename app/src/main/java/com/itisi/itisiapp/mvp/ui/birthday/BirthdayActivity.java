package com.itisi.itisiapp.mvp.ui.birthday;

import android.view.View;
import android.widget.TextView;

import com.itisi.itisiapp.R;
import com.itisi.itisiapp.mvp.model.entity.GankFuLiEntity;
import com.itisi.itisiapp.mvp.ui.base.BaseRxBusActivity;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;

public class BirthdayActivity  extends BaseRxBusActivity<BirthdayPresenter> implements BirthdayContract.View,TimePickerDialog.OnTimeSetListener,DatePickerDialog.OnDateSetListener {

    @BindView(R.id.tv_birthday_testdate)
    TextView tv_birthday_testdate;

    @BindView(R.id.tv_birthday_testtime)
    TextView tv_birthday_test;
    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public int getConentlayout() {
        return R.layout.activity_birthday;
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void showContent(List<GankFuLiEntity> list) {

    }

    public void openDate(View view){
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
//        dpd.setVersion(DatePickerDialog.Version.VERSION_2);
        dpd.show(getFragmentManager(), "Datepickerdialog");
    }

    public void openTime(View view){
        Calendar now = Calendar.getInstance();
        TimePickerDialog tpd=TimePickerDialog.newInstance(
                this,
                now.HOUR,
                now.MINUTE,
                now.SECOND,
                true
        );

//        tpd.setThemeDark(true); //深色主题
        tpd.dismissOnPause(true);//跟随依附的activity的 onpause onresume 生命周期  重启?
        tpd.show(getFragmentManager(),"timepickerdialog");
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = "You picked the following date: "+dayOfMonth+"/"+(monthOfYear+1)+"/"+year;
        tv_birthday_testdate.setText(date);
    }

    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
        String time = "You picked the following time: "+hourOfDay+":"+minute+":"+second;
        tv_birthday_test.setText(time);
    }
}
