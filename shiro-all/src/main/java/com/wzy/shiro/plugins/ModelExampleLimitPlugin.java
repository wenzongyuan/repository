package com.wzy.shiro.plugins;

import java.util.List;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Element;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

public class ModelExampleLimitPlugin extends PluginAdapter {
    private String limitTypeString = "com.wzy.shiro.util.Limit";

    @Override
    public boolean validate(List<String> arg0) {
        return true;
    }

    @Override
    public boolean modelExampleClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        FullyQualifiedJavaType limitType = new FullyQualifiedJavaType(limitTypeString);
        topLevelClass.addImportedType(limitType);
        Field field = new Field();
        field.setName("limit");
        field.setType(limitType);
        field.setVisibility(JavaVisibility.PRIVATE);
        topLevelClass.addField(field);
        Method setMethod = new Method();
        setMethod.setName("setLimit");
        setMethod.setVisibility(JavaVisibility.PUBLIC);
        setMethod.addParameter(new Parameter(limitType, "limit"));
        setMethod.addBodyLine("this.limit = limit;");
        topLevelClass.addMethod(setMethod);
        Method getMethod = new Method();
        getMethod.setName("getLimit");
        getMethod.setVisibility(JavaVisibility.PUBLIC);
        getMethod.setReturnType(limitType);
        getMethod.addBodyLine("return this.limit;");
        topLevelClass.addMethod(getMethod);
        return true;
    }

    @Override
    public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        addLimitSqlMapCode(element);
        return true;
    }

    @Override
    public boolean sqlMapSelectByExampleWithBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        List<Element> elementList = element.getElements();
        XmlElement orderByElement = (XmlElement) elementList.get(elementList.size() - 1);
        orderByElement.getElements().set(0, new TextElement("order by ${orderByClause}"));
        addLimitSqlMapCode(element);
        return true;
    }

    private void addLimitSqlMapCode(XmlElement element) {
        XmlElement limit = new XmlElement("if");
        limit.addAttribute(new Attribute("test", "limit != null"));
        limit.addElement(new TextElement("limit #{limit.start},#{limit.maxRows}"));
        element.addElement(limit);
    }

}
