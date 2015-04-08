package mybatis.handler;

import java.util.Date;

/**
 * Created by Irrielde on 2.4.2015.
 */
public class TwoDateTwoIntParams {

       private Date date1;

    private Date date2;

    private Integer integer1;
    private Integer integer2;

    public TwoDateTwoIntParams(Date since, Date now, Integer pageIndex, Integer numberOfPages) {
        date1= since;
        date2=now;
        integer1 = pageIndex;
        integer2 = numberOfPages;
    }


    public Date getDate1() {
        return date1;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    public Date getDate2() {
        return date2;
    }

    public void setDate2(Date date2) {
        this.date2 = date2;
    }

    public Integer getInteger1() {
        return integer1;
    }

    public void setInteger1(Integer integer1) {
        this.integer1 = integer1;
    }

    public Integer getInteger2() {
        return integer2;
    }

    public void setInteger2(Integer integer2) {
        this.integer2 = integer2;
    }
}
