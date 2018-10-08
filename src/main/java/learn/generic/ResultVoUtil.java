package learn.generic;

import learn.apacheCommons.Student;

import java.util.Date;

public class ResultVoUtil<T> {

    public static <T>ResultVO success(T object) {
        ResultVO resultVO = new ResultVO(ResultVO.SUCCESS,"success",object);
        return  resultVO;
    }

    public static void main(String[] args) {
        Student student = new Student("aaa", 28, 100, new Date());
        ResultVO vo = ResultVoUtil.<Student>success(student);
        System.out.println(vo);
    }
}
