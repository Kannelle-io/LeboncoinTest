package com.test.leboncointest;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.test.leboncointest.databinding.AlbumDetailBindingImpl;
import com.test.leboncointest.databinding.AlbumItemBindingImpl;
import com.test.leboncointest.databinding.AlbumListBindingImpl;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_ALBUMDETAILFRAGMENT = 1;

  private static final int LAYOUT_ALBUMLISTFRAGMENT = 2;

  private static final int LAYOUT_ITEMALBUM = 3;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(3);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.test.leboncointest.R.layout.album_detail_fragment, LAYOUT_ALBUMDETAILFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.test.leboncointest.R.layout.album_list_fragment, LAYOUT_ALBUMLISTFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.test.leboncointest.R.layout.item_album, LAYOUT_ITEMALBUM);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_ALBUMDETAILFRAGMENT: {
          if ("layout/album_detail_fragment_0".equals(tag)) {
            return new AlbumDetailBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for album_detail_fragment is invalid. Received: " + tag);
        }
        case  LAYOUT_ALBUMLISTFRAGMENT: {
          if ("layout/album_list_fragment_0".equals(tag)) {
            return new AlbumListBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for album_list_fragment is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMALBUM: {
          if ("layout/item_album_0".equals(tag)) {
            return new AlbumItemBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_album is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(2);

    static {
      sKeys.put(0, "_all");
      sKeys.put(1, "viewModel");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(3);

    static {
      sKeys.put("layout/album_detail_fragment_0", com.test.leboncointest.R.layout.album_detail_fragment);
      sKeys.put("layout/album_list_fragment_0", com.test.leboncointest.R.layout.album_list_fragment);
      sKeys.put("layout/item_album_0", com.test.leboncointest.R.layout.item_album);
    }
  }
}
