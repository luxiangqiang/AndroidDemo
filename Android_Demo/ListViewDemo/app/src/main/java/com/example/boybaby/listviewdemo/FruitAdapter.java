package com.example.boybaby.listviewdemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 *
 * 功能：用户自定义适配器
 * Created by Boy Baby on 2018/4/28.
 */

public class FruitAdapter extends ArrayAdapter<Fruit> {

    //接受布局id
    private int resourceID;
    /**
     * 功能：构造函数
     * @param context   上下文对象
     * @param textViewResourceId 布局文件id
     * @param object list集合对象
     */
    public FruitAdapter(@NonNull Context context, int textViewResourceId, List<Fruit> object) {
        super(context, textViewResourceId,object);
        resourceID = textViewResourceId;
    }

    /**
     * 功能：每个子项被滚动到屏幕内的时候都被调用
     * @param position
     * @param convertView 用于之前加载好的布局进行缓存
     * @param parent
     * @return
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //getItem（）用于获取为指定视图项提供数据的物品。
        Fruit fruit = getItem(position);//获取当前项的实例 （listView第几行）
        View view;
        ViewHolder viewHolder;
        if(convertView == null){
            //对每个数据子项布局文件的动态加载
           view  = LayoutInflater.from(getContext()).inflate(resourceID,parent,false);
           viewHolder = new ViewHolder();
            //获取布局实例
           viewHolder.fruitImage =  (ImageView) view.findViewById(R.id.fruit_image);
           viewHolder.fruitName = (TextView) view.findViewById(R.id.fruit_name);
           //将viewHolder对象存放到View中
           view.setTag(viewHolder);
        }else{
            view = convertView;
            //重新获取ViewHolder
            viewHolder = (ViewHolder)view.getTag();
        }
        //设置布局显示的图片和文字
        viewHolder.fruitImage.setImageResource(fruit.getImageID());
        viewHolder.fruitName.setText(fruit.getName());
        //把布局返回了每个item项所显示的view
        return view;
    }
    //对控件实例进行缓存
    class ViewHolder{

        ImageView fruitImage;

        TextView fruitName;
    }
}
