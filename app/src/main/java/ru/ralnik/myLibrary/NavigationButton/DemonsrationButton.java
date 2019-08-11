package ru.ralnik.myLibrary.NavigationButton;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;

import ru.ralnik.centralpark.R;

public class DemonsrationButton extends AppCompatImageView  {

    private Context context;
    private Drawable demo_image;
    private Drawable demo_image_down;
    private boolean demo_status;
    private OnDemonstrationButtonClickListener click;

    public DemonsrationButton(Context context) {
        super(context);
        this.context = context;
        init(context);

    }

    public DemonsrationButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.DemonstrationButton);
        try{
            this.demo_image = typedArray.getDrawable(R.styleable.DemonstrationButton_demo_image);
            this.demo_image_down = typedArray.getDrawable(R.styleable.DemonstrationButton_demo_image_down);
            this.demo_status = typedArray.getBoolean(R.styleable.DemonstrationButton_demo_status, false);
        }
        finally {
            typedArray.recycle();
        }

        init(context);
    }

    private void init(Context context) {
        if(demo_status){
            setBackground(demo_image_down);
        }else{
            setBackground(demo_image);
        }

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setStatus();
                if(click != null)
                    click.onClick(view);
            }
        });
    }


    //Устанавливаем статут, если вылключен, включаем и наоборот
    public void setStatus() {
        if(this.demo_status){
            setBackground(demo_image);
            this.demo_status = false;
        }else{
            setBackground(demo_image_down);
            this.demo_status = true;
        }
    }

    //Устанавливаем статут в ручную, т.е. если flag=true, значит посылаем команду включить и flag=false - значит выключить
    public void setStatus(Boolean flag) {
        //статус принимает значение, которое мы ему даем
        this.demo_status = flag;
        if(flag) {
            setBackground(demo_image_down);
        }else {
            setBackground(demo_image);
        }

    }

    public Boolean getStatus(){
        return this.demo_status;
    }


    public void setOnDemonstrationButtonClickListener(OnDemonstrationButtonClickListener click){
        this.click = click;
    }


}
