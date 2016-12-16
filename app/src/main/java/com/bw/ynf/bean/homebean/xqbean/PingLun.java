package com.bw.ynf.bean.homebean.xqbean;

/**评论
 * Created by GaoJun on 2016/12/16 0016.
 */
public class PingLun {
    private String content;
    private String createtime;
    private User user;

    public PingLun() {
    }

    public PingLun(String content, String createtime, User user) {
        this.content = content;
        this.createtime = createtime;
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
