package com.raik383h_group_6.healthtracmobile.adapter;
import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import roboguice.inject.InjectView;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * @author Ildar Karimov
 */
public abstract class RoboBaseAdapter<THolder> extends BaseAdapter {
    protected ArrayList<ViewMembersInjector> viewsForInjection = new ArrayList<ViewMembersInjector>();
    private Context context;
    private int layoutId;
    private LayoutInflater inflater;

    private void init(Class holderType) {
        inflater = LayoutInflater.from(context);
        prepareFields(holderType);
    }

    public RoboBaseAdapter(Context context, int layoutId, Class<? extends Object> holderType) {
        this.context = context;
        this.layoutId = layoutId;
        init(holderType);
    }

    protected abstract THolder createNewViewHolder(int position, View convertView, ViewGroup parent);
    protected abstract void bindView(int position, View convertView, ViewGroup parent, THolder holder);

    protected View createNewView(int position, ViewGroup parent) {
        return inflater.inflate(layoutId, parent, false);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        THolder holder;
        if (convertView == null) {
            convertView = createNewView(position, parent);
            holder = createNewViewHolder(position, convertView, parent);
            convertView.setTag(holder);
            injectViews(convertView);
        } else {
            holder = (THolder) convertView.getTag();
        }
        bindView(position, convertView, parent, holder);
        return convertView;
    }

    public void injectViews(View view) {
        for (ViewMembersInjector viewMembersInjector : viewsForInjection) {
            viewMembersInjector.reallyInjectMembers(this, view);
        }
    }

    private void prepareFields(Class clazz) {
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(InjectView.class)) {
                field.setAccessible(true);
                viewsForInjection.add(new ViewMembersInjector(field, field.getAnnotation(InjectView.class)));
            }
        }
    }

    class ViewMembersInjector {
        protected Field field;
        protected InjectView annotation;

        public ViewMembersInjector(Field field, InjectView annotation) {
            this.field = field;
            this.annotation = annotation;
        }

        public void reallyInjectMembers(Object holder, View view) {
            Object value = null;
            try {
                value = view.findViewById(annotation.value());
                field.set(holder, value);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (IllegalArgumentException f) {
                throw new IllegalArgumentException(String.format("Can't assign %s value %s to %s field %s", value != null ? value.getClass() : "(null)", value,
                        field.getType(), field.getName()));
            }
        }
    }
}