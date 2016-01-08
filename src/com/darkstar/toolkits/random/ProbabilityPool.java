package com.darkstar.toolkits.random;

import java.util.Random;

/**
 * Created by levy on 2015/12/31.
 * This is a probability generator and have three way to produce probability in condition
 * It is useful to generate bonus rank in lottery situation
 */
public class ProbabilityPool {
    private static final String TAG = ProbabilityPool.class.getSimpleName();

    public static final int RESULT_NO_HIT = -1;

    /**
     * In condition, the RESULT_NO_HIT will be return
     */
    public static final int FILL_NO_HIT = 0;
    /**
     * In condition, the RESULT_NO_HIT will be return only pool is empty
     */
    public static final int FILL_NOTHING = 1;
    /**
     * In condition, the index fill will be enlarged every time when other
     * probability has generated, that means the probability of a specific index will grow
     */
    public static final int FILL_INDEX = 2;

    /**
     * Whether the probability of the pool is condition probability
     */
    private boolean mCondition;
    /**
     * only make sense when mCondition is true
     */
    private int mFillType;

    /**
     * only make sense when mFillType is FILL_INDEX
     */
    private int mFillIndex;

    private int[] mPool;

    /**
     * for rest
     */
    private int[] mOriginPool;

    private int mMax;

    public ProbabilityPool() {
    }

    public synchronized void config(Integer... probability) {
        config(false, probability);
    }

    public synchronized void config(boolean isCondition, Integer... probability) {
        config(FILL_NO_HIT, 0, isCondition, probability);
    }

    public synchronized void config(int fillType, int fillIndex, boolean isCondition,
                                    /**
                                     * must be order by rank
                                     */
                                    Integer... probability) {
        mCondition = isCondition;
        mFillType = fillType;
        mFillIndex = fillIndex;
        mOriginPool = new int[probability.length];
        mPool = new int[probability.length];
        mMax = 0;
        for (int i = 0; i < probability.length; i++) {
            mMax += probability[i];
            mOriginPool[i] = mPool[i] = mMax;
        }
    }

    /**
     * generate a index by specific probability table
     * @return index from [0, mPool.size() - 1]
     */
    public synchronized int next() {
        if (mPool == null || mPool.length == 0) return -1;
        int index = new Random().nextInt(Math.max(1, mMax));
        int result = RESULT_NO_HIT;
        for (int i = 0; i < mPool.length; i++) {
            if (((i == 0) ? 0 : mPool[i - 1]) <= index && index < mPool[i]) {
                result = i;
                break;
            }
        }
        if (!mCondition || result == RESULT_NO_HIT) return result;
        switch (mFillType) {
            case FILL_NOTHING: {
                for (int i = result; i < mPool.length; i++) {
                    mPool[i] -= 1;
                }
                mMax--;
                break;
            }
            case FILL_NO_HIT: {
                for (int i = result; i < mPool.length; i++) {
                    mPool[i] -= 1;
                }
                break;
            }
            case FILL_INDEX: {
                if (mFillIndex < result) {
                    for (int i = mFillIndex; i < result; i++) {
                        mPool[i] += 1;
                    }
                } else if (mFillIndex > result) {
                    for (int i = result; i < mFillIndex; i++) {
                        mPool[i] -= 1;
                    }
                }
            }
            default:
                break;
        }
        return result;
    }

    /**
     * re-config pool, only make sense for Condition Probability
     * @return true if reset succeed
     */
    public synchronized boolean reset() {
        if (!mCondition) return false;
        if (mOriginPool == null || mPool == null || mOriginPool.length != mPool.length) {
            return false;
        }
        mMax = 0;
        for (int i = 0; i < mOriginPool.length; i++) {
            mMax += mOriginPool[i];
            mPool[i] = mOriginPool[i];
        }
        return true;
    }
}
