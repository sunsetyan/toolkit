package net.xiaoyuyu.timer;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityEvent;

public class DebugAccessibilityService extends AccessibilityService {

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        // Doing nothing at all
    }

    @Override
    public void onInterrupt() {
        // doing nothing at all
    }
}