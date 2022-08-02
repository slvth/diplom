package com.example.lifestyle.UI.Workout.Custom;

import android.os.SystemClock;
import android.view.View;
import android.widget.AdapterView;

public class DebounceClickListener implements View.OnClickListener, AdapterView.OnItemClickListener {

    private static final long DEBOUNCE_INTERVAL_DEFAULT = 750;
    private long debounceInterval;
    private long lastClickTime;
    private View.OnClickListener clickListener;
    private AdapterView.OnItemClickListener clickListener2;

    public DebounceClickListener(final View.OnClickListener clickListener) {
        this(clickListener, DEBOUNCE_INTERVAL_DEFAULT);
    }

    public DebounceClickListener(final View.OnClickListener clickListener, final long debounceInterval) {
        this.clickListener = clickListener;
        this.debounceInterval = debounceInterval;
    }

    public DebounceClickListener(final AdapterView.OnItemClickListener clickListener) {
        this(clickListener, DEBOUNCE_INTERVAL_DEFAULT);
    }

    public DebounceClickListener(final AdapterView.OnItemClickListener clickListener,final long debounceIntervalDefault) {
        this.clickListener2 =  clickListener;
        this.debounceInterval = debounceIntervalDefault;
    }

    @Override
    public void onClick(final View v) {
        if ((SystemClock.elapsedRealtime() - lastClickTime) < debounceInterval) {
            return;
        }
        lastClickTime = SystemClock.elapsedRealtime();
        clickListener.onClick(v);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if ((SystemClock.elapsedRealtime() - lastClickTime) < debounceInterval) {
            return;
        }
        lastClickTime = SystemClock.elapsedRealtime();
        clickListener2.onItemClick(adapterView,view,i,l);
    }
}
