package com.example.apple.demo43;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by apple on 2018/4/4.
 */

public class ListAdapter extends BaseAdapter {

    //上下文
    private Context context;
    //数据集合
    private List<ListData> list;
    LayoutInflater inflater;
    public ListAdapter(Context c,List<ListData> list){
        //创建时传递过来的窗口
        context=c;
        //要显示的数据集
        this.list=list;
        //inflater用来获取listrow.xml中定义的控件，通过java代码来访问这两个控件（调用布局文件）
        inflater=(LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return list.get(arg0);
    }
    public List<ListData> getlistdata()
    {
        //返回listview列表相关数据集合
        return list;
    }
    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(final int position1, View arg1, ViewGroup arg2) {
        // TODO Auto-generated method stub
        if(arg1==null)
        {
            //view对象如果为空，通过xml文件创建view对象
            arg1=inflater.inflate(R.layout.listview_item, null);
        }
        //食品图片控件
        ImageView foodImg=(ImageView)arg1.findViewById(R.id.list_item_foodImg);
        //食品名称控件
        TextView foodNameTextView=(TextView) arg1.findViewById(R.id.list_item_foodName);
        //食品价格控件
        TextView foodPriceTextView=(TextView) arg1.findViewById(R.id.list_item_foodprice);
        //设置食品图片
        foodImg.setImageResource(list.get(position1).getImageID());
        //设置食品名称
        foodNameTextView.setText(list.get(position1).getName());
        //设置食品价格
        foodPriceTextView.setText(Double.toString(list.get(position1).getPrice()));
        //食品份数控件
        final EditText countEditText=(EditText)arg1.findViewById(R.id.list_item_edit);
        //增加食品数量按钮
        Button addButton=(Button)arg1.findViewById(R.id.list_item_button_add);
        //减少食品数量按钮
        Button subButton=(Button)arg1.findViewById(R.id.list_item_button_sub);
        //设置增加食品数量按钮监听，每点一次增加一份
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    //获取当前份数
                    int k=Integer.parseInt(countEditText.getText().toString().trim());
                    //当前份数加1
                    k=k+1;
                    //更新listview绑定数据集合
                    list.get(position1).setCount(k);
                    //新数量显示在数量控件
                    countEditText.setText(Integer.toString(k));
                }catch (Exception e)
                {
                }
            }
        });
        //设置减少食品数量按钮监听，每点一次减少一份
        subButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    //获取当前份数
                    int k=Integer.parseInt(countEditText.getText().toString().trim());
                    //判断当前数量，如果大于0则进行减少处理
                    if (k>0)
                    {
                        //当前分数减1
                        k=k-1;
                        //更新listview绑定数据集合
                        list.get(position1).setCount(k);
                        //新数量显示在数量控件
                        countEditText.setText(Integer.toString(k));
                    }

                }catch (Exception e)
                {
                }
            }
        });
        return arg1;
    }

}
