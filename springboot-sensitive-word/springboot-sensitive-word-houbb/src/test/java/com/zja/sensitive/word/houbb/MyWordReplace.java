package com.zja.sensitive.word.houbb;

import com.github.houbb.sensitive.word.api.IWordContext;
import com.github.houbb.sensitive.word.api.IWordReplace;
import com.github.houbb.sensitive.word.api.IWordResult;
import com.github.houbb.sensitive.word.utils.InnerWordCharUtils;

/**
 * 自定义替换策略：比如【五星红旗】替换为【国家旗帜】，【毛主席】替换为【教员】
 *
 * @Author: zhengja
 * @Date: 2025-02-19 13:09
 */
public class MyWordReplace implements IWordReplace {

    @Override
    public void replace(StringBuilder stringBuilder, final char[] rawChars, IWordResult wordResult, IWordContext wordContext) {
        String sensitiveWord = InnerWordCharUtils.getString(rawChars, wordResult);
        // 自定义不同的敏感词替换策略，可以从数据库等地方读取
        if ("五星红旗".equals(sensitiveWord)) {
            stringBuilder.append("国家旗帜");
        } else if ("毛主席".equals(sensitiveWord)) {
            stringBuilder.append("教员");
        } else {
            // 其他默认使用 * 代替
            int wordLength = wordResult.endIndex() - wordResult.startIndex();
            for (int i = 0; i < wordLength; i++) {
                stringBuilder.append('*');
            }
        }
    }
}
