package com.example.dailytest.demos.Service.Impl;

import com.example.dailytest.demos.Service.DesensitizationService;
import org.springframework.stereotype.Service;

/**
 * @author chenxin
 * @Description
 * @date 2023/12/19 10:02
 */
@Service
public class DesensitizationPhoneNumServiceImpl implements DesensitizationService {
    @Override
    public String desensitizationField(String originalField) {
        return hide(originalField, 3, 7);
    }
    public static String hide(CharSequence str, int startInclude, int endExclude) {
        return replace(str, startInclude, endExclude, '*');
    }
    public static String replace(CharSequence str, int startInclude, int endExclude, char replacedChar) {
        if (isEmpty(str)) {
            return str(str);
        } else {
            String originalStr = str(str);
            int[] strCodePoints = originalStr.codePoints().toArray();
            int strLength = strCodePoints.length;
            if (startInclude > strLength) {
                return originalStr;
            } else {
                if (endExclude > strLength) {
                    endExclude = strLength;
                }

                if (startInclude > endExclude) {
                    return originalStr;
                } else {
                    StringBuilder stringBuilder = new StringBuilder();

                    for(int i = 0; i < strLength; ++i) {
                        if (i >= startInclude && i < endExclude) {
                            stringBuilder.append(replacedChar);
                        } else {
                            stringBuilder.append(new String(strCodePoints, i, 1));
                        }
                    }

                    return stringBuilder.toString();
                }
            }
        }
    }

    public static boolean isEmpty(CharSequence str) {
        return str == null || str.length() == 0;
    }

    public static String str(CharSequence cs) {
        return null == cs ? null : cs.toString();
    }
}
