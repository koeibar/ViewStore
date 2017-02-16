package com.damai_tech.myviewstore.customview;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by Administrator on 2017/1/13.
 */

public class MyView extends EditText {
    private final static int LENGTH=10;
    private String[] mHistories=new String[LENGTH];
    private int mIndex=0;

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public Parcelable onSaveInstanceState() {
        Parcelable superState=super.onSaveInstanceState();
        SaveState ss=new SaveState(superState);
        ss.histories=mHistories;
        ss.index=mIndex;
        return ss;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        SaveState ss= (SaveState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        setHistories(ss.histories);
        setIndex(ss.index);
    }

    public void setHistories(String[] histories) {
        this.mHistories = histories;
    }

    public void addHistoryText(String s) {
        mHistories[mIndex]=s;
        mIndex=(++mIndex)%LENGTH;
    }

    public void setIndex(int index) {
        this.mIndex = index;
    }

    public String getHistories() {
        StringBuilder stringBuilder=new StringBuilder(LENGTH*10);
        for (int i = 0; i < LENGTH; i++) {
            if (!TextUtils.isEmpty(mHistories[i]))
            {
                stringBuilder.append(mHistories[i]);
                stringBuilder.append("//n");
            }
        }
        return stringBuilder.toString();
    }

    static class SaveState extends BaseSavedState{
        String[] histories;
        int index;

        public SaveState(Parcel source) {
            super(source);
            source.readStringArray(histories);
            index=source.readInt();
        }

        public SaveState(Parcelable superState) {
            super(superState);
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeStringArray(histories);
            out.writeInt(index);
        }

        public static final Parcelable.Creator<SaveState>CREATOR= new Creator<SaveState>() {
            @Override
            public SaveState createFromParcel(Parcel source) {
                return new SaveState(source);
            }

            @Override
            public SaveState[] newArray(int size) {
                return new SaveState[size];
            }
        };
    }
}
