/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.enumoption.helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import tw.dev.tomoaki.enumoption.AliasedEnum;
import tw.dev.tomoaki.enumoption.CodedEnum;
import tw.dev.tomoaki.util.commondatavalidator.ListValidator;

/**
 *
 * @author tomoaki
 */
public class EnumHelper {

    public static <T extends CodedEnum> T codeOf(String desigCode, Class<T> enumClazz) {
        if (enumClazz.isEnum()) {
            return codeOf(desigCode, enumClazz.getEnumConstants(), enumClazz);
        }
        throw new IllegalArgumentException(String.format("Class[%s] Is Not An Enum", enumClazz.getSimpleName()));
    }

    public static <T extends CodedEnum> T codeOf(String desigCode, T[] introEnums, Class<T> enumClazz) {
        //enumClazz.getEnumConstants()
        T desigIntroEnum = null;
        for (T introEnum : introEnums) {
            if (Objects.equals(introEnum.getCode(), desigCode)) {
                desigIntroEnum = introEnum;
                break;
            }
        }

        if (desigIntroEnum == null) {
            String msgFmt = "Cannot Found %s[code= %s]";
            throw new IllegalArgumentException(String.format(msgFmt, enumClazz.getSimpleName(), desigCode));
        }
        return desigIntroEnum;
    }

    public static <T extends AliasedEnum> T aliasOf(String desigAlias, Class<T> enumClazz) {
        if (enumClazz.isEnum()) {
            return aliasOf(desigAlias, enumClazz.getEnumConstants(), enumClazz);
        }
        throw new IllegalArgumentException(String.format("Class[%s] Is Not An Enum", enumClazz.getSimpleName()));
    }

    public static <T extends AliasedEnum> T aliasOf(String desigAlias, T[] aliasedEnums, Class<T> enumClazz) {
        Map<String, T> aliasedEnumKeyByAliasMap = new HashMap();
        for (T aliasedEnum : aliasedEnums) {
            List<String> aliases = aliasedEnum.getAliases();
            if (ListValidator.isListExist(aliases)) {
                for (String alias : aliases) {
                    aliasedEnumKeyByAliasMap.put(alias, aliasedEnum);
                }
            }
        }
        T desigAliasedEnum = aliasedEnumKeyByAliasMap.get(desigAlias);

        if (desigAliasedEnum == null) {
            String msgFmt = "Cannot Found %s[alias= %s]";
            throw new IllegalArgumentException(String.format(msgFmt, enumClazz.getSimpleName(), desigAlias));
        }
        return desigAliasedEnum;
    }
}
