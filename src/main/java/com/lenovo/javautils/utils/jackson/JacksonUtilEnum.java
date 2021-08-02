package com.lenovo.javautils.utils.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.text.SimpleDateFormat;

public enum JacksonUtilEnum {
    //枚举单例
    INSTANCE;
    private final ObjectMapper objectMapper;

    JacksonUtilEnum() {
        String timeFormat = "yyyy-MM-dd HH:mm:ss";
        objectMapper = new ObjectMapper();
        //对象的所有字段全部列入
        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        //取消默认转换timestamps形式
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        //忽略空Bean转json的错误
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        //所有的日期格式都统一为以下的样式，即yyyy-MM-dd HH:mm:ss
        objectMapper.setDateFormat(new SimpleDateFormat(timeFormat));
        //忽略 在json字符串中存在，但是在java对象中不存在对应属性的情况。防止错误
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = JacksonUtilEnum.INSTANCE.objectMapper;
        String a = "[{\"I\":1194,\"V\":\"1.38\"},{\"I\":1196,\"V\":\"2005\"},{\"I\":5332,\"V\":\"brand=%E5%A4%A7%E4%BC%97|chexi=POLO%E5%8A%B2%E6%83%85|carchexing=2006%E6%AC%BE%20%E5%8A%B2%E6%83%85%201.4L%20%E8%87%AA%E5%8A%A8%E9%A3%8E%E5%B0%9A%E7%89%88|cheshenyanse=%E9%BB%84|buytime=2005%E5%B9%B4|shangpaiyuefen=4%E6%9C%88|cateapplyed=29|localapplyed=202|D|轿车|大众POLO劲情|合资|1.4|国Ⅲ|自动|2005\"},{\"I\":5333,\"V\":\"张女士\"},{\"I\":8428,\"V\":\"0\"},{\"I\":8432,\"V\":\"0\"},{\"I\":2539,\"V\":\"1\"},{\"I\":2546,\"V\":\"自动\"},{\"I\":2557,\"V\":\"1.4\"},{\"I\":4923,\"V\":\"2021|12\"},{\"I\":7091,\"V\":\"左前|左前|左前|左前|右后|右后|右后|内饰|内饰|后备箱||\"},{\"I\":12453,\"V\":\"773092\"},{\"I\":8929,\"V\":\"亲民车行\"},{\"I\":9184,\"V\":\"2021-11-19 09:58:09\"},{\"I\":5866,\"V\":\"411611\"},{\"I\":5867,\"V\":\"411674\"},{\"I\":5868,\"V\":\"411675\"},{\"I\":5864,\"V\":\"1_2\"},{\"I\":5865,\"V\":\"12_18\"},{\"I\":5883,\"V\":\"1900_2010\"},{\"I\":8815,\"V\":\"303\"},{\"I\":5871,\"V\":\"408988\"},{\"I\":5872,\"V\":\"408822\"},{\"I\":5870,\"V\":\"408852\"},{\"I\":1193,\"V\":\"0\"},{\"I\":4917,\"V\":\"9\"},{\"I\":2451,\"V\":\"10_16\"},{\"I\":6808,\"V\":\"583047\"},{\"I\":6809,\"V\":\"515763\"},{\"I\":6855,\"V\":\"515674\"},{\"I\":6860,\"V\":\"515684\"},{\"I\":6867,\"V\":\"2021|10\"},{\"I\":6870,\"V\":\"无商业险\"},{\"I\":7066,\"V\":\"525379\"},{\"I\":7064,\"V\":\"https://pic1.58cdn.com.cn/p1/big/n_v21113b291584140579fbacec8e5ab9230.jpg\"},{\"I\":1195,\"V\":\"14\"},{\"I\":7152,\"V\":\"0\"},{\"I\":9754,\"V\":\"L3VFB4UJ152002224\"},{\"I\":9096,\"V\":\"1\"},{\"I\":9109,\"V\":\"1\"},{\"I\":11336,\"V\":\"2\"},{\"I\":7500,\"V\":\"553664\"},{\"I\":11531,\"V\":\"23\"},{\"I\":10247,\"V\":\"松北一路74号看车\"},{\"I\":10589,\"V\":\"26000000\"},{\"I\":7603,\"V\":\"200504\"},{\"I\":8992,\"V\":\"23\"},{\"I\":11124,\"V\":\"大众 POLO劲情 2006款 劲情 1.4L 自动风尚版\"},{\"I\":10126,\"V\":\"1618753998819\"},{\"I\":10127,\"V\":\"2021-05-19 09:58:09=1.38\"},{\"I\":11182,\"V\":\"1.68,1.48,1.58,1.38\"},{\"I\":12680,\"V\":\"0\"},{\"I\":12113,\"V\":\"0\"},{\"I\":112831,\"V\":\"0\"},{\"I\":112832,\"V\":\"0\"},{\"I\":112836,\"V\":\"\"},{\"I\":112845,\"V\":\"\"},{\"I\":12340,\"V\":\"1\"},{\"I\":12435,\"V\":\"770337\"},{\"I\":12438,\"V\":\"770352\"},{\"I\":12452,\"V\":\"773032\"},{\"I\":111232,\"V\":\"您的证件图片可能存在以下问题，请修改后重新提交。\\r\\n1.当前账号的实名认证姓名，与证件图片中“所有人姓名”不一致。\\r\\n2.帖子中填写的车架号和上传的证件图片的车架号不一致。\\r\\n了解详细原因，APP端请查看&amp;lt;a style=\\&#39;color:blue\\&#39; href=https://i.58.com/5Ku97F target=&#39;_blank&#39;&amp;gt;审核规则&amp;lt;/a&amp;gt;；PC端请查看&amp;lt;a style=\\&#39;color:blue\\&#39; href=https://i.58.com/5Ku9gL target=&#39;_blank&#39;&amp;gt;审核规则&amp;lt;/a&amp;gt;|1621387401|0.1|pcenter|panxue|0|0\"},{\"I\":12549,\"V\":\"3\"},{\"I\":12550,\"V\":\"3\"},{\"I\":12551,\"V\":\"3\"},{\"I\":12552,\"V\":\"3\"},{\"I\":12553,\"V\":\"0\"},{\"I\":12570,\"V\":\"0\"},{\"I\":12578,\"V\":\"0\"},{\"I\":12688,\"V\":\"3\"},{\"I\":12712,\"V\":\"0\"},{\"I\":111275,\"V\":\"0\"},{\"I\":111596,\"V\":\"3\"},{\"I\":111597,\"V\":\"3\"},{\"I\":111653,\"V\":\"0\"},{\"I\":112691,\"V\":\"0\"},{\"I\":112690,\"V\":\"0\"},{\"I\":112693,\"V\":\"41167402\"},{\"I\":112878,\"V\":\"0\"},{\"I\":112903,\"V\":\"1\"},{\"I\":112948,\"V\":\"1\"},{\"I\":113002,\"V\":\"3\"},{\"I\":113011,\"V\":\"768,1024|0.10207486,0.3259291|0.8979573,0.81199497\"},{\"I\":113019,\"V\":\"-10\"},{\"I\":111994,\"V\":\"1\"},{\"I\":113029,\"V\":\"1\"},{\"I\":113058,\"V\":\"0\"},{\"I\":112113,\"V\":\"850514\"},{\"I\":113149,\"V\":\"0\"},{\"I\":112138,\"V\":\"850600\"},{\"I\":113170,\"V\":\"144\"},{\"I\":113178,\"V\":\"281548\"},{\"I\":113181,\"V\":\"281571\"},{\"I\":112156,\"V\":\"1\"},{\"I\":113180,\"V\":\"281566\"},{\"I\":112161,\"V\":\"1\"},{\"I\":113184,\"V\":\"1\"},{\"I\":112164,\"V\":\"1\"},{\"I\":112212,\"V\":\"45339575012283\"},{\"I\":113267,\"V\":\"1\"},{\"I\":113272,\"V\":\"0\"},{\"I\":112275,\"V\":\"0.92\"},{\"I\":112346,\"V\":\"/p1/big/n_v21b4580e1a9cd48a2acea702f68817a12.jpg\"},{\"I\":112418,\"V\":\"0\"},{\"I\":112439,\"V\":\"7\"},{\"I\":112521,\"V\":\"/p1/big/n_v2d29bd82379584af3b9717314b8172a2c.jpg\"},{\"I\":112522,\"V\":\"1\"},{\"I\":112541,\"V\":\"2\"},{\"I\":112561,\"V\":\"0.92_0.99\"},{\"I\":112560,\"V\":\"0.94\"},{\"I\":5461,\"V\":\"brand=%E5%A4%A7%E4%BC%97&amp;chexi=POLO%E5%8A%B2%E6%83%85&amp;carchexing=2006%E6%AC%BE%20%E5%8A%B2%E6%83%85%201.4L%20%E8%87%AA%E5%8A%A8%E9%A3%8E%E5%B0%9A%E7%89%88&amp;cheshenyanse=%E9%BB%84&amp;buytime=2005%E5%B9%B4&amp;shangpaiyuefen=4%E6%9C%88&amp;zimu=D&amp;objecttype=%E8%BD%BF%E8%BD%A6&amp;chexibieming=%E5%A4%A7%E4%BC%97POLO%E5%8A%B2%E6%83%85&amp;madein=%E5%90%88%E8%B5%84&amp;cheliangjibie=%E5%B0%8F%E5%9E%8B%E8%BD%A6&amp;displacement=1.4&amp;paifangbiaozhun=%E5%9B%BD%E2%85%A2&amp;gearbox=%E8%87%AA%E5%8A%A8&amp;shangshishijian=2005&amp;rundistanceqj=12-18%E4%B8%87%E5%85%AC%E9%87%8C&amp;minpriceqj=1-2%E4%B8%87%E5%85%83&amp;chelingqj=10%E5%B9%B4%E4%BB%A5%E4%B8%8A\"},{\"I\":111315,\"V\":\"0\"},{\"I\":8431,\"V\":\"0\"},{\"I\":10253,\"V\":\"678608\"},{\"I\":6691,\"V\":\"21.716747937233897\"},{\"I\":6692,\"V\":\"110.90980152053206\"},{\"I\":12615,\"V\":\"1\"},{\"I\":12326,\"V\":\"2\"},{\"I\":113173,\"V\":\"41\"},{\"I\":111524,\"V\":\"3\"},{\"I\":111592,\"V\":\"784049\"},{\"I\":111593,\"V\":\"784032\"},{\"I\":111594,\"V\":\"784021\"},{\"I\":12583,\"V\":\"779412\"},{\"I\":9057,\"V\":\"0\"},{\"I\":11074,\"V\":\"0\"},{\"I\":112344,\"V\":\"\"}]";
        JsonNode jsonNode = objectMapper.readTree(a);
    }
}
