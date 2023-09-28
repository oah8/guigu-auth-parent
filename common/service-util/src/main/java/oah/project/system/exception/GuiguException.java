package oah.project.system.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import oah.project.common.result.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName GuiguException
 * @Description TODO
 * @Author _oah
 * @Date 2023.09.09 19:52
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuiguException extends RuntimeException{

    private Integer code;
    private String msg;

}
