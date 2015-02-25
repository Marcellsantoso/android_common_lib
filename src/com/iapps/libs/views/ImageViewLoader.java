package com.iapps.libs.views;

import java.util.Calendar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.iapps.common_library.R;
import com.iapps.libs.helpers.CircleTransform;
import com.iapps.libs.objects.ListenerDoubleTap;
import com.squareup.picasso.Callback.EmptyCallback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

public class ImageViewLoader extends RelativeLayout {

	private ImageView	image;
	private ProgressBar	progress;
	private boolean		isFade	= true;
	private long		delay;

	public ImageViewLoader(Context context) {
		this(context, null);
	}

	public ImageViewLoader(Context context, AttributeSet attr) {
		super(context, attr);

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.image_view_loader, this, true);

		image = (ImageView) this.findViewById(R.id.image);
		progress = (ProgressBar) this.findViewById(R.id.loader);

		// Default scale type
		image.setScaleType(ScaleType.CENTER_CROP);
	}

	public void hideProgress() {
		progress.setVisibility(View.GONE);
	}

	public void showProgress() {
		progress.setVisibility(View.VISIBLE);
	}

	public void showFail() {
		image.setScaleType(ScaleType.CENTER);
		image.setImageResource(R.drawable.ic_cross_light);
	}

	// ================================================================================
	// Image Loader Functions
	// ================================================================================
	public void loadImage(String url) {
		this.loadImage(url, 0, false);
	}

	public void loadImage(String url, boolean isCircular) {
		this.loadImage(url, 0, isCircular);
	}

	public void loadImage(String url, int resPlaceHolder, boolean isCircular) {
		if (this.image != null && this.progress != null) {
			RequestCreator imageLoader = Picasso
					.with(this.getContext())
					.load(url);

			if (!isFade) {
				imageLoader.noFade();
			}

			if (resPlaceHolder > 0) {
				imageLoader.placeholder(resPlaceHolder);
			}

			// Rounded image
			if (isCircular) {
				imageLoader = imageLoader.transform(new CircleTransform());
			}

			imageLoader.into(this.image, new EmptyCallback() {

				@Override
				public void onSuccess() {
					super.onSuccess();
					hideProgress();
				}

				@Override
				public void onError() {
					super.onError();
					showFail();
					hideProgress();
				}
			});
		}
	}

	public void loadImage(int resImage) {
		RequestCreator imageLoader = Picasso
				.with(this.getContext())
				.load(resImage);
		imageLoader.into(this.image);

		hideProgress();
	}

	// ================================================================================
	// Getter & Setter
	// ================================================================================
	public ImageView getImage() {
		return image;
	}

	public boolean isFade() {
		return isFade;
	}

	public void setFade(boolean isFade) {
		this.isFade = isFade;
	}

	public void setOnDoubleTapListener(ListenerDoubleTap listenerDoubleTap) {
		this.setOnClickListener(new ListenerClick(listenerDoubleTap));
	}

	// ================================================================================
	// Double Tap Functions
	// ================================================================================
	public class ListenerClick implements OnClickListener {

		private ListenerDoubleTap	listener;

		public ListenerClick(ListenerDoubleTap listener) {
			this.listener = listener;
		}

		@Override
		public void onClick(View v) {
			long curTime = Calendar.getInstance().getTimeInMillis();
			long diffTime = curTime - delay;

			if (diffTime < 500) {
				listener.onDoubleTap(v);
				delay = 0;
			} else {
				delay = curTime;
			}

		}

	}

}
