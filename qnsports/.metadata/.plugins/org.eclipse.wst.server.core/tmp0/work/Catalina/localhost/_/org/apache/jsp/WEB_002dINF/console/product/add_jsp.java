/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.79
 * Generated at: 2018-02-01 07:58:44 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.console.product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;

public final class add_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(1);
    _jspx_dependants.put("/WEB-INF/console/head.jsp", Long.valueOf(1517036964442L));
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems;

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.release();
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write('\r');
      out.write('\n');
      out.write("<link href=\"/jsutils/qingniao/css/admin.css\" rel=\"stylesheet\" type=\"text/css\"/>\r\n");
      out.write("<link href=\"/jsutils/common/css/theme.css\" rel=\"stylesheet\" type=\"text/css\"/>\r\n");
      out.write("<link href=\"/jsutils/common/css/jquery.validate.css\" rel=\"stylesheet\" type=\"text/css\"/>\r\n");
      out.write("<link href=\"/jsutils/common/css/jquery.treeview.css\" rel=\"stylesheet\" type=\"text/css\"/>\r\n");
      out.write("<link href=\"/jsutils/common/css/jquery.ui.css\" rel=\"stylesheet\" type=\"text/css\"/>\r\n");
      out.write("\r\n");
      out.write("<!-- <script src=\"/thirdparty/ckeditor/ckeditor.js\" type=\"text/javascript\"></script> -->\r\n");
      out.write("<!-- <script src=\"/thirdparty/My97DatePicker/WdatePicker.js\" type=\"text/javascript\"></script> -->\r\n");
      out.write("<script type=\"text/javascript\" src=\"/jsutils/fckeditor/fckeditor.js\"></script>\r\n");
      out.write("<script src=\"/jsutils/common/js/jquery.js\" type=\"text/javascript\"></script>\r\n");
      out.write("<script src=\"/jsutils/common/js/jquery.ext.js\" type=\"text/javascript\"></script>\r\n");
      out.write("<script src=\"/jsutils/common/js/jquery.form.js\" type=\"text/javascript\"></script>\r\n");
      out.write("<script src=\"/jsutils/common/js/qingniao.js\" type=\"text/javascript\"></script>\r\n");
      out.write("<script src=\"/jsutils/qingniao/js/admin.js\" type=\"text/javascript\"></script>\r\n");
      out.write("\r\n");
      out.write("<link rel=\"stylesheet\" href=\"/jsutils/css/style.css\" />\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\r\n");
      out.write("<title>qingniao-add</title>\r\n");
      out.write("<style type=\"\">\r\n");
      out.write(".h2_ch a:hover, .h2_ch a.here {\r\n");
      out.write("\tcolor: #FFF;\r\n");
      out.write("\tfont-weight: bold;\r\n");
      out.write("\tbackground-position: 0px -32px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".h2_ch a {\r\n");
      out.write("\tfloat: left;\r\n");
      out.write("\theight: 32px;\r\n");
      out.write("\tmargin-right: -1px;\r\n");
      out.write("\tpadding: 0px 16px;\r\n");
      out.write("\tline-height: 32px;\r\n");
      out.write("\tfont-size: 14px;\r\n");
      out.write("\tfont-weight: normal;\r\n");
      out.write("\tborder: 1px solid #C5C5C5;\r\n");
      out.write("\tbackground: url('/jsutils/qingniao/img/admin/bg_ch.gif') repeat-x scroll\r\n");
      out.write("\t\t0% 0% transparent;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("a {\r\n");
      out.write("\tcolor: #06C;\r\n");
      out.write("\ttext-decoration: none;\r\n");
      out.write("}\r\n");
      out.write("</style>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("$(function(){\r\n");
      out.write("\tvar tObj;\r\n");
      out.write("\t$(\"#tabs a\").each(function(){\r\n");
      out.write("\t\tif($(this).attr(\"class\").indexOf(\"here\") == 0){tObj = $(this)}\r\n");
      out.write("\t\t$(this).click(function(){\r\n");
      out.write("\t\t\tvar c = $(this).attr(\"class\");\r\n");
      out.write("\t\t\tif(c.indexOf(\"here\") == 0){return;}\r\n");
      out.write("\t\t\tvar ref = $(this).attr(\"ref\");\r\n");
      out.write("\t\t\tvar ref_t = tObj.attr(\"ref\");\r\n");
      out.write("\t\t\ttObj.attr(\"class\",\"nor\");\r\n");
      out.write("\t\t\t$(this).attr(\"class\",\"here\");\r\n");
      out.write("\t\t\t$(ref_t).hide();\r\n");
      out.write("\t\t\t$(ref).show();\r\n");
      out.write("\t\t\ttObj = $(this);\r\n");
      out.write("\t\t\tif(ref == '#tab_2'){\r\n");
      out.write("\t\t\t\tvar fck = new FCKeditor(\"productdesc\");\r\n");
      out.write("\t\t\t\tfck.BasePath = \"/jsutils/fckeditor/\";\r\n");
      out.write("\t\t\t\tfck.Height = 400;\r\n");
      out.write("\t\t\t\t//设置上传路径\r\n");
      out.write("\t\t\t\tfck.Config[\"ImageUploadURL\"] = \"/upload/productDesc.do\"\t//图片上传的路径\r\n");
      out.write("\t\t\t\t//把数据同步到 textarea\r\n");
      out.write("\t\t\t\tfck.ReplaceTextarea();\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t});\r\n");
      out.write("});\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t//图片上传的操作\r\n");
      out.write("\tfunction ImgUpload() {\r\n");
      out.write("\t\t//使用jQuery的三方插件 jQuery.form.js\r\n");
      out.write("\t\tvar opts = {\r\n");
      out.write("\t\t\turl : \"/upload/imgUploadToFastDFS.do\",\r\n");
      out.write("\t\t\ttype : \"post\",\r\n");
      out.write("\t\t\tdataType : \"json\",\r\n");
      out.write("\t\t\tsuccess : function(data) {\r\n");
      out.write("\t\t\t\t//返回图片存储在服务器的路径来进行回显\r\n");
      out.write("\t\t\t\t$(\"#allimg\").attr(\"src\",data.url);\r\n");
      out.write("\t\t\t\t//把路径存在把隐藏域里面保存到数据库\r\n");
      out.write("\t\t\t\t$(\"#himg\").val(data.path);\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t};\r\n");
      out.write("\t\t//提交表单\r\n");
      out.write("\t\t$(\"#pForm\").ajaxSubmit(opts);\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("</script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t<div class=\"box-positon\">\r\n");
      out.write("\t\t<div class=\"rpos\">当前位置: 商品管理 - 添加</div>\r\n");
      out.write("\t\t<form class=\"ropt\">\r\n");
      out.write("\t\t\t<input type=\"submit\" onclick=\"this.form.action='/product/list.do';\"\r\n");
      out.write("\t\t\t\tvalue=\"返回列表\" class=\"return-button\" />\r\n");
      out.write("\t\t</form>\r\n");
      out.write("\t\t<div class=\"clear\"></div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<h2 class=\"h2_ch\">\r\n");
      out.write("\t\t<span id=\"tabs\"> <a href=\"javascript:void(0);\" ref=\"#tab_1\"\r\n");
      out.write("\t\t\ttitle=\"基本信息\" class=\"here\">基本信息</a> <a href=\"javascript:void(0);\"\r\n");
      out.write("\t\t\tref=\"#tab_2\" title=\"商品描述\" class=\"nor\">商品描述</a> <a\r\n");
      out.write("\t\t\thref=\"javascript:void(0);\" ref=\"#tab_3\" title=\"商品参数\" class=\"nor\">包装清单</a>\r\n");
      out.write("\t\t</span>\r\n");
      out.write("\t</h2>\r\n");
      out.write("\t<div class=\"body-box\" style=\"float: right\">\r\n");
      out.write("\t\t<form id=\"pForm\" action=\"/product/save.do\" method=\"post\"\r\n");
      out.write("\t\t\tenctype=\"multipart/form-data\">\r\n");
      out.write("\t\t\t<table cellspacing=\"1\" cellpadding=\"2\" width=\"100%\" border=\"0\"\r\n");
      out.write("\t\t\t\tclass=\"pn-ftable\">\r\n");
      out.write("\t\t\t\t<tbody id=\"tab_1\">\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td width=\"20%\" class=\"pn-flabel pn-flabel-h\"><span\r\n");
      out.write("\t\t\t\t\t\t\tclass=\"pn-frequired\">*</span> 商品类型:</td>\r\n");
      out.write("\t\t\t\t\t\t<td width=\"80%\" class=\"pn-fcontent\"><select name=\"typeId\">\r\n");
      out.write("\t\t\t\t\t\t\t<option value=\"\">请选择</option>\r\n");
      out.write("\t\t\t\t\t\t\t");
      if (_jspx_meth_c_005fforEach_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t</select></td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td width=\"20%\" class=\"pn-flabel pn-flabel-h\"><span\r\n");
      out.write("\t\t\t\t\t\t\tclass=\"pn-frequired\">*</span> 商品名称:</td>\r\n");
      out.write("\t\t\t\t\t\t<td width=\"80%\" class=\"pn-fcontent\"><input type=\"text\"\r\n");
      out.write("\t\t\t\t\t\t\tclass=\"required\" name=\"name\" maxlength=\"100\" size=\"100\" /></td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td width=\"20%\" class=\"pn-flabel pn-flabel-h\">商品品牌:</td>\r\n");
      out.write("\t\t\t\t\t\t<td width=\"80%\" class=\"pn-fcontent\"><select name=\"brandId\">\r\n");
      out.write("\t\t\t\t\t\t\t<option value=\"\">请选择品牌</option>\r\n");
      out.write("\t\t\t\t\t\t\t");
      if (_jspx_meth_c_005fforEach_005f1(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t</select></td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td width=\"20%\" class=\"pn-flabel pn-flabel-h\">商品毛重:</td>\r\n");
      out.write("\t\t\t\t\t\t<td width=\"80%\" class=\"pn-fcontent\"><input type=\"text\"\r\n");
      out.write("\t\t\t\t\t\t\tclass=\"required\" name=\"weight\" maxlength=\"10\" />KG</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td width=\"20%\" class=\"pn-flabel pn-flabel-h\"><span\r\n");
      out.write("\t\t\t\t\t\t\tclass=\"pn-frequired\">*</span> 材质:</td>\r\n");
      out.write("\t\t\t\t\t\t<td width=\"80%\" class=\"pn-fcontent\">\r\n");
      out.write("\t\t\t\t\t\t\t");
      if (_jspx_meth_c_005fforEach_005f2(_jspx_page_context))
        return;
      out.write("</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td width=\"20%\" class=\"pn-flabel pn-flabel-h\"><span\r\n");
      out.write("\t\t\t\t\t\t\tclass=\"pn-frequired\">*</span> 颜色:</td>\r\n");
      out.write("\t\t\t\t\t\t<td width=\"80%\" class=\"pn-fcontent\">\r\n");
      out.write("\t\t\t\t\t\t");
      if (_jspx_meth_c_005fforEach_005f3(_jspx_page_context))
        return;
      out.write("</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td width=\"20%\" class=\"pn-flabel pn-flabel-h\"><span\r\n");
      out.write("\t\t\t\t\t\t\tclass=\"pn-frequired\">*</span> 尺码:</td>\r\n");
      out.write("\t\t\t\t\t\t<td width=\"80%\" class=\"pn-fcontent\">\r\n");
      out.write("\t\t\t\t\t\t<input type=\"checkbox\" name=\"sizes\" value=\"S\" />S \r\n");
      out.write("\t\t\t\t\t\t<input type=\"checkbox\" name=\"sizes\" value=\"M\" />M \r\n");
      out.write("\t\t\t\t\t\t<input type=\"checkbox\" name=\"sizes\" value=\"L\" />L \r\n");
      out.write("\t\t\t\t\t\t<input type=\"checkbox\" name=\"sizes\" value=\"XL\" />XL \r\n");
      out.write("\t\t\t\t\t\t<input type=\"checkbox\" name=\"sizes\" value=\"XXL\" />XXL</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td width=\"20%\" class=\"pn-flabel pn-flabel-h\">状态:</td>\r\n");
      out.write("\t\t\t\t\t\t<td width=\"80%\" class=\"pn-fcontent\"><input type=\"checkbox\"\r\n");
      out.write("\t\t\t\t\t\t\tname=\"isNew\" value=\"1\" />新品 <input type=\"checkbox\"\r\n");
      out.write("\t\t\t\t\t\t\tname=\"isCommend\" value=\"1\" />推荐 <input type=\"checkbox\"\r\n");
      out.write("\t\t\t\t\t\t\tname=\"isHot\" value=\"1\" />热卖</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td width=\"20%\" class=\"pn-flabel pn-flabel-h\"><span\r\n");
      out.write("\t\t\t\t\t\t\tclass=\"pn-frequired\">*</span> 上传商品图片(90x150尺寸):</td>\r\n");
      out.write("\t\t\t\t\t\t<td width=\"80%\" class=\"pn-fcontent\">注:该尺寸图片必须为90x150。</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td width=\"20%\" class=\"pn-flabel pn-flabel-h\"></td>\r\n");
      out.write("\t\t\t\t\t\t<td width=\"80%\" class=\"pn-fcontent\">\r\n");
      out.write("\t\t\t\t\t\t\t<img width=\"100\" height=\"100\" id=\"allimg\" />\r\n");
      out.write("\t\t\t\t\t\t\t<input type=\"file\" name=\"picfile\" onchange=\"ImgUpload();\" />\r\n");
      out.write("\t\t\t\t\t\t\t<input type=\"hidden\" name=\"img.url\" id=\"himg\" />\t<!-- 回显图片的路径 -->\r\n");
      out.write("\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t</tbody>\r\n");
      out.write("\t\t\t\t<tbody id=\"tab_2\" style=\"display: none\">\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td><textarea rows=\"10\" cols=\"10\" id=\"productdesc\"\r\n");
      out.write("\t\t\t\t\t\t\t\tname=\"description\"></textarea></td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t</tbody>\r\n");
      out.write("\t\t\t\t<tbody id=\"tab_3\" style=\"display: none\">\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td><textarea rows=\"15\" cols=\"136\" id=\"productList\"\r\n");
      out.write("\t\t\t\t\t\t\t\tname=\"packageList\"></textarea></td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t</tbody>\r\n");
      out.write("\t\t\t\t<tbody>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td class=\"pn-fbutton\" colspan=\"2\"><input type=\"submit\"\r\n");
      out.write("\t\t\t\t\t\t\tclass=\"submit\" value=\"提交\" /> &nbsp; <input type=\"reset\"\r\n");
      out.write("\t\t\t\t\t\t\tclass=\"submit\" value=\"重置\" /></td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t</tbody>\r\n");
      out.write("\t\t\t</table>\r\n");
      out.write("\t\t</form>\r\n");
      out.write("\t</div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_c_005fforEach_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    boolean _jspx_th_c_005fforEach_005f0_reused = false;
    try {
      _jspx_th_c_005fforEach_005f0.setPageContext(_jspx_page_context);
      _jspx_th_c_005fforEach_005f0.setParent(null);
      // /WEB-INF/console/product/add.jsp(109,7) name = items type = java.lang.Object reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fforEach_005f0.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${typeList }", java.lang.Object.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      // /WEB-INF/console/product/add.jsp(109,7) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fforEach_005f0.setVar("type");
      int[] _jspx_push_body_count_c_005fforEach_005f0 = new int[] { 0 };
      try {
        int _jspx_eval_c_005fforEach_005f0 = _jspx_th_c_005fforEach_005f0.doStartTag();
        if (_jspx_eval_c_005fforEach_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          do {
            out.write("\r\n");
            out.write("\t\t\t\t\t\t\t\t<option value=\"");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${type.id }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
            out.write('"');
            out.write('>');
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${type.name }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
            out.write("</option>\r\n");
            out.write("\t\t\t\t\t\t\t");
            int evalDoAfterBody = _jspx_th_c_005fforEach_005f0.doAfterBody();
            if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
              break;
          } while (true);
        }
        if (_jspx_th_c_005fforEach_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          return true;
        }
      } catch (java.lang.Throwable _jspx_exception) {
        while (_jspx_push_body_count_c_005fforEach_005f0[0]-- > 0)
          out = _jspx_page_context.popBody();
        _jspx_th_c_005fforEach_005f0.doCatch(_jspx_exception);
      } finally {
        _jspx_th_c_005fforEach_005f0.doFinally();
      }
      _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.reuse(_jspx_th_c_005fforEach_005f0);
      _jspx_th_c_005fforEach_005f0_reused = true;
    } finally {
      org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_c_005fforEach_005f0, _jsp_getInstanceManager(), _jspx_th_c_005fforEach_005f0_reused);
    }
    return false;
  }

  private boolean _jspx_meth_c_005fforEach_005f1(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f1 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    boolean _jspx_th_c_005fforEach_005f1_reused = false;
    try {
      _jspx_th_c_005fforEach_005f1.setPageContext(_jspx_page_context);
      _jspx_th_c_005fforEach_005f1.setParent(null);
      // /WEB-INF/console/product/add.jsp(124,7) name = items type = java.lang.Object reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fforEach_005f1.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${brandList }", java.lang.Object.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      // /WEB-INF/console/product/add.jsp(124,7) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fforEach_005f1.setVar("brand");
      int[] _jspx_push_body_count_c_005fforEach_005f1 = new int[] { 0 };
      try {
        int _jspx_eval_c_005fforEach_005f1 = _jspx_th_c_005fforEach_005f1.doStartTag();
        if (_jspx_eval_c_005fforEach_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          do {
            out.write("\r\n");
            out.write("\t\t\t\t\t\t\t\t<option value=\"");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${brand.brandId }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
            out.write('"');
            out.write('>');
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${brand.brandName }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
            out.write("</option>\r\n");
            out.write("\t\t\t\t\t\t\t");
            int evalDoAfterBody = _jspx_th_c_005fforEach_005f1.doAfterBody();
            if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
              break;
          } while (true);
        }
        if (_jspx_th_c_005fforEach_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          return true;
        }
      } catch (java.lang.Throwable _jspx_exception) {
        while (_jspx_push_body_count_c_005fforEach_005f1[0]-- > 0)
          out = _jspx_page_context.popBody();
        _jspx_th_c_005fforEach_005f1.doCatch(_jspx_exception);
      } finally {
        _jspx_th_c_005fforEach_005f1.doFinally();
      }
      _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.reuse(_jspx_th_c_005fforEach_005f1);
      _jspx_th_c_005fforEach_005f1_reused = true;
    } finally {
      org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_c_005fforEach_005f1, _jsp_getInstanceManager(), _jspx_th_c_005fforEach_005f1_reused);
    }
    return false;
  }

  private boolean _jspx_meth_c_005fforEach_005f2(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f2 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    boolean _jspx_th_c_005fforEach_005f2_reused = false;
    try {
      _jspx_th_c_005fforEach_005f2.setPageContext(_jspx_page_context);
      _jspx_th_c_005fforEach_005f2.setParent(null);
      // /WEB-INF/console/product/add.jsp(138,7) name = items type = java.lang.Object reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fforEach_005f2.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${featureList }", java.lang.Object.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      // /WEB-INF/console/product/add.jsp(138,7) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fforEach_005f2.setVar("feature");
      int[] _jspx_push_body_count_c_005fforEach_005f2 = new int[] { 0 };
      try {
        int _jspx_eval_c_005fforEach_005f2 = _jspx_th_c_005fforEach_005f2.doStartTag();
        if (_jspx_eval_c_005fforEach_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          do {
            out.write("\r\n");
            out.write("\t\t\t\t\t\t\t\t<input type=\"checkbox\" value=\"");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${feature.id }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
            out.write("\"\r\n");
            out.write("\t\t\t\t\t\t\t\t\tname=\"featujsutils\" />");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${feature.name }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
            out.write("\r\n");
            out.write("\t\t\t\t\t\t\t");
            int evalDoAfterBody = _jspx_th_c_005fforEach_005f2.doAfterBody();
            if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
              break;
          } while (true);
        }
        if (_jspx_th_c_005fforEach_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          return true;
        }
      } catch (java.lang.Throwable _jspx_exception) {
        while (_jspx_push_body_count_c_005fforEach_005f2[0]-- > 0)
          out = _jspx_page_context.popBody();
        _jspx_th_c_005fforEach_005f2.doCatch(_jspx_exception);
      } finally {
        _jspx_th_c_005fforEach_005f2.doFinally();
      }
      _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.reuse(_jspx_th_c_005fforEach_005f2);
      _jspx_th_c_005fforEach_005f2_reused = true;
    } finally {
      org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_c_005fforEach_005f2, _jsp_getInstanceManager(), _jspx_th_c_005fforEach_005f2_reused);
    }
    return false;
  }

  private boolean _jspx_meth_c_005fforEach_005f3(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f3 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    boolean _jspx_th_c_005fforEach_005f3_reused = false;
    try {
      _jspx_th_c_005fforEach_005f3.setPageContext(_jspx_page_context);
      _jspx_th_c_005fforEach_005f3.setParent(null);
      // /WEB-INF/console/product/add.jsp(148,6) name = items type = java.lang.Object reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fforEach_005f3.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${colorList }", java.lang.Object.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      // /WEB-INF/console/product/add.jsp(148,6) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fforEach_005f3.setVar("color");
      int[] _jspx_push_body_count_c_005fforEach_005f3 = new int[] { 0 };
      try {
        int _jspx_eval_c_005fforEach_005f3 = _jspx_th_c_005fforEach_005f3.doStartTag();
        if (_jspx_eval_c_005fforEach_005f3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          do {
            out.write("\r\n");
            out.write("\t\t\t\t\t\t\t\t<input type=\"checkbox\" value=\"");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${color.id }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
            out.write("\" name=\"colors\" />");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${color.name }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
            out.write("\r\n");
            out.write("\t\t\t\t\t\t");
            int evalDoAfterBody = _jspx_th_c_005fforEach_005f3.doAfterBody();
            if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
              break;
          } while (true);
        }
        if (_jspx_th_c_005fforEach_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          return true;
        }
      } catch (java.lang.Throwable _jspx_exception) {
        while (_jspx_push_body_count_c_005fforEach_005f3[0]-- > 0)
          out = _jspx_page_context.popBody();
        _jspx_th_c_005fforEach_005f3.doCatch(_jspx_exception);
      } finally {
        _jspx_th_c_005fforEach_005f3.doFinally();
      }
      _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.reuse(_jspx_th_c_005fforEach_005f3);
      _jspx_th_c_005fforEach_005f3_reused = true;
    } finally {
      org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_c_005fforEach_005f3, _jsp_getInstanceManager(), _jspx_th_c_005fforEach_005f3_reused);
    }
    return false;
  }
}
