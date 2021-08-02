package com.lenovo.javautils.utils;

import com.google.common.base.Splitter;
import org.apache.commons.lang.StringUtils;
import org.ho.yaml.Yaml;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {
    private static Map<String, Object> config = new HashMap<String, Object>();
    private static String context = "" +
            "#外投媒体同步到kafka\n" +
            "mediaSpm:\n" +
            "    -\n" +
            "        name: \"baidu\"\n" +
            "        spm: \"xxl_esc_baidu\"\n" +
            "        utm_source: \"market\"\n" +
            "        clickId: \"bd_vid\"\n" +
            "    -\n" +
            "        name: \"头条\" \n" +
            "        spm: \"b-31580022738699-me-f-866\"\n" +
            "        utm_source: \"market\"\n" +
            "        clickId: \"\"\n" +
            "    -\n" +
            "        name: \"百度秋实\" \n" +
            "        spm: \"sem_6e6_baiyi\"\n" +
            "        utm_source: \"market\"\n" +
            "        clickId: \"\"\n" +
            "    -\n" +
            "        name: \"uc信息流\" \n" +
            "        spm: \"b-31580022738699-me-f-868\"\n" +
            "        utm_source: \"market\"\n" +
            "        clickId: \"\"\n" +
            "    -\n" +
            "        name: \"广点通\" \n" +
            "        spm: \"b-32486882048012-me-f-813\"\n" +
            "        utm_source: \"market\"\n" +
            "        clickId: \"\"\n" +
            "    -\n" +
            "        name: \"广点通\" \n" +
            "        spm: \"b-31580022738699-me-f-906\"\n" +
            "        utm_source: \"market\"\n" +
            "        clickId: \"\"\n" +
            "    -\n" +
            "        name: \"手百\" \n" +
            "        spm: \"sem_shoubaixxl\"\n" +
            "        utm_source: \"market\"\n" +
            "        clickId: \"\"\n" +
            "    -\n" +
            "        name: \"头条（新模板）\" \n" +
            "        spm: \"u-2dfyzj8fa97pnc4f21\"\n" +
            "        utm_source: \"market\"\n" +
            "        clickId: \"\"\n" +
            "    -\n" +
            "        name: \"广点通（新模板）\" \n" +
            "        spm: \"u-2dfxy78ng97pnc4f21\"\n" +
            "        utm_source: \"market\"\n" +
            "        clickId: \"\"\n" +
            "    -\n" +
            "        name: \"vivo（新模板）\" \n" +
            "        spm: \"u-2dfyctkht97pnc4f21\"\n" +
            "        utm_source: \"market\"\n" +
            "        clickId: \"\"\n" +
            "    -\n" +
            "        name: \"WiFi\"  \n" +
            "        spm: \"u-2d4dznxhm3v43nkddh1\"\n" +
            "        utm_source: \"market\"\n" +
            "        clickId: \"\"\n" +
            "    -\n" +
            "        name: \"一点资讯\" \n" +
            "        spm: \"u-2dgefhb6c97pnc46zg\"\n" +
            "        utm_source: \"market\"\n" +
            "        clickId: \"\"\n" +
            "    -\n" +
            "        name: \"省广畅思\" \n" +
            "        spm: \"u-2d678j5xd97pnc46zg.shengguangchangsi_1\"\n" +
            "        utm_source: \"market\"\n" +
            "        clickId: \"\"\n" +
            "    -\n" +
            "        name: \"省广畅思\" \n" +
            "        spm: \"u-2d678j5xd97pnc46zg.shengguangchangsi_2\"\n" +
            "        utm_source: \"market\"\n" +
            "        clickId: \"\"\n" +
            "    -\n" +
            "        name: \"智檬dsp\" \n" +
            "        spm: \"u-2dz4fbz8997pnc4f21.ZMdsp_ESC_TTXQY&-15=20\"\n" +
            "        utm_source: \"market\"\n" +
            "        clickId: \"\"\n" +
            "    -\n" +
            "        name: \"FMOBI平台\" \n" +
            "        spm: \"u-2dzg2753w3v43nkddh1.FMOBI_ESC_TTXQY&-15=20\"\n" +
            "        utm_source: \"market\"\n" +
            "        clickId: \"\"\n" +
            "    -\n" +
            "        name: \"m360sem\" \n" +
            "        spm: \"sem_esc_xinmuban__sgsm_20190613\"\n" +
            "        utm_source: \"sem-esc-360-m\"\n" +
            "        clickId: \"\"\n" +
            "    -\n" +
            "        name: \"pc360sem\" \n" +
            "        spm: \"sem_esc_baicaipinpai__dtmax_20181029\"\n" +
            "        utm_source: \"sem-esc-360-pc\"\n" +
            "        clickId: \"\"\n" +
            "    -\n" +
            "        name: \"pc百度sem\" \n" +
            "        spm: \"sem_esc_baicaipinpai__dtmax_20181029\"\n" +
            "        utm_source: \"sem-esc-baidu-pc\"\n" +
            "        clickId: \"\"\n" +
            "    -\n" +
            "        name: \"百度sem\" \n" +
            "        spm: \"sem_esc_baicaipinpai__dtmax_20181029\"\n" +
            "        utm_source: \"sem-esc-baidu-m\"\n" +
            "        clickId: \"\"\n" +
            "    -\n" +
            "        name: \"货车百度M\" \n" +
            "        spm: \"sem_huoche\"\n" +
            "        utm_source: \"\"\n" +
            "        clickId: \"\"\n" +
            "    -\n" +
            "        name: \"货车百度PC\" \n" +
            "        spm: \"sem_huoche\"\n" +
            "        utm_source: \"\"\n" +
            "        clickId: \"\"\n" +
            "    -\n" +
            "        name: \"神马sem\" \n" +
            "        spm: \"sem_esc_baicaipinpai__dtmax_20181029\"\n" +
            "        utm_source: \"sem_esc_sm_m\"\n" +
            "        clickId: \"\"\n" +
            "    -\n" +
            "        name: \"搜狗sem\" \n" +
            "        spm: \"sem_esc_baicaipinpai__sgsm_20181102\"\n" +
            "        utm_source: \"sem_esc_sg_m\"\n" +
            "        clickId: \"\"\n" +
            "    -\n" +
            "        name: \"搜狗新模板\" \n" +
            "        spm: \"sem_esc_xinmuban__sgsm_20190613\"\n" +
            "        utm_source: \"sem_esc_sg_new_m\"\n" +
            "        clickId: \"\"\n" +
            "    -\n" +
            "        name: \"头条搜索\" \n" +
            "        spm: \"u-2d8ghaas63v43nkdq9g.toutiaoss_pinzhuan0410\"\n" +
            "        utm_source: \"market\"\n" +
            "        clickId: \"\"\n" +
            "    -\n" +
            "        name: \"头条搜索\" \n" +
            "        spm: \"u-2d8ghaas63v43nkdq9g.toutiaoss_xiangqiangye0410\"\n" +
            "        utm_source: \"market\"\n" +
            "        clickId: \"\"\n" +
            "    -\n" +
            "        name: \"头条搜索\" \n" +
            "        spm: \"u-2d8ghaas63v43nkdq9g.toutiaoss_xinxiliu0410\"\n" +
            "        utm_source: \"market\"\n" +
            "        clickId: \"\"\n" +
            "    -\n" +
            "        name: \"头条搜索\" \n" +
            "        spm: \"b-31580022738699-me-f-866.ershouche_tongyongci\"\n" +
            "        utm_source: \"market\"\n" +
            "        clickId: \"\"\n" +
            "    -\n" +
            "        name: \"头条搜索\" \n" +
            "        spm: \"\"\n" +
            "        utm_source: \"sem-toutiao-sousuo-m\"\n" +
            "        clickId: \"\"\n" +
            "    -\n" +
            "        name: \"神马货车\" \n" +
            "        spm: \"\"\n" +
            "        utm_source: \"sem-sm-huoche-m\"\n" +
            "        clickId: \"\"\n" +
            "    -\n" +
            "        name: \"神马工程车\" \n" +
            "        spm: \"\"\n" +
            "        utm_source: \"sem-sm-gongchengche-m\"\n" +
            "        clickId: \"\"\n" +
            "    -\n" +
            "        name: \"百度货车\" \n" +
            "        spm: \"\"\n" +
            "        utm_source: \"sem-baidu-huoche-m\"\n" +
            "        clickId: \"\"\n" +
            "    -\n" +
            "        name: \"百度工程车\" \n" +
            "        spm: \"\"\n" +
            "        utm_source: \"sem-baidu-gongchengche-m\"\n" +
            "        clickId: \"\"\n" +
            "    -\n" +
            "        name: \"搜狗货车\" \n" +
            "        spm: \"\"\n" +
            "        utm_source: \"sem-sg-huoche-m\"\n" +
            "        clickId: \"\"\n" +
            "    -\n" +
            "        name: \"搜狗工程车\" \n" +
            "        spm: \"\"\n" +
            "        utm_source: \"sem-sg-gongchengche-m\"\n" +
            "        clickId: \"\"\n" +
            "    -\n" +
            "        name: \"搜狗PC\" \n" +
            "        spm: \"\"\n" +
            "        utm_source: \"sem_esc_sg_pc\"\n" +
            "        clickId: \"\"\n" +
            "    -\n" +
            "        name: \"今日头条\" \n" +
            "        spm: \"\"\n" +
            "        utm_source: \"sem-toutiao-m\"\n" +
            "        clickId: \"\"\n" +
            "    -\n" +
            "        name: \"今日头条\" \n" +
            "        spm: \"b-31580022738699-me-f-866\"\n" +
            "        utm_source: \"\"\n" +
            "        clickId: \"\"\n" +
            "    -\n" +
            "        name: \"今日头条\" \n" +
            "        spm: \"u-2ercge1tx98zj69jtg.2erknfqn9wgt4eurg\"\n" +
            "        utm_source: \"\"\n" +
            "        clickId: \"\"\n" +
            "    -\n" +
            "        name: \"今日头条\" \n" +
            "        spm: \"uu-2ew7rsutzwgudhkf1\"\n" +
            "        utm_source: \"\"\n" +
            "        clickId: \"\"\n" +
            "    -\n" +
            "        name: \"广点通\" \n" +
            "        spm: \"u-Lg7H6rJ2J9tMeMj\"\n" +
            "        utm_source: \"\"\n" +
            "        clickId: \"\"\n" +
            "    -\n" +
            "        name: \"广点通\" \n" +
            "        spm: \"u-2ercgjs2x988m2wxeg.2erknzfv8wgt4eurg\"\n" +
            "        utm_source: \"\"\n" +
            "        clickId: \"\"\n" +
            "    -\n" +
            "        name: \"百度信息流\" \n" +
            "        spm: \"\"\n" +
            "        utm_source: \"sem-baidu-xxl\"\n" +
            "        clickId: \"\"\n" +
            "    -\n" +
            "        name: \"百度信息流\" \n" +
            "        spm: \"u-2by5ssayp3v43nkdq9g\"\n" +
            "        utm_source: \"\"\n" +
            "        clickId: \"\"\n" +
            "    -\n" +
            "        name: \"vivo\" \n" +
            "        spm: \"u-2dar7vzsz3v43nkddh1\"\n" +
            "        utm_source: \"\"\n" +
            "        clickId: \"\"\n" +
            "    -\n" +
            "        name: \"oppo\" \n" +
            "        spm: \"u-2d7xmzyvg3v43nkdq9g\"\n" +
            "        utm_source: \"\"\n" +
            "        clickId: \"\"\n" +
            "    -\n" +
            "        name: \"华为\" \n" +
            "        spm: \"u-2d9hp21jx3v43nkddh1\"\n" +
            "        utm_source: \"\"\n" +
            "        clickId: \"\"\n" +
            "    -\n" +
            "        name: \"小米\" \n" +
            "        spm: \"u-2d95msgf897pnc46zg\"\n" +
            "        utm_source: \"\"\n" +
            "        clickId: \"\"";

    public static <T> List<T> getListConfig(String key) throws FileNotFoundException {
        config = (Map<String, Object>) Yaml.load(context);
        return getConfig(key, new ArrayList<>());
    }

    private static <T> T getConfig(String key, T defaultValue) {
        List<String> keys = Splitter.on(".").splitToList(key);
        Object value = findMap(keys, 0, config);

        if (defaultValue.getClass().isInstance(value)) {
            return (T) value;
        }
        return defaultValue;
    }

    private static Object findMap(List<String> keys, int deep, Map<String, Object> map) {
        if (deep > keys.size()) {
            return null;
        }
        String currKey = keys.get(deep);
        if (keys.size() == (deep + 1)) {
            return map.get(currKey);
        }
        Map<String, Object> currObj = (Map<String, Object>) map.get(currKey);
        if (currObj == null) {
            return null;
        }
        return findMap(keys, deep + 1, currObj);
    }

    public static void main(String[] args) throws FileNotFoundException {
        String spm = "180608711922.10982786832562944552";
        String utmSource = "sem-esc-baidu-m";
        List<Map<String, String>> mediaSpm = getListConfig("mediaSpm");
        mediaSpm.stream()
                .filter(key -> {
                    String cSpm = key.get("spm");
                    String cUtmSource = key.get("utm_source");
                    System.out.println("cSpm:" + cSpm);
                    System.out.println("cUtmSource:" + cUtmSource);
                    boolean b1 = spm.equalsIgnoreCase(cSpm);
                    boolean b2 = utmSource.equalsIgnoreCase(cUtmSource);
                    boolean b = (StringUtils.isEmpty(cSpm) || spm.contains(cSpm))
                            && (StringUtils.isEmpty(cUtmSource) || utmSource.contains(cUtmSource));
                    System.out.println(b1);
                    System.out.println(b2);
                    System.out.println(b1 || b2);
                    return b1 && b2;
                })
                .forEach(key -> {
                    String name = key.get("name");
                    String clickId = key.get("clickId");
                    String clickIdValue = "";
                    System.out.println("获取到");
                });


//        for (Map<String, String> map : mediaSpm) {
//            String cSpm = map.get("spm");
//            String cUtmSource = map.get("utm_source");
//            if (StringUtils.isBlank(cSpm) && StringUtils.isBlank(cUtmSource)) {
//                continue;
//            }
//            if (spm.contains(cSpm) || utmSource.contains(cUtmSource)) {
//                String name = map.get("name");
//                String clickId = map.get("clickId");
//                String clickIdValue = "";
//                System.out.println("gey");
//            }
//        }

    }
}
