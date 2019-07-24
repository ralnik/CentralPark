package ru.ralnik.myLibrary.CustomSeekbarRange;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.util.Random;

import io.apptik.widget.MultiSlider;
import ru.ralnik.centralpark.R;
import ru.ralnik.myseekbarrange.SeekbarRange;
import ru.ralnik.myseekbarrange.interfaces.OnSeekbarRangeChangeListener;

public class SeekbarRangeAdvance extends LinearLayout {

    private LinearLayout ll_for_edits;
    private myEditText editMin;
    private myEditText editMax;
    private ru.ralnik.myseekbarrange.SeekbarRange seekbar;
//    private MultiSlider.Thumb thumb1;
//    private MultiSlider.Thumb thumb2;

    //   ******* ATTRIBUTES ***************
    private int sbr_absoluteMinValue, sbr_absoluteMaxValue;
    private int sbr_textSizeOfEdits;
    private int sbr_textColorOfEdits;
    private Drawable sbr_bgSeekbarRange;
    private Drawable sbr_bgEditText;
    private int sbr_barColor;
    private int sbr_barHighlightColor;
    private Drawable sbr_left_thumb_image;
    private Drawable sbr_left_thumb_image_pressed;
    private Drawable sbr_right_thumb_image;
    private Drawable sbr_right_thumb_image_pressed;
    private int sbr_edits_ems;
    private boolean sbr_show_edits;
    private int sbr_dataType;


    public SeekbarRangeAdvance(Context context) {
        super(context);
        init(context);
    }

    public SeekbarRangeAdvance(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.SeekbarRange);
        try {
//            cornerRadius           = getCornerRadius(array);
//            minStartValue          = getMinStartValue(array);
//            maxStartValue          = getMaxStartValue(array);
//            steps                  = getSteps(array);
//            gap                    = getGap(array);
//            fixGap                 = getFixedGap(array);
//            leftThumbColorNormal   = getLeftThumbColor(array);
//            rightThumbColorNormal  = getRightThumbColor(array);
//            leftThumbColorPressed  = getLeftThumbColorPressed(array);
//            rightThumbColorPressed = getRightThumbColorPressed(array);
            this.sbr_textSizeOfEdits = typedArray.getInt(R.styleable.SeekbarRange_sbr_textSizeOfEdits, 8);
            this.sbr_textColorOfEdits = typedArray.getColor(R.styleable.SeekbarRange_sbr_textColorOfEdits, Color.BLACK);
            this.sbr_absoluteMinValue = typedArray.getInt(R.styleable.SeekbarRange_sbr_absoluteMinValue, 0);
            this.sbr_absoluteMaxValue = typedArray.getInt(R.styleable.SeekbarRange_sbr_absoluteMaxValue, 100);
            this.sbr_bgEditText = typedArray.getDrawable(R.styleable.SeekbarRange_sbr_bgEdits);
            this.sbr_bgSeekbarRange = typedArray.getDrawable(R.styleable.SeekbarRange_sbr_bgSeekbarRange);
            this.sbr_barColor = typedArray.getColor(R.styleable.SeekbarRange_sbr_barColor, Color.GRAY);
            this.sbr_barHighlightColor = typedArray.getDrawable(R.styleable.sbr_barHighlightColor);
            this.sbr_left_thumb_image = typedArray.getDrawable(R.styleable.SeekbarRange_sbr_left_thumb_image);
            this.sbr_right_thumb_image = typedArray.getDrawable(R.styleable.SeekbarRange_sbr_right_thumb_image);
            this.sbr_left_thumb_image_pressed = typedArray.getDrawable(R.styleable.SeekbarRange_sbr_left_thumb_image_pressed);
            this.sbr_right_thumb_image_pressed = typedArray.getDrawable(R.styleable.SeekbarRange_sbr_right_thumb_image_pressed);
            this.sbr_edits_ems = typedArray.getInt(R.styleable.SeekbarRange_sbr_edits_ems, 4);
            this.sbr_show_edits = typedArray.getBoolean(R.styleable.SeekbarRange_sbr_show_edits, true);
            this.sbr_dataType = typedArray.getInt(R.styleable.SeekbarRange_sbr_data_type, 0);
        } finally {
            typedArray.recycle();
        }

        init(context);
    }

    private void init(Context context) {
        setOrientation(VERTICAL);
        seekbar = new SeekbarRange(context);

        ll_for_edits = new LinearLayout(context);
        ll_for_edits.setOrientation(HORIZONTAL);

        addView(seekbar);
        addView(ll_for_edits);

        editMin = new myEditText(context);
        editMin.setText("0");

        editMax = new myEditText(context);
        editMax.setText("100");

        //Устанавливаем размер шрифта
        editMin.setSBR_textSize(sbr_textSizeOfEdits);
        editMax.setSBR_textSize(sbr_textSizeOfEdits);

        //Устанавливаем цвет шрифта
        editMin.setSBR_TextColor(sbr_textColorOfEdits);
        editMax.setSBR_TextColor(sbr_textColorOfEdits);

        //задний фон
        editMin.setBackground(sbr_bgEditText);
        editMax.setBackground(sbr_bgEditText);

        editMin.setEms(sbr_edits_ems);
        editMax.setEms(sbr_edits_ems);


        FrameLayout fl = new FrameLayout(context);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.weight = 1;
        fl.setLayoutParams(layoutParams);
        setLayoutParams(layoutParams);

        ll_for_edits.addView(editMin);
        ll_for_edits.addView(fl);
        ll_for_edits.addView(editMax);

        //отображение едитов
        setShowEdits(sbr_show_edits);

        //Устанавливаем абсолютные значения seekbar-range
        seekbar.setMin(sbr_absoluteMinValue);
        seekbar.setMax(sbr_absoluteMaxValue);


        //Задний фон
        seekbar.setBackground(sbr_bgSeekbarRange);

        //Цвет выделенного
        seekbar.setBarHighlightColor(sbr_barHighlightColor);

        //thumb
        seekbar.setThumbLeftBitmap(sbr_left_thumb_image);
        seekbar.setThumbRightBitmap(sbr_right_thumb_image);

        //thumb default position
        seekbar.setAbsoluteMinValue(sbr_absoluteMinValue);
        seekbar.setAbsoluteMaxValue(sbr_absoluteMaxValue);
        editMin.setText(seekbar.getMin() + "");
        editMax.setText(seekbar.getMax() + "");

        seekbar.setSeekBarChangeListener(new OnSeekbarRangeChangeListener() {
            @Override
            public void SeekbarRangeValueChanged(float Thumb1Value, float Thumb2Value) {
                editMax.setText(Thumb1Value + "");
                editMax.setText(Thumb2Value + "");
            }
        });

        editMin.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //editMax.setText(keyCode+"");
                if (keyCode == 66) {
                        String result = null ;
                    switch (sbr_dataType){
                        case 0: result = editMin.getText().toString(); break;
                        case 1: result = ((int) Math.floor(Float.parseFloat(editMin.getText().toString()))) + ""; break;
                    }
                    seekbar.setMin(Float.parseFloat(result));
                }
                return false;
            }
        });

        editMax.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //проверка нажатие enter
                if (keyCode == 66) {
                        String result = null ;
                        switch (sbr_dataType){
                            case 0: result = editMax.getText().toString(); break;
                            case 1: result = ((int) Math.floor(Float.parseFloat(editMax.getText().toString()))) + ""; break;
                        }
                    seekbar.setMax(Float.parseFloat(result));
                }
                return false;
            }
        });

    }



        public Number getMinValue() {
            //return thumb1.getValue();
           switch (sbr_dataType){
               case 0: return Integer.parseInt(editMin.getText().toString());
               case 1: return Float.parseFloat(editMin.getText().toString());
            }
            return 0;
        }

        public Number getMaxValue() {
            //return thumb2.getValue();
            switch (sbr_dataType){
                case 0: return Integer.parseInt(editMax.getText().toString());
                case 1: return Float.parseFloat(editMax.getText().toString());
            }
            return 0;
        }

        public Number getAbsoluteMinValue() {
            switch (sbr_dataType){
                case 0: return Integer.parseInt(seekbar.getAbsoluteMinValue() + "");
                case 1: return seekbar.getAbsoluteMinValue();
            }
            return 0;
        }

        public Number getAbsoluteMaxValue() {
            switch (sbr_dataType){
                case 0: return Integer.parseInt(seekbar.getAbsoluteMaxValue() + "");
                case 1: return seekbar.getAbsoluteMaxValue();
            }
            return 0;
        }

        public void setAbsoluteMinValue(Number min) {
            seekbar.setAbsoluteMinValue(Float.parseFloat(min + ""));
        }

        public void setAbsoluteMaxValue(Number max) {
            seekbar.setAbsoluteMaxValue(Float.parseFloat(max + ""));
        }

        public void setMinValue(Number min) {
            seekbar.setMin(Float.parseFloat(min + ""));
        }

        public void setMaxValue(Number max) {
           seekbar.setMax(Float.parseFloat(max + ""));
        }

//        public void setMinValue(float min) {
//            thumb1.setValue((int) Math.floor(min));
//        }
//
//        public void setMaxValue(float max) {
//            thumb2.setValue((int) Math.floor(max));
//        }

        public void setShowEdits(Boolean flag) {
            if (flag == true) {
                ll_for_edits.setVisibility(VISIBLE);
            } else {
                ll_for_edits.setVisibility(GONE);
            }
        }

//        public void setDefaultValue(){
//            setMinValue(seekbar.getMin());
//            setMaxValue(seekbar.getMax());
//        }
}