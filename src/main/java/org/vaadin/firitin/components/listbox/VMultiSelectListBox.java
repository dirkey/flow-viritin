package org.vaadin.firitin.components.listbox;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.listbox.MultiSelectListBox;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.function.SerializablePredicate;
import org.vaadin.firitin.fluency.ui.*;

import java.util.Set;

public class VMultiSelectListBox<T> extends MultiSelectListBox<T> implements FluentComponent<VMultiSelectListBox<T>>, FluentHasStyle<VMultiSelectListBox<T>>, FluentHasDataProvider<VMultiSelectListBox<T>, T>,
        FluentHasSize<VMultiSelectListBox<T>>, FluentHasItems<VMultiSelectListBox<T>, T>, FluentFocusable<VMultiSelectListBox<T>, VMultiSelectListBox<T>> {

    public VMultiSelectListBox<T> withRenderer(ComponentRenderer<? extends Component, T> itemRenderer) {
        setRenderer(itemRenderer);
        return this;
    }

    public VMultiSelectListBox<T> withItemEnabledProvider(SerializablePredicate<T> itemEnabledProvider) {
        setItemEnabledProvider(itemEnabledProvider);
        return this;
    }

    public VMultiSelectListBox<T> withValue(Set<T> value) {
        setValue(value);
        return this;
    }
}
