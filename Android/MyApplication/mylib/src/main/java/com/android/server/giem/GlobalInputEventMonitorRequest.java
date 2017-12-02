
package com.android.server.giem;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;

public class GlobalInputEventMonitorRequest implements Parcelable {

    public enum InputEventType implements Parcelable {
        KEY_EVENT,
        MOTION_EVENT;

        public static final Parcelable.Creator<InputEventType> CREATOR = new Parcelable.Creator<InputEventType>() {
            public InputEventType createFromParcel(Parcel in) {
                return InputEventType.values()[in.readInt()];
            }

            public InputEventType[] newArray(int size) {
                return new InputEventType[size];
            }
        };

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(ordinal());
        }
    };

    public enum Action {
        PROCESS_AS_USUAL,
        FILTER_SEND_INTENT,
        PROCESS_SEND_INTENT,
        FILTER_KEEP_MONITOR;

        public static final Parcelable.Creator<Action> CREATOR = new Parcelable.Creator<Action>() {
            public Action createFromParcel(Parcel in) {
                return Action.values()[in.readInt()];
            }

            public Action[] newArray(int size) {
                return new Action[size];
            }
        };

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(ordinal());
        }
    };

    public static class RequestItem implements Parcelable {
        public InputEventType mEventType;
        public int mKeyCode;
        public Action mAction;
        public Intent mIntent;

        public RequestItem() {
        }

        public String toString(){
            StringBuilder sb = new StringBuilder(getClass().getSimpleName());
            sb.append("[");
            sb.append("mEventType=").append(mEventType);
            sb.append(";");
            sb.append("mKeyCode=").append(mKeyCode);
            sb.append(";");
            sb.append("mAction=").append(mAction);
            sb.append(";");
            sb.append("mIntent=").append(mIntent);
            sb.append("]");
            return sb.toString();
        }

        private RequestItem(Parcel in) {
            mEventType = InputEventType.CREATOR.createFromParcel(in);
            mKeyCode = in.readInt();
            mAction = Action.CREATOR.createFromParcel(in);
            if (in.readInt() == 1) {
                mIntent = Intent.CREATOR.createFromParcel(in);
            } else {
                mIntent = null;
            }
        }

        public RequestItem(InputEventType type, int keyCode, Action action, Intent intent) {
            mEventType = type;
            mKeyCode = keyCode;
            mAction = action;
            mIntent = intent;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            mEventType.writeToParcel(out, flags);
            out.writeInt(mKeyCode);
            mAction.writeToParcel(out, flags);
            if (mIntent != null) {
                out.writeInt(1);
                mIntent.writeToParcel(out, flags);
            } else {
                out.writeInt(0);
            }
        }

        public static final Parcelable.Creator<RequestItem> CREATOR = new Parcelable.Creator<RequestItem>() {
            public RequestItem createFromParcel(Parcel in) {
                return new RequestItem(in);
            }

            public RequestItem[] newArray(int size) {
                return new RequestItem[size];
            }
        };
    }

    public enum RequestType implements Parcelable {
        MONITOR_EVENT,
        MONITOR_TIMEOUT;

        public static final Parcelable.Creator<RequestType> CREATOR = new Parcelable.Creator<RequestType>() {
            public RequestType createFromParcel(Parcel in) {
                return RequestType.values()[in.readInt()];
            }

            public RequestType[] newArray(int size) {
                return new RequestType[size];
            }
        };

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(ordinal());
        }
    }

    final private RequestType mRequestType;

    private long mTimeout;
    private long mWarningOffset;
    private Intent mTimeoutIntent;
    private Intent mWarningIntent;
    private Intent mWarningCancelIntent;
    private boolean mInWarningPeriod;
    private RequestItem[] mRequestItems;
    private Boolean mNeedMonitorMotionEvent;
    private Boolean mNeedMonitorKeyEvent;

    public String toString() {
        StringBuilder sb = new StringBuilder(getClass().getSimpleName());
        sb.append("[");
        sb.append("mRequestType=").append(mRequestType).append(";");
        sb.append("mTimeoutIntent=").append(mTimeoutIntent).append(";");
        sb.append("mWarningIntent=").append(mWarningIntent).append(";");
        sb.append("mWarningCancelIntent=").append(mWarningCancelIntent).append(";");
        if (mRequestItems != null) {
            sb.append("\n");
            for (int i=0; i < mRequestItems.length; i++) {
                sb.append("mRequestItems[").append(i).append("]=").append(mRequestItems[i]);
                sb.append("\n");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public RequestItem[] getRequestItems() {
        return mRequestItems;
    }

    public void setRequestItems(RequestItem[] requestItems) {
        mRequestItems = requestItems;
    }

    public long getTimeout() {
        return mTimeout;
    }

    public void setTimeout(long timeout) {
        mTimeout = timeout;
    }

    public long getWarningOffset() {
        return mWarningOffset;
    }

    public void setWarningOffset(long offset) {
        mWarningOffset = offset;
    }

    public boolean getInWarningPeriod() {
        return mInWarningPeriod;
    }

    public void setInWarningPeriod(boolean inWarningPeriod) {
        mInWarningPeriod = inWarningPeriod;
    }

    public RequestType getRequestType() {
        return mRequestType;
    }

    public GlobalInputEventMonitorRequest(RequestType requestType) {
        mRequestType = requestType;
        mTimeout = 0;
        mWarningOffset = 0;
        mInWarningPeriod = false;
    }

    public static final Parcelable.Creator<GlobalInputEventMonitorRequest> CREATOR = new Parcelable.Creator<GlobalInputEventMonitorRequest>() {
        public GlobalInputEventMonitorRequest createFromParcel(Parcel in) {
            GlobalInputEventMonitorRequest result = new GlobalInputEventMonitorRequest(
                    RequestType.CREATOR.createFromParcel(in));
            switch (result.getRequestType()) {
                case MONITOR_TIMEOUT:
                    result.setTimeout(in.readLong());
                    result.setWarningOffset(in.readLong());
                    if (in.readInt() == 1) {
                        result.mTimeoutIntent = Intent.CREATOR.createFromParcel(in);
                    } else {
                        result.mTimeoutIntent = null;
                    }
                    if (in.readInt() == 1) {
                        result.mWarningIntent = Intent.CREATOR.createFromParcel(in);
                    } else {
                        result.mWarningIntent = null;
                    }
                    if (in.readInt() == 1) {
                        result.mWarningCancelIntent = Intent.CREATOR.createFromParcel(in);
                    } else {
                        result.mWarningCancelIntent = null;
                    }
                    break;

                case MONITOR_EVENT:
                    int count = in.readInt();
                    RequestItem[] items = new RequestItem[count];
                    for (int i = 0; i < count; i++) {
                        items[i] = RequestItem.CREATOR.createFromParcel(in);
                    }
                    result.mRequestItems = items;
                    break;

                default:
                    break;
            }
            return result;
        }

        public GlobalInputEventMonitorRequest[] newArray(int size) {
            return new GlobalInputEventMonitorRequest[size];
        }
    };

    public int describeContents() {
        return 0;
    }

    protected void decideMonitorType() {
        switch (mRequestType) {
            case MONITOR_TIMEOUT:
                mNeedMonitorKeyEvent = mNeedMonitorMotionEvent = true;
                break;

            case MONITOR_EVENT:
                mNeedMonitorKeyEvent = mNeedMonitorMotionEvent = false;
                for (RequestItem item : mRequestItems) {
                    if (item.mEventType == InputEventType.KEY_EVENT) {
                        mNeedMonitorKeyEvent = true;
                    } else if (item.mEventType == InputEventType.MOTION_EVENT) {
                        mNeedMonitorMotionEvent = true;
                    }
                }
                break;

            default:
                break;
        }
    }

    public void writeToParcel(Parcel dest, int flags) {
        mRequestType.writeToParcel(dest, flags);
        switch (mRequestType) {
            case MONITOR_TIMEOUT:
                dest.writeLong(mTimeout);
                dest.writeLong(mWarningOffset);
                if (mTimeoutIntent == null){
                    dest.writeInt(0);
                } else{
                    dest.writeInt(1);
                    mTimeoutIntent.writeToParcel(dest, flags);
                }
                if (mWarningIntent == null){
                    dest.writeInt(0);
                } else{
                    dest.writeInt(1);
                    mWarningIntent.writeToParcel(dest, flags);
                }
                if (mWarningCancelIntent == null){
                    dest.writeInt(0);
                } else{
                    dest.writeInt(1);
                    mWarningCancelIntent.writeToParcel(dest, flags);
                }
                break;

            case MONITOR_EVENT:
                dest.writeInt(mRequestItems.length);
                for (RequestItem item : mRequestItems) {
                    item.writeToParcel(dest, flags);
                }
                break;

            default:
                break;
        }
    }

    public boolean needMonitorKeyEvent() {
        if (mNeedMonitorKeyEvent == null) {
            decideMonitorType();
        }
        return mNeedMonitorKeyEvent;
    }

    public boolean needMonitorMotionEvent() {
        if (mNeedMonitorMotionEvent == null) {
            decideMonitorType();
        }
        return mNeedMonitorMotionEvent;
    }

    public Intent getTimeoutIntent() {
        return mTimeoutIntent;
    }

    public void setTimeoutIntent(Intent intent) {
        mTimeoutIntent = intent;
    }

    public Intent getWarningIntent() {
        return mWarningIntent;
    }

    public void setWarningIntent(Intent intent) {
        mWarningIntent = intent;
    }

    public Intent getWarningCancelIntent() {
        return mWarningCancelIntent;
    }

    public void setWarningCancelIntent(Intent intent) {
        mWarningCancelIntent = intent;
    }
}
