package cn.key;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.EditText;

public class KeydemoActivity extends Activity {
	private Context mCtx;
	private Activity mAct;
	private EditText mEdit;
	private EditText mEdit1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mCtx = this;
		mAct = this;

		mEdit = (EditText) this.findViewById(R.id.edit);
		mEdit.setInputType(InputType.TYPE_NULL);

		mEdit1 = (EditText) this.findViewById(R.id.edit1);

		mEdit.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				new KeyboardUtil(mAct, mCtx, mEdit).showKeyboard();
				return false;
			}
		});

		mEdit1.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				int inputback = mEdit1.getInputType();
				mEdit1.setInputType(InputType.TYPE_NULL);
				new KeyboardUtil(mAct, mCtx, mEdit1).showKeyboard();
				mEdit1.setInputType(inputback);
				return false;
			}
		});
	}
}