package com.example.androidyingyong;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.TextView;

@SuppressWarnings("unused")
public class Result extends Activity {
    //private TextView result;
    private Button sin;
    private Button cos;
    private Button left;
    private Button right;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result);
		Intent intent=getIntent();
		
		TextView result = (TextView)findViewById(R.id.result);
		
		
		sin=(Button)findViewById(R.id.sin);
		sin.setBackgroundColor(Color.TRANSPARENT);//将按钮设置为透明
		sin.setBackgroundResource(R.drawable.a);
		sin.setTextColor(Color.RED);
		
		
		cos=(Button)findViewById(R.id.cos);
		cos.setBackgroundResource(R.drawable.b);
		cos.setBackgroundColor(Color.TRANSPARENT);	
	    cos.setTextColor(Color.BLUE);
	    
		left=(Button)findViewById(R.id.left);
		left.setBackgroundResource(R.drawable.b);
		left.setBackgroundColor(Color.TRANSPARENT);
		left.setTextColor(Color.RED);
		
		right=(Button)findViewById(R.id.right);
		right.setBackgroundResource(R.drawable.b);
	    right.setBackgroundColor(Color.TRANSPARENT);
	    right.setTextColor(Color.RED);
	    
		//result=(TextView)findViewById(R.id.result);
		//sin的监听器
		sin.setOnClickListener(new Button.OnClickListener(){

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			   /* result.setText("sin");
				Intent intent=new Intent();
				intent.setClass(Result.this, MainActivity.class);
				Result.this.startActivity(intent);*/
			}
			
		});
		sin.setOnTouchListener(new OnTouchListener() {
			
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					sin.setBackgroundResource(R.drawable.d);
				}else {
					if (event.getAction() == MotionEvent.ACTION_UP) {
						sin.setBackgroundColor(Color.TRANSPARENT);
					}
				}
				return false;
			}
		});
		//cos的监听器
		cos.setOnClickListener(new Button.OnClickListener(){

			public void onClick(View v) {
				// TODO Auto-generated method stub
				/*result.setText("cos");
				Intent intent1=new Intent();
				intent1.setClass(Result.this, MainActivity.class);
				Result.this.startActivity(intent1);
				*/
			}
			
		});
		cos.setOnTouchListener(new OnTouchListener() {
			
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					cos.setBackgroundResource(R.drawable.d);
				}else {
					if (event.getAction() == MotionEvent.ACTION_UP) {
						cos.setBackgroundColor(Color.TRANSPARENT);
					}
				}
				return false;
			}
		});
		//点击left，触发的事件
		left.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//result.setText(result.getText()+"(");
			}
		});
		left.setOnTouchListener(new OnTouchListener() {
			
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					left.setBackgroundResource(R.drawable.d);
				}else {
					if (event.getAction() == MotionEvent.ACTION_UP) {
						left.setBackgroundColor(Color.TRANSPARENT);
					}
				}
				return false;
			}
		});
		//点击right触发的事件
		right.setOnClickListener( new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		right.setOnTouchListener(new OnTouchListener() {
			
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
                 if (event.getAction() == MotionEvent.ACTION_DOWN) {
					right.setBackgroundResource(R.drawable.d);
				}else {
					if (event.getAction() == MotionEvent.ACTION_UP) {
						right.setBackgroundColor(Color.TRANSPARENT);
					}
				}
                return false;
			}
		});
	}


}
