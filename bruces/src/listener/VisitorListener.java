package listener;

import pojo.User;
import pojo.Visitor;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.Cookie;
import java.util.Map;

@WebListener()
public class VisitorListener implements ServletContextListener, ServletContextAttributeListener {

    // Public constructor is required by servlet spec
    public VisitorListener() {
    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("add");
    }

    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("remove");
    }

    @Override
    public void attributeAdded(ServletContextAttributeEvent servletContextAttributeEvent) {

        Visitor.addVisitor((Map<Integer,User>) servletContextAttributeEvent.getValue());
        System.out.println("Add successfully");

    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent servletContextAttributeEvent) {
        Visitor.removeVisitor((Map<Integer,User>) servletContextAttributeEvent.getValue());
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent servletContextAttributeEvent) {
        Object oldvalue = servletContextAttributeEvent.getValue();
        Object newvalue = servletContextAttributeEvent.getServletContext().getAttribute(servletContextAttributeEvent.getName());
        Visitor.removeVisitor((Map<Integer, User>) oldvalue);
        Visitor.addVisitor((Map<Integer, User>) newvalue);
    }
}
