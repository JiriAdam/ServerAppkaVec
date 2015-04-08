package mybatis.handler;

/**
 * Created by Irrielde on 1.4.2015.
 */
public class LongIntParameters {

    private Long param1;

    private Integer param2;

    public LongIntParameters(Long param1, Integer param2) {
        this.param1 = param1;
        this.param2 = param2;
    }


    public Long getParam1() {
        return param1;
    }

    public void setParam1(Long param1) {
        this.param1 = param1;
    }

    public Integer getParam2() {
        return param2;
    }

    public void setParam2(Integer param2) {
        this.param2 = param2;
    }
}
