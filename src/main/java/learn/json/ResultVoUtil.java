package learn.json;

import java.util.List;

public class ResultVoUtil {

    public static ResultVO success(List list) {
        ResultVO resultVO = new ResultVO();
        resultVO.setData(list);
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        return resultVO;
    }

    public static ResultVO success() {
        return success(null);
    }

    public static ResultVO error(Integer code, String msg) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }
}
