package com.example.palibinfamily.weatheragregator.View.MainActivity.Activities.CustomViews;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;


import com.example.palibinfamily.weatheragregator.Model.WeatherSnapshot;
import com.example.palibinfamily.weatheragregator.R;
import com.example.palibinfamily.weatheragregator.View.MainActivity.Activities.ViewHelpers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class DaysBar extends View {
    private final String TAG = "DaysBar";
    private ViewPager viewPager;
    private int width;
    private int height;
    private int position = 0;
    private HashMap<ViewHelpers.WeatherType,Drawable> smallIcons = new HashMap<>();
    private ArrayList<WeatherSnapshot> weatherDtata;
    private Paint paintSmallBlur = new Paint();
    private Paint paint = new Paint();

    public ArrayList<WeatherSnapshot> getWeatherDtata() {
        return weatherDtata;
    }

    public void setWeatherDtata(ArrayList<WeatherSnapshot> weatherDtata) {
        this.weatherDtata = weatherDtata;
        this.postInvalidate();
    }

    public ViewPager getViewPager() {
        return viewPager;
    }

    public void setViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
    }


    public void setPosition(int position) {
        this.position = position;
        Log.d(TAG,"position : " + position);
        this.postInvalidate();
    }

    private void loadIcons(){
        smallIcons.put(ViewHelpers.WeatherType.clear, ContextCompat.getDrawable(getContext(), R.drawable.ic_weather_clear));
        smallIcons.put(ViewHelpers.WeatherType.few_clouds, ContextCompat.getDrawable(getContext(), R.drawable.ic_weather_few_clouds));
        smallIcons.put(ViewHelpers.WeatherType.storm, ContextCompat.getDrawable(getContext(), R.drawable.ic_weather_storm));
        smallIcons.put(ViewHelpers.WeatherType.snow, ContextCompat.getDrawable(getContext(), R.drawable.ic_weather_snow));

        paintSmallBlur.setShadowLayer(4, 1, 1, Color.BLACK);
        setLayerType(LAYER_TYPE_SOFTWARE, paintSmallBlur);
    }

    public DaysBar(Context context) {
        super(context);
        loadIcons();
    }

    public DaysBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        loadIcons();
    }

    public DaysBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        loadIcons();
    }

    private void setTextSizeForWidth(Paint paint, float desiredWidth, String text) {

        // Pick a reasonably large value for the test. Larger values produce
        // more accurate results, but may cause problems with hardware
        // acceleration. But there are workarounds for that, too; refer to
        // http://stackoverflow.com/questions/6253528/font-size-too-large-to-fit-in-cache
        final float testTextSize = 48f;

        // Get the bounds of the text, using our testTextSize.
        paint.setTextSize(testTextSize);
        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);

        // Calculate the desired size as a proportion of our testTextSize.
        float desiredTextSize = testTextSize * desiredWidth / bounds.width();

        // Set the paint for that size.
        paint.setTextSize(desiredTextSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        width = canvas.getWidth();
        height = canvas.getHeight();

//        paint.setColor(0x44000000);
//        canvas.drawRect(0,0,width, height, paint);

        paint.setColor(0x99000000);
        //canvas.drawRect((width / 7) * (position),0,(width / 7) * (position + 1),height - 10, paint);
        canvas.drawRect(0,0,(width / 7) * (position ),height, paint);
        canvas.drawRect((width / 7) * (position + 1),0,(width ),height, paint);
        Resources res = getResources();
        if (weatherDtata != null) {
            for (int i = 0; i < 7; i++) {
                WeatherSnapshot snap = null;
                if (i < weatherDtata.size()) {
                    snap = weatherDtata.get(i);
                }
                if (snap != null) {
                    Drawable dr = smallIcons.get(ViewHelpers.WeatherType.clear);

                    if (snap.isSnowing()) {
                        dr = smallIcons.get(ViewHelpers.WeatherType.snow);
                    }
                    if (snap.isRaining()) {
                        dr = smallIcons.get(ViewHelpers.WeatherType.storm);
                    }

                    dr.setBounds((width / 7) * (i), 0, (width / 7) * (i + 1), 100);
                    dr.draw(canvas);
                    int dayOfWeek = 7;
                    int date = -1;
                    if (snap.getDate() != null) {
                        Log.d(TAG, "onDraw: " + snap.getDate().toLocaleString());
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(snap.getDate());
                        calendar.setFirstDayOfWeek(Calendar.MONDAY);
                        dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
                        date = calendar.getTime().getDate();
                    } else {
                        Log.d(TAG, "onDraw: snap.getDate() == null ????????????????");
                    }
                    paint.setColor(0xFFFFFFFF);
                    paintSmallBlur.setColor(0xFF000000);

                    setTextSizeForWidth(paint, (width / 14), "12");
                    setTextSizeForWidth(paintSmallBlur, (width / 14), "12");

                    int temperature = snap.getTemperature();

                    if ((temperature > -100)&&(temperature < 100)) {

                        canvas.drawText("" + temperature, (width / 7) * (i) + 25, (float) (height * 0.45), paintSmallBlur);
                        canvas.drawText("" + temperature, (width / 7) * (i) + 25, (float) (height * 0.45), paint);
                    }
                    canvas.drawText("" + ViewHelpers.days[dayOfWeek], (width / 7) * (i) + (width / 28), (float) (height * 0.65), paintSmallBlur);
                    canvas.drawText("" + ViewHelpers.days[dayOfWeek], (width / 7) * (i) + (width / 28), (float) (height * 0.65), paint);

                    canvas.drawText("" + date, (width / 7) * (i) + 25, (float) (height * 0.85), paintSmallBlur);
                    canvas.drawText("" + date, (width / 7) * (i) + 25, (float) (height * 0.85), paint);
                }
            }
        }
    }

    private int measureDimension(int desiredSize, int measureSpec) {
        int result;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = desiredSize;
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }

        if (result < desiredSize){
            Log.e("ChartView", "The view is too small, the content might get cut");
        }
        return result;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d(TAG,"width : ");
        Log.v("Chart onMeasure w", MeasureSpec.toString(widthMeasureSpec));
        Log.v("Chart onMeasure h", MeasureSpec.toString(heightMeasureSpec));

        int desiredWidth = getSuggestedMinimumWidth() + getPaddingLeft() + getPaddingRight();
        int desiredHeight = getSuggestedMinimumHeight() + getPaddingTop() + getPaddingBottom();

        setMeasuredDimension(measureDimension(desiredWidth, widthMeasureSpec), (int) (0.416 * measureDimension(desiredWidth, widthMeasureSpec)));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float pointX = event.getX();
        float pointY = event.getY();
        int action = MotionEventCompat.getActionMasked(event);
        switch(action) {
            case (MotionEvent.ACTION_DOWN) :
                Log.d("s", "Action was DOWN");
                int screenNumber = (int) pointX / (width / 7);
                viewPager.setCurrentItem(screenNumber);
                break;
            case (MotionEvent.ACTION_MOVE) :
                Log.d("s","Action was MOVE");
                break;
            case (MotionEvent.ACTION_UP) :
                Log.d("s", "Action was UP");
                break;
            case (MotionEvent.ACTION_CANCEL) :
                Log.d("s","Action was CANCEL");
                break;
            case (MotionEvent.ACTION_OUTSIDE) :
                Log.d("s","Movement occurred outside bounds " +
                        "of current screen element");
                break;
        }
        this.postInvalidate();
        return true;
    }

}
