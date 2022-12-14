package seb39_40.coffeewithme.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserRequestDto {
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    //간단한 유효성 검사 진행 - 1. 비었는지 2. 글자 수 확인 3. 이메일 형식인지
    public static class UserJoin{
        @NotBlank
        @Size(max=50,message="이름은 50자미만으로 작성해야 합니다.")
        private String userName;
        @NotBlank
        @Size(max=20,message="비밀번호는 20자미만으로 작성해야 합니다.")
        private String password;
        @Email
        @Size(max=20,message="이메일은 20자미만으로 작성해야 합니다.")
        private String email;
        @NotBlank
        @Size(max=11,message="핸드폰 번호는 11자미만으로 작성해야 합니다.")
        //핸드폰 형식 검사하는 거 추가하기
        private String mobile;
    }
}
