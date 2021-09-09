package com.mellow.aliyunoss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.AccessControlList;
import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.CannedAccessControlList;
import org.junit.Test;

import java.util.List;

public class testAliyunOss {

    // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
    private String endpoint = "oss-cn-shenzhen.aliyuncs.com";
    // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
    private String accessKeyId = "LTAI5tSLGQLydzexjnWnJDFr";
    private String accessKeySecret = "BudpMsQdeUEXgHz59FT1PQRePA8VJP";

    private String bucketName = "srb-file-20210908-1";

    @Test
    public void testCreateBucket() {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        String location = ossClient.getBucketLocation(bucketName);
        System.out.println(location);

        // 关闭OSSClient。
        ossClient.shutdown();
    }

    @Test
    public void testBucketLocation() {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 创建存储空间。
        ossClient.createBucket(bucketName);

        String location = ossClient.getBucketLocation(bucketName);
        System.out.println(location);

        // 关闭OSSClient。
        ossClient.shutdown();


    }

    @Test
    public void testBucketAcl() {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 获取存储空间的访问权限。
        AccessControlList acl = ossClient.getBucketAcl(bucketName);
        System.out.println(acl.toString());

        // 关闭OSSClient。
        ossClient.shutdown();
    }

    @Test
    public void testBucketControl() {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 设置存储空间的访问权限为PublicRead。
        ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);

        // 获取存储空间的访问权限。
        AccessControlList acl = ossClient.getBucketAcl(bucketName);
        System.out.println(acl.toString());

        // 关闭OSSClient。
        ossClient.shutdown();

    }

    @Test
    public void test() {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 列举存储空间。
        List<Bucket> buckets = ossClient.listBuckets();
        for (Bucket bucket : buckets) {
            System.out.println(" - " + bucket.getName());
        }

        // 关闭OSSClient。
        ossClient.shutdown();

    }

    @Test
    public void testBucketExist() {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 判断存储空间examplebucket是否存在。如果返回值为true，则存储空间存在，否则存储空间不存在。
        boolean exists = ossClient.doesBucketExist(bucketName);
        System.out.println("存储空间是否存在:" + exists);

        // 关闭OSSClient。
        ossClient.shutdown();

    }
}
