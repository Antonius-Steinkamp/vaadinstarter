package com.example.application.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public final class ViewUtils {
    public static Component createButtonLayout(final Button... buttons) {
        final HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassName("button-layout");
        for (final Button button: buttons) {
	        buttonLayout.add(button);
        }
        return buttonLayout;
    }


}
