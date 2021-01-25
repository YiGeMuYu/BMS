package com.muyu.bms.service.impl;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.muyu.bms.service.DocuService;
import com.muyu.bms.vo.Docu;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class DocuServiceImpl implements DocuService {

	public String queryDocuDetail(String href){
		Document document = null;
		String path = "";
		try {
			document = Jsoup.parse(new URL(href),3000);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Elements content = document.getElementsByClass("content");
		for (Element element : content) {
			String tempPath = element.getElementsByTag("a").eq(0).text();
			if(".pdf".equals(tempPath.substring(tempPath.length()-4))){
				//说明是pdf文件可以直接打开
				path = tempPath;
			}else{
				if("abstract".equals(tempPath.substring(tempPath.length()-8))){
					//继续进入下一个页面进行爬取
					try {
						Document document1 = Jsoup.parse(new URL(tempPath), 5000);
						Elements absBox = document1.getElementsByClass("absBox");
						for (Element box : absBox) {
							Elements pp = box.getElementsByTag("p").eq(0);
							for (Element p : pp) {
								String deepHref = p.getElementsByTag("a").eq(1).attr("href");
								path = tempPath.substring(0,tempPath.indexOf("/journal"))+deepHref.substring(deepHref.indexOf("/"));
							}

						}

					} catch (IOException e) {
						e.printStackTrace();
					}
				}else{
					//其他网站 不做技术支持
					path = tempPath;
				}
			}
		}
		return path;
	}

	@Override
	public List<Docu> queryDocu(String documentName) {
		List<Docu> list = new ArrayList<Docu>();
		//生成一个浏览器模拟器
		WebClient browser = new WebClient();
		browser.getOptions().setCssEnabled(false);
		browser.getOptions().setJavaScriptEnabled(true);
		browser.getOptions().setThrowExceptionOnScriptError(false);
		String url = "http://oar.nstl.gov.cn/Paper/Search?searchKey="+documentName+"&x=0&y=0";
		try {
			//用模拟器动态加载网页
			HtmlPage htmlPage = browser.getPage(url);
			browser.waitForBackgroundJavaScript(3000);
			//获取网页节点信息
			Document document = Jsoup.parse(htmlPage.asXml());
			Element paper = document.getElementById("paper");
			Elements divs = paper.getElementsByClass("s2listtab");
			for (Element div : divs) {
				Docu docu = new Docu();
				String title = div.getElementsByTag("strong").eq(0).text();
				//获取第一个链接
				String href = div.getElementsByTag("a").eq(0).attr("href");
				docu.setTitle(title);
				//使用queryDocuDetail()方法，进入第一个链接中，取另一个网页的值
				docu.setPath(queryDocuDetail(href));
				Elements others = div.getElementsByClass("other");
				//标志位 判断是第几个other
				int flag = 0;
				for (Element other : others) {
					if(flag == 0){
						docu.setPublishedInformation(other.text());
						flag++;
					}else if(flag == 1){
						docu.setRealName(other.text());
						flag++;
					}else{
						docu.setDiscription(other.text());
					}
				}

				list.add(docu);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}


}
