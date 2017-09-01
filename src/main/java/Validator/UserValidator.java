package Validator;

import com.ssm.model.User;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;

/**
 * 用户的检验类
 * Created by acer on 2017/7/9.
 */
public class UserValidator implements Validator{


    //判断是不是User实体类
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {
        System.out.println("进入校验");

    }

    /**
     * 检验登录参数是否正确
     * @param user
     * @param bindingResult
     * @return
     */
    public static boolean loginParam(@Validated(value={ValidatorGroup1.class}) User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return false;
        }else{
            return true;
        }
    }
}
