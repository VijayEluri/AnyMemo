/*
Copyright (C) 2010 Haowen Ning

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either version 2
of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.

*/
package org.liberty.android.fantastischmemo;

import java.util.ArrayList;

import android.os.Bundle;
import android.content.Context;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ImageView;
import android.util.Log;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public abstract class DownloaderBase extends Activity{

    
    protected class DownloadItem{
        public static final int TYPE_CATEGORY = 1;
        public static final int TYPE_DATABASE = 2;
        public static final int TYPE_UP= 3;
        private int type;
        private String title = "";
        private String description = "";
        private String address = "";

        public DownloadItem(){
        }

        public DownloadItem(int type, String title, String description, String address){

        }

        public void setType(int type){
            this.type = type;
        }

        public void setTitle(String title){
            this.title = title;
        }

        public void setDescription(String description){
            this.description = description;
        }

        public void setAddress(String address){
            this.address = address;
        }

        public int getType(){
            return type;
        }

        public String getTitle(){
            return title;
        }

        public String getDescription(){
            return description;
        }

        public String getAddress(){
            return address;
        }

    }
    private class DownloadListAdapter extends ArrayAdapter<DownloadItem>{
        private ArrayList<DownloadItem> mDownloadItems;

        public DownloadListAdapter(Context context, int textViewResourceId, ArrayList<DownloadItem> items){
            super(context, textViewResourceId, items);
            mDownloadItems = items;
        }

        @Override
        public void add(DownloadItem item){
            super.add(item);
            mDownloadItems.add(item);
        }

        @Override
        public void clear(){
            super.clear();
            mDownloadItems.clear();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            View v = convertView;
            if(v == null){
                LayoutInflater li = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                /* Reuse the filebrowser's resources */
                v = li.inflate(R.layout.filebrowser_item, null);
            }
            DownloadItem item = mDownloadItems.get(position);
            if(item != null){
                TextView tv = (TextView)v.findViewById(R.id.file_name);
                ImageView iv = (ImageView)v.findViewById(R.id.file_icon);
                if(item.getType() == DownloadItem.TYPE_CATEGORY){
                    iv.setImageResource(R.drawable.dir);
                }
                else if(item.getType() == DownloadItem.TYPE_UP){
                    iv.setImageResource(R.drawable.back);
                }
                else{
                    iv.setImageResource(R.drawable.database24);
                }
                tv.setText(item.getTitle());
            }
            return v;
        }
    }
}
