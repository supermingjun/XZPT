package xz.fzu.createword;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

/**
 * freemarker 工具类
 * @author brandon
 * @since 2017-08-01
 */
public class FreemarkerUtil {

    private static Configuration config = null;

    /**
     * Static initialization.
     *
     * Initialize the configuration of Freemarker.
     */
    static{
        config = new Configuration();
        config.setClassForTemplateLoading(FreemarkerUtil.class, "/ftl/");
        config.setTemplateUpdateDelay(0);
    }

    public static Configuration getConfiguration(){
        return config;
    }

    /**
     * @param template
     * @param variables
     * @return
     * @throws Exception
     */
    public static String generate(String template, Object obj) throws IOException, TemplateException {
        Configuration config = getConfiguration();
        config.setDefaultEncoding("UTF-8");
        config.setDirectoryForTemplateLoading(new File("E:\\java代码\\简历生成Word\\src"));
        Template tp = config.getTemplate(template);
        StringWriter stringWriter = new StringWriter();
        BufferedWriter writer = new BufferedWriter(stringWriter);
        tp.setEncoding("UTF-8");
        tp.process(obj, writer);
        String htmlStr = stringWriter.toString();
        writer.flush();
        writer.close();
        return htmlStr;
    }


}
