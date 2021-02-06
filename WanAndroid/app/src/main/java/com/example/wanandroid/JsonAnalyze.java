package com.example.wanandroid;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class JsonAnalyze {

    protected void JsonDataGet_web(String jsonData, List<WebData> list) {
        try {
            Log.e("json解析","常用网站");
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray jsonArray_Data = jsonObject.getJSONArray("data");

            for (int i = 0; i < jsonData.length(); i++) {
                JSONObject jsonObjectk = jsonArray_Data.getJSONObject(i);
                String link = jsonObjectk.getString("link");
                String name = jsonObjectk.getString("name");
                String category = jsonObjectk.getString("category");
                list.add(new WebData(category,link,name));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected void JsonDataGet_article(String jsonData, List<UsefulData> list) {
        try {
            Log.e("json解析","文章");
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONObject jsonObjectData = jsonObject.getJSONObject("data");
            JSONArray jsonArray_Datas = jsonObjectData.getJSONArray("datas");

            for (int i = 0; i < jsonData.length(); i++) {
                JSONObject jsonObjectk = jsonArray_Datas.getJSONObject(i);
                String title = jsonObjectk.getString("title");
                String niceDate = jsonObjectk.getString("niceDate");
                String link = jsonObjectk.getString("link");
                String shareUser = jsonObjectk.getString("shareUser");
                String desc = jsonObjectk.getString("desc");
                String author = jsonObjectk.getString("author");
                String chapterName= jsonObjectk.getString("chapterName");
                String superChapterName= jsonObjectk.getString("superChapterName");
                String projectLink= jsonObjectk.getString("projectLink");
                int id= jsonObjectk.getInt("id");
                list.add(new UsefulData(title,niceDate,link,shareUser,desc,author,chapterName,superChapterName,projectLink,id,null,null));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected void JsonDataGet_top_article(String jsonData, List<UsefulData> list) {
        try {
            Log.e("json解析","文章");
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray jsonArray_Datas= jsonObject.getJSONArray("data");
            for (int i = 0; i < jsonData.length(); i++) {
                JSONObject jsonObjectk = jsonArray_Datas.getJSONObject(i);
                String title = jsonObjectk.getString("title");
                String niceDate = jsonObjectk.getString("niceDate");
                String link = jsonObjectk.getString("link");
                String shareUser = jsonObjectk.getString("shareUser");
                String desc = jsonObjectk.getString("desc");
                String author = jsonObjectk.getString("author");
                String chapterName= jsonObjectk.getString("chapterName");
                String superChapterName= jsonObjectk.getString("superChapterName");
                String projectLink= jsonObjectk.getString("projectLink");
                int id= jsonObjectk.getInt("id");
                list.add(new UsefulData(title,niceDate,link,shareUser,desc,author,chapterName,superChapterName,projectLink,id,"置顶   ",null));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected void JsonDataGet_project_tree(String jsonData, List<TreeData> list) {
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray jsonArray_Data = jsonObject.getJSONArray("data");
            for (int i = 0; i < jsonData.length(); i++) {
                JSONObject jsonObjectk = jsonArray_Data.getJSONObject(i);
                int id = jsonObjectk.getInt("id");
                String name = jsonObjectk.getString("name");
                list.add(new TreeData(id,name));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected void JsonDataGet_knowledge_tree(String jsonData, List<TreeData> list) {
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray jsonArray_Data = jsonObject.getJSONArray("data");
            for (int i = 0; i < jsonData.length(); i++) {
                JSONObject jsonObjectk = jsonArray_Data.getJSONObject(i);
                int id = jsonObjectk.getInt("id");
                String name = jsonObjectk.getString("name");
                list.add(new TreeData(id,name));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    protected void JsonDataGet_knowledge_tree_item(String jsonData, List<TreeData> list,String key) {
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray jsonArray_Data = jsonObject.getJSONArray("data");
            for (int i = 0; i < jsonData.length(); i++) {
                JSONObject jsonObjectk = jsonArray_Data.getJSONObject(i);
                String name = jsonObjectk.getString("name");
                if (name.equals(key)){
                JSONArray jsonArray_children = jsonObjectk.getJSONArray("children");
                for (int k=0;k<jsonArray_children.length();k++){
                    JSONObject jsonObject2=jsonArray_children.getJSONObject(k);
                    int id = jsonObject2.getInt("id");
                    String mname = jsonObject2.getString("name");
                    list.add(new TreeData(id,mname));
                }
            }}
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected void JsonDataGet_project_list(String jsonData, List<UsefulData> list) {
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONObject jsonObjectData = jsonObject.getJSONObject("data");
            JSONArray jsonArray_Datas = jsonObjectData.getJSONArray("datas");

            for (int i = 0; i < jsonData.length(); i++) {
                JSONObject jsonObjectk = jsonArray_Datas.getJSONObject(i);
                String title = jsonObjectk.getString("title");
                String niceDate = jsonObjectk.getString("niceDate");
                String link = jsonObjectk.getString("link");
                String shareUser = jsonObjectk.getString("shareUser");
                String desc = jsonObjectk.getString("desc");
                String author = jsonObjectk.getString("author");
                String chapterName= jsonObjectk.getString("chapterName");
                String superChapterName= jsonObjectk.getString("superChapterName");
                String projectLink= jsonObjectk.getString("projectLink");
                String envelopePic= jsonObjectk.getString("envelopePic");
                int id= jsonObjectk.getInt("id");
                list.add(new UsefulData(title,niceDate,link,shareUser,desc,author,chapterName,superChapterName,projectLink,id,null,envelopePic));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
