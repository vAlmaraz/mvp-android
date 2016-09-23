package com.valmaraz.mvp.model;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.valmaraz.mvp.Log;
import com.valmaraz.mvp.model.entity.CachedData;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vAlmaraz on 18/09/2016.
 * https://www.valmaraz.com
 */

public abstract class BaseCache {

    private static final String TAG = "BaseCache";

    protected Gson gson;

    private File cacheFile;

    public BaseCache(Context context, String cacheFileName) {
        cacheFile = new File(context.getCacheDir(), cacheFileName);
        gson = new Gson();
    }

    // =============================================================================
    // PROTECTED METHODS
    // =============================================================================

    protected List<CachedData> getCachedDataList() {
        List<CachedData> cachedDataList = new ArrayList<>();

        String json = readCacheFile();
        if (!TextUtils.isEmpty(json)) {
            Type listType = new TypeToken<ArrayList<CachedData>>() {
            }.getType();
            cachedDataList = new Gson().fromJson(json, listType);
        }
        return cachedDataList;
    }

    protected CachedData find(String id) {
        CachedData foundCachedData = null;
        CachedData searchCachedData = new CachedData(id);

        List<CachedData> cachedDataList = getCachedDataList();
        for (CachedData cd : cachedDataList) {
            if (searchCachedData.id.equals(cd.id)) {
                foundCachedData = cd;
                break;
            }
        }
        return foundCachedData;
    }

    protected void save(CachedData cachedData) {
        // Find and update object or create new one
        boolean found = false;
        List<CachedData> cachedDataList = getCachedDataList();
        for (CachedData cd : cachedDataList) {
            if (cachedData.id.equals(cd.id)) {
                cd.saveResponse(cachedData.responseJson);
                found = true;
                break;
            }
        }
        if (!found) {
            cachedDataList.add(cachedData);
        }
        saveCacheFile(new Gson().toJson(cachedDataList));
    }

    // =============================================================================
    // PRIVATE METHODS
    // =============================================================================

    private String readCacheFile() {
        String fileContent = null;
        int length = (int) cacheFile.length();
        if (length > 0) {
            try {
                byte[] bytes = new byte[length];
                FileInputStream in = new FileInputStream(cacheFile);
                in.read(bytes);
                in.close();
                fileContent = new String(bytes);
            } catch (IOException e) {
                Log.e(TAG, Log.getStackTraceString(e));
            }
        }
        return fileContent;
    }

    private void saveCacheFile(String fileContent) {
        try {
            FileOutputStream stream = new FileOutputStream(cacheFile);
            stream.write(fileContent.getBytes());
            stream.close();
        } catch (IOException e) {
            Log.e(TAG, Log.getStackTraceString(e));
        }
    }
}
