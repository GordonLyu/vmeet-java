package com.vmeetcommon.utils;

import com.github.binarywang.java.emoji.EmojiConverter;

/**
 * @author LvXinming
 * @since 2023/6/27
 */
public class EmojiUtil {

    private static final EmojiConverter emojiConverter = EmojiConverter.getInstance();

    public static String encode(String str){
        return emojiConverter.toAlias(str);
    }

    public static String decode(String str){
        return emojiConverter.toUnicode(str);
    }
}
