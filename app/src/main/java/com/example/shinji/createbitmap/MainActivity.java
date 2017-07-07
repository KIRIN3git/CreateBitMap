package com.example.shinji.createbitmap;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Paint作成
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.WHITE);
		paint.setTextSize(66);

		// 描画テキストの領域を取得
		// rect.widthとmtwは違う。rect.widthの方が小さい。
		// rect.heightとfmHeightは違う。rect.heightの方が小さい。
		String txt = "Bitmap Text : abcdefghijklmnopqrstuvwxyz";
		Rect rect = new Rect();
		// 矩形を返す
		paint.getTextBounds(txt, 0, txt.length(), rect);
		// 行間スペースを返す
		Paint.FontMetrics fm = paint.getFontMetrics();
		// テキストの横長を返す
		int mtw = (int) paint.measureText(txt);

		int fmHeight = (int) (Math.abs(fm.top) + fm.bottom);
		Log.v("onCreate", "rect.w=" + rect.width() + " rect.h=" + rect.height() + " fm.top=" + fm.top + " fm.bottom=" + fm.bottom + " fm.ascent=" + fm.ascent + "fm.descent=" + fm.descent + " fm.height=" + fmHeight + " mtw=" + mtw);

		// 描画領域ピッタリのビットマップ作成
		int margin = 1; // ギリギリすぎるので上下左右に多少余裕を取る
		Bitmap bmp = Bitmap.createBitmap(mtw + margin * 2, fmHeight + margin * 2, Bitmap.Config.ARGB_8888);

		// ビットマップからキャンバスを作成してテキスト描画
		Canvas cv = new Canvas(bmp);
		cv.drawText(txt, margin, Math.abs(fm.ascent) + margin, paint);

		// ビットマップテキストをImageViewにセット
		ImageView iv = (ImageView) findViewById(R.id.iv);
		iv.setImageBitmap(bmp);

	}
}
