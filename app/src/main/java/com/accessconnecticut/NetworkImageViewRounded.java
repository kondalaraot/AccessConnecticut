/**
 * 
 */
package com.accessconnecticut;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

/**
 * @author ktirumalasetty
 * 
 */
public class NetworkImageViewRounded extends NetworkImageView {

	private Bitmap mLocalBitmap;

	private boolean mShowLocal;
	
	public NetworkImageViewRounded(Context paramContext) {
		super(paramContext);
	}

	public NetworkImageViewRounded(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public void setLocalImageBitmap(Bitmap bitmap) {
		if (bitmap != null) {
			mShowLocal = true;
		}
		this.mLocalBitmap = bitmap;
		requestLayout();
	}

	@Override
	public void setImageUrl(String url, ImageLoader imageLoader) {
		mShowLocal = false;
		super.setImageUrl(url, imageLoader);
	}

	public NetworkImageViewRounded(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom) {

		super.onLayout(changed, left, top, right, bottom);
		if (mShowLocal) {
			setImageBitmap(mLocalBitmap);
		}
	}


	@Override
	protected void onDraw(Canvas canvas) {
		BitmapDrawable drawable = (BitmapDrawable) getDrawable();

		if (drawable == null) {
			return;
		}

		if (getWidth() == 0 || getHeight() == 0) {
			return;
		}

		Bitmap fullSizeBitmap = drawable.getBitmap();
		if(fullSizeBitmap == null){
			return;
		}

		Bitmap oldBitmap = fullSizeBitmap;
		if (oldBitmap != fullSizeBitmap) {
			oldBitmap.recycle();
		}

		int scaledWidth = getMeasuredWidth();
		int scaledHeight = getMeasuredHeight();

		Bitmap mScaledBitmap;
		if (scaledWidth == fullSizeBitmap.getWidth() && scaledHeight == fullSizeBitmap.getHeight()) {
			mScaledBitmap = fullSizeBitmap;
		} else {
			mScaledBitmap = Bitmap.createScaledBitmap(fullSizeBitmap, scaledWidth, scaledHeight,
					true /* filter */);
		}

		// Bitmap roundBitmap = getRoundedCornerBitmap(mScaledBitmap);

		// Bitmap roundBitmap = getRoundedCornerBitmap(getContext(),
		// mScaledBitmap, 10, scaledWidth, scaledHeight, false, false,
		// false, false);
		// canvas.drawBitmap(roundBitmap, 0, 0, null);

		Bitmap circleBitmap = getCircledBitmap(mScaledBitmap);

		canvas.drawBitmap(circleBitmap, 0, 0, null);

		Bitmap oldScaledBitmap = mScaledBitmap;
		if (oldScaledBitmap != mScaledBitmap) {
			oldScaledBitmap.recycle();
		}

	}

	Bitmap getCircledBitmap(Bitmap bitmap) {
		Bitmap result = null;
		try {
			BitmapFactory.Options opt = new BitmapFactory.Options();
			opt.inPurgeable = true;
			opt.inPreferQualityOverSpeed = false;
			opt.inSampleSize = 1;

			opt.inJustDecodeBounds = true;
			/*
			 * while(opt.outHeight > MaxTextureSize || opt.outWidth >
			 * MaxTextureSize) { opt.inSampleSize++;
			 * BitmapFactory.decodeFile(params[0], opt); }
			 */
			opt.inJustDecodeBounds = false;

			result = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(),
					Bitmap.Config.ARGB_8888);

			Canvas canvas = new Canvas(result);

			int color = Color.BLUE;
			Paint paint = new Paint();
			Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

			paint.setAntiAlias(true);
			canvas.drawARGB(0, 0, 0, 0);
			paint.setColor(color);
			// canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
			canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2,
					bitmap.getHeight() / 2, paint);

			paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
			canvas.drawBitmap(bitmap, rect, rect, paint);

			Bitmap oldResultBitmap = result;
			if (oldResultBitmap != result) {
				oldResultBitmap.recycle();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
}
