package com.example.CustomExpandableListView;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * �̶�ListView�ĸ߶ȣ�ʹ�ö��ListView������ScrollView�д�ֱ������
 * ͨ����ȡÿһ�еĸ߶ȡ��ܹ����������м�ֽ��ߵĸ߶��������ܹ��ĸ߶�ֵ��
 * @param listView
 *			Ҫ�̶��߶ȵ�ListView���
 */

public class FixedListViewHeight {
	public void setListViewHeightBasedOnChildren(ListView listView) {
		ListAdapter listAdapter = listView.getAdapter(); // ȡ�õ�ǰ�����listview��������
		if (listAdapter == null) {
			return;
		}
		// ��������Ϊ�գ����ȡ���ݽ��м���
		int totalHeight = 0;// �ܸ߶ȳ�ʼ��Ϊ0
		for (int i = 0; i < listAdapter.getCount(); i++) {// ȡ��listview�ܹ�������
			View listItem = listAdapter.getView(i, null, listView);// ȡ��ÿһ�е���ͼ
			listItem.measure(0, 0);// ��ʼ����ͼ�ĳ����С
			totalHeight += listItem.getMeasuredHeight();// ����ȡ�ĸ߶Ƚ����ۼ�
		}
		ViewGroup.LayoutParams params = listView.getLayoutParams();// ���ò��ֲ���
		params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));// �����ļ��ĸ߶ȼ�Ϊ�����еĸ߶ȼ����м�ֽ��ߵĸ߶��ܺ�
		listView.setLayoutParams(params);// ��listview���ò��ֲ���
	}
}
