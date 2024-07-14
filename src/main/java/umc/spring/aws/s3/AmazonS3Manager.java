package umc.spring.aws.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import umc.spring.config.AmazonConfig;
import umc.spring.domain.Uuid;
import umc.spring.repository.UuidRepository;

import java.io.IOException;

// 12주차 사진도 같이 업로드하기

// 이제 다음으로 AWS S3에 사진을 업로드 하는 매서드를 가진
// AmazonS3Manager라는 클래스를 만들어서 사용합시다.
// S3 말고도 다른 AWS의 서비스를 사용 할 수 있으니
// aws라는 폴더에 s3라는 폴더를 만들고
// 하위에 AmazonS3Manager를 만들어 두겠습니다.
@Slf4j
@Component
@RequiredArgsConstructor
public class AmazonS3Manager {

    private final AmazonS3 amazonS3;
    // amazonS3는 저희가 만들 필요는 없고 build.gradle에서
    // amazon 관련 외부 모듈을 받으면 사용이 가능합니다.
    // 해당 amazonS3가 제공하는 메서드를 사용하면 됩니다.
    private final AmazonConfig amazonConfig;
    // amazonConfig는 S3를 사용하기 위해서 필요한
    // 인증에 대한 과정 등이 포함이 됩니다.
    // 인증이 되었다는 것을 가지고 AmazonS3를 사용해서
    // 파일을 업로드하면 됩니다
    private final UuidRepository uuidRepository;

    // AWS S3를 사용해서 파일을 업로드하고 그 결과로
    // 우리가 필요한 것은 파일의 URL입니다.
    // uploadFile은 내부적으로 Amazon이 제공하는
    // putObject 메서드를 사용 할 것입니다.
    public String uploadFile(String KeyName, MultipartFile file){

        System.out.println(KeyName);

        ObjectMetadata metadata = new ObjectMetadata();
        // metaData는 필수는 아니고 추가적인 정보를 담아주는 것입니다.
        metadata.setContentLength(file.getSize());

        try {

            amazonS3.putObject(new PutObjectRequest(amazonConfig.getBucket(), KeyName, file.getInputStream(), metadata));
            // putObject 메서드는 PutObjectRequest를
            // 파라미터로 받아 S3 버킷에 저장합니다.
            // amazonS3의 putObject 매서드에 파라미터로
            // PutObjectReqeust를 담는 것을 확인 가능하며,
            // PutObjectRequest를 만들 때,
            // 어떤 S3의 버킷에 올릴지, 식별 할 이름은 무엇인지,
            // 그리고 파일의 데이터는 무엇인지를 담죠.
            // keyName과 file의 경우는 당연히
            // 해당 매서드를 호출할 서비스 계층에서 담아줍니다!

            // 이제 저희가 S3에 사진을 업로드 할 때
            // 이렇게 요청을 해야합니다.
            // 어떤 버킷의 특정 디렉토리에 이런 식별자로
            // 업로드 해줘!
            // amazonConfig.getBucket()
            // 어떤 버킷인지 가져온다
            // KeyName
            // 어떤 디렉토리의 어떤 식별자인지 KeyName으로 지정한다

        }catch (IOException e){

            log.error("error at AmazonS3Manager uploadFile : {}", (Object) e.getStackTrace());

        }

        return amazonS3.getUrl(amazonConfig.getBucket(), KeyName).toString();
        // 우리는 getUrl 메서드를 이용해서 버킷에 저장된
        // 파일의 URL을 받아서 최종적으로 return합니다.

    }

    // 이제 KeyName을 만들어서 리턴 해주는 매서드를
    // Manager에 추가해봅시다!
    public String generateReviewKeyName(Uuid uuid){

        return amazonConfig.getReviewPath() + '/' + uuid.getUuid();

    }

}
