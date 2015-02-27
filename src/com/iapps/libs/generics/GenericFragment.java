package com.iapps.libs.generics;

import java.lang.reflect.Field;

import roboguice.fragment.RoboFragment;
import android.support.v4.app.Fragment;

import com.doomonafireball.betterpickers.numberpicker.NumberPickerBuilder;
import com.iapps.common_library.R;

public class GenericFragment extends RoboFragment {
	private static final Field	sChildFragmentManagerField;

	// To prevent error in implementing nested fragment
	static {
		Field f = null;
		try {
			f = Fragment.class.getDeclaredField("mChildFragmentManager");
			f.setAccessible(true);
		}
		catch (NoSuchFieldException e) {
			// Error getting mChildFragmentManager field
			e.printStackTrace();
		}
		sChildFragmentManagerField = f;
	}

	protected boolean isThere() {
		if (!getUserVisibleHint() || !isVisible() || !isAdded()) {
			return false;
		}

		return true;
	}

	public void setTitle(int resTitle) {
		getActivity().setTitle(resTitle);
	}

	public void setTitle(String title) {
		getActivity().setTitle(title);
	}

	@Override
	public void onDetach() {
		super.onDetach();

		if (sChildFragmentManagerField != null) {
			try {
				sChildFragmentManagerField.set(this, null);
			}
			catch (Exception e) {
				// Error setting mChildFragmentManager field
				e.printStackTrace();
			}
		}
	}

	public NumberPickerBuilder popupPicker(Fragment targetFragment) {
		NumberPickerBuilder picker = new NumberPickerBuilder()
				.setFragmentManager(getChildFragmentManager())
				.setStyleResId(R.style.BetterPickersDialogFragment)
				.setTargetFragment(targetFragment);

		return picker;
	}

	public GenericActivity getHome() {
		return (GenericActivity) getActivity();
	}
}
