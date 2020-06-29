package com.throne.common;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.stream.Stream;

/**
 * 将代码文件解析成字符流后，通过该类读取，
 * 本质上是一个迭代器，对字符流进行迭代
 *
 * @param <T>
 */
public class PeekIterator<T> implements Iterator<T> {
    private static final int CACHE_SIZE = 10;

    private Iterator<T> iterator;

    // 用于缓存因预览而取出的元素，先进先出
    private LinkedList<T> outCache = new LinkedList<>();

    // 结束符
    private T _endToken = null;

    // _endToken已被同步到缓存中的标志
    private boolean endFlag = false;

    public PeekIterator(Stream<T> stream) {
        this.iterator = stream.iterator();
    }

    public PeekIterator(Stream<T> stream, T endToken) {
        this.iterator = stream.iterator();
        this._endToken = endToken;
    }

    /**
     * todo: 太罗嗦了，有时间重新改写下这段注释
     * 当缓存为空，流中无法继续迭代出元素，
     * 同时_endToken也在之前已经被同步缓存中（缓存为空说明_endToken此时已经被消费了），
     * 则表明无法继续迭代
     * 存在一种可能的情况，即缓存为空，流中无下一个元素，但_endToken还没有被同步给缓存，
     * 此时，如果_endToken为null，实际上表明整个PeekIterator无法再释放出元素了，hasNext()结果应为false，
     * 如果_endToken不为null，则还可以释放一个元素，hasNext()结果应为true，
     * 在同步缓存时，如果_endToken为null不会被放入缓存，反之则会放入缓存，
     * 因此_endToken是否为null也需要和endFlag一起纳入判断逻辑中
     *
     * @return 可以继续迭代为true，反之false
     */
    @Override
    public boolean hasNext() {
        return this.outCache.size() != 0 || this.iterator.hasNext() || (!this.endFlag || this._endToken != null);
    }

    @Override
    public T next() {
        syncElementsToCache();
        return this.outCache.poll();
    }

    public void putBack(T e) {
        this.outCache.addFirst(e);
    }

    /**
     * 将流中的元素或_endToken取出并放入缓存中，直到缓存填满或流中没有元素为止
     * 核心方法，对流和_endToken的操作都先经过缓存同步后，集中成为对缓存的操作，
     * 从而避免既操作缓存，又操作流和_endToken时因逻辑不统一出现错漏或超限的问题。
     */
    private void syncElementsToCache() {
        while ((this.outCache.size() < CACHE_SIZE) && !this.endFlag) {
            if (this.iterator.hasNext()) {
                this.outCache.add(this.iterator.next());
            } else {
                if (this._endToken != null) {
                    this.outCache.add(this._endToken);
                    this._endToken = null;
                }
                this.endFlag = true;
            }
        }
    }

    /**
     * 预览流中的下一个元素，首先判断缓存中是否有元素，
     * 没有再从流中拿，流中也没有，返回null
     * 保持每次只看缓存的第一个元素，
     *
     * @return 流或缓存中的第一个元素
     */
    public T peek() {
        syncElementsToCache();
        return this.outCache.peekFirst();
    }

}