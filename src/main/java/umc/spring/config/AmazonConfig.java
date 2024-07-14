package umc.spring.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 12주차 사진도 같이 업로드하기

// 이제 AWS S3에 사진을 업로드 하기 위한
// 설정 정보가 담긴 클래스를 만들어 줍시다.
// 해당 클래스는 순수하게 AWS의 서비스를 사용하기 위한
// 설정 정보가 담기게 됩니다.
// 11주차 CI/CD에서도 AWS는 보안에 굉장히 민감하고
// 외부에서 AWS의 서비스를 사용하기 위해
// Access Key와 Secret Key를 발급했죠?
// 그런 정보를 담아 둘 클래스라고 생각하면 됩니다.
@Configuration
@Getter
public class AmazonConfig {

    private AWSCredentials awsCredentials;
    // 이제 저 키를 어디에 보관 해야할지가 문제이죠
    // CI/CD에서는 깃허브 액션에서 사용이 되니
    // 리포지토리의 키에 저장을 했죠?
    // 지금은 어떻게 할까요?
    // 여기서 환경변수라는 개념을 활용 할 수 있습니다.
    // 일단 무식하게 아래처럼 가능합니다.
//    private String accessKey = "Springboot 액세스 키";
//    private String secretKey = "Springboot 비밀 액세스 키";
//    private String region = "ap-northeast-2";
    // 네. 저렇게 했다가 깃허브에 올리면 바로 털리겠죠?
    // 따라서 저런 상수 값들은 저렇게 직접 가져다 넣기 보다는
    // application.yml에 두고 가져오는 것이 좋습니다.
    // application.yml에서 처음 시작이 cloud
    // (잘 보면 spring이랑 별개임)
    // 1depth 아래가 aws 그리고 s3, region 등등이 같은 depth이죠
    // 저렇게 depth에 맞춰서 어떤 값을 가져올지 기입하면 됩니다.
    @Value("${cloud.aws.credentials.accessKey}")
    private String accessKey;
    @Value("${cloud.aws.credentials.secretKey}")
    private String secretKey;
    @Value("${cloud.aws.region.static}")
    private String region;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    // 버킷의 정보 지정
    @Value("${cloud.aws.s3.path.review}")
    private String reviewPath;



    @PostConstruct
    public void init(){

        this.awsCredentials = new BasicAWSCredentials(accessKey, secretKey);

    }

    @Bean
    public AmazonS3 amazonS3(){

        AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);

        return AmazonS3ClientBuilder.standard()
                .withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();

    }

    @Bean
    public AWSCredentialsProvider awsCredentialsProvider() {

        return new AWSStaticCredentialsProvider(awsCredentials);

    }

}
