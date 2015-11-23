package com.example.CustomExpandableListView;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ExpandableListAdapter;
import android.widget.Toast;

public class CustomELVAdapter extends BaseExpandableListAdapter implements ExpandableListAdapter {
	
	private LayoutInflater vi;
	private String[][] data;
    private String [][] listinfo;
    private String[] groupname;
    private int[] ImgBckgrnd;
    private Context context;
    BounceInterpolator   bounceInterpolator;
    View v;
    
    private static final int GROUP_ITEM_RESOURCE = R.layout.basic_fragment_list_item;
    private static final int CHILD_ITEM_RESOURCE = R.layout.list_item;
    
    
    public CustomELVAdapter(Context context, Activity activity, String[] groupname, String [][] listinfo, String[][] data){
    	this.context = context;
    	this.groupname = groupname;
    	//this.ImgBckgrnd = ImgBckgrnd;
    	this.listinfo = listinfo;
    	this.data = data;
    	
    	vi = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    	
        bounceInterpolator = new BounceInterpolator();
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
    	 String child = getChild(groupPosition, childPosition);
         String list = getList(groupPosition, childPosition);
          v = convertView;
         v = vi.inflate(CHILD_ITEM_RESOURCE, null);
         final ViewHolder  holder = new ViewHolder(v);
      
         
         if (child != null) {      
        	 	holder.toggleLayout.setVisibility(View.VISIBLE);
        	 	holder.ExpCol.setChecked(true);
        	    String data[] = {"湖北华中科技大学华中科技大学华中科技大学华中科技大学华中科技大学华中科技大学华中科技大学华中科技大学湖北华中科技大学华中科技大学华中科技大学华中科技大学华中科技大学华中科技大学华中科技大学华中科技大学","武汉","洪山","珞瑜路","1037号","华中科技大学"};
        	 	holder.listView.setAdapter(new ArrayAdapter<String>(
        	 			context, android.R.layout.simple_list_item_1, data));
        	 
        	 	FixedListViewHeight fixedListViewHeight = new FixedListViewHeight();
        	 	fixedListViewHeight.setListViewHeightBasedOnChildren(holder.listView);
        	 	holder.listView.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
						Toast.makeText(context, "第一级菜单："+ groupPosition+" 第二级菜单：" + childPosition + " 第三级菜单：" + position,
								Toast.LENGTH_SHORT).show();
					}
        	 		
				});
        	 	
        	 	
                holder.ExpCol.setFocusable(false);
                
                
                
                holder.ListHead.setText(Html.fromHtml(child));
                holder.ListDetail.setText(Html.fromHtml(list));
                
                
   
                
                /**
                 * EXPANDING AND COLAPSING SECOND LEVEL CHILD
                 * **/
                holder.ExpCol.setOnCheckedChangeListener(new OnCheckedChangeListener(){

    				@Override
    				public void onCheckedChanged(CompoundButton buttonView,
    						boolean isChecked) {
    					if(holder.ExpCol.isChecked()){	
//    						holder.toggleLayout.startAnimation(slidedown);	
    					    holder.toggleLayout.setVisibility(View.VISIBLE);
    					
    					}else{
    						holder.toggleLayout.setVisibility(View.GONE);
//    						holder.toggleLayout.startAnimation(slideup);
    						
    					}
    				}
                	
                });

             /** 
             * ON CLICK LISTENER FOR CHILD 
             * **/
                holder.ChildLayout.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View v) {
						if(holder.ExpCol.isChecked()){
							holder.ExpCol.setChecked(false);
						}else{ 
							holder.ExpCol.setChecked(true);
				       }
						
					}
			     });
                
                
                /**
                 * ONCLICK METHODS FOR SECOND LEVEL CHILD BUTTONS
                 * **/
                holder.directions.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View v) {
						
						//DO SOMETHING
						
						Toast.makeText(context,"GET DIRECTIONS",Toast.LENGTH_SHORT).show();
						
					}
                	
                });
                
                holder.details.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View v) {
						
						//DO SOMETHING 
						
						Toast.makeText(context,"GET DETAILS",Toast.LENGTH_SHORT).show();
					}
                	
                });
                
          }
    
    	return v;
    }
    
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View v = convertView;
        ViewHolder holder;
        if(v==null){
        v = vi.inflate(GROUP_ITEM_RESOURCE, null);
        holder = new ViewHolder(v);
        v.setTag(holder);
        }else{
        	holder = (ViewHolder) v.getTag();
        }
        
        if (getGroupName(groupPosition) != null) {
          
           /**SET GROUP HEAD TEXT**/
            holder.GroupHead.setText(getGroupName(groupPosition));
            
            //holder.LayoutBackground.setBackgroundResource(getImage(groupPosition));
            }
        return v;
    }
    
    
/*    public int getImage(int groupPosition){
 		return ImgBckgrnd[groupPosition];
     }
     */
     public String getGroupName(int groupPosition){
   		return groupname[groupPosition];
       }

	@Override
	public String getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return data[groupPosition][childPosition];
	}
	
	public String getList(int groupPosition, int childPosition){
    	return listinfo[groupPosition][childPosition];
    	
    }

	@Override
    public int getChildrenCount(int groupPosition) {
        return data[groupPosition].length;
    }
	
	public int getGroupCount() {
        return groupname.length;
    }
    
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }
    
    @Override
	 public long getChildId(int groupPosition, int childPosition) {
	        return childPosition;
	    }
    
    @Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return true;
	}

    @Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return false;
	}

    public String getGroup(int groupPosition) {
        return "group-" + groupPosition;
    }

	


}
