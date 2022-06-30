package listener;

import pojo.OnlineUsers;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;

@WebListener()
public class OnlineUserListener implements HttpSessionListener, HttpSessionAttributeListener {

    public OnlineUserListener() {
    }

    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("会话已创建");
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("会话已销毁");
    }

    // -------------------------------------------------------
    // HttpSessionAttributeListener implementation
    // -------------------------------------------------------

    public void attributeAdded(HttpSessionBindingEvent sbe) {
        if (sbe.getName().equals("username")){
            OnlineUsers.addUser(String.valueOf(sbe.getValue()));
        }
    }

    public void attributeRemoved(HttpSessionBindingEvent sbe) {
        if (sbe.getName().equals("username")){
            OnlineUsers.removeUser(String.valueOf(sbe.getValue()));
        }
    }

    public void attributeReplaced(HttpSessionBindingEvent sbe) {
        if (sbe.getName().equals("username")){
            String oldValue=String.valueOf(sbe.getValue());
            String newValue=String.valueOf(sbe.getSession().getAttribute(sbe.getName()));
            OnlineUsers.removeUser(oldValue);
            OnlineUsers.addUser(newValue);
        }

    }
}
