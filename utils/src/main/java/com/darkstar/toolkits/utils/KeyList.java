package com.darkstar.toolkits.utils;

import android.annotation.TargetApi;
import android.os.Build;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by levy on 2015/9/23.
 * KeyList is a {@link HashMap} with index by {@link LinkedList}, for those
 * who's willing to add and remove object with both Key and Index
 */
@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public class KeyList<K, V> {
    private static final String TAG = KeyList.class.getSimpleName();

    /**
     * List of {@link K} in order
     */
    private final LinkedList<K> mKeyList = new LinkedList<>();

    /**
     * Map of {@link K} and {@link V}
     * actual holder of {@link V}
     */
    private final HashMap<K, V> mMap = new HashMap<>();

    public boolean isEmpty() {
        return mKeyList.isEmpty();
    }

    public int size() {
        return mKeyList.size();
    }

    public V put(K key, V object) {
        boolean existInList = mKeyList.contains(key);
        if (!existInList) {
            boolean retInsert = mKeyList.add(key);
            if (!retInsert) return null;
        }
        V ret = mMap.put(key, object);
        if (ret == null) {
            mKeyList.remove(key);
        }
        return ret;
    }

    public V putFirst(K key, V object) {
        int index = mKeyList.indexOf(key);
        if (index < 0) {
            if (mKeyList.offerFirst(key)) {
                V ret = mMap.put(key, object);
                if (ret == null) {
                    mKeyList.remove(key);
                    return null;
                }
                return ret;
            }
        } else {
            K k = mKeyList.remove(index);
            if (mKeyList.offerFirst(key)) {
                V ret = mMap.put(key, object);
                if (ret == null) {
                    mKeyList.add(index, k);
                }
                return ret;
            }
        }
        return null;
    }

    public V getByKey(K key) {
        return mMap.get(key);
    }

    public V getByIndex(int index) {
        if (index < 0 || index >= mKeyList.size()) return null;
        K key = mKeyList.get(index);
        return mMap.get(key);
    }

    public void clear() {
        mKeyList.clear();
        mMap.clear();
    }

    public V removeByIndex(int index) {
        K key = mKeyList.remove(index);
        return mMap.remove(key);
    }

    public V removeByKey(K key) {
        if (mKeyList.remove(key)) {
            return mMap.remove(key);
        }
        return null;
    }
}
