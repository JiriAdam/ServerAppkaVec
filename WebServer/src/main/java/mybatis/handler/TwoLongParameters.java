package mybatis.handler;

/**
 * Created by Irrielde on 25.3.2015.
 * Wrapper for multiple (Two) arguments for mybatis parameters in sql queries
 */
public class TwoLongParameters {

    private Long param1;

    private Long param2;

    public TwoLongParameters(Long param1, Long param2){
        this.param1 = param1;
        this.param2 = param2;
    }

    public Long getParam1() {
        return param1;
    }

    public void setParam1(Long param1) {
        this.param1 = param1;
    }

    public Long getParam2() {
        return param2;
    }

    public void setParam2(Long param2) {
        this.param2 = param2;
    }
}
