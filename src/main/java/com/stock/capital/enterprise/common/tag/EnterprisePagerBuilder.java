package com.stock.capital.enterprise.common.tag;

import com.stock.core.Constant;
import com.stock.core.dto.QueryInfo;
import com.stock.core.tag.PagerBuilder;

public class EnterprisePagerBuilder implements PagerBuilder {

    public String buildPager(QueryInfo<?> queryInfo, String action) {
        StringBuilder html = new StringBuilder();
        if (queryInfo != null) {
            long total = queryInfo.getTotal();
            long startRow = Constant.DEFAULT_START_ROW;
            int pageSize = Constant.DEFAULT_PAGE_SIZE;
            if (queryInfo != null) {
                startRow = queryInfo.getStartRow();
                pageSize = queryInfo.getPageSize();
            }
            html.append("<div class=\"table-footer clearfix\">");
            html.append("<div class=\"DT-label col-xs-5\">");
            html.append("<div class=\"dataTables_info\" id=\"table_info\" role=\"status\" aria-live=\"polite\">");
            int pages = (int) Math.ceil(Double.valueOf(total) / Double.valueOf(pageSize));
            long endRow = startRow + pageSize;
            if (endRow > total) {
                endRow = total;
            }
            int curPage = (int) Math.ceil(Double.valueOf(endRow) / Double.valueOf(pageSize));
            long start = total == 0 ? 0 : startRow + 1;
            // 需求1670：列表底部的文案调整 2017/04/10 bywangyu start
            html.append("显示第 ").append(start).append(" 至 ").append(endRow).append(" 项数据，共 ").append(total)
                    .append(" 项");
            // 需求1670：列表底部的文案调整 2017/04/10 bywangyu end
            html.append("</div>");
            html.append("</div>");
            html.append("<div class=\"DT-per-page col-xs-3\">");
            html.append("<div class=\"dataTables_length\" id=\"table_id_length\">");
            html.append("<label>每页显示<select id=\"tableLength\" name=\"tableLength\" class=\"form-control input-sm\">");
            if (pageSize == 10) {
                html.append(" <option value=\"10\" selected=\"selected\">10</option><option value=\"20\">20</option><option value=\"50\">50</option><option value=\"100\">100</option>");
            } else if (pageSize == 20) {
                html.append(" <option value=\"10\">10</option><option value=\"20\" selected=\"selected\">20</option><option value=\"50\">50</option><option value=\"100\">100</option>");
            } else if (pageSize == 50) {
                html.append(" <option value=\"10\">10</option><option value=\"20\">20</option><option value=\"50\" selected=\"selected\">50</option><option value=\"100\">100</option>");
            } else {
                html.append(" <option value=\"10\">10</option><option value=\"20\">20</option><option value=\"50\">50</option><option value=\"100\" selected=\"selected\">100</option>");
            }
            // 需求1670：列表底部的文案调整 2017/04/10 bywangyu start
            html.append("</select> 项数据");
            // 需求1670：列表底部的文案调整 2017/04/10 bywangyu end
            html.append("</label>");
            html.append("</div>");
            html.append("</div>");
            html.append("<div class=\"DT-pagination col-xs-4\"  align=\"center\">");
            html.append("<ul class=\"pagination\">");
            if (total > pageSize) {
                if (curPage < 4) {
                    if (curPage == 1) {
                        html.append("<li class=\"disabled\"><a href=\"javascript:void(0);\">上页</a></li>");
                        html.append("<li class=\"active\"><a href=\"javascript:void(0);\">1 <span class=\"sr-only\">(current)</span></a></li>");
                    } else {
                        addPrev(html, curPage, action, pageSize);
                        html.append("<li><a onclick=\"submitForm('").append(action)
                                .append("?start=0&length=").append(pageSize);
                        html.append("');\">1 </a></li>");
                    }
                    if (pages > 1) {
                        if (curPage == 2) {
                            html.append("<li class=\"active\"><a href=\"javascript:void(0);\">2 <span class=\"sr-only\">(current)</span></a></li>");
                        } else {
                            addHtml(html, 2, action, pageSize);
                        }
                        if (pages > 2) {
                            if (curPage == 3) {
                                html.append("<li class=\"active\"><a href=\"javascript:void(0);\">3 <span class=\"sr-only\">(current)</span></a></li>");
                            } else {
                                addHtml(html, 3, action, pageSize);
                            }
                            if (pages > 3) {
                                addHtml(html, 4, action, pageSize);
                                if (pages > 4) {
                                    addHtml(html, 5, action, pageSize);
                                    addNext(html, curPage, action, pageSize);
                                }
                            }
                        }
                    }
                } else if (pages - curPage == 1) {
                    addPrev(html, curPage, action, pageSize);
                    addHtml(html, curPage - 3, action, pageSize);
                    addHtml(html, curPage - 2, action, pageSize);
                    addHtml(html, curPage - 1, action, pageSize);
                    html.append("<li class=\"active\"><a href=\"javascript:void(0);\">" + curPage + " <span class=\"sr-only\">(current)</span></a></li>");
                    addHtml(html, curPage + 1, action, pageSize);
                } else if (pages == curPage) {
                    addPrev(html, curPage, action, pageSize);
                    addHtml(html, curPage - 4, action, pageSize);
                    addHtml(html, curPage - 3, action, pageSize);
                    addHtml(html, curPage - 2, action, pageSize);
                    addHtml(html, curPage - 1, action, pageSize);
                    html.append("<li class=\"active\"><a href=\"javascript:void(0);\">" + curPage + " <span class=\"sr-only\">(current)</span></a></li>");
                } else {
                    addPrev(html, curPage, action, pageSize);
                    addHtml(html, curPage - 2, action, pageSize);
                    addHtml(html, curPage - 1, action, pageSize);
                    html.append("<li class=\"active\"><a href=\"javascript:void(0);\">" + curPage + " <span class=\"sr-only\">(current)</span></a></li>");
                    addHtml(html, curPage + 1, action, pageSize);
                    addHtml(html, curPage + 2, action, pageSize);
                    if (curPage + 2 < pages) {
                        addNext(html, curPage, action, pageSize);
                    }
                }
            } else {
                html.append("<li class=\"disabled\"><a href=\"javascript:void(0);\">上页</a></li>");
                html.append("<li class=\"active\"><a href=\"javascript:void(0);\">1 <span class=\"sr-only\">(current)</span></a></li>");
            }
            html.append("</ul>");
            html.append("</div>");
            html.append("</div>");

        }
        return html.toString();
    }

    private void addHtml(StringBuilder html, Integer curPage, String action, Integer pageSize) {
        html.append("<li><a onclick=\"submitForm('").append(action)
                .append("?start=").append((curPage - 1) * pageSize).append("&length=")
                .append(pageSize);
        html.append("');\">" + curPage + " </a></li>");
    }

    private void addPrev(StringBuilder html, Integer curPage, String action, Integer pageSize) {
        html.append("<li><a onclick=\"submitForm('").append(action)
                .append("?start=").append((curPage - 2) * pageSize).append("&length=")
                .append(pageSize);
        html.append("');\">上页</a></li>");
    }

    private void addNext(StringBuilder html, Integer curPage, String action, Integer pageSize) {
        html.append("<li><a onclick=\"submitForm('").append(action)
                .append("?start=").append(curPage * pageSize).append("&length=")
                .append(pageSize);
        html.append("');\">下页</a></li>");
    }
}
