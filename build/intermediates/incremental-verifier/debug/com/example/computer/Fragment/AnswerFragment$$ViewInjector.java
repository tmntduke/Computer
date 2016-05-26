// Generated code from Butter Knife. Do not modify!
package com.example.computer.Fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class AnswerFragment$$ViewInjector<T extends com.example.computer.Fragment.AnswerFragment> implements Injector<T> {
  @Override public void inject(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131165233, "field 'mTv1'");
    target.mTv1 = finder.castView(view, 2131165233, "field 'mTv1'");
    view = finder.findRequiredView(source, 2131165234, "field 'mTv2' and method 'onClick'");
    target.mTv2 = finder.castView(view, 2131165234, "field 'mTv2'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131165235, "field 'mTv3' and method 'onClick'");
    target.mTv3 = finder.castView(view, 2131165235, "field 'mTv3'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131165236, "field 'mTv4' and method 'onClick'");
    target.mTv4 = finder.castView(view, 2131165236, "field 'mTv4'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131165237, "field 'mTv5' and method 'onClick'");
    target.mTv5 = finder.castView(view, 2131165237, "field 'mTv5'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131165238, "field 'mBtn1' and method 'onClick'");
    target.mBtn1 = finder.castView(view, 2131165238, "field 'mBtn1'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131165239, "field 'mBtn2' and method 'onClick'");
    target.mBtn2 = finder.castView(view, 2131165239, "field 'mBtn2'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131165240, "field 'mBtn3' and method 'onClick'");
    target.mBtn3 = finder.castView(view, 2131165240, "field 'mBtn3'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131165232, "field 'mCount'");
    target.mCount = finder.castView(view, 2131165232, "field 'mCount'");
  }

  @Override public void reset(T target) {
    target.mTv1 = null;
    target.mTv2 = null;
    target.mTv3 = null;
    target.mTv4 = null;
    target.mTv5 = null;
    target.mBtn1 = null;
    target.mBtn2 = null;
    target.mBtn3 = null;
    target.mCount = null;
  }
}
