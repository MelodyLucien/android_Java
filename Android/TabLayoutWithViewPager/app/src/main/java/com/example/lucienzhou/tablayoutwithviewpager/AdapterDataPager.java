package com.example.lucienzhou.tablayoutwithviewpager;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * <pre>
 *     author : Lucien Zhou
 *     e-mail : 825038797@qq.com
 *     time   : 2018/02/03
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class AdapterDataPager extends FragmentPagerAdapter {
    private final int PAGE_COUNT = 3;
    private String tabTitles[];
    private Context mContext;

    public AdapterDataPager(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
        initTabs();
    }

    private void initTabs(){
         tabTitles= new String[] {getStringFromApp(R.string.static_speed),
                 getStringFromApp(R.string.dynamic_speed),
                 getStringFromApp(R.string.speed_distance)
         };
    }

    private String getStringFromApp(int id){
        String str = mContext.getResources().getString(id);
        return str;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        return FragmentDataPage.newInstance(position + 1);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
