package com.iapps.libs.helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;

import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Editable;
import android.text.Layout;
import android.text.TextUtils.TruncateAt;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.iapps.common_library.R;
import com.squareup.picasso.Callback.EmptyCallback;
import com.squareup.picasso.Picasso;

public class BaseUIHelper {

	// public static void defaultSearchView(View v) {
	// if (v != null && v instanceof com.actionbarsherlock.widget.SearchView) {
	// SearchView view = (SearchView) v;
	//
	// try {
	// Field searchField = SearchView.class
	// .getDeclaredField("mSearchButton");
	// searchField.setAccessible(true);
	// ImageView searchBtn = (ImageView) searchField.get(view);
	// searchBtn.setImageResource(R.drawable.ic_action_ic_search);
	// searchBtn
	// .setBackgroundResource(R.drawable.ad_selectable_background);
	// //
	// searchBtn.setBackgroundDrawable(resources.getDrawable(R.drawable.ad_selectable_background));
	// searchField = SearchView.class.getDeclaredField("mSearchPlate");
	// searchField.setAccessible(true);
	//
	// LinearLayout searchPlate = (LinearLayout) searchField.get(view);
	//
	// // close button
	// searchPlate.getChildAt(1).setBackgroundResource(
	// R.drawable.ad_selectable_background);
	// searchPlate
	// .setBackgroundResource(R.drawable.textfield_activated_holo_light);
	//
	// } catch (NoSuchFieldException e) {
	// // Log.e(TAG,e.getMessage(),e);
	// } catch (IllegalAccessException e) {
	// // Log.e(TAG,e.getMessage(),e);
	// }
	// }
	// }

	// public static void defaultSearchView(SearchView search){
	// try {
	// AutoCompleteTextView searchText = (AutoCompleteTextView)
	// search.findViewById(R.id.abs__search_src_text);
	// searchText.setHintTextColor(search.getContext().getResources().getColor(R.color.DeepSkyBlue));
	// } catch (NotFoundException e) {
	// e.printStackTrace();
	// }
	// //
	// searchText.setTextColor(search.getContext().getResources().getColor(R.color.AliceBlue));
	// }

	@SuppressWarnings("deprecation")
	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	public static void resizeImageView(Context ctx, ImageView imgView) {
		WindowManager wm = (WindowManager) ctx
				.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		int width;
		// int height;
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR2) {
			width = display.getWidth(); // deprecated
			// height = display.getHeight(); // deprecated
		}
		else {
			Point size = new Point();
			display.getSize(size);
			width = size.x;
			// height = size.y;
		}

		imgView.setMinimumHeight(width);
		imgView.setMinimumWidth(width);
		imgView.getLayoutParams().height = width;
		imgView.getLayoutParams().width = width;
	}

	@SuppressWarnings("deprecation")
	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	public static int getScreenHeight(Context ctx) {
		WindowManager wm = (WindowManager) ctx
				.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		int height;
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR2) {
			height = display.getHeight(); // deprecated
		}
		else {
			Point size = new Point();
			display.getSize(size);
			height = size.y;
		}

		return height;
	}

	@SuppressWarnings("deprecation")
	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	public static int getScreenWidth(Context ctx) {
		WindowManager wm = (WindowManager) ctx
				.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		int width;

		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR2) {
			width = display.getWidth(); // deprecated

		}
		else {
			Point size = new Point();
			display.getSize(size);
			width = size.x;

		}

		return width;
	}

	//
	// public static int getActionBarHeight(Context ctx) {
	//
	// int actionBarHeight = 0;
	// TypedValue tv = new TypedValue();
	// if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
	// if (ctx.getTheme().resolveAttribute(android.R.attr.actionBarSize,
	// tv, true))
	// actionBarHeight = TypedValue.complexToDimensionPixelSize(
	// tv.data, ctx.getResources().getDisplayMetrics());
	// } else if (ctx.getTheme().resolveAttribute(
	// com.actionbarsherlock.R.attr.actionBarSize, tv, true)) {
	// actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data,
	// ctx.getResources().getDisplayMetrics());
	// }
	//
	// return actionBarHeight;
	//
	// }

	public static BitmapDrawable writeOnDrawable(
			Activity actv, Resources res, int drawableId, String text,
			int textSize) {

		Bitmap bm = BitmapFactory.decodeResource(res, drawableId).copy(
				Bitmap.Config.ARGB_8888, true);

		DisplayMetrics dm = new DisplayMetrics();
		actv.getWindowManager().getDefaultDisplay().getMetrics(dm);

		int pixelSize = (int) ((textSize * dm.scaledDensity));

		if (text.length() > 2) {
			pixelSize = (int) ((textSize * dm.scaledDensity) * (0.5 - (text
					.length() / 10)));
		}

		Paint paint = new Paint();
		paint.setStyle(Style.FILL);
		paint.setColor(Color.WHITE);
		paint.setTextSize(pixelSize);
		paint.setTextAlign(Paint.Align.CENTER);

		// float adjust = paint.measureText(text);

		Canvas canvas = new Canvas(bm);
		int xPos = (int) ((bm.getWidth() / 2));
		int yPos = (int) ((bm.getHeight() / 2) - ((paint.descent() + paint
				.ascent()) / 2));

		canvas.drawText(text, xPos, yPos, paint);

		return new BitmapDrawable(res, bm);
	}

	// public static void resetSearchView(SearchView searchV) {
	// searchV.setQuery("", false);
	// searchV.onActionViewCollapsed();
	// searchV.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
	// }

	public static Bitmap textAsBitmap(String text, float textSize) {
		if (text == null || text.length() == 0) {
			return null;
		}

		Paint paint = new Paint();
		paint.setTextSize(textSize);
		paint.setColor(Color.BLACK);
		paint.setTextAlign(Paint.Align.CENTER);
		int width = (int) (paint.measureText(text) + 0.5f); // round
		float baseline = (int) -(paint.ascent() + 0.5f);
		int height = (int) (baseline + paint.descent() + 0.5f);
		Bitmap image = Bitmap.createBitmap(width, height,
				Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(image);
		canvas.drawText(text, 0, baseline, paint);
		return image;
	}

	/**
	 * This method convets dp unit to equivalent device specific value in pixels.
	 * 
	 * @param dp A value in dp(Device independent pixels) unit. Which we need to convert into pixels
	 * @param context Context to get resources and device specific display metrics
	 * @return A float value to represent Pixels equivalent to dp according to device
	 */
	public static float convertDpToPixel(float dp, Context context) {
		Resources resources = context.getResources();
		DisplayMetrics metrics = resources.getDisplayMetrics();
		float px = dp * (metrics.densityDpi / 160f);
		return px;
	}

	/**
	 * This method converts device specific pixels to device independent pixels.
	 * 
	 * @param px A value in px (pixels) unit. Which we need to convert into db
	 * @param context Context to get resources and device specific display metrics
	 * @return A float value to represent db equivalent to px value
	 */
	public static float convertPixelsToDp(float px, Context context) {
		Resources resources = context.getResources();
		DisplayMetrics metrics = resources.getDisplayMetrics();
		float dp = px / (metrics.densityDpi / 160f);
		return dp;

	}

	public static int getFistLine(TextView tv) {
		// int height = tv.getHeight();
		int scrollY = tv.getScrollY();
		Layout layout = tv.getLayout();

		int firstVisibleLineNumber = layout.getLineForVertical(scrollY);
		// int lastVisibleLineNumber =
		// layout.getLineForVertical(scrollY+height);
		return firstVisibleLineNumber;
	}

	public static int getLastLine(TextView tv) {
		int height = tv.getHeight();
		int scrollY = tv.getScrollY();
		Layout layout = tv.getLayout();

		if (layout == null) {
			return 0;
		}
		// int firstVisibleLineNumber = layout.getLineForVertical(scrollY);
		int lastVisibleLineNumber = layout.getLineForVertical(scrollY + height);
		return lastVisibleLineNumber;
	}

	/**
	 * Make a textview to a collapsible textview with the indicator on the right of the textview
	 * 
	 * @param tv , {@link TextView} to be converted
	 * @param upDrawableResId , drawable resource id to be used as up indicator
	 * @param downDrawableResId , drawable resource id to be used as down indicator
	 * @param lineTreshold , no of line to be displayed for the collapsed state
	 */
	public static void makeCollapsible(
			final TextView tv, int upDrawableResId, int downDrawableResId,
			final int lineTreshold) {

		final Drawable[] drawables = tv.getCompoundDrawables();
		final Drawable up = tv.getContext().getResources()
				.getDrawable(upDrawableResId);
		final Drawable down = tv.getContext().getResources()
				.getDrawable(downDrawableResId);
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
			tv.setCompoundDrawablesWithIntrinsicBounds(drawables[0],
					drawables[1], down, drawables[3]);
			tv.setEllipsize(TruncateAt.END);
			tv.setMaxLines(lineTreshold);
			tv.setTag(true);
			tv.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					if (v instanceof TextView) {
						TextView tv = (TextView) v;
						boolean snippet = (Boolean) tv.getTag();
						if (snippet) {
							// show everything
							snippet = false;
							tv.setMaxLines(Integer.MAX_VALUE);
							tv.setEllipsize(null);
							tv.setCompoundDrawablesWithIntrinsicBounds(
									drawables[0], drawables[1], up,
									drawables[3]);
						}
						else {
							// show snippet
							snippet = true;
							tv.setMaxLines(lineTreshold);
							tv.setEllipsize(TruncateAt.END);
							tv.setCompoundDrawablesWithIntrinsicBounds(
									drawables[0], drawables[1], down,
									drawables[3]);
						}
						tv.setTag(snippet);
					}

				}
			});
		}
		else {
			tv.addTextChangedListener(new TextWatcher() {

				@Override
				public void afterTextChanged(Editable arg0) {

					ViewTreeObserver vto = tv.getViewTreeObserver();
					vto.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

						@SuppressWarnings("deprecation")
						@SuppressLint("NewApi")
						@Override
						public void onGlobalLayout() {

							tv.setEllipsize(TruncateAt.END);
							int line = tv.getLineCount();
							tv.setMaxLines(lineTreshold);
							if (line <= lineTreshold) {
								tv.setOnClickListener(new View.OnClickListener() {

									@Override
									public void onClick(View arg0) {
										// empty listener
										// Log.d("line count", "count: "+
										// tv.getLineCount());
									}
								});
								if (tv.getLayout() != null) {
									if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
										tv.getViewTreeObserver()
												.removeGlobalOnLayoutListener(
														this);
									}
									else {
										tv.getViewTreeObserver()
												.removeOnGlobalLayoutListener(
														this);
									}
								}
								return;
							}

							tv.setCompoundDrawablesWithIntrinsicBounds(
									drawables[0], drawables[1], down,
									drawables[3]);
							tv.setTag(true);
							tv.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									if (v instanceof TextView) {
										TextView tv = (TextView) v;
										boolean snippet = (Boolean) tv.getTag();
										if (snippet) {
											snippet = false;
											// show everything
											tv.setMaxLines(Integer.MAX_VALUE);
											tv.setEllipsize(null);
											tv.setCompoundDrawablesWithIntrinsicBounds(
													drawables[0], drawables[1],
													up, drawables[3]);
										}
										else {
											snippet = true;
											// show snippet
											tv.setMaxLines(lineTreshold);
											tv.setEllipsize(TruncateAt.END);
											tv.setCompoundDrawablesWithIntrinsicBounds(
													drawables[0], drawables[1],
													down, drawables[3]);
										}
										tv.setTag(snippet);
									}

								}
							});

							if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
								tv.getViewTreeObserver()
										.removeGlobalOnLayoutListener(this);
							}
							else {
								tv.getViewTreeObserver()
										.removeOnGlobalLayoutListener(this);
							}
						}

					});
				}

				@Override
				public void beforeTextChanged(
						CharSequence arg0, int arg1, int arg2, int arg3) {
				}

				@Override
				public void onTextChanged(
						CharSequence arg0, int arg1, int arg2, int arg3) {
				}

			});
		}

	}

	public static void addBadge(TextView tv, Drawable d) {
		Drawable[] dws = tv.getCompoundDrawables();
		dws[2] = d;
		tv.setCompoundDrawablesWithIntrinsicBounds(dws[0], dws[1], dws[2],
				dws[3]);
	}

	/**
	 * Adjust the tab host being used in the tab host + view pager to use the new drawableResId for
	 * tab items
	 * 
	 * @param mTabHost , tab host to be adjusted
	 * @param context , context being used
	 * @param drawableResId , drawable resource for the new background for tab item. Needs to use
	 *        drawable with different appropriate states!
	 */
	@SuppressWarnings("deprecation")
	@SuppressLint("NewApi")
	public static void adjustTabHost(
			TabHost mTabHost, Context context, int drawableResId,
			int textColorResId) {
		if (mTabHost == null || context == null) {
			return;
		}
		try {

			for (int i = 0; i < mTabHost.getTabWidget().getChildCount(); i++) {
				Drawable d = context.getResources().getDrawable(drawableResId);

				int sdk = android.os.Build.VERSION.SDK_INT;
				ViewGroup tab = null;
				TextView t = null;
				try {
					tab = (ViewGroup) mTabHost.getTabWidget().getChildAt(i);
					t = (TextView) tab.findViewById(android.R.id.title);
					t.setTextColor(context.getResources().getColor(
							textColorResId));
				}
				catch (Exception e1) {
					e1.printStackTrace();
				}
				if (tab == null) {
					return;
				}

				if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
					tab.setBackgroundDrawable(d); // unselected
				}
				else {
					tab.setBackground(d); // unselected
				}

				if (sdk < Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
					// only for android older than ICS
					// manually adjust

					try {
						// TextView tv = (TextView) v.getChildAt(1);
						TextView tv = t;
						tv.setText(tv.getText().toString()
								.toUpperCase(Locale.ENGLISH));
						RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
								RelativeLayout.LayoutParams.WRAP_CONTENT,
								RelativeLayout.LayoutParams.WRAP_CONTENT);
						params.addRule(RelativeLayout.CENTER_IN_PARENT);
						tv.setLayoutParams(params);
						tv.setTextColor(Color.WHITE);
						tv.setGravity(Gravity.CENTER);
					}
					catch (NotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					catch (ClassCastException ex) {
						ex.printStackTrace();
					}
				}

			}
		}
		catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Bitmap getThumbnail(Activity activity, Uri uri)
			throws FileNotFoundException, IOException {
		return getThumbnail(activity, uri, BaseConstants.THUMBNAIL_SIZE);
	}

	public static Bitmap getThumbnail(Context context, Uri uri, int size)
			throws FileNotFoundException, IOException {
		InputStream input = context.getContentResolver().openInputStream(uri);

		BitmapFactory.Options onlyBoundsOptions = new BitmapFactory.Options();
		onlyBoundsOptions.inJustDecodeBounds = true;
		onlyBoundsOptions.inDither = true;// optional
		onlyBoundsOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;// optional
		BitmapFactory.decodeStream(input, null, onlyBoundsOptions);
		input.close();
		if ((onlyBoundsOptions.outWidth == -1)
				|| (onlyBoundsOptions.outHeight == -1))
			return null;

		int originalSize = (onlyBoundsOptions.outHeight > onlyBoundsOptions.outWidth) ? onlyBoundsOptions.outHeight
				: onlyBoundsOptions.outWidth;

		double ratio = (originalSize > size) ? (originalSize / size) : 1.0;

		BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
		bitmapOptions.inSampleSize = getPowerOfTwoForSampleRatio(ratio);
		bitmapOptions.inDither = true;// optional
		bitmapOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;// optional
		input = context.getContentResolver().openInputStream(uri);
		Bitmap bitmap = BitmapFactory.decodeStream(input, null, bitmapOptions);
		input.close();
		return bitmap;
	}

	public static int getPowerOfTwoForSampleRatio(double ratio) {
		int k = Integer.highestOneBit((int) Math.floor(ratio));
		if (k == 0)
			return 1;
		else
			return k;
	}

	public Bitmap getFullBitmap(Activity activity, Uri uri)
			throws FileNotFoundException, IOException {
		InputStream input = activity.getContentResolver().openInputStream(uri);

		BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
		bitmapOptions.inSampleSize = 1;
		bitmapOptions.inDither = true;// optional
		bitmapOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;// optional

		input = activity.getContentResolver().openInputStream(uri);
		Bitmap bitmap = BitmapFactory.decodeStream(input, null, bitmapOptions);
		input.close();
		return bitmap;
	}

	public static void hideKeyboard(Activity activity) {
		InputMethodManager inputManager = (InputMethodManager) activity
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		if (activity.getCurrentFocus() != null) {
			inputManager.hideSoftInputFromWindow(activity.getCurrentFocus()
					.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		}
	}

	public static File processImage(Activity activity, ImageView imgButton) {
		File avatar = null;
		Bitmap bitmap;
		String filePath = Environment.getExternalStorageDirectory() + "/"
				+ BaseConstants.TEMP_PHOTO_FILE;
		try {
			avatar = new File(filePath);
			Uri uri = Uri.fromFile(avatar);

			if (imgButton != null) {
				if (imgButton.getMeasuredHeight() > 0
						&& imgButton.getMeasuredWidth() > 0) {
					int ratio = imgButton.getMeasuredHeight() > imgButton
							.getMeasuredWidth() ? imgButton.getMeasuredWidth()
							: imgButton.getMeasuredHeight();
					bitmap = BaseUIHelper.getThumbnail(activity, uri, ratio);
				}
				else {
					bitmap = BaseUIHelper.getThumbnail(activity, uri);
				}
				imgButton.setImageBitmap(bitmap);
			}

			saveSmallerImage(filePath, BaseConstants.MAX_IMAGE_SIZE,
					BaseConstants.MAX_IMAGE_SIZE);
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return avatar;
	}

	public interface ImagePreviewListener {

		public void onYes(File photo);

		public void onShow();
	}

	private static AlertDialog showImagePreview(
			Context context, String title, final File file, Bitmap bmp,
			final ImagePreviewListener l) {

		LinearLayout la = new LinearLayout(context);
		la.setGravity(Gravity.CENTER);
		ImageView view = new ImageView(context);
		view.setScaleType(ImageView.ScaleType.FIT_XY);
		view.setImageBitmap(bmp);
		int px = (int) BaseUIHelper.convertDpToPixel(
				BaseConstants.PREVIEW_SIZE, context);
		view.setMinimumHeight(px);
		view.setMinimumWidth(px);
		view.setMaxHeight(px);
		view.setMaxWidth(px);
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(px, px);
		lp.gravity = Gravity.CENTER;
		view.setLayoutParams(lp);

		la.addView(view);

		return new AlertDialog.Builder(context)
				.setTitle(title)
				.setCancelable(true)
				.setView(la)
				.setPositiveButton(R.string.iapps__yes,
						new DialogInterface.OnClickListener() {

							public void onClick(
									DialogInterface dialog, int whichButton) {
								if (l != null) {
									l.onYes(file);
								}
							}
						}).setNegativeButton(R.string.iapps__cancel, null)
				.show();
	}

	public static File processPreview(
			Context context, String title, final ImagePreviewListener l) {
		File avatar = null;
		Bitmap bitmap;
		String filePath = Environment.getExternalStorageDirectory() + "/"
				+ BaseConstants.TEMP_PHOTO_FILE;
		try {

			avatar = new File(filePath);
			Uri uri = Uri.fromFile(avatar);

			int ratio = (int) BaseConstants.PREVIEW_SIZE;
			bitmap = BaseUIHelper.getThumbnail(context, uri, ratio);

			AlertDialog ld = BaseUIHelper.showImagePreview(context, title,
					avatar, bitmap, l);
			ld.setOnShowListener(new DialogInterface.OnShowListener() {

				@Override
				public void onShow(DialogInterface dialog) {
					if (l != null) {
						l.onShow();
					}
				}
			});

		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			saveSmallerImage(filePath, BaseConstants.MAX_IMAGE_SIZE,
					BaseConstants.MAX_IMAGE_SIZE);
		}

		return avatar;
	}

	private static void saveSmallerImage(
			String pathOfInputImage, int dstWidth, int dstHeight) {
		try {
			int inWidth = 0;
			int inHeight = 0;

			InputStream in = new FileInputStream(pathOfInputImage);

			// decode image size (decode metadata only, not the whole image)
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inJustDecodeBounds = true;
			BitmapFactory.decodeStream(in, null, options);
			in.close();
			in = null;

			// save width and height
			inWidth = options.outWidth;
			inHeight = options.outHeight;

			// decode full image pre-resized
			in = new FileInputStream(pathOfInputImage);
			options = new BitmapFactory.Options();
			// calc rought re-size (this is no exact resize)
			options.inSampleSize = Math.max(inWidth / dstWidth, inHeight
					/ dstHeight);
			// decode full image
			Bitmap roughBitmap = BitmapFactory.decodeStream(in, null, options);

			// calc exact destination size
			Matrix m = new Matrix();
			RectF inRect = new RectF(0, 0, roughBitmap.getWidth(),
					roughBitmap.getHeight());
			RectF outRect = new RectF(0, 0, dstWidth, dstHeight);
			m.setRectToRect(inRect, outRect, Matrix.ScaleToFit.CENTER);
			float[] values = new float[9];
			m.getValues(values);

			// resize bitmap
			Bitmap resizedBitmap = Bitmap.createScaledBitmap(roughBitmap,
					(int) (roughBitmap.getWidth() * values[0]),
					(int) (roughBitmap.getHeight() * values[4]), true);

			// save image
			try {
				FileOutputStream out = new FileOutputStream(pathOfInputImage);
				resizedBitmap.compress(Bitmap.CompressFormat.JPEG, 80, out);
			}
			catch (Exception e) {
				Log.e("Image", e.getMessage(), e);
			}
		}
		catch (IOException e) {
			Log.e("Image", e.getMessage(), e);
		}
		catch (Exception e) {
			Log.e("Image", e.getMessage());
		}
	}

	public static File saveBitmapToTemp(Bitmap bmp) {
		String filePath = Environment.getExternalStorageDirectory() + "/"
				+ BaseConstants.TEMP_PHOTO_FILE;
		OutputStream fOut = null;
		File f = new File(filePath);
		try {
			fOut = new FileOutputStream(f);
			bmp.compress(Bitmap.CompressFormat.JPEG, 90, fOut);
			fOut.flush();
			fOut.close();

		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			saveSmallerImage(filePath, BaseConstants.MAX_IMAGE_SIZE,
					BaseConstants.MAX_IMAGE_SIZE);
		}
		return f;
	}

	public static void rotate(float direction, ImageView compass) {
		if (compass == null) {
			return;
		}
		Matrix matrix = new Matrix();
		matrix.postRotate(direction, compass.getMeasuredWidth() / 2,
				compass.getMeasuredHeight() / 2);
		compass.setImageMatrix(matrix);
	}

	public static LayoutInflater getThemedLayoutInflater(
			LayoutInflater inflater, Context context, int styleThemeRes) {
		// create ContextThemeWrapper from the original Activity Context with
		// the custom theme
		final Context contextThemeWrapper = new ContextThemeWrapper(context,
				styleThemeRes);

		// clone the inflater using the ContextThemeWrapper
		LayoutInflater localInflater = inflater
				.cloneInContext(contextThemeWrapper);

		return localInflater;
	}

	/**
	 * Get Folders to be displayed for the full screen image
	 * 
	 * @param ctx
	 * @param folders
	 * @return
	 */
	public static final String getFolder(Context ctx, JSONObject folders) {
		int dpi = ctx.getResources().getDisplayMetrics().densityDpi;
		String folder = "";
		if (dpi == DisplayMetrics.DENSITY_MEDIUM
				|| dpi == DisplayMetrics.DENSITY_HIGH) {
			// medium
			folder = folders.optString(BaseKeys.MEDIUM_IMAGE_FOLDER);
		}
		else if (dpi >= DisplayMetrics.DENSITY_XHIGH) {
			// big
			folder = folders.optString(BaseKeys.BIG_IMAGE_FOLDER);
		}
		else {
			// default
			// big
			folder = folders.optString(BaseKeys.BIG_IMAGE_FOLDER);
		}
		return folder;
	}

	public static final String getSmallFolder(JSONObject folders) {
		String folder = folders.optString(BaseKeys.SMALL_IMAGE_FOLDER);
		return folder;
	}

	// ================================================================================
	// DEPRECATED FUNCTIONS
	// USE ImageViewLoader INSTEAD
	// ================================================================================
	/**
	 * Load image from url
	 * 
	 * @param context
	 * @param url
	 * @param resPlaceholder
	 * @param img
	 */
	public static void loadImage(
			Context context, final String url, int resPlaceholder, final ImageView img) {
		// Init animation & start animating with no scale type set to imageview
		// To avoid cropped progressBar
		Animation rotate = AnimationUtils.loadAnimation(context, R.anim.rotate);
		img.setScaleType(ScaleType.FIT_CENTER);
		img.startAnimation(rotate);

		// Load normally
		Picasso
				.with(context)
				.load(url)
				.placeholder(resPlaceholder)
				.noFade()

				.into(img, new EmptyCallback() {

					@Override
					public void onSuccess() {
						super.onSuccess();
						// Stop animation
						img.clearAnimation();

						// Set image back to centerCrop
						img.setScaleType(ScaleType.CENTER_CROP);
					}

					@Override
					public void onError() {
						super.onError();
						// Stop animation
						img.clearAnimation();

						// Show cross image
						img.setImageResource(R.drawable.ic_cross_dark);
					}
				});
	}

	/**
	 * Load normal image
	 * 
	 * @param context
	 * @param url
	 * @param img
	 */
	public static void loadImage(Context context, String url, ImageView img) {
		// TODO : auto detect theme dark or light
		loadImage(context, url, R.drawable.ic_progress_dark, img);
	}

	/**
	 * Load image from resource
	 * 
	 * @param context
	 * @param res
	 * @param img
	 */
	public static void loadImage(Context context, int res, ImageView img) {
		Picasso.with(context).load(res).into(img);
	}

	/**
	 * Load image from url with rounded corners
	 * 
	 * @param context
	 * @param url
	 * @param img
	 * @param placeholder
	 */
	public static void loadImageRounded(
			Context context, String url, final ImageView img) {
		// Init animation & start animating with no scale type set to imageview
		// To avoid cropped progressBar
		Animation rotate = AnimationUtils.loadAnimation(context, R.anim.rotate);
		img.setScaleType(ScaleType.FIT_CENTER);
		img.startAnimation(rotate);

		// Load normally
		Picasso
				.with(context)
				.load(url)
				.placeholder(R.drawable.ic_progress_dark)
				.noFade()
				.transform(new CircleTransform())
				.into(img, new EmptyCallback() {

					@Override
					public void onSuccess() {
						super.onSuccess();
						// Stop animation
						img.clearAnimation();

						// Set image back to centerCrop
						img.setScaleType(ScaleType.CENTER_CROP);
					}

					@Override
					public void onError() {
						super.onError();
						// Stop animation
						img.clearAnimation();

						// Show cross image
						img.setImageResource(R.drawable.ic_cross_dark);
					}
				});
	}

	// ================================================================================
	// Animation
	// ================================================================================
	public static void animSlideInFromBottom(View view) {
		BaseUIHelper.animSlideIn(view, R.anim.up_from_bottom);
	}

	public static void animSlideInFromTop(View view) {
		BaseUIHelper.animSlideIn(view, R.anim.down_from_top);
	}

	private static void animSlideIn(View view, int resAnim) {
		Animation animation = AnimationUtils.loadAnimation(view.getContext(), resAnim);
		view.startAnimation(animation);
		view.setVisibility(View.VISIBLE);
	}

	public static void animSlideOutFromTop(final View view) {
		TranslateAnimation animate = new TranslateAnimation(0, 0, 0, view.getHeight());
		animate.setDuration(400);
		animate.setFillAfter(true);
		view.startAnimation(animate);
		view.setVisibility(View.GONE);
	}

	public static void animSlideOutFromBottom(final View view) {
		TranslateAnimation animate = new TranslateAnimation(0, 0, 0, -view.getHeight());
		animate.setDuration(500);
		animate.setFillAfter(true);
		view.startAnimation(animate);
		view.setVisibility(View.GONE);
	}

	public static void animRotate(View view) {
		Animation animRotate = AnimationUtils.loadAnimation(view.getContext(),
				R.anim.rotate);
		view.startAnimation(animRotate);
	}

	public static void animFadeIn(View view) {
		BaseUIHelper.animate(view, R.anim.fadein);
	}

	public static void animFadeOut(View view) {
		BaseUIHelper.animate(view, R.anim.fadeout);
	}

	public static void animate(View view, int resAnim) {
		Animation animFadein = AnimationUtils.loadAnimation(view.getContext(),
				resAnim);
		view.startAnimation(animFadein);
	}

	public static void animExpand(final View v) {
		v.measure(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		final int targetHeight = v.getMeasuredHeight();

		v.getLayoutParams().height = 0;
		v.setVisibility(View.VISIBLE);
		Animation a = new Animation()
		{
			@Override
			protected void applyTransformation(float interpolatedTime, Transformation t) {
				v.getLayoutParams().height = interpolatedTime == 1
						? LayoutParams.WRAP_CONTENT
						: (int) (targetHeight * interpolatedTime);
				v.requestLayout();
			}

			@Override
			public boolean willChangeBounds() {
				return true;
			}
		};

		// 1dp/ms
		a.setDuration((int) (targetHeight / v.getContext().getResources().getDisplayMetrics().density));
		v.startAnimation(a);
	}

	public static void animCollapse(final View v) {
		final int initialHeight = v.getMeasuredHeight();

		Animation a = new Animation()
		{
			@Override
			protected void applyTransformation(float interpolatedTime, Transformation t) {
				if (interpolatedTime == 1) {
					v.setVisibility(View.GONE);
				}
				else {
					v.getLayoutParams().height = initialHeight - (int) (initialHeight * interpolatedTime);
					v.requestLayout();
				}
			}

			@Override
			public boolean willChangeBounds() {
				return true;
			}
		};

		// 1dp/ms
		a.setDuration((int) (initialHeight / v.getContext().getResources().getDisplayMetrics().density));
		v.startAnimation(a);
	}

	// ================================================================================
	// SwipeRefresh
	// ================================================================================

	@SuppressLint("InlinedApi")
	public static void setRefreshColor(SwipeRefreshLayout sr) {
		sr.setColorSchemeResources(
				android.R.color.holo_blue_light,
				android.R.color.holo_green_light,
				android.R.color.holo_orange_light,
				android.R.color.holo_red_light);
	}
}
