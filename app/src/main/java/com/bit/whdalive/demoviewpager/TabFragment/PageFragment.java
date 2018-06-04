package com.bit.whdalive.demoviewpager.TabFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bit.whdalive.demoviewpager.R;

public class PageFragment extends Fragment {
    public static final String ARGS = "PageFragment";

    private int curPage;

    public static PageFragment newinstance(int curPage) {
        Bundle args = new Bundle();
        args.putInt(ARGS, curPage);
        PageFragment fragment = new PageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        curPage = getArguments().getInt(ARGS);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page, container, false);
        TextView textView = view.findViewById(R.id.text_view);
        textView.setText("Page :" + curPage);
        return view;
    }
}
