package com.example.CustomExpandableListView;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * 固定ListView的高度，使得多个ListView仍能在ScrollView中垂直滑动。
 * 通过获取每一行的高度、总共的行数、行间分界线的高度来计算总共的高度值。
 * @param listView
 *			要固定高度的ListView组件
 */

public class FixedListViewHeight {
	public void setListViewHeightBasedOnChildren(ListView listView) {
		ListAdapter listAdapter = listView.getAdapter(); // 取得当前传入的listview的适配器
		if (listAdapter == null) {
			return;
		}
		// 适配器不为空，则获取数据进行计算
		int totalHeight = 0;// 总高度初始化为0
		for (int i = 0; i < listAdapter.getCount(); i++) {// 取得listview总共的行数
			View listItem = listAdapter.getView(i, null, listView);// 取得每一行的视图
			listItem.measure(0, 0);// 初始化视图的长宽大小
			totalHeight += listItem.getMeasuredHeight();// 将获取的高度进行累加
		}
		ViewGroup.LayoutParams params = listView.getLayoutParams();// 设置布局参数
		params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));// 布局文件的高度即为所有行的高度加上行间分界线的高度总和
		listView.setLayoutParams(params);// 给listview设置布局参数
	}
}
