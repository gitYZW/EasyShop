/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2018-07-14 13:19:53 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class search_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005ffmt_005fformatNumber_0026_005fvalue_005fminFractionDigits_005fmaxFractionDigits_005fgroupingUsed_005fnobody;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005ffmt_005fformatNumber_0026_005fvalue_005fminFractionDigits_005fmaxFractionDigits_005fgroupingUsed_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.release();
    _005fjspx_005ftagPool_005ffmt_005fformatNumber_0026_005fvalue_005fminFractionDigits_005fmaxFractionDigits_005fgroupingUsed_005fnobody.release();
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
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Cache-Control\" content=\"max-age=300\" />\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n");
      out.write("<title>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${query}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write(" - 商品搜索 - EasyShop商城</title>\r\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"/css/productList.css\" />\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"/css/base_w1200.css\" />\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"/css/common.css\" />\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"/css/jquery.alerts.css\" />\r\n");
      out.write("<script type=\"text/javascript\" src=\"/js/jquery-1.5.1.min.js\"></script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<!-- header start -->\r\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "commons/header.jsp", out, false);
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "commons/mainmenu.jsp", out, false);
      out.write("<!-------面包线 linknav--------->\r\n");
      out.write("<div class=\"linknav\">\r\n");
      out.write("\t<div class=\"schArticle\">\r\n");
      out.write("\t\t<a href=\"/article/search?keyword=%E6%9C%88%E9%A5%BC\" target=\"_blank\">找到和“<span>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${query}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</span>”相关的文章<span\r\n");
      out.write("\t\t\tid=\"articlenum\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${totalPages }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</span>篇&gt;&gt;\r\n");
      out.write("\t\t</a>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div class=\"breadcrumb\">\r\n");
      out.write("\t\t<span>全部结果&nbsp;&gt;&nbsp;");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${query}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</span>\r\n");
      out.write("\t</div>\r\n");
      out.write("</div>\r\n");
      out.write("<div class=\"content_list\">\r\n");
      out.write("  <div class=\"main-box\">\r\n");
      out.write("    \r\n");
      out.write("   <a id=\"prolist-id\"></a>\r\n");
      out.write("    <div class=\"r-filter\">\r\n");
      out.write("      <div class=\"f-sort\">\r\n");
      out.write("        <div class=\"pagin\">\r\n");
      out.write("          <span class=\"txt\"><span class=\"n\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${page }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</span>/");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${totalPages }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</span>\r\n");
      out.write("          <span class=\"prev\">上一页</span><span class=\"next\">下一页</span>       \t</div>\r\n");
      out.write("        <div class=\"total\">共<span>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${recourdCount }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</span>个商品</div>\r\n");
      out.write("      </div>\r\n");
      out.write("    </div>\r\n");
      out.write("        \r\n");
      out.write("    <a name=\"prolist\" id=\"prolist\"></a>\r\n");
      out.write("    <div class=\"p-list\">\r\n");
      out.write("      <ul class=\"list-all\">\r\n");
      out.write("         ");
      if (_jspx_meth_c_005fforEach_005f0(_jspx_page_context))
        return;
      out.write("</ul>\r\n");
      out.write("      <span class=\"clear\"></span>\r\n");
      out.write("    </div>\r\n");
      out.write("    \r\n");
      out.write("    <div class=\"pages\">\r\n");
      out.write("\t      </div>\r\n");
      out.write("    \r\n");
      out.write("  </div>\r\n");
      out.write("    <div class=\"left-box\">\r\n");
      out.write("    <div class=\"catlist\" id=\"cateall\">\r\n");
      out.write("      <div class=\"ct\"><h2>在结果中筛选</h2></div>\r\n");
      out.write("      <div class=\"cm\">\r\n");
      out.write("      <div class=\"catitem\" style=\"border-top-width: 0px;\"><h3><b></b><a href=\"/productlist/search/?categoryId=8&amp;keyword=%E6%9C%88%E9%A5%BC&amp;o=saleNum%3Adesc\" class=\"\">生鲜食品<span>（1）</span></a></h3><ul><li><a href=\"/productlist/search/?categoryId=57&amp;keyword=%E6%9C%88%E9%A5%BC&amp;o=saleNum%3Adesc\">蛋品（1）</a></li><li><a href=\"/productlist/search/?categoryId=292&amp;keyword=%E6%9C%88%E9%A5%BC&amp;o=saleNum%3Adesc\">&nbsp;&nbsp;&nbsp;&nbsp;松花蛋/咸鸭蛋（1）</a></li></ul></div><div class=\"catitem\"><h3><b></b><a href=\"/productlist/search/?categoryId=6&amp;keyword=%E6%9C%88%E9%A5%BC&amp;o=saleNum%3Adesc\" class=\"\">休闲食品<span>（35）</span></a></h3><ul><li><a href=\"/productlist/search/?categoryId=44&amp;keyword=%E6%9C%88%E9%A5%BC&amp;o=saleNum%3Adesc\">糖果/巧克力（2）</a></li><li><a href=\"/productlist/search/?categoryId=238&amp;keyword=%E6%9C%88%E9%A5%BC&amp;o=saleNum%3Adesc\">&nbsp;&nbsp;&nbsp;&nbsp;巧克力（2）</a></li><li><a href=\"/productlist/search/?categoryId=7321&amp;keyword=%E6%9C%88%E9%A5%BC&amp;o=saleNum%3Adesc\">月饼（33）</a></li><li><a href=\"/productlist/search/?categoryId=7325&amp;keyword=%E6%9C%88%E9%A5%BC&amp;o=saleNum%3Adesc\">&nbsp;&nbsp;&nbsp;&nbsp;冰皮月饼（1）</a></li><li><a href=\"/productlist/search/?categoryId=7331&amp;keyword=%E6%9C%88%E9%A5%BC&amp;o=saleNum%3Adesc\">&nbsp;&nbsp;&nbsp;&nbsp;其他月饼（21）</a></li><li><a href=\"/productlist/search/?categoryId=7381&amp;keyword=%E6%9C%88%E9%A5%BC&amp;o=saleNum%3Adesc\">&nbsp;&nbsp;&nbsp;&nbsp;月饼券（11）</a></li></ul></div>       </div>\r\n");
      out.write("    </div> \r\n");
      out.write("      \r\n");
      out.write("    \r\n");
      out.write("  </div>\r\n");
      out.write("  \r\n");
      out.write("  ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "commons/footer.jsp", out, false);
      out.write("<script type=\"text/javascript\" src=\"/js/common.js?v=20160713\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/js/cart.js?v=20160713\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/js/jquery.alerts.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/js/NewVersion.js?v=20160713\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/js/cookie.js?v=20160416222\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/js/shadow.js?v=20160416\"></script>\r\n");
      out.write("</div>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
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
    _jspx_th_c_005fforEach_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fforEach_005f0.setParent(null);
    // /WEB-INF/jsp/search.jsp(48,9) name = items type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setItems(new org.apache.jasper.el.JspValueExpression("/WEB-INF/jsp/search.jsp(48,9) '${itemList }'",_el_expressionfactory.createValueExpression(_jspx_page_context.getELContext(),"${itemList }",java.lang.Object.class)).getValue(_jspx_page_context.getELContext()));
    // /WEB-INF/jsp/search.jsp(48,9) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setVar("item");
    int[] _jspx_push_body_count_c_005fforEach_005f0 = new int[] { 0 };
    try {
      int _jspx_eval_c_005fforEach_005f0 = _jspx_th_c_005fforEach_005f0.doStartTag();
      if (_jspx_eval_c_005fforEach_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("<li>\r\n");
          out.write("            <div class=\"l-wrap\">\r\n");
          out.write("\t\t\t\t<div class=\"pic\">\r\n");
          out.write("\t\t\t\t\t<a class=\"trackref\" href=\"http://localhost:8086/item/");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.id}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write(".html\" title=\"\" target=\"_blank\">\r\n");
          out.write("\t\t\t\t\t\t<img src=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.images[0]}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write("\" style=\"display:inline\"/>\r\n");
          out.write("\t\t\t\t\t</a>\r\n");
          out.write("\t\t\t\t</div>\r\n");
          out.write("\t            <div class=\"price\">\r\n");
          out.write("\t\t\t\t\t<span><span class=\"p-now\">￥<strong>");
          if (_jspx_meth_fmt_005fformatNumber_005f0(_jspx_th_c_005fforEach_005f0, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f0))
            return true;
          out.write("</strong></span><span class=\"p-nor\"></span><span class=\"active\" style=\"\">直降</span></span>\t\t\t\r\n");
          out.write("\t\t\t\t</div>\r\n");
          out.write("\t            <div class=\"title-a\">\r\n");
          out.write("\t                <a class=\"trackref presaleSign_225865\" href=\"http://localhost:8086/item/");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.id}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write(".html\" target=\"_blank\">");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.title }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write("</a>\r\n");
          out.write("\t            </div>\r\n");
          out.write("\t        \t<div class=\"title-b\" style=\"\"><a class=\"trackref\" href=\"http://localhost:8086/item/");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.id}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write(".html\" target=\"_blank\">");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${sell_point }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write("</a></div>\r\n");
          out.write("\t            <div class=\"comment\">\r\n");
          out.write("\t                <div class=\"owner_shop_list\">自营</div>                    \r\n");
          out.write("\t            </div>\r\n");
          out.write("\t\t    </div>\r\n");
          out.write("\t\t </li>\r\n");
          out.write("         ");
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
      _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.reuse(_jspx_th_c_005fforEach_005f0);
    }
    return false;
  }

  private boolean _jspx_meth_fmt_005fformatNumber_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f0, javax.servlet.jsp.PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f0)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  fmt:formatNumber
    org.apache.taglibs.standard.tag.rt.fmt.FormatNumberTag _jspx_th_fmt_005fformatNumber_005f0 = (org.apache.taglibs.standard.tag.rt.fmt.FormatNumberTag) _005fjspx_005ftagPool_005ffmt_005fformatNumber_0026_005fvalue_005fminFractionDigits_005fmaxFractionDigits_005fgroupingUsed_005fnobody.get(org.apache.taglibs.standard.tag.rt.fmt.FormatNumberTag.class);
    _jspx_th_fmt_005fformatNumber_005f0.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fformatNumber_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f0);
    // /WEB-INF/jsp/search.jsp(57,40) name = groupingUsed type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fformatNumber_005f0.setGroupingUsed(false);
    // /WEB-INF/jsp/search.jsp(57,40) name = maxFractionDigits type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fformatNumber_005f0.setMaxFractionDigits(2);
    // /WEB-INF/jsp/search.jsp(57,40) name = minFractionDigits type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fformatNumber_005f0.setMinFractionDigits(2);
    // /WEB-INF/jsp/search.jsp(57,40) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fformatNumber_005f0.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.price / 100 }", java.lang.Object.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
    int _jspx_eval_fmt_005fformatNumber_005f0 = _jspx_th_fmt_005fformatNumber_005f0.doStartTag();
    if (_jspx_th_fmt_005fformatNumber_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fformatNumber_0026_005fvalue_005fminFractionDigits_005fmaxFractionDigits_005fgroupingUsed_005fnobody.reuse(_jspx_th_fmt_005fformatNumber_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fformatNumber_0026_005fvalue_005fminFractionDigits_005fmaxFractionDigits_005fgroupingUsed_005fnobody.reuse(_jspx_th_fmt_005fformatNumber_005f0);
    return false;
  }
}
