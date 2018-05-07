package com.example.boybaby.recyclerviewdemo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Boy Baby on 2018/4/29.*
 */

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHeloder> {
    private List<Fruit> mFruitList;

    //ViewHeloder类（获取布局中的实例）
    static class ViewHeloder extends RecyclerView.ViewHolder{
        ImageView fruitImage;
        TextView fruitName;
        public ViewHeloder(View itemView) {
            super(itemView);
            fruitImage = (ImageView) itemView.findViewById(R.id.fruit_image1);
            fruitName = (TextView) itemView.findViewById(R.id.fruit_name1);
        }
    }
    //FruitAdapter类的构造函数(把ListView中的数据源传递进来)
    public FruitAdapter(List<Fruit> fruitList){
        //把数据源复制给一个全局变量
        mFruitList=fruitList;
    }
    /**
     *
     * 功能：加载xml布局文件
     * @param parent
     * @param viewType
     * @return 返回ViewHeloder的实例关联控件
     */
    @Override
    public ViewHeloder onCreateViewHolder(ViewGroup parent, int viewType) {
        //将fruit_item布局文件加载进来
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit_item,parent,false);
        //把加载出来的布局文件传入到ViewHeloder构造函数中去
        RecyclerView.ViewHolder holder = new ViewHeloder(view);
        return (ViewHeloder) holder;
    }

    /**
     *
     * 功能：对RecyclerView子项的数据赋值，会在每个子项被滚动到屏幕内的时候执行
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ViewHeloder holder, int position) {
        //通过position参数得到当前项的Fruit的实例
        Fruit fruit =mFruitList.get(position);
        //将数据设置到ImageView和TextView中去
        holder.fruitImage.setImageResource(fruit.getImageID());
        holder.fruitName.setText(fruit.getName());
    }

    /**
     * 功能：Recycler一共有多少子项
     * @return
     */
    @Override
    public int getItemCount() {
        return mFruitList.size();
    }
}
