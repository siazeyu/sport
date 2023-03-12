package com.sport.utils;

import com.alibaba.fastjson.JSON;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Map;

public class ServletUtils {

    public static HttpServletRequest getRequest(){

        return getRequestAttributes().getRequest();
    }

    public static HttpServletResponse getResponse(){
        return getRequestAttributes().getResponse();
    }

    public static String getIpRequestAddress(){

        HttpServletRequest request = getRequest();

            String ipAddress = request.getHeader("x-forwarded-for");

            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                if (ipAddress.equals("127.0.0.1")||ipAddress.equals("0:0:0:0:0:0:0:1")) {
                    ipAddress = "127.0.0.1";
                }
            }
            if (ipAddress.length() > 15) { // "***.***.***.***".length()
                // = 15
                if (ipAddress.indexOf(",") > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }

        // 多个路由时，取第一个非unknown的ip
        final String[] arr = ipAddress.split(",");
        for (final String str : arr) {
            if (!"unknown".equalsIgnoreCase(str)) {
                ipAddress = str;
                break;
            }
        }
        return ipAddress;
    }


    public static String getRequestParams(){
        return argsArrayToString(getRequest().getParameterMap());
    }

    private static ServletRequestAttributes getRequestAttributes(){
        return  (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    }


    /**
     * 参数拼装
     */
    private static String argsArrayToString(Map paramsArray)
    {
        String params = "";
        if (paramsArray != null && paramsArray.size() > 0)
        {
            for (Object o : paramsArray.keySet())
            {
                Object v = paramsArray.get(o);
                if (o != null && !isFilterObject(v))
                {
                    try
                    {
                        Object jsonObj = JSON.toJSONString(v);
                        params += o + ":" + StringUtils.removeString(jsonObj.toString(), "\\\"") + " ";
                    }
                    catch (Exception e)
                    {
                        params += o + ":无法展示";
                    }
                }
            }
        }
        return params.trim();
    }

    /**
     * 判断是否需要过滤的对象。
     *
     * @param o 对象信息。
     * @return 如果是需要过滤的对象，则返回true；否则返回false。
     */
    @SuppressWarnings("rawtypes")
    public static boolean isFilterObject(final Object o)
    {
        Class<?> clazz = o.getClass();
        if (clazz.isArray())
        {
            return clazz.getComponentType().isAssignableFrom(MultipartFile.class);
        }
        else if (Collection.class.isAssignableFrom(clazz))
        {
            Collection collection = (Collection) o;
            for (Object value : collection)
            {
                return value instanceof MultipartFile;
            }
        }
        else if (Map.class.isAssignableFrom(clazz))
        {
            Map map = (Map) o;
            for (Object value : map.entrySet())
            {
                Map.Entry entry = (Map.Entry) value;
                return entry.getValue() instanceof MultipartFile;
            }
        }
        return o instanceof MultipartFile || o instanceof HttpServletRequest || o instanceof HttpServletResponse
                || o instanceof BindingResult;
    }
}
