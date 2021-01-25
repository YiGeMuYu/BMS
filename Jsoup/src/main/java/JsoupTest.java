import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class JsoupTest {
	public static void main(String[] args) {
		queryDocument("北斗");
	}
	public static void queryDocument(String documentName){
		WebClient browser = new WebClient();
		browser.getOptions().setCssEnabled(false);
		browser.getOptions().setJavaScriptEnabled(true);
		browser.getOptions().setThrowExceptionOnScriptError(false);
		String url = "http://oar.nstl.gov.cn/Paper/Search?searchKey="+documentName+"&x=0&y=0";
		try {
			HtmlPage htmlPage = browser.getPage(url);
			browser.waitForBackgroundJavaScript(3000);
			Document document = Jsoup.parse(htmlPage.asXml());
			Element paper = document.getElementById("paper");
			System.out.println(paper);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

