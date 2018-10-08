package learn.httpclient;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.common.collect.Maps;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpClientUtil {

    public static String post(String url,Map<String ,String > param,Map<String ,String > header) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        List<NameValuePair> formList = new ArrayList<>();
        for (Map.Entry<String, String> entry : param.entrySet()) {
            formList.add(new BasicNameValuePair(entry.getKey(),entry.getValue()));
        }
        StringEntity entity = new UrlEncodedFormEntity(formList, "utf-8");
        post.setEntity(entity);

        for (Map.Entry<String, String> entry : header.entrySet()) {
            post.setHeader(new BasicHeader(entry.getKey(),entry.getValue()) );
        }
        CloseableHttpResponse response = client.execute(post);
        //获取响应码
        int statusCode = response.getStatusLine().getStatusCode();

        if (statusCode == HttpStatus.SC_OK)
            return  EntityUtils.toString(response.getEntity());
        return null;
    }

    public static String get(String url, Map<String ,String > param) {

        StringBuilder sb = new StringBuilder(url);
        for (Map.Entry<String, String> entry : param.entrySet()) {
            try {
                sb.append("&").append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue(),"UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        HttpGet get = new HttpGet(sb.toString());
        HttpClient httpClient = HttpClients.createDefault();
        try {
            HttpResponse response = httpClient.execute(get);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String result = EntityUtils.toString(response.getEntity(), "UTF-8");
                return result;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
//        String url = "http://api.map.baidu.com/geocoder/v2/?";
//        Map<String ,String > param = Maps.newHashMap();
//        param.put("address","海淀区丹棱街16号海兴大厦");
//        param.put("city","北京");
//        param.put("output","json");
//        param.put("ak","6QtSF673D1pYl3eQkEXfwp8ZgsQpB77U");
//        String result = get(url,param);
//        System.out.println(result);

        System.out.println("post-------");

        String url = "http://erp.check.jzt.jd.com:8080/audit/material/queryAuditExternalList";
        Map<String ,String > param = Maps.newHashMap();
        param.put("itemsPerPage","10");
        param.put("page","1");

        Map<String ,String > header = Maps.newHashMap();
        header.put("Cookie","shshshfpa=1e0ef6cb-a12e-43d8-8e59-8529561e7215-1534486742; shshshfpb=2d9d40118cb3f40079083e914861d51f15b7668d93b4f48e95b6e10537; 3AB9D23F7A4B3C9B=6U43ZFZAJLHWEKPL2IDEFDIUB6WDNHZ2ZMCALRR2LNNEGJ5IHZBRYIXCUER2L2PPDCZBOXVJYH4FRQ6ZIGI4SH7SDU; wxa_level=1; retina=0; cid=9; webp=1; __jda=122270672.15373398276971702481818.1537339827.1537339827.1537339827.1; __jdv=122270672|direct|-|none|-|1537339827697; mba_muid=15373398276971702481818; mobilev=html5; visitkey=2622362539389370; __wga=1537339829356.1537339829356.1537339829356.1537339829356.1.1; PPRD_P=UUID.15373398276971702481818-LOGID.1537339829386.1620802764; sc_width=1536; __jdu=2622362539389370; shshshfp=9089ab399269c14c6d5cca8a4290c795; erp1.jd.com=91029897E399D0A0813530670241B2FCAA9D025170E8AA2E289D9981A67EDC15EA4B14204FAB0BEB69A5A4D78025A032CF08DC416E5389CA03E1FD297C595D2849850574E79A5F47EB2225754CF9F87F; sso.jd.com=0b7009aaf1de447f986e07a74cf265c4");
        String result =  post(url,param,header);
        System.out.println(result);
        //{"success":true,"code":"1","errorMsg":null,"data":{"count":{"allAuditCount":353,"tobeAuditCount":5,"needAuditCount":59,"backAuditCount":282,"passAuditCount":4},"page":1,"pageSize":10,"list":[{"adId":965665,"videoSrc":"http://test.storage.jd.com/null","videoImageUrl":"//img1.360buyimg.com/pop/http://storage.jd.com/audit-public/video-img540_870.jpg","logoUrl":"//img1.360buyimg.com/pop/http://storage.jd.com/audit-public/video-logo200_200.jpg","adTitle":null,"pin":"","createdTime":1522318570000,"auditType":65,"planId":null,"approveTime":null,"rejectTime":1523178354000,"auditStatus":-2,"auditInfo":"修改计划，被覆盖","startCreatedTime":null,"endCreatedTime":null,"dspid":null},{"adId":205485014,"videoSrc":null,"videoImageUrl":null,"logoUrl":null,"adTitle":null,"pin":"戴尔_Dell","createdTime":1522664274000,"auditType":65,"planId":null,"approveTime":1522744267000,"rejectTime":1522744267000,"auditStatus":-2,"auditInfo":"审核通过","startCreatedTime":null,"endCreatedTime":null,"dspid":null},{"adId":205485014,"videoSrc":null,"videoImageUrl":null,"logoUrl":null,"adTitle":null,"pin":"戴尔_Dell","createdTime":1522664600000,"auditType":65,"planId":null,"approveTime":1522744267000,"rejectTime":1522744267000,"auditStatus":-2,"auditInfo":"审核通过","startCreatedTime":null,"endCreatedTime":null,"dspid":null},{"adId":205485014,"videoSrc":null,"videoImageUrl":null,"logoUrl":null,"adTitle":null,"pin":"戴尔_Dell","createdTime":1522722793000,"auditType":65,"planId":null,"approveTime":1522744267000,"rejectTime":1522744267000,"auditStatus":-2,"auditInfo":"审核通过","startCreatedTime":null,"endCreatedTime":null,"dspid":null},{"adId":205485014,"videoSrc":null,"videoImageUrl":null,"logoUrl":null,"adTitle":null,"pin":"戴尔_Dell","createdTime":1522744313000,"auditType":65,"planId":null,"approveTime":1522744267000,"rejectTime":1522744701000,"auditStatus":-2,"auditInfo":"审核驳回","startCreatedTime":null,"endCreatedTime":null,"dspid":null},{"adId":1120172624,"videoSrc":"http://test.storage.jd.com/material-video/20180408/6bae573e698aeb8915a4c0d57ac870a0.mp4","videoImageUrl":"//img1.360buyimg.com/pop/jfs/t10/360/511132848/106801/c4900147/5ac9e34cN17d8d0a3.jpg","logoUrl":"//img1.360buyimg.com/pop/jfs/t28/188/392518917/28488/c0e7f579/5ac9e353N4515696f.jpg","adTitle":"ceshiceshi","pin":"sop_order","createdTime":1523188907000,"auditType":65,"planId":null,"approveTime":null,"rejectTime":1523190239000,"auditStatus":-2,"auditInfo":"title: 标题字段(title)必须在6-25","startCreatedTime":null,"endCreatedTime":null,"dspid":null},{"adId":1120172920,"videoSrc":"http://test.storage.jd.com/material-video/20180409/9fb069968f75a0bd4d8a53dd0a8bc4bd.mp4","videoImageUrl":"//img1.360buyimg.com/pop/jfs/t28/134/407020134/106801/c4900147/5acacd92N72f0d218.jpg","logoUrl":"//img1.360buyimg.com/pop/jfs/t34/27/906856512/28488/c0e7f579/5acacda9N467a47d8.jpg","adTitle":"主题主题主题主题","pin":"sop_order","createdTime":1523260682000,"auditType":65,"planId":null,"approveTime":null,"rejectTime":1523260886000,"auditStatus":-2,"auditInfo":"height: height字段不正确,必须是870","startCreatedTime":null,"endCreatedTime":null,"dspid":null},{"adId":1120173104,"videoSrc":"http://test.storage.jd.com/material-video/20180409/2df3817c6616b156bb0552dae14dc54f.mp4","videoImageUrl":null,"logoUrl":null,"adTitle":null,"pin":"sop_order","createdTime":1523265377000,"auditType":65,"planId":null,"approveTime":null,"rejectTime":1523265438000,"auditStatus":-2,"auditInfo":"图片存在问题,请检查","startCreatedTime":null,"endCreatedTime":null,"dspid":null},{"adId":1120170514,"videoSrc":"http://test.storage.jd.com/material-video/20180404/cfd3989b3c85d5e2b8ea411bdb453068.MP4","videoImageUrl":"//img1.360buyimg.com/pop/jfs/t31/42/318222465/24239/9b71895c/5ac4bb7eN45bbe09f.jpg","logoUrl":"//img1.360buyimg.com/pop/jfs/t34/52/921809912/28488/c0e7f579/5ac4bb90N30a67c8f.jpg","adTitle":"123456","pin":"sop_order","createdTime":1523346443000,"auditType":65,"planId":null,"approveTime":null,"rejectTime":1523346535000,"auditStatus":-2,"auditInfo":"内审下线","startCreatedTime":null,"endCreatedTime":null,"dspid":null},{"adId":1120170518,"videoSrc":"http://test.storage.jd.com/material-video/20180404/ac598a1521be2795969f314bfb65f4bc.MP4","videoImageUrl":"//img1.360buyimg.com/pop/jfs/t16/177/259440781/24239/9b71895c/5ac4bc06N2924c502.jpg","logoUrl":"//img1.360buyimg.com/pop/jfs/t19/246/376988313/28488/c0e7f579/5ac4bc0bNdbbc0951.jpg","adTitle":"123456","pin":"sop_order","createdTime":1523346568000,"auditType":65,"planId":null,"approveTime":1523346725000,"rejectTime":null,"auditStatus":-2,"auditInfo":null,"startCreatedTime":null,"endCreatedTime":null,"dspid":null}],"totalPage":36}}
        ObjectMapper mapper = new ObjectMapper();
        JsonNode treeNode = mapper.readTree(result);
        System.out.println(treeNode.get("success").asBoolean());

        JsonNode dataNode = treeNode.get("data");
        System.out.println(dataNode.get("count").get("allAuditCount").asInt());

        System.out.println(dataNode.get("list").get(0).get("adId").asLong());

        for (JsonNode node : dataNode.get("list")) {
            String videoSrc = node.get("videoSrc").asText();
            long adId = node.get("adId").asLong();
            String r = String .format("adId:%d and videoSrc:%s",adId,videoSrc);
            System.out.println(r);
        }
}
}
