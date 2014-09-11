package com.example.androidyingyong;

import java.io.IOException;

@SuppressWarnings("unused")
public class VoiceControlOutput {

	public static long n;// n是计算的结果，s是每位的数字
	int i = 0;
	char[] s;// 用来保存将字符串转换为的字符

	@SuppressWarnings("static-access")
	public void voiceOutput() {

		System.out.println("已经进入");
		MainActivity outPut = new MainActivity();
		/*
		 * n = outPut.calResult;// 将计算的结果传过来 while (n != 0) { s[i++] = (int) (n
		 * % 10);// 将每一位存在数组里面 n /= 10; }
		 */
		String resultString = outPut.calResult;
		System.out.println(resultString+"传过来的结果");
		// 将字符串转换为字符数组
		for (int i = 0; i < resultString.length(); i++) {
			s[i] = resultString.charAt(i);
			System.out.print(s[i]+" 转换为字符数组");
		}
		for (i--; i >= 0; i--) {
			
			switch (s[i]) {
			case '1':

				if (outPut.one != null) {
					outPut.one.stop();
				}
				try {
					outPut.one.prepare();
					outPut.one.start();

				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				break;
			case '2':
				if (outPut.two != null) {
					outPut.two.stop();
				}
				try {
					outPut.two.prepare();
					outPut.two.start();
				} catch (Exception e) {
					// TODO: handle exception
				}
				break;
			case '3':
				if (outPut.three != null) {
					outPut.three.stop();
				}
				try {
					outPut.three.prepare();
					outPut.three.start();
				} catch (Exception e) {
					// TODO: handle exception
				}
				break;
			case '4':
				if (outPut.four != null) {
					outPut.four.stop();
				}
				try {
					outPut.four.prepare();
					outPut.four.start();
				} catch (Exception e) {
					// TODO: handle exception
				}
				break;
			case '5':
				if (outPut.five != null) {
					outPut.five.stop();
				}
				try {
					outPut.five.prepare();
					outPut.five.start();
				} catch (Exception e) {
					// TODO: handle exception
				}
				break;
			case '6':
				if (outPut.six != null) {
					outPut.six.stop();
				}
				try {
					outPut.six.prepare();
					outPut.six.start();
				} catch (Exception e) {
					// TODO: handle exception
				}
				break;
			case '7':
				if (outPut.seven != null) {
					outPut.seven.stop();
				}
				try {
					outPut.seven.prepare();
					outPut.seven.start();
				} catch (Exception e) {
					// TODO: handle exception
				}
				break;
			case '8':
				if (outPut.eight != null) {
					outPut.eight.stop();
				}
				try {
					outPut.eight.prepare();
					outPut.eight.start();
					Thread.sleep(1000);
				} catch (Exception e) {
					// TODO: handle exception
				}
				break;
			case '9':
				if (outPut.nine != null) {
					outPut.nine.stop();
				}
				try {
					Thread.interrupted();
					outPut.nine.prepare();
					outPut.nine.start();
					Thread.sleep(1000);
				} catch (Exception e) {
					// TODO: handle exception
				}
				break;
			case '-':
				if (outPut.minus != null) {
					outPut.minus.stop();
				}
				try {
					outPut.minus.prepare();
					outPut.minus.start();

				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				break;
			case '.':
				if (outPut.dot != null) {
					outPut.dot.stop();
				}
				try {
					
					outPut.dot.prepare();
					outPut.dot.start();
					
				} catch (Exception e) {
					// TODO: handle exception
				}
				break;
			default:
				break;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				Thread.interrupted();
			}
		}

	}

}
