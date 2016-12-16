package com.bw.ynf.bean.homebean.xqbean;

/**用户信息
 * Created by GaoJun on 2016/12/16 0016.
 */
public class User {
    private String icon;
    private String nick_name;

    public User() {
    }

    public User(String icon, String nick_name) {
        this.icon = icon;
        this.nick_name = nick_name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }
}
