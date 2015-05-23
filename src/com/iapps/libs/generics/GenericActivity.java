package com.iapps.libs.generics;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.iapps.libs.helpers.BaseUIHelper;
import com.verano.actionbar4guice.activity.RoboActionBarActivity;

public class GenericActivity
	extends RoboActionBarActivity {
	int containerId = 0;

	// ================================================================================
	// Fragment Functions
	// ================================================================================
	public void setContainerId(int containerId) {
		this.containerId = containerId;
	}

	public void addFragment(Fragment frag) {
		addFragment(this.containerId, frag);
	}

	public void setFragment(Fragment frag) {
		setFragment(this.containerId, frag);
	}

	/**
	 * Add fragment on top of the current one
	 * 
	 * @param frag
	 */
	public void addFragment(int containerId, Fragment frag) {
		if (containerId > 0) {
			getSupportFragmentManager().beginTransaction()
					.add(containerId, frag).addToBackStack(null).commit();

			if (getSupportActionBar() != null)
				getSupportActionBar().setDisplayHomeAsUpEnabled(false);

			// Hide keyboard by default
			BaseUIHelper.hideKeyboard(this);
		}
	}

	/**
	 * Change to new fragment
	 * 
	 * @param frag
	 */
	public void setFragment(int containerId, Fragment frag) {
		if (containerId > 0) {
			getSupportFragmentManager().beginTransaction()
					.replace(containerId, frag).addToBackStack(null)
					.commit();

			if (getSupportActionBar() != null)
				getSupportActionBar().setDisplayHomeAsUpEnabled(false);
			// setSupportProgressBarIndeterminateVisibility(false);

			// Hide keyboard by default when changing fragment
			BaseUIHelper.hideKeyboard(this);
		}
	}

	/**
	 * Clear all fragments
	 */
	public void clearFragment() {
		getSupportFragmentManager().popBackStack(null,
				FragmentManager.POP_BACK_STACK_INCLUSIVE);
	}

	/**
	 * Remove top of fragments
	 */
	public void popBackstack() {
		getSupportFragmentManager().popBackStack();
	}

	// ================================================================================
	// Behavior Controller
	// ================================================================================
	/**
	 * Controls behavior of the back button
	 */
	@Override
	public void onBackPressed() {
		// Only close apps when there's no backstack
		if (getSupportFragmentManager().getBackStackEntryCount() <= 1) {
			finish();
		}
		else {
			super.onBackPressed();
		}
	}
}
