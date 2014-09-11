package com.example.androidyingyong;

import java.io.IOException;
import java.security.PublicKey;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Stack;

import android.R.color;
import android.R.integer;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.StaticLayout;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

@SuppressWarnings("unused")
public class MainActivity extends Activity implements OnTouchListener,
		OnGestureListener {
	// 声音资源

	// private MediaPlayer ac;
	public static MediaPlayer del;
	public static MediaPlayer dot;
	public static MediaPlayer zero;
	public static MediaPlayer one;
	public static MediaPlayer two;
	public static MediaPlayer three;
	public static MediaPlayer four;
	public static MediaPlayer five;
	public static MediaPlayer six;
	public static MediaPlayer seven;
	public static MediaPlayer eight;
	public static MediaPlayer nine;

	public static MediaPlayer plus;
	public static MediaPlayer minus;
	public static MediaPlayer mul;
	public static MediaPlayer div;

	private MediaPlayer equal;

	// 计算得到的结果
	public static String calResult;
	// 这两个类分别表示触屏监听器和手势监控器
	private double f;
	private int flag = 0, swap = 0, s = 0;
	private Button cal;

	private EditText result;
	private static TextView result2;
	private Button symbol;
	private GestureDetector gestureDetector;// 手指在屏幕上的动作
	private Button left = null;
	private Button right = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);

		// 声音资源
		zero = new MediaPlayer();
		zero = MediaPlayer.create(MainActivity.this, R.raw.zero);
		one = new MediaPlayer();
		one = MediaPlayer.create(MainActivity.this, R.raw.one);
		two = new MediaPlayer();
		two = MediaPlayer.create(MainActivity.this, R.raw.two);
		three = new MediaPlayer();
		three = MediaPlayer.create(MainActivity.this, R.raw.three);
		four = new MediaPlayer();
		four = MediaPlayer.create(MainActivity.this, R.raw.four);
		five = new MediaPlayer();
		five = MediaPlayer.create(MainActivity.this, R.raw.five);
		six = new MediaPlayer();
		six = MediaPlayer.create(MainActivity.this, R.raw.six);
		seven = new MediaPlayer();
		seven = MediaPlayer.create(MainActivity.this, R.raw.seven);
		eight = new MediaPlayer();
		eight = MediaPlayer.create(MainActivity.this, R.raw.eight);
		nine = new MediaPlayer();
		nine = MediaPlayer.create(MainActivity.this, R.raw.nine);

		del = new MediaPlayer();
		del = MediaPlayer.create(MainActivity.this, R.raw.del);

		plus = new MediaPlayer();
		plus = MediaPlayer.create(MainActivity.this, R.raw.plus);
		minus = new MediaPlayer();
		minus = MediaPlayer.create(MainActivity.this, R.raw.minus);
		mul = new MediaPlayer();
		mul = MediaPlayer.create(MainActivity.this, R.raw.mul);
		div = new MediaPlayer();
		div = MediaPlayer.create(MainActivity.this, R.raw.div);

		dot = new MediaPlayer();
		dot = MediaPlayer.create(MainActivity.this, R.raw.dot);
		equal = new MediaPlayer();
		equal = MediaPlayer.create(MainActivity.this, R.raw.equal);

		left = (Button) findViewById(R.id.left);// 左括号
		left.setBackgroundResource(R.drawable.a);
		left.setBackgroundColor(Color.TRANSPARENT);
		left.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String string = new String();
				flag = 0;

				string = result.getText() + "(";
				result.setText(string);
				result.setSelection(string.length());
			}
		});
		// 左括号的监听器
		left.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					left.setBackgroundResource(R.drawable.d);

				} else {
					if (event.getAction() == MotionEvent.ACTION_UP) {
						left.setBackgroundResource(R.drawable.a);
						left.setBackgroundColor(Color.TRANSPARENT);
					}
				}
				return false;
			}
		});
		right = (Button) findViewById(R.id.right);// 右括号
		right.setBackgroundResource(R.drawable.b);
		right.setBackgroundColor(Color.TRANSPARENT);
		right.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				String string = new String();
				flag = 0;

				string = result.getText() + ")";
				result.setText(string);
				result.setSelection(string.length());
			}
		});
		// 右括号的监听器
		right.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					right.setBackgroundResource(R.drawable.a);
				} else {
					right.setBackgroundResource(R.drawable.b);
					right.setBackgroundColor(Color.TRANSPARENT);
				}
				return false;
			}
		});
		// 这是一个触屏事件，在软件里面没有应用到
		gestureDetector = new GestureDetector((OnGestureListener) this);

		// fipper = (ViewFlipper)findViewById(R.id.flipper);
		RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.relativelayout);// 第一个layout
		relativeLayout.setOnTouchListener(this);// 表示该layout的触屏事件由下面的ontouch执行
		relativeLayout.setLongClickable(true);

		// Intent intent=getIntent();
		// 隐藏软键盘
		// getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);//隐藏软键盘

		Button button0 = (Button) findViewById(R.id.button0);
		Button button1 = (Button) findViewById(R.id.button1);
		Button button2 = (Button) findViewById(R.id.button2);
		Button button3 = (Button) findViewById(R.id.button3);
		Button button4 = (Button) findViewById(R.id.button4);
		Button button5 = (Button) findViewById(R.id.button5);
		Button button6 = (Button) findViewById(R.id.button6);
		Button button7 = (Button) findViewById(R.id.button7);
		Button button8 = (Button) findViewById(R.id.button8);
		Button button9 = (Button) findViewById(R.id.button9);
		Button point = (Button) findViewById(R.id.point);

		Button calButton = (Button) findViewById(R.id.calculate);
		Button clearButton = (Button) findViewById(R.id.clear);

		Button symbolButton = (Button) findViewById(R.id.symbol);
		Button addButton = (Button) findViewById(R.id.add);
		Button cutButton = (Button) findViewById(R.id.cut);
		Button devideButton = (Button) findViewById(R.id.devide);
		symbol = (Button) findViewById(R.id.symbol);

		// TextView textview = (TextView) findViewById(R.id.textview1);
		// textview.setBackgroundColor(Color.TRANSPARENT);
		// textview.setTextColor(Color.BLACK);

		result = (EditText) findViewById(R.id.result);
		// String string = result.getText().toString();
		result.setInputType(InputType.TYPE_NULL);// 不弹出软键盘

		result.setBackgroundColor(Color.TRANSPARENT);
		result.setTextColor(Color.RED);

		result2 = (TextView) findViewById(R.id.result2);
		result2.setBackgroundColor(Color.TRANSPARENT);
		result2.setTextColor(Color.RED);

		cal = (Button) findViewById(R.id.calculate);

		button0.setText("0");
		button0.setTextColor(Color.RED);
		// button0.setBackgroundResource(R.drawable.a);
		button0.setBackgroundResource(R.drawable.bihua);
		button0.setBackgroundColor(Color.TRANSPARENT);

		button1.setText("1");
		// button1.setBackgroundResource(R.drawable.a);
		button1.setTextColor(Color.BLUE);// 按钮中字体的颜色
		button1.setBackgroundResource(R.drawable.bihua);
		button1.setBackgroundColor(Color.TRANSPARENT);// 将按钮设置为透明

		button2.setText("2");
		// button2.setBackgroundResource(R.drawable.a);
		button2.setTextColor(Color.BLUE);
		button2.setBackgroundResource(R.drawable.bihua);
		button2.setBackgroundColor(Color.TRANSPARENT);

		button3.setText("3");
		// button3.setBackgroundResource(R.drawable.a);
		button3.setTextColor(Color.DKGRAY);
		button3.setBackgroundResource(R.drawable.bihua);
		button3.setBackgroundColor(Color.TRANSPARENT);

		button4.setText("4");
		// button4.setBackgroundResource(R.drawable.a);
		button4.setTextColor(Color.GREEN);
		button4.setBackgroundResource(R.drawable.bihua);
		button4.setBackgroundColor(Color.TRANSPARENT);

		button5.setText("5");
		button5.setTextColor(Color.YELLOW);
		// button5.setBackgroundResource(R.drawable.a);
		button5.setBackgroundResource(R.drawable.bihua);
		button5.setBackgroundColor(Color.TRANSPARENT);

		button6.setText("6");
		button6.setTextColor(Color.LTGRAY);
		// button6.setBackgroundResource(R.drawable.a);
		button6.setBackgroundResource(R.drawable.bihua);
		button6.setBackgroundColor(Color.TRANSPARENT);

		button7.setText("7");
		button7.setTextColor(Color.BLUE);
		// button7.setBackgroundResource(R.drawable.a);
		button7.setBackgroundResource(R.drawable.bihua);
		button7.setBackgroundColor(Color.TRANSPARENT);

		button8.setText("8");
		button8.setTextColor(Color.GRAY);
		// button8.setBackgroundResource(R.drawable.a);
		button8.setBackgroundResource(R.drawable.bihua);
		button8.setBackgroundColor(Color.TRANSPARENT);

		button9.setText("9");
		button9.setTextColor(Color.BLUE);
		// button9.setBackgroundResource(R.drawable.a);
		button9.setBackgroundResource(R.drawable.bihua);
		button9.setBackgroundColor(Color.TRANSPARENT);

		// point.setBackgroundResource(R.drawable.a);
		point.setTextColor(Color.BLUE);
		point.setBackgroundResource(R.drawable.bihua);
		point.setBackgroundColor(Color.TRANSPARENT);

		// clearButton.setBackgroundResource(R.drawable.a);
		clearButton.setTextColor(Color.GRAY);
		clearButton.setBackgroundResource(R.drawable.bihua);
		clearButton.setBackgroundColor(Color.TRANSPARENT);

		// calButton.setBackgroundResource(R.drawable.a);
		calButton.setTextColor(Color.BLUE);
		calButton.setBackgroundResource(R.drawable.bihua);
		calButton.setBackgroundColor(Color.TRANSPARENT);

		// symbolButton.setBackgroundResource(R.drawable.a);
		symbolButton.setTextColor(Color.GREEN);
		symbolButton.setBackgroundResource(R.drawable.bihua);
		symbolButton.setBackgroundColor(Color.TRANSPARENT);

		// addButton.setBackgroundResource(R.drawable.a);
		addButton.setTextColor(Color.RED);
		addButton.setBackgroundResource(R.drawable.bihua);
		addButton.setBackgroundColor(Color.TRANSPARENT);

		// cutButton.setBackgroundResource(R.drawable.a);
		cutButton.setTextColor(Color.BLUE);
		cutButton.setBackgroundResource(R.drawable.bihua);
		cutButton.setBackgroundColor(Color.TRANSPARENT);

		// devideButton.setBackgroundResource(R.drawable.a);
		devideButton.setTextColor(Color.RED);
		devideButton.setBackgroundResource(R.drawable.bihua);
		devideButton.setBackgroundColor(Color.TRANSPARENT);

		symbol.setText(R.string.symbol);
		// textview.setText(R.string.result);
		cal.setText(R.string.calculate);
		// 点击1，触发的事件
		button1.setOnClickListener(new Button.OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (one != null) {
					one.stop();
				}
				try {
					one.prepare();
					one.start();
				} catch (IOException e) {
					// TODO: handle exception
				}
				String string = new String();

				flag = 0;
				string = result.getText() + "1";
				result.setText(string);
				result.setSelection(string.length());

			}

		});

		button1.setOnTouchListener(new Button.OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					v.setBackgroundResource(R.drawable.b);
					// Log.i("TestAndroid Button", "MotionEvent.ACTION_DOWN");
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					v.setBackgroundResource(R.drawable.a);
					v.setBackgroundColor(Color.TRANSPARENT);
					// Log.i("TestAndroid Button", "MotionEvent.ACTION_UP");
				}
				return false;
			}

		});
		// button1.setBackgroundResource(R.drawable.b);
		// 点击2，触发的事件
		button2.setOnClickListener(new Button.OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (two != null) {
					two.stop();
				}
				try {
					two.prepare();
					two.start();
				} catch (IOException e) {
					// TODO: handle exception
				}
				String string = new String();
				flag = 0;

				string = result.getText() + "2";
				result.setText(string);
				result.setSelection(string.length());

			}

		});

		button2.setOnTouchListener(new Button.OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					v.setBackgroundResource(R.drawable.b);
				} else {
					if (event.getAction() == MotionEvent.ACTION_UP) {
						v.setBackgroundResource(R.drawable.a);
						v.setBackgroundColor(Color.TRANSPARENT);
					}
				}
				return false;
			}

		});
		// 点击3，触发的事件
		button3.setOnClickListener(new Button.OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (three != null) {
					three.stop();
				}
				try {
					three.prepare();
					three.start();
				} catch (IOException e) {
					// TODO: handle exception
				}
				String string = new String();

				flag = 0;

				string = result.getText() + "3";
				result.setText(string);
				result.setSelection(string.length());
				// }

			}

		});

		button3.setOnTouchListener(new Button.OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					v.setBackgroundResource(R.drawable.b);

				} else {
					if (event.getAction() == MotionEvent.ACTION_UP) {
						v.setBackgroundResource(R.drawable.a);
						v.setBackgroundColor(Color.TRANSPARENT);
					}
				}
				return false;
			}

		});
		// 点击4，触发的事件
		button4.setOnClickListener(new Button.OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (four != null) {
					four.stop();
				}
				try {
					four.prepare();
					four.start();
				} catch (IOException e) {
					// TODO: handle exception
				}
				String string = new String();
				flag = 0;

				string = result.getText() + "4";
				result.setText(string);
				result.setSelection(string.length());
				// }

			}

		});
		button4.setOnTouchListener(new Button.OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					v.setBackgroundResource(R.drawable.b);

				} else {
					if (event.getAction() == MotionEvent.ACTION_UP) {
						v.setBackgroundResource(R.drawable.a);
						v.setBackgroundColor(Color.TRANSPARENT);
					}
				}
				return false;
			}

		});

		// 点击5，触发的事件
		button5.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (five != null) {
					five.stop();
				}
				try {
					five.prepare();
					five.start();
				} catch (IOException e) {
					// TODO: handle exception
				}
				String string = new String();
				flag = 0;

				string = result.getText() + "5";
				result.setText(string);
				result.setSelection(string.length());
				// }

			}

		});

		button5.setOnTouchListener(new Button.OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					v.setBackgroundResource(R.drawable.b);

				} else {
					if (event.getAction() == MotionEvent.ACTION_UP) {
						v.setBackgroundResource(R.drawable.a);
						v.setBackgroundColor(Color.TRANSPARENT);
					}
				}
				return false;
			}

		});

		// 点击6，触发的事件
		button6.setOnClickListener(new Button.OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (six != null) {
					six.stop();
				}
				try {
					six.prepare();
					six.start();
				} catch (IOException e) {
					// TODO: handle exception
				}
				String string = new String();
				flag = 0;

				string = result.getText() + "6";
				result.setText(string);
				result.setSelection(string.length());
				// }

			}

		});

		button6.setOnTouchListener(new Button.OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					v.setBackgroundResource(R.drawable.b);

				} else {
					if (event.getAction() == MotionEvent.ACTION_UP) {
						v.setBackgroundResource(R.drawable.a);
						v.setBackgroundColor(Color.TRANSPARENT);
					}
				}
				return false;
			}

		});

		// 点击7，触发的事件
		button7.setOnClickListener(new Button.OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (seven != null) {
					seven.stop();
				}
				try {
					seven.prepare();
					seven.start();
				} catch (IOException e) {
					// TODO: handle exception
				}
				String string = new String();
				flag = 0;

				string = result.getText() + "7";
				result.setText(string);
				result.setSelection(string.length());
				// }

			}

		});

		button7.setOnTouchListener(new Button.OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					v.setBackgroundResource(R.drawable.b);

				} else {
					if (event.getAction() == MotionEvent.ACTION_UP) {
						v.setBackgroundResource(R.drawable.a);
						v.setBackgroundColor(Color.TRANSPARENT);
					}
				}
				return false;
			}

		});

		// 点击8，触发的事件
		button8.setOnClickListener(new Button.OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (eight != null) {
					eight.stop();
				}
				try {
					eight.prepare();
					eight.start();
				} catch (IOException e) {
					// TODO: handle exception
				}
				String string = new String();
				flag = 0;

				string = result.getText() + "8";
				result.setText(string);
				result.setSelection(string.length());
				// }

			}

		});

		button8.setOnTouchListener(new Button.OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					v.setBackgroundResource(R.drawable.b);

				} else {
					if (event.getAction() == MotionEvent.ACTION_UP) {
						v.setBackgroundResource(R.drawable.a);
						v.setBackgroundColor(Color.TRANSPARENT);
					}
				}
				return false;
			}

		});

		// 触发9，触发的事件
		button9.setOnClickListener(new Button.OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (nine != null) {
					nine.stop();
				}
				try {
					nine.prepare();
					nine.start();
				} catch (IOException e) {
					// TODO: handle exception
				}
				String string = new String();
				flag = 0;

				string = result.getText() + "9";
				result.setText(string);
				result.setSelection(string.length());
				// }
			}

		});

		button9.setOnTouchListener(new Button.OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					v.setBackgroundResource(R.drawable.b);

				} else {
					if (event.getAction() == MotionEvent.ACTION_UP) {
						v.setBackgroundResource(R.drawable.a);
						v.setBackgroundColor(Color.TRANSPARENT);
					}
				}
				return false;
			}

		});

		// 点击0，触发的事件
		button0.setOnClickListener(new Button.OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (zero != null) {
					zero.stop();
				}
				try {
					zero.prepare();
					zero.start();
				} catch (IOException e) {
					// TODO: handle exception
				}
				String string = new String();
				flag = 0;

				string = result.getText() + "0";
				result.setText(string);
				result.setSelection(string.length());
				// }

			}
		});

		button0.setOnTouchListener(new Button.OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					v.setBackgroundResource(R.drawable.b);

				} else {
					if (event.getAction() == MotionEvent.ACTION_UP) {
						v.setBackgroundResource(R.drawable.a);
						v.setBackgroundColor(Color.TRANSPARENT);
					}
				}
				return false;
			}

		});

		// 点击.，触发的事件
		point.setOnClickListener(new Button.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (dot != null) {
					dot.stop();
				}
				try {
					dot.prepare();
					dot.start();
				} catch (IOException e) {
					// TODO: handle exception
				}
				String string = new String();
				flag = 0;

				string = result.getText() + ".";
				result.setText(string);
				result.setSelection(string.length());
				// }
			}

		});

		point.setOnTouchListener(new Button.OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					v.setBackgroundResource(R.drawable.b);

				} else {
					if (event.getAction() == MotionEvent.ACTION_UP) {
						v.setBackgroundResource(R.drawable.a);
						v.setBackgroundColor(Color.TRANSPARENT);
					}
				}
				return false;
			}

		});

		// 点击乘以，触发的事件
		symbolButton.setOnClickListener(new Button.OnClickListener() {

			public void onClick(View arg0) {

				if (mul != null) {
					mul.stop();
				}
				try {
					mul.prepare();
					mul.start();
				} catch (IOException e) {
					// TODO: handle exception
				}
				// TODO Auto-generated method stub
				try {
					if (flag == 0) {

						s = 1;// s表示已经输入乘以，并且用s控制可以输入的符号
						flag = 1;// 1表示乘以
						// f = Double.parseDouble(result.getText().toString());
						String string = new String();
						string = result.getText() + "*";
						result.setText(string);
						result.setSelection(string.length());

					} else {
						if (flag != 1) {
							String str = result.getText().toString();
							try {
								result.setText(str.substring(0,
										result.length() - 1));
							} catch (Exception e) {
								// TODO: handle exception
								e.getStackTrace();
							}
							str = result.getText() + "*";
							result.setText(str);
							result.setSelection(str.length());
							flag = 1;
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}

				// result.setText("");
			}

		});

		symbolButton.setOnTouchListener(new Button.OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					v.setBackgroundResource(R.drawable.d);

				} else {
					if (event.getAction() == MotionEvent.ACTION_UP) {
						v.setBackgroundResource(R.drawable.a);
						v.setBackgroundColor(Color.TRANSPARENT);
					}
				}
				return false;
			}

		});

		// 点击计算，触发的事件
		calButton.setOnClickListener(new Button.OnClickListener() {

			@SuppressWarnings("null")
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				// result2.setText("fasf");
				
				System.out.println("计算");
				if (equal != null) {
					equal.stop();
				}
				try {
					equal.prepare();
					equal.start();
				} catch (IOException e) {
					// TODO: handle exception
				}
				String string1 = result.getText().toString();// 获得中缀表达式
				System.out.println(string1 + "************");

				try {
					swap = 1;
					if (flag != 0) {
						Toast.makeText(MainActivity.this, "输入的表达式不对!",
								Toast.LENGTH_SHORT).show();
						// break;
					} else {

						ArrayList<Character> postfix = transform(string1);// 转成功
						System.out.println(postfix
								+ "*******************转为后缀表达式****************");
						calResult = String.valueOf(calculate(postfix));

						System.out.println(calResult + " 结果");
						// 为了输出结果时候能够利用语音控制

						char[] sq = null;
						int i = 0;

						for (i = 0; i < calResult.length(); i++) {
							sq[i] = calResult.charAt(i);
						}
						for (i--; i >= 0; i--) {
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								Thread.interrupted();
							}
							switch (sq[i]) {
							case '1':

								if (one != null) {
									one.stop();
								}
								try {
									one.prepare();
									one.start();

								} catch (Exception e) {
									// TODO: handle exception
									e.printStackTrace();
								}
								break;
							case '2':
								if (two != null) {
									two.stop();
								}
								try {
									two.prepare();
									two.start();
								} catch (Exception e) {
									// TODO: handle exception
								}
								break;
							case '3':
								if (three != null) {
									three.stop();
									System.out.println("暂停");
								}
								try {
									System.out.println("播放");
									three.prepare();
									three.start();
								} catch (Exception e) {
									// TODO: handle exception
								}
								break;
							case '4':
								if (four != null) {
									four.stop();
								}
								try {
									four.prepare();
									four.start();
								} catch (Exception e) {
									// TODO: handle exception
								}
								break;
							case '5':
								if (five != null) {
									five.stop();
								}
								try {
									five.prepare();
									five.start();
								} catch (Exception e) {
									// TODO: handle exception
								}
								break;
							case '6':
								if (six != null) {
									six.stop();
								}
								try {
									six.prepare();
									six.start();
								} catch (Exception e) {
									// TODO: handle exception
								}
								break;
							case '7':
								if (seven != null) {
									seven.stop();
								}
								try {
									seven.prepare();
									seven.start();
								} catch (Exception e) {
									// TODO: handle exception
								}
								break;
							case '8':
								if (eight != null) {
									eight.stop();
								}
								try {
									eight.prepare();
									eight.start();
									Thread.sleep(1000);
								} catch (Exception e) {
									// TODO: handle exception
								}
								break;
							case '9':
								if (nine != null) {
									nine.stop();
								}
								try {
									nine.prepare();
									nine.start();
								} catch (Exception e) {
									// TODO: handle exception
								}
								break;
							case '-':
								if (minus != null) {
									minus.stop();
								}
								try {
									minus.prepare();
									minus.start();

								} catch (Exception e) {
									// TODO: handle exception
									e.printStackTrace();
								}
								break;
							case '.':
								if (dot != null) {
									dot.stop();
								}
								try {

									dot.prepare();
									dot.start();

								} catch (Exception e) {
									// TODO: handle exception
								}
								break;
							default:
								break;
							}

						}

						System.out.println(result
								+ "******************结果****************888");

						result2.setText(String.valueOf(calculate(postfix)));
						// 为了输出结果时候能够利用语音控制
						// VoiceControlOutput voiceControlOutput = new
						// VoiceControlOutput();
						// voiceControlOutput.voiceOutput();
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}

			}

		});

		calButton.setOnTouchListener(new Button.OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					v.setBackgroundResource(R.drawable.d);
				} else {
					if (event.getAction() == MotionEvent.ACTION_UP) {
						v.setBackgroundResource(R.drawable.a);
						v.setBackgroundColor(Color.TRANSPARENT);
					}
				}
				return false;
			}

		});

		// 点击addButton时，触发的事件
		addButton.setOnClickListener(new Button.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (plus != null) {
					plus.stop();
				}
				try {
					plus.prepare();
					plus.start();
				} catch (IOException e) {
					// TODO: handle exception
				}
				try {
					if (flag == 0) {

						flag = 2;
						String string = new String();

						string = result.getText() + "+";

						result.setText(string);
						result.setSelection(string.length());

					} else {
						if (flag != 2) {
							String str1 = result.getText().toString();
							try {
								result.setText(str1.substring(0,
										result.length() - 1));
							} catch (Exception e) {
								// TODO: handle exception
								e.getStackTrace();
							}
							str1 = result.getText() + "+";
							result.setText(str1);
							result.setSelection(str1.length());
							flag = 2;
						}

					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}

			}

		});

		addButton.setOnTouchListener(new Button.OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					v.setBackgroundResource(R.drawable.d);
				} else {
					if (event.getAction() == MotionEvent.ACTION_UP) {
						v.setBackgroundResource(R.drawable.a);
						v.setBackgroundColor(Color.TRANSPARENT);
					}
				}
				return false;
			}

		});

		// 点击减，触发的事件

		cutButton.setOnClickListener(new Button.OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (minus != null) {
					minus.stop();
				}
				try {
					minus.prepare();
					minus.start();
				} catch (IOException e) {
					// TODO: handle exception
				}
				try {
					if (flag == 0) {
						flag = 3;
						String string = new String();
						string = result.getText() + "-";
						result.setText(string);
						result.setSelection(string.length());
					} else {
						if (flag != 3) {
							String str3 = result.getText().toString();

							result.setText(str3.substring(0,
									result.length() - 1));

							str3 = result.getText() + "-";
							result.setText(str3);
							result.setSelection(str3.length());
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}

			}

		});

		cutButton.setOnTouchListener(new Button.OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					v.setBackgroundResource(R.drawable.d);

				} else {
					if (event.getAction() == MotionEvent.ACTION_UP) {
						v.setBackgroundResource(R.drawable.a);
						v.setBackgroundColor(Color.TRANSPARENT);
					}
				}
				return false;
			}

		});

		// 点击除以，触发的事件
		devideButton.setOnClickListener(new Button.OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (div != null) {
					div.stop();
				}
				try {
					div.prepare();
					div.start();
				} catch (IOException e) {
					// TODO: handle exception
				}
				try {

					if (flag == 0) {
						flag = 4;
						String string = new String();
						string = result.getText() + "/";
						result.setText(string);
						result.setSelection(string.length());
					} else {
						if (flag != 4) {
							String str4 = result.getText().toString();

							result.setText(str4.substring(0,
									result.length() - 1));

							str4 = result.getText() + "/";
							result.setText(str4);
							result.setSelection(str4.length());
							flag = 4;
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}

			}
		});

		devideButton.setOnTouchListener(new Button.OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					v.setBackgroundResource(R.drawable.d);
					// Log.i("TestAndroid Button", "MotionEvent.ACTION_DOWN");
				} else {
					if (event.getAction() == MotionEvent.ACTION_UP) {
						v.setBackgroundResource(R.drawable.a);
						v.setBackgroundColor(Color.TRANSPARENT);
						// Log.i("TestAndroid Button", "MotionEvent.ACTION_UP");
					}
				}
				return false;
			}

		});

		// 点击清除，触发的事件
		clearButton.setOnClickListener(new Button.OnClickListener() {

			public void onClick(View v) {

				if (del != null) {
					del.stop();
				}
				try {
					del.prepare();
					del.start();
				} catch (IOException e) {
					// TODO: handle exception
				}
				// TODO Auto-generated method stub
				flag = 0;
				if (swap == 1) {// 当按下等于后，swap==1，清除所有数据
					result.setText("");
					result2.setText("");

				} else {
					String str = result.getText().toString();
					try {
						result.setText(str.substring(0, result.length() - 1));
						result.setSelection(str.substring(0,
								result.length() - 1).length() + 1);
					} catch (Exception e) {
						// TODO: handle exception
						e.getStackTrace();
					}
				}

				swap = 0;

			}

		});

		clearButton.setOnTouchListener(new Button.OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					v.setBackgroundResource(R.drawable.c);
				} else {
					if (event.getAction() == MotionEvent.ACTION_UP) {
						v.setBackgroundResource(R.drawable.a);
						v.setBackgroundColor(Color.TRANSPARENT);
					}
				}
				return false;
			}

		});
	}

	// 符号的菜单
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		menu.add(0, 1, 1, R.string.code);
		menu.add(1, 2, 2, R.string.Octal);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if (item.getItemId() == 1) {

			Intent intent = new Intent();
			intent.setClass(MainActivity.this, Result.class);
			MainActivity.this.startActivity(intent);

		} else {
			if (item.getItemId() == 2) {
				f = Double.parseDouble(result.getText().toString());
				result.setText(String.valueOf(f / 2));
			}
		}
		return super.onOptionsItemSelected(item);
	}

	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		// TODO Auto-generated method stub
		if (e1.getX() - e2.getX() > 20 && Math.abs(velocityX) > 0) {
			// 想做手势
			Toast.makeText(this, "向左的手势", Toast.LENGTH_SHORT).show();
			Intent intent = new Intent(MainActivity.this, Result.class);
			startActivityForResult(intent, 11);
			// startActivity(intent);
			// 设置切换效果
			overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
			// MainActivity.this.finish();// 防止打开多个页面
		}

		return false;
	}

	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}

	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		return gestureDetector.onTouchEvent(event);
	}

	// 点击返回 弹出窗口
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			AlertDialog.Builder alertDialog = new AlertDialog.Builder(
					MainActivity.this);
			alertDialog
					.setTitle("确定要退出:")
					.setPositiveButton("确定",
							new DialogInterface.OnClickListener() {

								public void onClick(DialogInterface arg0,
										int arg1) {
									// TODO Auto-generated method stub
									finish();
								}
							})
					.setNegativeButton("取消",
							new DialogInterface.OnClickListener() {

								public void onClick(DialogInterface arg0,
										int arg1) {
									// TODO Auto-generated method stub
									arg0.cancel();
								}
							}).create().show();
		}
		return true;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static ArrayList<Character> transform(String postfix)// 将中缀表达式转为后缀表达式
	{
		int i, len = postfix.length();
		postfix = postfix + "#";// 将表达式的最后置为#，为了控制结束
		Stack<Character> stack = new Stack<Character>();// 保存操作符的栈
		stack.push('#');// 让#入栈。
		ArrayList postfixExpression = new ArrayList();// 保存后缀表达式
		for (i = 0; i < len + 1; i++) {
			if (Character.isDigit(postfix.charAt(i))) {// 该元素为数字
				if (Character.isDigit(postfix.charAt(i + 1))) {// 下一个元素也为数字
					if (Character.isDigit(postfix.charAt(i + 1 + 1))) {// 第三位是数字
						if (Character.isDigit(postfix.charAt(i + 1 + 1 + 1))) {// 第四位是数字
							if (Character.isDigit(postfix.charAt(i + 1 + 1 + 1
									+ 1))) {// 第五位
								postfixExpression
										.add(10000
												* (postfix.charAt(i) - '0')
												+ 1000
												* (postfix.charAt(i + 1) - '0')
												+ 100
												* (postfix.charAt(i + 1 + 1) - '0')
												+ 10
												* (postfix
														.charAt(i + 1 + 1 + 1) - '0')
												+ (postfix.charAt(i + 1 + 1 + 1
														+ 1) - '0'));// 将该非个位数的数组存在数组列表里面
								// i++;
							} else {
								postfixExpression
										.add(1000
												* (postfix.charAt(i) - '0')
												+ 100
												* (postfix.charAt(i + 1) - '0')
												+ 10
												* (postfix.charAt(i + 1 + 1) - '0')
												+ (postfix
														.charAt(i + 1 + 1 + 1) - '0'));// 将该非个位数的数组存在数组列表里面
								i++;
							}

						} else {
							postfixExpression.add(100
									* (postfix.charAt(i) - '0') + 10
									* (postfix.charAt(i + 1) - '0')
									+ (postfix.charAt(i + 1 + 1) - '0'));// 将该非个位数的数组存在数组列表里面
							i++;
						}

					} else {
						postfixExpression.add(10 * (postfix.charAt(i) - '0')
								+ (postfix.charAt(i + 1) - '0'));// 将该非个位数的数组存在数组列表里面
						i++;
					}

				} else {
					postfixExpression.add(postfix.charAt(i) - '0');// 将个位数存在数组列表里面

				}

			} else {// 是运算符
				switch (postfix.charAt(i)) {
				case '(':
					stack.push('(');// 将开括号入栈
					break;
				case ')':
					while (stack.peek() != '(') {
						postfixExpression.add(stack.pop());// 将运算符出栈，存在字符数组
					}
					stack.pop();// 弹出(
					break;
				default:
					while (stack.peek() != '#'
							&& compare(stack.peek(), postfix.charAt(i))) {
						postfixExpression.add(stack.pop());
					}
					if (postfix.charAt(i) != '#') {
						stack.push(postfix.charAt(i));
					}
					break;
				}
			}
		}

		return postfixExpression;

	}

	private static boolean compare(Character peek, char cur) {// 比较运算符
		// TODO Auto-generated method stub
		if (peek == '*'
				&& (cur == '+' || cur == '-' || cur == '/' || cur == '*')) {// 如果cur是'('，那么cur的优先级高,如果是')'，是在上面处理
			return true;
		} else if (peek == '/'
				&& (cur == '+' || cur == '-' || cur == '*' || cur == '/')) {
			return true;
		} else if (peek == '+' && (cur == '+' || cur == '-')) {
			return true;
		} else if (peek == '-' && (cur == '+' || cur == '-')) {
			return true;
		} else if (cur == '#') {// 这个很特别，这里说明到了中缀表达式的结尾，那么就要弹出操作符栈中的所有操作符到后缀表达式中
			return true;// 当cur为'#'时，cur的优先级算是最低的
		}

		return false;
	}

	@SuppressWarnings("rawtypes")
	public static long calculate(ArrayList postfix) {

		Stack<Integer> stack_num = new Stack<Integer>();// 保存全部的操作数
		int result = 0;
		for (int i = 0; i < postfix.size(); i++) {
			if (postfix.get(i).getClass() == Integer.class) {// 表示该元素是数字
				stack_num.push((Integer) postfix.get(i));// 将该数入栈
			} else {// 如果是运算符
				int a = stack_num.pop();// 第二个数
				int b = stack_num.pop();// 第一个数
				switch ((Character) postfix.get(i)) {
				case '+':
					result = b + a;
					break;
				case '-':
					result = b - a;
					break;
				case '*':
					result = b * a;
					break;
				case '/':
					result = b / a;
					break;
				default:
					break;
				}
				stack_num.push(result);

			}
		}
		/*
		 * System.out.println(stack_num.pop() +
		 * "********************栈顶元素******************888");
		 */
		long res = stack_num.pop();
		System.out.println(res
				+ "********************栈顶元素******************888");
		return res;

	}

}
